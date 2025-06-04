Factory Method або Factory Pattern.

🏭 Патерн Factory Method
🔹 Ідея:
Factory Method — це патерн проєктування, який:
відокремлює логіку створення об'єктів від клієнтського коду;
дозволяє створювати об’єкти, не вказуючи конкретний клас
створюваного об’єкта.

📦 Приклад: Створення кнопок у GUI (Windows / MacOS)

🔸 Абстрактний продукт:
public interface Button {
void render();
}

🔸 Конкретні продукти:
public class WindowsButton implements Button {
public void render() {
System.out.println("Rendering Windows Button");
}
}

public class MacOSButton implements Button {
public void render() {
System.out.println("Rendering MacOS Button");
}
}

🔸 Абстрактна фабрика:
public abstract class Dialog {
public void renderWindow() {
Button okButton = createButton();
okButton.render();
}

// Factory Method
public abstract Button createButton();
}

🔸 Конкретні фабрики:
public class WindowsDialog extends Dialog {
public Button createButton() {
return new WindowsButton();
}
}

public class MacOSDialog extends Dialog {
public Button createButton() {
return new MacOSButton();
}
}

🔸 Використання:

public class Main {
public static void main(String[] args) {
Dialog dialog = getDialog("windows"); // умовно
dialog.renderWindow(); // клієнт не знає, який саме клас кнопки буде створено
}
public static Dialog getDialog(String os) {
if ("windows".equalsIgnoreCase(os)) {
return new WindowsDialog();
} else {
return new MacOSDialog();
}
}
}

🧠 Навіщо це все?
Переваги                                	        Недоліки
Виносить створення об'єкта в один метод	            Ускладнення коду (більше класів)
Спрощує підтримку і розширення	                    Менш очевидна структура
Легко додати новий тип продукту	                    Необхідність створювати ієрархію

🤔 Аналогія з реального життя:
У тебе є фабрика піци. Ти не створюєш піцу вручну, а викликаєш метод createPizza() — він створює потрібну піцу залежно від типу. Це й є factory method.

✅ Якщо коротко: Factory Method — це спосіб довірити підкласам створення об’єкта.



❌ Проблеми та підводні камені Factory Method
🔻 1. Надлишкова кількість класів
Що не так: Для кожного нового типу продукту створюється новий підклас-фабрика. Якщо в тебе десятки типів — вийде справжній "класовий вибух".

// кожен тип має свій клас
public class PDFReportFactory extends ReportFactory { ... }
public class ExcelReportFactory extends ReportFactory { ... }
📌 Порада: Іноді краще використати Simple Factory (тобто switch/if у єдиному методі), якщо типів небагато.

🔻 2. Труднощі з передаванням параметрів
Що не так: Factory Method зазвичай створює об’єкти без параметрів або з мінімальною конфігурацією. Якщо створюваний об’єкт потребує багато даних → фабрика стає складною і погано маштабується.

public abstract class PizzaFactory {
abstract Pizza createPizza(String toppings, boolean glutenFree, String size, boolean spicy); // це вже погано
}
📌 Порада: Якщо об’єкт складний — краще використовувати Builder у комбінації з фабрикою.

🔻 3. Порушення SRP (Single Responsibility Principle)
Що не так: Factory іноді бере на себе дві ролі:

Створення об’єкта.

Логіку вибору типу.

Це робить фабрику надто важкою і непридатною до повторного використання.

📌 Порада: Поділи логіку створення і логіку вибору — наприклад, через окремий ProductSelector.

🔻 4. Жорстке зв'язування через спадкування
Що не так: Factory Method вимагає створювати підкласи для нових типів. Це ускладнює масштабування: кожного разу треба розширювати ієрархію.

📌 Альтернатива: Використати стратегії або функціональні інтерфейси, особливо у Java 8+.

Map<String, Supplier<Product>> map = Map.of(
"pdf", PDFReport::new,
"excel", ExcelReport::new
);
🔻 5. Ускладнене тестування
Що не так: Якщо фабрика жорстко зашита у клієнтський код:
Report report = new PDFReportFactory().createReport();
— то в тестах складно підставити мок або тестову реалізацію.

📌 Порада: Замість створення фабрик усередині коду, використовуй DI (впровадження залежностей) або передавай фабрику як параметр.

🔻 6. Factory виглядає як “перевантаження”
Що не так: Якщо потрібно створювати багато схожих об’єктів, можна випадково перетворити фабрику на звалище логіки — починаєш додавати if-else, залежності, конфігурацію, читаєш з файлу і т.д.

📌 Порада: Фабрика має тільки створювати об’єкти. Логіку зчитування/обробки варто винести в окремий клас (наприклад, Configurator, Builder, або Service).

🔻 7. Важко динамічно розширити набір продуктів
Що не так: Якщо продукти і фабрики жорстко зашиті в код, додати новий тип продукту без редагування існуючої фабрики буде складно.

📌 Порада: Можна використовувати реєстрацію в runtime:

Map<String, Supplier<Button>> registry = new HashMap<>();
registry.put("windows", WindowsButton::new);
registry.put("mac", MacOSButton::new);
🧩 Порівняння з Singleton — як просили
Проблема	Singleton	Factory
Багатопоточність	Може створити кілька об’єктів	Може створити зайві об'єкти або неправильні
Reflection / Hack	Може обійти приватний конструктор	Можна зламати логіку вибору типу
Ускладнення тестування	Зберігає стан між тестами	Трудно підмінити вбудовану фабрику
Порушення SRP	Має стан + логіку доступу	Має вибір + створення + конфігурація
Проблеми з масштабуванням	Один клас — багато обов’язків	Багато підкласів і розгалуження

✅ Як зробити краще:
Простий випадок? — Simple Factory (один метод зі switch).

Складна структура? — Factory Method + Abstract Factory.

Багато параметрів? — Builder або DTO.

Динаміка/плагіни? — Реєстрація фабрик через Map.

