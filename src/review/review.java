package review;

import com.mysql.cj.jdbc.admin.MiniAdmin;

public class review {
    //инкапсуляция
    private  int a;
    private String name;

    public review(int a, String name) {
        this.a = a;
        this.name = name;
    }
    public void setName(String name, Object obj){
        if (obj instanceof Admin){
            this.name = name;
        }
        else {
            System.out.println("ты не админ, ты не можешь менять имя.");
        }
    }

    //полиморфизм
    public int count(int a){return 0;}
    public int count(int a, int c){return 0;}
    public int count(int a, double c){return 0;}

}

