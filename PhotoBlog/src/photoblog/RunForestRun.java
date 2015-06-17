package photoblog;

import org.hibernate.annotations.SourceType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;

/**
 *
 * @author Diana
 */
public class RunForestRun {

       public static void main(String[] args) {

           System.out.println("START");

           @SuppressWarnings("resources")
           ApplicationContext context = new ClassPathXmlApplicationContext("/beans.xml");
           
           PhotoBlog blog = (PhotoBlog) context.getBean("blog");
           blog.getMyUtils().getJdbcUtils().printAllUsers();
           blog.getMyUtils().getUtils().stop();

           System.out.println("STOP");
       }
}
