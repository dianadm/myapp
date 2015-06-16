package photoblog;

import photoblog.connections.HibernateUtils;
import photoblog.connections.JDBCUtils;

/**
 *
 * @author Diana
 */
public class MyUtils {
    
    private HibernateUtils utils;
    private JDBCUtils jdbcUtils;

    public MyUtils() {
    }

    public MyUtils(HibernateUtils utils,JDBCUtils jdbcUtils) {
        this.utils = utils;
        this.jdbcUtils = jdbcUtils;
    }

    public HibernateUtils getUtils() {
        return utils;
    }

    public JDBCUtils getJdbcUtils() {
        return jdbcUtils;
    }

}
