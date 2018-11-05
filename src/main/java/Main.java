import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
     //   D:\Java_web1\Task_3\src\main\resources\jdbc.properties

      /*  File file = new File("jdbc.properties");
        File file2 = new File("D:\\Java_web1\\Task_3\\src\\main\\java\\name.txt");
        System.out.println(file);
        System.out.println(file2.isFile());*/

        /*List rawList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        rawList = list;
            rawList.add(8);
        System.out.println(rawList.get(0));*/
        // TODO Почему так работает?
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        prop.load(inputStream);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        System.out.println(driver + " "+ url);
    }
}
