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

--------------------------------------------------

❓ Ти питаєш: "Як ми огортаємо клас (EmailNotifier) і маємо змогу
викликати його методи через notifier.send() у SMSDecorator?"

🔁 Суть — композиція + інтерфейс

public class SMSDecorator implements Notifier {
private Notifier notifier;

    public SMSDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

    public void send(String message) {
        notifier.send(message);   // <--- Ось тут магія
        System.out.println("SMS " + message);
    }
}
🧠 Як це працює по-простому
1. SMSDecorator — це клас, який сам реалізує інтерфейс Notifier
   Це значить, що його можна використовувати скрізь,
   де очікується Notifier — тобто ми підміняємо "базовий об'єкт".

2. Але всередині SMSDecorator є інший об’єкт типу Notifier:

   private Notifier notifier;
   Тобто він "огортає" або "вміщує в собі" 
   інший об'єкт Notifier, наприклад EmailNotifier.

3. Коли викликається метод send(), декоратор:
   спочатку делегує виклик методу обгорнутому об’єкту:

notifier.send(message);  // це насправді EmailNotifier.send(message)
а потім додає свою поведінку:

System.out.println("SMS " + message);
🤯 Аналогія з життям:
Уяви, що ти хочеш сказати щось другові. Але перед тим, як сказати, 
ти записуєш це на диктофон — як додаткову функцію.

public class DictaphoneDecorator implements Human {
private Human human;

    public DictaphoneDecorator(Human human) {
        this.human = human;
    }

    public void speak(String msg) {
        human.speak(msg);         // людина говорить
        System.out.println("🎙 Записали: " + msg); // диктофон додає запис
    }
}
📦 Декомпозиція виклику

Notifier notifier = new SMSDecorator(new EmailNotifier());
notifier.send("Hello");
Означає:

EmailNotifier — основна логіка.
SMSDecorator — додає щось зверху.
send() викликається на обгортці, але вона:
викликає send() у внутрішнього (EmailNotifier)
виконує ще щось своє.

🤖 В уяві: як виглядає стек викликів

main()
└── notifier.send("Hello") → SMSDecorator.send()
├── notifier.send("Hello") → EmailNotifier.send()
│   └── Вивід: "Email Hello"
└── Вивід: "SMS Hello"

✅ Висновок:
Так, ми маємо доступ до методу send() іншого об'єкта, 
бо ми його передали у конструктор, зберегли у полі notifier, 
і викликали через нього метод — все це через інтерфейс Notifier, 
який знає лише про send().

🔁 Це називається делегування — декоратор не замінює, а розширює поведінку.