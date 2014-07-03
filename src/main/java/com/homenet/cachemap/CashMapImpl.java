package com.homenet.cachemap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author denis.bilyk.
 * CashMap implementation.
 */
public class CashMapImpl<KeyType, ValueType> implements CacheMap<KeyType, ValueType> {
    private long timeToLive = 1000;
    private final Map<KeyType, ValueType> map = new HashMap<KeyType, ValueType>();
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
        if (key == null) return null;
        if (value == null) {
            context.remove(key);
            map.remove(key);
        }
        if (context.get(key) == null) {
            context.put(key, Clock.getTime());
        }
        map.put(key, value);
        validateKey(key);
        return map.get(key);
    }

    @Override
    public void clearExpired() {
        for (Map.Entry<KeyType, Long> keyTypeLongEntry : context.entrySet()) {
            validateKey(keyTypeLongEntry.getKey());
        }
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
        clearExpired();
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
        clearExpired();
        return map.size();
    }

    private void validateKey(Object key) {
        Long aliveTime = context.get(key);
        if (aliveTime != null && aliveTime < Clock.getTime() - timeToLive) {
            map.remove(key);
        }
    }
}
