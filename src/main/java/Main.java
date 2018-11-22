package main.java;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Main {
    public static void main(String[] args) throws IOException {
     //   D:\Java_web1\Task_3\src\main\resources\config.properties

       // File file = new File("config.properties");
       // File file2 = new File("D:\\Java_web1\\Task_3\\src\\main\\resources\\config.properties");


        /*List rawList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        rawList = list;
            rawList.add(8);
        System.out.println(rawList.get(0));*/
        // TODO Почему так работает?Hekllo mne


        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        prop.load(inputStream);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        System.out.println(driver + " "+ url);
    }
}
