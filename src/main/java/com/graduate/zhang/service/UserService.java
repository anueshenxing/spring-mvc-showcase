package com.graduate.zhang.service;

import com.graduate.zhang.model.User;
import com.graduate.zhang.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 张晓磊 on 2016/3/4.
 */
@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public void save(User user){
        userDao.save(user);
    }

    public User findOne(String username){
        return userDao.findOne(username);
    }

    public List<User> findUsers(String username){
        return userDao.findUsers(username);
    }

    public List<User> findUsersByMultiple(String username, String password){
        return userDao.findUsersByMultiple(username, password);
    }

    public static void main(String[] args){
        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);
        List<User> userList = userService.findUsers("张晓磊");
        for (User user:userList
             ) {
            System.out.println(user.getUsername() + ", " + user.getPassword());
        }
    }
}
