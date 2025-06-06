😊 Полетіли в чарівний світ Factory Pattern! ✈️

🏭 Factory Pattern — що це?
Factory Pattern (Фабричний шаблон) — це патерн створення об'єктів, 
який делегує створення об'єктів окремому методу або класу, 
а не створює їх безпосередньо через new.

📌 Ключова ідея:
Замість того, щоб писати:

Car car = new Sedan();
Ми пишемо:

Car car = CarFactory.createCar("sedan");

✅ Переваги:
Інкапсуляція створення — клієнт не знає деталей створення об'єктів.
Легше додавати нові типи — просто додаєш новий клас і дописуєш у фабрику.
Зменшує залежність від конкретних класів — працюєш через інтерфейси або абстракції.

❌ Недоліки:
Ускладнює код — додає додатковий рівень (фабрику), навіть для простих випадків.
Порушення принципу відкритості/закритості (OCP) — кожен раз при додаванні нового класу змінюєш фабрику.
Може вирости в "Бога" — централізована фабрика з купою if або switch.

📍 Де використовується:
Java API: Calendar.getInstance(), NumberFormat.getInstance()
Spring: ApplicationContext.getBean(...)
Android: LayoutInflater.from(context).inflate(...)

🤼 З чим порівнюють?
Патерн	                Відмінність
Factory Method	        Делегує створення об'єкта через метод підкласу
Abstract Factory	    Створює групу пов’язаних об'єктів без вказівки конкретних класів
Builder	                Розбиває процес створення на кроки, ідеально для складних об'єктів
Prototype	            Створює новий об'єкт через клонування існуючого

🧪 Маленька реалізація (Java)
Уявімо, що в нас є іграшки: "bear", "robot" та "doll".

// Product
interface Toy {
void play();
}

// Concrete products
class Bear implements Toy {
public void play() {
System.out.println("🧸 Playing with a bear!");
}
}

class Robot implements Toy {
public void play() {
System.out.println("🤖 Beep bop! Playing with a robot!");
}
}

class Doll implements Toy {
public void play() {
System.out.println("👧 Playing with a doll!");
}
}

// Factory
class ToyFactory {
public static Toy createToy(String type) {
return switch (type.toLowerCase()) {
case "bear" -> new Bear();
case "robot" -> new Robot();
case "doll" -> new Doll();
default -> throw new IllegalArgumentException("Unknown toy: " + type);
};
}
}

// Клієнт
public class Main {
public static void main(String[] args) {
Toy toy1 = ToyFactory.createToy("bear");
Toy toy2 = ToyFactory.createToy("robot");
Toy toy3 = ToyFactory.createToy("doll");

        toy1.play();
        toy2.play();
        toy3.play();
    }
}
🧠 Що тут відбувається:
Інтерфейс Toy задає поведінку.
ToyFactory відповідає за вирішення, який клас створити.
Main (клієнтський код) просто викликає фабрику, не знаючи деталей створення.

🔥 Хочеш ще приклад зі Spring або порівняння з Builder у дії — можу зробити прямо зараз!

-----------------------------------------------------------------------------------------

Супер, Віталію! 🔥 Покажу приклад Factory Pattern у стилі Spring з елементами конфігурації.
Такий підхід часто використовують, коли:

ти не хочеш вручну створювати об'єкти;
маєш багато залежностей;
хочеш контролювати логіку створення бінів (наприклад, залежно від профілю або типу).

☕ Приклад: Кавова машина
У нас є декілька типів напоїв: Latte, Espresso. Ми хочемо, щоб Spring створював відповідні об'єкти через фабрику.

📦 Структура:

src/
├─ config/
│  └─ DrinkFactory.java
├─ drinks/
│  ├─ Drink.java
│  ├─ Latte.java
│  └─ Espresso.java
├─ MainApp.java
└─ Application.java
1. 🔹 Ієрархія напоїв:

   // drinks/Drink.java
   public interface Drink {
   void serve();
   }

// drinks/Latte.java
public class Latte implements Drink {
public void serve() {
System.out.println("🥛 Serving a creamy Latte");
}
}

// drinks/Espresso.java
public class Espresso implements Drink {
public void serve() {
System.out.println("☕ Serving a strong Espresso");
}
}
2. 🏭 Factory через Spring Bean

   // config/DrinkFactory.java
   import drinks.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DrinkFactory {

    @Bean
    public Drink latte() {
        return new Latte();
    }

    @Bean
    public Drink espresso() {
        return new Espresso();
    }
}
Тут ми явно реєструємо фабричні методи, які створюють об'єкти типу Drink.

3. 🧩 Основний клас

   // MainApp.java
   import drinks.Drink;
   import org.springframework.context.ApplicationContext;
   import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
public static void main(String[] args) {
ApplicationContext context = new AnnotationConfigApplicationContext(config.DrinkFactory.class);

        Drink latte = (Drink) context.getBean("latte");
        Drink espresso = (Drink) context.getBean("espresso");

        latte.serve();
        espresso.serve();
    }
}
🧠 Що тут особливого?
Ми не викликаємо new взагалі.

Spring Container управляє фабрикою (через анотацію @Configuration і @Bean).

Якщо захочеш — можеш інжектити бін Drink або навіть створювати бін, що повертає тип в залежності від конфігурації.

💡 Альтернатива — використання FactoryBean<T>
Spring дозволяє ще більш кастомізовану фабрику:

@Component
public class DrinkFactoryBean implements FactoryBean<Drink> {

    @Override
    public Drink getObject() throws Exception {
        return new Latte(); // або динамічний вибір
    }

    @Override
    public Class<?> getObjectType() {
        return Drink.class;
    }
}
✨ Висновок:
✅ У Spring вся концепція DI базується на фабричному підході.

Що?	Як виглядає?
Factory Pattern	@Bean, FactoryBean, @Component
Клієнт використовує	ApplicationContext.getBean(...)
Spring сам створює об'єкти	І сам керує залежностями

🔧 Хочеш, покажу ще, як Factory працює разом із профілями (наприклад, dev/prod) або зробимо приклад з параметром в конструкторі?

--------------------------------------------------------------------------------------------------

Чудово, Віталію! Зараз покажу, як Factory Pattern у Spring можна поєднувати з профілями (@Profile) — щоб створювати різні реалізації в залежності від середовища: "dev", "prod", "test" тощо.

☕ Сценарій: кав'ярня в різних країнах
Уявімо, що:

У dev-оточенні ми хочемо пити дешеву каву (CheapCoffee),

А в prod-оточенні — преміум каву (PremiumCoffee).

📦 Структура:

src/
├─ drinks/
│  ├─ Drink.java
│  ├─ CheapCoffee.java
│  └─ PremiumCoffee.java
├─ config/
│  └─ CoffeeFactoryConfig.java
├─ Application.java
└─ MainApp.java
1. 🔸 Інтерфейс та реалізації

   // drinks/Drink.java
   public interface Drink {
   void serve();
   }

// drinks/CheapCoffee.java
public class CheapCoffee implements Drink {
public void serve() {
System.out.println("☕ Serving cheap coffee for devs 💻");
}
}

// drinks/PremiumCoffee.java
public class PremiumCoffee implements Drink {
public void serve() {
System.out.println("☕ Serving premium coffee for production 👑");
}
}
2. ⚙️ Конфігурація з профілями

   // config/CoffeeFactoryConfig.java
   import drinks.*;
   import org.springframework.context.annotation.*;

@Configuration
public class CoffeeFactoryConfig {

    @Bean
    @Profile("dev")
    public Drink devCoffee() {
        return new CheapCoffee();
    }

    @Bean
    @Profile("prod")
    public Drink prodCoffee() {
        return new PremiumCoffee();
    }
}
Тут ми вказуємо, який бін буде створено в залежності від активного профілю.

3. 🚀 MainApp — запуск

   // MainApp.java
   import drinks.Drink;
   import org.springframework.context.annotation.*;

public class MainApp {
public static void main(String[] args) {
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.getEnvironment().setActiveProfiles("prod"); // Спробуй змінити на "dev"
        context.register(config.CoffeeFactoryConfig.class);
        context.refresh();

        Drink coffee = context.getBean(Drink.class);
        coffee.serve();

        context.close();
    }
}
📌 Результат:
🟢 Якщо профіль "dev" — консоль видає:

☕ Serving cheap coffee for devs 💻
🟢 Якщо профіль "prod":

☕ Serving premium coffee for production 👑
💡 Що тут важливе:
Особливість	Пояснення
@Profile("...")	Бін буде створено лише якщо активний відповідний профіль.
context.getEnvironment().setActiveProfiles(...)	Вручну активуємо профіль (у реальному Spring Boot — через application.properties).
Factory Pattern + Spring Profile	Дозволяє створювати контекстно-залежні об’єкти без змін у коді клієнта.

🌐 А в Spring Boot?
У Spring Boot ти просто пишеш у application.properties:

spring.profiles.active=prod
І Spring автоматично активує потрібний профіль 🙌

🔧 Хочеш наступним кроком:
як створити бін із параметрами у фабриці?

чи реалізацію з ApplicationContextAware або FactoryBean?

Скажи — і я зроблю! ☕✨

------------------------------------------------------------------------------

Гаразд, Віталію! 💡 Давай розглянемо створення біна у фабриці з параметрами — тобто, ситуацію, коли потрібно передати значення в конструктор під час створення об’єкта через фабрику.

Це не класичний підхід Spring через DI, але іноді потрібно створювати об'єкти динамічно — наприклад, з ID, конфігурацією, налаштуванням, яке неможливо прокинути через @Autowired.

📦 Сценарій: Кастомний сік
У нас є клас Juice, і ми хочемо створювати його з динамічними параметрами — наприклад, "apple" або "orange".

1. 🔸 Клас Juice

   public class Juice {
   private final String flavor;

   public Juice(String flavor) {
   this.flavor = flavor;
   }

   public void serve() {
   System.out.println("🧃 Serving " + flavor + " juice!");
   }
   }
2. 🏭 Фабрика з параметром

   import org.springframework.stereotype.Component;

@Component
public class JuiceFactory {

    public Juice createJuice(String flavor) {
        return new Juice(flavor);
    }
}
Фабрика має метод createJuice(String flavor), який повертає об’єкт з конкретним параметром.

3. 🚀 Клієнтський код

   import org.springframework.context.ApplicationContext;
   import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
public static void main(String[] args) {
ApplicationContext context = new AnnotationConfigApplicationContext("your.package.name");

        JuiceFactory factory = context.getBean(JuiceFactory.class);

        Juice appleJuice = factory.createJuice("apple");
        Juice mangoJuice = factory.createJuice("mango");

        appleJuice.serve();
        mangoJuice.serve();
    }
}
🧠 Що відбувається:
JuiceFactory — це Spring-бін (керується контейнером),

але об’єкти Juice створюються вручну всередині методу,

ми можемо передавати будь-які параметри в метод фабрики (це неможливо напряму через @Autowired).

🔁 Варіант із ApplicationContext.getBean(..., args...)
Spring дозволяє створити бін із аргументами навіть без фабрики, напряму:

Juice juice = context.getBean(Juice.class, "cherry");
Але для цього потрібен @Component + конструктор з аргументом:

@Component
@Scope("prototype") // важливо!
public class Juice {
private final String flavor;

    public Juice(String flavor) {
        this.flavor = flavor;
    }

    public void serve() {
        System.out.println("🧃 Serving " + flavor + " juice!");
    }
}
Бін повинен бути @Scope("prototype"), інакше Spring буде намагатися створити його при старті без параметрів і впаде з помилкою.

✨ Висновки
Підхід	Коли використовувати
@Bean або @Component	Коли немає параметрів
Фабрика з методом createXxx(String)	Коли треба параметри
context.getBean(X.class, arg...)	Коли хочеш динаміку через Spring (прототипний бін)

🧪 Хочеш зробимо щось схоже на "JuiceService", де фабрика зберігає історію замовлень?
Або приклад де фабрика створює об'єкт із параметром + логування в файл?

------------------------------------------

Супер! 🚀 Тоді зробимо "JuiceService", який:
створює соки через фабрику з параметрами (наприклад, "apple", "cherry"),
зберігає історію замовлень у списку,
дозволяє переглянути всі замовлення.

🔧 Структура компонентів:

src/
├─ drinks/
│  ├─ Juice.java
│  └─ JuiceFactory.java
├─ service/
│  └─ JuiceService.java
├─ MainApp.java
1. 🧃 Клас Juice
   java
   Copy
   Edit
   package drinks;

public class Juice {
private final String flavor;

    public Juice(String flavor) {
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    public void serve() {
        System.out.println("🧃 Serving " + flavor + " juice!");
    }

    @Override
    public String toString() {
        return "Juice{" +
                "flavor='" + flavor + '\'' +
                '}';
    }
}
2. 🏭 JuiceFactory

   package drinks;

import org.springframework.stereotype.Component;

@Component
public class JuiceFactory {
public Juice createJuice(String flavor) {
System.out.println("🔧 Creating juice: " + flavor);
return new Juice(flavor);
}
}
3. 🧾 JuiceService з історією

   package service;

import drinks.Juice;
import drinks.JuiceFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JuiceService {

    private final JuiceFactory factory;
    private final List<Juice> orderHistory = new ArrayList<>();

    public JuiceService(JuiceFactory factory) {
        this.factory = factory;🔥 Полетіли, Віталію! Зараз перетворимо нашу сокову систему 🍹 на Spring Boot REST API, через який можна буде:

📥 Замовити сік: POST /juice/order?flavor=apple

📜 Отримати список усіх замовлень: GET /juice/history

🛠 Що будемо мати:
lua
Copy
Edit
src/
├─ drinks/
│  ├─ Juice.java
│  └─ JuiceFactory.java
├─ service/
│  ├─ JuiceService.java
│  └─ JuiceLogger.java
├─ controller/
│  └─ JuiceController.java   <-- новий контролер
├─ Application.java          <-- Spring Boot starter
✅ 1. Application.java — точка входу
java
Copy
Edit
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"drinks", "service", "controller"})
public class Application {
public static void main(String[] args) {
SpringApplication.run(Application.class, args);
}
}
🌍 2. JuiceController — REST API

package controller;

import drinks.Juice;
import org.springframework.web.bind.annotation.*;
import service.JuiceService;

import java.util.List;

@RestController
@RequestMapping("/juice")
public class JuiceController {

    private final JuiceService service;

    public JuiceController(JuiceService service) {
        this.service = service;
    }

    @PostMapping("/order")
    public String orderJuice(@RequestParam String flavor) {
        Juice juice = service.orderJuice(flavor);
        return "✅ Ordered: " + juice.getFlavor() + " juice!";
    }

    @GetMapping("/history")
    public List<Juice> getOrderHistory() {
        return service.getOrderHistory();
    }
}
🌐 Тестування (через Postman / браузер):
✅ Замовлення соку:

POST http://localhost:8080/juice/order?flavor=banana
Response:

✅ Ordered: banana juice!
📜 Перегляд історії:

GET http://localhost:8080/juice/history
Response:

[
{
"flavor": "apple"
},
{
"flavor": "mango"
},
{
"flavor": "banana"
}
]
⚙️ Як запустити:
Створи pom.xml (можу дати).
Створи Application.java як головний клас.
Запусти через IDE або mvn spring-boot:run.
Переходь в Postman або браузер 🧪

💡 Що далі?
✅ Додати ID та час до Juice + логувати це.
✅ Зберігати все в MySQL або H2 базі.
✅ Додати /juice/delete, /juice/latest, /juice/byUser.
✅ Перевірка на неправильні смаки.
✅ Авторизація по токену (Spring Security).
✅ Swagger UI для документації.

Готовий зробити pom.xml, додати H2 базу або зробити Juice сутністю з @Entity? 😎

--------------------------------------------------------------------

    }

    public Juice orderJuice(String flavor) {
        Juice juice = factory.createJuice(flavor);
        orderHistory.add(juice);
        juice.serve();
        return juice;
    }

    public List<Juice> getOrderHistory() {
        return orderHistory;
    }
}
4. 🚀 MainApp
   java
   Copy
   Edit
   import org.springframework.context.annotation.AnnotationConfigApplicationContext;
   import service.JuiceService;

public class MainApp {
public static void main(String[] args) {
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("drinks", "service");

        JuiceService service = context.getBean(JuiceService.class);

        service.orderJuice("apple");
        service.orderJuice("mango");
        service.orderJuice("blackberry");

        System.out.println("\n📜 Order history:");
        service.getOrderHistory().forEach(System.out::println);

        context.close();
    }
}
🧠 Що тут відбувається?
Компонент	Роль
Juice	Об'єкт-сік з полем flavor
JuiceFactory	Створює об'єкти Juice з параметрами
JuiceService	Відповідає за логіку: замовлення, зберігання історії, перегляд
MainApp	Клієнтський код для тестування всього процесу

🧪 Вивід:
bash
Copy
Edit
🔧 Creating juice: apple
🧃 Serving apple juice!
🔧 Creating juice: mango
🧃 Serving mango juice!
🔧 Creating juice: blackberry
🧃 Serving blackberry juice!

📜 Order history:
Juice{flavor='apple'}
Juice{flavor='mango'}
Juice{flavor='blackberry'}
🔥 Хочеш додати логування в файл, зберігання в базу, або зробити REST-контролер для замовлення соків?
Це можна розширити до повного CRUD або REST API!

------------------------------------------------------------------------------------------------

Класно! 🔥 Давай додамо логування замовлень у файл — кожного разу, коли створюється сік, ми записуємо:

дату й час,
тип соку,
хто замовив (для простоти буде просто user123, але ти можеш зробити динамічно).

🔧 Що змінимо:
Створимо клас JuiceLogger, який пише в лог-файл.

Використаємо його в JuiceService.

📁 Структура оновлюється:

src/
├─ drinks/
│  ├─ Juice.java
│  └─ JuiceFactory.java
├─ service/
│  ├─ JuiceService.java
│  └─ JuiceLogger.java  <-- новий
├─ MainApp.java
🆕 1. JuiceLogger — простий логер у файл

package service;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JuiceLogger {
private static final String LOG_FILE = "juice-orders.log";

    public void logOrder(String flavor, String username) {
        String entry = String.format("[%s] Ordered: %s juice by %s",
                LocalDateTime.now(), flavor, username);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("❌ Failed to log order: " + e.getMessage());
        }
    }
}
🧾 2. Оновлюємо JuiceService

package service;

import drinks.Juice;
import drinks.JuiceFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JuiceService {

    private final JuiceFactory factory;
    private final JuiceLogger logger;
    private final List<Juice> orderHistory = new ArrayList<>();

    public JuiceService(JuiceFactory factory, JuiceLogger logger) {
        this.factory = factory;
        this.logger = logger;
    }

    public Juice orderJuice(String flavor) {
        Juice juice = factory.createJuice(flavor);
        orderHistory.add(juice);
        logger.logOrder(flavor, "user123"); // або зробимо параметр у майбутньому
        juice.serve();
        return juice;
    }

    public List<Juice> getOrderHistory() {
        return orderHistory;
    }
}
✅ MainApp залишається тим самим

public class MainApp {
public static void main(String[] args) {
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("drinks", "service");

        JuiceService service = context.getBean(JuiceService.class);

        service.orderJuice("apple");
        service.orderJuice("mango");
        service.orderJuice("blackberry");

        System.out.println("\n📜 Order history:");
        service.getOrderHistory().forEach(System.out::println);

        context.close();
    }
}
📝 Вміст juice-orders.log після запуску:

[2025-06-04T09:46:13.915378] Ordered: apple juice by user123
[2025-06-04T09:46:13.923411] Ordered: mango juice by user123
[2025-06-04T09:46:13.923723] Ordered: blackberry juice by user123
🌟 Ідеї для розширення:
зберігати в кого які смаки (мультикористувачі),
зробити логування через slf4j + logback,
зберігати в SQLite або MySQL,
зробити REST API: POST /order?flavor=apple

Хочеш зробимо наступним кроком REST API для замовлення соків у стилі Spring Boot? Це вже буде повноцінна міні-система 🍹

-----------------------------------------------------------------

🔥 Полетіли, Віталію! Зараз перетворимо нашу сокову систему 🍹 на Spring Boot REST API, через який можна буде:

📥 Замовити сік: POST /juice/order?flavor=apple

📜 Отримати список усіх замовлень: GET /juice/history

🛠 Що будемо мати:

src/
├─ drinks/
│  ├─ Juice.java
│  └─ JuiceFactory.java
├─ service/
│  ├─ JuiceService.java
│  └─ JuiceLogger.java
├─ controller/
│  └─ JuiceController.java   <-- новий контролер
├─ Application.java          <-- Spring Boot starter
✅ 1. Application.java — точка входу

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"drinks", "service", "controller"})
public class Application {
public static void main(String[] args) {
SpringApplication.run(Application.class, args);
}
}
🌍 2. JuiceController — REST API

package controller;

import drinks.Juice;
import org.springframework.web.bind.annotation.*;
import service.JuiceService;

import java.util.List;

@RestController
@RequestMapping("/juice")
public class JuiceController {

    private final JuiceService service;

    public JuiceController(JuiceService service) {
        this.service = service;
    }

    @PostMapping("/order")
    public String orderJuice(@RequestParam String flavor) {
        Juice juice = service.orderJuice(flavor);
        return "✅ Ordered: " + juice.getFlavor() + " juice!";
    }

    @GetMapping("/history")
    public List<Juice> getOrderHistory() {
        return service.getOrderHistory();
    }
}
🌐 Тестування (через Postman / браузер):
✅ Замовлення соку:

POST http://localhost:8080/juice/order?flavor=banana
Response:

✅ Ordered: banana juice!
📜 Перегляд історії:

GET http://localhost:8080/juice/history
Response:

[
{
"flavor": "apple"
},
{
"flavor": "mango"
},
{
"flavor": "banana"
}
]
⚙️ Як запустити:
Створи pom.xml (можу дати).

Створи Application.java як головний клас.

Запусти через IDE або mvn spring-boot:run.

Переходь в Postman або браузер 🧪

💡 Що далі?
✅ Додати ID та час до Juice + логувати це.
✅ Зберігати все в MySQL або H2 базі.
✅ Додати /juice/delete, /juice/latest, /juice/byUser.
✅ Перевірка на неправильні смаки.
✅ Авторизація по токену (Spring Security).
✅ Swagger UI для документації.

Готовий зробити pom.xml, додати H2 базу або зробити Juice сутністю з @Entity? 😎

Продовження або початок https://chatgpt.com/c/683fe26d-0f90-8009-8c82-c2c0cd1f0700 