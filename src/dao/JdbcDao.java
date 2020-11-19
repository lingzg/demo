package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcDao {

//    public static final String URL = "jdbc:mysql://47.112.221.30:3306/environmental_protection?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/spring?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String USER = "root";
//    public static final String PASSWORD = "Cecsys%secmax$#@!123456";
//    public static final String PASSWORD = "Zdka1qaz@WSX";
    public static final String PASSWORD = "root";
    
    private Connection conn;

    public JdbcDao() {
    	this(URL, USER, PASSWORD);
    }
    
    public JdbcDao(String url, String user, String pwd) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC drive class not found.",e);
        } catch (SQLException e) {
            throw new RuntimeException("connect database failed.",e);
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("close connection failed.",e);
        }
    }
    
    public PreparedStatement openStatement(String sql, Object... params) throws SQLException{
    	PreparedStatement stmt = conn.prepareStatement(sql);
        if(params != null && params.length > 0){
        	for(int i=0;i<params.length;i++){
        		stmt.setObject(i+1, params[i]);
        	}
        }
        return stmt;
    }
    
    public List<Map<String, Object>> query(String sql, Object... params) {
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        try {
        	PreparedStatement stmt = openStatement(sql, params);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            while(rs.next()){
                Map<String,Object> map = new HashMap<String,Object>();
                for(int i=0;i<md.getColumnCount();i++){
                    String name = md.getColumnName(i+1).toLowerCase();
                    map.put(name, rs.getObject(i+1));
                }
                result.add(map);
            }
        } catch (SQLException e) {
        	throw new RuntimeException("query failed.",e);
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
	public <T> List<T> find(String sql, Class<T> cls, Object... params) {
        List<T> result = new ArrayList<T>();
        try {
        	PreparedStatement stmt = openStatement(sql, params);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                result.add((T) rs.getObject(1));
            }
        } catch (SQLException e) {
        	throw new RuntimeException("query failed.",e);
        }
        return result;
    }
    
    public List<Object[]> find(String sql, Object... params) {
        List<Object[]> result = new ArrayList<Object[]>();
        try {
        	PreparedStatement stmt = openStatement(sql, params);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int len = md.getColumnCount();
            while(rs.next()){
            	Object[] array = new Object[len];
            	for(int i=0;i<len;i++){
            		array[i] = rs.getObject(i+1);
            	}
            	result.add(array);
            }
        } catch (SQLException e) {
        	throw new RuntimeException("query failed.",e);
        }
        return result;
    }
    
    public void print(String sql, int length, Object... params) {
        try {
        	PreparedStatement stmt = openStatement(sql, params);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
//            String position5 = String.format("%5s", a);   //表示 a 右对齐占用5个字符，不足的用空格补位
//            String position5 = String.format("%-5s", a);   //表示 a 左对齐占用5个字符，不足的用空格补位
            String fmt = new StringBuilder("%-").append(length).append("s").toString();
            for(int i=0;i<md.getColumnCount();i++){
                String name = md.getColumnName(i+1).toLowerCase();
                System.out.print(String.format(fmt, name));
            }
            System.out.println();
            while(rs.next()){
                for(int i=0;i<md.getColumnCount();i++){
                    System.out.print(String.format(fmt, rs.getObject(i+1)));
                }
                System.out.println();
            }
        } catch (SQLException e) {
        	throw new RuntimeException("query failed.",e);
        }
    }
    
    public int count(String sql, Object... params) {
        try {
        	PreparedStatement stmt = openStatement(sql, params);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
            	return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
        	throw new RuntimeException("query failed.",e);
        }
    }
    
    public int execute(String sql, Object... params) {
        try {
        	PreparedStatement stmt = openStatement(sql, params);
            int i = stmt.executeUpdate();
            return i;
        } catch (SQLException e) {
        	throw new RuntimeException("execute failed.",e);
        }
    }
    
    public int[] executeBatch(String sql, List<Object[]> paramsList) {
        try {
        	PreparedStatement stmt = conn.prepareStatement(sql);
            for(Object[] params : paramsList){
            	for(int i=0;i<params.length;i++){
            		stmt.setObject(i+1, params[i]);
            	}
            	stmt.addBatch();
            }
            int[] i = stmt.executeBatch();
            return i;
        } catch (SQLException e) {
        	throw new RuntimeException("execute failed.",e);
        }
    }
}
