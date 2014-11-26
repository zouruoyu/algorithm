import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roy
 * Date: 11/25/14
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<Integer>();
        list.add(1);

        Iterator iterator = list.iterator();
        Iterator iterator1 = iterator;
        System.out.println("Printing iterator1");

        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }

        System.out.println("printing iterator");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
