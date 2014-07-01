package com.homenet.cachemap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author denis.bilyk.
 */
public class CashMapImpl<KeyType, ValueType> implements CacheMap<KeyType, ValueType> {
    private long timeToLive;
    private final Map<KeyType, ValueType> map = new ConcurrentHashMap<KeyType, ValueType>();
    private final Map<KeyType, Long> context = new HashMap<KeyType, Long>();

    @Override
    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    @Override
    public long getTimeToLive() {
        return timeToLive;
    }

    @Override
    public ValueType put(KeyType key, ValueType value) {
        if(context.get(key) == null) {
            context.put(key, Clock.getTime());
            return map.put(key, value);
        }
        validateKey(key);
        return map.get(key);
    }

    @Override
    public void clearExpired() {
        validateKeys();

    }

    @Override
    public void clear() {
        map.clear();
        context.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        validateKey(key);
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        validateKeys();
        return map.containsValue(value);
    }

    @Override
    public ValueType get(Object key) {
        validateKey(key);
        return map.get(key);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public ValueType remove(Object key) {
        return map.remove(key);
    }

    @Override
    public int size() {
        validateKeys();
        return map.size();
    }

    private void validateKey(Object key) {
        Long v = context.get(key);
        if (v!=null && v < Clock.getTime() - timeToLive) {
            map.remove(key);
        }
    }

    private void validateKeys() {
        for (Map.Entry<KeyType, Long> keyTypeLongEntry : context.entrySet()) {
            validateKey(keyTypeLongEntry.getKey());
        }
    }
}
