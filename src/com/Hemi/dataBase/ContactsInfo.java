package com.Hemi.dataBase;

public class ContactsInfo {
	
	private String contactName;
	private String contactNumber;
	public ContactsInfo(String contactName, String contactNumber) {
		super();
		this.contactName = contactName;
		this.contactNumber = contactNumber;
	}
	
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
}
