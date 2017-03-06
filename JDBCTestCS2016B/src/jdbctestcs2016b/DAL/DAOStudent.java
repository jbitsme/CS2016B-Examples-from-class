/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbctestcs2016b.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jeppjleemoritzled
 */
public class DAOStudent
{
    SQLConnectionManager conManager;
    
    public DAOStudent()
    {
        conManager = new SQLConnectionManager();
    }
    
    public void addStudent(String name, String email, int classid)
    {
        try(Connection con = conManager.getConnection())
        {
            String sqlCommand =
            "INSERT INTO Student(name, email, classid) VALUES(?, ?, ?)";
            PreparedStatement pstat = con.prepareStatement(sqlCommand);
            pstat.setString(1, name);
            pstat.setString(2, email);
            pstat.setInt(3, classid);
            pstat.executeUpdate();
        }
        catch (SQLException sqle)
        {
            System.err.println(sqle);
        }
    }
    
    public boolean deleteStudent(int studentId)
    {
        // DELETE FROM Student WHERE id=981
        try(Connection con = conManager.getConnection())
        {
            String sqlCommand = 
                    "DELETE FROM Student WHERE Id=" + studentId;
            Statement stmt = con.createStatement();
            return stmt.execute(sqlCommand);
        }
        catch (SQLException sqle)
        {
            System.err.println(sqle);
            return false;
        }
        
    }
    
    public ArrayList<String> getStudents()
    {
        try(Connection con = conManager.getConnection())
        {
            String query = "SELECT * FROM [Student] WHERE classid=12 ORDER BY name DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<String> students = new ArrayList<>();
            while(rs.next())
            {
                String studentString = "";
                studentString += rs.getString("id") + " ";
                studentString +=rs.getString("name") + " ";
                studentString +=rs.getString("classid");
                students.add(studentString);
            }
            return students;
            
        }
        catch(SQLException sqle)
        {
            System.err.println(sqle);
            return null;
        }
    }

    public void updateStudent(
            int id, String name, String email, int classid)
    {
        try(Connection con = conManager.getConnection())
        {
            String sqlQuery =
            "UPDATE Student SET name=?,email=?,classid=? WHERE id=?";
            PreparedStatement pstmt =
               con.prepareStatement(sqlQuery);
            
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, classid);
            pstmt.setInt(4, id);
            
            pstmt.execute();
            
        }
        catch (SQLException sqle)
        {
            System.err.println(sqle);
        }
        
    }
}
