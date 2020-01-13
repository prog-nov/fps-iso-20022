package com.forms.framework.persistence;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.forms.framework.exception.BatchFrameworkException;

public class BaseDao
{

	public <T> T queryBean(Class<T> ip_clazz, String ip_sql)
			throws BatchFrameworkException
	{
		return queryBean(ip_clazz, ip_sql, (Object[]) null);
	}

	public <T> T queryBean(Class<T> ip_clazz, String ip_sql, Object... ip_params)
			throws BatchFrameworkException
	{
		ResultSetHandler<T> loc_rsh = null;

		loc_rsh = new BeanHandler<T>(ip_clazz);
		return (T) query(ip_sql, loc_rsh, ip_params);
	}

	public <T> List<T> queryBeanList(Class<T> ip_clazz, String ip_sql)
			throws BatchFrameworkException
	{
		return queryBeanList(ip_clazz, ip_sql, (Object[]) null);
	}

	public <T> List<T> queryBeanList(Class<T> ip_clazz, String ip_sql,
			Object... ip_params) throws BatchFrameworkException
	{
		ResultSetHandler<List<T>> loc_rsh = null;

		loc_rsh = new BeanListHandler<T>(ip_clazz);
		return (List<T>) query(ip_sql, loc_rsh, ip_params);
	}

	public <T> Object[] queryArray(String ip_sql)
			throws BatchFrameworkException
	{
		return queryArray(ip_sql, (Object[]) null);
	}

	public <T> Object[] queryArray(String ip_sql, Object... ip_params)
			throws BatchFrameworkException
	{
		ResultSetHandler<Object[]> loc_rsh = null;

		loc_rsh = new ArrayHandler();
		return (Object[]) query(ip_sql, loc_rsh, ip_params);
	}

	public <T> List<Object[]> queryArrayList(String ip_sql)
			throws BatchFrameworkException
	{
		return queryArrayList(ip_sql, (Object[]) null);
	}

	public <T> List<Object[]> queryArrayList(String ip_sql, Object... ip_params)
			throws BatchFrameworkException
	{
		ResultSetHandler<List<Object[]>> loc_rsh = null;

		loc_rsh = new ArrayListHandler();
		return (List<Object[]>) query(ip_sql, loc_rsh, ip_params);
	}

	public <T> List<Object> queryColumnList(String ip_columnName, String ip_sql)
			throws BatchFrameworkException
	{
		return queryColumnList(ip_columnName, ip_sql, (Object[]) null);
	}

	public <T> List<Object> queryColumnList(String ip_columnName, String ip_sql,
			Object... ip_params) throws BatchFrameworkException
	{
		ResultSetHandler<List<Object>> loc_rsh = null;

		loc_rsh = new ColumnListHandler(ip_columnName);
		return (List<Object>) query(ip_sql, loc_rsh, ip_params);
	}

	public <T> Map<String, Object> queryMap(String ip_sql)
			throws BatchFrameworkException
	{
		return queryMap(ip_sql, (Object[]) null);
	}

	public <T> Map<String, Object> queryMap(String ip_sql, Object... ip_params)
			throws BatchFrameworkException
	{
		ResultSetHandler<Map<String, Object>> loc_rsh = null;

		loc_rsh = new MapHandler();
		return (Map<String, Object>) query(ip_sql, loc_rsh, ip_params);
	}

	public <T> List<Map<String, Object>> queryMapList(String ip_sql)
			throws BatchFrameworkException
	{
		return queryMapList(ip_sql, (Object[]) null);
	}

	public <T> List<Map<String, Object>> queryMapList(String ip_sql,
			Object... ip_params) throws BatchFrameworkException
	{
		ResultSetHandler<List<Map<String, Object>>> loc_rsh = null;

		loc_rsh = new MapListHandler();
		return (List<Map<String, Object>>) query(ip_sql, loc_rsh, ip_params);
	}

	public <T> Map<Object, Map<String, Object>> queryKeyedMapList(
			String ip_columnName, String ip_sql) throws BatchFrameworkException
	{
		return queryKeyedMapList(ip_columnName, ip_sql, (Object[]) null);
	}

	public <T> Map<Object, Map<String, Object>> queryKeyedMapList(
			String ip_columnName, String ip_sql, Object... ip_params)
			throws BatchFrameworkException
	{
		KeyedHandler loc_rsh = null;

		loc_rsh = new KeyedHandler(ip_columnName);
		return (Map<Object, Map<String, Object>>) query(ip_sql, loc_rsh, ip_params);
	}

	public int update(String ip_sql) throws BatchFrameworkException
	{
		return update(ip_sql, (Object[]) null);
	}

	public int update(String ip_sql, Object... ip_params)
			throws BatchFrameworkException
	{
		int[] loc_iCount = null;
		Object[][] loc_arrParams = null;

		loc_arrParams = new Object[1][];
		loc_arrParams[0] = ip_params;
		loc_iCount = batch(ip_sql, loc_arrParams);
		return loc_iCount[0];
	}

	public int[] batch(String ip_sql, Object[][] ip_params)
			throws BatchFrameworkException
	{
		Connection loc_conn = null;
		QueryRunner loc_qr = null;
		int[] iCount = null;

		try
		{
			loc_conn = ConnectionManager.getInstance().getConnection();
			loc_conn.setAutoCommit(false);
			loc_qr = new QueryRunner();

			iCount = loc_qr.batch(loc_conn, ip_sql, ip_params);
			commit(loc_conn);

			return iCount;
		} catch (SQLException ip_e)
		{
			printStackTrace(ip_e);
			try
			{
				rollback(loc_conn);
			} catch (SQLException ip_e1)
			{
				printStackTrace(ip_e1);
				throw new BatchFrameworkException(ip_e1);
			}
			throw new BatchFrameworkException(ip_e);
		} finally
		{
			closeQuietly(loc_conn);
		}

	}

	protected void commit(Connection ip_conn) throws SQLException
	{
		if (ip_conn != null)
		{
			ip_conn.commit();
		}
	}

	protected void commitAndClose(Connection ip_conn) throws SQLException
	{
		try
		{
			commit(ip_conn);
		} finally
		{
			close(ip_conn);
		}
	}

	protected void commitAndCloseQuietly(Connection ip_conn) throws SQLException
	{
		try
		{
			commit(ip_conn);
		} finally
		{
			closeQuietly(ip_conn);
		}
	}

	protected void rollback(Connection ip_conn) throws SQLException
	{
		if (ip_conn != null)
		{
			ip_conn.rollback();
		}
	}

	protected void rollbackAndClose(Connection ip_conn) throws SQLException
	{
		try
		{
			rollback(ip_conn);
		} finally
		{
			close(ip_conn);
		}
	}

	protected void rollbackAndCloseQuietly(Connection ip_conn) throws SQLException
	{
		try
		{
			rollback(ip_conn);
		} finally
		{
			closeQuietly(ip_conn);
		}
	}

	protected void close(ResultSet ip_rs) throws SQLException
	{
		if (ip_rs != null)
		{
			ip_rs.close();
		}
	}

	protected void close(Statement ip_stmt) throws SQLException
	{
		if (ip_stmt != null)
		{
			ip_stmt.close();
		}
	}

	protected void close(Connection ip_conn) throws SQLException
	{
		if (ip_conn != null)
		{
			ip_conn.close();
		}
	}

	protected void close(Connection ip_conn, Statement ip_stmt, ResultSet ip_rs)
			throws SQLException
	{
		try
		{
			close(ip_rs);
		} finally
		{
			try
			{
				close(ip_stmt);
			} finally
			{
				close(ip_conn);
			}
		}
	}

	protected void closeQuietly(ResultSet ip_rs)
	{
		try
		{
			close(ip_rs);
		} catch (SQLException ip_e)
		{
		}
	}

	protected void closeQuietly(Statement ip_stmt)
	{
		try
		{
			close(ip_stmt);
		} catch (SQLException ip_e)
		{
		}
	}

	protected void closeQuietly(Connection ip_conn)
	{
		try
		{
			close(ip_conn);
		} catch (SQLException ip_e)
		{
		}
	}

	protected void closeQuietly(Connection ip_conn, Statement ip_stmt, ResultSet ip_rs)
	{
		try
		{
			closeQuietly(ip_rs);
		} finally
		{
			try
			{
				closeQuietly(ip_stmt);
			} finally
			{
				closeQuietly(ip_conn);
			}
		}
	}

	protected void printStackTrace(SQLException ip_e)
	{
		printStackTrace(ip_e, new PrintWriter(System.err));
	}

	protected void printStackTrace(SQLException ip_e, PrintWriter ip_pw)
	{
		SQLException loc_next = ip_e;

		while (loc_next != null)
		{
			loc_next.printStackTrace(ip_pw);
			loc_next = loc_next.getNextException();
			if (loc_next != null)
			{
				ip_pw.println("Next SQLException:");
			}
		}
	}

	protected void printWarnings(Connection ip_conn)
	{
		printWarnings(ip_conn, new PrintWriter(System.err));
	}

	protected void printWarnings(Connection ip_conn, PrintWriter ip_pw)
	{
		try
		{
			if (ip_conn != null)
			{
				printStackTrace(ip_conn.getWarnings(), ip_pw);
			}
		} catch (SQLException ip_e)
		{
			printStackTrace(ip_e, ip_pw);
		}
	}

	private <T> T query(String ip_sql, ResultSetHandler<T> ip_rsh, Object... ip_params)
			throws BatchFrameworkException
	{
		Connection loc_conn = null;
		QueryRunner loc_qr = null;

		try
		{
			loc_conn = ConnectionManager.getInstance().getConnection();
			loc_qr = new QueryRunner();

			return loc_qr.query(loc_conn, ip_sql, ip_rsh, ip_params);
		} catch (SQLException ip_e)
		{
			printStackTrace(ip_e);
			throw new BatchFrameworkException(ip_e);
		} finally
		{
			closeQuietly(loc_conn);
		}
	}
}
