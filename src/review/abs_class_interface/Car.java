package review.abs_class_interface;

public class Car extends Machine implements Operator {
    @Override
    public void gas() {
        System.out.println("Car газует быстро!");
    }

    @Override
    public void brake() {
        System.out.println("Car тормозит быстро!");
    }

    @Override
    public void turn() {
        System.out.println("Оператор тебе надо очень легко управлять машиной. Все делай плавно, без резких движений");
    }

    @Override
    public void signalOn() {
        System.out.println("Для того чтобы сделать сигнал, надо нажать на 5-ую кнопку справа от 2-го тумблера слева.");
    }
}
