🧩 Що таке Facade Pattern?
Facade (Фасад) — структурний патерн проєктування, 
що надає спрощений інтерфейс до складної підсистеми. 
Він приховує внутрішню складність і дозволяє клієнтам 
взаємодіяти з нею через зручний "фасадний" клас.

📌 Основна ідея:
"Зроби зручну вхідну точку до великої підсистеми."

📦 Приклад із життя:
Уяви собі комп'ютер, який складається з:
Блоку живлення
Жорсткого диска
Оперативної пам'яті
Процесора

Коли ти натискаєш "Power", ти не думаєш, що треба 
вручну вмикати кожен елемент. Це і є Facade — 
він ховає внутрішню логіку та надає одну кнопку.

✅ Використання у Java:
Крок 1. Створимо складні компоненти:

class CPU {
public void freeze() {
System.out.println("Freezing processor...");
}

public void jump(long position) {
        System.out.println("Jumping to position " + position);
    }

public void execute() {
        System.out.println("Executing instructions...");
    }
}

class Memory {
public void load(long position, String data) {
System.out.println("Loading data '" + data + "' into position " + position);
}
}

class HardDrive {
public String read(long lba, int size) {
return "OS Bootloader";
}
}

Крок 2. Створимо Facade:

class ComputerFacade {
private CPU cpu;
private Memory memory;
private HardDrive hardDrive;

public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

public void start() {
        cpu.freeze();
        String bootData = hardDrive.read(0, 1024);
        memory.load(0, bootData);
        cpu.jump(0);
        cpu.execute();
    }
}

Крок 3. Клієнт користується лише фасадом:

public class Main {
public static void main(String[] args) {
ComputerFacade computer = new ComputerFacade();
computer.start(); // Одним викликом запускаємо 
всю складну систему
}
}

🧠 Де застосовується?
Коли система має багато компонентів із складною взаємодією

Коли потрібно ізолювати клієнтський код від 
внутрішньої структури підсистеми

У великих бібліотеках (Spring, Hibernate, JDBC) 
фасади часто використовуються для спрощення API

✅ Переваги:
✔️ Простота для клієнтів
✔️ Менше залежностей від внутрішніх класів
✔️ Краща структурованість коду

❌ Недоліки:
❗ Фасад може стати "Богом" — знати все про всіх
❗ Приховує функціонал, який може знадобитися більш 
глибоким користувачам

🔁 Порівняння з іншими:
Патерн	Схожість	Відмінність
Adapter	Також створює інтерфейс	Перетворює один інтерфейс в інший
Facade	Спрощує інтерфейс	Не змінює інтерфейси, лише агрегує
Mediator	Організовує взаємодію між об'єктами	Mediator має двосторонню взаємодію
Decorator	Обгортає логіку	Facade не додає поведінку, лише спрощує доступ

https://chatgpt.com/c/68528e29-6d10-8009-9840-f461b1db6128