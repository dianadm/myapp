package photoblog;

import org.hibernate.annotations.SourceType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import photoblog.entity.users.User;

import javax.swing.*;

/**
 * @author Diana
 */
public class RunForestRun {

    /*
    CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `SURNAME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `LOGIN` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOGIN` (`LOGIN`)
    )
     */
    private static void init(PhotoBlog blog) {
        User user = new User("Diana", "M", "dianadm@op.pl", "", "dianadm");
        blog.getMyUtils().getUtils().createNewUser(user);
    }

    public static void main(String[] args) {

        System.out.println("START");

        @SuppressWarnings("resources")
        ApplicationContext context = new ClassPathXmlApplicationContext("/beans.xml");

        PhotoBlog blog = (PhotoBlog) context.getBean("blog");
        blog.getMyUtils().getJdbcUtils().printUsersById(1);
        blog.getMyUtils().getUtils().stop();

        System.out.println("STOP");
    }
}
