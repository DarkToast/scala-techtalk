package pittfalls.immutable;


import java.util.*;


public class JavaConcurrentState {
    private final Map<Integer, String> state = new HashMap<>();

    public synchronized void addValue(int key, String value) {
        state.put(key, value);
    }

    public synchronized String getIndex(int key) {
        if(state.containsKey(key)) {
            return state.get(key);
        }
        return null;
    }
}
