🧠 Що таке Memento?
Memento — це поведінковий патерн проєктування, який дозволяє зберігати і відновлювати попередній стан об'єкта без порушення інкапсуляції.

Уяви, що ти малюєш у Photoshop і натискаєш Ctrl + Z — це і є ідея Memento!

🏗 Структура патерна
Патерн складається з трьох основних компонентів:

Роль	            Опис
Originator	        Об’єкт, стан якого потрібно зберігати
Memento	            Об'єкт, який зберігає стан Originator (тільки для читання)
Caretaker	        Відповідає за збереження та відновлення Memento, не читає його вміст

📦 Приклад Java-реалізації

    // Originator
    class TextEditor {
    private String text;

    public void type(String newText) {
        text = newText;
    }

    public String getText() {
        return text;
    }

    public Memento save() {
        return new Memento(text);
    }

    public void restore(Memento memento) {
        this.text = memento.getState();
    }

    // Memento (внутрішній клас)
    public static class Memento {
        private final String state;

        private Memento(String state) {
            this.state = state;
        }

        private String getState() {
            return state;
        }
    }
}

    // Caretaker
    class History {
    private List<TextEditor.Memento> history = new ArrayList<>();

    public void save(TextEditor.Memento m) {
        history.add(m);
    }

    public TextEditor.Memento get(int index) {
        return history.get(index);
    }
}
🧪 Використання

    public class Main {
    public static void main(String[] args) {
    TextEditor editor = new TextEditor();
    History history = new History();

        editor.type("Версія 1");
        history.save(editor.save());

        editor.type("Версія 2");
        history.save(editor.save());

        editor.type("Версія 3");

        System.out.println("Поточний текст: " + editor.getText()); // Версія 3

        editor.restore(history.get(0));
        System.out.println("Відновлено: " + editor.getText()); // Версія 1
    }
}
✅ Переваги
✔ Збереження історії змін стану
✔ Відновлення стану без порушення інкапсуляції
✔ Добре працює у поєднанні з Undo/Redo

⚠ Недоліки
❌ Може споживати багато пам’яті, якщо стан об'єкта великий
❌ Caretaker повинен керувати кількома версіями — можливий хаос

🗂 Коли застосовувати?
Коли потрібно реалізувати undo/redo (текстовий редактор, графічний редактор, ігри)
Коли об'єкт має важливий стан, який потрібно мати можливість відкотити
Коли інші патерни (наприклад, зберігання всього стану в Originator) порушують інкапсуляцію

🔄 Варіації
Іноді Memento зберігається всередині Originator (внутрішній клас), щоб приховати деталі стану.
Іноді реалізується обмежений доступ до Memento, наприклад, через інтерфейси.

🧵 Порівняння з іншими патернами:
Патерн	            Основна різниця
Command	            Інкапсулює дію, а не стан
State	            Поведінка змінюється залежно від стану
Memento	            Зберігає попередній стан, без логіки поведінки

**Якщо хочеш — зробимо разом варіацію з Undo/Redo, або розглянемо графічний редактор, гру, калькулятор з відкотом дій 🕹️📋**

https://chatgpt.com/c/685be025-bce8-8009-9774-0508e5952e84