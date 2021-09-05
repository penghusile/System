package com.web2019.team4.system.Dao.Entity;

public class Gender {
    String gender;

    public Gender() {
        gender = "女";
    }

    public Gender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Gender:" + gender;
    }

    public void setGender(String gender) {
        this.gender=gender;
    }

    public String getGender() {
        return gender;
    }
}
