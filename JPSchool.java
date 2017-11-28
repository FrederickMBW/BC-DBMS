import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;

@SuppressWarnings("serial")
public class JPSchool extends JPanel {
	private JComboBox<Integer> cbAllSID;
    private JLabel lbSID, lbOID, lbName, lbAddress, lbCity, lbState, lbZip;
	private JTextField tfOID, tfName, tfAddress, tfCity, tfState, tfZip;
    private JButton btnAdd, btnUpdate, btnSearch, btnClear;
	
	public JPSchool(Connection con) throws SQLException {
		super(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL; 
        
        lbSID = new JLabel("SID (School ID):");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbSID, cs);
        
        Integer[] aryAllSID = Queries.getAllSID(con);
        cbAllSID = new JComboBox<Integer>(aryAllSID);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(cbAllSID, cs);
        
        lbOID = new JLabel("OID (FAFSA):");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(lbOID, cs);
        
        tfOID = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfOID, cs);
        
        lbName = new JLabel("School Name:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(lbName, cs);
        
        tfName = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(tfName, cs);
        
        lbAddress = new JLabel("School Address:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(lbAddress, cs);
        
        tfAddress = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(tfAddress, cs);
        
        lbCity = new JLabel("School City:");
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(lbCity, cs);
        
        tfCity = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(tfCity, cs);
        
        lbState = new JLabel("School State:");
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(lbState, cs);
        
        tfState = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(tfState, cs);
        
        lbZip = new JLabel("School Zip Code:");
        cs.gridx = 0;
        cs.gridy = 6;
        cs.gridwidth = 1;
        this.add(lbZip, cs);
        
        tfZip = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 6;
        cs.gridwidth = 1;
        this.add(tfZip, cs);
        
        btnSearch = new JButton("Search");
        cs.gridx = 1;
        cs.gridy = 7;
        cs.gridwidth = 1;
        this.add(btnSearch, cs);
        
        btnUpdate = new JButton("Update");
        cs.gridx = 1;
        cs.gridy = 8;
        cs.gridwidth = 1;
        this.add(btnUpdate, cs);
        
        btnAdd = new JButton("Add");
        cs.gridx = 1;
        cs.gridy = 9;
        cs.gridwidth = 1;
        this.add(btnAdd, cs);
        
        btnClear = new JButton("Clear");
        cs.gridx = 1;
        cs.gridy = 10;
        cs.gridwidth = 1;
        this.add(btnClear, cs);
        
        //Update the school in the database when the update button is hit
        //Don't update if the SID selected is 0
        btnUpdate.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			if (getSID() != 0) {
	        			try {
						updateSchool(con);
						JOptionPane.showMessageDialog(JPSchool.this, "You have successfully updated a school.", "You Did It!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException|NumberFormatException e1) {
						JOptionPane.showMessageDialog(JPSchool.this, "MySql Error", "Error", JOptionPane.ERROR_MESSAGE);
					}catch (IllegalArgumentException e3) {
						JOptionPane.showMessageDialog(JPSchool.this, "Illegal Argument Error", "Error", JOptionPane.ERROR_MESSAGE);
					}
        			} else {
        				JOptionPane.showMessageDialog(JPSchool.this, "Can't Update SID 0!", "Error", JOptionPane.ERROR_MESSAGE);
        			}
            }
        });
        
        //Add a new school to the database when the add button is clicked
        //Clear all the fields after adding the school
        //Only allow a school to be added when SID selected is 0
        btnAdd.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			if (getSID() == 0) {
		    			try {
						addSchool(con);
						cbAllSID.setSelectedItem(0);
						//updatejbAllSID(con); TODO
						JOptionPane.showMessageDialog(JPSchool.this, "You have successfully added a school.", "High Five!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(JPSchool.this, "Mysql Error", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (IllegalArgumentException e3) {
						JOptionPane.showMessageDialog(JPSchool.this, "Illegal Argument Error", "Error", JOptionPane.ERROR_MESSAGE);
					}
	    			} else {
	    				JOptionPane.showMessageDialog(JPSchool.this, "SID Must Be 0 To Add A School", "Error", JOptionPane.ERROR_MESSAGE);
	    			}
	        }
        });
        
        //Clear the table
        //Set the SID to 0 and all the fields to blank
        btnClear.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			cbAllSID.setSelectedItem(0);
	        }
        });
        
        //Update the fields every time a new SID is selected
        cbAllSID.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			if (getSID() != 0) {
		    			try {
		    				updateFields(con);
		    			} catch (SQLException|NumberFormatException e1) {
		    				JOptionPane.showMessageDialog(JPSchool.this, "Load Failed", "Error", JOptionPane.ERROR_MESSAGE);
					}
	    			} else {
	    				clearFields();
	    			}
	    		}
        });
        
        //Search the database for schools that match the data in the fields
        btnSearch.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			try {
					searchAndDisplay(con);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(JPSchool.this, "MySql Error", "Error", JOptionPane.ERROR_MESSAGE);
				}
	        }
        });
	}
	
	//Return the SID
    public int getSID() {
    		return (int) cbAllSID.getSelectedItem();
    }
	
	//Return the OID
    public String getOID() {
    		return tfOID.getText().trim();
    }
    
	//Return the name
    public String getName() {
    		return tfName.getText().trim();
    }
    
	//Return the address
    public String getAddress() {
    		return tfAddress.getText().trim();
    }
    
	//Return the city
    public String getCity() {
        return tfCity.getText().trim();
    }
    
	//Return the state
    public String getState() {
        return tfState.getText().trim();
    }
    
	//Return the zip code
    public String getZipCode() {
        return tfZip.getText().trim();
    }
    
    //Update a school with the data in the fields
    public void updateSchool(Connection con) throws SQLException, NumberFormatException, IllegalArgumentException {
    		//Get the currently selected SID
    		int intSID = getSID();
    		
    		//Get the data from the fields
    		String strOID = getOID();
    		String strName = getName();
    		String strAddress = getAddress();
    		String strCity =  getCity();
    		String strState =  getState();
    		String strZip =  getZipCode();
    		
    		//Update the school in the database
    		//Only Update if name is not blank
    		if (illegalDataFields(strName)) {
    			throw new IllegalArgumentException();
    		} else {
    			Queries.updateSchool(con, intSID, strOID, strName, strAddress, strCity, strState, strZip);
    		}
    }
    
    //Load the data for a school into data fields
    public void updateFields(Connection con) throws SQLException, NumberFormatException {
    		//Get the SID currently selected
    		int intSID = getSID();
    	
    		//Get the information for the school with the selected SID
    		ResultSet rs = Queries.getSchool(con, intSID);
    		
    		//If a SID exists, update the fields to match the information for that SID
    		//Otherwise give an error message
    		if (rs.next()) {
    			tfOID.setText(rs.getString(2));
    			tfName.setText(rs.getString(3));
    			tfAddress.setText(rs.getString(4));
    			tfCity.setText(rs.getString(5));
    			tfState.setText(rs.getString(6));
    			tfZip.setText(rs.getString(7));
    		} else {
    			JOptionPane.showMessageDialog(JPSchool.this, "School Not Found", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    }
    
    //Add a school to the database
    public void addSchool(Connection con) throws SQLException, IllegalArgumentException {
    		//Get all the data from the fields
    		String strOID = getOID();
    		String strName = getName();
    		String strAddress = getAddress();
    		String strCity =  getCity();
    		String strState =  getState();
    		String strZip =  getZipCode();

    		//If name is blank, give an error message
    		//Otherwise add the school to the database
    		if (illegalDataFields(strName)) {
    			throw new IllegalArgumentException();
    		} else {
    			Queries.addSchool(con, strOID, strName, strAddress, strCity, strState, strZip);
    		}
    }
    
    //Search for a school with the given information
    //Create a popup with all the schools that match given information
    //SID overrides all other data fields
    public void searchAndDisplay(Connection con) throws SQLException {
    		ResultSet rs;
    	
    		if (getSID() == 0) {
	    		//Get all the data from the fields and add wild cards to every space, the front, and the end
	    		String strOID = "%" + getOID().replace(' ', '%') + "%";
	    		String strName = "%" + getName().replace(' ', '%') + "%";
	    		String strAddress = "%" + getAddress().replace(' ', '%') + "%";
	    		String strCity = "%" + getCity().replace(' ', '%') + "%";
	    		String strState = "%" + getState().replace(' ', '%') + "%";
	    		String strZipCode =  "%" + getZipCode().replace(' ', '%') + "%";
	    		
	    		//Query for all the matching schools
	    		rs = Queries.searchSchool(con, strOID, strName, strAddress, strCity, strState, strZipCode);
    		} else {
    			//Get the school with the currently selected SID
    			int intSID = getSID();
    			rs = Queries.getSchool(con, intSID);
    		}
    		
    		//Create a JTable to hold the results and set the preferred width for the columns
    		JTable jtbResult = Queries.ResultSetToJTable(rs);
		jtbResult.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtbResult.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtbResult.getColumnModel().getColumn(2).setPreferredWidth(250);
		jtbResult.getColumnModel().getColumn(3).setPreferredWidth(200);
		jtbResult.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtbResult.getColumnModel().getColumn(5).setPreferredWidth(50);
		int intWidth = 750;
		
		//Create a popup with the results.
    		new PopUp(new JScrollPane(jtbResult), "Results", intWidth, 300);
    }
    
    //Clear all the data fields
    public void clearFields() {
		tfOID.setText("");
		tfName.setText("");
		tfAddress.setText("");
		tfCity.setText("");
		tfState.setText("");
		tfZip.setText("");
    }
    
    //Make sure none of the NOT NULL data fields in the database are blank and that SID != 0
    public boolean illegalDataFields(String strName) {
    		if (strName.equals("")) {
    			return true;
    		}
    		return false;
    }
    	
//    //Update jbAllSID to include all SIDs // TODO
//	public void updatejbAllSID(Connection con) {
//    	
//    }
}