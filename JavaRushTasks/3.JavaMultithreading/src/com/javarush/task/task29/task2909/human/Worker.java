package com.javarush.task.task29.task2909.human;

public class Worker extends Human {
   // private Soldier human;
    private double salary;
    private String company;

    public Worker(String name, int age) {
        //human = new Soldier(name, age);
        super(name, age);
    }


    /*public void live() {
        human.live();
    }*/

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}