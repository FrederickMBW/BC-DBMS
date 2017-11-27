import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

@SuppressWarnings("serial")
public class JPAddCourse extends JPanel {
	private JTextField tfSID;
    private JLabel lbSID;
	private JTextField tfName;
    private JLabel lbName;
	private JTextField tfTitle;
    private JLabel lbTitle;
	private JTextField tfDepartment;
    private JLabel lbDepartment;
	private JTextField tfCredits;
    private JLabel lbCredits;
    private JScrollPane spDescription;
    private JTextArea taDescription;
    private JLabel lbDescription;
    private JScrollPane spOutcomes;
    private JTextArea taOutcomes;
    private JLabel lbOutcomes;
	private JTextField tfContactEmail;
    private JLabel lbContactEmail;
	private JTextField tfContactName;
    private JLabel lbContactName;
    private JButton btnAdd;
    
	public JPAddCourse(Connection con) {
		super(new GridBagLayout());

        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL; 
        
        lbSID = new JLabel("SID (School ID):");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbSID, cs);
        
        tfSID = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(tfSID, cs);
        
        lbName = new JLabel("Course Name:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(lbName, cs);
        
        tfName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfName, cs);
        
        lbTitle = new JLabel("Course Title:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(lbTitle, cs);
        
        tfTitle = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(tfTitle, cs);
        
        lbDepartment = new JLabel("Course Department:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(lbDepartment, cs);
        
        tfDepartment = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(tfDepartment, cs);
        
        lbCredits = new JLabel("Credits:");
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(lbCredits, cs);
        
        tfCredits = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(tfCredits, cs);
        
        lbDescription = new JLabel("Course Description:");
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(lbDescription, cs);
        
        taDescription = new JTextArea(5, 30);
        taDescription.setSize(new Dimension(5, 30));
        taDescription.setWrapStyleWord(true);
        taDescription.setLineWrap(true);
        spDescription = new JScrollPane(taDescription);
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(spDescription, cs);
        
        lbOutcomes = new JLabel("Course Outcomes:");
        cs.gridx = 0;
        cs.gridy = 6;
        cs.gridwidth = 1;
        this.add(lbOutcomes, cs);
        
        taOutcomes = new JTextArea(5, 30);
        taOutcomes.setSize(new Dimension(5, 30));
        taOutcomes.setWrapStyleWord(true);
        taOutcomes.setLineWrap(true);
        spOutcomes = new JScrollPane(taOutcomes);
        cs.gridx = 1;
        cs.gridy = 6;
        cs.gridwidth = 1;
        this.add(spOutcomes, cs);
        
        lbContactEmail = new JLabel("Contact Email:");
        cs.gridx = 0;
        cs.gridy = 7;
        cs.gridwidth = 1;
        this.add(lbContactEmail, cs);
        
        tfContactEmail = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 7;
        cs.gridwidth = 1;
        this.add(tfContactEmail, cs);
        
        lbContactName = new JLabel("Contact Name:");
        cs.gridx = 0;
        cs.gridy = 8;
        cs.gridwidth = 1;
        this.add(lbContactName, cs);
        
        tfContactName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 8;
        cs.gridwidth = 1;
        this.add(tfContactName, cs);
        
        btnAdd = new JButton("Add Course");
        cs.gridx = 1;
        cs.gridy = 9;
        cs.gridwidth = 1;
        this.add(btnAdd, cs);
        
        btnAdd.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			try {
					addCourse(con);
					JOptionPane.showMessageDialog(JPAddCourse.this, "You have successfully added a course.", "Nice Job!", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException|NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPAddCourse.this, "Error 404", "Error", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
	}
	
	//Return the SID
    public String getSID() {
    		return tfSID.getText().trim();
    }
    
	//Return the course name
    public String getName() {
    		return tfName.getText().trim();
    }
    
	//Return the course title
    public String getTitle() {
    		return tfTitle.getText().trim();
    }
    
	//Return the course department
    public String getDepartment() {
        return tfDepartment.getText().trim();
    }
    
	//Return the course credits
    public String getCredits() {
        return tfCredits.getText().trim();
    }
    
	//Return the course description
    public String getDescription() {
        return taDescription.getText().trim();
    }
    
	//Return the course outcomes
    public String getCourseOutcomes() {
        return taOutcomes.getText().trim();
    }
    
	//Return the course description
    public String getContactEmail() {
        return tfContactEmail.getText().trim();
    }
    
	//Return the course description
    public String getContactName() {
        return tfContactName.getText().trim();
    }

    //Create a pop up for the result of the search
    public void addCourse(Connection con) throws SQLException, NumberFormatException {
    		String strSID = getSID().trim();
    		String strName = getName().trim();
    		String strTitle = getTitle().trim();
    		String strDepartment =  getDepartment().trim();
    		String strCredits =  getCredits().trim();
    		String strDescription =  getDescription().trim();
    		String strOutcomes=  getCourseOutcomes().trim();
    		String strContactEmail =  getContactEmail().trim();
    		String strContactName =  getContactName().trim();

    		Queries.addCourse(con, Integer.parseInt(strSID), strName, strTitle, strDepartment, Integer.parseInt(strCredits), strDescription, strOutcomes, strContactEmail, strContactName);

    }
}