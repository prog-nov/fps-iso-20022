package com.forms.ffp.core.msg.iclfps;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.xb.xmlconfig.NamespacePrefixList;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.FpsMessageEnvelope;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ObjectFactory;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.adaptor.jaxb.iclfps.xmldsig.SignatureType;
import com.forms.ffp.adaptor.jaxb.iclfps.xmldsig.X509DataType;
import com.forms.ffp.adaptor.prefix.FFPIclfpsMsgNamespacePrefixMapper;
import com.forms.ffp.adaptor.prefix.FFPMsgNamespacePrefixFactory;
import com.forms.ffp.adaptor.xml.writer.FFPCDataXMLStreamWriter;
import com.forms.ffp.core.config.keystore.FFPKeystoreConfig;
import com.forms.ffp.core.keystore.FFPX509KeySelector;
import com.forms.ffp.core.service.FFPKeyStoreSvc;
import com.forms.ffp.core.utils.FFPSecurityUtils;
import com.forms.ffp.core.utils.FFPXMLUtils;
import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;

public class FFPHkiclMessageConverter
{
	private static Logger _logger = LoggerFactory.getLogger(FFPHkiclMessageConverter.class);

	private static final String _fpsNsUrlsReplmnt = "xmlns:fps=\"urn:hkicl:fps:xsd:fps.envelope.01\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:hkicl:fps:xsd:fps.envelope.01 fps.envelope.01.xsd\"";
	private static final String _fpsNameSpace = "fps";
	private static final String _documentNameSpace = "doc";
	private static final String _fpsMsgTag = "FpsMsg";
	private static final String _documentTag = "Document";
	private static final String _headerTag = "AppHdr";

	public static String packageXml(FpsMessageEnvelope fpsMessageEnvelope) throws Exception
	{
		return packageXml(fpsMessageEnvelope, new ValidationEventHandler()
		{
			public boolean handleEvent(ValidationEvent event)
			{
				if (event.getMessage().contains("http://www.w3.org/2000/09/xmldsig#"))
				{
					return true;
				}
				throw new RuntimeException(String.format("Marshal XML Error: %s (Object: %s)",
						new Object[] { event.getMessage(), (event.getLocator() != null) && (event.getLocator().getObject() != null) ? event.getLocator().getObject().getClass() : "null" }));
			}
		});
	}

	public static String packageXml(FpsMessageEnvelope fpsMessageEnvelope, ValidationEventHandler handler) throws Exception
	{
		String xml = null;
		Marshaller marshaller = null;

		try
		{
			JAXBContext context = JAXBContext.newInstance(FFPJaxbConstants.JAXB_CONTEXT_PACKAGE_ICLFPS);
			marshaller = context.createMarshaller();
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new FFPIclfpsMsgNamespacePrefixMapper());
			marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
			marshaller.setProperty("jaxb.encoding", "UTF-8");
			marshaller.setEventHandler(handler);
			JAXBElement<FpsMessageEnvelope> e = (new ObjectFactory()).createFpsMsg(fpsMessageEnvelope);
			StringWriter sw = new StringWriter();
			XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(sw);
			marshaller.marshal(e, new FFPCDataXMLStreamWriter(new IndentingXMLStreamWriter(xmlStreamWriter)));
			xml = sw.toString();
		} catch (PropertyException arg20)
		{
			_logger.error("marshal PropertyException: " + arg20.getMessage());
			arg20.printStackTrace();
			throw new RuntimeException(String.format("PropertyException: %s", new Object[] { arg20.getMessage() }));
		} catch (JAXBException arg21)
		{
			_logger.error("marshal JAXBException: " + arg21.getMessage());
			arg21.printStackTrace();
			throw new RuntimeException(String.format("JAXBException: %s", new Object[] { arg21.getMessage() }));
		} catch (Exception arg22)
		{
			_logger.error("marshal Exception: " + arg22.getMessage());
			arg22.printStackTrace();
			throw new RuntimeException(String.format("Exception: %s", new Object[] { arg22.getMessage() }));
		}
		return xml;
	}

	public static FpsMessageEnvelope parseObject(File file) throws Exception
	{
		SAXReader reader = new SAXReader();
		org.dom4j.Document document = reader.read(file);
		return parseObject(document.asXML());
	}
	
	public static FpsMessageEnvelope parseObject(String message) throws Exception
	{
		return parseObject(message, new ValidationEventHandler()
		{
			public boolean handleEvent(ValidationEvent event)
			{
				if ((event.getSeverity() == 1) || (event.getSeverity() == 2))
				{
					throw new RuntimeException(String.format("Unmarshal XML Error: %s (Object: %s)",
							new Object[] { event.getMessage(), (event.getLocator() != null) && (event.getLocator().getObject() != null) ? event.getLocator().getObject().getClass() : "null" }));
				}
				return true;
			}
		});
	}

	public static FpsMessageEnvelope parseObject(String fpsMsg, ValidationEventHandler eventHandler) throws Exception
	{
		FpsMessageEnvelope fpsMessageEnvelope = null;
		XMLStreamReader xsr = null;
		Unmarshaller unmarshaller = null;

		try
		{
			XMLInputFactory e = XMLInputFactory.newFactory();
			xsr = e.createXMLStreamReader(new StreamSource(new StringReader(fpsMsg)));

			JAXBContext context = JAXBContext.newInstance(FFPJaxbConstants.JAXB_CONTEXT_PACKAGE_ICLFPS);
			unmarshaller = context.createUnmarshaller();
			unmarshaller.setEventHandler(eventHandler);
			JAXBElement displayExpt1 = (JAXBElement) unmarshaller.unmarshal(xsr);
			if (displayExpt1.getValue() instanceof FpsMessageEnvelope)
			{
				fpsMessageEnvelope = (FpsMessageEnvelope) JAXBIntrospector.getValue(displayExpt1);
			}
		} catch (XMLStreamException arg23)
		{
			_logger.error("unmarshal XMLStreamException: " + arg23.getMessage());
			arg23.printStackTrace();
			throw new RuntimeException(String.format("XMLStreamException: %s", new Object[] { arg23.getMessage() }));
		} catch (JAXBException arg24)
		{
			Object displayExpt = arg24;
			if (arg24.getLinkedException() != null)
			{
				displayExpt = arg24.getLinkedException();
			}

			_logger.error(String.format("unmarshal %s: %s", new Object[] { displayExpt.getClass().getSimpleName(), ((Throwable) displayExpt).getMessage() }));
			arg24.printStackTrace();
			throw new RuntimeException(String.format("%s: %s", new Object[] { displayExpt.getClass().getSimpleName(), ((Throwable) displayExpt).getMessage() }));
		} catch (Exception arg25)
		{
			_logger.error("unmarshal Exception: " + arg25.getMessage());
			arg25.printStackTrace();
			throw new RuntimeException(String.format("Exception: %s", new Object[] { arg25.getMessage() }));
		}

		return fpsMessageEnvelope;
	}

	public X509Certificate getX509Certificate(SignatureType signatureType)
	{
		Iterator<Object> localIterator1 = signatureType.getKeyInfo().getContent().iterator();
		while (localIterator1.hasNext())
		{
			Object content = localIterator1.next();
			if ((!(content instanceof JAXBElement)) || (!(((JAXBElement) content).getValue() instanceof X509DataType)))
			{
				break;
			}
			X509DataType X509Data = (X509DataType) ((JAXBElement) content).getValue();
			Iterator<Object> localIterator2 = X509Data.getX509IssuerSerialOrX509SKIOrX509SubjectName().iterator();
			while (localIterator2.hasNext())
			{
				Object object = localIterator2.next();
				if ((object instanceof JAXBElement))
				{
					JAXBElement<?> el = (JAXBElement) object;
					if (el.getName().getLocalPart().equals("X509Certificate"))
					{
						byte[] certificate = (byte[]) el.getValue();
						try
						{
							CertificateFactory cf = CertificateFactory.getInstance("X.509");
							ByteArrayInputStream bis = new ByteArrayInputStream(certificate);
							return (X509Certificate) cf.generateCertificate(bis);
						} catch (CertificateException e)
						{
							throw new RuntimeException(e);
						}
					}
				}
			}
		}
		return null;
	}

	protected static String getNsUrlByNsFrmNsMap(String ns, Map<String, String> nsMap)
	{
		Iterator<Map.Entry<String, String>> iterator = nsMap.entrySet().iterator();
		while (iterator.hasNext())
		{
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
			if (ns.equals(entry.getValue()))
			{
				return (String) entry.getKey();
			}
		}
		return null;
	}

	public static String getDocumentNs(String xml)
	{
		Matcher m = Pattern.compile("<([a-zA-Z0-9_\\-\\.]+):" + _documentTag + "(\\s+" + "[a-zA-Z0-9_\\-\\.]" + "+:" + "[a-zA-Z0-9_\\-\\.]" + "+=\".+\")?>").matcher(xml);
		if (m.find())
		{
			return m.group(1);
		}
		return null;
	}

	public static String replaceTagNs(String xml, String oldTagNs, String newTagNs)
	{
		String result = xml.replaceAll("<" + oldTagNs + ":", "<" + newTagNs + ":").replaceAll("</" + oldTagNs + ":", "</" + newTagNs + ":").replaceAll("\\sxmlns:" + oldTagNs, " xmlns:" + newTagNs);
		return result;
	}

	public static String addNsUrlToRootTags(String xml)
	{
		return addNsUrlToRootTagsByTagNms(xml, new String[] { _headerTag, _documentTag });
	}

	public static String addNsUrlToRootTagsByTagNms(String xml, String[] rootTagNms)
	{
		String rootTagExp = StringUtils.join(rootTagNms, "|");
		Matcher m = Pattern.compile("(<([a-zA-Z0-9_\\-\\.]+):(" + rootTagExp + "))(>)").matcher(xml);
		StringBuffer sb = new StringBuffer();
		while (m.find())
		{
			String nsNm = m.group(2);
			String nsURL = getNsUrlByNsFrmNsMap(nsNm, FFPMsgNamespacePrefixFactory._namespaceMap);
			String newTag = m.group(1) + " xmlns:" + nsNm + "=\"" + nsURL + "\"" + m.group(4);
			m.appendReplacement(sb, newTag);
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public static String replaceFpsMsgNsUrls(String xml, String fpsMsgNsUrlsReplmnt)
	{
		Matcher m = Pattern.compile("(<" + _fpsNameSpace + ":" + _fpsMsgTag + ")([\\s\\S]*?)(>)").matcher(xml);
		if (m.find())
		{
			StringBuffer sb = new StringBuffer();
			String newTag = m.group(1) + " " + fpsMsgNsUrlsReplmnt + m.group(3);
			m.appendReplacement(sb, newTag);
			m.appendTail(sb);
			xml = sb.toString();
		}
		return xml;
	}

	public static String makeupRealTimeXml(String xml)
	{
		String result = replaceFpsMsgNsUrls(xml, _fpsNsUrlsReplmnt);
		result = addNsUrlToRootTags(result);
		result = replaceTagNs(result, getDocumentNs(result), _documentNameSpace);
		return result;
	}

	public static String makeupMultiBizDataXml(String xml)
	{
		String documentRegExp = "<(([a-zA-Z0-9_\\-\\.]+):" + _documentTag + ")(\\s+" + "[a-zA-Z0-9_\\-\\.]" + "+:" + "[a-zA-Z0-9_\\-\\.]" + "+=\".+\")?>([\\s\\S]*?)</\\1>";
		String result = replaceFpsMsgNsUrls(xml, _fpsNsUrlsReplmnt);
		result = addNsUrlToRootTags(result);
		StringBuffer sb = new StringBuffer();

		Matcher m = Pattern.compile(documentRegExp).matcher(Matcher.quoteReplacement(result));
		while (m.find())
		{
			String oldbizDataXml = m.group();
			String newbizDataXml = replaceTagNs(oldbizDataXml, getDocumentNs(oldbizDataXml), _documentNameSpace);
			m.appendReplacement(sb, newbizDataXml);
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public static String signXml(String message) throws Exception
	{
		FFPKeystoreConfig keystoreConfig = FFPKeyStoreSvc.getInstance().getKeysotreConfig(FFPKeyStoreSvc.PARTICIPANT_KEYSTORE_NAME);
		return signXml(message, keystoreConfig.getFilepath(), keystoreConfig.getAlias(), keystoreConfig.getPassword(), keystoreConfig.getType(), DigestMethod.SHA256, SignatureMethod.RSA_SHA1);
	}
	
	/*
	 * keyStoreType=JKS digestMethod=DigestMethod.SHA256 signatureMethod=
	 */
	private static String signXml(String msg, Path keystorePath, String keyAlias, String password, String keyStoreType, String digestMethod, String signatureMethod)
	{

		if ((keystorePath == null) || (keyAlias == null) || (password == null))
		{
			_logger.warn(String.format("One of keystore info is null: keystore name=%s, key alias=%s, password=%s", new Object[] { keystorePath, keyAlias, password }));
			return msg;
		}
		Document doc = FFPXMLUtils.string2Documnet(msg);
		if (doc == null)
		{
			return msg;
		}
		try
		{
			Resource r = new FileSystemResource(keystorePath.toString());
			if (!r.exists())
			{
				throw new FileNotFoundException(keystorePath.toString());
			}
			KeyStore keyStore = FFPSecurityUtils.loadKeyStore(r, password, keyStoreType);
			KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(keyAlias, new KeyStore.PasswordProtection(password.toCharArray()));

			PrivateKey privateKey = keyEntry.getPrivateKey();

			X509Certificate cert = (X509Certificate) keyEntry.getCertificate();
			PublicKey myPublicKey = cert.getPublicKey();
			System.out.println("FFP public key:[" + myPublicKey + "]");
			String type = cert.getType();
			System.out.println("Type:[" + type + "]");

			XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
			XMLStructure structure = null;
			Reference ref = fac.newReference("", fac.newDigestMethod(digestMethod, null), Collections.singletonList(fac.newTransform(Transform.ENVELOPED, structure)), null, null);

			SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE_WITH_COMMENTS, structure), fac.newSignatureMethod(signatureMethod, null),
					Collections.singletonList(ref));

			DOMSignContext dsc = new DOMSignContext(privateKey, doc.getDocumentElement());

			KeyInfoFactory kif = fac.getKeyInfoFactory();
			List<X509Certificate> x509Content = new ArrayList<X509Certificate>();
			x509Content.add(cert);

			X509Data xd = kif.newX509Data(x509Content);
			KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

			XMLSignature signature = fac.newXMLSignature(si, ki);
			signature.sign(dsc);

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			t.transform(new DOMSource(doc), new StreamResult(os));

			return new String(os.toString("UTF-8"));
		} catch (Exception e)
		{
			_logger.error(String.format("keystore=%s,keyAlias=%s,message=%s", new Object[] { keystorePath, keyAlias, msg }), e);
			throw new RuntimeException(
					String.format("Failed to sign message by keystore=%s,keyAlias=%s. %s: %s", new Object[] { keystorePath, keyAlias, e.getClass().getSimpleName(), e.getMessage() }));
		}
	}

	public static boolean validateDsignXML(NodeList signatureNodeList)
	{
		boolean isValid = true;
		try
		{
			if (signatureNodeList != null)
			{
				XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
				if (_logger.isInfoEnabled())
				{
					_logger.info("validateDsignXML >> Node list length: {}", Integer.valueOf(signatureNodeList.getLength()));
				}
				for (int k = 0; k < signatureNodeList.getLength(); k++)
				{
					DOMValidateContext valContext = new DOMValidateContext(new FFPX509KeySelector(), signatureNodeList.item(k));

					valContext.setProperty("javax.xml.crypto.dsig.cacheReference", Boolean.TRUE);

					XMLSignature signature = fac.unmarshalXMLSignature(valContext);

					isValid &= signature.validate(valContext);
				}
				if (_logger.isInfoEnabled())
				{
					_logger.info("validateDsignXML >> Completed. isValid={}", Boolean.valueOf(isValid));
				}
			} else
			{
				isValid = false;
			}
		} catch (XMLSignatureException e)
		{
			_logger.debug("Cannot validate the XML", e);
			isValid = false;
		} catch (MarshalException e)
		{
			_logger.debug("Cannot validate the XML", e);
			isValid = false;
		}
		if (!isValid)
		{
			_logger.debug("Invalid XML Sigature.");
		}
		return isValid;
	}

	public static boolean isRealtimeMessage(ISO20022BusinessDataV01 bizData)
	{
		if ((bizData == null) || (bizData.getContent().get(0) == null))
		{
			return true;
		}
		BusinessApplicationHeaderV01 head = (BusinessApplicationHeaderV01)bizData.getContent().get(0).getValue();
		String msgDefId = head.getMsgDefIdr();
		if (msgDefId.equals(FFPJaxbConstants.JAXB_MSG_TYPE_PACS_008))
		{
			com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Document document = (com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Document) bizData.getContent().get(1).getValue();
			if ((document != null) && (document.getFIToFICstmrCdtTrf() != null) && (document.getFIToFICstmrCdtTrf().getCdtTrfTxInf() != null)
					&& (document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().size() > 0))
			{
				com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.CreditTransferTransaction251 tx = (com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.CreditTransferTransaction251) document
						.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0);
				if ((tx != null) && (tx.getPmtTpInf() != null) && (tx.getPmtTpInf().getLclInstrm() != null))
				{
					if (!"PERFORM_PYE_VRF".equals(tx.getPmtTpInf().getLclInstrm().getPrtry()))
					{
						return false;
					}
				}
			}
		}
		return true;
	}

	public static NodeList getSignatureNodeList(Document doc) throws Exception
	{
		NodeList nl = doc.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Signature");
		if (nl.getLength() == 0)
		{
			_logger.debug("Cannot find <Signature> element.");
			return null;
		}
		return nl;
	}
}
