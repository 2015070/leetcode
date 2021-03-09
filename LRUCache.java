package main.cache;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

class LRUCache {

    public static final Integer DEFAULT_CAPACITY = 16;

    private Integer capacity;
    private Integer size;
    private Map<Integer, Node> cacheMap;

    public LRUCache() {
        this.capacity = DEFAULT_CAPACITY;
        this.cacheMap = new HashMap<>();
        this.size = 0;
    }

    public LRUCache(final Integer capacity) {
        super();
        this.capacity = capacity;
    }

    public void insert(final Integer key, final Integer value) {
        if (cacheMap.containsKey(key)) {
            Node node = cacheMap.get(key);
            node.setValue(value);
            node.setTime(Instant.now());
        } else {
            if(this.size == this.capacity) {
                Map.Entry<Integer, Node> entry = null;
                for (Map.Entry<Integer, Node> cacheEntry : cacheMap.entrySet()) {
                    if(entry == null || cacheEntry.getValue().getTime().isBefore(entry.getValue().getTime())) {
                        entry = cacheEntry;
                    }
                }
                cacheMap.remove(entry.getKey());
                size--;
            }
            size++;
            cacheMap.put(key, new Node(key, value));
        }


    }

    public Integer getValue(final Integer key) {
        if (cacheMap.containsKey(key)) {
            cacheMap.get(key).setTime(Instant.MAX);
            return cacheMap.get(key).getValue();
        }
        return -1;
    }

    class Node {
        private final Integer key;
        private Integer value;
        private Instant time;

        Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            this.time = Instant.now();
        }

        public Integer getValue() {
            return this.value;
        }

        public void setValue(final Integer value) {
            this.value = value;
        }

        public Instant getTime() {
            return this.time;
        }

        public void setTime(final Instant time) {
            this.time = time;
        }
    }
}
