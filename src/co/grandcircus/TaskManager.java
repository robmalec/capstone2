package co.grandcircus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.GregorianCalendar;

public class TaskManager {
	static List<Task> taskList = new ArrayList<Task>();
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
			System.out.println("\t5. Quit");

			nextMove = Validator.getInt(scn, "What would you like to do?", 0, 5);

			switch (nextMove) {
			case 1:
				// List tasks
				listTasks();
				break;
			case 2:
				// Add task
				break;
			case 3:
				// Delete task
				break;
			case 4:
				// Mark task complete
				break;
			case 5:
				// Quit
				break;
			default:
				System.out.println("That wasn't an option.  Please try again.");
				break;
			}

			// Logic should stop here if it doesn't need to be included in loop
			System.out.println("Do you want to continue?  (yes/no)");

		} while (nextMove != 5);

		// Indication that the program has ended
		System.out.println("Goodbye.");
		scn.close();
	}

	public static void listTasks() {
		String printFormat = "%-3s%-20s%-20s%-20s%-100s";

		System.out.println("LIST TASKS\n");

		System.out.printf(printFormat, " ", "Done?", "Due date", "Team member", "Description");
		System.out.println("");

		int counter = 1;
		for (Task t : taskList) {
			System.out.printf(printFormat, (counter + ". "), t.getCompleted().toString(), t.getDate(),
					t.getMemberName(), t.getDescription());
			counter++;
		}

	}

	public static void addTask() {
		String memberName = "", description = "", date = "";
		
		System.out.println("ADD TASK\n");
		
		memberName = Validator.getString(scn, "Team member name: ");
		System.out.println("");
		
		description = Validator.getString(scn, "Description: ");
		System.out.println("");
		
		date = Validator.getStringMatchingRegex(scn, "Date (mm/dd/yyyy): ", "[0-9]{1,2}[/][0-9]{1,2}[/][0-9]{4}");
		String[] dateInfo = date.split("/");
		
		int month = Integer.valueOf(dateInfo[0]);
		int day = Integer.valueOf(dateInfo[1]);
		int year = Integer.valueOf(dateInfo[2]);
		
		GregorianCalendar c = new GregorianCalendar(month,day,year);
		
		taskList.add(new Task(memberName, description, c));
	}

	public static void deleteTask() {
		taskList.remove(getTaskIndexFromInput("delete"));
	}

	public static void markComplete() {
		int i = getTaskIndexFromInput("complete");
		if (taskList.get(i).setCompleted(true)) {
			confirmAction(i, "marked as complete.");
		} else {
			System.out.println("Task #" + i + " is already complete.");
		}
	}

	public static int getTaskIndexFromInput(String action) {
		int taskNum = Validator.getInt(scn, "What task would you like to " + action + "?  Enter the task number: ", 1,
				taskList.size());
		return (taskNum - 1);
	}

	public static Task getTaskByInputNum(int inputNum) {
		return taskList.get(inputNum - 1);
	}

	public static void confirmAction(int taskNum, String actionPastTense) {
		System.out.println("Task #" + taskNum + " has been successfully " + actionPastTense + ".");
	}

}
