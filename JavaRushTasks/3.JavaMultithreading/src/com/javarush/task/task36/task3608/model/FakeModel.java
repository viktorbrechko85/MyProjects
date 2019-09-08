package com.javarush.task.task36.task3608.model;


import com.javarush.task.task36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MarKiz on 01.01.2018.
 */
public class FakeModel implements Model {
    private ModelData modelData = new ModelData();

    public void loadUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User("U1",1,1));
        users.add(new User("U2",2,2));
        modelData.setUsers(users);
    };
    @Override
    public void loadDeletedUsers(){
        throw  new UnsupportedOperationException();
    }
    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUserById(long userId) {
        throw  new UnsupportedOperationException();
    }

    @Override
    public void deleteUserById(long userId) {
        throw  new UnsupportedOperationException();
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        throw  new UnsupportedOperationException();
    }
}
