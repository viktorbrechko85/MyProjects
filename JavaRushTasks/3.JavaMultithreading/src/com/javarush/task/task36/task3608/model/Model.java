package com.javarush.task.task36.task3608.model;


/**
 * Created by MarKiz on 01.01.2018.
 */
public interface Model {
    ModelData getModelData();
    void loadUsers();
    void loadDeletedUsers();
    void loadUserById(long userId);
    void deleteUserById(long userId);
    void changeUserData(String name, long id, int level);
}
