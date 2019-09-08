package com.javarush.task.task21.task2113;

import java.util.*;

/**
 * Created by oper on 18.12.2017.
 */
public class Hippodrome {
    private List<Horse> horses = null;
    public List getHorses(){
        return horses;
    }
    static Hippodrome game;
    public Hippodrome() {}
    public Hippodrome(List horses){
        this.horses = horses;
    }
    public  void move(){
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).move();
        }
    }
    public  void run() throws InterruptedException {

            for (int i = 1; i <= 100; i++) {
                move();
                print();
                Thread.sleep(200);
            }

    }
    public  void print(){
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).print();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
    public Horse getWinner(){
        /*double dist = horses.get(0).getDistance();
        int HorseIndexMax=0;
        for (int i = 0; i < horses.size(); i++) {
            if(horses.get(i).getDistance()>dist){
                dist=horses.get(i).getDistance();
                HorseIndexMax = i;
            }

        }
        return horses.get(HorseIndexMax);*/
        /*Horse winner = horses.get(0);
        for(Horse horse: horses) {
            if (winner.getDistance() < horse.getDistance()) winner = horse;
        }
        return winner;*/

        return horses.stream()
                .max(Comparator.comparingDouble(Horse::getDistance))
                .get();
    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName()+"!");
    }

    public static void main(String[] args) throws InterruptedException {
        game = new Hippodrome(new ArrayList<Horse>());
        game.getHorses().add(new Horse("Horse1",3.0,0.0));
        game.getHorses().add(new Horse("Horse2",3.0,0.0));
        game.getHorses().add(new Horse("Horse3",3.0,0.0));
        game.getHorses().add(new Horse("Буббка",3.0,0.0));
        game.getHorses().add(new Horse("Мордайчан",3.0,0.0));
        game.getHorses().add(new Horse("Кукушка",3.0,0.0));
        game.run();
        game.printWinner();
    }
}
