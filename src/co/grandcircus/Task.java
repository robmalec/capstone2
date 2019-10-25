package co.grandcircus;

import java.util.GregorianCalendar;

public class Task {
	String memberName = "";
	String description = "";
	GregorianCalendar dueDate = null;
	Boolean completed = false;
	
	public Task() {	};
	public Task(String memberName, String description, GregorianCalendar dueDate) {
		this.memberName = memberName;
		this.description = description;
		this.dueDate = dueDate;
	}
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDate() {
		int month = dueDate.get(GregorianCalendar.MONTH) + 1;
		int day = dueDate.get(GregorianCalendar.DAY_OF_MONTH);
		int year = dueDate.get(GregorianCalendar.YEAR);
		int hour = dueDate.get(GregorianCalendar.HOUR);
		int minute = dueDate.get(GregorianCalendar.MINUTE);
		String minuteString = "";
		
		//Adding the extra leading zero if it's less than 10 minutes into the hour
		if (minute < 10) {
			minuteString += "0";
		}
		minuteString += minute;
		
		String amPm = "";
		
		switch (dueDate.get(GregorianCalendar.AM_PM)) {
		case GregorianCalendar.AM:
			amPm = "am";
			break;
		case GregorianCalendar.PM:
			amPm = "pm";
			break;
			default:
				break;
				
		}
		
		return (month + "/" + day + "/" + year + " at " + hour + ":" + minuteString + " " + amPm);
	}
	public void setDate(GregorianCalendar dueDate) {
		this.dueDate = dueDate;
	}
	
	public Boolean getCompleted() {
		return completed;
	}
	public Boolean setCompleted(Boolean completed) {
		if (!getCompleted()) {
			this.completed = completed;
			return true;
		}
		else {
			return false;
		}
	}
}
