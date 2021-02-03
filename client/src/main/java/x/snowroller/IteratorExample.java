package x.snowroller;

import java.util.Iterator;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {
        MyCollection myCollection = new MyCollection();

        for (var val : myCollection) {
            System.out.println(val);
        }


        Iterator<Integer> iterator = myCollection.iterator();
        while(iterator.hasNext())
        {
            var value = iterator.next();
            System.out.println(value);
        }
    }
}

class MyCollection implements Iterable<Integer> {

    private List<Integer> list = List.of(1,2,3);
    public MyCollection() {
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyCollectionIterator(list);
    }
}

class MyCollectionIterator implements Iterator<Integer> {

    private List<Integer> list;
    private int position;

    public MyCollectionIterator(List<Integer> array) {
        list = array;
    }

    @Override
    public boolean hasNext() {
        if (position < list.size())
            return true;
        return false;
    }

    @Override
    public Integer next() {
        return list.get(position++);
    }
}



