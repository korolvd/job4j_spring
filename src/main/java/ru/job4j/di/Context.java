package ru.job4j.di;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {
    private Map<String, Object> elements = new HashMap<>();

    public void reg(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();
        if (constructors.length > 1) {
            throw new IllegalStateException("Class has multiple constructors : " + cl.getCanonicalName());
        }
        Constructor constructor = constructors[0];
        List<Object> args = new ArrayList<>();
        for (Class arg : constructor.getParameterTypes()) {
            if (!elements.containsKey(arg.getCanonicalName())) {
                throw new IllegalStateException("Object doest found in context : " + cl.getCanonicalName());
            }
            args.add(elements.get(arg.getCanonicalName()));
        }
        try {
            elements.put(cl.getCanonicalName(), constructor.newInstance(args.toArray()));
        } catch (Exception e) {
            throw new IllegalStateException("Cant create an istatnce of : " + cl.getCanonicalName(), e);
        }
    }

    public <T> T get(Class<T> inst) {
        return (T) elements.get(inst.getCanonicalName());
    }
}
