package com.forms.datapipe.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.config.datatype.DataTypeConfig;
import com.forms.datapipe.config.datatype.DataTypeConfigRuleSet;
import com.forms.datapipe.config.input.CsvFileInputConfig;
import com.forms.datapipe.config.input.DatabaseInputConfig;
import com.forms.datapipe.config.input.ExcelInputConfig;
import com.forms.datapipe.config.input.FixedFileInputConfig;
import com.forms.datapipe.config.input.FixedFileInputExConfig;
import com.forms.datapipe.config.input.LineFileInputConfig;
import com.forms.datapipe.config.output.CsvFileOutputConfig;
import com.forms.datapipe.config.output.DatabaseOutputConfig;
import com.forms.datapipe.config.output.ExcelOutputConfig;
import com.forms.datapipe.config.output.FixedFileOutput4DetailConfig;
import com.forms.datapipe.config.output.FixedFileOutput4MultiConfig;
import com.forms.datapipe.config.output.FixedFileOutputConfig;
import com.forms.datapipe.config.output.FixedFileOutputEmConfig;
import com.forms.datapipe.config.output.FixedFileOutputExConfig;
import com.forms.datapipe.config.valve.CaseValveConfig;
import com.forms.datapipe.config.valve.CaseValveConfigRuleSet;
import com.forms.datapipe.config.valve.ExportValveConfig;
import com.forms.datapipe.config.valve.ExportValveConfigRuleSet;
import com.forms.datapipe.config.valve.FilterValveConfig;
import com.forms.datapipe.config.valve.FilterValveConfigRuleSet;
import com.forms.datapipe.config.valve.ImportValveConfig;
import com.forms.datapipe.config.valve.ImportValveConfigRuleSet;
import com.forms.datapipe.config.valve.ValidateValveConfig;
import com.forms.datapipe.config.valve.ValidateValveConfigRuleSet;
import com.forms.datapipe.exception.DataPipeException;

/**
 * config Factory,get all the config from this class
 * 
 * JAXB + Digesiter
 * 
 * @author cxl
 * 
 */
public class DataPipeConfigFactory
{
	public final static Object LOCK = new Object();

	private static Log log = LogFactory.getLog(DataPipeConfigFactory.class);

	private static String configFilePath;

	/**
	 * 
	 * @param path --
	 *            configFilePath
	 * @throws DataPipeException
	 */
	public static void init(String path) throws DataPipeException
	{
//		if (configFilePath != null)
//			throw new DataPipeException(
//					" [ DataPipeConfigFactory has been inited!  ] ");

		configFilePath = path;
	}

	private static File getFile(String fileName) throws DataPipeException
	{
		if (configFilePath == null)
			throw new DataPipeException(" [ Call method 'init' first!  ] ");
		
		/**update by zt
		 * return new file(fileName) when fileName is full path 
		*/
		File file = new File(fileName);
		if(file.exists())
		{
			return file;
		}else
		{
			String realFilePath = configFilePath + "/" + fileName;
			return new File(realFilePath);
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T readConfig(String fileName, RuleSet ruleSet)
			throws DataPipeException
	{
		if (log.isDebugEnabled())
			log.debug(" [ read config: " + fileName + " ] ");

		Digester digester = new Digester();
		digester.setValidating(false);
		digester.addRuleSet(ruleSet);
		try
		{
			return (T) digester.parse(getFile(fileName));
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T readConfig(String fileName, Class<T> clazz)
			throws DataPipeException
	{
		if (log.isDebugEnabled())
			log.debug(" [ read config: " + fileName + " ] ");

		try
		{
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller u = context.createUnmarshaller();
			return (T) u.unmarshal(getFile(fileName));
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}

	private static Map<String, Object> configCache = new HashMap<String, Object>();

	@SuppressWarnings("unchecked")
	public static Object getConfig(String fileName, RuleSet ruleSet)
			throws DataPipeException
	{
		if (log.isDebugEnabled())
			log.debug(" [ getConfig: " + fileName + " ] ");

		Object config = configCache.get(fileName);
		if (config == null)
		{
			config = readConfig(fileName, ruleSet);
			configCache.put(fileName, config);
		}
		return config;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getConfig(String fileName, Class<T> clazz)
			throws DataPipeException
	{
		if (log.isDebugEnabled())
			log.debug(" [ getConfig: " + fileName + " ] ");

		T config = (T) configCache.get(fileName);
		if (config == null)
		{
			config = readConfig(fileName, clazz);
			configCache.put(fileName, config);
		}
		return config;
	}

	public static void remove(String fileName)
	{
		configCache.remove(fileName);
	}

	/*
	 * datatype-config.xml
	 */
	public static DataTypeConfig getDataTypeConfig(String fileName)
			throws DataPipeException
	{
		return (DataTypeConfig) getConfig(fileName, new DataTypeConfigRuleSet());
	}

	/*
	 * pipe-config.xml
	 */
	public static PipeConfig getPipeConfig(String fileName)
			throws DataPipeException
	{
		return (PipeConfig) getConfig(fileName, new PipeConfigRuleSet());
	}
	
	/*
	 * export-valve-config.xml
	 */
	public static ExportValveConfig getExportValveConfig(String fileName)
			throws DataPipeException
	{
		return (ExportValveConfig) getConfig(fileName,
				new ExportValveConfigRuleSet());
	}

	/*
	 * import-valve-config.xml
	 */
	public static ImportValveConfig getImportValveConfig(String fileName)
			throws DataPipeException
	{
		return (ImportValveConfig) getConfig(fileName,
				new ImportValveConfigRuleSet());
	}
	
	/*
	 * validate-valve-config.xml
	 */
	public static ValidateValveConfig getValidateValveConfig(String fileName)
			throws DataPipeException
	{
		return (ValidateValveConfig) getConfig(fileName,
				new ValidateValveConfigRuleSet());
	}
	
	// input and output config read function beging
	/*
	 * database-input-config.xml
	 */
	public static DatabaseInputConfig getDatabaseInputConfig(String fileName)
			throws DataPipeException
	{
		return getConfig(fileName, DatabaseInputConfig.class);
	}

	/*
	 * fixedfile-input-config.xml
	 */
	public static FixedFileInputConfig getFixedFileInputConfig(String fileName)
			throws DataPipeException
	{
		return getConfig(fileName, FixedFileInputConfig.class);
	}

	public static LineFileInputConfig getUnfixedLineFileInputConfig(
			String fileName) throws DataPipeException
	{
		return getConfig(fileName, LineFileInputConfig.class);
	}

	/*
	 * linefile-input-config.xml
	 */
	public static LineFileInputConfig getLineFileInputConfig(String fileName)
			throws DataPipeException
	{
		return getConfig(fileName, LineFileInputConfig.class);
	}

	/*
	 * database-output-config.xml
	 */
	public static DatabaseOutputConfig getDatabaseOutputConfig(String fileName)
			throws DataPipeException
	{
		return getConfig(fileName, DatabaseOutputConfig.class);
	}

	/*
	 * fixedfile-output-config.xml
	 */
	public static FixedFileOutputConfig getFixedFileOutputConfig(String fileName)
			throws DataPipeException
	{
		return getConfig(fileName, FixedFileOutputConfig.class);
	}

	/*
	 * fixedfile-output-config-em.xml
	 */
	public static FixedFileOutput4MultiConfig getFixedFileOutput4MultiConfig(
			String fileName) throws DataPipeException
	{
		return getConfig(fileName, FixedFileOutput4MultiConfig.class);
	}

	/*
	 * fixedfile-output-config.xml for detail records
	 */
	public static FixedFileOutput4DetailConfig getFixedFileOutput4DetailConfig(
			String fileName) throws DataPipeException
	{
		return getConfig(fileName, FixedFileOutput4DetailConfig.class);
	}
	
	public static FixedFileOutputConfig getFixedFileOutput4PDConfig(
			String fileName) throws DataPipeException
	{
		return (FixedFileOutputConfig) getConfig(fileName,FixedFileOutputConfig.class);
	}

	public static FixedFileOutputConfig getErrorLogOutputConfig(
			String fileName) throws DataPipeException
	{
		return getConfig(fileName, FixedFileOutputConfig.class);
	}
	
	 /*
     * excel-input-config.xml
     */
    public static ExcelInputConfig getExcelInputConfig(String fileName)
        throws DataPipeException
    {
        return (ExcelInputConfig) getConfig(fileName, ExcelInputConfig.class);
    }
    
    /*
     * excel-output-config.xml
     */
    public static ExcelOutputConfig getExcelOutputConfig(String fileName)
        throws DataPipeException
    {
        return (ExcelOutputConfig) getConfig(fileName, ExcelOutputConfig.class);
    }
    
    /*
     * filter-valve-config.xml
     */
    public static FilterValveConfig getFilterValveConfig(String fileName)
        throws DataPipeException
    {
        return (FilterValveConfig)getConfig(fileName, new FilterValveConfigRuleSet());
    }
    
    /*
     * case-valve-config.xml
     */
    public static CaseValveConfig getCaseValveConfig(String fileName)
        throws DataPipeException
    {
        return (CaseValveConfig)getConfig(fileName, new CaseValveConfigRuleSet());
    }
    
    /*
     * loopfile-output-config.xml
     */
    public static FixedFileOutputExConfig getFixedFileOutputExConfig(String fileName)
        throws DataPipeException
    {
        return (FixedFileOutputExConfig) getConfig(fileName, FixedFileOutputExConfig.class);
    }
    
    /*
     * csvfile-output-config.xml
     */
    public static CsvFileOutputConfig getCsvFileOutputConfig(String fileName)
        throws DataPipeException
    {
        return (CsvFileOutputConfig) getConfig(fileName, CsvFileOutputConfig.class);
    }
    
    /*
     * loopfile-input-config.xml
     */
    public static FixedFileInputExConfig getFixedFileInputExConfig(String fileName)
        throws DataPipeException
    {
        return (FixedFileInputExConfig) getConfig(fileName, FixedFileInputExConfig.class);
    }
    
    /*
     * csvfile-input-config.xml
     */
    public static CsvFileInputConfig getCsvFileInputConfig(String fileName)
        throws DataPipeException
    {
        return (CsvFileInputConfig) getConfig(fileName, CsvFileInputConfig.class);
    }
    
    public static FixedFileOutputEmConfig getFixedFileOutputEmConfig(
			String fileName) throws DataPipeException
	{
		return getConfig(fileName, FixedFileOutputEmConfig.class);
	}
}
