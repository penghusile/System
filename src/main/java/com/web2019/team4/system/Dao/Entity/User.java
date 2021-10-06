package com.web2019.team4.system.Dao.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class User  {
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

}
