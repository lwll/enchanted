package com.lwsmilence.enchanted.common.designPattern.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {
    private List<Observer> observerList = new ArrayList<>();

    private String message;

    @Override
    public void registerObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer: observerList) {
            observer.update(message);
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
