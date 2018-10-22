package main.java;

//синглтон, у него есть два метода getConnection и getConfiguration
// которые отдают Connection для jdbc dao и Configuration для hibernatedao соотвтетственно
public class DBHelper {
    private static DBHelper ourInstance = new DBHelper();

    public static DBHelper getInstance() {
        return ourInstance;
    }
    //здесь вместо воид надо что то поставить для JDBC
    public static void getConnection(){}
    //здесь вместо воид надо что то поставить для HIBERNATE
    //конфигурация hibernete в коде
    public static void getConfiguration (){}

    private DBHelper() {
    }
}
