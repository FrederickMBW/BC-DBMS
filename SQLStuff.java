package com.company;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import com.mysql.cj.jdbc.Driver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SQLStuff
{
    static String url = "jdbc:mysql://localhost:3306/sean_server?serverTimezone=UTC";
    static String username = "root";
    static String password = "cs331";
    static String[] studentTableColumnsName = {"SID", "First Name","Last Name", "Street", "City","State", "Zip", "Phone Number", "Email","Standing", "CGPA", "Demographic", "Date of Birth","advisor","enrolled","graduation Date"};
    static String[] studentSQLcolumnNames = {"SID", "nameFirst","NameLast", "Address_street", "address_City","address_State", "address_Zip", "Phone", "Email","Standing", "CGPA", "Demographic", "dob","advisor", "currently_enrolled","grad_Date"};
    static String[] devicesColumns = {"Item ID","Device ID","Item type","Status","Model Number","Price","Date of purchase"};
    static String[] devicesSQLcolumns = {"item_id","device_id","item_description","status","model_number","price","date_of_purchase"};
    static String[] booksColumns = {"Item ID","Book ID","Title","Status","Item description","Edition","Book type","ISBN","Condition","Price","Date of purchase"};
    static String[] booksSQLcolumns = {"item_id","book_id","title","status","item_description","edition","book_type","isbn","book_condition","price","date_of_purchase"};
    static String[] genericItemColumns = {"Item id", "Item description","Status","Price","Date of purchase"};
    static String[] genericItemSQLcolumns = {"item_id","item_description","status","price","date_of_purchase"};
    static String[] checkoutColumns = {"SID","First name","Last name","Item description","Item ID","Date checked out","Date returned"};
    static String[] checkoutSQLcolumns = {"sid","namefirst","namelast","item_description","item_id","date_checked_out","date_returned"};
    static String[] maintenanceColumns = {"Item description","Item ID","Description of maintenance","Date sent out","Date returned"};
    static String[] maintenanceSQLColumns = {"item_description","item_id","description_of_maintenance","date_maintainance_start","date_maintainance_end"};

    static Connection conn = null;

    public static void searchSchoolsViewThing(){
        try {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            JDialog d = new JDialog();
            d.addWindowListener(new WindowListener() {
                public void windowOpened(WindowEvent e) {

                }
                public void windowClosing(WindowEvent e) { }
                public void windowClosed(WindowEvent e) {
                    closeConnection();
                }
                public void windowIconified(WindowEvent e) { }
                public void windowDeiconified(WindowEvent e) { }
                public void windowActivated(WindowEvent e) { }
                public void windowDeactivated(WindowEvent e) { }
            });
            d.add(new JPSchool(conn));
            d.setSize(500,550);
            d.setVisible(true);
        } catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("School search broke",ex.toString());
        }
    }

    public static void searchCoursesViewThing(){
        try {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            JDialog d = new JDialog();
            d.addWindowListener(new WindowListener() {
                public void windowOpened(WindowEvent e) {

                }
                public void windowClosing(WindowEvent e) { }
                public void windowClosed(WindowEvent e) {
                    closeConnection();
                }
                public void windowIconified(WindowEvent e) { }
                public void windowDeiconified(WindowEvent e) { }
                public void windowActivated(WindowEvent e) { }
                public void windowDeactivated(WindowEvent e) { }
            });
            d.add(new JPCourse(conn));
            d.setSize(500,550);
            d.setVisible(true);
        } catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Course search broke",ex.toString());
        }
    }

    public static void closeConnection(){
        if (conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            { /* ignored */}
        }
    }

    public static void searchEquivalenciesViewThing(){
        try {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            JDialog d = new JDialog();
            d.addWindowListener(new WindowListener() {
                public void windowOpened(WindowEvent e) {

                }
                public void windowClosing(WindowEvent e) { }
                public void windowClosed(WindowEvent e) {
                    closeConnection();
                }
                public void windowIconified(WindowEvent e) { }
                public void windowDeiconified(WindowEvent e) { }
                public void windowActivated(WindowEvent e) { }
                public void windowDeactivated(WindowEvent e) { }
            });
            d.add(new JPEquivalentCourse(conn));
            d.setSize(500,550);
            d.setVisible(true);

        } catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Equivalency search broke",ex.toString());
        }
    }

    public static void update_student(String[] f){
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            String query = "select * from students_view where sid = '" + f[0] + "'";
            String query1 = "update student set " + studentSQLcolumnNames[9] + " = ? where sid = ?";
            String query2 = "update BC_Member set " + studentSQLcolumnNames[1] + " = ? where sid = ?";
            //System.out.println(query);
            Statement stmt1 = conn.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            rs.next();
            PreparedStatement stmt;
            if (!rs.getString(studentSQLcolumnNames[1]).equals(f[1])) {
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[1]);
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if (!rs.getString(studentSQLcolumnNames[2]).equals(f[2])) {
                query2 = "update BC_Member set " + studentSQLcolumnNames[2] + " = ? where sid = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[2]);
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if (!rs.getString(studentSQLcolumnNames[3]).equals(f[3])) {
                query2 = "update BC_Member set " + studentSQLcolumnNames[3] + " = ? where sid = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[3]);
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if (!rs.getString(studentSQLcolumnNames[4]).equals(f[4])) {
                query2 = "update BC_Member set " + studentSQLcolumnNames[4] + " = ? where sid = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[4]);
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if (!rs.getString(studentSQLcolumnNames[5]).equals(f[5])) {
                query2 = "update BC_Member set " + studentSQLcolumnNames[5] + " = ? where sid = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[5]);
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if (!rs.getString(studentSQLcolumnNames[6]).equals(f[6])) {
                query2 = "update BC_Member set " + studentSQLcolumnNames[6] + " = ? where sid = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[6]);
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if (!rs.getString(studentSQLcolumnNames[7]).equals(f[7])) {
                query2 = "update BC_Member set " + studentSQLcolumnNames[7] + " = ? where sid = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[7]);
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if (!rs.getString(studentSQLcolumnNames[8]).equals(f[8])) {
                query2 = "update BC_Member set " + studentSQLcolumnNames[8] + " = ? where sid = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[8]);
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if (!rs.getString(studentSQLcolumnNames[9]).equals(f[9])) {
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[9]);
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if (!rs.getString(studentSQLcolumnNames[10]).equals(f[10])) {
                query1 = "update student set " + studentSQLcolumnNames[10] + " = ? where sid = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[10]);
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if (!rs.getString(studentSQLcolumnNames[11]).equals(f[11])) {
                query1 = "update student set " + studentSQLcolumnNames[11] + " = ? where sid = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[11]);
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if (!rs.getString(studentSQLcolumnNames[12]).equals(f[12])) {
                query1 = "update student set " + studentSQLcolumnNames[12] + " = ? where sid = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[12]);
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if (!rs.getString(studentSQLcolumnNames[13]).equals(f[13])) {
                query1 = "update student set " + studentSQLcolumnNames[13] + " = ? where sid = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[13]);
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if ((rs.getBoolean(studentSQLcolumnNames[14]) && f[14].equals("no")) || (!rs.getBoolean(studentSQLcolumnNames[14]) && f[14].equals("yes"))){
                query1 = "update student set " + studentSQLcolumnNames[14] + " = ? where sid = ?";
                stmt = conn.prepareStatement(query1);
                if (f[14].equals("yes"))
                    stmt.setString(1, "1");
                else
                    stmt.setString(1,"0");
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            if (rs.getString(studentSQLcolumnNames[15]) == null && !f[15].equals("")) {
                query1 = "update student set " + studentSQLcolumnNames[15] + " = ? where sid = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[15] + "-00-00");
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
            CommonDialogs.standardMessageBox("Update successful","Student record has been updated.");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Student update broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void add_student(String f[]){
        Connection conn = null;
        boolean bcMemberAdded = false;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);

            String query1 = "insert into BC_Member (SID, nameFirst, nameLast, address_street, address_city, address_state, address_zip, phone,email) VALUES (?,?,?,?,?,?,?,?,?)";
            //9
            String query2 = "insert into student (sid, standing, cgpa, demographic, dob, advisor, currently_enrolled) values (?,?,?,?,?,?,?)";
            //8
            //System.out.println(query1);
            //System.out.println(query2);
            PreparedStatement stmt = conn.prepareStatement(query1);
            stmt.setString(1,f[0]);
            stmt.setString(2,f[1]);
            stmt.setString(3,f[2]);
            stmt.setString(4,f[3]);
            stmt.setString(5,f[4]);
            stmt.setString(6,f[5]);
            stmt.setString(7,f[6]);
            stmt.setString(8,f[7]);
            stmt.setString(9,f[8]);
            stmt.executeUpdate();
            bcMemberAdded = true;
            stmt = conn.prepareStatement(query2);
            stmt.setString(1,f[0]);
            stmt.setString(2,f[9]);
            if (f[10].equals(""))
                f[10] = "0";
            stmt.setString(3,f[10]);
            stmt.setString(4,f[11]);
            if (f[12].equals(""))
                f[12] = "0";
            stmt.setString(5,f[12] + "-00-00");
            stmt.setString(6,f[13]);
            stmt.setString(7,"1");
            stmt.executeUpdate();
            CommonDialogs.standardMessageBox("Student addition successful","Student has been added to the database.");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Student addition broke",ex.toString());
            if (bcMemberAdded) {
                try {
                    String query2 = "delete from bc_member where sid = ?";
                    PreparedStatement stmt = conn.prepareStatement(query2);
                    stmt.setString(1, f[0]);
                    //System.out.println(query2);
                    stmt.executeUpdate();

                } catch (SQLException e) {
                    CommonDialogs.standardErrorMessage("Deleting extra entry broke",ex.toString());
                }
            }
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void updateStudentGetter(JComboBox<String> j, String[] f){
        j.removeAllItems();
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from students_view s";
            query += " where s.sid <> ''";
            if (!f[0].equals(""))
                query = query + " and s.sid like '%" + f[0] + "%'";
            if (!f[1].equals(""))
                query = query + " and s.namefirst like '%" + f[1] + "%'";
            if (!f[2].equals(""))
                query = query + " and s.namelast like '%" + f[2] + "%'";
            if (!f[3].equals(""))
                query = query + " and s.address_street like '%" + f[3] + "%'";
            if (!f[4].equals(""))
                query = query + " and s.address_city like '%" + f[4] + "%'";
            if (!f[5].equals(""))
                query = query + " and s.address_state = '" + f[5] + "'";
            if (!f[6].equals(""))
                query = query + " and s.address_zip like '%" + f[6] + "%'";
            if (!f[7].equals(""))
                query = query + " and s.phone like '%" + f[7] + "%'";
            if (!f[8].equals(""))
                query = query + " and s.email like '%" + f[8] + "%'";
            if (!f[9].equals(""))
                query = query + " and s.standing = '" + f[9] + "'";
            if (!f[10].equals(""))
                query = query + " and s.cgpa = " + f[10];
            if (!f[11].equals(""))
                query = query + " and s.demographic like '%" + f[11] + "%'";
            if (!f[12].equals("")) {
                if (f[12].length() == 4)
                    query = query + " and s.dob = '" + f[12] + "-00-00'";
                else
                    query = query + " and s.dob = '" + f[12] + "'";
            }
            if (!f[13].equals(""))
                query = query + " and s.advisor like '%" + f[13] + "%'";
            if (!f[14].equals("")) {
                if (f[14].length() == 4)
                    query = query + " and s.grad_date = '" + f[14] + "-00-00'";
                else
                    query = query + " and s.grad_date = '" + f[14] + "'";
            }
            if (!f[15].equals("no"))
                query = query + " and s.currently_enrolled = true";
            else
                query = query + " and s.currently_enrolled = false";
            query += " order by namelast";
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while ( resultSet.next())
            {
                String res = resultSet.getString("sid") + ' ' + resultSet.getString("namelast") + ' ' + resultSet.getString("namefirst");
                j.addItem(res);
            }
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Student combobox update broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void get_student_data(DefaultTableModel t, String f[]){
        t.setColumnCount(0);
        for (int i = 0; i < 16; i++)
            t.addColumn(studentTableColumnsName[i]);
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from students_view s";
//            if (!(f[0].equals("") && f[1].equals("") && f[2].equals("") && f[3].equals("") && f[4].equals("") && f[5].equals("") && f[7].equals("") &&
//                    f[8].equals("") && f[10].equals("") && f[11].equals("") && f[12].equals(""))){
                query += " where s.sid <> ''";
                if (!f[0].equals(""))
                    query = query + " and s.sid = '" + f[0] + "'";
                if (!f[1].equals(""))
                    query = query + " and s.namefirst like '%" + f[1] + "%'";
                if (!f[2].equals(""))
                    query = query + " and s.namelast like '%" + f[2] + "%'";
                if (!f[3].equals(""))
                    query = query + " and s.address_city like '%" + f[3] + "%'";
                if (!f[4].equals(""))
                    query = query + " and s.address_state = '" + f[4] + "'";
                if (!f[5].equals(""))
                    query = query + " and s.standing = '" + f[5] + "'";
                if (!f[7].equals(""))
                    query = query + " and s.cgpa " + f[6] + ' ' + f[7];
                if (!f[8].equals(""))
                    query = query + " and s.demographic like '%" + f[8] + "%'";
                if (!f[10].equals(""))
                    query = query + " and datediff(now(), s.dob)/365 " + f[9] + ' ' + f[10];
                if (!f[11].equals(""))
                    query = query + " and s.advisor like '%" + f[11] + "%'";
                if (!f[12].equals(""))
                    query = query + " and s.grad_date < '" + f[12] + "-00-00 00:00:00'";
            //}
            if (!f[13].equals("no"))
                query = query + " and s.currently_enrolled = true";
            else
                query = query + " and s.currently_enrolled = false";
            query = query + " order by " + f[14];
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            String data[][] = new String[count][16];
            int j = 0;
            resultSet.beforeFirst();
            String[] SQLcolumns = {"SID", "nameFirst","NameLast", "Address_street", "address_City","address_State", "address_Zip", "Phone", "Email","Standing", "CGPA", "Demographic", "dob","advisor","currently_enrolled","grad_Date"};

            while ( resultSet.next())
            {
                for (int i = 0; i < 16; i++) {
                    data[j][i] = resultSet.getString(SQLcolumns[i]);
                }
                j++;
            }
            for (int i = 0; i < count; i++) {
                t.addRow(data[i]);
            }
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Student selection broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static String[] get_single_student_data(String f[]){
        String[] returnFields = {f[0],f[1],f[2],f[3],f[4],f[5],f[6],f[7],f[8],f[9],f[10],f[11],f[12],f[13],f[14],f[15]};
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from students_view s where s.sid <> ''";
            if (!f[0].equals(""))
                query = query + " and s.sid = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.namefirst like '%" + f[1] + "%'";
            if (!f[2].equals(""))
                query = query + " and s.namelast like '%" + f[2] + "%'";
            if (!f[3].equals(""))
                query = query + " and s.address_street like '%" + f[3] + "%'";
            if (!f[4].equals(""))
                query = query + " and s.address_city like '%" + f[4] + "%'";
            if (!f[5].equals(""))
                query = query + " and s.address_state = '" + f[5] + "'";
            if (!f[6].equals(""))
                query = query + " and s.address_zip = '" + f[6] + "'";
            if (!f[7].equals(""))
                query = query + " and s.phone = '" + f[7] + "'";
            if (!f[8].equals(""))
                query = query + " and s.email = '" + f[8] + "'";
            if (!f[9].equals(""))
                query = query + " and s.standing = '" + f[9] + "'";
            if (!f[10].equals(""))
                query = query + " and s.cgpa = " + f[10];
            if (!f[11].equals(""))
                query = query + " and s.demographic like '%" + f[11] + "%'";
            if (!f[12].equals(""))
                query = query + " and s.dob = '" + f[12];
            if (f[12].length() == 4)
                query += "-00-00'";
            if (!f[13].equals(""))
                query = query + " and s.advisor like '%" + f[13] + "%'";
            if (f[14].equals("yes"))
                query = query + " and s.currently_enrolled = true";
            else
                query = query + " and s.currently_enrolled = false";
            if (!f[15].equals(""))
                query = query + " and s.grad_date < '" + f[15];
            if (f[15].length() == 4)
                query += "-00-00'";
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            if (count == 1) {
                resultSet.beforeFirst();
                resultSet.next();
                returnFields[0] = resultSet.getString("sid");
                returnFields[1] = resultSet.getString("nameFirst");
                returnFields[2] = resultSet.getString("NameLast");
                returnFields[3] = resultSet.getString("Address_street");
                returnFields[4] = resultSet.getString("address_City");
                returnFields[5] = resultSet.getString("address_State");
                returnFields[6] = resultSet.getString("address_Zip");
                returnFields[7] = resultSet.getString("Phone");
                returnFields[8] = resultSet.getString("Email");
                returnFields[9] = resultSet.getString("Standing");
                returnFields[10] = resultSet.getString("CGPA");
                returnFields[11] = resultSet.getString("Demographic");
                returnFields[12] = resultSet.getString("dob");
                returnFields[13] = resultSet.getString("advisor");
                if (resultSet.getBoolean("currently_enrolled"))
                    returnFields[14] = "yes";
                else
                    returnFields[14] = "no";
                if (resultSet.getString("grad_Date") != null)
                    returnFields[15] = resultSet.getString("grad_Date");
            } else
                CommonDialogs.standardErrorMessage("School search broke",count + " entries found, required: 1");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Get student broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
        return returnFields;
    }

    public static void update_device(String[] f){
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            String query = "select * from devices_view where item_id = '" + f[0] + "'";
            String query1 = "update devices set " + devicesSQLcolumns[1] + " = ? where item_id = ?";
            String query2 = "update generic_item set " + devicesSQLcolumns[2] + " = ? where item_id = ?";
            //System.out.println(query);
            Statement stmt1 = conn.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            rs.next();
            PreparedStatement stmt;
            if (!rs.getString(devicesSQLcolumns[1]).equals(f[1])) {//"Device ID",
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[1]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(devicesSQLcolumns[2]).equals(f[2])) {//"Item type",
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[2]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(devicesSQLcolumns[3]).equals(f[3]) ){//"Status",
                query2 = "update generic_item set " + devicesSQLcolumns[3] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[3]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(devicesSQLcolumns[4]).equals(f[4]) ){//"Model Number",
                query1 = "update devices set " + devicesSQLcolumns[4] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[4]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(devicesSQLcolumns[5]).equals(f[5]) && !f[5].equals("")) {//"Price",
                query2 = "update generic_item set " + devicesSQLcolumns[5] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[5]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(devicesSQLcolumns[6]).equals(f[6])) {//"Date of purchase"
                query2 = "update generic_item set " + devicesSQLcolumns[6] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query2);
                if (f[6].length() == 4)
                    stmt.setString(1, f[6] + "-00-00");
                else
                    stmt.setString(1,f[6]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            CommonDialogs.standardMessageBox("Update successful","Device record has been updated.");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Device update broke",ex.toString());
            ////System.out.println("java.sql.SQLException: Illegal operation on empty result set.");
        }   //if update nonexistent value(or change device id)
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void add_device(String f[]){
        Connection conn = null;
        boolean genericItemAdded = false;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            String query1 = "insert into generic_item (item_description, status, price, date_of_purchase) VALUES (?,?,?,?)";
            String query2 = "insert into devices (item_id,device_id, model_number) values (?,?,?)";

            //System.out.println(query1);

            PreparedStatement stmt = conn.prepareStatement(query1);
            stmt.setString(1,f[2]);
            if (f[3].equals("All"))
                f[3] = "Available";
            stmt.setString(2,f[3]);
            if (f[5].equals(""))
                f[5] = "0";
            stmt.setString(3,f[5]);
            if (f[6].length() == 4)
                stmt.setString(4,f[6] + "-00-00");
            else if (f[6].length() == 0)
                stmt.setString(4,"0000-00-00");
            else
                stmt.setString(4,f[6]);
            stmt.executeUpdate();
            genericItemAdded = true;
            String query3 = "select * from generic_item where item_id = (select max(item_id) from generic_item)";
            Statement stmt2 = conn.createStatement();
            //System.out.println(query3);
            ResultSet rs = stmt2.executeQuery(query3);
            rs.next();
            stmt = conn.prepareStatement(query2);
            stmt.setString(1,rs.getString("item_id"));
            stmt.setString(2,f[1]);
            stmt.setString(3,f[4]);
            //System.out.println(query2);
            stmt.executeUpdate();
            CommonDialogs.standardMessageBox("Device addition successful","Device has been added to the database.");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Device addition broke",ex.toString());
            if (genericItemAdded) {
                try {
                    String query2 = "delete from generic_item where item_id = ?";
                    String query3 = "select * from generic_item where item_id = (select max(item_id) from generic_item)";
                    Statement stmt2 = conn.createStatement();
                    //System.out.println(query3);
                    ResultSet rs = stmt2.executeQuery(query3);
                    rs.next();
                    PreparedStatement stmt = conn.prepareStatement(query2);
                    stmt.setString(1, rs.getString("item_id"));
                    //System.out.println(query2);
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    CommonDialogs.standardErrorMessage("Deletion of extra broke",ex.toString());
                }
            }
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void updateDeviceGetter(JComboBox<String> j, String[] f){
        j.removeAllItems();
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from devices_view s where s.device_id <> ''";
            if (!f[0].equals(""))
                query = query + " and s.item_id = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.device_id = '" + f[1] + "'";
            if (!f[2].equals(""))
                query = query + " and s.item_description like '%" + f[2] + "%'";
            if (!f[3].equals("All"))
                query = query + " and s.status = '" + f[3] + "'";
            if (!f[4].equals(""))
                query = query + " and s.model_number like '%" + f[4] + "%'";
            if (!f[5].equals(""))
                query = query + " and s.price = '" + f[5] + "'";
            if (!f[6].equals("")) {
                if (f[6].length() == 4)
                    query = query + " and s.date_of_purchase = '" + f[6] + "-00-00'";
                else
                    query = query + " and s.date_of_purchase = '" + f[6] + "'";
            }
            query = query + " order by device_id";
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while ( resultSet.next())
            {
                String res = resultSet.getString("device_id") + ' ' + resultSet.getString("item_description");
                j.addItem(res);
            }
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Device combobox update broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void get_device_data(DefaultTableModel t, String f[]){
        t.setColumnCount(0);
        for (int i = 0; i < 7; i++)
            t.addColumn(devicesColumns[i]);
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from devices_view s";
            query += " where s.device_id <> ''";
            if (!f[0].equals(""))
                query = query + "and s.item_id = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.device_id = '" + f[1] + "'";
            if (!f[2].equals(""))
                query = query + " and s.item_description like '%" + f[2] + "%'";
            if (!f[3].equals("All"))
                query = query + " and s.status = '" + f[3] + "'";
            if (!f[4].equals(""))
                query = query + " and s.model_number like '%" + f[4] + "%'";
            if (!f[6].equals(""))
                query = query + " and s.price " + f[5] + " " + f[6];
            if (!f[7].equals("")) {
                if (f[7].length() == 4)
                    query = query + " and s.date_of_purchase = '" + f[7] + "-00-00'";
                else
                    query = query + " and s.date_of_purchase = '" + f[7] + "'";
            }
            query = query + " order by " + f[8];
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            String data[][] = new String[count][7];
            int j = 0;
            resultSet.beforeFirst();
            while ( resultSet.next())
            {
                for (int i = 0; i < 7; i++) {
                    data[j][i] = resultSet.getString(devicesSQLcolumns[i]);
                }
                j++;
            }
            for (int i = 0; i < count; i++) {
                t.addRow(data[i]);
            }
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Device selection broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static String[] get_single_device_data(String f[]){
        String[] returnFields = {f[0],f[1],f[2],f[3],f[4],f[5],f[6]};
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from devices_view s where s.device_id <> ''";
            if (!f[0].equals(""))
                query = query + " and s.item_id = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.device_id = '" + f[1] + "'";
            if (!f[2].equals(""))
                query = query + " and s.item_description like '%" + f[2] + "%'";
            if (!f[3].equals("All"))
            query = query + " and s.status = '" + f[3] + "'";
            if (!f[4].equals(""))
                query = query + " and s.model_number like '%" + f[4] + "%'";
            if (!f[5].equals(""))
                query = query + " and s.price = '" + f[5] + "'";
            if (!f[6].equals("")) {
                if (f[6].length() == 4)
                    query = query + " and s.date_of_purchase = '" + f[6] + "-00-00'";
                else
                    query = query + " and s.date_of_purchase = '" + f[6] + "'";
            }
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            if (count == 1) {
                resultSet.beforeFirst();
                resultSet.next();
                for (int i = 0; i < 7; i++){
                    returnFields[i] = resultSet.getString(devicesSQLcolumns[i]);
                }
            } else
                CommonDialogs.standardErrorMessage("Get device failed",count + " entries found, required: 1");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Get device broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
        return returnFields;
    }

    public static void update_book(String[] f){
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            String query = "select * from books_view where item_id = '" + f[0] + "'";
            String query1 = "update books set " + booksSQLcolumns[1] + " = ? where item_id = ?";
            String query2 = "update generic_item set " + booksSQLcolumns[3] + " = ? where item_id = ?";
            //System.out.println(query);
            Statement stmt1 = conn.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            rs.next();
            PreparedStatement stmt;
            if (!rs.getString(booksSQLcolumns[1]).equals(f[1])) {//book id
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[1]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[2]).equals(f[2])) {//title
                query1 = "update books set " + booksSQLcolumns[2] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[2]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[3]).equals(f[3]) ){//status
                if (f[3].equals("All"))
                    f[3] = "Available";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[3]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[4]).equals(f[4]) ){//item description
                query2 = "update generic_item set " + booksSQLcolumns[4] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[4]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[5]).equals(f[5]) ){//edition
                query1 = "update books set " + booksSQLcolumns[5] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[5]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[6]).equals(f[6]) ){//book type
                query1 = "update books set " + booksSQLcolumns[6] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[4]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[7]).equals(f[7]) ){//isbn
                query1 = "update books set " + booksSQLcolumns[7] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[7]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[8]).equals(f[8]) ){//condition
                query1 = "update books set " + booksSQLcolumns[8] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[8]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[9]).equals(f[9]) && !f[9].equals("")) {//price
                query2 = "update generic_item set " + booksSQLcolumns[9] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[9]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[10]).equals(f[10])) {//dop
                query2 = "update generic_item set " + booksSQLcolumns[10] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query2);
                if (f[6].length() == 4)
                    stmt.setString(1, f[10] + "-00-00");
                else
                    stmt.setString(1,f[10]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            CommonDialogs.standardMessageBox("Update successful","Book record has been updated.");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Book updates broke",ex.toString());
        }   //if update nonexistent value(or change device id)
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void add_book(String f[]){
        Connection conn = null;
        boolean genericItemAdded = false;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            String query1 = "insert into generic_item ( status,item_description, price, date_of_purchase) VALUES (?,?,?,?)";
            String query2 = "insert into books (item_id,book_id, title,edition,book_type,isbn, book_condition) values (?,?,?,?,?,?,?)";

            //System.out.println(query1);

            PreparedStatement stmt = conn.prepareStatement(query1);
            stmt.setString(1,f[3]);
            if (f[3].equals("All"))
                f[3] = "Available";
            stmt.setString(2,f[4]);
            if (f[9].equals(""))
                f[9] = "0";
            stmt.setString(3,f[9]);
            if (f[10].length() == 4)
                stmt.setString(4,f[10] + "-00-00");
            else if (f[10].length() == 0) {
                stmt.setString(4, "0000-00-00");
            } else {
                stmt.setString(4, f[10]);
            }
            stmt.executeUpdate();
            genericItemAdded = true;
            String query3 = "select * from generic_item where item_id = (select max(item_id) from generic_item)";
            Statement stmt2 = conn.createStatement();
            //System.out.println(query3);
            ResultSet rs = stmt2.executeQuery(query3);
            rs.next();
            stmt = conn.prepareStatement(query2);
            stmt.setString(1,rs.getString("item_id"));
            stmt.setString(2,f[1]);
            stmt.setString(3,f[2]);
            if (f[5].equals(""))
                f[5] = "0";
            stmt.setString(4,f[5]);
            stmt.setString(5,f[6]);
            stmt.setString(6,f[7]);
            stmt.setString(7,f[8]);
            //System.out.println(query2);
            stmt.executeUpdate();
            CommonDialogs.standardMessageBox("Book addition successful","Book has been added to the database.");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Book addition broke",ex.toString());
            if (genericItemAdded) {
                try {
                    String query2 = "delete from generic_item where item_id = ?";
                    String query3 = "select * from generic_item where item_id = (select max(item_id) from generic_item)";
                    Statement stmt2 = conn.createStatement();
                    //System.out.println(query3);
                    ResultSet rs = stmt2.executeQuery(query3);
                    rs.next();
                    PreparedStatement stmt = conn.prepareStatement(query2);
                    stmt.setString(1, rs.getString("item_id"));
                    //System.out.println(query2);
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    CommonDialogs.standardErrorMessage("Deletion of extra broke",ex.toString());
                }
            }
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void updateBookGetter(JComboBox<String> j, String[] f){
        j.removeAllItems();
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from books_view s";
            query += " where s.book_id <> ''";
            if (!f[0].equals(""))
                query = query + " and s.item_id = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.book_id = '" + f[1] + "'";
            if (!f[2].equals(""))
                query = query + " and s.title = '" + f[2] + "'";
            if (!f[3].equals("All"))
                query = query + " and s.status = '" + f[3] + "'";
            if (!f[4].equals(""))
                query = query + " and s.item_description like '%" + f[4] + "%'";
            if (!f[5].equals(""))
                query = query + " and s.edition = '" + f[5] + "'";
            if (!f[6].equals(""))
                query = query + " and s.book_type like '%" + f[6] + "%'";
            if (!f[7].equals(""))
                query = query + " and s.isbn = '" + f[7] + "'";
            if (!f[8].equals(""))
                query = query + " and s.book_condition = '" + f[8] + "'";
            if (!f[9].equals(""))
                query = query + " and s.price = " + f[9];
            if (!f[10].equals("")) {
                if (f[10].length() == 4)
                    query = query + " and s.date_of_purchase = '" + f[10] + "-00-00'";
                else
                    query = query + " and s.date_of_purchase = '" + f[10] + "'";
            }
            query = query + " order by book_id";
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while ( resultSet.next())
            {
                String res = resultSet.getString("book_id") + ' ' + resultSet.getString("item_description");
                j.addItem(res);
            }
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Device combobox broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void get_book_data(DefaultTableModel t, String f[]){
        t.setColumnCount(0);
        for (int i = 0; i < 11; i++)
            t.addColumn(booksColumns[i]);
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from books_view s";
            query += " where s.book_id <> ''";
            if (!f[0].equals(""))
                query = query + "and s.item_id = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.book_id = '" + f[1] + "'";
            if (!f[2].equals(""))
                query = query + " and s.title = '" + f[2] + "'";
            if (!f[3].equals("All"))
                query = query + " and s.status = '" + f[3] + "'";
            if (!f[4].equals(""))
                query = query + " and s.item_description like '%" + f[4] + "%'";
            if (!f[5].equals(""))
                query = query + " and s.edition = '" + f[5] + "'";
            if (!f[6].equals(""))
                query = query + " and s.book_type like '%" + f[6] + "%'";
            if (!f[7].equals(""))
                query = query + " and s.isbn = '" + f[7] + "'";
            if (!f[8].equals(""))
                query = query + " and s.book_condition = '" + f[8] + "'";
            if (!f[10].equals(""))
                query = query + " and s.price " + f[9] + " " + f[10];
            if (!f[11].equals("")) {
                if (f[11].length() == 4)
                    query = query + " and s.date_of_purchase = '" + f[11] + "-00-00'";
                else
                    query = query + " and s.date_of_purchase = '" + f[11] + "'";
            }
            query = query + " order by " + f[12];
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            String data[][] = new String[count][11];
            int j = 0;
            resultSet.beforeFirst();
            while ( resultSet.next())
            {
                for (int i = 0; i < 11; i++) {
                    data[j][i] = resultSet.getString(booksSQLcolumns[i]);
                }
                j++;
            }
            for (int i = 0; i < count; i++) {
                t.addRow(data[i]);
            }
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Book selection broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static String[] get_single_book_data(String f[]){
        String[] returnFields = {f[0],f[1],f[2],f[3],f[4],f[5],f[6],f[7],f[8],f[9],f[10]};
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from books_view s where s.book_id <> ''";
            if (!f[0].equals(""))
                query = query + " and s.item_id = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.book_id = '" + f[1] + "'";
            if (!f[2].equals(""))
                query = query + " and s.title like '%" + f[2] + "%'";
            if (!f[3].equals("All"))
                query = query + " and s.status = '" + f[3] + "'";
            if (!f[4].equals(""))
                query = query + " and s.item_description like '%" + f[4] + "%'";
            if (!f[5].equals(""))
                query = query + " and s.edition = '" + f[5] + "'";
            if (!f[6].equals(""))
                query = query + " and s.book_type = '" + f[6] + "'";
            if (!f[7].equals(""))
                query = query + " and s.isbn = '" + f[7] + "'";
            if (!f[8].equals(""))
                query = query + " and s.book_condition = '" + f[8] + "'";
            if (!f[9].equals(""))
                query = query + " and s.price = '" + f[9] + "'";
            if (!f[10].equals("")) {
                if (f[10].length() == 4)
                    query = query + " and s.date_of_purchase = '" + f[10] + "-00-00'";
                else
                    query = query + " and s.date_of_purchase = '" + f[10] + "'";
            }
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            if (count == 1) {
                resultSet.beforeFirst();
                resultSet.next();
                for (int i = 0; i < 11; i++){
                    returnFields[i] = resultSet.getString(booksSQLcolumns[i]);
                }
            } else
                CommonDialogs.standardErrorMessage("Get book failed",count + " entries found, required: 1");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Get book broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
        return returnFields;
    }

    public static void update_generic_item(String[] f){
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            String query = "select * from generic_items_view where item_id = '" + f[0] + "'";
            String query1 = "update generic_item set " + genericItemSQLcolumns[1] + " = ? where item_id = ?";
            //System.out.println(query);
            Statement stmt1 = conn.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            rs.next();
            PreparedStatement stmt;
            if (!rs.getString(genericItemSQLcolumns[1]).equals(f[1])) {//item description
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[1]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(genericItemSQLcolumns[2]).equals(f[2])) {//status
                if (f[2].equals("All"))
                    f[2] = "Available";
                query1 = "update generic_item set " + genericItemSQLcolumns[2] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[2]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(genericItemSQLcolumns[3]).equals(f[3]) && !f[3].equals("")) {//price
                query1 = "update generic_item set " + genericItemSQLcolumns[3] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[3]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(genericItemSQLcolumns[4]).equals(f[4]) && !f[4].equals("")) {//dop
                query1 = "update generic_item set " + genericItemSQLcolumns[4] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                if (f[4].length() == 4)
                    stmt.setString(1, f[4] + "-00-00");
                else
                    stmt.setString(1,f[4]);
                stmt.setString(2, f[0]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            CommonDialogs.standardMessageBox("Update successful","General item record has been updated.");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("General item update broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void add_generic_item(String f[]){
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            String query = "insert into generic_item (item_id, item_description,status, price, date_of_purchase) VALUES (0,?,?,?,?)";

            //System.out.println(query);

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,f[1]);
            if (f[2].equals("All"))
                f[2] = "Available";
            stmt.setString(2,f[2]);
            if (f[3].equals(""))
                f[3] = "0";
            stmt.setString(3,f[3]);
            if (f[4].length() == 4)
                stmt.setString(4,f[4] + "-00-00");
            else if (f[4].length() == 0)
                stmt.setString(4,"0000-00-00");
            else
                stmt.setString(4,f[4]);
            stmt.executeUpdate();
            CommonDialogs.standardMessageBox("General item addition successful","General item record has been added to the database.");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("General item addition broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void updateGenericItemGetter(JComboBox<String> j, String[] f){
        j.removeAllItems();
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from generic_items_view s where true";
            if (!f[0].equals(""))
                query = query + "and s.item_id = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.item_description like '%" + f[1] + "%'";
            if (!f[2].equals("All"))
                query = query + " and s.status = '" + f[2] + "'";
            if (!f[3].equals(""))
                query = query + " and s.price = " + f[3];
            if (!f[4].equals("")) {
                if (f[4].length() == 4)
                    query = query + " and s.date_of_purchase = '" + f[4] + "-00-00'";
                else
                    query = query + " and s.date_of_purchase = '" + f[4] + "'";
            }
            query = query + " order by item_id";
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while ( resultSet.next())
            {
                String res = resultSet.getString("item_id") + "   " + resultSet.getString("item_description");
                j.addItem(res);//maybe add status later to select without having to refresh
            }
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Device combobox update broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void get_generic_item_data(DefaultTableModel t, String f[]){
        t.setColumnCount(0);
        for (int i = 0; i < 5; i++)
            t.addColumn(genericItemColumns[i]);
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from generic_items_view s where true";
            if (!f[0].equals(""))
                query = query + " and s.item_id = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.item_description like '%" + f[1] + "%'";
            if (!f[2].equals("All"))
                query = query + " and s.status = '" + f[2] + "'";
            if (!f[4].equals(""))
                query = query + " and s.price " + f[3] + " " + f[4];
            if (!f[5].equals("")) {
                if (f[5].length() == 4)
                    query = query + " and s.date_of_purchase = '" + f[5] + "-00-00'";
                else
                    query = query + " and s.date_of_purchase = '" + f[5] + "'";
            }
            query = query + " order by " + f[6];
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            String data[][] = new String[count][5];
            int j = 0;
            resultSet.beforeFirst();
            while ( resultSet.next())
            {
                for (int i = 0; i < 5; i++) {
                    data[j][i] = resultSet.getString(genericItemSQLcolumns[i]);
                }
                j++;
            }
            for (int i = 0; i < count; i++) {
                t.addRow(data[i]);
            }
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Generic item selection broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static String[] get_single_generic_item_data(String f[]){
        String[] returnFields = {f[0],f[1],f[2],f[3],f[4]};
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from generic_items_view s where true";
            if (!f[0].equals(""))
                query = query + " and s.item_id = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.item_description like '%" + f[1] + "%'";
            if (!f[2].equals("All"))
                query = query + " and s.status = '" + f[2] + "'";
            if (!f[3].equals(""))
                query = query + " and s.price = '" + f[9] + "'";
            if (!f[4].equals("")) {
                if (f[4].length() == 4)
                    query = query + " and s.date_of_purchase = '" + f[4] + "-00-00'";
                else
                    query = query + " and s.date_of_purchase = '" + f[4] + "'";
            }
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            if (count == 1) {
                resultSet.beforeFirst();
                resultSet.next();
                for (int i = 0; i < 5; i++){
                    returnFields[i] = resultSet.getString(genericItemSQLcolumns[i]);
                }
            } else
                CommonDialogs.standardErrorMessage("Get generic item failed",count + " entries found, required: 1");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Get general item broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
        return returnFields;
    }

    public static void update_checkout(String[] f){
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            String query = "select * from checkout where sid = '" + f[0] + "' and item_id = '" + f[4] + "' and date_checked_out = '" + f[5] + ' ' + f[6] + "'";
            String query1;
            //System.out.println(query);
            Statement stmt1 = conn.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            rs.next();
            PreparedStatement stmt;
            String dateReturned = "";
            if (!f[7].equals("")){
                if (!f[8].equals("")) {
                    if (f[8].length() == 5)
                        f[8] = f[8] + ":00";
                } else
                    f[8] = "00:00:00";
                dateReturned = f[7] + ' ' + f[8];
            }
            if (rs.getString(checkoutSQLcolumns[6]) == null && !dateReturned.equals("")) {//date returned
                query1 = "update checking_out_item set " + checkoutSQLcolumns[6] + " = ? where sid = '" + f[0] + "' and item_id = '" + f[4] + "' and date_checked_out = '" + f[5] + ' ' + f[6] + "'";
                stmt = conn.prepareStatement(query1);
                if (!f[8].equals(""))
                    stmt.setString(1,f[7]+' '+f[8]);
                else
                    stmt.setString(1, f[4] + " 00:00:00");
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            CommonDialogs.standardMessageBox("Update successful","Checkout record has been updated.");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Checkout updates broke",ex.toString());
            ////System.out.println("java.sql.SQLException: Illegal operation on empty result set.");
        }   //if update nonexistent value(or change device id)
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void add_checkout(String f[]){
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            String query3 = "select * from generic_item where item_id = " + f[4] + " and status = 'Available'";
            Statement stmt3 = conn.createStatement();
            //System.out.println(query3);
            ResultSet rs = stmt3.executeQuery(query3);
            int count = 0;
            while (rs.next()){
                count++;
            }
            if (count == 1) {
                String query = "insert into checking_out_item (sid, date_checked_out, date_returned,item_id) VALUES (?,?,?,?)";
                //System.out.println(query);
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, f[0]);
                if (f[6].length() == 5)
                    f[6] = f[6] + ":00";
                stmt.setString(2, f[5] + ' ' + f[6]);
                if (f[7].equals(""))
                    stmt.setString(3, null);
                else {
                    if (!f[8].equals("")) {
                        if (f[8].length() == 5)
                            f[8] = f[8] + ":00";
                    } else
                        f[8] = "00:00:00";
                    stmt.setString(3, f[7] + ' ' + f[8]);
                }
                stmt.setString(4, f[4]);
                stmt.executeUpdate();
                CommonDialogs.standardMessageBox("Checkout addition successful","Checkout information has been added to the database.");
            } else
                CommonDialogs.standardErrorMessage("Error","Item isn't available to check out.");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Checkout addition broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void updateCheckoutGetter(JComboBox<String> j, String[] f){
        j.removeAllItems();
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from checkout s where true";//damn good time to learn about jcombobox listener. this would be a pain otherwise
            if (!f[0].equals(""))
                query = query + " and s.sid = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.namefirst like '%" + f[1] + "%'";
            if (!f[2].equals(""))
                query = query + " and s.namelast like '%" + f[2] + "%'";
            if (!f[3].equals(""))
                query = query + " and s.item_description like '%" + f[3] + "%'";
            if (!f[4].equals(""))
                query = query + " and s.item_id = '" + f[4] + "'";
            if (!f[5].equals("")) {
                if (!f[6].equals(""))
                    query = query + " and s.date_checked_out like '" + f[5] + " " + f[6] + "%'";
                else
                    query = query + " and s.date_checked_out like '%" + f[5] + "%'";
            } else {
                if (!f[6].equals(""))
                    query = query + " and s.date_checked_out like '%" + f[6] + "%'";
            }
            if (!f[7].equals("")) {
                if (!f[8].equals(""))
                    query = query + " and s.date_checked_out like '" + f[7] + " " + f[8] + "%'";
                else
                    query = query + " and s.date_checked_out like '%" + f[7] + "%'";
            } else {
                if (!f[8].equals(""))
                    query = query + " and s.date_checked_out like '%" + f[8] + "%'";
            }
            query = query + " order by date_returned";
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while ( resultSet.next())
            {
                String availability = "";
                if (resultSet.getString("date_returned") == null)
                    availability = "Still out";
                else
                    availability = resultSet.getString("date_returned");
                String res = resultSet.getString("date_checked_out") + ' ' + resultSet.getString("sid") + ' ' + resultSet.getString("item_id") + "   " + availability;
                j.addItem(res);
            }
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Device combobox update broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void get_checkout_data(DefaultTableModel t, String f[]){
        t.setColumnCount(0);
        for (int i = 0; i < 7; i++)
            t.addColumn(checkoutColumns[i]);
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from checkout s where true";//damn good time to learn about jcombobox listener. this would be a pain otherwise
            if (!f[0].equals(""))
                query = query + " and s.sid = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.namefirst like '%" + f[1] + "%'";
            if (!f[2].equals(""))
                query = query + " and s.namelast like '%" + f[2] + "%'";
            if (!f[3].equals(""))
                query = query + " and s.item_description like '%" + f[3] + "%'";
            if (!f[4].equals(""))
                query = query + " and s.item_id = '" + f[4] + "'";
            if (!f[5].equals("")) {
                if (!f[6].equals(""))
                    query = query + " and s.date_checked_out like '" + f[5] + " " + f[6] + "%'";
                else
                    query = query + " and s.date_checked_out like '%" + f[5] + "%'";
            } else {
                if (!f[6].equals(""))
                    query = query + " and s.date_checked_out like '%" + f[6] + "%'";
            }
            if (!f[7].equals("")) {
                if (!f[8].equals(""))
                    query = query + " and s.date_returned like '" + f[7] + " " + f[8] + "%'";
                else
                    query = query + " and s.date_returned like '%" + f[7] + "%'";
            } else {
                if (!f[8].equals(""))
                    query = query + " and s.date_returned like '%" + f[8] + "%'";
            }
            query = query + " order by " + f[9];
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            String data[][] = new String[count][7];
            int j = 0;
            resultSet.beforeFirst();
            while ( resultSet.next())
            {
                for (int i = 0; i < 7; i++) {
                    data[j][i] = resultSet.getString(checkoutSQLcolumns[i]);
                }
                j++;
            }
            for (int i = 0; i < count; i++) {
                t.addRow(data[i]);
            }
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Checkout selection broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void get_overdue_data(DefaultTableModel t){
        t.setColumnCount(0);
        for (int i = 0; i < 7; i++)
            t.addColumn(checkoutColumns[i]);
        t.addColumn("Days overdue");
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from overdue";//damn good time to learn about jcombobox listener. this would be a pain otherwise
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            String data[][] = new String[count][8];
            int j = 0;
            resultSet.beforeFirst();
            while ( resultSet.next())
            {
                for (int i = 0; i < 7; i++) {
                    data[j][i] = resultSet.getString(checkoutSQLcolumns[i]);
                }
                data[j][7] = resultSet.getString("days_overdue");
                j++;
            }
            for (int i = 0; i < count; i++) {
                t.addRow(data[i]);
            }
            if (count == 0)
                CommonDialogs.standardErrorMessage("No entries","No overdue checkouts were found.");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Overdue selection broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static String[] get_single_checkout_data(String f[]){
        String[] returnFields = {f[0],f[1],f[2],f[3],f[4],f[5],f[6],f[7],f[8]};
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from checkout s where true";
            if (!f[0].equals(""))
                query = query + " and s.sid = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.namefirst like '%" + f[1] + "%'";
            if (!f[2].equals(""))
                query = query + " and s.namelast like '%" + f[2] + "%'";
            if (!f[3].equals(""))
                query = query + " and s.item_description like '%" + f[3] + "%'";
            if (!f[4].equals(""))
                query = query + " and s.item_id = '" + f[4] + "'";
            if (!f[5].equals("")) {
                if (!f[6].equals(""))
                    query = query + " and s.date_checked_out like '" + f[5] + " " + f[6] + "%'";
                else
                    query = query + " and s.date_checked_out like '%" + f[5] + "%'";
            } else {
                if (!f[6].equals(""))
                    query = query + " and s.date_checked_out like '%" + f[6] + "%'";
            }
            if (!f[7].equals("")) {
                if (!f[8].equals(""))
                    query = query + " and s.date_checked_out like '" + f[7] + " " + f[8] + "%'";
                else
                    query = query + " and s.date_checked_out like '%" + f[7] + "%'";
            } else {
                if (!f[8].equals(""))
                    query = query + " and s.date_checked_out like '%" + f[8] + "%'";
            }
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            if (count == 1) {
                resultSet.beforeFirst();
                resultSet.next();
                for (int i = 0; i < 5; i++){
                    returnFields[i] = resultSet.getString(checkoutSQLcolumns[i]);
                }
                returnFields[5] = resultSet.getString(checkoutSQLcolumns[5]).substring(0,11);
                returnFields[6] = resultSet.getString(checkoutSQLcolumns[5]).substring(11);
                String returnDate = (String)resultSet.getString(checkoutSQLcolumns[6]);
                if (returnDate == null)
                    returnFields[7] = returnFields[8] = "";
                else {
                    //System.out.println(returnDate);
                    returnFields[7] = resultSet.getString(checkoutSQLcolumns[6]).substring(0,11);
                    returnFields[8] = resultSet.getString(checkoutSQLcolumns[6]).substring(11);
                }
            } else
                CommonDialogs.standardErrorMessage("Get checkout failed",count + " entries found, required: 1");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Get checkout broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
        return returnFields;
    }

    public static void update_maintenance(String[] f){
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            String query = "select * from maint_view where item_id = '" + f[1] + "' and date_maintainance_start = '" +f[3] + ' ' + f[4] + "'";
            String query1;
            //System.out.println(query);
            Statement stmt1 = conn.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            rs.next();
            PreparedStatement stmt;
            String dateOut = "";
            if (!f[3].equals("")){
                if (!f[4].equals("")){
                    if (f[4].length() == 5)
                        f[4] = f[4] + ":00";
                } else
                    f[4] = "00:00:00";
                dateOut = f[3] + ' ' + f[4];
            }
            if (!rs.getString(maintenanceSQLColumns[2]).equals(f[2])) {//maint description
                query1 = "update maintenance set " + maintenanceSQLColumns[2] + " = ? where item_id = '" + f[1] + "' and date_maintainance_start = '" + dateOut + "'";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[2]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (rs.getString(maintenanceSQLColumns[4]) == null && !f[5].equals("")) {//date returned
                if (!f[6].equals("")){
                    if (f[6].length() == 5)
                        f[6] = f[6] + ":00";
                } else
                    f[6] = "00:00:00";
                query1 = "update maintenance set " + maintenanceSQLColumns[4] + " = ? where item_id = '" + f[1] + "' and date_maintainance_start = '" + dateOut + "'";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1,f[5]+' '+f[6]);
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            CommonDialogs.standardMessageBox("Update successful","Maintenance record has been updated.");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Maintenance updates broke",ex.toString());
            ////System.out.println("java.sql.SQLException: Illegal operation on empty result set.");
        }   //if update nonexistent value(or change device id)
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void add_maintenance(String f[]){
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            String query3 = "select * from generic_item where item_id = " + f[1] + " and status = 'Available'";
            Statement stmt3 = conn.createStatement();
            //System.out.println(query3);
            ResultSet rs = stmt3.executeQuery(query3);
            int count = 0;
            while (rs.next()){
                count++;
            }
            if (count == 1) {
                String query = "insert into maintenance (item_id, description_of_maintenance, date_maintainance_start, date_maintainance_end) VALUES (?,?,?,?)";
                //System.out.println(query);
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, f[1]);
                stmt.setString(2, f[2]);
                String dateOut = "";
                String dateIn = "";
                if (!f[3].equals("")) {
                    if (!f[4].equals("")) {
                        if (f[4].length() == 5)
                            f[4] = f[4] + ":00";
                    } else
                        f[4] = "00:00:00";
                    dateOut = f[3] + ' ' + f[4];
                }
                if (!f[5].equals("")) {
                    if (!f[6].equals("")) {
                        if (f[6].length() == 5)
                            f[6] = f[6] + ":00";
                    } else
                        f[6] = "00:00:00";
                    dateIn = f[5] + ' ' + f[6];
                }
                stmt.setString(3, dateOut);
                if (f[5].equals(""))
                    stmt.setString(4, null);
                else {
                    stmt.setString(4, dateIn);
                }
                //System.out.println(stmt.toString());
                stmt.executeUpdate();
                CommonDialogs.standardMessageBox("Maintenance addition successful","Maintenance record has been added to the database.");
            } else
                CommonDialogs.standardErrorMessage("Error","Item isn't available to send out.");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Maintenance addition broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void updateMaintenanceGetter(JComboBox<String> j, String[] f){
        j.removeAllItems();
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from maint_view s where true";
            if (!f[0].equals(""))
                query = query + " and s.item_description like '%" + f[0] + "%'";
            if (!f[1].equals(""))
                query = query + " and s.item_id = '" + f[1] + "'";
            if (!f[2].equals(""))
                query = query + " and s.description_of_maintenance like '%" + f[2] + "%'";
            if (!f[3].equals("")) {
                if (!f[4].equals("")) {
                    if (f[4].length() == 5) {
                        f[4] = f[4] + ":00";
                    }
                    query = query + " and s.date_maintainance_start = '" + f[3] + ' ' + f[4] + "'";
                } else
                    query = query + " and s.date_maintainance_start like '%" + f[3] + "%'";
            } else {
                if (!f[4].equals(""))
                    query = query + " and s.date_maintainance_start like '%" + f[4] + "%'";
            }
            if (!f[5].equals("")) {
                if (!f[6].equals("")) {
                    if (f[6].length() == 5) {
                        f[6] = f[6] + ":00";
                    }
                    query = query + " and s.date_maintainance_end = '" + f[5] + ' ' + f[6] + "'";
                } else
                    query = query + " and s.date_maintainance_end like '%" + f[5] + "%'";
            } else {
                if (!f[6].equals(""))
                    query = query + " and s.date_maintainance_end like '%" + f[6] + "%'";
            }
            query = query + " order by date_maintainance_end";
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while ( resultSet.next())
            {
                String availability = "";
                if (resultSet.getString("date_maintainance_end") == null)
                    availability = "still out";
                else
                    availability = resultSet.getString("date_maintainance_end");
                String res = resultSet.getString("date_maintainance_start") + ' ' + resultSet.getString("item_id") + "   " + availability;
                j.addItem(res);
            }
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Maintenance combobox update broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static void get_maintenance_data(DefaultTableModel t, String f[]){
        t.setColumnCount(0);
        for (int i = 0; i < 5; i++)
            t.addColumn(maintenanceColumns[i]);
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from maint_view s where true";
            if (!f[0].equals(""))
                query = query + " and s.item_description like '%" + f[0] + "%'";
            if (!f[1].equals(""))
                query = query + " and s.item_id = '" + f[1] + "'";
            if (!f[2].equals(""))
                query = query + " and s.description_of_maintenance like '%" + f[2] + "%'";
            if (!f[3].equals("")) {
                if (!f[4].equals("")) {
                    if (f[4].length() == 5) {
                        f[4] = f[4] + ":00";
                    }
                    query = query + " and s.date_maintainance_start = '" + f[3] + ' ' + f[4] + "'";
                } else
                    query = query + " and s.date_maintainance_start like '%" + f[3] + "%'";
            } else {
                if (!f[4].equals(""))
                    query = query + " and s.date_maintainance_start like '%" + f[4] + "%'";
            }
            if (!f[5].equals("")) {
                if (!f[6].equals("")) {
                    if (f[6].length() == 5) {
                        f[6] = f[6] + ":00";
                    }
                    query = query + " and s.date_maintainance_end = '" + f[5] + ' ' + f[6] + "'";
                } else
                    query = query + " and s.date_maintainance_end like '%" + f[5] + "%'";
            } else {
                if (!f[6].equals(""))
                    query = query + " and s.date_maintainance_end like '%" + f[6] + "%'";
            }
            query = query + " order by " + f[7];
            //System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            String data[][] = new String[count][5];
            int j = 0;
            resultSet.beforeFirst();
            while ( resultSet.next())
            {
                for (int i = 0; i < 5; i++) {
                    data[j][i] = resultSet.getString(maintenanceSQLColumns[i]);
                }
                j++;
            }
            for (int i = 0; i < count; i++) {
                t.addRow(data[i]);
            }
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Maintenance selection broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
    }

    public static String[] get_single_maintenance_data(String f[]){
        String[] returnFields = {f[0],f[1],f[2],f[3],f[4],f[5],f[6]};
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "select * from maint_view s where true";
            if (!f[0].equals(""))
                query = query + " and s.item_description like '%" + f[0] + "%'";
            if (!f[1].equals(""))
                query = query + " and s.item_id = '" + f[1] + "'";
            if (!f[2].equals(""))
                query = query + " and s.description_of_maintenance like '%" + f[2] + "%'";
            if (!f[3].equals("")) {
                if (!f[4].equals("")) {
                    if (f[4].length() == 5) {
                        f[4] = f[4] + ":00";
                    }
                    query = query + " and s.date_maintainance_start = '" + f[3] + ' ' + f[4] + "'";
                } else
                    query = query + " and s.date_maintainance_start like '%" + f[3] + "%'";
            } else {
                if (!f[4].equals(""))
                    query = query + " and s.date_maintainance_start like '%" + f[4] + "%'";
            }
            if (!f[5].equals("")) {
                if (!f[6].equals("")) {
                    if (f[6].length() == 5) {
                        f[6] = f[6] + ":00";
                    }
                    query = query + " and s.date_maintainance_end = '" + f[5] + ' ' + f[6] + "'";
                } else
                    query = query + " and s.date_maintainance_end like '%" + f[5] + "%'";
            } else {
                if (!f[6].equals(""))
                    query = query + " and s.date_maintainance_end like '%" + f[6] + "%'";
            }
            //System.out.println(query);//COMMENT ALL THESE OUT, KEEP FOR FUTURE DEBUGGING
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            if (count == 1) {
                resultSet.beforeFirst();
                resultSet.next();
                for (int i = 0; i < 3; i++){
                    returnFields[i] = resultSet.getString(maintenanceSQLColumns[i]);
                }
                returnFields[3] = resultSet.getString(maintenanceSQLColumns[3]).substring(0,11);
                returnFields[4] = resultSet.getString(maintenanceSQLColumns[3]).substring(11);
                String returnDate = resultSet.getString(maintenanceSQLColumns[4]);
                if (returnDate == null)
                    returnFields[5] = returnFields[6] = "";
                else {
                    //System.out.println(returnDate);
                    returnFields[5] = resultSet.getString(maintenanceSQLColumns[4]).substring(0,11);
                    returnFields[6] = resultSet.getString(maintenanceSQLColumns[4]).substring(11);
                }
            } else
                CommonDialogs.standardErrorMessage("Get maintenance failed",count + "entries found, required: 1");
        }
        catch (SQLException ex)
        {
            CommonDialogs.standardErrorMessage("Get maintenance broke",ex.toString());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                { /* ignored */}
            }
        }
        return returnFields;
    }
}
