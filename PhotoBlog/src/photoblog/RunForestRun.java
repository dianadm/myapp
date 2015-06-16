package photoblog;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Diana
 */
public class RunForestRun {

       public static void main(String[] args) {
           
           @SuppressWarnings("resources")
           ApplicationContext context = new ClassPathXmlApplicationContext("/beans.xml");
           
           PhotoBlog blog = (PhotoBlog) context.getBean("blog");
           blog.getMyUtils().getJdbcUtils().printAllUsers();
           blog.getMyUtils().getUtils().stop();
       }
}
