package review;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Generic<T extends review> {

    public void move(Good<? super Integer> good) {
    }

    public static void main(String[] args) {
        Generic<review> generic = new Generic<review>();
        List list1 = new ArrayList<ArrayList>();
    }

}
class Good<T>{

}

