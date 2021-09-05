package com.web2019.team4.system.Dao.Entity;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Component
public class User {
    String id;
    String password;
    Gender gender;
    public User(){}
    public User(String id, String password){
        this.id=id;
        this.password=password;
        this.gender=new Gender();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }
    @Override
    public String toString() {
        return "id: "+id+", password: " +password+", "+gender;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) return true;
        else return false;
    }
}
