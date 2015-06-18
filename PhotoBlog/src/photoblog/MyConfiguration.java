package photoblog;

import java.util.Properties;

public class MyConfiguration {

    private String userName = "root";
    private String password = "haslo";
    private String dbms = "mysql";
    private String serverName = "localhost";
    private int portNumber = 3306;
    private String dbName = "mydatabase";

    public MyConfiguration() {
    }

    public MyConfiguration(Properties props) {
        this.userName = props.getProperty("USERNAME");
        this.password = props.getProperty("PASSWORD");
        this.dbms = props.getProperty("DBMS");
        this.serverName = props.getProperty("SERVERNAME");
        this.portNumber = Integer.parseInt(props.getProperty("PORT"));
        this.dbName = props.getProperty("DBNAME");
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDbms() {
        return dbms;
    }

    public String getServerName() {
        return serverName;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getDbName() {
        return dbName;
    }
}
