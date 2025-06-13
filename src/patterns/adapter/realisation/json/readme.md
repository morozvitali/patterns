🎯 Давай зробимо адаптер для роботи з JSON-бібліотеками, наприклад — коли ми хочемо, щоб наша система працювала з різними JSON-бібліотеками, але не змінювала основну логіку.

🔧 Сценарій:
Уяви, що твоя система використовує інтерфейс JsonParser, а от сторонні бібліотеки — наприклад:

Jackson
Gson

мають різні способи парсингу JSON.

🔍 Ціль:
Створити адаптер, який дозволить нам підключати будь-яку JSON-бібліотеку (наприклад, Gson), але при цьому вся наша система буде працювати з єдиним інтерфейсом JsonParser.

🪜 Кроки:

✅ Крок 1: Створюємо інтерфейс, який очікує наша система
Це наш Target — інтерфейс, з яким працює клієнт.

public interface JsonParser {
Map<String, Object> parse(String json);
}

✅ Крок 2: Створюємо клас-адаптер для Gson
Тут ми пристосовуємо чужий інтерфейс (Gson) до нашого інтерфейсу JsonParser.

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonAdapter implements JsonParser {
private final Gson gson = new Gson();

@Override
    public Map<String, Object> parse(String json) {
        return gson.fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());
    }
}
✅ Крок 3: Симуляція клієнтського коду
Цей код нічого не знає про Gson, Jackson чи інші. Він працює тільки з JsonParser.

public class JsonService {
private final JsonParser parser;

public JsonService(JsonParser parser) {
        this.parser = parser;
    }

public void printParsedJson(String json) {
        Map<String, Object> data = parser.parse(json);
        System.out.println("Parsed JSON: " + data);
    }
}

✅ Крок 4: Тестування адаптера

public class Main {
public static void main(String[] args) {
String json = "{\"name\":\"Віталій\",\"age\":30}";

JsonParser parser = new GsonAdapter(); // адаптер
JsonService service = new JsonService(parser); // клієнт

service.printParsedJson(json); // працює з єдиним інтерфейсом
    }
}
🎁 Що ми досягли:

Елемент	Роль
JsonParser	Target (очікуваний інтерфейс)
GsonAdapter	Adapter (адаптер до Gson)
Gson	Adaptee (бібліотека, яку адаптуємо)
JsonService	Client (клієнтський код)

📌 Далі можемо:
Додати JacksonAdapter — і показати як легко замінити бібліотеку
Зробити Unit-тест
Або реалізувати двосторонній адаптер — який і парсить, і будує JSON
Готовий до наступного кроку? 😎


https://chatgpt.com/c/684be3cb-8d7c-8009-9954-36fd49be9568






