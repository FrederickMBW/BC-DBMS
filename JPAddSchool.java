import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

@SuppressWarnings("serial")
public class JPAddSchool extends JPanel {
	private JTextField tfOID;
    private JLabel lbOID;
	private JTextField tfName;
    private JLabel lbName;
	private JTextField tfAddress;
    private JLabel lbAddress;
	private JTextField tfCity;
    private JLabel lbCity;
	private JTextField tfState;
    private JLabel lbState;
	private JTextField tfZip;
    private JLabel lbZip;
    private JButton btnAdd;
	
	public JPAddSchool(Connection con) {
		super(new GridBagLayout());

        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL; 
        
        lbOID = new JLabel("OID (FAFSA):");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbOID, cs);
        
        tfOID = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(tfOID, cs);
        
        lbName = new JLabel("School Name:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(lbName, cs);
        
        tfName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfName, cs);
        
        lbAddress = new JLabel("School Address:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(lbAddress, cs);
        
        tfAddress = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(tfAddress, cs);
        
        lbCity = new JLabel("School City:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(lbCity, cs);
        
        tfCity = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(tfCity, cs);
        
        lbState = new JLabel("School State:");
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(lbState, cs);
        
        tfState = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(tfState, cs);
        
        lbZip = new JLabel("School Zip Code:");
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(lbZip, cs);
        
        tfZip = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(tfZip, cs);
        
        btnAdd = new JButton("Add School");
        cs.gridx = 1;
        cs.gridy = 6;
        cs.gridwidth = 1;
        this.add(btnAdd, cs);
        
        btnAdd.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			try {
					addSchool(con);
					JOptionPane.showMessageDialog(JPAddSchool.this, "You have successfully added a school.", "High Five!", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException|NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPAddSchool.this, "Error 404", "Error", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
	}
	
	//Return the OID
    public String getOID() {
    		return tfOID.getText().trim();
    }
    
	//Return the course name
    public String getName() {
    		return tfName.getText().trim();
    }
    
	//Return the school address
    public String getAddress() {
    		return tfAddress.getText().trim();
    }
    
	//Return the school city
    public String getCity() {
        return tfCity.getText().trim();
    }
    
	//Return the school state
    public String getState() {
        return tfState.getText().trim();
    }
    
	//Return the school zipcode
    public String getZipCode() {
        return tfZip.getText().trim();
    }
    
    //Create a pop up for the result of the search
    public void addSchool(Connection con) throws SQLException, NumberFormatException {
    		String strOID = getOID().trim();
    		String strName = getName().trim();
    		String strAddress = getAddress().trim();
    		String strCity =  getCity().trim();
    		String strState =  getState().trim();
    		String strZip =  getZipCode().trim();

    		Queries.addSchool(con, strOID, strName, strAddress, strCity, strState, strZip);

    }
}