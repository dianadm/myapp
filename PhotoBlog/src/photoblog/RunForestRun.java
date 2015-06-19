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
