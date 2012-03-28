/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
	private NameSurferGraph graph;
	private JTextField nameField;
	private NameSurferDataBase listNames = null;

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the top of the window.
	 */
	public void init() {

		nameField = new JTextField(20);
		nameField.addActionListener(this);

		add(new JLabel("NameSurfer:"), NORTH);
		add(nameField, NORTH);

		add(new JButton("Graph"), NORTH);
		add(new JButton("Clear"), NORTH);

		graph = new NameSurferGraph();
		add(graph);
		addActionListeners();
		// File dir1 = new File(".") ;
		// ;

		// String vDir = System.getProperty("user.dir");
		// add(new JButton(vDir),NORTH);
		listNames = new NameSurferDataBase(NAMES_DATA_FILE);
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);

	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so
	 * you will have to define a method to respond to button actions.
	 */
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();
		if (cmd.equals("Graph")) {
			String vTmp = nameField.getText().toUpperCase();
			NameSurferEntry decadesG = listNames.findEntry(vTmp);
			if (decadesG != null) {
				graph.addEntry(decadesG);
			}

		} else if (cmd.equals("Clear")) {
			graph.clear();
		}

	}
}
