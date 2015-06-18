package photoblog.connections;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import photoblog.MyConfiguration;

/**
 * @author Diana
 */
public class JDBCUtils {

    // slf4j
    private static Logger logger = LoggerFactory.getLogger(JDBCUtils.class);
    private MyConfiguration configuration;

    public JDBCUtils() {
        configuration = loadConf();
    }

    public MyConfiguration loadConf() {

        MyConfiguration configuration = new MyConfiguration();
        Properties props = null;
        try {
            props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("myconf.properties"));
            configuration = new MyConfiguration(props);
        } catch (IOException e) {
            logger.error("Error while reading configuration.", e);
        }

        return configuration;
    }

    private Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();

        if (configuration.getDbms().equals("mysql")) {
            conn = DriverManager.getConnection(
                    "jdbc:" + configuration.getDbms() + "://"
                            + configuration.getServerName()
                            + ":" + configuration.getPortNumber()
                            + "/" + configuration.getDbName()
                            + "?user=" + configuration.getUserName()
                            + "&password=" + configuration.getPassword(),
                    connectionProps);
        } else if (configuration.getDbms().equals("derby")) {
            conn = DriverManager.getConnection(
                    "jdbc:" + configuration.getDbms() + ":"
                            + configuration.getDbName()
                            + ";create=true",
                    connectionProps);
        }
        logger.info("Connected to database");
        return conn;
    }

    public void printAllUsers() {
        try {
            Connection conn = getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM USER");
            while (rs.next()) {
                String str1 = rs.getString(1);
                String str2 = rs.getString(2);
                String str3 = rs.getString(3);
                System.out.println("User = " + str1 + ", " + str2 + ", " + str3);
            }
        } catch (SQLException ex) {
            logger.error("Error while selecting all users.", ex);
        }

    }

    public void printUsersById(int id) {
        try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM USER WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String str1 = rs.getString(1);
                String str2 = rs.getString(2);
                String str3 = rs.getString(3);
                System.out.println("User = " + str1 + ", " + str2 + ", " + str3);
            }
        } catch (SQLException ex) {
            logger.error("Error while selecting all users.", ex);
        }
    }
}
