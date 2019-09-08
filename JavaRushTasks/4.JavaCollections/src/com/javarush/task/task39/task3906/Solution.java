package com.javarush.task.task39.task3906;

/* 
Интерфейсы нас спасут!
*/
public class Solution {
    public static void main(String[] args) {
        SecuritySystem securitySystem = new SecuritySystem();
        LightBulb lightBulb = new LightBulb();
        ElectricPowerSwitch electricPowerSwitch = new ElectricPowerSwitch(securitySystem);

        electricPowerSwitch.press();
        electricPowerSwitch.press();

        electricPowerSwitch = new ElectricPowerSwitch(lightBulb);

        electricPowerSwitch.press();
        electricPowerSwitch.press();

    }
}
