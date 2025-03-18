import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainToDoList extends JFrame{
	//GUI components to use classes to make the GUI
	private JTextField TaskName, DueDate;
	private JLabel Status;
	private JList<String> Tasks;
	private JComboBox<String> Priority;
	private DefaultListModel<String> TaskList;
	
	//A list to store the tasks entered
	private ArrayList<Task> tasks = new ArrayList<>();
	
	//Creates the border of the GUI
	public MainToDoList() {
		setTitle("To Do List");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		//calls the list so it can be called anywhere as the main class is public
		tasks = new ArrayList<>();
		//this calls the private classes to the main class
		setupInputPanel();
		setupListPanel();
		setupButtonPanel();		
	}
	
	private void setupInputPanel() {
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
		//The box for the user to enter the task name
		inputPanel.add(new JLabel("Task Name:"));
		TaskName = new JTextField();
		inputPanel.add(TaskName);
		//The box to enter the date
		inputPanel.add(new JLabel("Due Date (DD-MM-YYYY):"));
		DueDate = new JTextField();
		inputPanel.add(DueDate);
		//The choice to choose between levels of priority
		inputPanel.add(new JLabel("Priority Level:"));
		Priority = new JComboBox<>(new String[] {"High","Medium","Low"});
		inputPanel.add(Priority);
		//A button to add the data to the list
		JButton addTaskButton = new JButton("Add Task");
		addTaskButton.addActionListener(e -> addTask());
		inputPanel.add(addTaskButton);
		
		Status = new JLabel(" ");
		inputPanel.add(Status);
		
		add(inputPanel, BorderLayout.NORTH);
	}
	//This method adds tasks
	private void addTask() {
		String taskName = TaskName.getText().trim();
		String dueDate = DueDate.getText().trim();
		String priority = (String) Priority.getSelectedItem();
		
		//To confirm if the task name is empty or not
		if (taskName.isEmpty()) {
			Status.setText("Task Name cannot be empty");
			return;
		}
		//To confirm the format of the date
		SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		date.setLenient(false);
		try {
			date.parse(dueDate);
		} catch (ParseException e) {
			Status.setText("Invalid data format. Please use DD-MM-YYYY");
			return;
		}
		//Adds tasks to the list
		Task newTask = new Task(taskName, dueDate, priority, false);
		tasks.add(newTask);
		TaskList.addElement(newTask.toString());
		
		TaskName.setText("");
		DueDate.setText("");
		Status.setText("Task added successfully.");
	}
	private class Task {
		//Creates variables to represent a task
		private String taskName; 
		private String dueDate;
		private String priority;
		private boolean completed;
		public Task(String taskName, String dueDate, String priority, boolean completed) {
			this.taskName = taskName;
			this.dueDate = dueDate;
			this.priority = priority;
			this.completed = completed;
		}
		public void setCompleted(boolean completed) {
			this.completed = completed;}
		
		public String toString() {
			String completionStatus = completed ? "✔ " : "";
			return completionStatus + taskName + " - (Due: " + dueDate + ", Priority: " + priority + ")";
		}
	}
	//This creates a page where the tasks are shown
	private void setupListPanel() {
		TaskList = new DefaultListModel<>();
		Tasks = new JList<>(TaskList);
		Tasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane ScrollList = new JScrollPane(Tasks);
		add(ScrollList, BorderLayout.CENTER);
	}
	//Method to setup buttons to mark and delete
	private void setupButtonPanel() {
		JPanel Button = new JPanel();
		Button.setLayout(new FlowLayout());
		//Mark tasks as complete
		JButton MarkComplete = new JButton("Mark as Complete");
		MarkComplete.addActionListener(e -> MarkCompleted());
		Button.add(MarkComplete);
		//To delete tasks
		JButton DeleteTask = new JButton("Delete");
		DeleteTask.addActionListener(e -> Delete());
		Button.add(DeleteTask);
		
		add(Button, BorderLayout.SOUTH);
	}
	//A method to mark tasks as being completed
	private void MarkCompleted() {
		int Index = Tasks.getSelectedIndex();
		//.getSelectedIndex() sets the index to -1 if item is empty
		//so instead of the condition being != 1, to == -1
		if (Index == -1) {
			Status.setText("Please select a task to mark as completed.");
		} else{
			System.out.println("task length: " + tasks.size()); //Test
			System.out.println("Index = " + Index);				//Test
			Task selectedTask = tasks.get(Index);
			System.out.println("stored task object");			//Test
			selectedTask.setCompleted(true);
			System.out.println("set complete to true");			//Test
			TaskList.set(Index, selectedTask.toString());
			Status.setText("Task marked as completed.");
		}
	}
	//A method to delete tasks
	private void Delete() {
		int Index = Tasks.getSelectedIndex();
		if(Index != -1) {
			tasks.remove(Index);
			TaskList.remove(Index);
			Status.setText("Task delted successfully.");
		} else {
			Status.setText("Please select a task to be delete.");
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainToDoList app = new MainToDoList();
			app.setVisible(true);
		});
	}
}