package gui;

import java.awt.*;        
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class AddAnimal extends JFrame{
	//instance variables
	//traditional variable types
    private String name , animalType,  gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, inServiceCountry, breed, tailLength, height, bodyLength, species, reserved;
	private int rowNum;
	
	//arrays containing dropdown menu choices
	public final static String[] animalTypeChoices = {"None","Dog", "Monkey"}; 
	public final static String[] trainingStatusChoices = {"None", "Phase 1", "Phase 2", "Phase 3", "Phase 4", "Phase 5"};
	public final static String[] reservedChoices = {"None", "no", "yes"};
	public final static String[] speciesChoices = {"None", "Capuchin", "Guenon", "Macaque", "Marmoset", "Squirrel Monkey", "Tamarin"};
	
	//holds the form answers after submit button is pressed
	private String[] finalAdditionString;
	
	//dropdown menus
	private JComboBox<String> animalTypeDropdown;  //fully implemented
	private JComboBox<String> trainingStatusDropdown;	
	private JComboBox<String> reservedDropdown;
	private JComboBox<String> speciesDropdown;
	
	//text fields
	private JTextField breedField;
	private JTextField nameField;
	private JTextField genderField;
	private JTextField ageField;
	private JTextField weightField;
	private JTextField acquisitionCountryField;
	private JTextField serviceCountryField;
	private JTextField heightField;
	private JTextField tailLengthField;
	private JTextField bodyLengthField;

	//special type of text field, user can only input dates, displays as __/__/____
	private JFormattedTextField date;
	
	//button instance for when form is completed, submit then saves inputted data and saves to outside file
	private JButton submitButton;
	
	//dynamic panel used to contain the changing fields
	private JPanel dynamicP = new JPanel(new GridBagLayout());
	
	//creates an instance of the DatabaseAccess class - see DatabaseAccess.java for further information
	private DatabaseAccess dbAccess = new DatabaseAccess();
	
	//constructor
	public AddAnimal() {
		
		UpdateHelper updateHelper = new UpdateHelper();
		
		//basic window data
		setLayout(new BorderLayout(10, 10));
		setTitle("Grazioso Salvare Data Addition Screen");
		setSize(700, 600);
		//sets the window to initialize in the middle of the screen
		setLocationRelativeTo(null);

		//creates the north most panel, out of 3 panels this one is unchanging and contains the animal type dropdown
		JPanel topP = new JPanel(new FlowLayout(FlowLayout.CENTER));	
		topP.add(new JLabel("Animal Type: "));
		animalTypeDropdown = new JComboBox<>(animalTypeChoices);
		topP.add(animalTypeDropdown);
		add(topP, BorderLayout.NORTH);
		
		//creates the middle most panel , this panel changes depending on the animal type selected, contains fields to fill out animal information
		add(dynamicP, BorderLayout.CENTER);
		
		//creates south most panel, this panel is unchanging and contains the submit button
		JPanel botP = new JPanel(new FlowLayout(FlowLayout.CENTER));
		submitButton = new JButton("Submit");
		botP.add(submitButton);
		add(botP, BorderLayout.SOUTH);

			
		//listener event to detect when the animal type is updated
		animalTypeDropdown.addActionListener( e -> { 
			animalType = (String) animalTypeDropdown.getSelectedItem();			
			updateHelper.updateFields(dynamicP, animalType, true, null, new String[15]);
			repaint();

			getContentPane().revalidate();
			System.out.println("Selected animal type: " + animalType);

		});
		
		//listener event to send input information to the database after submit button is pressed
		submitButton.addActionListener( e -> {
			finalAdditionString = updateHelper.returnAndConcatenateAnswers(animalType);
			dbAccess.AddInformation(finalAdditionString, animalType);
			dispose();
		});
		
		//sets window visibility after initialization
		setVisible(true);	
		
	}
	
	//main
	public static void main(String[] args) {
		AddAnimal animal = new AddAnimal();
	}
	
}
	

