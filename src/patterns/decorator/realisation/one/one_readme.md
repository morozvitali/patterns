патерн "Декоратор" — це чудовий архітектурний 
інструмент для додавання поведінки без зміни оригінального коду. 
Розберемо цей приклад по кісточках 🪓, щоб все стало ясно.

🔧 Структура коду
Ти маєш наступні класи:

Notifier (інтерфейс) — базова абстракція.
EmailNotifier — конкретна реалізація Notifier, 
яка просто надсилає повідомлення на email.
SMSDecorator — декоратор, який також реалізує Notifier, 
але обгортає інший Notifier і додає функціональність (SMS).
Main — клієнтський код, який використовує цю конструкцію.

🔍 Покрокова розшифровка того, що відбувається:
java
Copy
Edit
Notifier notifier = new SMSDecorator(new EmailNotifier());
1️new EmailNotifier()
Створюється об'єкт, який реалізує Notifier, і виводить 
повідомлення через System.out.println("Email " + message).

2️new SMSDecorator(...)
Декоратор SMSDecorator отримує на вхід інший Notifier 
(у цьому випадку — EmailNotifier) і обгортає його. 
Він також реалізує інтерфейс Notifier.

3️notifier.send("Hello");
Викликається метод send() на об'єкті SMSDecorator.

А ось як цей метод працює:

public void send (String message) {
notifier.send(message); // викликає send() у внутрішнього EmailNotifier
System.out.println("SMS " + message); // додає ще вивід SMS
}
Отже:

Спочатку викликається send("Hello") у EmailNotifier:
👉 Виводить: Email Hello

Потім друкується другий рядок у SMSDecorator:
👉 Виводить: SMS Hello

🧠 Що ми отримали на виході в консолі:
Email  Hello
SMS Hello

🎯 Ідея патерну Декоратор тут:
Ми не змінювали EmailNotifier, а додали нову 
поведінку — надсилання SMS.

Можна додавати інші декоратори (наприклад 
FacebookDecorator, SlackDecorator тощо).

Гнучкість: кожен "декоратор" може сам вирішити, 
коли і як викликати базовий send().

🧪 Що можна додати ще для практики?
Додати SlackDecorator, TelegramDecorator — потренуєшся з ланцюжком.

Впровадити логування, наприклад:
System.out.println("[INFO] Sending through SMS: " + message);

Вивести всі повідомлення через один об'єкт:

Notifier notifier = new SlackDecorator(
new SMSDecorator(
new EmailNotifier()));
Результат:

Email Hello
SMS Hello
Slack Hello
