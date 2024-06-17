package com.lwsmilence.enchanted.common.designPattern.factory;

public class ShapeFactory {

    public Shape getShape(String shapeType) {
        if ("circle".equals(shapeType)) {
            return new Circle();
        } else if ("square".equals(shapeType)) {
            return new Square();
        }
        // 给默认值
        return new Circle();
    }
}
