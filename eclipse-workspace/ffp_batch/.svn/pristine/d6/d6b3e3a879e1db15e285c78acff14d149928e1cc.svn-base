package com.forms.batch.job.unit.addresssevice;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;

public class AddressService {
	public static Map<String, String> getAccountNo(String cusId, String proxyId, String proxyIdType, String serviceType,
			String currency) throws Exception {
		Connection conn = ConnectionManager.getInstance().getConnection();

		HashMap<String, String> resultMap = new HashMap<>();
		String query_proxy_sql = "SELECT ACCT_NUM, ACCT_TP FROM tb_dt_addressing_acct WHERE CUS_ID = ? AND PROXY_ID = ? AND PROXY_ID_TP = ? AND ACCT_CUR = ? AND SRVC_TP IN (?,?) ORDER BY ACCT_DEF ASC LIMIT 1; ";
		ArrayList<String> query_proxy_list = new ArrayList<>();

		switch (proxyIdType) {
		case "BBAN":
		case "AIIN":
			resultMap.put("ACCT_NUM", proxyId);
			resultMap.put("ACCT_TP", proxyIdType);
			break;
		case "EMAL":
		case "MOBN":
		case "SVID":
		case "CUST":
			query_proxy_list.add(cusId);
			query_proxy_list.add(proxyId);
			query_proxy_list.add(proxyIdType);
			query_proxy_list.add(currency);
			query_proxy_list.add(serviceType);
			query_proxy_list.add("OR");
			Map<String, Object> query_proxy_map = EntityManager.queryMap(conn, query_proxy_sql,
					query_proxy_list.toArray());
			if (query_proxy_map != null && query_proxy_map.size() > 0) {
				resultMap.put("ACCT_NUM", (String) query_proxy_map.get("ACCT_NUM"));
				resultMap.put("ACCT_TP", (String) query_proxy_map.get("ACCT_TP"));
			}
			break;
		default:
			break;
		}
		
		if(conn != null){
			conn.close();
		}

		return resultMap;
	}

}
