import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

@SuppressWarnings("serial")
public class JPQuerySchool extends JPanel {
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
    private JButton btnSearch;
	
	public JPQuerySchool(Connection con) {
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
        
        lbName = new JLabel("Name:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(lbName, cs);
        
        tfName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfName, cs);
        
        lbAddress = new JLabel("Address:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(lbAddress, cs);
        
        tfAddress = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(tfAddress, cs);
        
        lbCity = new JLabel("City:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(lbCity, cs);
        
        tfCity = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(tfCity, cs);
        
        lbState = new JLabel("State:");
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(lbState, cs);
        
        tfState = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(tfState, cs);
        
        lbZip = new JLabel("ZipCode:");
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(lbZip, cs);
        
        tfZip = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(tfZip, cs);
        
        btnSearch = new JButton("Search");
        cs.gridx = 1;
        cs.gridy = 6;
        cs.gridwidth = 1;
        this.add(btnSearch, cs);
        
        btnSearch.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			try {
					showResultSet(con);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(JPQuerySchool.this, "Error 404", "Error", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
	}
	
	//Return the other ID
    public String getOID() {
    		return tfOID.getText().trim();
    }
    
	//Return the school name
    public String getName() {
    		return tfName.getText().trim();
    }
    
	//Return the address
    public String getAddress() {
    		return tfAddress.getText().trim();
    }
    
	//Return the City
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
    
    //Create a pop up for the result of the search
    public void showResultSet(Connection con) throws SQLException {
    		String strOID = "%" + getOID().trim().replace(' ', '%') + "%";
    		String strName = "%" + getName().trim().replace(' ', '%') + "%";
    		String strAddress = "%" + getAddress().trim().replace(' ', '%') + "%";
    		String strCity = "%" + getCity().trim().replace(' ', '%') + "%";
    		String strState = "%" + getState().trim().replace(' ', '%') + "%";
    		String strZipCode =  "%" + getZipCode().trim().replace(' ', '%') + "%";
    		
    		ResultSet rsResults = Queries.searchSchool(con, strOID, strName, strAddress, strCity, strState, strZipCode);
    		JTable jtbResult = Queries.ResultSetToJTable(rsResults);
		jtbResult.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtbResult.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtbResult.getColumnModel().getColumn(2).setPreferredWidth(250);
		jtbResult.getColumnModel().getColumn(3).setPreferredWidth(200);
		jtbResult.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtbResult.getColumnModel().getColumn(5).setPreferredWidth(50);
		int intWidth = 750;
    			
    		new PopUp(new JScrollPane(jtbResult), "Results", intWidth, 300);
    }
}