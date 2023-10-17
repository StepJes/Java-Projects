package todo.app.crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
	private static ArrayList<Task> tasks = new ArrayList<>();
	private static JList<Task> taskList;

	public static void main(String[] args) {
		JFrame frame = new JFrame("To-Do List Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Create task list
		taskList = new JList<>();
		panel.add(new JScrollPane(taskList), BorderLayout.CENTER);

		// Create buttons
		JButton addButton = new JButton("Add Task");
		JButton removeButton = new JButton("Remove Task");
		JButton viewButton = new JButton("View Task");
		JButton editButton = new JButton("Edit Task");

		// Add buttons to a separate panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(400, 70)); // Set preferred size

		// Set background color for the buttons
		buttonPanel.setBackground(Color.white);
		addButton.setBackground(Color.YELLOW);
		removeButton.setBackground(Color.RED);
		viewButton.setBackground(Color.CYAN);
		editButton.setBackground(Color.GREEN);

		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(viewButton);
		buttonPanel.add(editButton);

		panel.add(buttonPanel, BorderLayout.SOUTH);

		// Add action listeners
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = JOptionPane.showInputDialog("Enter Task Title:");
				String description = JOptionPane.showInputDialog("Enter Task Description:");
				Task task = new Task(title, description);
				tasks.add(task);
				updateTaskList();
			}
		});

		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (taskList.getSelectedIndex() != -1) {
					tasks.remove(taskList.getSelectedIndex());
					updateTaskList();
				}
			}
		});

		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = taskList.getSelectedIndex();
				if (index != -1) {
					Task task = tasks.get(index);
					JOptionPane.showMessageDialog(null,
							"Title: " + task.getTitle() + "\nDescription: " + task.getDescription());
				}
			}
		});

		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = taskList.getSelectedIndex();
				if (index != -1) {
					Task task = tasks.get(index);
					String newTitle = JOptionPane.showInputDialog("Enter New Title:", task.getTitle());
					String newDescription = JOptionPane.showInputDialog("Enter New Description:",
							task.getDescription());
					task.setTitle(newTitle);
					task.setDescription(newDescription);
					updateTaskList();
				}
			}
		});

		frame.add(panel);
		frame.setVisible(true);
	}

	private static void updateTaskList() {
		Task[] taskArray = new Task[tasks.size()];
		tasks.toArray(taskArray);
		taskList.setListData(taskArray);
	}
}
