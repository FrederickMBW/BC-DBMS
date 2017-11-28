import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Test2 extends JDialog {
	public Test2(Connection con) {

		try {
			JPanel panel6= new JPSchool(con);
			getContentPane().add(panel6, BorderLayout.CENTER);
		} catch (SQLException e) {
			System.out.println("fail!");
			e.printStackTrace();
		}
	
		this.setVisible(true);
		
		pack();
		setResizable(false);
	}
}