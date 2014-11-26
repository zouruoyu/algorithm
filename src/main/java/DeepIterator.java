import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: roy
 * Date: 11/25/14
 * Time: 8:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeepIterator<T> implements Iterator<T> {
    Stack<Iterable<T>> stack = new Stack<Iterable<T>>();
    T element = null;
    public DeepIterator(Iterable<T> iterable) {
        stack.push(iterable);
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            Iterator<T> itr = stack.peek().iterator();
            if (itr.hasNext()) {
                T object = itr.next();
                if (object instanceof Iterable) {
                    stack.push((Iterable)object);
                } else {
                    element = object;
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
            return stack.peek().iterator().next();
        }
        throw new NoSuchElementException();
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
        DeepIterator deepIterator = new DeepIterator(l1);

        while(deepIterator.hasNext()) {
            System.out.println(deepIterator.next());
        }
    }
}
