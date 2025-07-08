Ось вправа для самостійної реалізації патерну Bridge, 
у якій ти сам сконструюєш «міст» між абстракцією та реалізацією.
Я все підготував як шаблон і кроки — бери як конструктор LEGO 🧱

🧃 Тематика: Соковитискачі (Juicer) та Плоди (Fruit)
У нас є різні типи соковитискачів: ручна, електрична.
І є різні типи фруктів: апельсин, яблуко, гранат.

Твоя мета: зробити систему, де соковитискач незалежно 
працює з будь-яким фруктом.

📦 Крок 1. Створи інтерфейс Fruit (реалізація)

        public interface Fruit {
        String getJuice();
        }

🍎 Крок 2. Створи кілька реалізацій фруктів

        public class Apple implements Fruit {
        @Override
        public String getJuice() {
        return "Яблучний сік";
        }
        }

        public class Orange implements Fruit {
        @Override
        public String getJuice() {
        return "Апельсиновий сік";
        }
        }

(можеш додати Pomegranate, Kiwi тощо)

⚙️ Крок 3. Створи абстракцію — Juicer

        public abstract class Juicer {
        protected Fruit fruit;
    
        public Juicer(Fruit fruit) {
            this.fruit = fruit;
        }

        public abstract void makeJuice();
        }

🔋 Крок 4. Створи реалізації соковитискачів

        public class ManualJuicer extends Juicer {
        public ManualJuicer(Fruit fruit) {
        super(fruit);
        }

        @Override
        public void makeJuice() {
            System.out.println("🔧 Ручна соковитискачка видавила: " 
        + fruit.getJuice());
        }
        }

        public class ElectricJuicer extends Juicer {
        public ElectricJuicer(Fruit fruit) {
        super(fruit);
        }

        @Override
        public void makeJuice() {
            System.out.println("⚡ Електрична соковитискачка видавила: " 
        + fruit.getJuice());
        }
        }   
🧪 Крок 5. Протестуй!

        public class Main {
        public static void main(String[] args) {
        Juicer juicer1 = new ManualJuicer(new Apple());
        Juicer juicer2 = new ElectricJuicer(new Orange());

        juicer1.makeJuice(); // 🔧 Ручна соковитискачка 
                             //     видавила: Яблучний сік
        juicer2.makeJuice(); // ⚡ Електрична соковитискачка 
                             //   видавила: Апельсиновий сік
    }
}

🎯 Завдання для тебе
✅ Реалізуй усе за шаблоном
✳️ Додай ще один фрукт (наприклад, Pineapple)
✳️ Додай соковитискачку з підсиленням ("TurboJuicer"), 
яка кричить: TURBO! 🍍
✳️ Замість System.out.println спробуй повертати рядки, 
щоб мати більше контролю

📌 Підсумок
Ця вправа дозволяє:
Повністю розділити абстракцію (Juicer) та реалізацію (Fruit)
Додавати нові фрукти чи нові соковитискачки 
без зміни вже існуючих класів 
Застосувати Bridge Pattern у практичному прикладі
Якщо хочеш — я зроблю візуальну схему або 
дам міні-тест 🧪 для перевірки!

https://chatgpt.com/c/686d1347-af28-8009-bb79-cbfec19b5b01
