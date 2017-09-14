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

    public static boolean flag = false;

    /**
     * @param args
     * @throws DocumentException
     */
    public static void main(String[] args) throws Exception {
        testMycatConnection();
    }

    public static void testMycatConnection() throws SQLException {
//        String url = "jdbc:mysql://127.0.0.1:9066/mycat?useUnicode=true&amp;characterEncoding=UTF-8";
        String url = "jdbc:mysql://127.0.0.1:9066";
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

            String sql = "show @@help";
            try {
                psmt = connection.prepareStatement(sql);
                rs = psmt.executeQuery();

                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
                throw sqlEx;
            } finally {
                rs.close();
                psmt.close();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver " + url + " ,Please check your action");
            e.printStackTrace();
            connection = null;
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + url + " ,Please check network");
            connection = null;
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

}