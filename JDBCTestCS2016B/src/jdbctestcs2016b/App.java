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
import java.util.ArrayList;
import jdbctestcs2016b.DAL.DAOStudent;
/**
 *
 * @author jeppjleemoritzled
 */
public class App
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        DAOStudent dm = new DAOStudent();
        
        //dm.setupDataSource();
        
        //printStudents();
        //addStudent("Peter Strgeer", "stegger@steg.com", 12);
        //printStudents();
        //dm.updateStudent(1008, "Peter Strgeer", "stegger@steg.com", 12);
        
        ArrayList<String> students = dm.getStudents();
        for (String student : students)
        {
            System.out.println(student);
        }
        
        //addStudent("Jeppe", "dsfdsf@fdkkkd.com", 12);
        //printStudents();
    }

    
}
