🧩 Що таке патерн Strategy?
Це патерн поведінки, який дозволяє визначити сімейство алгоритмів,
інкапсулювати кожен з них, і зробити їх взаємозамінними.
Клієнтський код (той, що використовує ці алгоритми) не знає про
конкретну реалізацію — він просто викликає "стратегію".

🎯 Коли використовувати?
Коли в тебе кілька варіантів поведінки (наприклад, різні способи
сортування, фільтрації, оплати).

Коли хочеш позбутись switch або if-else.

Коли треба змінювати логіку під час виконання.

🧱 Складові:
Інтерфейс стратегії — описує спільну дію (наприклад, execute()).
Конкретні стратегії — класи, які реалізують цю дію по-різному.
Контекст — клас, який має посилання на стратегію і використовує її.

🍰 Приклад: Калькулятор з різними операціями
🔹 Крок 1. Створюємо інтерфейс OperationStrategy
public interface OperationStrategy {
int execute(int a, int b);
}
🔹 Крок 2. Реалізуємо кілька стратегій
public class AddStrategy implements OperationStrategy {
public int execute(int a, int b) {
return a + b;
}
}

public class SubtractStrategy implements OperationStrategy {
public int execute(int a, int b) {
return a - b;
}
}

public class MultiplyStrategy implements OperationStrategy {
public int execute(int a, int b) {
return a * b;
}
}
🔹 Крок 3. Контекст (наш калькулятор)

public class Calculator {
private OperationStrategy strategy;

    public void setStrategy(OperationStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculate(int a, int b) {
        return strategy.execute(a, b);
    }

}
🔹 Крок 4. Використання

public class Main {
public static void main(String[] args) {
Calculator calculator = new Calculator();

        calculator.setStrategy(new AddStrategy());
        System.out.println("Сума: " + calculator.calculate(5, 3));  // 8

        calculator.setStrategy(new SubtractStrategy());
        System.out.println("Різниця: " + calculator.calculate(5, 3));  // 2

        calculator.setStrategy(new MultiplyStrategy());
        System.out.println("Добуток: " + calculator.calculate(5, 3));  // 15
    }

}
🔥 Плюшки:
Клас Calculator взагалі не змінюється, якщо з’являється нова операція.
Стратегії можна впровадити через лямбди (в Java 8+).
Це ідеальний кандидат для Spring Bean Injection або Stream API.

Хочеш:
🎯 Варіант з лямбда-виразами?
🔁 Завдання на реалізацію свого прикладу?
🎨 Намалювати UML-діаграму як це виглядає?
Якщо ти погоджуєшся, то я вже розігріваю мозок! 🧠