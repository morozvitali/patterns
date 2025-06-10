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


------------------------------------------------------------------


🚀 Чудово, Віталію! Полетіли в глибини Prototype з вкладеними об'єктами та deep copy.
Почнемо з реального прикладу та поступово ускладнимо.

🎮 Сценарій: Клонуємо персонажа гри
Уявімо, що у нас є персонаж GameCharacter, у якого є вкладений об’єкт Weapon.

🧱 1. Початкова структура класів
java
Copy
Edit
class Weapon {
String type;

    public Weapon(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Weapon(Weapon original) {
        this.type = original.type;
    }
}

class GameCharacter implements Cloneable {
String name;
Weapon weapon;

    public GameCharacter(String name, Weapon weapon) {
        this.name = name;
        this.weapon = weapon;
    }

    @Override
    protected GameCharacter clone() {
        try {
            GameCharacter clone = (GameCharacter) super.clone();
            clone.weapon = new Weapon(this.weapon); // ✨ deep copy вручну
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
🧪 2. Тестування копіювання:
java
Copy
Edit
public class PrototypeDemo {
public static void main(String[] args) {
GameCharacter warrior = new GameCharacter("Knight", new Weapon("Sword"));
GameCharacter clone = warrior.clone();

        clone.weapon.type = "Axe";

        System.out.println(warrior.weapon.getType()); // Sword
        System.out.println(clone.weapon.getType());   // Axe
    }
}
🧠 Якби ми не створили new Weapon(...), обидва об'єкти мали б спільну зброю.

🪄 3. Альтернатива: Копіюючий конструктор
java
Copy
Edit
public GameCharacter(GameCharacter original) {
this.name = original.name;
this.weapon = new Weapon(original.weapon);
}
📌 Класичний спосіб уникнути бруду з clone().

💾 4. Deep copy через серіалізацію
Плюси: універсально.
Мінуси: повільніше, всі класи мають бути Serializable.

java
Copy
Edit
import java.io.*;

public class DeepCopyUtil {
@SuppressWarnings("unchecked")
public static <T> T deepCopy(T object) {
try (
ByteArrayOutputStream bos = new ByteArrayOutputStream();
ObjectOutputStream out = new ObjectOutputStream(bos)
) {
out.writeObject(object);
ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
ObjectInputStream in = new ObjectInputStream(bis);
return (T) in.readObject();
} catch (IOException | ClassNotFoundException e) {
throw new RuntimeException("Deep copy failed", e);
}
}
}
Класи:

java
Copy
Edit
class Weapon implements Serializable {
String type;
public Weapon(String type) { this.type = type; }
}

class GameCharacter implements Serializable {
String name;
Weapon weapon;
public GameCharacter(String name, Weapon weapon) {
this.name = name;
this.weapon = weapon;
}
}
Тест:

java
Copy
Edit
GameCharacter original = new GameCharacter("Mage", new Weapon("Staff"));
GameCharacter copy = DeepCopyUtil.deepCopy(original);

copy.weapon.type = "Fire Wand";

System.out.println(original.weapon.type); // Staff
System.out.println(copy.weapon.type);     // Fire Wand
🌐 5. Варіант з Jackson (JSON deep copy)
java
Copy
Edit
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonCopyUtil {
private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T deepCopy(T object, Class<T> clazz) {
        try {
            String json = mapper.writeValueAsString(object);
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JSON copy failed", e);
        }
    }
}
📊 Візуальна схема (спрощено):
pgsql
Copy
Edit
GameCharacter                   GameCharacter (клон)
--------------                 ----------------------
name: "Knight"                 name: "Knight"
weapon --> [Sword]       =>   weapon --> [Axe]
(Weapon)                     (Weapon clone)
🏁 Висновки:
Метод	Глибина	Швидкість	Гнучкість	Ризик помилок
clone()	поверхнева, ручна глибока	швидко	базова	високий
копіюючий конструктор	повна контрольована	швидко	висока	низький
серіалізація	повна	повільно	універсально	низький
Jackson JSON	повна	повільно	зручно	залежність

🧩 Хочеш наступним зробити приклад з Jackson або глибоке клонування в колекціях (List, Map) у Prototype-стилі?
Чи оформимо завдання, щоб самостійно реалізувати персонажа з інвентарем та клонуванням?

💬 Ти обираєш напрямок — я запалюю прожектори.