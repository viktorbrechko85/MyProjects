package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.Controller;
import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.view.View;

import java.nio.file.Paths;
import java.util.Set;

/*
Sokoban (15)
Пришло время реализовать метод модели, отвечающий за движение move(), но для начала реализуем вспомогательные методы.
Добавь в класс модели методы:
15.1. boolean checkWallCollision(CollisionObject gameObject, Direction direction).
Этот метод проверяет столкновение со стеной. Он должен вернуть true, если при движении объекта gameObject в направлении
direction произойдет столкновение со стеной, иначе false. Для определения столкновения используй метод isCollision()
у игрового объекта.
15.2. boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction). Этот метод проверяет столкновение с ящиками.
Метод должен:
15.2.1. Вернуть true, если игрок не может быть сдвинут в направлении direction (там находится: или ящик, за которым стена;
или ящик за которым еще один ящик).
15.2.2. Вернуть false, если игрок может быть сдвинут в направлении direction (там находится: или свободная ячейка;
или дом; или ящик, за которым свободная ячейка или дом). При этом, если на пути есть ящик, который может быть сдвинут,
то необходимо переместить этот ящик на новые координаты. Обрати внимание, что все объекты перемещаются на фиксированное
значение FIELD_CELL_SIZE, независящее от размеров объекта, которые используются для его отрисовки.
Подсказка: для определения столкновений используй методы isCollision() игровых объектов и метод checkWallCollision() модели.
15.3. void checkCompletion(). Этот метод должен проверить пройден ли уровень (на всех ли домах стоят ящики, их координаты
должны совпадать). Если условие выполнено, то проинформировать слушателя событий, что текущий уровень завершен.
15.4. void move(Direction direction). Метод должен:
15.4.1. Проверить столкновение со стеной (метод checkWallCollision()), если есть столкновение - выйти из метода.
15.4.2. Проверить столкновение с ящиками (метод checkBoxCollisionAndMoveIfAvaliable()), если есть столкновение - выйти из метода.
15.4.3. Передвинуть игрока в направлении direction.
15.4.4. Проверить завершен ли уровень.

Запусти программу и проверь, что игрока можно перемещать, он может перемещать ящики, стены преграждают движение, а при перемещении всех ящиков в дома выводится сообщение о прохождении уровня.


Требования:
1. Реализуй в классе модели метод boolean checkWallCollision(CollisionObject gameObject, Direction direction).
2. Реализуй в классе модели метод boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction).
3. Реализуй в классе модели метод void checkCompletion().
4. Реализуй в классе модели метод void move(Direction direction).
*/
public class Model {
    private EventListener eventListener;
    public static int FIELD_CELL_SIZE = 20;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("C:\\1\\levels.txt"));

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects(){
        return gameObjects;
    }

    public void restartLevel(int level){
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart(){
        restartLevel(currentLevel);
    }

    public void startNextLevel(){
        currentLevel++;
        restartLevel(currentLevel);
    }

    public void move(Direction direction){
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)){
            return;
        }
        if (checkBoxCollisionAndMoveIfAvaliable(direction)){
            return;
        }

        switch (direction) {
            case LEFT:
                player.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_CELL_SIZE);
        }

        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction))
                return true;
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction){
        Player player = gameObjects.getPlayer();
        GameObject stoped = null;
        for (GameObject gameObject : gameObjects.getAll()) {
            if (!(gameObject instanceof Player) && !(gameObject instanceof Home) && player.isCollision(gameObject, direction)) {
                stoped = gameObject;
            }
        }

        if ((stoped == null)) {
            return false;
        }

        if (stoped instanceof Box) {
            Box stopedBox = (Box) stoped;
            if (checkWallCollision(stopedBox, direction)) {
                return true;
            }
            for (Box box : gameObjects.getBoxes()) {
                if (stopedBox.isCollision(box, direction)) {
                    return true;
                }
            }

            switch (direction) {
                case LEFT:
                    stopedBox.move(-FIELD_CELL_SIZE, 0);
                    break;
                case RIGHT:
                    stopedBox.move(FIELD_CELL_SIZE, 0);
                    break;
                case UP:
                    stopedBox.move(0, -FIELD_CELL_SIZE);
                    break;
                case DOWN:
                    stopedBox.move(0, FIELD_CELL_SIZE);
            }
        }

        return false;
    }

    public void checkCompletion(){
        boolean isLevelCompleted = true;
        for (Home home : gameObjects.getHomes()) {
            boolean chk = false;

            for (Box box : gameObjects.getBoxes()) {
                if ((box.getX() == home.getX()) && (box.getY() == home.getY()))
                    chk = true;
            }
            if (!chk) isLevelCompleted = false;
        }

        if (isLevelCompleted)
            eventListener.levelCompleted(currentLevel);
    }
}
