import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Queries {
	//get the SID of every school with matching information
	public static ResultSet getSID(Connection con, String OID, String name, String address, String city, String state, String zipCode) throws SQLException {
		CallableStatement cst = con.prepareCall("{call getSID(?,?,?,?,?,?)}");
		cst.setString(1, OID);
		cst.setString(2, name);
		cst.setString(3, address);
		cst.setString(4, city);
		cst.setString(5, state);
		cst.setString(6, zipCode);
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
	
	//Get the information for a school when given the SID
	public static ResultSet getInfoSID(Connection con, int SID) throws SQLException {
    		CallableStatement cst = con.prepareCall("{call getInfoSID(?)}");
		cst.setInt(1, SID);
		return cst.executeQuery();
	}
	
	//Get the SID of every school
	public static ResultSet getEverySID(Connection con) throws SQLException {
		java.sql.Statement stmt = con.createStatement();
		return stmt.executeQuery("SELECT * FROM EverySID");
	}
	
	//get Course number for every course with a matching information not using the SID
	public static ResultSet getCID(Connection con, String courseName, String courseTitle, String courseDepartment, String schoolName) throws SQLException {
		CallableStatement cst = con.prepareCall("{call getCID(?,?,?,?)}");
		cst.setString(1, courseName);
		cst.setString(2, courseTitle);
		cst.setString(3, courseDepartment);
		cst.setString(4, schoolName);
		return cst.executeQuery();
	}
	
	//get Course number for every course with a matching information using the SID
	public static ResultSet getCIDwithSID (Connection con, String courseName, String title, String department, int SID) throws SQLException {
		CallableStatement cst = con.prepareCall("{call GetCIDwithSID(?,?,?,?)}");
		cst.setString(1, courseName);
		cst.setString(2,  title);
		cst.setString(3,  department);
		cst.setInt(4,  SID);
		return cst.executeQuery();
	}
	
	//Get the name of the course and the school's name
	public static ResultSet getCourseNameAndSchool(Connection con, int CID) throws SQLException {
		CallableStatement cst = con.prepareCall("{call getCourseNameAndSchool(?)}");
		cst.setInt(1, CID);
		return cst.executeQuery();
	}
	
	//Get every course CID in the database
	public static ResultSet getAllNonBCCID(Connection con) throws SQLException {
		java.sql.Statement stmt = con.createStatement();
		return stmt.executeQuery("SELECT * FROM EVERYNONBCCID");
	}
	
	//Get every course CID from BC in the database
	public static ResultSet getAllBCCID(Connection con) throws SQLException  {
		java.sql.Statement stmt = con.createStatement();
		return stmt.executeQuery("SELECT * FROM EveryBCCID");
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
	
	//Get set of equivalent and non-equivalent courses when given BC course name, other course name, and other school name
	public static ResultSet isEquivilentSID (Connection con, String bellvueCollegeCourseName, String otherCourseName, int otherCourseSID) throws SQLException {
		CallableStatement cst = con.prepareCall("{call isEquivilentSID(?,?,?)}");
		cst.setString(1, bellvueCollegeCourseName);
		cst.setString(2, otherCourseName);
		cst.setInt(3, otherCourseSID);
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
	public static ResultSet isEquivilent (Connection con, String bellvueCollegeCourseName, String otherCourseName, String otherCourseSchoolName) throws SQLException {
		CallableStatement cst = con.prepareCall("{call isEquivilent(?,?,?)}");
		cst.setString(1, bellvueCollegeCourseName);
		cst.setString(2, otherCourseName);
		cst.setString(3, otherCourseSchoolName);
		return cst.executeQuery();
	}
	
	//Get set of equivalent and non-equivalent courses when given BC course name, other course name, and other school name
	public static ResultSet updateCourseEquivalent(Connection con, int CID1, int CID2, boolean isEquiv, String comment) throws SQLException {
		CallableStatement cst = con.prepareCall("{call UpdateEQUIVALENT(?,?,?,?)}");
		cst.setInt(1, CID1);
		cst.setInt(2, CID2);
		cst.setBoolean(3, isEquiv);
		cst.setString(4, comment);
		return cst.executeQuery();
	}
	
	//Add an equivalent course
	public static void addEquivalentCourse(Connection con, int bellevueCollegeCID, int otherCID, boolean isEquivalent, String comment) throws SQLException {
		CallableStatement cst = con.prepareCall("{call AddEquivalentCourse(?,?,?,?)}");
		cst.setInt(1, bellevueCollegeCID);
		cst.setInt(2, otherCID);
		cst.setBoolean(3, isEquivalent);
		cst.setString(4, comment);
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
}
