package com.lwsmilence.enchanted.common.designPattern.observer;

public class ObserverTest {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        ConcreteObserver observer1 = new ConcreteObserver();
        ConcreteObserver observer2 = new ConcreteObserver();

        subject.registerObserver(observer1);
        subject.registerObserver(observer2);

        subject.setMessage("hello");

        subject.notifyObserver();
    }

}
