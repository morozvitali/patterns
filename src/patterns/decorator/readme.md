Привіт, Віталію! 🔧 Сьогодні беремося за патерн "Декоратор" (Decorator pattern) — це один із фундаментальних структурних шаблонів проєктування, який дозволяє додавати нову поведінку об'єктам динамічно, без зміни їхнього коду. Давай розкладемо все по поличках.

🔍 Що вирішує патерн Декоратор?
Проблема:
Коли в тебе є клас, який уже працює, але потрібно додати до нього нову поведінку або змінити наявну:

без зміни вихідного коду,

без створення мільйона підкласів (вибух спадкування),

і бажано динамічно, в рантаймі.

Рішення:
Загорни (wrap) об'єкт у декоратор, який реалізує той самий інтерфейс і додає або змінює поведінку.

🧱 Структура (спрощено):

interface Component {
void operation();
}

class ConcreteComponent implements Component {
public void operation() {
System.out.println("Base operation");
}
}

abstract class Decorator implements Component {
protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }
    
    public void operation() {
        component.operation();
    }
}

class ConcreteDecoratorA extends Decorator {
public ConcreteDecoratorA(Component component) {
super(component);
}

    public void operation() {
        super.operation(); // базова поведінка
        addedBehavior();   // нова поведінка
    }
    
    private void addedBehavior() {
        System.out.println("Added behavior A");
    }
}
✅ Де він корисний?
UI бібліотеки (додаємо прокрутку, рамки, прозорість до віджета)

Потоки вводу/виводу в Java (BufferedReader, InputStreamReader)

Логування, авторизація, статистика — все, що можна обгорнути навколо основної логіки

🧠 Які принципи SOLID/DESIGN тут діють?
Single Responsibility — кожен клас відповідає за одну річ.

Open-Closed Principle (OCP) — відкритий для розширення, закритий для модифікації.

Композиція важливіша за спадкування — замість багатьох підкласів створюємо ланцюжок обгорток.

💡 Найпростіша реалізація?

interface Notifier {
void send(String message);
}

class EmailNotifier implements Notifier {
public void send(String message) {
System.out.println("Email: " + message);
}
}

class SMSDecorator implements Notifier {
private Notifier notifier;

    public SMSDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

    public void send(String message) {
        notifier.send(message);
        System.out.println("SMS: " + message);
    }
}

Notifier notifier = new SMSDecorator(new EmailNotifier());
notifier.send("Hello!");
// Вивід:
// Email: Hello!
// SMS: Hello!
🛑 Потенційні проблеми

Проблема	Пояснення
🔁 Занадто багато обгорток	Ланцюжок декораторів може стати складним для читання
🧪 Складність тестування	Поведінка розосереджена між декораторами
🧠 Ускладнення архітектури	Потребує розуміння композиції та делегування
🧩 Чим він відрізняється від спадкування?

Спадкування	Декоратор
Статичне	Динамічне (в рантаймі)
Нову поведінку додаємо в підклас	Нову поведінку додаємо через композицію
Важко змінювати поведінку без змін	Легко комбінувати декоратори
📎 Порівняння з іншими патернами

Патерн	Схожість / Відмінність
Adapter	Адаптує інтерфейс — не змінює поведінку
Proxy	Контролює доступ — не прикрашає поведінку
Strategy	Міняє логіку повністю, декоратор — розширює
Chain of Responsibility	Обробляє по черзі, декоратор — обгортає
✍️ Завдання для тренування
Напиши клас TextFormatter, який додає:

звичайний текст

декоратор UpperCase

декоратор Exclamation (додає "!" в кінці)

Хочеш, я сформулюю його як вправу і розберу разом з тобою?








