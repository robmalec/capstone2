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
		return (month + "/" + day + "/" + year);
	}
	public void setDate(GregorianCalendar dueDate) {
		this.dueDate = dueDate;
	}
	
	public Boolean getCompleted() {
		return completed;
	}
	public Boolean setCompleted(Boolean completed) {
		if (!getCompleted) {
			this.completed = completed;
			return true;
		}
		else {
			return false;
		}
	}
}
