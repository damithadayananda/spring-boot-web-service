package com.example.webservice.user;


import com.example.webservice.user.user;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static  ArrayList<user> users = new ArrayList<user>() {
        {
            add(new user(1,"Adam",new Date()));
            add(new user(2,"Eve",new Date()));
            add(new user(3,"Jack",new Date()));
        }
    };
    private static int userCount;
    public List<user> findAll(){
        return users;
    }
    public user save(user user){
        System.out.println(user.toString());
        if (user.getId()==null){
           user.setId(++userCount);
        }
        users.add(user);
        return user;
    }
    public user findOne(int id){
       for(user u:users){
           if (u.getId()==id){
               return u;
           }
        }
       return null;
    }
    public user deleteById(int id){
        Iterator<user> iterator = users.iterator();
        while (iterator.hasNext()){
            user user = iterator.next();
            if(user.getId()==id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
