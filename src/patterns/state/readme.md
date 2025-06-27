Один із найцікавіших поведінкових патернів — State (Стан). 
🎭 Цей патерн дозволяє об'єкту змінювати свою поведінку 
залежно від внутрішнього стану. І, як ти здогадався, 
це звучить як… перемикання режимів поведінки без if і switch.

🎯 Ідея патерна State
Проблема: Уявімо, що є об’єкт, поведінка якого змінюється 
залежно від його стану. Наприклад, у тебе може бути:

банкомат з режимами «Очікування карти», «Введення PIN», «Видача грошей»;
документ у редакторі з режимами «Чернетка», «На перевірці», «Опубліковано»;
гравець у грі, який змінює стани: «Живий», «Ранений», «Мертвий».

Якщо реалізовувати це через if-else або switch-case, 
код стає заплутаним і складно розширювати. State патерн 
дозволяє уникнути цього, делегуючи поведінку окремим об’єктам стану.

🔧 Реалізація (простий приклад)
Приклад: AudioPlayer — програвач із двома станами: Playing та Paused.

Інтерфейс State

       interface State {
       void play(AudioPlayer player);
       void pause(AudioPlayer player);
       }

Конкретні стани

       class PlayingState implements State {

       public void play(AudioPlayer player) {
       System.out.println("Уже відтворюється");
       }
    
       public void pause(AudioPlayer player) {
       System.out.println("Призупинено");

       player.setState(new PausedState());
       }

       }

        class PausedState implements State {
        public void play(AudioPlayer player) {
        System.out.println("Відтворення відновлено");
        player.setState(new PlayingState());
        }
    
        public void pause(AudioPlayer player) {
            System.out.println("Уже призупинено");
        }
    }
3. Контекст — AudioPlayer

       class AudioPlayer {
       private State state;
    
       public AudioPlayer() {
       this.state = new PausedState(); // початковий стан
       }
    
       public void setState(State state) {
       this.state = state;
       }
    
       public void play() {
       state.play(this);
       }
    
       public void pause() {
       state.pause(this);
       }
       }
   4. Використання

          public class Main {
          public static void main(String[] args) {
          AudioPlayer player = new AudioPlayer();
          player.play();   // Відтворення відновлено
          player.pause();  // Призупинено
          player.pause();  // Уже призупинено
          }
          }

      ✅ Які проблеми вирішує патерн State?
      Усуває великі if-else/switch-case у логіці станів.

Дозволяє змінювати поведінку об’єкта на льоту, не змінюючи сам клас.
Кожен стан інкапсулює свою поведінку, дотримуючись SRP.

⚔ Порівняння з іншими патернами
Патерн	                Основна ідея	                                    Чим відрізняється від State
Strategy	            Обирає алгоритм динамічно	                        Strategy не зберігає стан; у State — поведінка залежить від стану об’єкта
Observer	            Реагує на події	                                    State не реагує на зовнішні події, а міняє поведінку
Command	                Інкапсулює дію як об’єкт	                        State описує саме поведінку в контексті стану, а не окрему дію
Template Method	        Шаблон алгоритму з можливістю зміни частин	        State динамічний, а Template статичний (через наслідування)

😬 Недоліки
Може спричинити багато дрібних класів, якщо багато станів.
Не завжди очевидно, коли краще використовувати саме цей патерн (можна сплутати зі Strategy).
Зміна стану — відповідальність контексту, і це може ускладнювати логіку.

📦 Де використовується
UI: кнопки/меню, що поводяться по-різному у різних станах.
Ігри: логіка гравця залежно від стану.
Парсери / автомати: коли поведінка змінюється залежно 
від поточного стану обробки.
Workflow-системи: задачі переходять зі стану в стан.

--------------------------------Task-for-Training----------------------------------

Чудово, Віталію! 💥 Робимо коротку практичну задачу, 
щоб "відчути" патерн State руками. Без зайвого – прямо в ціль:

🎯 ЗАДАЧА: Кавомашина з режимами
Умова:
Тобі потрібно реалізувати каву машину, 
яка може перебувати в одному з кількох станів:

💤 IdleState — очікує запуску
🔥 BrewingState — готує каву
✅ ReadyState — кава готова, чекає, поки її заберуть

Машина реагує на метод pressButton() — залежно від стану:

Стан	Натискання кнопки (pressButton())
Idle	→ запускає приготування кави, переходить у BrewingState
Brewing	→ виводить "Кава готується…"
Ready	→ кава видана, переходить у IdleState

🧩 Шаблон коду
    
    interface State {
    void pressButton(CoffeeMachine machine);
    }
    
    class IdleState implements State {
    public void pressButton(CoffeeMachine machine) {
    System.out.println("Запускаємо приготування кави...");
    machine.setState(new BrewingState());
    }
    }
    
    class BrewingState implements State {
    public void pressButton(CoffeeMachine machine) {
    System.out.println("Кава готується… зачекайте.");
    // Імітація закінчення приготування
    machine.setState(new ReadyState());
    }
    }
    
    class ReadyState implements State {
    public void pressButton(CoffeeMachine machine) {
    System.out.println("Каву видано. Смачного!");
    machine.setState(new IdleState());
    }
    }
    
    class CoffeeMachine {
    private State state;

    public CoffeeMachine() {
        state = new IdleState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void pressButton() {
        state.pressButton(this);
    }
}
🧪 Приклад використання:

public class Main {
public static void main(String[] args) {
CoffeeMachine machine = new CoffeeMachine();

        machine.pressButton(); // Запускаємо приготування...
        machine.pressButton(); // Кава готується…
        machine.pressButton(); // Каву видано
        machine.pressButton(); // Запускаємо приготування...
    }
}
✅ Завдання
🔧 Реалізуй цей код (або скопіюй шаблон і запускай).

📌 Спробуй додати ще один стан: "CleanState", 
який активується кожні 3 цикли натискань — 
кавомашина хоче чистки ☕🧽

👀 Або я можу одразу зробити продовження задачі 
з перевіркою лічильника приготувань — скажи, якщо хочеш ✌️


-------------------------------------------------------

https://chatgpt.com/c/685e8998-971c-8009-828f-ed9189e7600a






