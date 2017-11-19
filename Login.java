import java.sql.*;

//Post: Returns connection to the database if username and password are correct
//		Else returns null
public class Login {
    public static Connection authenticate(String username, String password) {
    		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/Default?useSSL=false", username, password);
		} catch (SQLException e) {
			return null;
		}
    }
}