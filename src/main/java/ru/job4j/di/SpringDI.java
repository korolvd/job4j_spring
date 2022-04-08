package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.di");
        context.refresh();
        StartUI firstUI = context.getBean(StartUI.class);
        firstUI.add("Petr Arsentev");
        StartUI secondUI = context.getBean(StartUI.class);
        secondUI.print();
    }
}
