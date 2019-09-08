package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.Controller;
import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.GameObjects;

import javax.swing.*;
/*
Sokoban (12)
Попробуем организовать взаимодействие представления и модели.
12.1. Добавь в класс View метод update(), он должен вызывать у игрового поля field метод repaint().
Другими словами, метод update() будет обновлять представление (перерисовывать поле).
12.2. Добавь в класс контроллера метод GameObjects getGameObjects(), он должен запросить игровые объекты у модели и вернуть их.
Добавь такой же метод и в класс представления, он должен получать объекты у контроллера.
12.3. Правильно реализуй в классе Field метод paint(Graphics g).
Он должен:
12.3.1. Залить все поле каким-то цветом, например, черным (вызови методы setColor и fillRect).
12.3.2. Получить у представления все игровые объекты.
12.3.3. Отрисовать все игровые объекты.
12.4. Контроллер в своем конструкторе должен перезапускать модель.

Запусти программу и проверь, что все игровые объекты рисуются правильно.


Требования:
1. Реализуй в классе View метод update().
2. Реализуй в классе контроллера метод GameObjects getGameObjects().
3. Реализуй в классе Field метод paint(Graphics g).
4. Контроллер в своем конструкторе должен перезапускать модель.

13.3. Добавь в представление метод completed(int level), который будет сообщать
пользователю, что уровень level пройден. Метод должен:
13.3.1. Обновлять представление.
13.3.2. Показывать диалоговое окно с информацией о том, что пользователь прошел
какой-то уровень.
Подсказка: используй JOptionPane.showMessageDialog.
13.3.3. Просить контроллер запустить следующий уровень.
 */
public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }

    public void setEventListener(EventListener eventListener){
        field.setEventListener(eventListener);
    }

    public void update(){
        field.repaint();
    }

    public GameObjects getGameObjects(){
        return controller.getGameObjects();
    }

    public void completed(int level){
        update();
        JOptionPane.showMessageDialog(null, level + "Completed", "Level", JOptionPane.INFORMATION_MESSAGE);
        controller.startNextLevel();
    }
}
