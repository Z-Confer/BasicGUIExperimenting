package gui;

import java.awt.*;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class UpdateHelper{
    private JTextField nameField, genderField, ageField, weightField, breedField;
    private JTextField acquisitionCountryField, serviceCountryField;

    // Monkey-only fields
    private JTextField heightField, tailLengthField, bodyLengthField;

    // Dropdowns
    private JComboBox<String> trainingStatusDropdown, reservedDropdown, speciesDropdown;

	public final static String[] animalTypeChoices = {"None","Dog", "Monkey"}; 
	public final static String[] trainingStatusChoices = {"None", "Phase 1", "Phase 2", "Phase 3", "Phase 4", "Phase 5"};
	public final static String[] reservedChoices = {"None", "no", "yes"};
	public final static String[] speciesChoices = {"None", "Capuchin", "Guenon", "Macaque", "Marmoset", "Squirrel Monkey", "Tamarin"};
	public String[] animalData = new String[15];
    
    // Date field (this fixes your "date cannot be resolved" error)
    private JFormattedTextField dateField;
	
	//helper method designed to assist in the page update when new animal type is selected
	public static JComboBox<String> updatePageForNames(JPanel dynamicP, String animalType) {
		
	    if ("None".equals(animalType)) {
	        dynamicP.revalidate();
	        dynamicP.repaint();
	        return null;
	    }
	    
		DatabaseAccess db = new DatabaseAccess();
		String [] animalNames = db.returnNames(animalType);
		
		JComboBox<String> animalNamesDropdown = new JComboBox<>(animalNames);
		
		addRow("Animal Name: ", animalNamesDropdown, dynamicP, 0);
		
	    dynamicP.revalidate();
	    dynamicP.repaint();
	    
	    return animalNamesDropdown;
	}
	
	//multi-purpose method which is used in both AddAnimal and UpdateInfo. Uses a boolean parameter named updateOrAdd to determine whether the user is adding a new animal
	//or updating existing information. In the event that the user is updating existing information- false is passed as the boolean parameter, and creates text boxes auto-filled with
	//the existing information, which the user can then choose to alter should need be. However, in the addAnimal class, when true is passed as the parameter, it populates the field
	//with empty text boxes 
	public void updateFields(JPanel dynamicP, String animalType, boolean updateOrAdd, String animalName, String[] animalD) { //true corresponds to adding, false corresponds to updating
		dynamicP.removeAll();
		
		if (true == updateOrAdd) {
		
			//populates the "Dog" animal type related questions
			if ("Dog".equals(animalType)) {
				nameField = new JTextField(10);
				addRow("Animal name: ", nameField, dynamicP, 0);
				
				ageField = new JTextField(10);
				addRow("Animal age (years): ", ageField, dynamicP, 1);
				
				genderField = new JTextField(10);
				addRow("Animal gender: ", genderField, dynamicP, 2);
				
				weightField = new JTextField(10);
				addRow("Animal weight (lbs): ", weightField, dynamicP, 3);
				
				breedField = new JTextField(10);
				addRow("Animal breed: ", breedField, dynamicP, 4);
				
				acquisitionCountryField = new JTextField(10);
				addRow("Country of acquisition: ", acquisitionCountryField, dynamicP, 5);
				
				serviceCountryField = new JTextField(10);
				addRow("Service country: ", serviceCountryField, dynamicP, 6);
				
				trainingStatusDropdown = new JComboBox<>(AddAnimal.trainingStatusChoices);
				addRow("Animal training phase: ", trainingStatusDropdown, dynamicP, 7);
				
				reservedDropdown = new JComboBox<>(AddAnimal.reservedChoices);
				addRow("Is the animal reserved: ", reservedDropdown, dynamicP, 8);
				
				dateField = dateField();
				addRow("Date acquired: ", dateField, dynamicP, 9);
				
				
			//populates the "Monkey" animal type related questions
			} else if ("Monkey".equals(animalType)) {
				nameField = new JTextField(10);
				addRow("Animal name: ", nameField, dynamicP, 1);
				
				ageField = new JTextField(10);
				addRow("Animal age (years): ", ageField, dynamicP, 2);
				
				genderField = new JTextField(10);
				addRow("Animal gender: ", genderField, dynamicP, 3);
				
				weightField = new JTextField(10);
				addRow("Animal weight (lbs): ", weightField, dynamicP, 4);
	
				heightField = new JTextField(10);
				addRow("Animal height (inches): ", heightField, dynamicP, 5);
				
				tailLengthField = new JTextField(10);
				addRow("Animal tail length (inches): ", tailLengthField, dynamicP, 6);
				
				bodyLengthField = new JTextField(10);
				addRow("Animal body length (inches): ", bodyLengthField, dynamicP, 7);
				
				acquisitionCountryField = new JTextField(10);
				addRow("Country of acquisition: ", acquisitionCountryField, dynamicP, 8);
				
				serviceCountryField = new JTextField(10);
				addRow("Service country: ", serviceCountryField, dynamicP, 9);
				
				speciesDropdown = new JComboBox<>(speciesChoices);
				addRow("Animal species: ", speciesDropdown, dynamicP, 10);	
				
				trainingStatusDropdown = new JComboBox<>(trainingStatusChoices);				
				addRow("Animal training phase: ", trainingStatusDropdown, dynamicP, 11);
				
				reservedDropdown = new JComboBox<>(reservedChoices);
				addRow("Is the animal reserved: ", reservedDropdown, dynamicP, 12);
				
				dateField = dateField();
				addRow("Date acquired: ", dateField, dynamicP, 13);
				
			}
				
			} else { //handles the event that a user is trying to update existing information
					

				//populates the "Dog" animal type related questions
				if ("Dog".equals(animalType)) {
					
					addRow("Animal name: ", nameField = new JTextField(animalD[1]), dynamicP, 0);
					
					addRow("Animal age (years): ", ageField = new JTextField(animalD[3]), dynamicP, 1);
					
					addRow("Animal gender: ", genderField = new JTextField(animalD[2]), dynamicP, 2);
					
					addRow("Animal weight (lbs): ", weightField = new JTextField(animalD[4]), dynamicP, 3);
					
					addRow("Animal breed: ", breedField = new JTextField(animalD[9]), dynamicP, 4);
					
					addRow("Country of acquisition: ", acquisitionCountryField = new JTextField(animalD[6]), dynamicP, 5);
					
					addRow("Service country: ", serviceCountryField = new JTextField(animalD[8]), dynamicP, 6);
					
					trainingStatusDropdown = new JComboBox<>(trainingStatusChoices);
					trainingStatusDropdown.setSelectedItem(animalD[7]);
					addRow("Animal training phase: ", trainingStatusDropdown, dynamicP, 7);
					
					reservedDropdown = new JComboBox<>(reservedChoices);
					reservedDropdown.setSelectedItem(animalD[10]);
					addRow("Is the animal reserved: ", reservedDropdown, dynamicP, 8);
					
					addRow("Date acquired: ", dateField = new JFormattedTextField(animalD[5]), dynamicP, 9);
					
						
				//populates the "Monkey" animal type related questions
				} else if ("Monkey".equals(animalType)) {
					
					addRow("Animal name: ", nameField = new JTextField(animalD[1]), dynamicP, 0);
					
					addRow("Animal age (years): ", ageField = new JTextField(animalD[3]), dynamicP, 1);
					
					addRow("Animal gender: ", genderField = new JTextField(animalD[2]), dynamicP, 2);
					
					addRow("Animal weight (lbs): ", weightField = new JTextField(animalD[4]), dynamicP, 3);
					
					addRow("Animal height (inches): ", heightField = new JTextField(animalD[11]), dynamicP, 4);
						
					addRow("Animal tail length (inches): ", tailLengthField = new JTextField(animalD[9]), dynamicP, 5);
						
					addRow("Animal body length (inches): ", bodyLengthField = new JTextField(animalD[10]), dynamicP, 6);
					
					speciesDropdown = new JComboBox<>(speciesChoices);
					speciesDropdown.setSelectedItem(animalD[13]);
					addRow("Animal species: ", speciesDropdown, dynamicP, 7);
					
					addRow("Country of acquisition: ", acquisitionCountryField = new JTextField(animalD[6]), dynamicP, 8);
					
					addRow("Service country: ", serviceCountryField = new JTextField(animalD[8]), dynamicP, 9);
					
					trainingStatusDropdown = new JComboBox<>(trainingStatusChoices);
					trainingStatusDropdown.setSelectedItem(animalD[7]);
					addRow("Animal training phase: ", trainingStatusDropdown, dynamicP, 10);
					
					reservedDropdown = new JComboBox<>(reservedChoices);
					reservedDropdown.setSelectedItem(animalD[9]);
					addRow("Is the animal reserved: ", reservedDropdown, dynamicP, 11);
					
					addRow("Date acquired: ",  dateField = new JFormattedTextField(animalD[5]), dynamicP, 12);
				}
				
				for (String value : animalD) {
					System.out.println("updateFields is reading animalData as: " + value);
				}
				
				System.out.println("\n");
			}
		
			dynamicP.revalidate();
			dynamicP.repaint();
		
		}

	//method used in retrieve info which populates existing information into JLabels
	public static void updatePageForDataFields(String animalName, String animalType, JPanel dynamicP, JComboBox<String> animalNamesDropdown) {
		

		System.out.println("updatePageForDataFields firing with animalName: " + animalName);
		
		//creates an instance of the database manager and queries for information recorded under the selected animal name
		DatabaseAccess db = new DatabaseAccess();
		String[] animalData = db.RetrieveInformation(animalName, animalType);
		
		dynamicP.removeAll();
		UpdateHelper.addRow("Animal Name: ", animalNamesDropdown, dynamicP, 0);
		
			//updates the dynamic panel and auto-populates with existing information on the selected dog
		if ("Dog".equals(animalType)) {
			UpdateHelper.addRow("Gender: ", new JLabel((String) animalData[2]), dynamicP, 1);
			UpdateHelper.addRow("Age: ", new JLabel((String) animalData[3]), dynamicP, 2);
			UpdateHelper.addRow("Weight: ", new JLabel((String) animalData[4]), dynamicP, 3);
			UpdateHelper.addRow("Acquisition Date: ", new JLabel((String) animalData[5]), dynamicP, 4);
			UpdateHelper.addRow("Acquisition Country: ", new JLabel((String) animalData[6]), dynamicP, 5);
			UpdateHelper.addRow("Training Status: ", new JLabel((String) animalData[7]), dynamicP, 6);
			UpdateHelper.addRow("In Service Country: ", new JLabel((String) animalData[8]), dynamicP, 7);
			UpdateHelper.addRow("Breed: ", new JLabel((String) animalData[9]), dynamicP, 8);
			UpdateHelper.addRow("Reserved: ", new JLabel((String) animalData[10]), dynamicP, 9);
			
			//updates the dynamic panel and auto-populates with existing information on the selected monkey
		} else if ("Monkey".equals(animalType)) {
			UpdateHelper.addRow("Gender: ", new JLabel((String) animalData[2]), dynamicP, 1);
			UpdateHelper.addRow("Age: ", new JLabel((String) animalData[3]), dynamicP, 2);
			UpdateHelper.addRow("Weight: ", new JLabel((String) animalData[4]), dynamicP, 3);
			UpdateHelper.addRow("Acquisition Date: ", new JLabel((String) animalData[5]), dynamicP, 4);
			UpdateHelper.addRow("Acquisition Country: ", new JLabel((String) animalData[6]), dynamicP, 5);
			UpdateHelper.addRow("Training Status: ", new JLabel((String) animalData[7]), dynamicP, 6);
			UpdateHelper.addRow("In Service Country: ", new JLabel((String) animalData[8]), dynamicP, 7);
			UpdateHelper.addRow("Reserved: ", new JLabel((String) animalData[9]), dynamicP, 8);
			UpdateHelper.addRow("Tail length: ", new JLabel((String) animalData[10]), dynamicP, 9);
			UpdateHelper.addRow("Body length: ", new JLabel((String) animalData[11]), dynamicP, 10);
			UpdateHelper.addRow("Height: ", new JLabel((String) animalData[12]), dynamicP, 11);
			UpdateHelper.addRow("Species: ", new JLabel((String) animalData[13]), dynamicP, 12);
			
		}
		
		
	    dynamicP.revalidate();
	    dynamicP.repaint();		
	}
	
	//helper method used to assist in communication with the database by creating an array containing the users input information
	//unimplemented	
	public String[] returnAndConcatenateAnswers(String animalType) {
		String[] completeString = new String[15];
		
		if ("Dog".equals(animalType)) {
			
			//retrieves the information from the users input
			String name = nameField.getText();
	        String gender = genderField.getText();
	        String age = ageField.getText();
	        String weight = weightField.getText();
	        String acquisitionDate = (dateField == null) ? "" : dateField.getText();
	        String acquisitionCountry = acquisitionCountryField.getText();
	        String trainingStatus = (trainingStatusDropdown == null) ? "" : (String) trainingStatusDropdown.getSelectedItem();
	        String inServiceCountry = serviceCountryField.getText();
	        String breed = breedField.getText();
	        String reserved = (reservedDropdown == null) ? "" : (String) reservedDropdown.getSelectedItem();

				
			//adds information into corresponding index of array
			completeString[0] = name;
			completeString[1] = gender;
			completeString[2] = age;
			completeString[3] = weight;
			completeString[4] = acquisitionDate;
			completeString[5] = acquisitionCountry;
			completeString[6] = trainingStatus;
			completeString[7] = inServiceCountry;
			completeString[8] = breed;
			completeString[9] = reserved;
			
		} else {

			//retrieves the information from the users input
            String name = nameField.getText();
            String gender = genderField.getText();
            String age = ageField.getText();
            String weight = weightField.getText();
            String acquisitionDate = (dateField == null) ? "" : dateField.getText();
            String acquisitionCountry = acquisitionCountryField.getText();
            String trainingStatus = (trainingStatusDropdown == null) ? "" : (String) trainingStatusDropdown.getSelectedItem();
            String inServiceCountry = serviceCountryField.getText();
            String reserved = (reservedDropdown == null) ? "" : (String) reservedDropdown.getSelectedItem();
            String tailLength = (tailLengthField == null) ? "" : tailLengthField.getText();
            String height = (heightField == null) ? "" : heightField.getText();
            String bodyLength = (bodyLengthField == null) ? "" : bodyLengthField.getText();
            String species = (speciesDropdown == null) ? "" : (String) speciesDropdown.getSelectedItem();

			//adds information into corresponding index of array
			completeString[0] = name; 
			completeString[1] = gender;
			completeString[2] = age;
			completeString[3] = weight;
			completeString[4] = acquisitionDate;
			completeString[5] = acquisitionCountry;
			completeString[6] = trainingStatus;
			completeString[7] = inServiceCountry;
			completeString[8] = reserved;  
			completeString[9] = tailLength;
			completeString[10] = bodyLength;
			completeString[11] = height;
			completeString[12] = species;
				
		}
		
		return completeString;
			
	}
	
	//helper method to create the date field
	public JFormattedTextField dateField() {
		
		try {
		MaskFormatter formatter = new MaskFormatter("##/##/####");
		formatter.setPlaceholderCharacter('_');
		return new JFormattedTextField(formatter);
		
		} catch (ParseException e) {
			e.printStackTrace();
			return new JFormattedTextField();
		}
	}

	//helper method to add rows into the dynamic panel
	public static void addRow(String textLabel, JComponent field, JPanel dynamicP, int rowNum) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 5, 10);
		gbc.anchor = GridBagConstraints.WEST;
		
		//sets up the corresponding textLabel field i.e. "Animal breed: "
		gbc.gridx = 0;
		gbc.gridy = rowNum;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		dynamicP.add(new JLabel(textLabel), gbc);
		
		//sets up the corresponding JComponenet field i.e. the text box correlated to the animal breed question
		gbc.gridx = 1;
		gbc.gridy = rowNum;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		dynamicP.add(field, gbc);
	}
	
}
