import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

@SuppressWarnings("serial")
public class JPAddEquivalentCourse extends JPanel {
	private JTextField tfCID1;
    private JLabel lbCID1;
	private JTextField tfCID2;
    private JLabel lbCID2;
    private JLabel lbIsEquivalent;
    private JComboBox<String> jbTrueFalse;
	private JTextField tfComment;
    private JLabel lbComment;
    private JButton btnAdd;
    
    //(IN CID1 INTEGER, CID2 INTEGER, inIS_EQUIVALENT BOOL, inEC_COMMENT VARCHAR(140))
    
	public JPAddEquivalentCourse(Connection con) {
		super(new GridBagLayout());

        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL; 
        
        lbCID1 = new JLabel("CID1:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbCID1, cs);
        
        tfCID1 = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(tfCID1, cs);
        
        lbCID2 = new JLabel("CID2:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(lbCID2, cs);
        
        tfCID2 = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfCID2, cs);
        
        lbIsEquivalent = new JLabel("Is Equivalent:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(lbIsEquivalent, cs);
        
        String[] aryTrueFalse = {"True", "False"};
        jbTrueFalse = new JComboBox<String>(aryTrueFalse);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(jbTrueFalse, cs);
        
        lbComment = new JLabel("Comment:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(lbComment, cs);
        
        tfComment = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(tfComment, cs);
   
        btnAdd = new JButton("Add Equivalent Course");
        cs.gridx = 1;
        cs.gridy = 9;
        cs.gridwidth = 1;
        this.add(btnAdd, cs);
        
        btnAdd.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			try {
					addCourse(con);
					JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "You have successfully added a course.", "Nice Job!", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException|NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "Error 404", "Error", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
	}
	
	//Return the CID of course on (Bellevue College Course)
    public String getCID1() {
    		return tfCID1.getText().trim();
    }
    
	//Return the course name
    public String getCID2() {
    		return tfCID2.getText().trim();
    }
    
	//Return if the course if equivalent
    public String getIsEquivalent() {
    		return jbTrueFalse.getItemAt(jbTrueFalse.getSelectedIndex());
    }
    
	//Return the comment
    public String getComment() {
        return tfComment.getText().trim();
    }

    //Create a pop up for the result of the search
    public void addCourse(Connection con) throws SQLException, NumberFormatException {
    		String strCID1 = getCID1().trim();
    		String strCID2 = getCID2().trim();
    		String strIsEquivalent = getIsEquivalent().trim();
    		String strComment = getComment().trim();

    		Queries.addEquivalentCourse(con, Integer.parseInt(strCID1), Integer.parseInt(strCID2), Boolean.valueOf(strIsEquivalent), strComment);
    }
}