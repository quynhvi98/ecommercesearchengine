/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.mvc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author viquy
 */
public class LoginBean implements Serializable{
    private String username;
    private String lastname;
    private boolean roles;
    
    public LoginBean(){}
    public boolean checkLogin(String username, String password){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student,instanceName=SQL2012";
            Connection con = DriverManager.getConnection(url, "sa","123456");
            String sql = "Select * from Registrtion where username = ? and password = ?";
            PreparedStatement stm= con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            boolean result = rs.next();
            rs.close();
            stm.close();
            con.close();
            if(result){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean insert(String username, String password, String lastname, boolean roles){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student,instanceName=SQL2012";
            Connection con = DriverManager.getConnection(url, "sa","123456");
            String sql = "Insert into Registration(username, password, lastname, isAdmin)"+"Values(?,?,?,?)";
            PreparedStatement stm= con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, lastname);
            stm.setBoolean(4, roles);
            int result = stm.executeUpdate();
            stm.close();
            con.close();
            if(result>0){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public LoginBean[] searchLikeLastname(String name){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student,instanceName=SQL2012";
            Connection con = DriverManager.getConnection(url, "sa","123456");
            String sql = "Select * from Registration Where lastname Like ?";
            PreparedStatement stm= con.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
           
            ResultSet rs = stm.executeQuery();
            ArrayList list = new ArrayList();
            while(rs.next()){
                String user = rs.getString("username");
                String last = rs.getString("lastname");
                boolean roles = rs.getBoolean("isAdmin");
                LoginBean tmp = new LoginBean(user, last, roles);
                list.add(tmp);
            }
            LoginBean[] result = new LoginBean[list.size()];
            list.toArray(result);
            rs.close();
            stm.close();
            con.close();
             return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean deleteRecord(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student,instanceName=SQL2012";
            Connection con = DriverManager.getConnection(url, "sa","123456");
            String sql = "Delete from Registration Where username = ?";
            PreparedStatement stm= con.prepareStatement(sql);
            stm.setString(1, username);
            int result = stm.executeUpdate();
            stm.close();
            con.close();
            if(result>0){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateRecord(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Student,instanceName=SQL2012";
            Connection con = DriverManager.getConnection(url, "sa","123456");
            String sql = "Update Register Set lastname=?, isAdmin=? where username=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, lastname);
            stm.setBoolean(2, roles);
            stm.setString(3, username);
            int result = stm.executeUpdate();
            stm.close();
            con.close();
            if(result>0){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the roles
     */
    public boolean isRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(boolean roles) {
        this.roles = roles;
    }

    public LoginBean(String username, String lastname, boolean roles) {
        this.username = username;
        this.lastname = lastname;
        this.roles = roles;
    }
    
}
