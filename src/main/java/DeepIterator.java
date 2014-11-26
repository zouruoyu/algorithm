import java.util.*;

public class DeepIterator<T> implements Iterator<T> {
    private Stack<Iterator<T>> stack = new Stack<Iterator<T>>();
    private T element = null;

    public DeepIterator(Iterable<T> iterable) {
        stack.push(iterable.iterator());
    }

    @Override
    public boolean hasNext() {
        if (element != null) return true;

        while (!stack.isEmpty()) {
            Iterator<T> itr = stack.peek();
            if (itr.hasNext()) {
                T object = itr.next();
                if (object instanceof Iterable) {
                    stack.push(((Iterable) object).iterator());
                } else {
                    element = (T)object;
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
            T temp = element;
            element = null;
            return temp;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {

    }

    public static void main(String[] args) {
        List l1 = new LinkedList();
        l1.add(1);
        l1.add(2);
        List l2 = new LinkedList();
        l2.add(3);
        l2.add(4);
        l1.add(l2);
        List l3 = new LinkedList();
        l3.add(5);
        l3.add(6);
        l2.add(l3);
        l2.add(7);
        l2.add(8);
        l1.add(9);
        DeepIterator<Integer> deepIterator = new DeepIterator<Integer>(l1);

        while(deepIterator.hasNext()) {
            System.out.println(deepIterator.next());
        }
    }
}
