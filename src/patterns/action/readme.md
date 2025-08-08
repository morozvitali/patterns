🧱 Патерн Command (Команда)
Команда — це об’єкт, який інкапсулює всю інформацію, потрібну для виконання дії пізніше.

🔹 Структура

interface Command {
void execute();
}

class Light {
void turnOn() {
System.out.println("Light is ON");
}
}

class LightOnCommand implements Command {
private Light light;
LightOnCommand(Light light) { this.light = light; }
public void execute() { light.turnOn(); }
}

class RemoteControl {
private Command command;
void setCommand(Command command) { this.command = command; }
void pressButton() { command.execute(); }
}
✅ Переваги:
Інкапсуляція виклику
Undo/Redo
Можна ставити команди в чергу
Працює у GUI, іграх, Spring Messaging, Job Scheduling

❌ Недоліки:
Множення класів (на кожну дію — окремий клас)
Може бути надмірним для простих запитів

🪛 Патерн Action (Дія)
Часто використовується як частина CQRS, Redux, MVC.
Це об'єкт-подія, який лише описує, що сталося або що має статися.

🌰 Приклад (в стилі Redux):
class AddToCartAction {
private final String productId;
public AddToCartAction(String productId) { this.productId = productId; }
public String getProductId() { return productId; }
}
Action — передається до обробника, який вже виконує дію.

🔄 Action часто співіснує з Command. Різниця:

Action = опис події
Command = опис запиту

💥 Патерн Transaction (Транзакція)
Це патерн, який забезпечує атомарність виконання групи операцій. Часто реалізується у вигляді "Unit of Work" або "TransactionalCommand".

🧠 Приклад з обгорткою:
class TransactionCommand implements Command {
private Command command;
public TransactionCommand(Command command) {
this.command = command;
}
    public void execute() {
        try {
            beginTransaction();
            command.execute();
            commit();
        } catch (Exception e) {
            rollback();
        }
    }
}

📦 Це дає можливість:

ізолювати логіку транзакції

обгорнути довільну команду або послідовність команд

🧩 Порівняння
Патерн	Що робить	Коли використовувати
Command	Інкапсулює запит як об’єкт	Виконання, Undo/Redo, черги команд
Action	Представляє подію або намір змінити стан	Redux, CQRS, події в системі
Transaction	Атомарно виконує групу дій, або скасовує всі	Зміни стану, БД, грошові операції, складні послідовності

⚔ Command vs Action
Command	Action
Має execute()	✅	❌ (тільки опис дії)
Вміє виконуватись	✅	❌
Використовується у	Spring, JavaFX, ігри, CLI	Redux, CQRS, Events
Undo / Redo	✅	❌
Часто разом із Transaction	✅	✅

🧪 Практичне використання
Command — у кнопках GUI (undo/redo)

Action — у Redux / CQRS ("SEND_EMAIL", "ADD_TO_CART")

Transaction — у Spring (@Transactional) або обгортці команд

🌟 Висновок
Якщо треба виконати дію — Command
Якщо треба повідомити систему про намір — Action
Якщо треба гарантувати цілісність групи дій — Transaction


🧠 Уточнення по кожному:
Термін	Що означає / коли синонім до Command	Коли вже не синонім, а окрема концепція
Команда / Command	✅ Це і є назва патерна	—
Дія / Action	✅ В побутовому сенсі — те саме	❌ У Redux, Action — просто "опис" події, а не виконання
Транзакція	🔸 Може бути формою команди: "виконай атомарно"	❌ У базах даних, transaction — ізоляція і rollback
Інструкція / Запит / Intent	✅ Дуже схожі по суті	❌ Якщо це просто DTO-об’єкт без поведінки

🎯 Якщо ти кажеш:
"У мене є набір дій, які я хочу передавати, ставити в чергу, виконувати, логувати, і відміняти"

Тоді ти точно говориш про Command Pattern, і слова «дія», «action», «запит», «транзакція» — просто назви цієї ж сутності в різних контекстах.

🤹‍♂️ Приклади, як одне і те саме називається по-різному
Контекст	Назва для Command	Реалізація або форма
Java	Command, Runnable, Callable	execute(), run()
Spring	@Transactional методи з логікою	інкапсуляція + rollback
Redux	Action	об’єкт без логіки, але описує дію
CQRS	Command / Query	інкапсуляція запиту
Геймінг / UI	InputAction, GameCommand	команда користувача

🧩 Підсумок:
Термін	Чи це Command-патерн?	Пояснення
Дія	✅	Побутова назва
Action	✅ / 🔸	Залежить від контексту (Redux ≠ Command)
Команда	✅	Це і є назва патерна
Транзакція	🔸	Може бути частиною виконання команди, але не завжди є Command