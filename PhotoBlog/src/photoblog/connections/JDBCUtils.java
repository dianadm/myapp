package photoblog.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Diana
 */
public class JDBCUtils {

    public String userName = "root";
    public String password = "";
    public String dbms = "mysql";
    public String serverName = "localhost";
    public int portNumber = 3306;
    public String dbName = "mydatabase";
    
    Logger logger = LoggerFactory.getLogger(JDBCUtils.class);

    private Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();

        if (this.dbms.equals("mysql")) {
            conn = DriverManager.getConnection(
                    "jdbc:" + this.dbms + "://"
                    + this.serverName
                    + ":" + this.portNumber + "/" + this.dbName,
                    connectionProps);
        } else if (this.dbms.equals("derby")) {
            conn = DriverManager.getConnection(
                    "jdbc:" + this.dbms + ":"
                    + this.dbName
                    + ";create=true",
                    connectionProps);
        }
        logger.info("Connected to database");
        return conn;
    }
    
    public void printAllUsers() {
        try {
            Connection conn = getConnection();
            PreparedStatement statement= conn.prepareStatement("SELECT * FROM USER");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String str1 = rs.getString(1);
                String str2 = rs.getString(2);
                String str3 = rs.getString(3);
                System.out.println("User = " + str1 +", "+str2+", "+str3);
            }
        } catch (SQLException ex) {
            logger.error("Error while selecting all users.", ex);
        }
        
    }
}
