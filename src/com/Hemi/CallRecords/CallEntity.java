package com.Hemi.CallRecords;

public class CallEntity {
	public static final int CALL_INCOMING=0;
	public static final int CALL_OUTGOING=1;
	public static final int CALL_MISSING=2;
	private String CallDate;
	private String SimLocation;
	private String CallName;
	private String CallDuration;
	private String CallNum;
	int type;
	
	public CallEntity(int type, String callDate, String simLocation, String callName, String CallNum, String CallDuration) {
		super();
		this.type=type;
		this.CallDate = callDate;
		this.SimLocation = simLocation;
		this.CallName = callName;
		this.CallNum = CallNum;
		this.CallDuration = CallDuration;
	}

	public String getCallDate() {
		return CallDate;
	}

	public void setCallDate(String callDate) {
		CallDate = callDate;
	}

	public String getSimLocation() {
		return SimLocation;
	}

	public void setSimLocation(String simLocation) {
		SimLocation = simLocation;
	}

	public String getCallName() {
		return CallName;
	}

	public void setCallName(String callName) {
		CallName = callName;
	}

	public String getCallNum() {
		return CallNum;
	}

	public void setCallNum(String callNum) {
		CallNum = callNum;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCallDuration() {
		return CallDuration;
	}

	public void setCallDuration(String callDuration) {
		CallDuration = callDuration;
	}
	
	

}
