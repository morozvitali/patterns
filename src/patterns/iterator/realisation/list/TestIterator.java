package patterns.iterator.realisation.list;

public class TestIterator {
    public static void main(String[] args) {
        MyList<String> list = new MyList<>(8);
        list.add("Java");
        list.add("Pithon");
        list.add("C++");
        list.add("Php");

        for (String lang : list) {
            System.out.println(lang);
        }
    }
}
