package review;

public class review2 extends review {
    public review2(int a, String name) {
        super(a, name);
    }

    @Override
    public int count(int a) {
        return 5;
    }

    @Override
    public int count(int a, int c) {
        return a + c;
    }

    @Override
    public int count(int a, double c) {
        return a;
    }
}
