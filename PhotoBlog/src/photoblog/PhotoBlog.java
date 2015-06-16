package photoblog;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Diana
 */
public class PhotoBlog {

    private MyUtils myUtils;
    
//    public PhotoBlog() {
//        @SuppressWarnings("resource")
//        ApplicationContext context = new ClassPathXmlApplicationContext("/beans.xml");
//        this.myUtils = (MyUtils) context.getBean(MyUtils.class);
//    }

    public PhotoBlog() {
        
    }
    
    @Autowired
    public PhotoBlog(MyUtils myUtils) {
        this.myUtils = myUtils;
    }
    
    public MyUtils getMyUtils() {
        return myUtils;
    }

}
