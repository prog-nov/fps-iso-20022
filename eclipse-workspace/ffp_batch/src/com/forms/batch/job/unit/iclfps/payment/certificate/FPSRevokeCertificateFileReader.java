package com.forms.batch.job.unit.iclfps.payment.certificate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBElement;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_cel_001_001_01.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_cel_001_001_01.GroupHeader;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_cel_001_001_01.RevokedCertificate;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.FpsMessageEnvelope;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.core.msg.iclfps.FFPHkiclMessageConverter;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;
import org.springframework.util.Base64Utils;

public class FPSRevokeCertificateFileReader extends BatchBaseJob {
	private static String FPS_CEL_001_MESSAGE_IDENTIFIER = "fps.cel.001.001.01";
	private static Connection conn = null;

	public void init() throws BatchJobException {

	}

	@Override
	public boolean execute() throws BatchJobException {
		try {
			String fileRootPath = this.batchData
					+ this.actionElement.element("parameters").elementText("local-file-path");

			ArrayList<String> resFiles = getDirectoryFile(fileRootPath);

			if (resFiles != null && resFiles.size() >= 1) {
				HashMap<String, String> msgsMap = getMsgsFromFile(resFiles);

				if (msgsMap != null) {
					conn = ConnectionManager.getInstance().getConnection();
					storeMsgToDB(msgsMap);
				} else {
					throw new Exception("No Message Data Found!");
				}

				for (int i = 0; i < resFiles.size(); i++) {
					deleteFile(resFiles.get(i));
				}
			}

		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
			throw new BatchJobException("Error Occurred In FPSRevokeCertificateFileReader!");
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					this.batchLogger.error(e.getMessage());
					throw new BatchJobException("Error Occurred In Closing The Database!");
				}
			}
		}

		return true;
	}

	// get content of the message files
	private HashMap<String, String> getMsgsFromFile(ArrayList<String> resFiles) {

		HashMap<String, String> msgsMap = new HashMap<>();

		for (int i = 0; i < resFiles.size(); i++) {
			String absolutePath = resFiles.get(i);
			String[] realPathArr = absolutePath.split("\\\\");
			String fileName = realPathArr[realPathArr.length - 1];

			String content = readLineFromFile(absolutePath);

			msgsMap.put(fileName, content);

		}

		return msgsMap;
	}

	// get files list from specified directory
	public ArrayList<String> getDirectoryFile(String dirPath) {
		String pattern = this.actionElement.element("parameters").elementText("filename-pattern")
				.replaceAll("#\\{clearingcode\\}", this.clearingCode).replaceAll("\\(", "").replaceAll("\\)", "");
		ArrayList<String> retFiles = new ArrayList<>();
		File dir = new File(dirPath);

		if (dir.exists()) {
			File[] cFiles = dir.listFiles();
			for (File f : cFiles) {
				if (f.isDirectory()) {
					retFiles.addAll(getDirectoryFile(f.getAbsolutePath()));
				} else {
					String[] realPathArr = f.getAbsolutePath().split("\\\\");
					String fileName = realPathArr[realPathArr.length - 1];
					if (fileName.matches("^" + pattern + "$")) {
						retFiles.add(f.getAbsolutePath());
						this.batchLogger.info(String.format("The File %s Is Found", f.getAbsolutePath()));
					}
				}
			}
		}

		return retFiles;
	}

	public String readLineFromFile(String file) {
		BufferedReader br = null;
		StringBuffer strBuf = new StringBuffer();

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));
			String content = "";
			while ((content = br.readLine()) != null) {
				strBuf.append(content);
			}
		} catch (IOException e) {
			this.batchLogger.error(e.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				this.batchLogger.error(e.getMessage());
			}
		}

		return strBuf.toString();
	}

	// store msg to DB
	private void storeMsgToDB(HashMap<String, String> msgsMap) {
		Date insertStartTime = Calendar.getInstance().getTime();
		this.batchLogger.info(String.format("Start To Insert Into tb_bh_revoke_ceri At %s", insertStartTime));
		Iterator<Entry<String, String>> it = msgsMap.entrySet().iterator();
		String prefixion = "ceri";

		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			String fileName = (String) entry.getKey();
			String content = (String) entry.getValue();
			try {
				conn.setAutoCommit(false);
				FpsMessageEnvelope fpsMsg = FFPHkiclMessageConverter.parseObject(content);

				List<JAXBElement<?>> jaxbElems = fpsMsg.getFpsPylds().getBizData().get(0).getContent();
				BusinessApplicationHeaderV01 header = (BusinessApplicationHeaderV01) jaxbElems.get(0).getValue();
				Object document = jaxbElems.get(1).getValue();
				if (FPS_CEL_001_MESSAGE_IDENTIFIER.equals(header.getMsgDefIdr().toLowerCase())) {

					GroupHeader grpHdr = ((Document) document).getCertNoti().getGrpHdr();
					List<RevokedCertificate> revokedCertificateList = ((Document) document).getCertNoti()
							.getRevokedCerts().getRevokedCert();
					int revokedCertNum = Integer.valueOf(grpHdr.getTotalRevokedCert());

					if (revokedCertNum != revokedCertificateList.size()) {
						throw new Exception("The Number Of Certificates Is Not Matched!");
					}

					// insert into tb_tx_fpsadri_temp
					String insert_fpsceri_temp_sql = "INSERT INTO tb_bh_revoke_ceri(CID, FILE_NAME, CREATE_TS, `STATUS`, ECERT_KEY, REVOKED_TS, MSG_ID, BATCH_ID, RANKING) VALUES(?,?,NOW(),?,?,?,?,?,?); ";

					conn.setAutoCommit(false);
					for (int i = 0; i < revokedCertificateList.size(); i++) {

						ArrayList<Object> fpsceri_insert_list = new ArrayList<>();
						fpsceri_insert_list.add(prefixion + (new Date()).getTime());
						fpsceri_insert_list.add(fileName);
						fpsceri_insert_list.add("I");
						fpsceri_insert_list.add(Base64Utils.encodeToString(revokedCertificateList.get(i).getECert()));
						fpsceri_insert_list
								.add(revokedCertificateList.get(i).getRevocationDate().toGregorianCalendar().getTime());
						fpsceri_insert_list.add(grpHdr.getMsgId());
						fpsceri_insert_list.add(fpsMsg.getBtchInf().getBtchId());
						fpsceri_insert_list.add(i);

						EntityManager.update(conn, insert_fpsceri_temp_sql, fpsceri_insert_list.toArray());

					}
					conn.commit();
					conn.setAutoCommit(true);
				}

			} catch (SQLException e) {
				this.batchLogger.error(e.getMessage());
			} catch (Exception e) {
				this.batchLogger.error(e.getMessage());
			}

		}

		Date insertEndTime = Calendar.getInstance().getTime();
		this.batchLogger.info(String.format("Finish Inserting Into tb_bh_revoke_ceri At %s", insertEndTime));
		this.batchLogger.info(String.format("Inserting Data Into tb_bh_revoke_ceri Use %.3f Seconds",
				(double) (insertEndTime.getTime() - insertStartTime.getTime()) / 1000));

	};

	// delete file
	private void deleteFile(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new IOException(String.format("The File(%s) Is Not Exists!", filePath));
		}

		file.delete();
		this.batchLogger.info(String.format("The File(%s) Has Been Deleted!", filePath));
	}
}
