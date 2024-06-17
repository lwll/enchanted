package com.lwsmilence.enchanted.common.designPattern.factory;

public class FactoryTest {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        shapeFactory.getShape("square").draw();

    }
}
