import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: roy
 * Date: 11/25/14
 * Time: 9:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeepIterator1<T> implements Iterator<T> {
    // A reference to the item which will be returned during
    // the next call to next().
    private T nextItem;
    private final Stack<Iterator<?>> stack = new Stack<Iterator<?>>();

    public DeepIterator1(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Can't iterate over a null collection.");
        }
        stack.push(collection.iterator());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean hasNext() {
        if (nextItem != null) {
            return true;
        }

        while (!stack.isEmpty()) {
            Iterator<?> iter = stack.peek();
            if (iter.hasNext()) {
                Object item = iter.next();
                if (item instanceof Collection<?>) {
                    stack.push(((Collection<?>) item).iterator());
                } else {
                    nextItem = (T) item;
                    return true;
                }
            } else {
                stack.pop();
            }
        }

        return false;
    }

    @Override
    public T next() {
        if (hasNext()) {
            T toReturn = nextItem;
            nextItem = null;
            return toReturn;
        }

        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args){
        List list1 = new LinkedList();
        list1.add(0);
        list1.add(new LinkedList<Integer>());
        list1.add(1);
        list1.add(new LinkedList<Integer>());
        List list2 = new LinkedList();
        list2.add(list1);
        list2.add(2);

        List<Integer> newList = new LinkedList<Integer>();
        DeepIterator1 di = new DeepIterator1(list2);

        while(di.hasNext()) {
            newList.add((Integer)di.next());
        }

        // Use assert if you want
        System.out.println(String.format("newList size is %d, expcted 3", newList.size()));

        list1 = new LinkedList();
        newList = new LinkedList<Integer>();
        di = new DeepIterator1(list1);
        while(di.hasNext()) {
            newList.add((Integer)di.next());
        }

        // assertTrue(newList.size() == 0);
        System.out.println(String.format("newList size is %d, expcted 0", newList.size()));

        list1 = new LinkedList();
        list1.add(new LinkedList<Integer>());
        list1.add(1);
        list1.add(new LinkedList<Integer>());
        list2 = new LinkedList();
        list2.add(list1);
        list2.add(2);
        List<Integer> list3 = new LinkedList<Integer>();
        list3.add(3);
        list3.add(4);
        list3.add(5);
        List list4 = new LinkedList();
        list4.add(list2);
        list4.add(list3);

        newList = new LinkedList<Integer>();

        di = new DeepIterator1(list4);
        while(di.hasNext()) {
            newList.add((Integer)di.next());
        }

        // assertTrue(newList.size() == 5);
        System.out.println(String.format("newList size is %d, expcted 5", newList.size()));


        /**
         ----> 5
         |
         ---- 3 -> 4 -> L -> 6
         |
         1 -> 2 -> L -> 7-> 8
         */
        list3 = new LinkedList<Integer>();
        list3.add(3);

        list2 = new LinkedList<Integer>();
        list2.add(2);
        list2.add(list3);
        list2.add(4);

        list1 = new LinkedList<Integer>();
        list1.add(1);
        list1.add(list2);
        list1.add(5);
        list1.add(6);

        di = new DeepIterator1(list1);
        newList = new LinkedList<Integer>();
        while(di.hasNext()) {
            newList.add((Integer)di.next());
        }
        System.out.println(String.format("newList size is %d, expcted 6", newList.size()));
    }
}
