/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbctestcs2016b;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author jeppjleemoritzled
 */
public class App
{
    private static SQLServerDataSource ds =
                new SQLServerDataSource();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        setupDataSource();
        
        printStudents();
        addStudent("Jeppe", "dsfdsf@fdkkkd.com", 12);
        printStudents();
    }
    
    private static void addStudent(String name, String email, int classid)
    {
        try(Connection con = ds.getConnection())
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
    
    private static boolean deleteStudent(int studentId)
    {
        // DELETE FROM Student WHERE id=981
        try(Connection con = ds.getConnection())
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
    
    private static void setupDataSource()
    {
        ds.setDatabaseName("School");
        ds.setUser("schooladmin");
        ds.setPassword("12345");
        ds.setPortNumber(1433);
        ds.setServerName("192.168.56.100");
    }
    
    private static void printStudents()
    {
        try(Connection con = ds.getConnection())
        {
            String query = "SELECT * FROM [Student] WHERE classid=12 ORDER BY name DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                System.out.print(rs.getString("id") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.println(rs.getString("classid"));
            }
        }
        catch(SQLException sqle)
        {
            System.err.println(sqle);
        }
    }
    
}
