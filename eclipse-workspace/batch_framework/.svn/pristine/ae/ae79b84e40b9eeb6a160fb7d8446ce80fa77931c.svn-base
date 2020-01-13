package com.forms.framework.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

public class EntityManager
{

	private static <T> T query(Connection ip_conn, String ip_sql,
			ResultSetHandler<T> ip_rsh, Object... ip_params) throws Exception
	{
		QueryRunner loc_qr = null;
		try
		{
			loc_qr = new QueryRunner();
			return loc_qr.query(ip_conn, ip_sql, ip_rsh, ip_params);
		} catch (SQLException ip_e)
		{
			throw ip_e;
		}
	}

	public static <T> T queryBean(Connection ip_conn, Class<T> ip_clazz, String ip_sql,
			Object... ip_params) throws Exception
	{
		ResultSetHandler<T> loc_rsh = null;
		loc_rsh = new BeanHandler<T>(ip_clazz);
		return (T) query(ip_conn, ip_sql, loc_rsh, ip_params);
	}

	public static <T> T queryBean(Connection ip_conn, Class<T> ip_clazz, String ip_sql)
			throws Exception
	{
		return (T) queryBean(ip_conn, ip_clazz, ip_sql, (Object[]) null);
	}

	public static <T> T queryBean(Class<T> ip_clazz, String ip_sql, Object... ip_sqlparams)
			throws Exception
	{
		Connection loc_conn = ConnectionManager.getInstance().getConnection();
		try
		{
			return (T) queryBean(loc_conn, ip_clazz, ip_sql, ip_sqlparams);
		} catch (Exception ip_ex)
		{
			if(!loc_conn.getAutoCommit())
				loc_conn.rollback();
			throw ip_ex;
		} finally
		{
			loc_conn.close();
		}
	}

	public static <T> T queryBean(Class<T> ip_clazz, String ip_sql) throws Exception
	{
		return (T) queryBean(ip_clazz, ip_sql, (Object[]) null);
	}

	public static <T> List<T> queryBeanList(Connection ip_conn, Class<T> ip_clazz,
			String ip_sql, Object... ip_params) throws Exception
	{
		ResultSetHandler<List<T>> loc_rsh = null;
		loc_rsh = new BeanListHandler<T>(ip_clazz);
		return (List<T>) query(ip_conn, ip_sql, loc_rsh, ip_params);
	}

	public static <T> List<T> queryBeanList(Connection ip_conn, Class<T> ip_clazz,
			String ip_sql) throws Exception
	{
		return (List<T>) queryBeanList(ip_conn, ip_clazz, ip_sql, (Object[]) null);
	}

	public static <T> List<T> queryBeanList(Class<T> ip_clazz, String ip_sql,
			Object... ip_params) throws Exception
	{
		Connection loc_conn = ConnectionManager.getInstance().getConnection();
		try
		{
			return (List<T>) queryBeanList(loc_conn, ip_clazz, ip_sql, ip_params);
		} catch (Exception ip_ex)
		{
			if(!loc_conn.getAutoCommit())
				loc_conn.rollback();
			throw ip_ex;
		} finally
		{
			loc_conn.close();
		}
	}

	public static <T> List<T> queryBeanList(Class<T> ip_clazz, String ip_sql)
			throws Exception
	{
		return (List<T>) queryBeanList(ip_clazz, ip_sql, (Object[]) null);
	}

	public static <T> Object[] queryArray(Connection ip_conn, String ip_sql,
			Object... ip_params) throws Exception
	{
		ResultSetHandler<Object[]> loc_rsh = null;
		loc_rsh = new ArrayHandler();
		return (Object[]) query(ip_conn, ip_sql, loc_rsh, ip_params);
	}

	public static <T> Object[] queryArray(Connection ip_conn, String ip_sql)
			throws Exception
	{
		return (Object[]) queryArray(ip_conn, ip_sql, (Object[]) null);
	}

	public static <T> Object[] queryArray(String ip_sql, Object... ip_params)
			throws Exception
	{
		Connection loc_conn = ConnectionManager.getInstance().getConnection();
		try
		{
			return (Object[]) queryArray(loc_conn, ip_sql, ip_params);
		} catch (Exception ip_ex)
		{
			if(!loc_conn.getAutoCommit())
				loc_conn.rollback();
			throw ip_ex;
		} finally
		{
			loc_conn.close();
		}
	}

	public static <T> Object[] queryArray(String ip_sql) throws Exception
	{
		return (Object[]) queryArray(ip_sql, (Object[]) null);
	}

	public static <T> List<Object[]> queryArrayList(Connection ip_conn,
			String ip_sql, Object... ip_params) throws Exception
	{
		ResultSetHandler<List<Object[]>> loc_rsh = null;
		loc_rsh = new ArrayListHandler();
		return (List<Object[]>) query(ip_conn, ip_sql, loc_rsh, ip_params);
	}

	public static <T> List<Object[]> queryArrayList(Connection ip_conn, String ip_sql)
			throws Exception
	{
		return (List<Object[]>) queryArrayList(ip_conn, ip_sql, (Object[]) null);
	}

	public static <T> List<Object[]> queryArrayList(String ip_sql,
			Object... ip_params) throws Exception
	{
		Connection loc_conn = ConnectionManager.getInstance().getConnection();
		try
		{
			return (List<Object[]>) queryArrayList(loc_conn, ip_sql, ip_params);
		} catch (Exception ip_ex)
		{
			if(!loc_conn.getAutoCommit())
				loc_conn.rollback();
			throw ip_ex;
		} finally
		{
			loc_conn.close();
		}
	}

	public static <T> List<Object[]> queryArrayList(String ip_sql)
			throws Exception
	{
		return (List<Object[]>) queryArrayList(ip_sql, (Object[]) null);
	}

	public static <T> Map<String, Object> queryMap(Connection ip_conn, String ip_sql,
			Object... ip_params) throws Exception
	{
		ResultSetHandler<Map<String, Object>> loc_rsh = null;
		loc_rsh = new MapHandler();
		return (Map<String, Object>) query(ip_conn, ip_sql, loc_rsh, ip_params);
	}

	public static <T> Map<String, Object> queryMap(Connection ip_conn, String ip_sql)
			throws Exception
	{
		return (Map<String, Object>) queryMap(ip_conn, ip_sql, (Object[]) null);
	}

	public static <T> Map<String, Object> queryMap(String ip_sql, Object... ip_params)
			throws Exception
	{
		Connection loc_conn = ConnectionManager.getInstance().getConnection();
		try
		{
			return (Map<String, Object>) queryMap(loc_conn, ip_sql, ip_params);
		} catch (Exception ip_ex)
		{
			if(!loc_conn.getAutoCommit())
				loc_conn.rollback();
			throw ip_ex;
		} finally
		{
			loc_conn.close();
		}
	}

	public static <T> Map<String, Object> queryMap(String ip_sql) throws Exception
	{
		return (Map<String, Object>) queryMap(ip_sql, (Object[]) null);
	}

	public static <T> List<Map<String, Object>> queryMapList(Connection ip_conn,
			String ip_sql, Object... ip_params) throws Exception
	{
		ResultSetHandler<List<Map<String, Object>>> loc_rsh = null;
		loc_rsh = new MapListHandler();
		return (List<Map<String, Object>>) query(ip_conn, ip_sql, loc_rsh, ip_params);
	}

	public static <T> List<Map<String, Object>> queryMapList(Connection ip_conn,
			String ip_sql) throws Exception
	{
		return (List<Map<String, Object>>) queryMapList(ip_conn, ip_sql,
				(Object[]) null);
	}

	public static <T> List<Map<String, Object>> queryMapList(String ip_sql,
			Object... ip_params) throws Exception
	{
		Connection loc_conn = ConnectionManager.getInstance().getConnection();
		try
		{
			return (List<Map<String, Object>>) queryMapList(loc_conn, ip_sql, ip_params);
		} catch (Exception ip_ex)
		{
			if(!loc_conn.getAutoCommit())
				loc_conn.rollback();
			throw ip_ex;
		} finally
		{
			loc_conn.close();
		}
	}

	public static <T> List<Map<String, Object>> queryMapList(String ip_sql)
			throws Exception
	{
		return (List<Map<String, Object>>) queryMapList(ip_sql, (Object[]) null);
	}

	public static int update(Connection ip_conn, String ip_sql, Object... ip_params)
			throws Exception
	{
		int[] loc_iCount = null;
		Object[][] loc_arrParams = null;
		loc_arrParams = new Object[1][];
		loc_arrParams[0] = ip_params;
		loc_iCount = batch(ip_conn, ip_sql, loc_arrParams);
		return loc_iCount[0];
	}

	public static int update(Connection ip_conn, String ip_sql) throws Exception
	{
		return update(ip_conn, ip_sql, (Object[]) null);
	}

	public static int update(String ip_sql, Object... ip_params) throws Exception
	{
		Connection loc_conn = ConnectionManager.getInstance().getConnection();
		try
		{
			return update(loc_conn, ip_sql, ip_params);
		} catch (Exception ip_ex)
		{
			throw ip_ex;
		} finally
		{
			loc_conn.close();
		}
	}

	public static int update(String ip_sql) throws Exception
	{
		return update(ip_sql, (Object[]) null);
	}

	public static int[] batch(Connection ip_conn, String ip_sql, Object[][] params)
			throws Exception
	{
		QueryRunner loc_qr = null;
		int[] loc_iCount = null;
		try
		{
			loc_qr = new QueryRunner();
			loc_iCount = loc_qr.batch(ip_conn, ip_sql, params);
			return loc_iCount;
		} catch (SQLException ip_e)
		{
			try
			{
				if(!ip_conn.getAutoCommit())
					ip_conn.rollback();
			} catch (SQLException ip_e1)
			{
				throw ip_e1;
			}
			throw ip_e;
		}
	}

	public static int[] batch(Connection ip_conn, String ip_sql) throws Exception
	{
		return batch(ip_conn, ip_sql, (Object[][]) null);
	}

	public static int[] batch(String ip_sql, Object[][] params) throws Exception
	{
		Connection loc_conn = ConnectionManager.getInstance().getConnection();
		try
		{
			return batch(loc_conn, ip_sql, params);
		} catch (Exception ip_ex)
		{
			throw ip_ex;
		} finally
		{
			loc_conn.close();
		}
	}

	public static int[] batch(String ip_sql) throws Exception
	{
		return batch(ip_sql, (Object[][]) null);
	}

}
