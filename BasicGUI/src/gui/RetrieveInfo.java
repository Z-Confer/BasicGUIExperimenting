package gui;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RetrieveInfo extends JFrame{
    //instance variables
	//traditional variables
	private String animalName;	
	private String animalType;
    
	//arrays containing dropdown menu choices
	private final String[] animalTypeChoices = {"None","Dog", "Monkey"}; 
	private String[] animalData;
	
	//dropdown menus
	private JComboBox<String> animalTypeDropdown;
	private JComboBox<String> animalNamesDropdown;
	
	//dynamic panel used to contain the changing fields
	private JPanel dynamicP = new JPanel(new GridBagLayout());
	
	//constructor
	public RetrieveInfo() {
		
		//basic window data
		setLayout(new BorderLayout(10, 10));
		setTitle("Grazioso Salvare Information Retrieval Screen");
		setSize(700, 600);
		//sets the window to initialize in the middle of the screen
		setLocationRelativeTo(null);

		//top panel contains animal type dropdown menu
		JPanel topP = new JPanel(new FlowLayout(FlowLayout.CENTER));	
		topP.add(new JLabel("Animal Type: "));
		animalTypeDropdown = new JComboBox<>(animalTypeChoices);
		topP.add(animalTypeDropdown);
		add(topP, BorderLayout.NORTH);
		
		//dynamic panel placed in the center of the window, updates after db is queried 
		add(dynamicP, BorderLayout.CENTER);
		
		//action listener to detect when an animal type is selected
		animalTypeDropdown.addActionListener( e -> { 
			animalType = (String) animalTypeDropdown.getSelectedItem();			
			
			animalNamesDropdown = UpdateHelper.updatePageForNames(dynamicP, animalType);
			System.out.println("Selected animal type: " + animalType);
			System.out.println("Data fields successfully populated");
			System.out.println("Selected animal: " + animalName);
			
			//prevents stacking of listeners 
			for (var listener : animalNamesDropdown.getActionListeners()) {
				animalNamesDropdown.removeActionListener(listener);
			}
			
			//action listener for when the animal names drop down is selected
			animalNamesDropdown.addActionListener( r -> {
				animalName = (String) animalNamesDropdown.getSelectedItem();
				
				if (animalName == null) {
					return;
				}
				
				UpdateHelper.updatePageForDataFields(animalName, animalType, dynamicP, animalNamesDropdown);
			
			});
			
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		RetrieveInfo info = new RetrieveInfo();
	}
	//updates the page after an animal type is selected, presents the user with the various names of the animals that can be queried
	public String updatePageForNames() {
		dynamicP.removeAll();
		
		//animalNamesDropdown = UpdateHelper.updatePageForNames(dynamicP, animalType);
		
	    if (null == animalNamesDropdown) {
	        return null;
	    }
		
		/*
		 * animalNamesDropdown.addActionListener( r -> { animalName = (String)
		 * animalNamesDropdown.getSelectedItem();
		 * UpdateHelper.updatePageForDataFields(animalName, animalType, dynamicP);
		 * System.out.println("Data fields successfully populated"); });
		 */
	    
	    animalName = (String) animalNamesDropdown.getSelectedItem(); 
//	    System.out.println("Selected animal: " + animalName);
	    
	    return animalName;
	}
	
}
