package patterns.iterator.realisation.list;

import java.util.Iterator;

public class MyList<T> implements Iterable <T> {
    private T[] items;
    private int size = 0;

    public MyList (int capacity) {
        items = (T[]) new Object[capacity]; // це конструктор
        //який змінній items присвоює масив з кількістю
    }

    public void add (T item) {
        if (size == items.length) { // перевірка розміру масива
            throw new RuntimeException("List Is full");
        }
        items [size++] = item;
    }

    public Iterator <T> iterator () {return new MyIterator();}

    private class MyIterator implements Iterator <T> {
        private int index = 0;
        private boolean canRemove = false;

        @Override
        public boolean hasNext () {
            return index < size;
        }

        @Override
        public T next() {
            canRemove = true;
            return items[index++];
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Remove called without next()");
            }

            for (int i=index-1; i<size-1; i++) {
                items [i] = items [i+1];
            }

            items [--size] = null;
            index--;
            canRemove = false;
        }
    }
}