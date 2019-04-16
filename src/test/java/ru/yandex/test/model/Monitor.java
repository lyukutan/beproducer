package ru.yandex.test.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Monitor {
    Integer priceFrom;
    Integer priceTo;
    String manufactorer;
    String screenSize;
    String resolution;
    String matrixType;
    String aspectRatio;
    Boolean hdmi;

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public String getManufactorer() {
        return manufactorer;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public String getResolution() {
        return resolution;
    }

    public String getMatrixType() {
        return matrixType;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public Boolean getHdmi() {
        return hdmi;
    }

    public Monitor(int priceFrom, int priceTo, String manufactorer, String screenSize, String resolution) {
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.manufactorer = manufactorer;
        this.screenSize = screenSize;
        this.resolution = resolution;
    }
/*
    public static void main(String[] args) throws Exception{
        ArrayList<Monitor> monitor = new ArrayList<Monitor>();
        monitor.add(new Monitor(3000, 5000, "DELL", "23.1\"-24\"", "1920x1080"));
        monitor.add(new Monitor(10000,20000,"ASUS", "31\"-40\"", "3840x2160"));
        Writer writer = new FileWriter("Output.json");
        Gson gson = new GsonBuilder().create();
        gson.toJson(monitor, writer);
        writer.close();

        System.out.println(new Gson().toJson(monitor));
    }*/
}
