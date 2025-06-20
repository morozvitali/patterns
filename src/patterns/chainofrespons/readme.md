патерн Chain of Responsibility (Ланцюжок обов’язків) —
один із найкрутіших способів уникати if-else хаосу у коді,
коли ми хочемо обробляти запити послідовно.

🔗 Chain of Responsibility (CoR) — що це?

Суть:
У тебе є ланцюжок об'єктів, кожен з яких може обробити 
запит або передати далі наступному в ланцюжку.

📦 Патерн дозволяє:

уникнути жорсткого зв'язку між відправником і обробниками;
гнучко додавати або змінювати обробників;
реалізувати динамічну чергу обробки.

🛠️ Реальний приклад (Java)

🎯 Завдання:
Треба обробляти повідомлення з різним 
рівнем важливості: INFO, DEBUG, ERROR.

enum LogLevel {
INFO, DEBUG, ERROR
}

📦 Абстрактний обробник:

abstract class Logger {
protected Logger next;

public void setNext(Logger next) {
        this.next = next;
    }

public void log(LogLevel level, String message) {
        if (canHandle(level)) {
            writeMessage(message);
        } else if (next != null) {
            next.log(level, message);
        }
    }

protected abstract boolean canHandle(LogLevel level);
    protected abstract void writeMessage(String message);
}

🧱 Конкретні обробники:

class InfoLogger extends Logger {
protected boolean canHandle(LogLevel level) {
return level == LogLevel.INFO;
}

protected void writeMessage(String message) {
System.out.println("INFO: " + message);
    }
}

class DebugLogger extends Logger {
protected boolean canHandle(LogLevel level) {
return level == LogLevel.DEBUG;
}

protected void writeMessage(String message) {
System.out.println("DEBUG: " + message);
    }
}

class ErrorLogger extends Logger {
protected boolean canHandle(LogLevel level) {
return level == LogLevel.ERROR;
}

protected void writeMessage(String message) {
System.out.println("ERROR: " + message);
    }
}

🚀 Налаштування ланцюга:

public class Main {
public static void main(String[] args) {
Logger error = new ErrorLogger();
Logger debug = new DebugLogger();
Logger info = new InfoLogger();

info.setNext(debug);
debug.setNext(error);

info.log(LogLevel.INFO, "Everything is fine.");
info.log(LogLevel.DEBUG, "Debugging app.");
info.log(LogLevel.ERROR, "Something went wrong!");
    }
}

✅ Вивід:

INFO: Everything is fine.
DEBUG: Debugging app.
ERROR: Something went wrong!

🧠 Де використовувати:

Обробка логів.
Валідатори (наприклад, перевірка форми).
UI-події (натискання клавіш, кліки).
Авторизація та фільтрація запитів (напр. в Spring Security).

➕ Переваги:
✅ Гнучка обробка запитів.
✅ Відсутність if-else каскадів.
✅ Можна легко додавати нові обробники.

➖ Недоліки:
❌ Не завжди очевидно, хто саме обробив запит.
❌ Можна не помітити, що ніхто не обробив запит 
(якщо в кінці ланцюга немає запасного обробника).

🧩 Варіації:
Spring Security — використовує фільтри, які дуже схожі на цей патерн.
Можна зробити динамічну побудову ланцюжка в рантаймі.

🔚 Якщо хочеш, я можу зробити:

👉 варіант із валідацією (наприклад: перевірка email, довжини, пароля),
👉 реалізацію на базі обробки HTTP запитів (типу фільтрів),
👉 або навіть на базі Spring.