Патерн Builder — один із найпрактичніших, коли йдеться про створення складних об’єктів із багатьма параметрами. Розглянемо його з усіх боків:

🔨 реалізація,
🧠 проблеми, які вирішує,
⚠ недоліки,
⚔ порівняння зі схожими патернами (Factory, Prototype, Fluent API).

🧩 Проблема, яку вирішує Builder
Уявімо клас Pizza:

public class Pizza {
private final String dough;
private final String sauce;
private final String topping;

public Pizza(String dough, String sauce, String topping) {
        this.dough = dough;
        this.sauce = sauce;
        this.topping = topping;
    }
}
Спроба створити об’єкт:

Pizza pizza = new Pizza("thin", "tomato", "cheese");
Проблеми:

якщо 10 параметрів — легко помилитися;
параметри одного типу → плутанина;
частина параметрів необов’язкова (наприклад, соус чи топінг).

✅ Builder: реалізація (покрокова)
🔹 Клас Pizza

public class Pizza {
private final String dough;
private final String sauce;
private final String topping;

private Pizza(Builder builder) {
        this.dough = builder.dough;
        this.sauce = builder.sauce;
        this.topping = builder.topping;
    }

public static class Builder {
        private String dough;
        private String sauce;
        private String topping;

public Builder setDough(String dough) {
            this.dough = dough;
            return this;
        }

public Builder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

public Builder setTopping(String topping) {
            this.topping = topping;
            return this;
        }

public Pizza build() {
            return new Pizza(this);
        }
    }
}

🔹 Створення об'єкта

Pizza pizza = new Pizza.Builder()
.setDough("thin crust")
.setSauce("pesto")
.setTopping("mozzarella")
.build();

🟢 Переваги
гнучкість (не треба передавати всі поля);

безпечність (типізовано);
читабельність (ліпше, ніж new Pizza("thin", null, "cheese"));
immutable об’єкт (усі поля final);
чудово працює з флюентним API.

❗ Недоліки
трохи більше коду (але легко автоматизується в IDE);
не завжди виправданий для простих POJO-класів;
не забезпечує логіку перевірки (якщо вона не вбудована у build()).

⚔ Порівняння з іншими патернами
Патерн	Суть і застосування
Builder	Створення складного об’єкта покроково. Зручно, коли багато параметрів чи їх варіацій
Factory	Вибір підкласу/реалізації (наприклад, ShapeFactory.create("circle"))
Prototype	Клонування існуючого об’єкта
Fluent API	Часто поєднується з Builder, додає синтаксичну зручність

🔁 Builder vs Factory
Factory повертає різні об'єкти залежно від умов.

Builder повертає той самий тип об’єкта, але з кастомізацією всередині.

📎 Варіації Builder
Ломбок (@Builder):

@Builder
public class Pizza {
private String dough;
private String sauce;
private String topping;
}

Director: окремий клас, який "керує" процесом побудови (частіше в С++ чи складних Java-продуктах).

🔍 Коли застосовувати
об’єкт має багато параметрів, частина з яких необов’язкова;
потрібно створювати immutable об’єкти;
хочеш зрозумілий код при побудові об’єкта (без мільйонів конструкторів).

