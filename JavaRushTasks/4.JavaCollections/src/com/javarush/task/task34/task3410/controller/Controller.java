package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObjects;
import com.javarush.task.task34.task3410.model.Model;
import com.javarush.task.task34.task3410.view.View;
/*
Sokoban (13)
Наполним контроллер функционалом.
13.1. Добавь в конструктор класса Controller к тому, что уже есть еще и установку
слушателя событий у модели и представления. Слушателем должен быть сам контроллер.
13.2. Реализуй методы контроллера:
13.2.1. move(Direction direction) - должен вызывать move(Direction direction) у модели
и update() у представления. Метода move() у модели еще нет, добавь для него
заглушку, мы его реализуем позже.
13.2.2. restart() - должен перезапускать модель и обновлять представление.
13.2.3. startNextLevel() - должен запускать у модели новый уровень и обновлять
представление.

13.4. Реализуй в контроллере метод levelCompleted(int level), он должен вызвать
метод completed() у представления.


Требования:
1. Конструктор класса Controller должен устанавливать слушателя событий у модели и представления.
2. Реализуй метод контроллера move(Direction direction).
3. Реализуй метод контроллера restart().
4. Реализуй метод контроллера startNextLevel().
5. Реализуй в представление метод completed(int level).
6. Реализуй в контроллере метод levelCompleted(int level).
 */
public class Controller implements EventListener{
    private View view;
    private Model model;


    public static void main(String[] args) {
        Controller controller = new Controller();
    }

    public Controller() {
        this.view = new View(this);
        this.model = new Model();
        view.init();
        model.restart();
        view.setEventListener(this);
        model.setEventListener(this);
    }

    public GameObjects getGameObjects(){
        return model.getGameObjects();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }
}
