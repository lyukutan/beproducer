package ru.yandex.test.model;

import java.util.Calendar;

public class NotebookPower {
    String batteryType;
    Integer cellsCount;
    Double capacity;
    String workTime;


    public NotebookPower(String batteryType, Integer cellsCount, Double capacity, String workTime) {
        this.batteryType = batteryType;
        this.cellsCount = cellsCount;
        this.capacity = capacity;
        this.workTime = workTime;
    }

    @Override
    public String toString() {
        return "Время работы от аккумулятора: " +
                this.workTime +
                "\nТип аккумулятора: " +
                this.batteryType +
                "\nКоличество ячеек батареи: " +
                this.cellsCount +
                "\nЕмкость аккумулятора: " +
                this.capacity;
    }

    public boolean equals(NotebookPower n2) {
        return ((this.capacity==n2.capacity) && (this.cellsCount==n2.cellsCount) && (this.batteryType.equals(n2.batteryType)) && (this.workTime==n2.workTime));
    }
}