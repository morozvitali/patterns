Патерн Prototype — один із класичних патернів створення об'єктів у Java.

Його основна ідея — створення нових об'єктів шляхом копіювання вже існуючих,
а не через конструктори. Це корисно, коли:
створення об'єкта «з нуля» дороге (наприклад, багато залежностей,
обчислень або запитів);
ми хочемо унікальні копії, але зі збереженням стану певного шаблону (прототипу);
потрібно швидко створити багато схожих об'єктів без надмірної ініціалізації.

🔍 Мета:
Створити нові об'єкти шляхом клонування існуючого екземпляра замість виклику конструктора.

🧠 Основна реалізація:
Java підтримує цей патерн на рівні мови через інтерфейс Cloneable та метод clone().

public class Sword implements Cloneable {
private String material;

public Sword(String material) {
        this.material = material;
    }

public void setMaterial(String material) {
        this.material = material;
    }

public String getMaterial() {
        return material;
    }

@Override
    protected Sword clone() {
        try {
            return (Sword) super.clone(); // поверхневе копіювання
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // не трапиться, бо реалізуємо Cloneable
        }
    }
}

Використання:

Sword prototype = new Sword("steel");
Sword enchantedSword = prototype.clone();
enchantedSword.setMaterial("magic steel");

System.out.println(prototype.getMaterial());      // steel
System.out.println(enchantedSword.getMaterial()); // magic steel
✅ Переваги:
🔁 Не потрібно щоразу створювати об'єкт з нуля.

⚡️ Працює швидше, ніж повна ініціалізація, особливо при копіюванні
складних об'єктів.

🧩 Зручно для динамічного створення конфігурованих об'єктів, наприклад,
у графічних редакторах або іграх.

❌ Недоліки:
😬 clone() — проблемний метод:

поверхневе копіювання → не копіює вкладені об'єкти (List, Map, тощо);
викликає багато тонких багів, якщо не реалізувати клонування правильно.
Cloneable — "порожній інтерфейс", не має методу, лише сигналізує JVM.
Порушує принципи інкапсуляції, бо clone() має доступ до Object.

🚀 Варіації / Альтернативи:
Deep copy — ручне клонування кожного поля:

@Override
public Sword clone() {
Sword clone = new Sword(new String(this.material));
return clone;
}
Серіалізація:

// Не найефективніший, але універсальний спосіб глибокого копіювання.
Копіюючий конструктор:

public Sword(Sword original) {
this.material = original.material;
}
Бібліотеки: Apache Commons Lang (SerializationUtils.clone()), ModelMapper,
MapStruct для складних об'єктів.

🏹 Де застосовується в реальності:
Ігри: створення NPC з шаблону;

Редактори: копіювання об'єктів на полотні;
Системи кешування: клонування перед обробкою;
Конфігурації: шаблон налаштувань, що модифікується в рантаймі.

🎯 Поради для запам’ятовування:
Якщо бачиш clone() → майже точно Prototype.
Якщо хочеш уникнути тонкощів клонування → використовуй копіюючі конструктори або фабрики.
Ідеально підходить, коли відтворення об'єкта складне, але копіювати — просто.