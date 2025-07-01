"Template Method" (шаблонний метод) — 
один із класичних патернів із банди чотирьох (GoF). 
Це структурований і дуже корисний підхід, 
особливо коли ти хочеш задати скелет алгоритму, 
але дозволити підкласам уточнювати окремі кроки.

🧠 Ідея патерна Template Method
Template Method — це поведінковий патерн, який:

🔹 визначає кістяк алгоритму в методі батьківського класу
🔹 делегує деякі кроки підкласам (шляхом перевизначення методів)
🔹 не дозволяє змінювати саму структуру алгоритму

Тобто, в тебе є "шаблон" (template) в базовому класі, 
а деталі можуть варіюватися в нащадках.

📦 Приклад — процес приготування напою

    abstract class CaffeineBeverage {
    // Шаблонний метод — задає послідовність
    public final void prepareRecipe() {
    boilWater();
    brew();
    pourInCup();
    addCondiments();
    }

    void boilWater() {
        System.out.println("Кип’ятимо воду");
    }

    void pourInCup() {
        System.out.println("Наливаємо у чашку");
    }

    abstract void brew();           // абстрактні методи — їх реалізують підкласи
    abstract void addCondiments();
    }

    class Tea extends CaffeineBeverage {
    void brew() {
    System.out.println("Заварюємо чай");
    }

    void addCondiments() {
        System.out.println("Додаємо лимон");
    }
    }

    class Coffee extends CaffeineBeverage {
    void brew() {
    System.out.println("Заварюємо каву");
    }

    void addCondiments() {
        System.out.println("Додаємо цукор і молоко");
    }
    }

🔍 Особливості
prepareRecipe() — шаблонний метод: він містить незмінний алгоритм
Методи brew() і addCondiments() — "гачки" для розширення в підкласах

✅ Переваги
✔️ Чітке розділення основного алгоритму і деталей
✔️ Уникаємо дублювання коду — загальне зберігається в базовому класі
✔️ Полегшує супровід і тестування

⚠️ Недоліки
❌ Може призвести до надмірного наслідування
❌ Не гнучкий, якщо потрібно динамічно змінювати структуру 
алгоритму (для цього краще Strategy)

🥊 Порівняння з іншими
Патерн	            Пояснення
Strategy	        Замість наслідування використовує делегування
Template Method	    Спирається на наслідування
Command	            Інкапсулює запити як об'єкти

🤓 Коли використовувати?
Коли алгоритм фіксований, але частина дій може змінюватися

Коли потрібно уніфікувати структуру багатьох реалізацій (наприклад, 
обробка даних, логування, формати документів)

🧪 Хочеш завдання?
💡 Хочу дати тобі маленьке практичне завдання на Template Method, щоби закріпити.
📌 Ситуація: треба зробити шаблон обробки файлу (читання, обробка, запис).

Наприклад:
CSV обробляє дані по-одному
JSON — по-іншому
Але етапи (read → process → write) однакові

--------------------------------------------------------------

🔨 ЗАДАЧА: Обробник файлів за шаблоном (Template Method)
📋 Умова:
Тобі потрібно реалізувати абстрактний клас FileProcessor, 
який описує шаблон роботи з будь-яким файлом:

readFile() → processContent() → writeFile()
🔸 Методи readFile() та writeFile() — однакові для всіх форматів
🔸 Метод processContent() — абстрактний, реалізується в підкласах

🧩 Вимога:
Реалізуй два конкретні класи:

CSVFileProcessor – обробляє CSV (розбиває кожен рядок на комірки)
JSONFileProcessor – обробляє JSON (рахує кількість об’єктів у масиві)

🧱 КРОК 1: Абстрактний клас

    public abstract class FileProcessor {

    // Шаблонний метод
    public final void execute() {
        String content = readFile();
        String processed = processContent(content);
        writeFile(processed);
    }

    protected String readFile() {
        System.out.println("Читаємо файл...");
        return "data"; // Заміна справжнього зчитування з файлу
    }

    protected abstract String processContent(String content);

    protected void writeFile(String content) {
        System.out.println("Записуємо оброблені дані: " + content);
    }
}
🧱 КРОК 2: CSVFileProcessor

    public class CSVFileProcessor extends FileProcessor {
    @Override
    protected String processContent(String content) {
        // Уявімо, що це CSV рядки з комами
        String[] lines = {"name,age", "Alice,30", "Bob,25"};

        StringBuilder sb = new StringBuilder("CSV Parsed:\n");
        for (String line : lines) {
            String[] cells = line.split(",");
            sb.append(" -> ");
            for (String cell : cells) {
                sb.append(cell).append(" | ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
🧱 КРОК 3: JSONFileProcessor

public class JSONFileProcessor extends FileProcessor {

    @Override
    protected String processContent(String content) {
        // Уявімо, що тут JSON-масив об’єктів
        String[] fakeJsonArray = {"{\"name\":\"Alice\"}", "{\"name\":\"Bob\"}"};

        return "Кількість об’єктів у JSON: " + fakeJsonArray.length;
    }
}
🧪 КРОК 4: Тестовий запуск

    public class Main {
    public static void main(String[] args) {
    FileProcessor csvProcessor = new CSVFileProcessor();
    csvProcessor.execute();

        System.out.println("---------------");

        FileProcessor jsonProcessor = new JSONFileProcessor();
        jsonProcessor.execute();
    }
}
🧠 Що далі?
🔧 Хочеш ускладнити задачу?

Додай метод-гак shouldWriteOutput() — щоб можна було пропускати writeFile()
Реалізуй реальне зчитування файлу через BufferedReader
Додай логування дій через шаблонний метод або в окремий клас (патерн Decorator / Logger)
💬 Якщо хочеш, я можу вигадати варіант із логуванням дій, або з тестами для шаблонного методу,
або зробимо завдання на наслідування і перевизначення з хуками (hook methods).
Готовий до наступного рівня? 🚀

---------------------------------------------------------------------------------

https://chatgpt.com/c/6862779f-657c-8009-a2d0-8725980486ea
