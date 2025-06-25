🧩 Що таке патерн Mediator?
Mediator (Посередник) — це поведінковий патерн, який дозволяє об'єктам спілкуватися опосередковано, через спеціальний об'єкт-посередник, а не напряму один з одним.
Це допомагає зменшити зв'язаність між об'єктами та зробити код чистішим і гнучкішим.

🎓 Навіщо він потрібен?
Уявімо UI з безліччю компонентів: кнопки, чекбокси, поля вводу. Якщо кожен елемент буде знати про інших — код перетвориться на павутину залежностей.

Mediator виступає як диспетчер, який координує взаємодію:
— "якщо поле заповнене, активуй кнопку"
— "якщо чекбокс обрано, покажи повідомлення"

🛠️ Простий приклад Java-реалізації
Уявімо простий UI: Button, TextBox, CheckBox — і один посередник:

🔸 Інтерфейс Mediator
java
Copy
Edit
interface Mediator {
void notify(Component sender, String event);
}
🔸 Абстрактний клас Component
java
Copy
Edit
abstract class Component {
protected Mediator mediator;

    public Component(Mediator mediator) {
        this.mediator = mediator;
    }

    public void changed(String event) {
        mediator.notify(this, event);
    }
}
🔸 Конкретні компоненти
java
Copy
Edit
class TextBox extends Component {
private String text = "";

    public TextBox(Mediator mediator) {
        super(mediator);
    }

    public void enterText(String input) {
        this.text = input;
        changed("textEntered");
    }

    public String getText() {
        return text;
    }
}

class CheckBox extends Component {
private boolean checked = false;

    public CheckBox(Mediator mediator) {
        super(mediator);
    }

    public void toggle() {
        checked = !checked;
        changed("checkToggled");
    }

    public boolean isChecked() {
        return checked;
    }
}

class Button extends Component {
private boolean enabled = false;

    public Button(Mediator mediator) {
        super(mediator);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        System.out.println("Button is now " + (enabled ? "enabled" : "disabled"));
    }
}
🔸 Конкретний Mediator
java
Copy
Edit
class DialogMediator implements Mediator {
private TextBox textBox;
private CheckBox checkBox;
private Button button;

    public void setComponents(TextBox textBox, CheckBox checkBox, Button button) {
        this.textBox = textBox;
        this.checkBox = checkBox;
        this.button = button;
    }

    @Override
    public void notify(Component sender, String event) {
        if ("textEntered".equals(event) || "checkToggled".equals(event)) {
            boolean enable = !textBox.getText().isEmpty() && checkBox.isChecked();
            button.setEnabled(enable);
        }
    }
}
🔸 Використання
java
Copy
Edit
public class MediatorDemo {
public static void main(String[] args) {
DialogMediator mediator = new DialogMediator();

        TextBox textBox = new TextBox(mediator);
        CheckBox checkBox = new CheckBox(mediator);
        Button button = new Button(mediator);

        mediator.setComponents(textBox, checkBox, button);

        textBox.enterText("Hello");  // button still disabled
        checkBox.toggle();          // button enabled
    }
}
✅ Переваги
Плюси	Пояснення
🧹 Зменшує зв'язність	Компоненти не знають один про одного
🧠 Централізація логіки	Вся взаємодія в одному місці — легше дебажити
🛠️ Легше додавати нові компоненти	Додаєш — і просто реєструєш у медіаторі
♻️ Полегшує повторне використання	Компоненти можна перевикористовувати в інших контекстах

❌ Недоліки
Мінуси	Пояснення
🧩 Централізація = Бог-об'єкт	Якщо логіка складна — медіатор перетворюється на монстра
🧼 Не завжди очевидна структура	Новачкам важко зрозуміти, як компоненти взаємодіють
🔄 Надлишковість	Іноді прості залежності краще зробити напряму

📌 Де використовується?
GUI системи: Swing, JavaFX, Android

Реалізація chat-ботів, де User взаємодіє через ChatRoomMediator

Мережеві модулі (сервер — диспетчер підключень)

При обробці івентів (напр., Game Engine Event Dispatcher)

🎯 Аналогія
Уяви собі диспетчера на вишці аеропорту — літаки не говорять один з одним, вони спілкуються лише через нього.
Mediator — це як той диспетчер ✈️

Хочеш — можемо зробити гру або чат на основі цього патерна. Якщо треба — додам приклад із JavaFX або більш просунутий варіант зі Spring.
