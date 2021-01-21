package com.javaee.example.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionUtil {

    /**
     * Creates a map from a pair of items (key and value)
     *
     * @param objects - massive of items, be careful that the
     *                first item it's a key and the next one it's a value
     * @param <K>     - key
     * @param <V>     - value
     * @return a map of items
     */

    public static <K, V> Map<K, V> mapOf(Object... objects) {
        Map<K, V> map = new HashMap<>();
        try {
            for (int i = 0; i < objects.length; i++) {
                map.put((K) objects[i], (V) objects[i = i + 1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Creates a list out of several individual items of the same type
     *
     * @param <T>
     *            The object type
     * @param objects
     *            The individual objects (or array of objects)
     * @return A {@code List} consisting of the individual objects
     */
    public static <T> List<T> listOf(T... objects) {
        List<T> list = new ArrayList<T>();
        for (T object : objects) {
            list.add(object);
        }
        return list;
    }
}
