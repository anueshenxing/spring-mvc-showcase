package com.graduate.zhang.repository;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.MongoClient;
import com.graduate.zhang.model.User;
import com.graduate.zhang.util.GlobalConst;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 张晓磊 on 2016/3/4.
 */
@Repository
public class UserDao {
    private final MongoClient mongoClient;
    private final Morphia morphia;
    private final Datastore ds;

    public UserDao(){
        mongoClient = DaoUtils.getMongo();
        morphia = new Morphia();
        morphia.map(User.class);
        ds = morphia.createDatastore(mongoClient, GlobalConst.MONGO_DB);
        ds.ensureIndexes();
    }

    public void save(User user){
        ds.save(user);
    }

    //查找一个
    public User findOne(String username){
        User user = ds.find(User.class).field("username").equal(username).get();
        return user;
    }

    //查找多个,查询条件为一个
    public List<User> findUsers(String username){
        List<User> userList = ds.find(User.class).field("username").equal(username).asList();
        return userList;
    }

    public List<User> findUsersByMultiple(String username, String password){
        Query<User> userQuery = ds.createQuery(User.class);
        userQuery.and(
                userQuery.criteria("username").equal(username),
                userQuery.criteria("password").equal(password)
        );
        return userQuery.asList();
    }

    public static void main(String[] args){
        UserDao userDao = new UserDao();
//        List<User> userList = userDao.findUsersByMultiple("张晓磊","123456");
//        try {
//            for (User user:userList) {
//                System.out.println(user.getUsername() + ", " +user.getPassword());
//            }
//        } catch (Exception e) {
//            System.out.println("没有找到！");
//        }
        User user = new User();
        user.setUsername("张晓磊");
        user.setPassword("654321");
        userDao.save(user);
        System.out.println("save data successfuly");
    }
}
