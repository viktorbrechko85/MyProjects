package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
/*
Осталось дописать реализацию загрузчика уровней.
16.1. Открой файл levels.txt и внимательно изучи структуру файла. Символ 'X' - означает стену, '*' - ящик,
'.' - дом, '&' - ящик который стоит в доме, а '@' - игрока. Всего в файле 60 уровней.
16.2. Реализуй метод GameObjects getLevel(int level). Он должен:
16.2.1. Вычитывать из файла данные уровня level. Уровни должны повторяться циклически, если пользователь прошел
все 60 уровней и попал на 61 уровень, то ему нужно вернуть 1, вместо 62 уровня вернуть 2 и т.д.
16.2.2. Создать все игровые объекты, описанные в указанном уровне. Координаты каждого игрового объекта должны
быть рассчитаны согласно следующей логике:
16.2.2.1. Самый верхний левый объект (если такой есть) должен иметь координаты
x = x0 = FIELD_CELL_SIZE / 2 и y = y0 = FIELD_CELL_SIZE / 2.
16.2.2.2. Объект, который находится на одну позицию правее от него должен иметь координаты x = x0 + FIELD_CELL_SIZE и y = y0.
16.2.2.3. Объект, который находится на одну позицию ниже от самого верхнего левого должен иметь координаты x = x0 и y = y0 + FIELD_CELL_SIZE.
16.2.2.4. Аналогично должны рассчитываться координаты любого объекта на поле.
16.2.3. Создать новое хранилище объектов GameObjects и поместить в него все объекты.
16.2.4. Вернуть созданное хранилище.
 */
public class LevelLoader {
    Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level){
        Set<Wall> wallSet = new HashSet<>();
        Set<Home> homeSet = new HashSet<>();
        Set<Box> boxSet = new HashSet<>();
        Player player = null;
        int loopLevel;
        if (level > 60) {
            loopLevel = level % 60;
        } else {
            loopLevel = level;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            int levelNumber = 0;
            String line;
            int x;
            int y = Model.FIELD_CELL_SIZE / 2;
            boolean isLevelMap = false;
            while ((line = reader.readLine()) != null) {
                if(line.contains("Maze:")){
                    levelNumber = Integer.parseInt(line.split(" ")[1].trim());
                    continue;
                }
                if (levelNumber == loopLevel) {
                    if (line.length() == 0) {
                        boolean isEnd = isLevelMap;

                        isLevelMap = !isLevelMap;

                        if (isEnd && !isLevelMap) {
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (isLevelMap) {
                        x = Model.FIELD_CELL_SIZE / 2;

                        char[] chars = line.toCharArray();
                        for (char c : chars) {
                            switch (c) {
                                case 'X':
                                    wallSet.add(new Wall(x, y));
                                    break;
                                case '*':
                                    boxSet.add(new Box(x, y));
                                    break;
                                case '.':
                                    homeSet.add(new Home(x, y));
                                    break;
                                case '&':
                                    boxSet.add(new Box(x, y));
                                    homeSet.add(new Home(x, y));
                                    break;
                                case '@':
                                    player = new Player(x, y);
                            }
                            x += Model.FIELD_CELL_SIZE;
                        }
                        y += Model.FIELD_CELL_SIZE;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        GameObjects gameObjects = new GameObjects(wallSet, boxSet, homeSet, player);
        return gameObjects;
        }
    }
