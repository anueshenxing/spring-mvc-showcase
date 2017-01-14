package com.graduate.zhang.repository;

import com.mongodb.MongoClient;
import com.graduate.zhang.util.GlobalConst;

/**
 * Created by zengshuai on 9/5/15.
 */
public class DaoUtils {
    private MongoClient mongo;

    public DaoUtils(){
        try {
            mongo=new MongoClient(GlobalConst.MONGO_HOST);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static DaoUtils daoUtils=new DaoUtils();

    public static MongoClient getMongo(){
        if(daoUtils==null||daoUtils.mongo==null)
            daoUtils=new DaoUtils();
        return daoUtils.mongo;
    }
}
