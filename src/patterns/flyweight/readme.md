патерн проєктування Flyweight (Легковаговик), який,
незважаючи на свою "легкість", не такий вже й простий 😄

🧩 Flyweight (Легковаговик) — суть
Це структурний патерн, який дозволяє зменшити використання пам’яті
шляхом повторного використання однакових об'єктів.

🔧 Коли застосовувати
Є величезна кількість дрібних об'єктів, які повторюються.
Ці об'єкти займають багато пам’яті (наприклад, символи на екрані,
пікселі, частини HTML-елементів).
Частина інформації у них однакова (shared) — її можна винести в один екземпляр.
Інша частина — унікальна (extrinsic) — передається зовні під час використання.

📦 Приклад з реального світу
Уявімо текстовий редактор, де кожна літера — це об’єкт.
Якщо в тексті є 100 000 символів, але лише 26 унікальних літер (англійський алфавіт),
то замість створення 100 000 об'єктів, Flyweight дозволяє мати 26 "легковагових" об'єктів,
які розділяють загальні дані — наприклад, шрифт, стиль,
а унікальні дані — координати — передаються окремо.

🏗️ Структура

1. Flyweight — спільний інтерфейс для всіх легковагових об’єктів.
2. ConcreteFlyweight — реалізація, яка зберігає внутрішній (спільний) стан.
3. FlyweightFactory — фабрика, яка керує легковаговими об’єктами. 
Видає вже існуючий або створює новий.
4. Client — використовує Flyweight, передаючи зовнішні параметри.

   🧪 Java-приклад
// Flyweight
interface Shape {
void draw(int x, int y); // x,y — extrinsic state
}

// ConcreteFlyweight
class Circle implements Shape {
private final String color; // intrinsic state

public Circle(String color) {
this.color = color;
}

@Override
public void draw(int x, int y) {
System.out.println("Drawing " + color + " circle at (" + x + ", " + y + ")");
}
}

// FlyweightFactory
class ShapeFactory {
private static final Map<String, Shape> circleMap = new HashMap<>();

public static Shape getCircle(String color) {
return circleMap.computeIfAbsent(color, c -> new Circle(c));
}
}

// Client
public class FlyweightDemo {
public static void main(String[] args) {
for (int i = 0; i < 10; i++) {
Shape circle = ShapeFactory.getCircle("red"); // only one red circle object is created
circle.draw(i, i * 2);
}
}
}

 🔍 У прикладі:
color — внутрішній стан (shared),
x, y — зовнішній стан (унікальний для кожного використання),
ShapeFactory забезпечує повторне використання.

✅ Переваги
⚡ Економія пам'яті.

📦 Менше об'єктів → менше тиску на GC.

🧠 Ясна структура для повторно використовуваних елементів.

❌ Недоліки
🔩 Ускладнює логіку — потрібно відділяти внутрішній і зовнішній стан.
🚧 Не підходить, якщо об’єкти часто змінюють свій стан.
🧪 Інколи складно тестувати, якщо стан розкидано.

🧠 Запам'ятай
Flyweight — це як роздача одного велосипеда різним дітям, де кожен приносить свій шолом.
Велосипед — спільний, шолом — індивідуальний.


--------------------------------------------------------------------------------------

💡 Зараз зробимо практичну задачу на реалізацію патерна Flyweight.
Сценарій буде простий, але реалістичний — для повного розуміння.

🧪 Завдання: Ліс із деревами 🌳
Уявімо, що ми малюємо ліс. Кожне дерево має:
тип дерева (наприклад, "дуб", "береза", "сосна") → це внутрішній (shared) стан,
координати X та Y → це зовнішній (унікальний) стан.

🎯 Нам потрібно:

зекономити пам’ять, створюючи по одному об'єкту на кожен тип дерева,
зберігати координати окремо,
відображати ліс, використовуючи Flyweight.

🔧 Кроки реалізації
Інтерфейс Tree
Клас ConcreteTree — зберігає спільну інформацію (назва, текстура)
Клас TreeFactory — видає Flyweight-дерева
Клас Forest — містить список позицій + посилання на Tree

Демонстрація використання

💻 Початковий код

import java.util.*;

// 1. Flyweight інтерфейс
interface Tree {
void display(int x, int y);
}

// 2. ConcreteFlyweight
class ConcreteTree implements Tree {
private final String name;
private final String texture;

public ConcreteTree(String name, String texture) {
        this.name = name;
        this.texture = texture;
    }

@Override
    public void display(int x, int y) {
        System.out.printf("🌲 %s (текстура: %s) — координати (%d, %d)%n", name, texture, x, y);
    }
}

// 3. Flyweight Factory
class TreeFactory {
private static final Map<String, Tree> trees = new HashMap<>();

public static Tree getTree(String name, String texture) {
        String key = name + ":" + texture;
        return trees.computeIfAbsent(key, k -> new ConcreteTree(name, texture));
    }

public static int totalTreeTypes() {
        return trees.size();
    }
}

// 4. Клас Forest (Client)
class Forest {
private static class TreePosition {
int x, y;
Tree tree;

TreePosition(int x, int y, Tree tree) {
            this.x = x;
            this.y = y;
            this.tree = tree;
        }
    }

private final List<TreePosition> forest = new ArrayList<>();

public void plantTree(int x, int y, String name, String texture) {
        Tree tree = TreeFactory.getTree(name, texture);
        forest.add(new TreePosition(x, y, tree));
    }

public void displayForest() {
        for (TreePosition position : forest) {
            position.tree.display(position.x, position.y);
        }
    }
}

// 5. Демонстрація
public class FlyweightTreeDemo {
public static void main(String[] args) {
Forest forest = new Forest();

// Посадимо багато дерев з повторюваними типами
        forest.plantTree(1, 2, "Сосна", "зелена текстура");
        forest.plantTree(2, 3, "Сосна", "зелена текстура");
        forest.plantTree(5, 1, "Береза", "біла текстура");
        forest.plantTree(7, 4, "Сосна", "зелена текстура");
        forest.plantTree(9, 6, "Дуб", "коричнева текстура");

System.out.println("=== Відображення лісу ===");
        forest.displayForest();

System.out.printf("📦 Всього унікальних типів дерев: %d%n", TreeFactory.totalTreeTypes());
    }
}
🧠 Що ти отримаєш:
тільки 3 реальні об'єкти Tree (1 для "Сосна", 1 для "Береза", 1 для "Дуб"),
скільки завгодно дерев із різними координатами — економія пам’яті!

🔍 Що можна додати:
Зробити графічну реалізацію (JavaFX)
Зберігати ще більше спільного стану (наприклад, форма листя)
Порахувати скільки реально зекономлено об’єктів

https://chatgpt.com/c/6853f69b-88b4-8009-ac0f-e8a0771c6ef2