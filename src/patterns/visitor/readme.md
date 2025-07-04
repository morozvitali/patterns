патерн Visitor (Відвідувач): це один із поведінкових патернів проєктування, 
який дозволяє додавати нові операції до об’єктів без зміни самих об’єктів. 
Його часто використовують, коли є багато різних типів об'єктів, до яких потрібно 
застосовувати різну поведінку, але ці типи вже не можна або не зручно змінювати напряму.

🔍 Основна ідея
Витягуємо операції, які мають бути виконані над різними об’єктами, в окремий "відвідувач" (visitor). 
Замість того, щоб реалізовувати ці методи в кожному класі, об’єкти передають себе як параметр у методи відвідувача.

📌 Учасники патерну
Роль	                        Опис
Visitor	                    Інтерфейс або абстрактний клас з методами visit() для кожного типу об'єкта
ConcreteVisitor	            Конкретна реалізація, яка виконує операції над елементами
Element	                    Інтерфейс або абстрактний клас, який має метод accept(Visitor visitor)
ConcreteElement	            Конкретні класи, які реалізують метод accept() — викликають visitor.visit(this)

🧠 Коли використовувати Visitor:
Коли над об'єктами ієрархії потрібно виконувати різні операції, але ти не хочеш змінювати їхній код.
Коли структура об'єктів не змінюється часто, але поведінка змінюється часто.
Наприклад, при побудові AST (abstract syntax tree) в компіляторах, генераторах коду, звітах, аналізаторах.

✅ Приклад
Уявімо, що є фігури: Circle, Rectangle, і ти хочеш застосувати різні операції: 
draw(), calculateArea(), exportToJson() — не змінюючи класи фігур.

🔧 1. Інтерфейс Element:

    interface Shape {
    void accept(ShapeVisitor visitor);
    }

🔧 2. Конкретні фігури:

    class Circle implements Shape {
    double radius = 5;

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this); // передаємо себе
    }
    }
    
    class Rectangle implements Shape {
    double width = 10, height = 4;

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}
🔧 3. Інтерфейс відвідувача:

    interface ShapeVisitor {
    void visit(Circle c);
    void visit(Rectangle r);
}
🔧 4. Реалізація операції:

    class AreaCalculator implements ShapeVisitor {
    @Override
    public void visit(Circle c) {
    double area = Math.PI * c.radius * c.radius;
    System.out.println("Circle area: " + area);
    }

    @Override
    public void visit(Rectangle r) {
        double area = r.width * r.height;
        System.out.println("Rectangle area: " + area);
    }
    }

🔧 5. Використання:

    public class Main {
    public static void main(String[] args) {
    List<Shape> shapes = List.of(new Circle(), new Rectangle());
    ShapeVisitor areaCalculator = new AreaCalculator();

        for (Shape shape : shapes) {
            shape.accept(areaCalculator);
        }
    }
    }
🪓 Мінуси патерну Visitor
Порушує принцип відкритості/закритості для елементів: 
якщо додається новий тип елемента — треба змінювати всі відвідувачі.

Трохи складніший код — особливо якщо елементів багато.

🎯 Висновок
Visitor — це:

Спосіб відокремити логіку обробки від структури об’єктів.
Потужний інструмент у випадках, де потрібні різні операції над незмінними класами.
Добре працює з деревоподібними структурами, наприклад, AST, XML-деревами тощо.
Якщо хочеш, можу зробити ще приклади — наприклад, з експортером у JSON або XML, 
або варіант, де один відвідувач збирає статистику, а інший друкує результат. 

-----------------------------------------------------------------------------

Супер! Почнемо з простого і поступово ускладнимо 💡
Ось план:

🎓 Вправа 1. Побудуй систему фігур і додай 2 відвідувачі
Мета: засвоїти, як відвідувач виконує різну поведінку над одними й тими самими класами.

📋 Умова:
Є 2 типи фігур — Circle, Rectangle.
Твоя задача:

Створити інтерфейс Shape з методом accept(ShapeVisitor visitor).
Реалізувати 2 класи: Circle(radius) і Rectangle(width, height).
Створити інтерфейс ShapeVisitor з методами visit(Circle) і visit(Rectangle).
Створити два відвідувачі:
AreaCalculator — обчислює площу
ShapePrinter — виводить інформацію про фігуру

🧠 Підказки:
Для обчислення площі:

Circle: π * r²

Rectangle: width * height

У ShapePrinter просто друкуй Printing: Circle(radius=5) або Printing: Rectangle(w=10, h=4)

Для збереження фігур використай:

    List<Shape> shapes = List.of(new Circle(5), new Rectangle(10, 4));
    🔧 Початковий шаблон (ти заповниш самостійно):

    interface Shape {
    void accept(ShapeVisitor visitor);
    }
    
    class Circle implements Shape {
    double radius;

    Circle(double radius) { this.radius = radius; }

    @Override
    public void accept(ShapeVisitor visitor) {
        // ?
    }
    }

    class Rectangle implements Shape {
    double width, height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        // ?
    }
    }   
    
    interface ShapeVisitor {
    void visit(Circle c);
    void visit(Rectangle r);
    }

    // TODO: реалізуй ці класи:
    class AreaCalculator implements ShapeVisitor {
    @Override
    public void visit(Circle c) {
    // Площа круга
    }

    @Override
    public void visit(Rectangle r) {
        // Площа прямокутника
    }
}

    class ShapePrinter implements ShapeVisitor {
    @Override
    public void visit(Circle c) {
    // Вивід інформації
    }

    @Override
    public void visit(Rectangle r) {
        // Вивід інформації
    }
    }

    public class Main {
    public static void main(String[] args) {
    List<Shape> shapes = List.of(
    new Circle(5),
    new Rectangle(10, 4)
    );

        ShapeVisitor areaVisitor = new AreaCalculator();
        ShapeVisitor printVisitor = new ShapePrinter();

        for (Shape s : shapes) {
            s.accept(areaVisitor);
        }

        for (Shape s : shapes) {
            s.accept(printVisitor);
        }
    }
    }

📦 Очікуваний результат:

Circle area: 78.54
Rectangle area: 40.00

Printing: Circle(radius=5.0)
Printing: Rectangle(width=10.0, height=4.0)
🔁 Наступні вправи (якщо тобі зайде):
Вправа 2: Visitor для експорту у JSON/XML
Вправа 3: Visitor, який збирає статистику по кількості фігур кожного типу
Вправа 4: Visitor з логікою, що в залежності від умов видаляє або змінює фігури

🔧 Готовий розв’язати цю першу задачу самостійно, а я перевірю? 
Чи зразу показати повністю рішення з поясненнями?

-------------------------------------------------

https://chatgpt.com/c/68651902-40d8-8009-a01f-a7be6ef10e84
