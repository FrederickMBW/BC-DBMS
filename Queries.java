package com.company;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Queries {
    //get the SID of every school with matching information
    public static ResultSet searchSchool(Connection con, String OID, String name, String address, String city, String state, String zipCode) throws SQLException {
        CallableStatement cst = con.prepareCall("{call searchSchool(?,?,?,?,?,?)}");
        cst.setString(1, OID);
        cst.setString(2, name);
        cst.setString(3, address);
        cst.setString(4, city);
        cst.setString(5, state);
        cst.setString(6, zipCode);
        return cst.executeQuery();
    }

    //get the SID of the school with a matching SID
    public static ResultSet getSchool(Connection con, int SID) throws SQLException {
        CallableStatement cst = con.prepareCall("{call getSchool(?)}");
        cst.setInt(1, SID);
        return cst.executeQuery();
    }

    //Add a school to the database
    public static void addSchool(Connection con, String otherID, String schoolName, String address, String city, String state, String zipCode) throws SQLException {
        CallableStatement cst = con.prepareCall("{call AddSchool(?,?,?,?,?,?)}");
        cst.setString(1, otherID);
        cst.setString(2, schoolName);
        cst.setString(3, address);
        cst.setString(4, city);
        cst.setString(5, state);
        cst.setString(6, zipCode);
        cst.executeQuery();
    }

    //Update a school in the database
    public static void updateSchool(Connection con, int SID, String otherID, String schoolName, String address, String city, String state, String zipCode) throws SQLException {
        CallableStatement cst = con.prepareCall("{call updateSchool(?,?,?,?,?,?,?)}");
        cst.setInt(1, SID);
        cst.setString(2, otherID);
        cst.setString(3, schoolName);
        cst.setString(4, address);
        cst.setString(5, city);
        cst.setString(6, state);
        cst.setString(7, zipCode);
        cst.executeQuery();
    }

    //Get the SID of every school
    public static ResultSet getEverySID(Connection con) throws SQLException {
        java.sql.Statement stmt = con.createStatement();
        return stmt.executeQuery("SELECT SID FROM SCHOOL");
    }

    //Get the Max SID of every school
    public static int getMaxSID(Connection con) throws SQLException {
        java.sql.Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(SID) FROM SCHOOL");
        rs.next();
        return rs.getInt(1);
    }

    //Update a course in the database
    public static void updateCourse(Connection con, int CID, int SID, String name, String title, String department, int credits, String description, String outcomes, String contactEmail, String contactName) throws SQLException {
        CallableStatement cst = con.prepareCall("{call updateCourse(?,?,?,?,?,?,?,?,?,?)}");
        cst.setInt(1, CID);
        cst.setInt(2, SID);
        cst.setString(3, name);
        cst.setString(4, title);
        cst.setString(5, department);
        cst.setInt(6, credits);
        cst.setString(7, description);
        cst.setString(8, outcomes);
        cst.setString(9, contactEmail);
        cst.setString(10, contactName);
        cst.executeQuery();
    }

    //Get the information for a course when given the CID
    public static ResultSet getCourse(Connection con, int CID) throws SQLException {
        CallableStatement cst = con.prepareCall("{call getCourse(?)}");
        cst.setInt(1, CID);
        return cst.executeQuery();
    }

    //get Course number for every course with a matching information not using the SID
    public static ResultSet searchCourse(Connection con, String courseName, String courseTitle, String courseDepartment, String schoolName) throws SQLException {
        CallableStatement cst = con.prepareCall("{call searchCourse(?,?,?,?)}");
        cst.setString(1, courseName);
        cst.setString(2, courseTitle);
        cst.setString(3, courseDepartment);
        cst.setString(4, schoolName);
        return cst.executeQuery();
    }

    //get Course number for every course with a matching information using the SID
    public static ResultSet searchCourseWithSID (Connection con, String courseName, String title, String department, int SID) throws SQLException {
        CallableStatement cst = con.prepareCall("{call searchCourseWithSID(?,?,?,?)}");
        cst.setString(1, courseName);
        cst.setString(2,  title);
        cst.setString(3,  department);
        cst.setInt(4,  SID);
        return cst.executeQuery();
    }

    //Get the CID of every course
    public static ResultSet getEveryCID(Connection con) throws SQLException {
        java.sql.Statement stmt = con.createStatement();
        return stmt.executeQuery("SELECT CID FROM COURSE");
    }

    //Get the Max CID of every course
    public static int getMaxCID(Connection con) throws SQLException {
        java.sql.Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(CID) FROM COURSE");
        rs.next();
        return rs.getInt(1);
    }

    //Get every course CID of every course no offered at Bellevue College
    public static ResultSet getAllNonBCCID(Connection con) throws SQLException {
        java.sql.Statement stmt = con.createStatement();
        return stmt.executeQuery("SELECT CID FROM COURSE WHERE SID <> 1");
    }

    //Get every course CID offered at Bellevue College
    public static ResultSet getAllBCCID(Connection con) throws SQLException  {
        java.sql.Statement stmt = con.createStatement();
        return stmt.executeQuery("SELECT CID FROM COURSE WHERE SID = 1");
    }

    //Get the name of the course and the school's name
    public static ResultSet getSchoolNameWithCID(Connection con, int CID) throws SQLException {
        CallableStatement cst = con.prepareCall("{call getSchoolNameWithCID(?)}");
        cst.setInt(1, CID);
        return cst.executeQuery();
    }

    //Add a course to the database
    public static void addCourse(Connection con, int SID, String courseName, String courseTitle, String courseDepartment, int credits, String courseDescription, String courseOutcomes, String contactEmail, String contactName) throws SQLException {
        CallableStatement cst = con.prepareCall("{call AddCourse(?,?,?,?,?,?,?,?,?)}");
        cst.setInt(1, SID);
        cst.setString(2, courseName);
        cst.setString(3, courseTitle);
        cst.setString(4, courseDepartment);
        cst.setInt(5, credits);
        cst.setString(6, courseDescription);
        cst.setString(7, courseOutcomes);
        cst.setString(8, contactEmail);
        cst.setString(9, contactName);
        cst.executeQuery();
    }

    //Search for equivalent courses when given the course names, titles, departments, and school names
    public static ResultSet searchEquivalent(Connection con, String name1, String name2, String title1, String title2, String department1, String department2, String schoolName1, String schoolName2) throws SQLException {
        CallableStatement cst = con.prepareCall("{call searchEquivalent(?,?,?,?,?,?,?,?)}");
        cst.setString(1, name1);
        cst.setString(2, name2);
        cst.setString(3, title1);
        cst.setString(4, title2);
        cst.setString(5, department1);
        cst.setString(6, department2);
        cst.setString(7, schoolName1);
        cst.setString(8, schoolName2);
        return cst.executeQuery();
    }

    //Get Equivalent course information
    public static ResultSet getEquivalentInfo (Connection con, int CID1, int CID2) throws SQLException {
        CallableStatement cst = con.prepareCall("{call getEquivalentInfo(?,?)}");
        cst.setInt(1, CID1);
        cst.setInt(2, CID2);
        return cst.executeQuery();
    }

    //Get set of equivalent and non-equivalent courses when given BC course name, other course name, and other school name
    public static ResultSet updateCourseEquivalent(Connection con, int CID1, int CID2, boolean isEquiv, String comment, String SID) throws SQLException {
        CallableStatement cst = con.prepareCall("{call UpdateEQUIVALENT(?,?,?,?,?)}");
        cst.setInt(1, CID1);
        cst.setInt(2, CID2);
        cst.setBoolean(3, isEquiv);
        cst.setString(4, comment);
        cst.setString(5, SID);
        return cst.executeQuery();
    }

    //Add an equivalent course
    public static void addEquivalentCourse(Connection con, int bellevueCollegeCID, int otherCID, boolean isEquivalent, String comment, String SID) throws SQLException {
        CallableStatement cst = con.prepareCall("{call AddEquivalentCourse(?,?,?,?,?)}");
        cst.setInt(1, bellevueCollegeCID);
        cst.setInt(2, otherCID);
        cst.setBoolean(3, isEquivalent);
        cst.setString(4, comment);
        cst.setString(5, SID);
        cst.executeQuery();
    }

    //Convert a result set to a JTable
    public static JTable ResultSetToJTable(ResultSet rs) throws SQLException {
        return new JTable(buildTableModel(rs));
    }

    //simple code to populate JTable from ResultSet
    //https://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        java.sql.ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }

    //Return Every SID in a sorted array
    //Adds 0 to the front of the array
    public static Integer[] getArrayAllSID(Connection con) throws SQLException {
        //Get a result set with every SID
        ResultSet rsAllSID = Queries.getEverySID(con);

        //Count the number of rows in the result set
        int rows = Queries.getRowCount(rsAllSID);

        //Create an array to store all the SIDs and zero
        Integer[] result = new Integer[rows + 1];

        //Set index zero to zero
        result[0] = 0;

        //Add all the SIDs to the array
        for (int i = 1; i <= rows; i++) {
            rsAllSID.next();
            result[i] = rsAllSID.getInt(1);
        }

        //Sort the array
        Arrays.sort(result);

        return result;
    }

    //Return every CID in a sorted array
    //Adds 0 to the front of the array
    public static Integer[] getArrayAllCID(Connection con) throws SQLException {
        //Query to get every CID
        ResultSet rsAllCID = Queries.getEveryCID(con);

        //Count the number of CIDs
        int rows = Queries.getRowCount(rsAllCID);

        //Create array to store all CIDs and 0
        Integer[] result = new Integer[rows + 1];

        //Set the first index to zero
        result[0] = 0;

        //Fill every other index with the CID numbers
        for (int i = 1; i <= rows; i++) {
            rsAllCID.next();
            result[i] = rsAllCID.getInt(1);
        }

        //Sort the array
        Arrays.sort(result);

        return result;
    }

    //Return every CID for courses offered at Bellevue College in a sorted array
    //Adds 0 to the front of the array
    public static Integer[] getArrayAllBCCID(Connection con) throws SQLException {
        //Query to get every CID offered at BC
        ResultSet rsAllBCCID = Queries.getAllBCCID(con);

        //Count the number of CIDs
        int rows = Queries.getRowCount(rsAllBCCID);

        //Create array to store all CIDs and 0
        Integer[] result = new Integer[rows + 1];

        //Set the first index to zero
        result[0] = 0;

        //Fill every other index with the CID numbers
        for (int i = 1; i <= rows; i++) {
            rsAllBCCID.next();
            result[i] = rsAllBCCID.getInt(1);
        }

        //Sort the array
        Arrays.sort(result);

        return result;
    }

    //Return every CID for courses NOT offered at Bellevue College in a sorted array
    //Adds 0 to the front of the array
    public static Integer[] getArrayAllNonBCCID(Connection con) throws SQLException {
        //Query to get every CID NOT offered at BC
        ResultSet rsAllNonBCCID = Queries.getAllNonBCCID(con);

        //Count the number of CIDs
        int rows = Queries.getRowCount(rsAllNonBCCID);

        //Create array to store all CIDs and 0
        Integer[] result = new Integer[rows + 1];

        //Set the first index to zero
        result[0] = 0;

        //Fill every other index with the CID numbers
        for (int i = 1; i <= rows; i++) {
            rsAllNonBCCID.next();
            result[i] = rsAllNonBCCID.getInt(1);
        }

        //Sort the array
        Arrays.sort(result);

        return result;
    }

    //Return the number of rows in a result set
    public static int getRowCount(ResultSet rs) throws SQLException {
        int count = 0;
        while (rs.next()) {
            count++;
        }

        rs.beforeFirst();

        return count;
    }
}
