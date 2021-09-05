package com.web2019.team4.system.Dao.Entity;

import org.springframework.stereotype.Component;

@Component
public class Student {
    String student_id;
    String student_password;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_password() {
        return student_password;
    }

    public void setStudent_password(String student_password) {
        this.student_password = student_password;
    }

    @Override
    public String toString() {
        return "Student:"+getStudent_id()+", password:"+getStudent_password();
    }
}
