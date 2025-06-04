package patterns.factory.realisation.one;

public class Main {
    public static void main(String[] args) {
        Toy toy1 = ToyFactory.createToy("bear");
        Toy toy2 = ToyFactory.createToy("robot");
        Toy toy3 = ToyFactory.createToy("doll");
        toy1.play();
        toy2.play();
        toy3.play();

        /*
        Factory Method - Делегує створення об'єкта через метод підкласу

        📌 Ключова ідея:
        Замість того, щоб писати:
        Car car = new Sedan();
        Ми пишемо:
        Car car = CarFactory.createCar("sedan");

        Інтерфейс Toy задає поведінку.
        ToyFactory відповідає за вирішення, який клас створити.
        Main (клієнтський код) просто викликає фабрику, не знаючи деталей створення.

        Завдання з зірочкою, створити магащин соків у окремому проєкті на спрінг,
        із логерами, парадом і Н2 базою, за посиланням:
        https://chatgpt.com/c/683fe26d-0f90-8009-8c82-c2c0cd1f0700
         */

    }
}
