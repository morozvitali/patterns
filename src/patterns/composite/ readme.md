Патерн Composite — це один із найцікавіших 
структурних патернів у Java, особливо коли 
мова йде про деревоподібні структури. 
Давай розкладемо його по поличках:

🔷 Суть Composite Pattern
Composite (Компонувальник) дозволяє об’єднувати 
об’єкти в деревоподібні структури, щоб працювати 
з окремими об’єктами і групами об’єктів однаково.
🔧 Наприклад: уяви, що у тебе є один файл або 
текста з файлами — з обома ти хочеш працювати 
однаково: open(), delete() тощо.

🔸 Структура патерна

        Component
        /        \
Leaf (Файл)  Composite (Тека)

🔹 Component — спільний інтерфейс:

interface FileSystemComponent {
void show();
}

🔹 Leaf — окремий об'єкт:

class File implements FileSystemComponent {
private String name;

public File(String name) {
        this.name = name;
    }

@Override
    public void show() {
        System.out.println("Файл: " + name);
    }
}
🔹 Composite — контейнер:

import java.util.ArrayList;
import java.util.List;

class Directory implements FileSystemComponent {
private String name;
private List<FileSystemComponent> children = new ArrayList<>();

public Directory(String name) {
        this.name = name;
    }

public void add(FileSystemComponent component) {
        children.add(component);
    }

@Override
    public void show() {
        System.out.println("Тека: " + name);
        for (FileSystemComponent child : children) {
            child.show();
        }
    }
}

🔹 Використання:

public class Main {
public static void main(String[] args) {
File file1 = new File("resume.docx");
File file2 = new File("photo.jpg");

Directory documents = new Directory("Documents");
        documents.add(file1);
        documents.add(file2);

Directory root = new Directory("Root");
        root.add(documents);
        root.show();
    }
}

📤 Вивід:

Тека: Root
Тека: Documents
Файл: resume.docx
Файл: photo.jpg

✅ Переваги
Плюс	                        Пояснення
✅ Єдина точка доступу	        Індивідуальні об'єкти та колекції об'єктів обробляються однаково
✅ Розширюваність	            Додавати нові типи файлів або каталогів просто
✅ Простота клієнтського коду	Не треба перевіряти тип об'єкта

❌ Недоліки
Мінус	                                Пояснення
❗ Може бути складніше відлагоджувати	Особливо, коли структура велика
❗ Можна зловживати	                    Іноді надто загальні інтерфейси ведуть до неочікуваної поведінки

🔁 Порівняння з іншими патернами
Патерн	                                Схожість	                            Відмінність
Decorator	                            Обидва мають деревоподібну структуру	Decorator додає поведінку, Composite — ієрархію
Composite vs. Iterator	                Обидва працюють із колекціями	        Composite будує ієрархію, Iterator перебирає
Composite vs. Chain of Responsibility	Обидва мають ланцюг	Composite — структура, Chain — послідовна обробка запиту

📦 Де застосовується в реальних проєктах
UI компоненти (Swing, JavaFX): кнопки, панелі, форми.

XML / HTML дерева: DOM-структура — типовий приклад Composite.

Файлові системи

Меню (навігаційні панелі, дропдауни)

Scene Graph в ігрових рушіях (Unity, Godot)

🧠 Думка наостанок
Composite — це універсальний спосіб побудови складних 
об'єктів із простих. Якщо в тебе дерево, і ти хочеш 
однакової поведінки — Composite саме те.

-------------------------------------------------------------------

📢 Якщо хочеш — можемо зробити задачку на реалізацію Composite. 
Наприклад, дерево UI-елементів чи файлова система з підтримкою 
remove(), search() тощо. Робимо задачу "Файлова система: Composite edition", 
щоб ти відчув, як працює цей патерн у реальності, а не лише на схемах.

✅ Завдання: Файлова система
🎯 Мета: реалізувати просту файлову систему, де можна:

створювати файли та папки
додавати елементи в папку
викликати show() — яка покаже всю структуру
опціонально: підтримка remove(String name)

📋 Структура:

Component (FileSystemItem)
├── Leaf: File
└── Composite: Directory
✨ Початковий код

// 🔹 Спільний інтерфейс
public interface FileSystemItem {
void show(String indent); // для відступів при виводі
}

// 🔹 Leaf
public class FileItem implements FileSystemItem {
private String name;

public FileItem(String name) {
        this.name = name;
    }

@Override
    public void show(String indent) {
        System.out.println(indent + "- 📄 " + name);
    }
}

// 🔹 Composite
import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemItem {
private String name;
private List<FileSystemItem> children = new ArrayList<>();

public Directory(String name) {
        this.name = name;
    }

public void add(FileSystemItem item) {
        children.add(item);
    }

@Override
    public void show(String indent) {
        System.out.println(indent + "+ 📁 " + name);
        for (FileSystemItem child : children) {
            child.show(indent + "   ");
        }
    }
}
🚀 Використання:

public class FileSystemDemo {
public static void main(String[] args) {
FileSystemItem file1 = new FileItem("main.java");
FileSystemItem file2 = new FileItem("notes.txt");
FileSystemItem file3 = new FileItem("README.md");

Directory src = new Directory("src");
        src.add(file1);

Directory docs = new Directory("docs");
        docs.add(file2);
        docs.add(file3);

Directory project = new Directory("MyProject");
        project.add(src);
        project.add(docs);

project.show("");  // Показати все дерево
    }
}
🖨️ Вивід буде такий:

+ 📁 MyProject
    + 📁 src
        - 📄 main.java
    + 📁 docs
        - 📄 notes.txt
        - 📄 README.md
          🧪 Ускладнення (опціонально):
          Хочеш прокачати задачу? Можемо:

➕ додати метод remove(String name)
🔍 пошук файлу за ім’ям find(String name)
📝 типи файлів, розширення
💾 зберігання в JSON (для Java + Gson)

📣 Ну що, як відчуття? Хочеш додати видалення, пошук чи щось сміливіше — наприклад, читати таку структуру з текстового файлу?
Кажи, Марічка тримає на поготові нові челенджі! 😄
https://chatgpt.com/c/685128ff-b734-8009-8624-2b45ed5e9766
