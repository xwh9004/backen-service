package com.example.user.service;

import com.example.user.entity.User;

import java.util.List;

public interface IUserService {

    void insert(User user);

    User select(int id);

    void delete(int id);

    void update(User user);

}
