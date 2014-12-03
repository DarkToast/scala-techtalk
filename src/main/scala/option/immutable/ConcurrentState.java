package option.immutable;


import java.util.*;


public class ConcurrentState {

    private volatile List<Integer> state = new ArrayList<>();

    private final Object token = new Object();

    public void addValue(int value) throws InterruptedException {
        synchronized(token) {
            List<Integer> tmpList = new ArrayList<>(state.size());
            Collections.copy(state, tmpList); // May be very expensive!
            tmpList.add(value);
            state = tmpList;
        }
    }

    public Integer getIndex(int key) {
        return state.get(key);
    }
}
