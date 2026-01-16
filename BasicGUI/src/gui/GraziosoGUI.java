package gui;

import java.awt.*;        
import java.awt.event.*;

import javax.swing.*;

public class GraziosoGUI extends JFrame {
	private JButton addAnimal;
	private JButton updateInfo;
	private JButton retrieveInfo;
	
	public GraziosoGUI() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		addAnimal = new JButton("Add new animal");
		add(addAnimal);
		
		updateInfo = new JButton("Update existing Information");
		add(updateInfo);
		
		retrieveInfo = new JButton("Retrieve Information");
		add(retrieveInfo);
		
		addAnimal.addActionListener(new addAnimal());
		updateInfo.addActionListener(new updateAnimal());
		retrieveInfo.addActionListener(new retrieveInformation());
		addWindowListener(new closeWindow());
		
		setTitle("Grazioso Salvare");
		setSize(300, 175);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		GraziosoGUI app = new GraziosoGUI();
	}
	

	private class addAnimal implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			AddAnimal animal = new AddAnimal();
		}
	}
	
	private class updateAnimal implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			UpdateInfo animal = new UpdateInfo();
		}
	}
	
	private class retrieveInformation implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			RetrieveInfo animal = new RetrieveInfo();
		}
	}
	
	
	private class closeWindow implements WindowListener {
	  
	  public void windowClosing(WindowEvent evt) { System.exit(0); }
	  
	  // Not Used, BUT need to provide an empty body to compile.
	  
	  @Override public void windowOpened(WindowEvent evt) { }
	  
	  @Override public void windowClosed(WindowEvent evt) { }
	  
	  @Override public void windowIconified(WindowEvent evt) {
	  System.out.println("Window Iconified"); }
	  
	  @Override public void windowDeiconified(WindowEvent evt) {
	  System.out.println("Window Deiconified"); }
	  
	  @Override public void windowActivated(WindowEvent evt) {
	  System.out.println("Window Activated"); }
	  
	  @Override public void windowDeactivated(WindowEvent evt) {
	  System.out.println("Window Deactivated"); }
	  
	  }
	 
	
}
