import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Test extends JDialog {
	public Test(Connection con) {
		//JPanel panel = new JPIsEquivalentCourse(con);
		//JPanel panel = new JPQueryCourse(con);
		//JPanel panel = new JPAddCourse(con);
		//JPanel panel = new JPQuerySchool(con);
		//JPanel panel = new JPAddSchool(con);
		//JPanel panel = new JPAddEquivalentCourse(con);
		
		JPanel panel;
		try {
			panel = new JPUpdateCourse(con);
		//	panel = new JPAddEquivalentCourse(con);
			getContentPane().add(panel, BorderLayout.CENTER);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//getContentPane().add(panel, BorderLayout.CENTER);
		
		pack();
		setResizable(false);
	}
}