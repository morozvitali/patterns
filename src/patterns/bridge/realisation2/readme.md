Патерн Bridge (Міст) — це структурний патерн,
який розділяє абстракцію і реалізацію так, 
щоб їх можна було змінювати незалежно.
Це як роз’єднати кермо і шасі: ти можеш 
змінити одне, не торкаючись іншого.

🔧 Ідея Bridge
Класична проблема: у нас є ієрархія 
пристроїв і різні способи керування.

Наприклад:

Абстракція: RemoteControl
Реалізація: TV, Radio, SmartDevice

Звичайне наслідування може призвести до вибуху підкласів:

RemoteForTV
RemoteForRadio
AdvancedRemoteForTV
AdvancedRemoteForRadio
...

👉 А Bridge дозволяє мати Remote
і Device як окремі ієрархії.

🔨 Структура патерна Bridge

Abstraction                  Implementor
--------------              -----------------
| RemoteControl |---------> |    Device      |
--------------              -----------------
       |                             |
-----------------             -----------------
| AdvancedRemote |           |  TV / Radio    |
-----------------           ------------------

🧪 Простий приклад реалізації (Java)

🔹 Інтерфейс реалізації (Implementor)
        
        interface Device {
        void turnOn();
        void turnOff();
        void setVolume(int level);
        }
🔹 Конкретні реалізації (ConcreteImplementors)

        class TV implements Device {
        public void turnOn() {
        System.out.println("TV is ON");
        }
        public void turnOff() {
        System.out.println("TV is OFF");
        }
        public void setVolume(int level) {
        System.out.println("TV volume set to " + level);
        }
        }

        class Radio implements Device {
        public void turnOn() {
        System.out.println("Radio is ON");
        }
        public void turnOff() {
        System.out.println("Radio is OFF");
        }
        public void setVolume(int level) {
        System.out.println("Radio volume set to " + level);
        }
        }

🔹 Абстракція (Abstraction)

        abstract class RemoteControl {
        protected Device device;

        public RemoteControl(Device device) {
            this.device = device;
        }
    
        public void turnOn() {
            device.turnOn();
        }
    
        public void turnOff() {
            device.turnOff();
        }
    
        public void setVolume(int level) {
            device.setVolume(level);
        }
        }
🔹 Розширена абстракція (RefinedAbstraction)

        class AdvancedRemote extends RemoteControl {
        public AdvancedRemote(Device device) {
        super(device);
        }
    
        public void mute() {
            device.setVolume(0);
            System.out.println("Muted");
        }
        }

🧪 Використання:

        public class Main {
        public static void main(String[] args) {
        Device tv = new TV();
        Device radio = new Radio();

        RemoteControl remote1 = new AdvancedRemote(tv);
        RemoteControl remote2 = new AdvancedRemote(radio);

        remote1.turnOn();
        remote1.setVolume(10);
        ((AdvancedRemote) remote1).mute();

        System.out.println();

        remote2.turnOn();
        remote2.setVolume(5);
        remote2.turnOff();
        }
        }

🧠 Висновки:
Плюси:
Дозволяє змінювати реалізацію і абстракцію незалежно
Уникає множинного наслідування (нема вибуху підкласів)
Гарно підходить для пристроїв, UI, графічних бібліотек

Мінуси:
Ускладнює структуру
Більше коду через поділ на рівні