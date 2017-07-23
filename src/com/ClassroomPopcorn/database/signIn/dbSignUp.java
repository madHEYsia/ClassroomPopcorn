package com.ClassroomPopcorn.database.signIn;

import com.ClassroomPopcorn.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbSignUp {

    public static String[] userSignUp(String fullName,String userName,String emailId,String password){
        Connection con = null;

        PreparedStatement stmt = null;
        String query = DBUtils.prepareInsertQuery("classroompopcorn.userdetail", "fullName, username, emailId, password", "?,?,?,?");

        String[] status = {"ongoing","username"};

        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, fullName);
            stmt.setString(2, userName);
            stmt.setString(3, emailId);
            stmt.setString(4, password);
            stmt.executeUpdate();
            status[0]="success";
            status[1]=fullName;
        }
        catch(Exception e){
            status[0] = e.getMessage();
        }
        finally{
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
            return status;
        }
    }

}