/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbctestcs2016b.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author jeppjleemoritzled
 */
public class SQLConnectionManager
{
    private SQLServerDataSource ds =
        new SQLServerDataSource();

    public SQLConnectionManager()
    {
        setupDataSource();
    }
    
    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
    
    private void setupDataSource()
    {
        ds.setDatabaseName("School");
        ds.setUser("schooladmin");
        ds.setPassword("12345");
        ds.setPortNumber(1433);
        ds.setServerName("192.168.56.100");
    }
}
