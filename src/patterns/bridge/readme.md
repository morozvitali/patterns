🔷 1. Що таке патерн Bridge (Міст)?

Мета:
Розділити абстракцію (інтерфейс) і її реалізацію так, щоб їх
можна було змінювати незалежно одна від одної.

Головна ідея:
Замість того, щоб мати десятки підкласів для кожної комбінації
"абстракція + реалізація", ми розділяємо їх на окремі ієрархії
і пов’язуємо через композицію.

🔷 2. Де використовується?
У Java та багатьох програмах патерн Bridge застосовується:

* для побудови UI-фреймворків, де абстракція (наприклад, кнопка)
  відділена від реалізації (платформа — Windows, Linux, Web);
* для створення кросплатформених API;
* для побудови складних ієрархій типу Shape → Color.

🔷 3. Структура патерна Bridge

Abstraction     →   Implementor
⬇                       ⬇
RefinedAbstraction → ConcreteImplementor

* Abstraction — визначає загальний інтерфейс (наприклад, Remote).
* Implementor — інтерфейс для "реалізації" (наприклад, Device).
* RefinedAbstraction — розширення абстракції (наприклад, AdvancedRemote).
* ConcreteImplementor — конкретна реалізація (наприклад, TV, Radio).

🔷 4. Приклад на Java
Уявімо, що в нас є різні пристрої (TV, Radio) та пульти (BasicRemote,
AdvancedRemote). Ми хочемо мати змогу писати:

Remote remote = new AdvancedRemote(new TV());
remote.togglePower();

✅ Код:

// Implementor
interface Device {
void enable();
void disable();
boolean isEnabled();
}

// ConcreteImplementors
class TV implements Device {
private boolean on = false;
public void enable() { on = true; System.out.println("TV on"); }
public void disable() { on = false; System.out.println("TV off"); }
public boolean isEnabled() { return on; }
}

class Radio implements Device {
private boolean on = false;
public void enable() { on = true; System.out.println("Radio on"); }
public void disable() { on = false; System.out.println("Radio off"); }
public boolean isEnabled() { return on; }
}

// Abstraction
abstract class Remote {
protected Device device;
public Remote(Device device) { this.device = device; }

public void togglePower() {
if (device.isEnabled()) device.disable();
else device.enable();
}
}

// Refined Abstraction
class AdvancedRemote extends Remote {
public AdvancedRemote(Device device) { super(device); }
public void mute() {
System.out.println("Muting the device.");
}
}

// Використання:
public class Main {
public static void main(String[] args) {
Remote remote = new AdvancedRemote(new TV());
remote.togglePower();  // TV on
((AdvancedRemote)remote).mute(); // Muting the device.
}
}


🔷 5. Плюси й мінуси патерна Bridge
✅ Плюси:
✔ Розділення абстракції та реалізації → легка заміна однієї без впливу на іншу.
✔ Комбінації логіки без вибуху класів.
✔ Проста підтримка коду.
✔ Зменшує зв’язність між компонентами.

❌ Мінуси:
❗ Додаткова складність (особливо для початківців).
❗ Ускладнення структури (2 ієрархії — замість 1).
❗ Не завжди потрібен, коли ієрархія проста.

🔷 6. Порівняння з іншими патернами
Патерн	                Схожість	                                Відмінність
Adapter	                Обидва "з'єднують" класи	                Adapter — для сумісності інтерфейсів, Bridge — для розділення абстракції та реалізації
Strategy	            Обидва дозволяють замінювати логіку	        Strategy — заміна поведінки, Bridge — поділ реалізації й інтерфейсу
Decorator	            Комбінує функціональність	                Decorator динамічно додає, а Bridge — розділяє по ієрархіях
Abstract Factory	    Дає змогу створити об'єкти певного типу	    Можна поєднувати з Bridge для створення реалізацій

🔷 7. Коли використовувати?
* Коли ієрархія класів "вибухає" — наприклад, багато комбінацій:
* Shape + Color → RedCircle, BlueCircle, RedSquare, BlueSquare...
  Bridge допоможе це розділити.
* Коли потрібно змінювати абстракції й реалізації незалежно.
* Коли хочеш зробити кросплатформений або розширюваний інтерфейс.