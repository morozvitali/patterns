Теорія: Singleton Pattern
🔹 Що таке Singleton?
Singleton — це патерн проєктування, який гарантує,
що клас має тільки один екземпляр,
і надає глобальну точку доступу до нього.

🔧 Навіщо він потрібен?
Коли треба централізовано керувати чимось — наприклад:

логування (Logger)
конфігурація (ConfigManager)
з’єднання з базою (DatabaseConnection)
кеш (CacheManager)

📦 Переваги:
❌ Ніхто не створить зайвий об’єкт
✅ Менше пам’яті
✅ Централізований контроль над поведінкою

⚠️ Недоліки:
Якщо зловживати — може стати глобальною змінною (поганий стиль)
У багатопотоковості потребує захисту

✅ Простий приклад (Java)
public class Logger {

    // приватне статичне поле для зберігання єдиного екземпляру
    private static Logger instance;

    // приватний конструктор — не дає створити об’єкт ззовні
    private Logger() {
        System.out.println("Logger створено");
    }

    // публічний метод доступу до єдиного екземпляра
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger(); // створюється лише один раз
        }
        return instance;
    }

    // метод логування
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
🧪 Використання:

public class Main {
public static void main(String[] args) {
Logger logger1 = Logger.getInstance();
Logger logger2 = Logger.getInstance();

        logger1.log("Програма стартувала");
        logger2.log("Це той самий логер? " + (logger1 == logger2)); // true
    }
}
🔍 Результат:

csharp
Copy
Edit
Logger створено
[LOG] Програма стартувала
[LOG] Це той самий логер? true

💡 Альтернативні варіанти реалізації:
З потокобезпекою (synchronized)
З enum (найбезпечніший варіант у Java)
Через static final поле (рання ініціалізація)

-------------------------------var-2------------------------------------

Singleton — це один із найвідоміших і найпростіших патернів проєктування, який гарантує, що у класу буде лише один екземпляр, і надає глобальну точку доступу до нього.

🔹 Суть Singleton
Один об'єкт на весь додаток.

Контроль над створенням об'єкта.

Часто використовується для конфігурацій, логування, пулів підключення до БД тощо.

🔧 Простий приклад (ледача ініціалізація, непотокобезпечна):

public class Singleton {
private static Singleton instance;

    // Приватний конструктор
    private Singleton() {}

    // Публічний метод доступу
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton(); // створюється тільки один раз
        }
        return instance;
    }
}
🔧 Потокобезпечна реалізація (синхронізований доступ):

public class Singleton {
private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
🔧 Кращий спосіб — "Double-checked locking":

public class Singleton {
private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) { // перша перевірка
            synchronized (Singleton.class) {
                if (instance == null) { // друга перевірка
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
🔧 Ще кращий варіант — використання статичного блоку:

public class Singleton {
private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
🔧 Найкращий і сучасний спосіб — через Enum (потокобезпечний, серіалізований, захищений від reflection):

public enum Singleton {
INSTANCE;

    public void doSomething() {
        System.out.println("Doing something...");
    }
}
🔍 Де використовується Singleton:
Runtime.getRuntime() — Java API

Logger.getLogger(...) — логери

Spring Bean з @Scope("singleton")

❗ Недоліки Singleton:
Може порушувати SRP (Single Responsibility Principle), якщо має багато відповідальностей.

Ускладнює тестування (особливо, якщо важко замінити на mock).

Може викликати проблеми з багатопоточністю, якщо реалізований неправильно.

