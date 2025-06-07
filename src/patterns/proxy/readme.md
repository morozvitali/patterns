🔷 Що таке Proxy Pattern?
Proxy (Проксі) — це структурний патерн, який створює
заступника для іншого об'єкта з тією ж інтерфейсною поведінкою.

Простими словами:
Проксі — це «обгортка» навколо реального об’єкта.
Він контролює доступ до нього, може додавати поведінку
(логування, кешування, безпека, тощо), не змінюючи сам об’єкт.

🔷 Типи Proxy
Virtual Proxy — відкладає створення "важкого"
об'єкта до першого запиту. (лінива ініціалізація)
Protection Proxy — перевіряє права доступу.
Remote Proxy — локальний представник віддаленого об'єкта.
Smart Proxy — додає додаткову поведінку при доступі
до об'єкта (наприклад, лічильники, логування, синхронізація).

🔷 Структура (діаграма UML)

Client --> Proxy --> RealSubject
| --> інтерфейс Subject


🔷 Java-приклад: Простий Protection Proxy

1. Інтерфейс

   public interface Image {
   void display();
   }

2. Реальний об'єкт

   public class RealImage implements Image {
   private String filename;

   public RealImage(String filename) {
   this.filename = filename;
   loadFromDisk(); // почати дуже повільну операцію
   }

   private void loadFromDisk() {
   System.out.println("Loading " + filename);
   }

   @Override
   public void display() {
   System.out.println("Displaying " + filename);
   }
   }

3. Проксі-обгортка

   public class ProxyImage implements Image {
   private RealImage realImage;
   private String filename;

   public ProxyImage(String filename) {
   this.filename = filename;
   }

   @Override
   public void display() {
   if (realImage == null) {
   realImage = new RealImage(filename); 
// лише при першому виклику
   }
   realImage.display();
   }
   }
4. Клієнтський код

   public class Main {
   public static void main(String[] args) {
   Image image = new ProxyImage("cat_photo.jpg");

        // завантаження відкладене
        image.display(); // Loading cat_photo.jpg \n Displaying cat_photo.jpg
        image.display(); // Displaying cat_photo.jpg (без повторного завантаження)
   }
   }
   🔷 Переваги
   ✅ Лінива ініціалізація (економія ресурсів)
   ✅ Безпечний контроль доступу
   ✅ Можливість логування, кешування, ретраїв тощо
   ✅ Можна легко підмінити об'єкт у рантаймі

🔷 Недоліки
❌ Ускладнює код (нові класи, нові об'єкти)
❌ Може ввести в оману щодо джерела поведінки
❌ Перформанс-проблеми при неправильній реалізації
❌ Не завжди потрібен — іноді краще використовувати
інші патерни (наприклад, Decorator або Facade)

🔷 Де використовується?
✅ Spring AOP (через проксі реалізується логування,
транзакції, сек’юріті)
✅ Hibernate (лениве завантаження)
✅ Java RMI (Remote Proxy)
✅ Security Frameworks — обмеження доступу на рівні методів
✅ Кешування — збереження результатів методів
✅ Smart UI — відкладене завантаження зображень, файлів

🔷 У Java можна використовувати:
Статичний проксі (ручна реалізація, як вище)

Динамічний проксі:

java.lang.reflect.Proxy (інтерфейси)

CGLIB (класи)

🔷 Приклад динамічного проксі через Reflection

import java.lang.reflect.*;

interface Service {
void run();
}

class RealService implements Service {
public void run() {
System.out.println("RealService is running");
}
}

class ProxyHandler implements InvocationHandler {
private Object realObject;

    public ProxyHandler(Object realObject) {
        this.realObject = realObject;
    }

    public Object invoke(Object proxy, Method method, 
                                 Object[] args) throws Throwable {
        System.out.println("Before method");
        Object result = method.invoke(realObject, args);
        System.out.println("After method");
        return result;
    }
}

public class DynamicProxyDemo {
public static void main(String[] args) {
Service real = new RealService();
Service proxy = (Service) Proxy.newProxyInstance(
real.getClass().getClassLoader(),
new Class[]{Service.class},
new ProxyHandler(real)
);

        proxy.run(); // додасть логування перед/після виклику
    }
}
🔶 Висновок
Proxy Pattern — один із найпрактичніших патернів у Java.
Його сила — в делегуванні, контролі та розширенні
поведінки без модифікації реального класу.

Якщо хочеш:

🔹 окремі приклади для Spring AOP Proxy

🔹 порівняння Proxy vs Decorator

🔹 задачі на тренування

кажи — і буде!
Полетіли далі, як вчений з мікроскопом — розглядати кожен байт 👨‍🔬🪄

