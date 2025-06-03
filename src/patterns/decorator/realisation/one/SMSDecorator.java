package patterns.decorator.realisation.one;

public class SMSDecorator implements Notifier {
    private Notifier notifier;

    public SMSDecorator (Notifier notifier) {
        this.notifier = notifier;
    }

    public void send (String message) {
        notifier.send(message);
        System.out.println("SMS " + message);
    }
}

/*
Дивися як я це зрозумів.
ми створюємо обєкт типу Notifier
і викликаємо на ньому метод send() -
А те як відбувається запуск цього
методу задежить від створеного обьєкту.
Ми маємо new SMSDecorator(new EmailNotifier())
а це значить що new EmailNotifier()
передасться в конструктор до СМСДекоратора,
а той має в собі поле обєкта notifier,
тобто змогу викликати методи EmailNotifier
за умови запису у формі notifier.send
Крім того цей СМСДекоратор сам має в собі
метод send в якому викличе і метод з email
плюс свою додаткову дію з смс


public void send (String message) {
notifier.send(message); // викликає send() у внутрішнього EmailNotifier
System.out.println("SMS " + message); // додає ще вивід SMS
}

🧠 Що ми отримали на виході в консолі:
Email Hello
SMS Hello

🎯 Ідея патерну Декоратор тут:
Ми не змінювали EmailNotifier, а додали нову
поведінку — надсилання SMS.

Можна додавати інші декоратори (наприклад
FacebookDecorator, SlackDecorator тощо).
 */