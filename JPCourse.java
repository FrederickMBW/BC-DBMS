package com.company;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

@SuppressWarnings("serial")
public class JPCourse extends JPanel {
    private JComboBox<Integer> cbAllCID, cbSID;
    private JLabel lbCID, lbSID, lbName, lbTitle, lbDepartment, lbCredit, lbDescription, lbOutcomes, lbContactEmail, lbContactName, lbSchoolName;
    private JTextField tfName, tfTitle, tfDepartment, tfCredits, tfContactEmail, tfContactName, tfSchoolName;
    private JScrollPane spDescription, spOutcomes;
    private JTextArea taOutcomes, taDescription;
    private JButton btnAdd, btnUpdate, btnSearch, btnClear;

    public JPCourse(Connection con) throws SQLException {
        super(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        //Make all the buttons and fields
        lbCID = new JLabel("CID (Course ID):");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbCID, cs);

        Integer[] aryAllCID = Queries.getArrayAllCID(con);
        cbAllCID = new JComboBox<Integer>(aryAllCID);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(cbAllCID, cs);

        lbSID = new JLabel("SID (School ID):");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(lbSID, cs);

        Integer[] aryAllSID = Queries.getArrayAllSID(con);
        cbSID = new JComboBox<Integer>(aryAllSID);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(cbSID, cs);

        lbSchoolName = new JLabel("School Name:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(lbSchoolName, cs);

        tfSchoolName = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(tfSchoolName, cs);

        lbName = new JLabel("Name:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(lbName, cs);

        tfName = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(tfName, cs);

        lbTitle = new JLabel("Title:");
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(lbTitle, cs);

        tfTitle = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(tfTitle, cs);

        lbDepartment = new JLabel("Department:");
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(lbDepartment, cs);

        tfDepartment = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(tfDepartment, cs);

        lbCredit = new JLabel("Credits:");
        cs.gridx = 0;
        cs.gridy = 6;
        cs.gridwidth = 1;
        this.add(lbCredit, cs);

        tfCredits = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 6;
        cs.gridwidth = 1;
        this.add(tfCredits, cs);

        lbDescription = new JLabel("Description:");
        cs.gridx = 0;
        cs.gridy = 7;
        cs.gridwidth = 1;
        this.add(lbDescription, cs);

        taDescription = new JTextArea(5, 30);
        taDescription.setSize(new Dimension(5, 30));
        taDescription.setWrapStyleWord(true);
        taDescription.setLineWrap(true);
        spDescription = new JScrollPane(taDescription);
        cs.gridx = 1;
        cs.gridy = 7;
        cs.gridwidth = 1;
        this.add(spDescription, cs);

        lbOutcomes = new JLabel("Outcomes:");
        cs.gridx = 0;
        cs.gridy = 8;
        cs.gridwidth = 1;
        this.add(lbOutcomes, cs);

        taOutcomes = new JTextArea(5, 30);
        taOutcomes.setSize(new Dimension(5, 30));
        taOutcomes.setWrapStyleWord(true);
        taOutcomes.setLineWrap(true);
        spOutcomes = new JScrollPane(taOutcomes);
        cs.gridx = 1;
        cs.gridy = 8;
        cs.gridwidth = 1;
        this.add(spOutcomes, cs);

        lbContactEmail = new JLabel("Contact Email:");
        cs.gridx = 0;
        cs.gridy = 9;
        cs.gridwidth = 1;
        this.add(lbContactEmail, cs);

        tfContactEmail = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 9;
        cs.gridwidth = 1;
        this.add(tfContactEmail, cs);

        lbContactName = new JLabel("Contact Name:");
        cs.gridx = 0;
        cs.gridy = 10;
        cs.gridwidth = 1;
        this.add(lbContactName, cs);

        tfContactName = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 10;
        cs.gridwidth = 1;
        this.add(tfContactName, cs);

        btnSearch = new JButton("Search");
        cs.gridx = 1;
        cs.gridy = 11;
        cs.gridwidth = 1;
        this.add(btnSearch, cs);

        btnUpdate = new JButton("Update");
        cs.gridx = 1;
        cs.gridy = 12;
        cs.gridwidth = 1;
        this.add(btnUpdate, cs);

        btnAdd = new JButton("Add");
        cs.gridx = 1;
        cs.gridy = 13;
        cs.gridwidth = 1;
        this.add(btnAdd, cs);

        btnClear = new JButton("Clear");
        cs.gridx = 1;
        cs.gridy = 14;
        cs.gridwidth = 1;
        this.add(btnClear, cs);

        //Update a course in the database when the button is clicked
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getCID() != 0) {
                    try {
                        updateCourse(con);
                        JOptionPane.showMessageDialog(JPCourse.this, "You have successfully updated a course.", "You Did It!", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException e1) {
                        CommonDialogs.mySqlErrorMessage(e1);
                    } catch (NumberFormatException e2) {
                        CommonDialogs.numberFormatErrorMessage(e2);
                    } catch (IllegalArgumentException e3) {
                        CommonDialogs.illegalArgumnetExceptionMessage(e3);
                    }
                } else {
                    JOptionPane.showMessageDialog(JPCourse.this, "Can't Update CID of 0!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Clear all data fields when button is clicked
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cbAllCID.setSelectedIndex(0);
            }
        });

        //Adds a course when button is clicked
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getCID() == 0) {
                    try {
                        addCourse(con);
                        updatejbAllCID(con);
                        JOptionPane.showMessageDialog(JPCourse.this, "You have successfully added a course.", "Nice Job!", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException e1) {
                        CommonDialogs.mySqlErrorMessage(e1);
                    } catch (NumberFormatException e2) {
                        CommonDialogs.numberFormatErrorMessage(e2);
                    } catch (IllegalArgumentException e3) {
                        CommonDialogs.illegalArgumnetExceptionMessage(e3);
                    }
                } else {
                    JOptionPane.showMessageDialog(JPCourse.this, "CID Must Be Zero To Add A Course!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Update fields every time the CID is changed
        //Clear fields if CID is set to 0
        cbAllCID.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    updateFields(con);
                } catch (SQLException e1) {
                    CommonDialogs.mySqlErrorMessage(e1);
                } catch (NumberFormatException e2) {
                    CommonDialogs.numberFormatErrorMessage(e2);
                }
            }
        });

        //Update the name of the school when value changes
        cbSID.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    updateSchoolName(con);
                } catch (SQLException e1) {
                    CommonDialogs.mySqlErrorMessage(e1);
                } catch (NumberFormatException e2) {
                    CommonDialogs.numberFormatErrorMessage(e2);
                }
            }
        });

        //Search the database for courses that match the data in the fields
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    searchAndDisplay(con);
                } catch (SQLException e1) {
                    CommonDialogs.mySqlErrorMessage(e1);
                } catch (NumberFormatException e2) {
                    CommonDialogs.numberFormatErrorMessage(e2);
                }
            }
        });
    }

    //Update the JComboBox with all CID
    public void updatejbAllCID(Connection con) throws SQLException {
        int maxCID = Queries.getMaxCID(con);
        cbAllCID.addItem(maxCID);
        cbAllCID.setSelectedIndex(cbAllCID.getItemCount() - 1);
    }

    //Return the CID
    public int getCID() throws NumberFormatException {
        return (int) cbAllCID.getSelectedItem();
    }

    //Return the SID
    public int getSID() throws NumberFormatException {
        return (int) cbSID.getSelectedItem();
    }

    //Return the name
    public String getName() {
        return tfName.getText().trim();
    }

    //Return the title
    public String getTitle() {
        return tfTitle.getText().trim();
    }

    //Return the department
    public String getDepartment() {
        return tfDepartment.getText().trim();
    }

    //Return the credits
    public int getCredits() throws NumberFormatException {
        return Integer.parseInt(tfCredits.getText().trim());
    }

    //Return the description
    public String getDescription() {
        return taDescription.getText().trim();
    }

    //Return the outcomes
    public String getOutcomes() {
        return taOutcomes.getText().trim();
    }

    //Return the contact email address
    public String getContactEmail() {
        return tfContactEmail.getText().trim();
    }

    //Return the contact name
    public String getContactName() {
        return tfContactName.getText().trim();
    }

    //Return the contact name
    public String getSchoolName() {
        return tfSchoolName.getText().trim();
    }

    //Update a course in the database
    public void updateCourse(Connection con) throws SQLException, NumberFormatException, IllegalArgumentException {
        //Get all the data about the course from the fields
        int intCID = getCID();
        int intSID = getSID();
        String strName = getName();
        String strTitle = getTitle();
        String strDepartment =  getDepartment();
        int intCredits =  getCredits();
        String strDescription =  getDescription();
        String strOutcomes = getOutcomes();
        String strContactEmail = getContactEmail();
        String strContactName = getContactName();

        illegalDataFieldsCheck(intSID, strName, strTitle);;
        Queries.updateCourse(con, intCID, intSID, strName, strTitle, strDepartment, intCredits, strDescription, strOutcomes, strContactEmail, strContactName);
    }

    //Load the data for a course into the fields
    public void updateFields(Connection con) throws SQLException, NumberFormatException {
        //Get the current CID selected
        int intCID = getCID();

        //If the CID != 0, load the data into the fields
        //Otherwise clear all the fields
        if (intCID != 0) {
            //Query for information associated with the selected CID
            ResultSet rs = Queries.getCourse(con, intCID);

            //Update all the fields with the associated data
            if (rs.next()) {
                cbSID.setSelectedItem(rs.getInt(2));;
                tfName.setText(rs.getString(3));
                tfTitle.setText(rs.getString(4));
                tfDepartment.setText(rs.getString(5));
                tfCredits.setText(rs.getString(6));
                taDescription.setText(rs.getString(7));
                taOutcomes.setText(rs.getString(8));
                tfContactEmail.setText(rs.getString(9));
                tfContactName.setText(rs.getString(10));
            } else {
                JOptionPane.showMessageDialog(JPCourse.this, "Course Not Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            clearFields();
        }
    }

    //Add a course to the database!
    public void addCourse(Connection con) throws SQLException, NumberFormatException, IllegalArgumentException {
        //Get the current data from the fields
        int intSID = getSID();
        String strName = getName();
        String strTitle = getTitle();
        String strDepartment =  getDepartment();
        int intCredits =  getCredits();
        String strDescription =  getDescription();
        String strOutcomes=  getOutcomes();
        String strContactEmail =  getContactEmail();
        String strContactName =  getContactName();

        //Add the course to the database!
        //Unless passing an illegal argument
        illegalDataFieldsCheck(intSID, strName, strTitle);
        Queries.addCourse(con, intSID, strName, strTitle, strDepartment, intCredits, strDescription, strOutcomes, strContactEmail, strContactName);

        //Clear all the fields
        cbAllCID.setSelectedIndex(0);
        clearFields();
    }

    //Create a pop up for the result of the search
    public void searchAndDisplay(Connection con) throws SQLException, NumberFormatException {
        //Declare and initialize all the variables
        //Get all the data from the fields and edit format with helper method
        String strCourseName = SearchHelper.searchHelper(getName());
        String strCourseTitle = SearchHelper.searchHelper(getTitle());
        String strCourseDepartment = SearchHelper.searchHelper(getDepartment());
        int intSID = getSID();
        String strSchoolName = SearchHelper.searchHelper(getSchoolName());

        ResultSet rsResults;
        JTable jtbResult;
        int intWidth;

        if (intSID == 0) {
            rsResults = Queries.searchCourse(con, strCourseName, strCourseTitle, strCourseDepartment, strSchoolName);
            jtbResult = Queries.ResultSetToJTable(rsResults);
            jtbResult.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtbResult.getColumnModel().getColumn(1).setPreferredWidth(75);
            jtbResult.getColumnModel().getColumn(2).setPreferredWidth(100);
            jtbResult.getColumnModel().getColumn(3).setPreferredWidth(100);
            jtbResult.getColumnModel().getColumn(4).setPreferredWidth(50);
            jtbResult.getColumnModel().getColumn(5).setPreferredWidth(100);
            jtbResult.getColumnModel().getColumn(6).setPreferredWidth(100);
            jtbResult.getColumnModel().getColumn(7).setPreferredWidth(100);
            jtbResult.getColumnModel().getColumn(8).setPreferredWidth(100);
            jtbResult.getColumnModel().getColumn(9).setPreferredWidth(150);
            jtbResult.getColumnModel().getColumn(10).setPreferredWidth(50);
            intWidth = 975;
        } else {
            rsResults = Queries.searchCourseWithSID(con, strCourseName, strCourseTitle, strCourseDepartment, intSID);
            jtbResult = Queries.ResultSetToJTable(rsResults);
            jtbResult.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtbResult.getColumnModel().getColumn(1).setPreferredWidth(75);
            jtbResult.getColumnModel().getColumn(2).setPreferredWidth(100);
            jtbResult.getColumnModel().getColumn(3).setPreferredWidth(100);
            jtbResult.getColumnModel().getColumn(4).setPreferredWidth(50);
            jtbResult.getColumnModel().getColumn(5).setPreferredWidth(100);
            jtbResult.getColumnModel().getColumn(6).setPreferredWidth(100);
            jtbResult.getColumnModel().getColumn(7).setPreferredWidth(100);
            jtbResult.getColumnModel().getColumn(8).setPreferredWidth(100);
            jtbResult.getColumnModel().getColumn(9).setPreferredWidth(50);
            intWidth = 825;
        }
        new PopUp(new JScrollPane(jtbResult), "Results", intWidth, 300);
    }

    //Update the school name
    public void updateSchoolName(Connection con) throws SQLException {
        int intSID = getSID();
        if (intSID == 0) {
            tfSchoolName.setText("");
            tfSchoolName.setEditable(true);
            tfSchoolName.setBackground(Color.WHITE);
        } else {
            ResultSet rs = Queries.getSchool(con, intSID);
            if (rs.next()) {
                tfSchoolName.setText(rs.getString(3));
                tfSchoolName.setEditable(false);
                tfSchoolName.setBackground(Color.LIGHT_GRAY);
            } else {
                JOptionPane.showMessageDialog(JPCourse.this, "School Not Found With SID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Clear all fields
    public void clearFields() {
        cbSID.setSelectedIndex(0);
        tfName.setText("");
        tfTitle.setText("");
        tfDepartment.setText("");
        tfCredits.setText("");
        taDescription.setText("");
        taOutcomes.setText("");
        tfContactEmail.setText("");
        tfContactName.setText("");
    }

    //Make sure none of the NOT NULL data fields in the database are blank and that SID != 0
    public void illegalDataFieldsCheck(int intSID, String strName, String strTitle) throws IllegalArgumentException{
        String exceptionMessage = "";
        if (strName.equals("")) {
            exceptionMessage += "Name Can't Be Blank \n";
        }

        if (strTitle.equals("")) {
            exceptionMessage += "Title Can't Be Blank \n";
        }

        if (intSID == 0) {
            exceptionMessage += "SID Can't Be 0 \n";
        }

        if (!exceptionMessage.equals("")) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }
}