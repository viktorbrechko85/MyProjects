package com.javarush.task.task33.task3305;
import com.fasterxml.jackson.annotation.*;

import java.util.List;
@JsonAutoDetect
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, include = JsonTypeInfo.As.PROPERTY,  property="className")
@JsonSubTypes(@JsonSubTypes.Type(value=ParkingLot.class, name=".ParkingLot"))
public class ParkingLot {
    public String name;
    public String city;
    public List<Vehicle> vehicles;
    public ParkingLot(){}
    public ParkingLot(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}