package com.forms.framework.job.common.husservice;

import java.util.ArrayList;
import java.util.List;

public class HousekeepRul {
	
	private String id=null;
	
	private String housekeepDays=null;
	
	private String housekeepType=null;
	
	private String dayType=null;
	
	private String className=null;
	
	private String isBackup=null;
	
	private String stats=null;
	
	private String type=null;
	
	private List<Housekeep> huslist=new ArrayList<Housekeep>();
	
	public static final String DAYTYPE_A="A";
	
	public static final String DAYTYPE_D="D";
	
	public static final String TYPE_PUBLIC="PUBLIC";
	
	public static final String TYPE_PRIVATE="PRIVATE";

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDayType() {
		return dayType;
	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

	public String getHousekeepDays() {
		return housekeepDays;
	}

	public void setHousekeepDays(String housekeepDays) {
		this.housekeepDays = housekeepDays;
	}

	public String getHousekeepType() {
		return housekeepType;
	}

	public void setHousekeepType(String housekeepType) {
		this.housekeepType = housekeepType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsBackup() {
		return isBackup;
	}

	public void setIsBackup(String isBackup) {
		this.isBackup = isBackup;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Housekeep> getHuslist() {
		return huslist;
	}

	public void setHuslist(List<Housekeep> huslist) {
		this.huslist = huslist;
	}

	
}
