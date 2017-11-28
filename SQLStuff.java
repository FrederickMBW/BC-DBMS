package com.company;

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


    public static void update_student(String[] f){
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            String query = "select * from students_view where sid = '" + f[0] + "'";
            String query1 = "update student set " + studentSQLcolumnNames[9] + " = ? where sid = ?";
            String query2 = "update BC_Member set " + studentSQLcolumnNames[1] + " = ? where sid = ?";
            System.out.println(query);
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
                stmt.setString(1, f[15] + "-01-01");
                stmt.setString(2, f[0]);
                stmt.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            System.out.println("student updates failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
            System.out.println(query1);
            System.out.println(query2);
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
            stmt.setString(5,f[12] + "-01-01");
            stmt.setString(6,f[13]);
            stmt.setString(7,"1");
            stmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println("student addition failed");//*************************************************************************************************************************************
            System.out.println(ex);
            if (bcMemberAdded) {
                try {
                    String query2 = "delete from bc_member where sid = ?";
                    PreparedStatement stmt = conn.prepareStatement(query2);
                    stmt.setString(1, f[0]);
                    System.out.println(query2);
                    stmt.executeUpdate();

                } catch (SQLException e) {
                    System.out.println("deletion of extra failed");
                    System.out.println(e);
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
                    query = query + " and s.dob = '" + f[12] + "-01-01'";
                else
                    query = query + " and s.dob = '" + f[12] + "'";
            }
            if (!f[13].equals(""))
                query = query + " and s.advisor like '%" + f[13] + "%'";
            if (!f[14].equals("")) {
                if (f[14].length() == 4)
                    query = query + " and s.grad_date = '" + f[14] + "-01-01'";
                else
                    query = query + " and s.grad_date = '" + f[14] + "'";
            }
            if (!f[15].equals("no"))
                query = query + " and s.currently_enrolled = true";
            else
                query = query + " and s.currently_enrolled = false";
            query += " order by namelast";
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while ( resultSet.next())
            {
                String res = resultSet.getString("sid") + ' ' + resultSet.getString("namelast") + ' ' + resultSet.getString("namefirst");
                j.addItem(res);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("student combobox update failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
                    query = query + " and s.grad_date < '" + f[12] + "-01-01 00:00:00'";
            //}
            if (!f[13].equals("no"))
                query = query + " and s.currently_enrolled = true";
            else
                query = query + " and s.currently_enrolled = false";
            query = query + " order by " + f[14];
            System.out.println(query);
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
            System.out.println("student selection failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
            System.out.println(query);
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
                System.out.println("HERE IS WHERE ERROR MESSAGE BOX GOES. " + count + "matches found");//*************************************************************************************************************************************
        }
        catch (SQLException ex)
        {
            System.out.println("single student selection failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
            System.out.println(query);
            Statement stmt1 = conn.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            rs.next();
            PreparedStatement stmt;
            if (!rs.getString(devicesSQLcolumns[1]).equals(f[1])) {//"Device ID",
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[1]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(devicesSQLcolumns[2]).equals(f[2])) {//"Item type",
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[2]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(devicesSQLcolumns[3]).equals(f[3]) ){//"Status",
                query2 = "update generic_item set " + devicesSQLcolumns[3] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[3]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(devicesSQLcolumns[4]).equals(f[4]) ){//"Model Number",
                query1 = "update devices set " + devicesSQLcolumns[4] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[4]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(devicesSQLcolumns[5]).equals(f[5]) && !f[5].equals("")) {//"Price",
                query2 = "update generic_item set " + devicesSQLcolumns[5] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[5]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(devicesSQLcolumns[6]).equals(f[6])) {//"Date of purchase"
                query2 = "update generic_item set " + devicesSQLcolumns[6] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query2);
                if (f[6].length() == 4)
                    stmt.setString(1, f[6] + "-01-01");
                else
                    stmt.setString(1,f[6]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            System.out.println("device updates failed");//*************************************************************************************************************************************
            System.out.println(ex);
            //System.out.println("java.sql.SQLException: Illegal operation on empty result set.");
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

            System.out.println(query1);

            PreparedStatement stmt = conn.prepareStatement(query1);
            stmt.setString(1,f[2]);
            if (f[3].equals("All"))
                f[3] = "Available";
            stmt.setString(2,f[3]);
            if (f[5].equals(""))
                f[5] = "0";
            stmt.setString(3,f[5]);
            if (f[6].length() == 4)
                stmt.setString(4,f[6] + "-01-01");
            else if (f[6].length() == 0)
                stmt.setString(4,"0000-01-01");
            else
                stmt.setString(4,f[6]);
            stmt.executeUpdate();
            genericItemAdded = true;
            String query3 = "select * from generic_item where item_id = (select max(item_id) from generic_item)";
            Statement stmt2 = conn.createStatement();
            System.out.println(query3);
            ResultSet rs = stmt2.executeQuery(query3);
            rs.next();
            stmt = conn.prepareStatement(query2);
            stmt.setString(1,rs.getString("item_id"));
            stmt.setString(2,f[1]);
            stmt.setString(3,f[4]);
            System.out.println(query2);
            stmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println("device addition failed");//*************************************************************************************************************************************
            System.out.println(ex);
            if (genericItemAdded) {
                try {
                    String query2 = "delete from generic_item where item_id = ?";
                    String query3 = "select * from generic_item where item_id = (select max(item_id) from generic_item)";
                    Statement stmt2 = conn.createStatement();
                    System.out.println(query3);
                    ResultSet rs = stmt2.executeQuery(query3);
                    rs.next();
                    PreparedStatement stmt = conn.prepareStatement(query2);
                    stmt.setString(1, rs.getString("item_id"));
                    System.out.println(query2);
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("deletion of extra failed");
                    System.out.println(e);
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
                    query = query + " and s.date_of_purchase = '" + f[6] + "-01-01'";
                else
                    query = query + " and s.date_of_purchase = '" + f[6] + "'";
            }
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while ( resultSet.next())
            {
                String res = resultSet.getString("device_id") + ' ' + resultSet.getString("item_description");
                j.addItem(res);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("device combobox update failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
                    query = query + " and s.date_of_purchase = '" + f[7] + "-01-01'";
                else
                    query = query + " and s.date_of_purchase = '" + f[7] + "'";
            }
            query = query + " order by " + f[8];
            System.out.println(query);
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
            System.out.println("devices selection failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
                    query = query + " and s.date_of_purchase = '" + f[6] + "-01-01'";
                else
                    query = query + " and s.date_of_purchase = '" + f[6] + "'";
            }
            System.out.println(query);
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
                System.out.println("HERE IS WHERE ERROR MESSAGE BOX GOES. " + count + "matches found");//*************************************************************************************************************************************
        }
        catch (SQLException ex)
        {
            System.out.println("single device selection failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
            System.out.println(query);
            Statement stmt1 = conn.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            rs.next();
            PreparedStatement stmt;
            if (!rs.getString(booksSQLcolumns[1]).equals(f[1])) {//book id
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[1]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[2]).equals(f[2])) {//title
                query1 = "update books set " + booksSQLcolumns[2] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[2]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[3]).equals(f[3]) ){//status
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[3]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[4]).equals(f[4]) ){//item description
                query2 = "update generic_item set " + booksSQLcolumns[4] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[4]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[5]).equals(f[5]) ){//edition
                query1 = "update books set " + booksSQLcolumns[5] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[5]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[6]).equals(f[6]) ){//book type
                query1 = "update books set " + booksSQLcolumns[6] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[4]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[7]).equals(f[7]) ){//isbn
                query1 = "update books set " + booksSQLcolumns[7] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[7]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[8]).equals(f[8]) ){//condition
                query1 = "update books set " + booksSQLcolumns[8] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[8]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[9]).equals(f[9]) && !f[9].equals("")) {//price
                query2 = "update generic_item set " + booksSQLcolumns[9] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query2);
                stmt.setString(1, f[9]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(booksSQLcolumns[10]).equals(f[10])) {//dop
                query2 = "update generic_item set " + booksSQLcolumns[10] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query2);
                if (f[6].length() == 4)
                    stmt.setString(1, f[10] + "-01-01");
                else
                    stmt.setString(1,f[10]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            System.out.println("book updates failed");//*************************************************************************************************************************************
            System.out.println(ex);
            //System.out.println("java.sql.SQLException: Illegal operation on empty result set.");
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

            System.out.println(query1);

            PreparedStatement stmt = conn.prepareStatement(query1);
            stmt.setString(1,f[3]);
            if (f[3].equals("All"))
                f[3] = "Available";
            stmt.setString(2,f[4]);
            if (f[9].equals(""))
                f[9] = "0";
            stmt.setString(3,f[9]);
            if (f[10].length() == 4)
                stmt.setString(4,f[10] + "-01-01");
            else if (f[6].length() == 0)
                stmt.setString(4,"0000-01-01");
            else
                stmt.setString(4,f[10]);
            stmt.executeUpdate();
            genericItemAdded = true;
            String query3 = "select * from generic_item where item_id = (select max(item_id) from generic_item)";
            Statement stmt2 = conn.createStatement();
            System.out.println(query3);
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
            System.out.println(query2);
            stmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println("book addition failed");//*************************************************************************************************************************************
            System.out.println(ex);
            if (genericItemAdded) {
                try {
                    String query2 = "delete from generic_item where item_id = ?";
                    String query3 = "select * from generic_item where item_id = (select max(item_id) from generic_item)";
                    Statement stmt2 = conn.createStatement();
                    System.out.println(query3);
                    ResultSet rs = stmt2.executeQuery(query3);
                    rs.next();
                    PreparedStatement stmt = conn.prepareStatement(query2);
                    stmt.setString(1, rs.getString("item_id"));
                    System.out.println(query2);
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("deletion of extra failed");
                    System.out.println(e);
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
                    query = query + " and s.date_of_purchase = '" + f[10] + "-01-01'";
                else
                    query = query + " and s.date_of_purchase = '" + f[10] + "'";
            }
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while ( resultSet.next())
            {
                String res = resultSet.getString("book_id") + ' ' + resultSet.getString("title");
                j.addItem(res);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("book combobox update failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
                    query = query + " and s.date_of_purchase = '" + f[11] + "-01-01'";
                else
                    query = query + " and s.date_of_purchase = '" + f[11] + "'";
            }
            query = query + " order by " + f[12];
            System.out.println(query);
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
            System.out.println("books selection failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
                    query = query + " and s.date_of_purchase = '" + f[10] + "-01-01'";
                else
                    query = query + " and s.date_of_purchase = '" + f[10] + "'";
            }
            System.out.println(query);
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
                System.out.println("HERE IS WHERE ERROR MESSAGE BOX GOES. " + count + "matches found");//*************************************************************************************************************************************
        }
        catch (SQLException ex)
        {
            System.out.println("single book selection failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
            System.out.println(query);
            Statement stmt1 = conn.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            rs.next();
            PreparedStatement stmt;
            if (!rs.getString(genericItemSQLcolumns[1]).equals(f[1])) {//item description
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[1]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(genericItemSQLcolumns[2]).equals(f[2])) {//status
                query1 = "update generic_item set " + genericItemSQLcolumns[2] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[2]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(genericItemSQLcolumns[3]).equals(f[3]) && !f[3].equals("")) {//price
                query1 = "update generic_item set " + genericItemSQLcolumns[3] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[3]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(genericItemSQLcolumns[4]).equals(f[4]) && !f[4].equals("")) {//dop
                query1 = "update generic_item set " + genericItemSQLcolumns[4] + " = ? where item_id = ?";
                stmt = conn.prepareStatement(query1);
                if (f[4].length() == 4)
                    stmt.setString(1, f[4] + "-01-01");
                else
                    stmt.setString(1,f[4]);
                stmt.setString(2, f[0]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            System.out.println("generic item updates failed");//*************************************************************************************************************************************
            System.out.println(ex);
            //System.out.println("java.sql.SQLException: Illegal operation on empty result set.");
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

    public static void add_generic_item(String f[]){
        Connection conn = null;
        try
        {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, username, password);
            String query = "insert into generic_item (item_id, item_description,status, price, date_of_purchase) VALUES (0,?,?,?,?)";

            System.out.println(query);

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,f[1]);
            if (f[2].equals("All"))
                f[2] = "Available";
            stmt.setString(2,f[2]);
            if (f[3].equals(""))
                f[3] = "0";
            stmt.setString(3,f[3]);
            if (f[4].length() == 4)
                stmt.setString(4,f[4] + "-01-01");
            else if (f[4].length() == 0)
                stmt.setString(4,"0000-01-01");
            else
                stmt.setString(4,f[4]);
            stmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println("generic item addition failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
                query = query + " and s.item_id = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.item_description like '%" + f[1] + "%'";
            if (!f[2].equals("All"))
                query = query + " and s.status = '" + f[2] + "'";
            if (!f[3].equals(""))
                query = query + " and s.price = '" + f[9] + "'";
            if (!f[4].equals("")) {
                if (f[4].length() == 4)
                    query = query + " and s.date_of_purchase = '" + f[4] + "-01-01'";
                else
                    query = query + " and s.date_of_purchase = '" + f[4] + "'";
            }
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while ( resultSet.next())
            {
                String res = resultSet.getString("item_id") + "   " + resultSet.getString("item_description");
                j.addItem(res);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("generic item combobox update failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
                query = query + "and s.item_id = '" + f[0] + "'";
            if (!f[1].equals(""))
                query = query + " and s.item_description like '%" + f[1] + "%'";
            if (!f[2].equals("All"))
                query = query + " and s.status = '" + f[2] + "'";
            if (!f[4].equals(""))
                query = query + " and s.price " + f[3] + " " + f[4];
            if (!f[5].equals("")) {
                if (f[5].length() == 4)
                    query = query + " and s.date_of_purchase = '" + f[5] + "-01-01'";
                else
                    query = query + " and s.date_of_purchase = '" + f[5] + "'";
            }
            query = query + " order by " + f[6];
            System.out.println(query);
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
            System.out.println("generic item selection failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
                    query = query + " and s.date_of_purchase = '" + f[4] + "-01-01'";
                else
                    query = query + " and s.date_of_purchase = '" + f[4] + "'";
            }
            System.out.println(query);
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
                System.out.println("HERE IS WHERE ERROR MESSAGE BOX GOES. " + count + "matches found");//*************************************************************************************************************************************
        }
        catch (SQLException ex)
        {
            System.out.println("single generic item selection failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
            String query1 = "update checking_out_item set " + checkoutSQLcolumns[1] + " = ? where sid = '" + f[0] + "' and item_id = '" + f[4] + "' and date_checked_out = '" + f[5] + ' ' + f[6] + "'";
            System.out.println(query);
            Statement stmt1 = conn.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            rs.next();
            PreparedStatement stmt;
            if (!rs.getString(genericItemSQLcolumns[1]).equals(f[1])) {//fname
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[1]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(genericItemSQLcolumns[2]).equals(f[2])) {//lname
                query1 = "update checking_out_item set " + checkoutSQLcolumns[2] + " = ? where sid = '" + f[0] + "' and item_id = '" + f[4] + "' and date_checked_out = '" + f[5] + ' ' + f[6] + "'";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[2]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(genericItemSQLcolumns[3]).equals(f[3])) {//item description
                query1 = "update checking_out_item set " + checkoutSQLcolumns[3] + " = ? where sid = '" + f[0] + "' and item_id = '" + f[4] + "' and date_checked_out = '" + f[5] + ' ' + f[6] + "'";
                stmt = conn.prepareStatement(query1);
                stmt.setString(1, f[3]);
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
            if (!rs.getString(genericItemSQLcolumns[4]).equals(f[4]) && !f[7].equals("")) {//date returned
                query1 = "update checking_out_item set " + checkoutSQLcolumns[6] + " = ? where sid = '" + f[0] + "' and item_id = '" + f[4] + "' and date_checked_out = '" + f[5] + ' ' + f[6] + "'";
                stmt = conn.prepareStatement(query1);
                if (!f[8].equals(""))
                    stmt.setString(1,f[7]+' '+f[8]);
                else
                    stmt.setString(1, f[4] + " 00:00:00");
                System.out.println(stmt.toString());
                stmt.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            System.out.println("checkout updates failed");//*************************************************************************************************************************************
            System.out.println(ex);
            //System.out.println("java.sql.SQLException: Illegal operation on empty result set.");
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
            String query = "insert into checking_out_item (sid, date_checked_out, date_returned,item_id) VALUES (?,?,?,?)";
            System.out.println(query);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,f[0]);
            if (f[6].length() == 5)
                f[6] = f[6] + ":00";
            stmt.setString(2,f[5] + ' ' + f[6]);
            if (f[7].equals(""))
                stmt.setString(3,null);
            else {
                if (!f[8].equals("")) {
                    if (f[8].length() == 5)
                        f[8] = f[8] + ":00";
                } else
                    f[8] = "00:00:00";
                stmt.setString(3,f[7] + ' ' + f[8]);
            }
            stmt.setString(4,f[3]);
            stmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println("checkout addition failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
            String query = "select * from checkout s where true";
            if (!f[0].equals(""))
                query = query + "and s.sid = '" + f[0] + "'";
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
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while ( resultSet.next())
            {
                String res = resultSet.getString("sid") + ' ' + resultSet.getString("item_description") + ' ' + resultSet.getString("date_checked_out").substring(0,11);
                j.addItem(res);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("checkout combobox update failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
            System.out.println("this one, right?");
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
            query = query + " order by " + f[9];
            System.out.println(query);
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
            System.out.println("checkout selection failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
                query = query + "and s.sid = '" + f[0] + "'";
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
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            int count = 0;
            while ( resultSet.next() )
            {
                count++;
            }
            if (count == 1) {
                resultSet.beforeFirst();
                resultSet.next();
                for (int i = 0; i < 9; i++){
                    returnFields[i] = resultSet.getString(checkoutSQLcolumns[i]);
                }
            } else
                System.out.println("HERE IS WHERE ERROR MESSAGE BOX GOES. " + count + "matches found");//*************************************************************************************************************************************
        }
        catch (SQLException ex)
        {
            System.out.println("single checkout selection failed");//*************************************************************************************************************************************
            System.out.println(ex);
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
