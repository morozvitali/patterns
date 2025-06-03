🎭 Патерн Декоратор (Decorator)
🔹 Ідея:
Декоратор дозволяє додавати нову поведінку об'єктам динамічно, без зміни їхнього коду.

Це структурний патерн, який дозволяє обгорнути об’єкт у нову "обгортку", яка розширює або змінює його поведінку.

📦 Коли застосовується
🔧 Застосовуй, якщо:
Потрібно додавати функціонал без успадкування.

Кілька "доповнень" мають поєднуватись довільно (комбінації).

Клас вже скомпільовано або він з бібліотеки, яку ти не можеш змінити.

Потрібно мати декілька рівнів розширення поведінки (наприклад, фільтри, логери, обгортки).

📍 Приклад із життя:
У тебе є кава, і ти хочеш додати молоко, цукор, шоколад.
Але ти не створюєш 100 класів CoffeeWithMilkAndSugarAndChocolate,
а просто обгортаєш каву в декоратори:

Coffee
    └── MilkDecorator
            └── SugarDecorator
                     └── ChocolateDecorator

🧱 Реалізація (Java-приклад)
🔸 1. Базовий інтерфейс
public interface Coffee {
String getDescription();
double getCost();
}

🔸 2. Конкретний компонент
public class SimpleCoffee implements Coffee {
public String getDescription() {
return "Simple coffee";
}

public double getCost() {
        return 10;
    }
}

🔸 3. Абстрактний декоратор
public abstract class CoffeeDecorator implements Coffee {
protected Coffee decoratedCoffee;
public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
public String getDescription() {
        return decoratedCoffee.getDescription();
    }

public double getCost() {
        return decoratedCoffee.getCost();
    }
}

🔸 4. Конкретні декоратори
public class MilkDecorator extends CoffeeDecorator {
public MilkDecorator(Coffee coffee) {
super(coffee);
}

public String getDescription() {
        return super.getDescription() + ", milk";
    }

public double getCost() {
        return super.getCost() + 3;
    }
}

public class SugarDecorator extends CoffeeDecorator {
public SugarDecorator(Coffee coffee) {
super(coffee);
}

public String getDescription() {
        return super.getDescription() + ", sugar";
    }

public double getCost() {
        return super.getCost() + 1;
    }
}
🔸 5. Використання
public class Main {
public static void main(String[] args) {
Coffee coffee = new SimpleCoffee();
coffee = new MilkDecorator(coffee);
coffee = new SugarDecorator(coffee);

System.out.println(coffee.getDescription()); // Simple coffee, milk, sugar
        System.out.println("Total: " + coffee.getCost()); // 10 + 3 + 1 = 14
    }
}

🧠 Чим Decorator НЕ є
Патерн	            Ключова різниця
Strategy	        Обирає алгоритм поведінки — в основі замінність
Decorator	        Обгортає поведінку, додає функціональність поступово
Adapter	            Перетворює інтерфейс одного класу в інший
Proxy	            Контролює доступ (безпеки, кешування, логування), зазвичай 1:1


✅ Переваги
Кожен декоратор має одну відповідальність (SRP)
Можна гнучко комбінувати
Не порушує відкритість-закритість (OCP) — ти додаєш, не змінюєш

❌ Недоліки
Може з'явитись багато дрібних класів
Важко відстежити повну поведінку, якщо багато обгорток
У порівнянні з успадкуванням — важче читати на перший погляд

🧰 Де в Java вже використовується Decorator?
java.io.InputStream / BufferedInputStream / DataInputStream
java.util.Collections.unmodifiableList(list)
HttpServletRequestWrapper
Spring Security (фільтри — це по суті декоратори)

🔹 Хочеш, можемо:

реалізувати декоратор для логера (додає timestamp, або логування у файл),
зробити декоратор для ігрових юнітів (захист, баф, збільшення швидкості),
або потренуватись розпізнавати, коли краще Decorator, а коли Strategy.
Що скажеш, друже-архітекторе? 😄