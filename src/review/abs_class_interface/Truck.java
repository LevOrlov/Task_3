package review.abs_class_interface;

public class Truck extends Machine implements Movable {
    @Override
    public void gas() {
        System.out.println("Truck газует медленоо!");
    }

    @Override
    public void brake() {
        System.out.println("Truck тормозит медленно!");
    }

    @Override
    public void turn() {
        System.out.println("Оператор это Урал 1982 года выпуска. " +
                           "Ты не волнуйся, здесь все также как и на тракторе. Все жестко и весело. Удачи");
}

    @Override
    public void signalOn() {
        System.out.println("Сигнала нет, надо кричать! Кричать оператор.");
    }
}
