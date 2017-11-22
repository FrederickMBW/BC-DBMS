import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {
	//get the SID of every school with otherID
	public static ResultSet getSIDwithOtherID (Connection con, String OtherID) {
		try {
			CallableStatement cst = con.prepareCall("{call getSIDwithOtherID(?)}");
			cst.setString(1, OtherID);
			ResultSet rs = cst.executeQuery();
			return rs;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}	
	}
	
	//get the SID of every school with given name
	public static ResultSet getSIDwithSchoolName (Connection con, String schoolName) {
		try {
			CallableStatement cst = con.prepareCall("{call getSIDwithSchoolName(?)}");
			cst.setString(1, schoolName);
			ResultSet rs = cst.executeQuery();
			return rs;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
	
	//Add a school to the database
	public static boolean addSchool(Connection con, String otherID, String schoolName, String address, String city, String state, String zipCode) {
		try {
    		CallableStatement cst = con.prepareCall("{call AddSchool(?,?,?,?,?,?)}");
			cst.setString(1, otherID);
			cst.setString(2, schoolName);
			cst.setString(3, address);
			cst.setString(4, city);
			cst.setString(5, state);
			cst.setString(6, zipCode);
			cst.executeQuery();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	//get Course number for every course with a matching course name and school
	public static ResultSet getCIDwithCourseNameAndSchoolName(Connection con, String courseName, String schoolName) {
		try {
			CallableStatement cst = con.prepareCall("{call getCIDwithCourseNameAndSchoolName(?,?)}");
			cst.setString(1, courseName);
			cst.setString(2, schoolName);
			ResultSet rs = cst.executeQuery();
			return rs;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
	
	//get Course number for every course with a matching course name and SID
	public static ResultSet GetCIDwithCourseNameAndSID (Connection con, String courseName, int SID) {
		try {
			CallableStatement cst = con.prepareCall("{call GetCIDwithCourseNameAndSID(?,?)}");
			cst.setString(1, courseName);
			cst.setInt(2,  SID);
			ResultSet rs = cst.executeQuery();
			return rs;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
	
	//Add a course to the database
	public static boolean addCourse(Connection con, int SID, String courseName, String courseTitle, String courseDepartment, int credits, String courseDescription, String courseOutcomes) {
	    	try {
	    		CallableStatement cst = con.prepareCall("{call AddCourse(?,?,?,?,?,?,?)}");
			cst.setInt(1, SID);
			cst.setString(2, courseName);
			cst.setString(3, courseTitle);
			cst.setString(4, courseDepartment);
			cst.setInt(5, credits);
			cst.setString(6, courseDescription);
			cst.setString(7, courseOutcomes);
			cst.executeQuery();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	//Get set of equivalent and non-equivalent courses when given BC course name, other course name, and other school name
	public static ResultSet isEquivilentSID (Connection con, String bellvueCollegeCourseName, String otherCourseName, int otherCourseSID) {
		try {
			CallableStatement cst = con.prepareCall("{call isEquivilentSID(?,?,?)}");
			cst.setString(1, bellvueCollegeCourseName);
			cst.setString(2, otherCourseName);
			cst.setInt(3, otherCourseSID);
			ResultSet rs = cst.executeQuery();
			return rs;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
	
	//Get set of equivalent and non-equivalent courses when given BC course name, other course name, and other school name
	public static ResultSet isEquivilentSchoolName (Connection con, String bellvueCollegeCourseName, String otherCourseName, String otherCourseSchoolName) {
		try {
			CallableStatement cst = con.prepareCall("{call isEquivilentSchoolName(?,?,?)}");
			cst.setString(1, bellvueCollegeCourseName);
			cst.setString(2, otherCourseName);
			cst.setString(3, otherCourseSchoolName);
			ResultSet rs = cst.executeQuery();
			return rs;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
	
	//Add an equivalent course
	public static boolean addEquivalentCourse(Connection con, int bellevueCollegeCID, int otherCID, boolean isEquivalent) {
		try {
			CallableStatement cst = con.prepareCall("{call AddEquivalentCourse(?,?,?)}");
			cst.setInt(1, bellevueCollegeCID);
			cst.setInt(2, otherCID);
			cst.setBoolean(3, isEquivalent);
			cst.executeQuery();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
}
