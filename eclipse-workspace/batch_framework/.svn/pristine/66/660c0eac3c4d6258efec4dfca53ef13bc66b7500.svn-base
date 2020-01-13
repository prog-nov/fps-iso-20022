package com.forms.datapipe.util;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.forms.datapipe.exception.DataPipeException;

/**
 * DataPipe utils
 * 
 * @author cxl
 *
 */
public class DataPipeUtils
{
	@SuppressWarnings("unchecked")
    public static <T> T newInstance(String className) throws DataPipeException
    {
        try
        {
            return (T) Class.forName(className).newInstance();
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new DataPipeException("[ Error occurs in newInstance for '"
                + className + "', detail: " + e.getMessage() + "  ]");
        }
    }
	
	/**
     * buffer
     */
    public static final int BUFFER_SIZE = 8 * 1024;

    /**
     * write
     * 
     * @param is -- input stream
     * @param os -- output stream
     * @throws IOException
     */
    public static void write(InputStream is, final OutputStream os)
        throws IOException
    {
        read(is, new ReadActionHandler()
        {
            public void handle(int readNum, byte[] buffer) throws IOException
            {
                os.write(buffer, 0, readNum);
            }
        });
    }

    /**
     * ReadActionHandler  handle
     * 
     * @param is --
     *            input stream
     * @param handler --
     * @throws IOException
     */
    public static void read(InputStream is, ReadActionHandler handler)
        throws IOException
    {
        int readNum = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        BufferedInputStream bis = new BufferedInputStream(is);
        while ((readNum = bis.read(buffer, 0, buffer.length)) != -1)
        {
            handler.handle(readNum, buffer);
        }
    }

    /**
     * @author cxl
     * 
     */
    public interface ReadActionHandler
    {
        /**
         * 
         * @param readNum --
         * @param buffer --
         * @throws IOException
         */
        public void handle(int readNum, byte[] buffer) throws IOException;
    }

    // -------------------------- base --------------------------------

    /**
     * @param closeable
     * @throws DataPipeException 
     */
    public static void close(Closeable closeable) throws DataPipeException
    {
        if (closeable == null) return;

        try
        {
            closeable.close();
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
            throw new DataPipeException(ioe);
        }
    }
    
    public static final String PATTERN_STRING = "#\\{.*?}";

    public static final Pattern PATTERN = Pattern.compile(PATTERN_STRING,
        Pattern.DOTALL);

    /**
     * 
     * @param content
     * @param parameters
     * @return
     */
    public static String replaceParameters(String content,
        Map<String, String> parameters) throws DataPipeException
    {
        StringBuffer sb = new StringBuffer();
        Matcher m = PATTERN.matcher(content);
        int lastIndex = 0;
        while (m.find())
        {
            String tag = m.group();
            String name = tag.substring(2, tag.length() - 1);
            String value = parameters.get(name);
            if (value == null)
                throw new DataPipeException(" [ Parameter '" + name
                    + "' not found. ] ");

            sb.append(content.substring(lastIndex, m.start()));
            sb.append(value);
            lastIndex = m.end();
        }
        sb.append(content.substring(lastIndex));

        return sb.toString();
    }
    
    /**
     * fill String
     * 
     * @param fillType
     * @param src
     * @param fillString
     * @param length
     * @return
     * @throws Exception
     */
    public static String fill(String fillType, String src, String fillString,
        int length) throws Exception
    {
        if (fillType == null || fillString == null || src == null)
        {
            throw new Exception(" [ At least one of parameters is null! ] ");
        }

        if ("Left".equalsIgnoreCase(fillType)
            && "Right".equalsIgnoreCase(fillType))
        {
            throw new Exception(" [ The fillType must be Right or Left! ] ");
        }

        int fillLength = length - src.length();
        if (fillLength == 0)
        {
            // not need to fill
            return src;
        } else if (fillLength < 0)
        {
            throw new Exception(
                " [ The src' length is longer than expected ! ] ");
        }

        StringBuffer sb = new StringBuffer();
        while (sb.length() < fillLength)
        {
            sb.append(fillString);
        }
        sb.delete(fillLength, sb.length());

        if ("Left".equalsIgnoreCase(fillType))
        {
            return sb + src;
        } else if ("Right".equalsIgnoreCase(fillType))
        {
            return src + sb;
        }

        return src;
    }
    
    /**
     * like trim
     * 
     * @param fillType
     * @param src
     * @param fillString
     * @return
     * @throws Exception
     */
    public static String unFill(String fillType, String src, String fillString)
	    throws Exception
	{
	    if (fillType == null || fillString == null || src == null)
	    {
	        throw new Exception(" [ At least one of parameters is null! ] ");
	    }
	
	    if ("Left".equalsIgnoreCase(fillType)
	        && "Right".equalsIgnoreCase(fillType))
	    {
	        throw new Exception(" [ The fillType must be Right or Left! ] ");
	    }
	
	    StringBuffer sb = new StringBuffer();
	    while (sb.length() < src.length())
	    {
	        sb.append(fillString);
	    }
	
	    if (fillType.equalsIgnoreCase("Left"))
	    {
	        for (int i = 0; i < src.length(); i++)
	        {
	            if (sb.charAt(i) != src.charAt(i))
	            {
	                return src.substring(i, src.length());
	            }
	        }
	    } else if (fillType.equalsIgnoreCase("Right"))
	    {
	        for (int i = src.length() - 1; i >= 0; i--)
	        {
	            if (sb.charAt(i) != src.charAt(i))
	            {
	                return src.substring(0, i + 1);
	            }
	        }
	    }
	    return src;
	}
    
    /**
     * close
     * 
     * @param conn
     * @param stmt
     * @param rs
     * @throws DataPipeException 
     */
    public static final void close(Connection conn, Statement stmt, ResultSet rs)
			throws DataPipeException
	{
		close(rs);
		close(stmt);
		close(conn);
	}

    /**
	 * close Connection
	 * 
	 * @param conn
	 * @throws DataPipeException
	 */
    public static final void close(Connection conn) throws DataPipeException
    {
        try
        {
            if (conn != null && !conn.isClosed())
            {
                conn.close();
            }
        } catch (SQLException sqle)
        {
        	sqle.printStackTrace();
        	throw new DataPipeException(sqle);
        }
    }
    
    /**
     * close Statement
     * 
     * @param ps
     * @throws DataPipeException 
     */
    public static final void close(Statement stmt) throws DataPipeException
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
        } catch (SQLException sqle)
        {
        	sqle.printStackTrace();
        	throw new DataPipeException(sqle);
        }
    }

    /**
     * close ResultSet
     * 
     * @param rs
     * @throws DataPipeException 
     */
    public static final void close(ResultSet rs) throws DataPipeException
    {
        try
        {
            if (rs != null)
            {
                rs.close();
            }
        } catch (SQLException sqle)
        {
        	sqle.printStackTrace();
        	throw new DataPipeException(sqle);
        }
    }
    
    /**
     * Commit
     * 
     * @param conn
     * @param autoCommit
     * @throws DataPipeException 
     */
    public static final void commit(Connection conn) throws DataPipeException
    {
        try
        {
            if (conn != null && !conn.isClosed())
            {
                conn.commit();
            }
        } catch (SQLException sqle)
        {
        	sqle.printStackTrace();
        	throw new DataPipeException(sqle);
        }
    }
}
