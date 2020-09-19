import java.util.ArrayList;
import java.util.List;

public class NiceStack {
    List list = new ArrayList();

    public int count() {
        return list.size();
    }

    public void push(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("You can not push null item.");
        }
        list.add(object);
    }

    public Object pop() {
        if (list.size() == 0) {
            throw new NullPointerException("You can not pop from empty stack.");
        }
        Object last = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return last;
    }

    public Object peek() {
        if (list.size() == 0) {
            throw new NullPointerException("You can not peek from empty stack.");
        }
        return list.get(list.size() - 1);
    }
}
