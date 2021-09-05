package com.web2019.team4.system.Dao.Entity;

import org.springframework.stereotype.Component;

@Component
public class Course {
    String id;
    String name;
    String teacher;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public Course() {}

    public Course(String id, String name, String teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }
    @Override
    public String toString() {
        return "course_id: "+getId()+
                ", course_name:"+ getName()+
                ", teacher:"+getTeacher();
    }
}
