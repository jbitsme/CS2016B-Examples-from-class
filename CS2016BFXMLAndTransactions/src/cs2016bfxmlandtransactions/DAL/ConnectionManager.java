/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016bfxmlandtransactions.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author jeppjleemoritzled
 */
public class ConnectionManager
{
    private final SQLServerDataSource ds =
            new SQLServerDataSource();

    public ConnectionManager()
    {
        setupDB();
    }
    
    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
    
    private void setupDB()
    {
        ds.setDatabaseName("Bank");
        ds.setUser("java");
        ds.setPassword("java");
        ds.setServerName("192.168.56.100");
        ds.setPortNumber(1433);
    }
}
