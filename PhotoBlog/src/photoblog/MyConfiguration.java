package photoblog;

/**
 * Created by dmigasiuk on 2015-06-17.
 */
public class MyConfiguration {

    private String userName = "root";
    private String password = "haslo";
    private String dbms = "mysql";
    private String serverName = "localhost";
    private int portNumber = 3306;
    private String dbName = "mydatabase";

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
