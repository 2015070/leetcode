package main.cache;

import java.util.*;

public class LFUCache {

    final int DEFAULT_CAPACITY = 16;
    int capacity;
    int size;
    Map<Integer, Node> cache;

    LFUCache() {
        this.cache = new HashMap<>();
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    LFUCache(int capacity) {
        super();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!cache.containsKey(key)) {
            return -1;
        }
        cache.get(key).setFreq(cache.get(key).getFreq()+1);
        return cache.get(key).getValue();
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            cache.get(key).setFreq(cache.get(key).getFreq()+1);
            cache.get(key).setValue(value);
        } else {
            if(this.size == capacity) {
                Map.Entry<Integer, Node> entry = null;
                for (Map.Entry<Integer, Node> cacheEntry : cache.entrySet()) {
                    if (entry == null || cacheEntry.getValue().getFreq() < entry.getValue().freq) {
                        entry = cacheEntry;
                    }
                }
                cache.remove(entry.getKey());
                this.size--;
            }
            this.cache.put(key, new Node(key, value));
            this.size++;
        }
    }

    class Node {
        private final int key;
        private int value;
        private int freq;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(final int value) {
            this.value = value;
        }

        public int getFreq() {
            return this.freq;
        }

        public void setFreq(final int freq) {
            this.freq = freq;
        }

    }

}
