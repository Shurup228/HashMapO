package com.testwork;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashMapTest {

    @Test
    public void common() {
        HashMap map = new HashMap();

        map.put(228, 322L);
        map.put(-228, -322L);
        map.put(322, 228L);
        map.put(-322, -228L);

        assertEquals(322L, map.get(228));
        assertEquals(-228L, map.get(-322));
        assertEquals(HashMap.NO_VALUE, map.get(1488));
    }

    @Test
    public void size() {
        HashMap map = new HashMap();
        assertEquals(0, map.size());

        for (int i = 0; i < 20; i++) {
            map.put(i, i);
            assertEquals(i + 1, map.size());
        }
    }

    @Test
    public void reassign() {
        HashMap map = new HashMap();

        map.put(228, 322L);
        map.put(322, 228L);

        assertEquals(map.get(228), 322L);
        assertEquals(map.get(322), 228L);

        map.put(228, 1488L);
        map.put(322, 1337L);

        assertEquals(map.get(228), 1488L);
        assertEquals(map.get(322), 1337L);
    }

}