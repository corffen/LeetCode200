package com.gordon.study;

import java.util.ArrayList;
import java.util.List;

public class StudyMain {
    public static void main(String[] args) {
        Message msg = new Message();
        lookupAllEventTypes(msg.getClass());
    }

    private static List<Class<?>> lookupAllEventTypes(Class<?> eventClass) {
        List<Class<?>>  eventTypes = new ArrayList<>();
        Class<?> clazz = eventClass;
        while (clazz != null) {
            System.out.println(clazz.getName());
            eventTypes.add(clazz);
            addInterfaces(eventTypes, clazz.getInterfaces());
            clazz = clazz.getSuperclass();
        }
        return eventTypes;
    }

    static void addInterfaces(List<Class<?>> eventTypes, Class<?>[] interfaces) {
        for (Class<?> interfaceClass : interfaces) {
            if (!eventTypes.contains(interfaceClass)) {
                eventTypes.add(interfaceClass);
                addInterfaces(eventTypes, interfaceClass.getInterfaces());
            }
        }
    }
}
