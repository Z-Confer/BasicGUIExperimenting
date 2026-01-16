package gui;

import java.sql.*;

//basic database query management 
public class DatabaseAccess {
	private String databaseURL = "jdbc:ucanaccess://C:/Users/Zach/Desktop/Java/BasicGUI/src/gui/GraziosoAnimals.accdb";

	//adds information based on user inputs
	public void AddInformation(String[] finishedFields, String animalType) {

		try (Connection connection = DriverManager.getConnection(databaseURL)) {
			
			
			if ("Dog".equals(animalType)) {
				String sql = "INSERT INTO Dogs ([Animal Name], Gender, Age, Weight, [Acquisition Date], [Acquisition Country], [Training Status], [In Service Country], [Animal Breed], Reserved) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, finishedFields[0]);
				preparedStatement.setString(2, finishedFields[1]);
				preparedStatement.setString(3, finishedFields[2]);
				preparedStatement.setString(4, finishedFields[3]);
				preparedStatement.setString(5, finishedFields[4]);
				preparedStatement.setString(6, finishedFields[5]);
				preparedStatement.setString(7, finishedFields[6]);
				preparedStatement.setString(8, finishedFields[7]);
				preparedStatement.setString(9, finishedFields[8]);
				preparedStatement.setString(10, finishedFields[9]);
				
				int row = preparedStatement.executeUpdate();
				
				if (row > 0) {
					System.out.println("A row has successfully been added");
				}
				
			} else {
				String sql = "INSERT INTO Monkeys ([Animal Name], Gender, Age, Weight, [Acquisition Date], [Acquisition Country], [Training Status], [In Service Country], Reserved, [Tail Length], [Body Length], Height, Species) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, finishedFields[0]);
				preparedStatement.setString(2, finishedFields[1]);
				preparedStatement.setString(3, finishedFields[2]);
				preparedStatement.setString(4, finishedFields[3]);
				preparedStatement.setString(5, finishedFields[4]);
				preparedStatement.setString(6, finishedFields[5]);
				preparedStatement.setString(7, finishedFields[6]);
				preparedStatement.setString(8, finishedFields[7]);
				preparedStatement.setString(9, finishedFields[8]);
				preparedStatement.setString(10, finishedFields[9]);
				preparedStatement.setString(11, finishedFields[10]);
				preparedStatement.setString(12, finishedFields[11]);
				preparedStatement.setString(13, finishedFields[12]);
				
				int row = preparedStatement.executeUpdate();
				
				if (row > 0) {
					System.out.println("A row has successfully been added");
				}
			}
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	//retrieve information for animal in either Dogs or Monkeys tables based on name querying 
	public String[] RetrieveInformation (String animalName, String animalType) {
		
		try (Connection connection = DriverManager.getConnection(databaseURL)) {
			
			if ("Dog".equals(animalType)) {
				
				String sql = "SELECT * FROM Dogs WHERE [Animal Name] = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, animalName);
				ResultSet result = statement.executeQuery();
				String [] allInfo = new String[10];
				allInfo = convertResultSetIntoStringArrayData(result, animalType);
				return allInfo;
				
				
			} else if("Monkey".equals(animalType)) {
				
				String sql = "SELECT * FROM Monkeys WHERE [Animal Name] = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, animalName);
				ResultSet result = statement.executeQuery();
				String [] allInfo = new String[13];
				allInfo = convertResultSetIntoStringArrayData(result, animalType);
				return allInfo;				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new String[0];
	}
	
	
	//solves for the array length of a set of data
	public int resultSetLength(ResultSet results){
		int length = 0;
		
		try {
		while (results.next()) {
			length++;
		}
		
		return length;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
		//method utilized in turning the ResultSet from querying the db into an array(Specific to the names, not other column data)
	public String[] convertResultSetIntoStringArrayNames(ResultSet results, int index, int resultsLength) {
		String[] resultsFinal = new String[resultsLength];
		
		try {

			results.next();
			
			while (index < resultsLength) {
					resultsFinal[index] = results.getString("Animal Name");
					results.next();
					index++;
			}
			
			return resultsFinal;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[0];
		}
	}
	
	//method similar to the previous method, however used to turn all other columns into a readable array
	public String[] convertResultSetIntoStringArrayData(ResultSet results, String animalType) {
		int colCount;
		String[] data;
				
		if ("Dog".equals(animalType)) {
			colCount = 11; //hard coded column count for number of fields related to the Dog animal type 
			
		} else if ("Monkey".equals(animalType)) {
			colCount = 14; //hard coded column count for number of fields related to the Monkey animal type
			
		} else {
			colCount = 0;
		}
		
		data = new String[colCount];
		
		try {
			results.next();
			for (int col = 1; col <= colCount; col++) {
				data[col-1] = results.getString(col);
			}
			
			return data;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[0];
		}
	}
	
	//returns all names from the database
	public String[] returnNames(String animalType) {
		int len = 0;
		String[] allNames;
		
		try (Connection connection = DriverManager.getConnection(databaseURL)) {
			
			if ("Dog".equals(animalType)) {
				
				String sql = "SELECT [Animal Name] FROM Dogs";
				
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				len = resultSetLength(result);
				result = statement.executeQuery(sql);
				allNames = new String[len];
				allNames = convertResultSetIntoStringArrayNames(result, 0, len);
				return allNames;
				
			} else if ("Monkey".equals(animalType)) {
				
				String sql = "SELECT [Animal Name] FROM Monkeys";
				
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				len = resultSetLength(result);
				result = statement.executeQuery(sql);
				allNames = new String[len];
				allNames = convertResultSetIntoStringArrayNames(result, 0, len);
				return allNames;
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new String[0];
	}
	
	//method used to update existing information
	public void updateInformation(String animalType, String[] finishedFields, String animalName) {
		
		for (String value : finishedFields) {
			System.out.println("updateInformation is being fed animalData as: " + value);
		}
		
		System.out.println("\n");
		
		try (Connection connection = DriverManager.getConnection(databaseURL)){
			
			//Queries the Dogs table and sets the answer fields in the db to be whatever currently resides in the text boxes
			if ("Dog".equals(animalType)) {
				String sql = "UPDATE Dogs SET [Animal Name] = ?, Gender = ?, Age = ?, Weight = ?, [Acquisition Date] = ?, [Acquisition Country] = ?, [Training Status] = ?, [In Service Country] = ?, [Animal Breed] = ?, Reserved = ? WHERE [Animal Name] = ?";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, finishedFields[0]);
				preparedStatement.setString(2, finishedFields[1]);
				preparedStatement.setString(3, finishedFields[2]);
				preparedStatement.setString(4, finishedFields[3]);
				preparedStatement.setString(5, finishedFields[4]);
				preparedStatement.setString(6, finishedFields[5]);
				preparedStatement.setString(7, finishedFields[6]);
				preparedStatement.setString(8, finishedFields[7]);
				preparedStatement.setString(9, finishedFields[8]);
				preparedStatement.setString(10, finishedFields[9]);
				preparedStatement.setString(11, animalName);
				
				int row = preparedStatement.executeUpdate();
				
				if (row > 0) {
					System.out.println("A row has successfully been updated");
				}
				
				//Queries the Monkeys table and sets the answer fields in the db to be whatever currently resides in the text boxes				
			} else {
				String sql = "UPDATE Monkeys SET [Animal Name] = ?, Gender = ?, Age = ?, Weight = ?, [Acquisition Date] = ?, [Acquisition Country] = ?, [Training Status] = ?, [In Service Country] = ?, Reserved = ?, [Tail Length] = ?, [Body Length] = ?, Height = ?, Species = ? WHERE [Animal Name] = ?";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, finishedFields[0]);
				preparedStatement.setString(2, finishedFields[1]);
				preparedStatement.setString(3, finishedFields[2]);
				preparedStatement.setString(4, finishedFields[3]);
				preparedStatement.setString(5, finishedFields[4]);
				preparedStatement.setString(6, finishedFields[5]);
				preparedStatement.setString(7, finishedFields[6]);
				preparedStatement.setString(8, finishedFields[7]);
				preparedStatement.setString(9, finishedFields[8]);
				preparedStatement.setString(10, finishedFields[9]);
				preparedStatement.setString(11, finishedFields[10]);
				preparedStatement.setString(12, finishedFields[11]);
				preparedStatement.setString(13, finishedFields[12]);
				preparedStatement.setString(14, animalName);
				
				int row = preparedStatement.executeUpdate();
		
			if (row > 0) {
				System.out.println("A row has successfully been updated");
			}
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
