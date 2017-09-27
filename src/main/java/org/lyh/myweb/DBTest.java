package org.lyh.myweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;

/**
 * 
 */

/**
 * @author liuyuho
 *
 */
public class DBTest {

    /**
     * @param args
     * @throws DocumentException
     */
    public static void main(String[] args) throws Exception {
        testMySQLConnection();
    }

    public static void testMySQLConnection() throws SQLException {

        String url = "jdbc:mysql://10.18.27.217:3306/data-stage?useUnicode=true&amp;characterEncoding=utf8";
        String username = "root";
        String password = "root";

        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            // Load the JDBC driver
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(" -> Connection built ....");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver " + url + " ,Please check your action");
            e.printStackTrace();
            connection = null;
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + url + " ,Please check network");
            connection = null;
        }

        String sql = "select user()";
        try {
            psmt = connection.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs != null && rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw sqlEx;
        } finally {
            if (rs != null) {
                rs.close();
            }
            psmt.close();
            connection.close();
        }
    }

    public static void testMycatReloadCommand() {
        //        String url = "jdbc:mysql://127.0.0.1:9066/mycat?useUnicode=true&amp;characterEncoding=UTF-8";
        String url = "jdbc:mysql://192.168.116.128:9066";
        String username = "test";
        String password = "test";

        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            // Load the JDBC driver
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(" -> Connection built ....");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver " + url + " ,Please check your action");
            e.printStackTrace();
            connection = null;
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + url + " ,Please check network");
            connection = null;
        }

        try {
            String sql = "reload @@config_all";
            psmt = connection.prepareStatement(sql);
            psmt.execute();
            System.out.println("All config reload successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                psmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void testMycatQuery() throws SQLException {
        //        String url = "jdbc:mysql://127.0.0.1:9066/mycat?useUnicode=true&amp;characterEncoding=UTF-8";
        String url = "jdbc:mysql://192.168.116.128:8066/TESTDB";
        String username = "test";
        String password = "test";

        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            // Load the JDBC driver
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(" -> Connection built ....");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver " + url + " ,Please check your action");
            e.printStackTrace();
            connection = null;
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + url + " ,Please check network");
            connection = null;
        }

        String sql = "select user()";
        try {
            psmt = connection.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs != null && rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw sqlEx;
        } finally {
            if (rs != null) {
                rs.close();
            }
            psmt.close();
            connection.close();
        }
    }

    public static void testMYSQLConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/xntscust?useUnicode=true&amp;characterEncoding=UTF-8";
        String username = "root";
        String password = "xiaoniu@2016";

        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            // Load the JDBC driver
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(" -> Connection built ....");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver " + url + " ,Please check your action");
            e.printStackTrace();
            connection = null;
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + url + " ,Please check network");
            connection = null;
        }

        List<String> list = new ArrayList<String>();
        list.add("0");
        list.add("1");
        list.add("2");

        String sql = "SELECT code_name FROM xntscust.t_sys_code where code_type in (";
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                sql += "?,";
            } else {
                sql += "?";
            }
        }
        sql += ")";
        System.out.println("SQL = " + sql);
        try {
            psmt = connection.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++) {
                psmt.setString(i + 1, list.get(i));
            }
            rs = psmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("code_name"));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw sqlEx;
        } finally {
            rs.close();
            psmt.close();
            connection.close();
        }

    }

    public static void testOracleConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@//10.18.27.15:1521/dzzwdb";//service_name
//        String url = "jdbc:oracle:thin:@10.18.27.15:1521:dzzwdb";//sid
        String username = "legal";
        String password = "123456";
        String sql = "select 1 from dual";

        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            // Load the JDBC driver
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(" -> Connection built ....");

            psmt = connection.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                psmt.close();
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}