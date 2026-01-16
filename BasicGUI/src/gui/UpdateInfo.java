package gui;

import java.awt.*;
import javax.swing.*;        

public class UpdateInfo extends JFrame{
    //instance variables
	//traditional variables
	private String animalName;	
	private String animalType;
    
	//arrays containing dropdown menu choices
	private final String[] animalTypeChoices = {"None","Dog", "Monkey"}; 
	private String[] animalNames;
	private String[] animalData;
	
	//dropdown menus
	private JComboBox<String> animalTypeDropdown;
	private JComboBox<String> animalNamesDropdown;

	//button instance for when form is completed, submit then saves inputted data and saves to outside file
	private JButton submitButton;
	
	private final UpdateHelper updateHelper = new UpdateHelper();
	
	private JPanel dynamicP = new JPanel(new GridBagLayout());	
	private DatabaseAccess db = new DatabaseAccess();
	
	//constructor method
	public UpdateInfo() {

		
		//basic window data
		setLayout(new BorderLayout(10, 10));
		setTitle("Grazioso Salvare Update Existing Information Screen");
		setSize(700, 600);
		//sets the window to initialize in the middle of the screen
		setLocationRelativeTo(null);
		
		JPanel topP = new JPanel(new FlowLayout(FlowLayout.CENTER));	
		topP.add(new JLabel("Animal Type: "));
		animalTypeDropdown = new JComboBox<>(animalTypeChoices);
		topP.add(animalTypeDropdown);
		add(topP, BorderLayout.NORTH);
		
		//dynamic panel placed in the center of the window, updates after db is queried 
		add(dynamicP, BorderLayout.CENTER);
		
		//creates south most panel, this panel is unchanging and contains the submit button
		JPanel botP = new JPanel(new FlowLayout(FlowLayout.CENTER));
		submitButton = new JButton("Submit");
		botP.add(submitButton);
		add(botP, BorderLayout.SOUTH);
		
		//action listener to detect when an animal type is selected
		animalTypeDropdown.addActionListener( e -> { 
			dynamicP.removeAll();
			animalType = (String) animalTypeDropdown.getSelectedItem();			
			
			animalNamesDropdown = UpdateHelper.updatePageForNames(dynamicP, animalType);
			
			if (animalNamesDropdown != null) {
				animalNamesDropdown.addActionListener(r -> {
					
					animalName = (String) animalNamesDropdown.getSelectedItem();
					DatabaseAccess db = new DatabaseAccess();
					updateHelper.updateFields(dynamicP, animalType, false, animalName, db.RetrieveInformation(animalName, animalType));
					
				});
			}
			
			System.out.println("Selected animal type: " + animalType);
			System.out.println("Selected animal: " + animalName);
		});
		
		submitButton.addActionListener( e -> {
			animalData = updateHelper.returnAndConcatenateAnswers(animalType);
			db.updateInformation(animalType, animalData, animalName);
			
			for (String value : animalData) {
				System.out.println("Data: " + value);
			}
			
			System.out.println("\n");
			
			updateHelper.updateFields(dynamicP, animalType, true, animalName, animalData);
			dispose();
			UpdateInfo newWindow = new UpdateInfo();
		});
		
		
		setVisible(true);
		
		
	}

}

