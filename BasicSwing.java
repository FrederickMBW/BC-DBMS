package com.company;

/**
 * Created by shymacbook on 11/6/17.
 */

// **************** grid bag layout *******************************
// border layout for Jframe
// center should have grid layout
// observer pattern for listeners, built in features
// button.addActionListener(this)

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class BasicSwing extends JFrame{
    //CREATE ALL PIECES FOR ALL VIEWS IN HERE (and rename it in the end)
      //WILL NEED JCOMBOBOX (second for <>=)?, JTEXTFIELD FOR EVERY TABLE COLUMN THAT GETS USED TO SEARCH
      //JPANEL, JTABLE, SCROLLBAR, DEFAULTTABLEMODEL, SEARCH BUTTON, MAIN MENU BUTTON FOR EVERY VIEW
      //NUMBER OF BUTTONS ON EVERY VIEW TO SWITCH TO RELATED VIEWS

    //DONT FORGET ADD AND DELETE BUTTONS
    String[] studentSQLcolumns = {"SID", "nameFirst","NameLast", "Address_street", "address_City","address_State", "address_Zip", "Phone", "Email","Standing", "CGPA", "Demographic", "dob","advisor","grad_Date"};
    String[] devicesSQLcolumns = {"device_id","item_description","status","model_number","price","date_of_purchase"};
    String[] studentColumnsName = {"SID", "First Name","Last Name", "Street", "City","State", "Zip", "Phone Number", "Email","Standing", "CGPA", "Demographic", "Date of Birth","advisor","graduation Date"};
    String[] devicesColumns = {"Device ID","Item type","Status","Model Number","Price","Date of purchase"};
    String[] booksColumns = {"Item ID","Book ID","Title","Status","Item description","Edition","Book type","ISBN","Condition","Price","Date of purchase"};
    String[] booksSQLcolumns = {"item_id","book_id","title","status","item_description","edition","book_type","isbn","book_condition","price","date_of_purchase"};
    String[] genericItemColumns = {"Item id", "Item description","Status","Price","Date of purchase"};
    String[] genericItemSQLcolumns = {"item_id","item_description","status","price","date_of_purchase"};
    String[] checkoutColumns = {"SID","First name","Last name","Item description","Item ID","Date checked out","Date returned"};
    String[] checkoutSQLcolumns = {"sid","namefirst","namelast","item_description","item_id","date_checked_out","date_returned"};

    DefaultTableModel model = new DefaultTableModel();
    JTable jt=new JTable(model);

    JPanel menu = new JPanel(new BorderLayout());
    JPanel test1 = new JPanel(new BorderLayout());
    JPanel test2 = new JPanel();

    JPanel searchStudent = new JPanel();
    JPanel addStudent = new JPanel();
    JPanel editStudentPanel = new JPanel(new BorderLayout());
    JPanel studentSidPanel = new JPanel();
    JPanel fnamePanel = new JPanel();
    JPanel lnamePanel = new JPanel();
    JPanel cityPanel = new JPanel();
    JPanel statePanel = new JPanel();
    JPanel standingPanel = new JPanel();
    JPanel gpaPanel = new JPanel();
    JPanel demoPanel = new JPanel();
    JPanel agePanel = new JPanel();
    JPanel dobPanel = new JPanel();
    JPanel advisorPanel = new JPanel();
    JPanel gradDatePanel = new JPanel();
    JPanel studentStreetPanel = new JPanel();
    JPanel studentZipPanel = new JPanel();
    JPanel studentPhonePanel = new JPanel();
    JPanel studentEmailPanel = new JPanel();
    JPanel studentEnrolledPanel = new JPanel();

    JPanel deviceContainerFrame1 = new JPanel();
    JPanel deviceContainerFrame2 = new JPanel();
    JPanel deviceContainerFrame3 = new JPanel(new BorderLayout());
    JPanel searchDevices = new JPanel(new BorderLayout());
    JPanel addDevices = new JPanel();
    JPanel deviceIDpanel = new JPanel();
    JPanel modelNumberPanel = new JPanel();

    JPanel itemDescriptionPanel = new JPanel();
    JPanel statusPanel = new JPanel();
    JPanel pricePanel = new JPanel();
    JPanel dopPanel = new JPanel();
    JPanel itemIDPanel = new JPanel();

    JPanel searchBooks = new JPanel();
    JPanel addBooks = new JPanel();
    JPanel bookIDpanel = new JPanel();
    JPanel titlePanel = new JPanel();
    JPanel editionPanel = new JPanel();
    JPanel bookTypePanel = new JPanel();
    JPanel isbnPanel = new JPanel();
    JPanel conditionPanel = new JPanel();

    JPanel searchGenericItems = new JPanel();
    JPanel addGenericItems = new JPanel();

    JPanel searchMaintenance = new JPanel();
    JPanel addMaintenance = new JPanel();
    JPanel outDatePanel = new JPanel();
    JPanel inDatePanel = new JPanel();
    JPanel searchCheckout = new JPanel();
    JPanel addCheckout = new JPanel();
    JPanel searchOverdue = new JPanel();




    //ON ALL FRAMES
    JButton returnToMenu = new JButton("Main Menu");
    JButton clearFields = new JButton("Clear fields");
    //MAIN MENU BUTTONS
    JButton searchStudentButton = new JButton("Students(and BC members)");
    JButton searchDevicesButton = new JButton("Devices");
    JButton searchBooksButton = new JButton("Books");
    JButton searchGenericItemsButton = new JButton("General items");
    JButton searchMaintenanceButton = new JButton("Maintenance");
    JButton searchCheckoutButton = new JButton("Item Checkout");
    //STUDENTS TABLE BUTTONS
    JButton addStudentFrame = new JButton("Add or edit a student");
    JButton refreshStudentGetter = new JButton("Refresh drop down");
    JButton studentQueryTrigger = new JButton("Search");
    JButton addStudentButton = new JButton("Add student to database");
    JButton returnToStudent = new JButton("Return");
    JButton getStudentButton = new JButton("Get student information");
    JButton editStudentQuery = new JButton("Update this student");
    //DEVICES TABLE BUTTONS
    JButton addDevicesFrame = new JButton("Add or edit a device");
    JButton devicesQueryTrigger = new JButton("Search");
    JButton addDeviceButton = new JButton("Add device to database");
    JButton returnToDevices = new JButton("Return");
    JButton getDeviceButton = new JButton("Get device information");
    JButton editDeviceQuery = new JButton("Update this device");
    //BOOKS TABLE BUTTONS
    JButton addBooksFrame = new JButton("Add or edit a book");
    JButton bookQueryTrigger = new JButton("Search");
    JButton addBooksButton = new JButton("Add book to database");
    JButton returnToBooks = new JButton("Return");
    JButton getBookButton = new JButton("Get book information");
    JButton editBooksQuery = new JButton("Update this book");
    //GENERIC ITEM BUTTONS
    JButton addGenericItemFrame = new JButton("Add or edit an item");
    JButton genericItemQueryTrigger = new JButton("Search");
    JButton addGenericItemButton = new JButton("Add item to database");
    JButton returnToGenericItems = new JButton("Return");
    JButton getGenericItemButton = new JButton("Get item information");
    JButton editGenericItemQuery = new JButton("Update this item");
    //CHECKOUT BUTTONS
    JButton addCheckoutFrame = new JButton("Add or edit an entry");
    JButton checkoutQueryTrigger = new JButton("Search");
    JButton addCheckoutButton = new JButton("Check out item");
    JButton returnToCheckout = new JButton("Return");
    JButton getCheckoutButton = new JButton("Get checkout information");
    JButton editCheckoutQuery = new JButton("Update this checkout");

    //JButton  = new JButton("");
    JTextField t = new JTextField("Hi",20);
    JTextField field1s8 = new JTextField(8);
    JTextField field2s8 = new JTextField(8);
    JTextField field3s8 = new JTextField(8);
    JTextField field4s3 = new JTextField(3);
    JTextField field5s8 = new JTextField(8);
    JTextField field6s3 = new JTextField(3);
    JTextField field7s3 = new JTextField(3);
    JTextField field8s8 = new JTextField(8);
    JTextField field9s8 = new JTextField(8);
    JTextField field10s8 = new JTextField(8);
    JTextField field11s8 = new JTextField(8);
    JTextField field12s10 = new JTextField(10);
    JTextField field13s7 = new JTextField(7);
    JTextField field14s8 = new JTextField(8);
    JTextField field15s10 = new JTextField(10);
    JTextField field16s3 = new JTextField(3);
    JTextField field17s8 = new JTextField(8);

    //can attach image to jlabel apparently
    JLabel orderByLabel = new JLabel("Order entries by");

    JLabel menuLabel = new JLabel("Please choose the table you want to view.");
    JLabel studentsidEquals = new JLabel("SID = ");
    JLabel studentFNameLike = new JLabel("First name contains: ");
    JLabel studentFNameEqual = new JLabel("First name = ");
    JLabel studentLNameLike = new JLabel("Last name contains: ");
    JLabel studentLNameEquals = new JLabel("Last name =");
    JLabel studentgpacompare = new JLabel("GPA");
    JLabel studentGPAequals = new JLabel("GPA = ");
    JLabel studentCityLike = new JLabel("City = ");
    JLabel studentStateLike = new JLabel("State = ");
    JLabel studentStandingEquals = new JLabel("Standing = ");
    JLabel studentDemoLike = new JLabel("Demographic = ");
    JLabel studentAgeCompare = new JLabel("Age");
    JLabel studentDOB = new JLabel("Birth date = ");
    JLabel studentAdvisorLike = new JLabel("Advisor = ");
    JLabel studentGradDateCompare = new JLabel("Graduation date (YYYY-MM-DD) ");
    JLabel gradDateEquals = new JLabel("Graduation date (YYYY-MM-DD) = ");
    JLabel studentStreetLabel = new JLabel("Street address = ");
    JLabel studentZipLabel = new JLabel("Zip = ");
    JLabel studentPhoneLabel = new JLabel("Phone number = ");
    JLabel studentEmailLabel = new JLabel("Email address = ");
    JLabel currentlyEnrolledLabel = new JLabel("Currently enrolled:");

    JLabel deviceIDlabel = new JLabel("Device ID = ");
    JLabel modelNumberLabel = new JLabel("Model Number = ");

    JLabel itemDescriptionLabel = new JLabel("Type of item = ");
    JLabel statusLabel = new JLabel("Item status = ");
    JLabel priceCompareLabel = new JLabel("Price");
    JLabel priceEqualLabel = new JLabel("Price = ");
    JLabel dopLabel = new JLabel("Date of purchase (YYYY-MM-DD) = ");
    JLabel itemIDlabel = new JLabel("Item ID = ");

    JLabel bookIDlabel = new JLabel("Book ID = ");
    JLabel titleLabel = new JLabel("Title = ");
    JLabel editionLabel = new JLabel("Edition = ");
    JLabel bookTypeLabel = new JLabel("Book type = ");
    JLabel isbnLabel = new JLabel("ISBN = ");
    JLabel conditionLabel = new JLabel("Condition = ");

    JLabel outDateLabel = new JLabel("Date sent out (YYYY-MM-DD) = ");
    JLabel inDateLabel = new JLabel("Date returned (YYYY-MM-DD) = ");
    JLabel hourLabel1 = new JLabel("Time (HH:MM) = ");
    JLabel hourLabel2 = new JLabel("Time (HH:MM) = ");

    JComboBox studentOrderOptions = new JComboBox(studentColumnsName);
    JComboBox devicesOrderOptions = new JComboBox(devicesColumns);
    JComboBox booksOrderOptions = new JComboBox(booksColumns);
    JComboBox genericItemOrderOptions = new JComboBox(genericItemColumns);
    JComboBox checkoutOrderOptions = new JComboBox(checkoutColumns);
    String tempChoices[] = {">","<",">=","<="};
    JComboBox comparators1 = new JComboBox(tempChoices);
    JComboBox comparators2 = new JComboBox(tempChoices);
    String noYes[] = {"yes","no"};
    JComboBox yesNo = new JComboBox(noYes);
    String statusOptions[] = {"Available","Out","Repairing","Missing","All"};
    JComboBox statusChoices = new JComboBox(statusOptions);
    JComboBox<String> studentGetter = new JComboBox();
    //CAN ADD LISTENER TO THIS*****************************************************************************************************************

   JScrollPane js=new JScrollPane(jt);

   public void clearTable(){
       int size = model.getRowCount()-1;
       for (int i = size;i>=0;i--) {
           model.removeRow(i);
       }
       System.out.println("clearTable successful");
   }


    public BasicSwing(){


        super("CS Dept Database");
        setSize(700,123);
        setResizable(true);

        jt.setPreferredScrollableViewportSize(new Dimension(450,100));
        jt.setFillsViewportHeight(true);

        clearFields.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field1s8.setText("");
                field2s8.setText("");
                field3s8.setText("");
                field4s3.setText("");
                field5s8.setText("");
                field6s3.setText("");
                field7s3.setText("");
                field8s8.setText("");
                field9s8.setText("");
                field10s8.setText("");
                field11s8.setText("");
                field12s10.setText("");
                field13s7.setText("");
                field14s8.setText("");
                field15s10.setText("");
                field16s3.setText("");
                comparators1.setSelectedIndex(0);
                comparators2.setSelectedIndex(0);
                statusChoices.setSelectedIndex(0);
                yesNo.setSelectedIndex(0);
            }
        });

        searchStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                addStudent.setVisible(false);
                editStudentPanel.setVisible(false);
                studentFNameEqual.setVisible(false);
                studentLNameEquals.setVisible(false);
                studentGPAequals.setVisible(false);
                studentDOB.setVisible(false);
                gradDateEquals.setVisible(false);

                studentSidPanel.add(studentsidEquals);
                studentSidPanel.add(field1s8);
                fnamePanel.add(studentFNameLike);
                fnamePanel.add(field2s8);
                lnamePanel.add(studentLNameLike);
                lnamePanel.add(field3s8);
                cityPanel.add(studentCityLike);
                cityPanel.add(field5s8);
                statePanel.add(studentStateLike);
                statePanel.add(field6s3);
                standingPanel.add(studentStandingEquals);
                standingPanel.add(field7s3);
                demoPanel.add(studentDemoLike);
                demoPanel.add(field8s8);
                advisorPanel.add(studentAdvisorLike);
                advisorPanel.add(field10s8);
                gradDatePanel.add(studentGradDateCompare);
                gradDatePanel.add(field11s8);
                studentStreetPanel.add(studentStreetLabel);
                studentStreetPanel.add(field12s10);
                studentZipPanel.add(studentZipLabel);
                studentZipPanel.add(field13s7);
                studentPhonePanel.add(studentPhoneLabel);
                studentPhonePanel.add(field14s8);
                studentEmailPanel.add(studentEmailLabel);
                studentEmailPanel.add(field15s10);
                studentEnrolledPanel.add(currentlyEnrolledLabel);
                studentEnrolledPanel.add(yesNo);

                gpaPanel.add(studentgpacompare);
                gpaPanel.add(comparators1);
                gpaPanel.add(field4s3);
                agePanel.add(studentAgeCompare);
                agePanel.add(comparators2);//may need to readd this one and find logistics
                agePanel.add(field9s8);
                searchStudent.add(studentSidPanel);
                searchStudent.add(fnamePanel);
                searchStudent.add(lnamePanel);
                searchStudent.add(cityPanel);
                searchStudent.add(statePanel);
                searchStudent.add(standingPanel);
                searchStudent.add(gpaPanel);
                searchStudent.add(demoPanel);
                searchStudent.add(agePanel);
                searchStudent.add(advisorPanel);
                searchStudent.add(gradDatePanel);
                searchStudent.add(studentEnrolledPanel);
                searchStudent.add(orderByLabel);
                searchStudent.add(studentOrderOptions);
                searchStudent.add(studentQueryTrigger);
                searchStudent.add(clearFields);
                searchStudent.add(addStudentFrame);
                searchStudent.add(returnToMenu);
                clearFields.doClick();
                studentQueryTrigger.doClick();
                searchStudent.add(js);
                setSize(1400,300);
                jt.setPreferredScrollableViewportSize(new Dimension(1200,100));

                studentGradDateCompare.setVisible(true);
                studentAgeCompare.setVisible(true);
                studentgpacompare.setVisible(true);
                comparators1.setVisible(true);
                studentFNameLike.setVisible(true);
                studentLNameLike.setVisible(true);
                add(searchStudent);
                searchStudent.setVisible(true);
            }
        });

        studentQueryTrigger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable();
                String filters[] = {field1s8.getText(),field2s8.getText(),
                        field3s8.getText(),field5s8.getText(),
                        field6s3.getText(),field7s3.getText(),
                        (String)comparators1.getSelectedItem(),field4s3.getText(),
                        field8s8.getText(), (String)comparators2.getSelectedItem(),
                        field9s8.getText(), field10s8.getText(),
                        field11s8.getText(), (String)yesNo.getSelectedItem(),
                        studentSQLcolumns[studentOrderOptions.getSelectedIndex()]};
                SQLStuff.get_student_data(model, filters);
            }
        });

        getStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field1s8.getText(),field2s8.getText(),
                        field3s8.getText(),field12s10.getText(),field5s8.getText(),
                        field6s3.getText(),field13s7.getText(),field14s8.getText(),
                        field15s10.getText(),field7s3.getText(),
                        field4s3.getText(),field8s8.getText(),
                        field9s8.getText(), field10s8.getText(),
                        (String)yesNo.getSelectedItem(), field11s8.getText()};
                String newFields[] = SQLStuff.get_single_student_data(fields);
                field1s8.setText(newFields[0]);
                field2s8.setText(newFields[1]);
                field3s8.setText(newFields[2]);
                field12s10.setText(newFields[3]);
                field5s8.setText(newFields[4]);
                field6s3.setText(newFields[5]);
                field13s7.setText(newFields[6]);
                field14s8.setText(newFields[7]);
                field15s10.setText(newFields[8]);
                field7s3.setText(newFields[9]);
                field4s3.setText(newFields[10]);
                field8s8.setText(newFields[11]);
                field9s8.setText(newFields[12]);
                field10s8.setText(newFields[13]);
                if (newFields[14].equals("yes"))
                    yesNo.setSelectedIndex(0);
                else
                    yesNo.setSelectedIndex(1);
                field11s8.setText(newFields[15]);
            }
        });

        editStudentQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field1s8.getText(),field2s8.getText(),
                        field3s8.getText(),field12s10.getText(),field5s8.getText(),
                        field6s3.getText(),field13s7.getText(),field14s8.getText(),
                        field15s10.getText(),field7s3.getText(),
                        field4s3.getText(),field8s8.getText(),
                        field9s8.getText(), field10s8.getText(),
                        (String)yesNo.getSelectedItem(), field11s8.getText()};
                SQLStuff.update_student(fields);
                //MESSAGE SHOWING SUCCESS/FAILURE
            }
        });

        studentGetter.addItem("option1");
        studentGetter.addItem("option2");
        studentGetter.addItem("option3");
        studentGetter.addItem("option4");

        studentGetter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (studentGetter.isValid()) {
                    clearFields.doClick();
                    String temp = (String) studentGetter.getSelectedItem();
                    System.out.println(temp);
                    temp = temp.substring(0, 9);
                    field1s8.setText(temp);
                    getStudentButton.doClick();
                }
            }
        });

        refreshStudentGetter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field1s8.getText(),field2s8.getText(),
                        field3s8.getText(),field12s10.getText(),field5s8.getText(),
                        field6s3.getText(),field13s7.getText(),field14s8.getText(),
                        field15s10.getText(),field7s3.getText(),
                        field4s3.getText(),field8s8.getText(),
                        field9s8.getText(), field10s8.getText(),
                        field11s8.getText(),(String)yesNo.getSelectedItem()};
                SQLStuff.updateStudentGetter(studentGetter,fields);
            }
        });

        addStudentFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent.setVisible(false);
                setSize(1400,150);


                //INCLUDE JCOMBOBOX BEFORE EVERYTHING ELSE FOR EXISTING VALUES
                //ALSO WANT A REFRESH DROPDOWN BUTTON FOR NARROWING IT DOWN
                addStudent.add(studentGetter);
                addStudent.add(refreshStudentGetter);
                addStudent.add(studentSidPanel);
                studentFNameLike.setVisible(false);
                fnamePanel.add(studentFNameEqual);
                fnamePanel.add(field2s8);
                addStudent.add(fnamePanel);
                studentLNameLike.setVisible(false);
                lnamePanel.add(studentLNameEquals);
                lnamePanel.add(field3s8);
                addStudent.add(lnamePanel);
                addStudent.add(studentStreetPanel);
                addStudent.add(cityPanel);
                addStudent.add(statePanel);
                addStudent.add(studentZipPanel);
                addStudent.add(studentPhonePanel);
                addStudent.add(studentEmailPanel);
                addStudent.add(standingPanel);
                studentgpacompare.setVisible(false);
                comparators1.setVisible(false);
                gpaPanel.add(studentGPAequals);
                gpaPanel.add(field4s3);
                addStudent.add(gpaPanel);
                addStudent.add(demoPanel);
                studentAgeCompare.setVisible(false);
                dobPanel.add(studentDOB);
                dobPanel.add(field9s8);
                addStudent.add(dobPanel);
                addStudent.add(advisorPanel);
                addStudent.add(studentEnrolledPanel);
                studentGradDateCompare.setVisible(false);
                gradDatePanel.add(gradDateEquals);
                gradDatePanel.add(field11s8);
                addStudent.add(gradDatePanel);
                clearFields.doClick();
                refreshStudentGetter.doClick();

                gradDateEquals.setVisible(true);
                studentDOB.setVisible(true);
                studentGPAequals.setVisible(true);
                studentFNameEqual.setVisible(true);
                studentLNameEquals.setVisible(true);
                addStudent.add(addStudentButton);
                addStudent.add(getStudentButton);
                addStudent.add(editStudentQuery);
                addStudent.add(clearFields);
                addStudent.add(returnToStudent);
                add(addStudent);
                addStudent.setVisible(true);
            }
        });

        returnToStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudentButton.doClick();
            }
        });

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field1s8.getText(),field2s8.getText(),
                        field3s8.getText(),field12s10.getText(),field5s8.getText(),
                        field6s3.getText(),field13s7.getText(),field14s8.getText(),
                        field15s10.getText(),field7s3.getText(),
                        field4s3.getText(),field8s8.getText(),
                        field9s8.getText(), field10s8.getText()};
                SQLStuff.add_student(fields);
                //GOOD PLACE FOR THE POPUP TO SAY STUDENT WAS ADDED OR NOT.
            }
        });

        searchDevicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                addDevices.setVisible(false);

                deviceIDpanel.add(deviceIDlabel);
                deviceIDpanel.add(field4s3);
                itemDescriptionPanel.add(itemDescriptionLabel);
                itemDescriptionPanel.add(field17s8);
                statusPanel.add(statusLabel);
                statusPanel.add(statusChoices);
                modelNumberPanel.add(modelNumberLabel);
                modelNumberPanel.add(field3s8);
                pricePanel.add(priceCompareLabel);
                priceCompareLabel.setVisible(true);
                pricePanel.add(comparators1);
                comparators1.setVisible(true);
                pricePanel.add(field16s3);
                dopPanel.add(dopLabel);
                dopPanel.add(field1s8);
                itemIDPanel.add(itemIDlabel);
                itemIDPanel.add(field6s3);
                //deviceContainerFrame1
                deviceContainerFrame1.add(itemIDPanel);
                deviceContainerFrame1.add(deviceIDpanel);
                deviceContainerFrame1.add(itemDescriptionPanel);
                deviceContainerFrame1.add(statusPanel);
                deviceContainerFrame1.add(modelNumberPanel);
                deviceContainerFrame1.add(pricePanel);
                deviceContainerFrame1.add(dopPanel);
                clearFields.doClick();
                devicesQueryTrigger.doClick();
                jt.setPreferredScrollableViewportSize(new Dimension(600,100));
                deviceContainerFrame1.add(orderByLabel);
                deviceContainerFrame1.add(devicesOrderOptions);
                deviceContainerFrame2.add(devicesQueryTrigger);
                deviceContainerFrame2.add(addDevicesFrame);
                deviceContainerFrame2.add(returnToMenu);
                deviceContainerFrame3.add(deviceContainerFrame2,BorderLayout.NORTH);
                deviceContainerFrame3.add(js,BorderLayout.PAGE_END);
                searchDevices.add(deviceContainerFrame1, BorderLayout.CENTER);
                searchDevices.add(deviceContainerFrame3,BorderLayout.SOUTH);
                //searchDevices.add(js,BorderLayout.PAGE_END);
                setSize(800,300);
                priceEqualLabel.setVisible(false);


                add(searchDevices);
                searchDevices.setVisible(true);
            }
        });

        devicesQueryTrigger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable();
                String filters[] = {field6s3.getText(),field4s3.getText(),field17s8.getText(),
                        (String)statusChoices.getSelectedItem(),field3s8.getText(),
                        (String)comparators1.getSelectedItem(),field16s3.getText(),
                        field1s8.getText(),
                        devicesSQLcolumns[devicesOrderOptions.getSelectedIndex()]};
                SQLStuff.get_device_data(model, filters);
            }
        });

        getDeviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field6s3.getText(),field4s3.getText(),field17s8.getText(),
                        (String)statusChoices.getSelectedItem(),field3s8.getText(),
                        field16s3.getText(),field1s8.getText()};
                String newFields[] = SQLStuff.get_single_device_data(fields);
                field6s3.setText(newFields[0]);
                field4s3.setText(newFields[1]);
                field17s8.setText(newFields[2]);
                statusChoices.setSelectedItem(newFields[3]);
                field3s8.setText(newFields[4]);
                field16s3.setText(newFields[5]);
                field1s8.setText(newFields[6]);
            }
        });

        editDeviceQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field6s3.getText(),field4s3.getText(),field17s8.getText(),
                        (String)statusChoices.getSelectedItem(),field3s8.getText(),
                        field16s3.getText(),field1s8.getText()};
                SQLStuff.update_device(fields);
            }
        });

        addDevicesFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchDevices.setVisible(false);
                setSize(700,150);
                priceCompareLabel.setVisible(false);
                comparators1.setVisible(false);

                pricePanel.add(priceEqualLabel);
                pricePanel.add(field16s3);
                priceEqualLabel.setVisible(true);

                addDevices.add(itemIDPanel);
                addDevices.add(deviceIDpanel);
                addDevices.add(itemDescriptionPanel);
                addDevices.add(statusPanel);
                addDevices.add(modelNumberPanel);
                addDevices.add(pricePanel);
                addDevices.add(dopPanel);
                clearFields.doClick();

                addDevices.add(addDeviceButton);
                addDevices.add(getDeviceButton);
                addDevices.add(editDeviceQuery);
                addDevices.add(returnToDevices);
                add(addDevices);
                addDevices.setVisible(true);
            }
        });

        returnToDevices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchDevicesButton.doClick();
            }
        });

        addDeviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field6s3.getText(),field4s3.getText(),field17s8.getText(),
                        (String)statusChoices.getSelectedItem(),field3s8.getText(),
                        field16s3.getText(),field1s8.getText()};
                SQLStuff.add_device(fields);
            }
        });
//CURRENT SETUP IS MAKING GENERIC ITEMS STILL EVEN ON QUERY FAILURE. may have fixed that. needs testing.
        searchBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                addBooks.setVisible(false);

                itemIDPanel.add(itemIDlabel);
                itemIDPanel.add(field6s3);
                bookIDpanel.add(bookIDlabel);
                bookIDpanel.add(field4s3);
                titlePanel.add(titleLabel);
                titlePanel.add(field9s8);
                statusPanel.add(statusLabel);
                statusPanel.add(statusChoices);
                itemDescriptionPanel.add(itemDescriptionLabel);
                itemDescriptionPanel.add(field17s8);
                editionPanel.add(editionLabel);
                editionPanel.add(field7s3);
                bookTypePanel.add(bookTypeLabel);
                bookTypePanel.add(field8s8);
                isbnPanel.add(isbnLabel);
                isbnPanel.add(field3s8);
                conditionPanel.add(conditionLabel);
                conditionPanel.add(field5s8);
                pricePanel.add(priceCompareLabel);
                pricePanel.add(comparators1);
                pricePanel.add(field16s3);
                dopPanel.add(dopLabel);
                dopPanel.add(field1s8);
                comparators1.setVisible(true);



                searchBooks.add(itemIDPanel);
                searchBooks.add(bookIDpanel);
                searchBooks.add(titlePanel);
                searchBooks.add(statusPanel);
                searchBooks.add(itemDescriptionPanel);
                searchBooks.add(editionPanel);
                searchBooks.add(bookTypePanel);
                searchBooks.add(isbnPanel);
                searchBooks.add(conditionPanel);
                searchBooks.add(pricePanel);
                searchBooks.add(dopPanel);
                clearFields.doClick();
                bookQueryTrigger.doClick();
                jt.setPreferredScrollableViewportSize(new Dimension(1000,100));
                searchBooks.add(orderByLabel);
                searchBooks.add(booksOrderOptions);
                searchBooks.add(bookQueryTrigger);
                searchBooks.add(addBooksFrame);
                searchBooks.add(returnToMenu);
                searchBooks.add(js);
                setSize(1030,300);
                priceEqualLabel.setVisible(false);
                priceCompareLabel.setVisible(true);

                add(searchBooks);
                searchBooks.setVisible(true);
            }
        });

        bookQueryTrigger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable();
                String filters[] = {field6s3.getText(),field4s3.getText(),field9s8.getText(),
                        (String)statusChoices.getSelectedItem(),field17s8.getText(),
                        field7s3.getText(),field8s8.getText(),field3s8.getText(),field5s8.getText(),
                        (String)comparators1.getSelectedItem(),field16s3.getText(),
                        field1s8.getText(),
                        booksSQLcolumns[booksOrderOptions.getSelectedIndex()]};
                SQLStuff.get_book_data(model, filters);
            }
        });

        getBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field6s3.getText(),field4s3.getText(),field9s8.getText(),
                        (String)statusChoices.getSelectedItem(),field17s8.getText(),
                        field7s3.getText(),field8s8.getText(),field3s8.getText(),field5s8.getText(),
                        field16s3.getText(),field1s8.getText()};
                String newFields[] = SQLStuff.get_single_book_data(fields);
                field6s3.setText(newFields[0]);
                field4s3.setText(newFields[1]);
                field9s8.setText(newFields[2]);
                statusChoices.setSelectedItem(newFields[3]);
                field17s8.setText(newFields[4]);
                field7s3.setText(newFields[5]);
                field8s8.setText(newFields[6]);
                field3s8.setText(newFields[7]);
                field5s8.setText(newFields[8]);
                field16s3.setText(newFields[9]);
                field1s8.setText(newFields[10]);
            }
        });

        editBooksQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field6s3.getText(),field4s3.getText(),field9s8.getText(),
                        (String)statusChoices.getSelectedItem(),field17s8.getText(),
                        field7s3.getText(),field8s8.getText(),field3s8.getText(),field5s8.getText(),
                        field16s3.getText(),field1s8.getText()};
                SQLStuff.update_book(fields);
            }
        });

        addBooksFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBooks.setVisible(false);
                setSize(1200,150);
                priceCompareLabel.setVisible(false);
                comparators1.setVisible(false);

                pricePanel.add(priceEqualLabel);
                pricePanel.add(field16s3);
                priceEqualLabel.setVisible(true);

                addBooks.add(itemIDPanel);
                addBooks.add(bookIDpanel);
                addBooks.add(titlePanel);
                addBooks.add(statusPanel);
                addBooks.add(itemDescriptionPanel);
                addBooks.add(editionPanel);
                addBooks.add(bookTypePanel);
                addBooks.add(isbnPanel);
                addBooks.add(conditionPanel);
                addBooks.add(pricePanel);
                addBooks.add(dopPanel);
                clearFields.doClick();

                addBooks.add(addBooksButton);
                addBooks.add(getBookButton);
                addBooks.add(editBooksQuery);
                addBooks.add(returnToBooks);
                add(addBooks);
                addBooks.setVisible(true);
            }
        });

        returnToBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBooksButton.doClick();
            }
        });

        addBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field6s3.getText(),field4s3.getText(),field9s8.getText(),
                        (String)statusChoices.getSelectedItem(),field17s8.getText(),
                        field7s3.getText(),field8s8.getText(),field3s8.getText(),field5s8.getText(),
                        field16s3.getText(),field1s8.getText()};
                SQLStuff.add_book(fields);
            }
        });

        searchGenericItemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                addGenericItems.setVisible(false);
                setSize(700,300);

                itemIDPanel.add(itemIDlabel);
                itemIDPanel.add(field6s3);
                itemDescriptionPanel.add(itemDescriptionLabel);
                itemDescriptionPanel.add(field17s8);
                statusPanel.add(statusLabel);
                statusPanel.add(statusChoices);
                pricePanel.add(priceCompareLabel);
                pricePanel.add(comparators1);
                pricePanel.add(field16s3);
                dopPanel.add(dopLabel);
                dopPanel.add(field1s8);
                comparators1.setVisible(true);

                searchGenericItems.add(itemIDPanel);
                searchGenericItems.add(itemDescriptionPanel);
                searchGenericItems.add(statusPanel);
                searchGenericItems.add(pricePanel);
                searchGenericItems.add(dopPanel);
                clearFields.doClick();
                genericItemQueryTrigger.doClick();
                jt.setPreferredScrollableViewportSize(new Dimension(400,100));
                searchGenericItems.add(orderByLabel);
                searchGenericItems.add(genericItemOrderOptions);
                searchGenericItems.add(genericItemQueryTrigger);
                searchGenericItems.add(addGenericItemFrame);
                searchGenericItems.add(returnToMenu);
                searchGenericItems.add(js);
                setSize(700,300);
                priceEqualLabel.setVisible(false);
                priceCompareLabel.setVisible(true);

                add(searchGenericItems);
                searchGenericItems.setVisible(true);
            }
        });

        genericItemQueryTrigger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable();
                String filters[] = {field6s3.getText(),field17s8.getText(),
                        (String)statusChoices.getSelectedItem(),
                        (String)comparators1.getSelectedItem(),field16s3.getText(),
                        field1s8.getText(),//dont forget next line next time
                        genericItemSQLcolumns[genericItemOrderOptions.getSelectedIndex()]};
                SQLStuff.get_generic_item_data(model, filters);
            }
        });

        getGenericItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field6s3.getText(),field17s8.getText(),
                        (String)statusChoices.getSelectedItem(),
                        field16s3.getText(),field1s8.getText()};
                String newFields[] = SQLStuff.get_single_generic_item_data(fields);
                field6s3.setText(newFields[0]);
                field17s8.setText(newFields[1]);
                statusChoices.setSelectedItem(newFields[2]);
                field16s3.setText(newFields[3]);
                field1s8.setText(newFields[4]);
            }
        });

        editGenericItemQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field6s3.getText(),field17s8.getText(),
                        (String)statusChoices.getSelectedItem(),
                        field16s3.getText(),field1s8.getText()};
                SQLStuff.update_generic_item(fields);
            }
        });

        addGenericItemFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchGenericItems.setVisible(false);
                setSize(700,150);
                priceCompareLabel.setVisible(false);
                comparators1.setVisible(false);

                pricePanel.add(priceEqualLabel);
                pricePanel.add(field16s3);
                priceEqualLabel.setVisible(true);

                addGenericItems.add(itemIDPanel);
                addGenericItems.add(itemDescriptionPanel);
                addGenericItems.add(statusPanel);
                addGenericItems.add(pricePanel);
                addGenericItems.add(dopPanel);
                clearFields.doClick();

                addGenericItems.add(addGenericItemButton);
                addGenericItems.add(getGenericItemButton);
                addGenericItems.add(editGenericItemQuery);
                addGenericItems.add(returnToGenericItems);
                add(addGenericItems);
                addGenericItems.setVisible(true);
            }
        });

        returnToGenericItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchGenericItemsButton.doClick();
            }
        });

        addGenericItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field6s3.getText(),field17s8.getText(),
                        (String)statusChoices.getSelectedItem(),
                        field16s3.getText(),field1s8.getText()};
                SQLStuff.add_generic_item(fields);
            }
        });

        searchCheckoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                addCheckout.setVisible(false);
                setSize(700,300);

                studentSidPanel.add(studentsidEquals);
                studentSidPanel.add(field1s8);
                fnamePanel.add(studentFNameLike);
                fnamePanel.add(field2s8);
                lnamePanel.add(studentLNameLike);
                lnamePanel.add(field3s8);
                itemDescriptionPanel.add(itemDescriptionLabel);
                itemDescriptionPanel.add(field17s8);
                itemIDPanel.add(itemIDlabel);
                itemIDPanel.add(field6s3);
                outDatePanel.add(outDateLabel);
                outDatePanel.add(field5s8);
                outDatePanel.add(hourLabel1);
                outDatePanel.add(field8s8);
                inDatePanel.add(inDateLabel);
                inDatePanel.add(field9s8);
                inDatePanel.add(hourLabel2);
                inDatePanel.add(field10s8);

                searchCheckout.add(studentSidPanel);
                searchCheckout.add(fnamePanel);
                searchCheckout.add(lnamePanel);
                searchCheckout.add(itemDescriptionPanel);
                searchCheckout.add(itemIDPanel);
                searchCheckout.add(outDatePanel);
                searchCheckout.add(inDatePanel);
                clearFields.doClick();
                checkoutQueryTrigger.doClick();
                jt.setPreferredScrollableViewportSize(new Dimension(800,100));
                searchCheckout.add(orderByLabel);
                searchCheckout.add(checkoutOrderOptions);
                searchCheckout.add(checkoutQueryTrigger);
                searchCheckout.add(addCheckoutFrame);
                searchCheckout.add(returnToMenu);
                searchCheckout.add(js);
                setSize(850,350);

                add(searchCheckout);
                searchCheckout.setVisible(true);
            }
        });

        checkoutQueryTrigger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable();
                String filters[] = {field1s8.getText(),field2s8.getText(),
                        field3s8.getText(),field17s8.getText(),field6s3.getText(),
                        field5s8.getText(),field8s8.getText(),field9s8.getText(),//dont forget next line next time
                        field10s8.getText(),checkoutSQLcolumns[checkoutOrderOptions.getSelectedIndex()]};
                SQLStuff.get_checkout_data(model, filters);
            }
        });

        getCheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field1s8.getText(),field2s8.getText(),
                        field3s8.getText(),field17s8.getText(),field6s3.getText(),
                        field5s8.getText(),field8s8.getText(),field9s8.getText(),//dont forget next line next time
                        field10s8.getText()};
                String newFields[] = SQLStuff.get_single_checkout_data(fields);
                field1s8.setText(newFields[0]);
                field2s8.setText(newFields[1]);
                field3s8.setText(newFields[3]);
                field17s8.setText(newFields[4]);
                field6s3.setText(newFields[5]);
                field5s8.setText(newFields[6]);
                field8s8.setText(newFields[7]);
                field9s8.setText(newFields[8]);
                field10s8.setText(newFields[9]);
            }
        });

        editCheckoutQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field1s8.getText(),field2s8.getText(),
                        field3s8.getText(),field17s8.getText(),field6s3.getText(),
                        field5s8.getText(),field8s8.getText(),field9s8.getText(),//dont forget next line next time
                        field10s8.getText()};
                SQLStuff.update_checkout(fields);
            }
        });

        addCheckoutFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCheckout.setVisible(false);
                setSize(700,150);

                pricePanel.add(priceEqualLabel);
                pricePanel.add(field16s3);
                priceEqualLabel.setVisible(true);

                searchCheckout.add(studentSidPanel);
                searchCheckout.add(fnamePanel);
                searchCheckout.add(lnamePanel);
                searchCheckout.add(itemDescriptionPanel);
                searchCheckout.add(itemIDPanel);
                searchCheckout.add(outDatePanel);
                searchCheckout.add(inDatePanel);
                clearFields.doClick();

                addCheckout.add(addCheckoutButton);
                addCheckout.add(getCheckoutButton);
                addCheckout.add(editCheckoutQuery);
                addCheckout.add(returnToCheckout);
                add(addCheckout);
                addCheckout.setVisible(true);
            }
        });

        returnToCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCheckoutButton.doClick();
            }
        });

        addCheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fields[] = {field1s8.getText(),field2s8.getText(),
                        field3s8.getText(),field17s8.getText(),field6s3.getText(),
                        field5s8.getText(),field8s8.getText(),field9s8.getText(),//dont forget next line next time
                        field10s8.getText()};
                SQLStuff.add_checkout(fields);
            }
        });

        searchMaintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                searchMaintenance.add(returnToMenu);
                setSize(700,300);
                //use 1s8 for description, rest same as checkout

                add(searchMaintenance);
                searchMaintenance.setVisible(true);
            }
        });

        returnToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent.setVisible(false);
                searchDevices.setVisible(false);
                searchBooks.setVisible(false);
                searchGenericItems.setVisible(false);
                searchCheckout.setVisible(false);
                searchMaintenance.setVisible(false);
                setSize(700, 123);
//                System.out.println("test1");
//                System.out.println("test2");
//                model.setColumnCount(0);
//
//                test2.add(searchStudentButton);
//                test2.add(searchDevicesButton);
//                test2.add(searchBooksButton);
//                test2.add(searchCheckoutButton);
//                test2.add(searchMaintenanceButton);
//                menu.add(test2, BorderLayout.PAGE_END);
//                add(menu);
                menu.setVisible(true);
            }
        });

        //searchStudent.add(returnToMenu);
        //searchDevices.add(returnToMenu);
        //searchBooks.add(returnToMenu);
        //searchCheckout.add(returnToMenu);
        //searchMaintenance.add(returnToMenu);


        test1.add(menuLabel, BorderLayout.CENTER);
        test2.add(searchStudentButton);
        test2.add(searchDevicesButton);
        test2.add(searchBooksButton);
        test2.add(searchGenericItemsButton);
        test2.add(searchCheckoutButton);
        test2.add(searchMaintenanceButton);
        menu.add(test1,BorderLayout.PAGE_START);
        menu.add(test2, BorderLayout.CENTER);




        add(menu);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
}
