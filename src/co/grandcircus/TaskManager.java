package co.grandcircus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.GregorianCalendar;

public class TaskManager {
	static ArrayList<Task> masterList = new ArrayList<Task>();
	static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {

		int nextMove = 0;

		// While loop
		do {

			System.out.println("Welcome to the task manager!");
			System.out.println("\n\t1. List tasks");
			System.out.println("\t2. Add task");
			System.out.println("\t3. Delete task");
			System.out.println("\t4. Mark task complete");
			System.out.println("\t5. View tasks due by time");
			System.out.println("\t6. Quit");

			System.out.println("");
			
			nextMove = Validator.getInt(scn, "What would you like to do? ", 0, 5);
			
			System.out.println("");
			
			//scn.nextLine();

			switch (nextMove) {
			case 1:
				// List tasks
				listTasks(masterList);
				break;
			case 2:
				// Add task
				try {
					addNewTask();
				}
				catch (Exception e){
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				// Delete task
				deleteTask();
				break;
			case 4:
				// Mark task complete
				markComplete();
				break;
			case 5:
				//View tasks due by given time
				listTasks(getTasksDueByTime());
				break;
			case 6:
				break;
			default:
				System.out.println("That wasn't an option.  Please try again.");
				break;
			}
		
		System.out.println("");

		} while (nextMove != 6);

		// Indication that the program has ended
		System.out.println("Goodbye.");
		scn.close();
	}

	public static void listTasks(ArrayList<Task> taskList) {
		
		if (!taskList.isEmpty()) {
			String printFormat = "%-3s%-20s%-30s%-20s%-100s";

			System.out.println("LIST TASKS\n");

			System.out.printf(printFormat, " ", "Done?", "Due date and time", "Team member", "Description");
			System.out.println("");

			int counter = 1;
			for (Task t : taskList) {
				System.out.printf(printFormat, (counter + ". "), t.getCompleted().toString(), t.getDate(),
						t.getMemberName(), t.getDescription());
				counter++;
				System.out.println("");
			}
			System.out.println("");
		}
		else {
			System.out.println("No tasks to show");
		}
		
	}

	public static void addNewTask() throws Exception{
		String memberName = "", description = "", date = "", time = "";
		
		System.out.println("ADD TASK\n");
		
		memberName = Validator.getString(scn, "Team member name: ");
		System.out.println("");
		
		description = Validator.getString(scn, "Description: ");
		System.out.println("");
		
		/*
		date = Validator.getStringMatchingRegex(scn, "Date (mm/dd/yyyy): ", "[0-9]{1,2}[/][0-9]{1,2}[/][0-9]{4}");
		String[] dateInfo = date.split("/");
		
		//Adding info for dates
		
		int month = Integer.valueOf(dateInfo[0]);
		int day = Integer.valueOf(dateInfo[1]);
		int year = Integer.valueOf(dateInfo[2]);
		
		GregorianCalendar c = new GregorianCalendar(month,day,year);
		
		System.out.println("");
		
		
		//Adding info for time
		
		time = Validator.getStringMatchingRegex(scn, "Time (hh:mm am/pm): ", "[0-9]{1,2}[:][0-9]{1,2}[ ][apAP][mM]");
		
		//timeInfo[1] is either "am" or "pm"
		
		String[] timeInfo = time.split(" ");
		String[] hourMinute = timeInfo[0].split(":");
		
		//Making sure the user didn't accidentally create an event at 0:00 
		if ((hourMinute[0].equals("0")) || hourMinute[0].equals("00")) {
			throw new Exception("Invalid time entered.  You tried to create an event at hour 0.  Try again.");
		}
		
		c.add(GregorianCalendar.HOUR, Integer.valueOf(hourMinute[0]));
		
		if (Character.toLowerCase(timeInfo[1].charAt(0)) == 'a') {
			c.add(GregorianCalendar.AM_PM, GregorianCalendar.AM);
		}
		else {
			c.add(GregorianCalendar.AM_PM, GregorianCalendar.PM);
		}
		
		c.add(GregorianCalendar.MINUTE, Integer.valueOf(hourMinute[1]));
		*/
		
		masterList.add(new Task(memberName, description, getDateAndTimeFromUser()));
		
		System.out.println("");
		System.out.println("Successfully added task.  This is task #" + masterList.size() + ".");
	}

	public static void deleteTask() {
		masterList.remove(getTaskIndexFromInput("delete"));
	}

	public static void markComplete() {
		int i = getTaskIndexFromInput("complete") + 1;
		if (masterList.get(i).setCompleted(true)) {
			confirmAction(i, "marked as complete.");
		} else {
			System.out.println("Task #" + i + " is already complete.");
		}
	}

	public static ArrayList<Task> getTasksDueByTime() {
		ArrayList<Task> list = new ArrayList<>();
		System.out.println("Enter a date and time that you'd like to see all tasks due by:\n");
		
		try {
			GregorianCalendar dueDateTime = getDateAndTimeFromUser();
			for (Task t : masterList) {
				if (t.getDateTimeNum() < dueDateTime.getTimeInMillis()) {
					list.add(t);
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	public static GregorianCalendar getDateAndTimeFromUser() throws Exception {
		String date = Validator.getStringMatchingRegex(scn, "Date (mm/dd/yyyy): ", "[0-9]{1,2}[/][0-9]{1,2}[/][0-9]{4}");
		String[] dateInfo = date.split("/");
		
		//Adding info for dates
		
		int month = Integer.valueOf(dateInfo[0]);
		int day = Integer.valueOf(dateInfo[1]);
		int year = Integer.valueOf(dateInfo[2]);
		
		GregorianCalendar c = new GregorianCalendar(year,month,day);
		
		System.out.println("");
		
		//Adding info for time
		
		String time = Validator.getStringMatchingRegex(scn, "Time (hh:mm am/pm): ", "[0-9]{1,2}[:][0-9]{1,2}[ ][apAP][mM]");
		
		//timeInfo[1] is either "am" or "pm"
		
		String[] timeInfo = time.split(" ");
		String[] hourMinute = timeInfo[0].split(":");
		
		//Making sure the user didn't accidentally create an event at 0:00 
		if ((hourMinute[0].equals("0")) || hourMinute[0].equals("00")) {
			throw new Exception("Invalid time entered.  Can't have things that start at 0:00.  Remember this is in 12-hour time.");
		}
		
		c.add(GregorianCalendar.HOUR, Integer.valueOf(hourMinute[0]));
		
		if (Character.toLowerCase(timeInfo[1].charAt(0)) == 'a') {
			c.add(GregorianCalendar.AM_PM, GregorianCalendar.AM);
		}
		else {
			c.add(GregorianCalendar.AM_PM, GregorianCalendar.PM);
		}
		
		c.add(GregorianCalendar.MINUTE, Integer.valueOf(hourMinute[1]));
		
		return c;
	}
	
	public static int getTaskIndexFromInput(String action) {
		int taskNum = Validator.getInt(scn, "What task would you like to " + action + "?  Enter the task number: ", 1,
				masterList.size());
		return (taskNum - 1);
	}

	public static Task getTaskByInputNum(int inputNum) {
		return masterList.get(inputNum - 1);
	}

	public static void confirmAction(int taskNum, String actionPastTense) {
		System.out.println("Task #" + taskNum + " has been successfully " + actionPastTense + ".");
	}

}
