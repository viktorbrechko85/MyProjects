package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.*;
import com.javarush.task.task34.task3410.model.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

/*
12.3. Правильно реализуй в классе Field метод paint(Graphics g).
Он должен:
12.3.1. Залить все поле каким-то цветом, например, черным (вызови методы setColor и fillRect).
12.3.2. Получить у представления все игровые объекты.
12.3.3. Отрисовать все игровые объекты.
12.4. Контроллер в своем конструкторе должен перезапускать модель.


Добавим немного интерактивности в нашу игру (перемещение игрока с помощью клавиатуры). Начнем с обработки нажатия клавиш клавиатуры.
14.1. Добавь в класс Field вложенный класс KeyHandler унаследованный от KeyAdapter.
14.2. Перегрузи у него метод keyPressed(). Если была нажата клавиша с кодом VK_LEFT, то пошли eventListener-у
событие move с параметром Direction.LEFT.
Аналогичным образом обработай нажатия клавиш с кодом VK_RIGHT, VK_UP и VK_DOWN.
Если пользователь нажал клавишу R с кодом VK_R, то вызови у слушателя событий метод restart().
14.3. В конструкторе класса Field:
14.3.1. Создай объект класса KeyHandler.
14.3.2. Добавь его слушателем с помощью метода addKeyListener().
14.3.3. Установи focusable в true (метод setFocusable()).


Требования:
1. Добавь в класс Field вложенный public класс KeyHandler унаследованный от KeyAdapter.
2. В классе KeyHandler правильно реализуй метод keyPressed().
3. В конструкторе класса Field создай объект класса KeyHandler.
4. В конструкторе класса Field добавь объект класса KeyHandler слушателем с помощью метода addKeyListener().
5. В конструкторе класса Field установи focusable в true (метод setFocusable()).
 */
public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) throws HeadlessException {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
       // super.paint(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,450,450);

        Set<GameObject> gameObjectSet = view.getGameObjects().getAll();
        for(GameObject gameObject:gameObjectSet){
            gameObject.draw(g);
        }

    }

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT: {
                    eventListener.move(Direction.LEFT);
                    break;
                }
                case KeyEvent.VK_RIGHT: {
                    eventListener.move(Direction.RIGHT);
                    break;
                }
                case KeyEvent.VK_UP: {
                    eventListener.move(Direction.UP);
                    break;
                }
                case KeyEvent.VK_DOWN: {
                    eventListener.move(Direction.DOWN);
                    break;
                }
                case KeyEvent.VK_R: {
                    eventListener.restart();
                    break;
                }
            }
        }
    }
}
