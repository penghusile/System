package com.web2019.team4.system.Dao.Entity;

import org.springframework.stereotype.Component;

@Component
public class SelectCourse {
    String select_id;
    String student_id;
    String course_id;
    public SelectCourse(){};
    public SelectCourse(String select_id,String student_id,String course_id){
        this.select_id=select_id;
        this.student_id=student_id;
        this.course_id = course_id;

    }
    public String getSelect_id() {
        return select_id;
    }

    public void setSelect_id(String select_id) {
        this.select_id = select_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "SelectCourse: course_id"+getCourse_id()+", student_id:"+getStudent_id()+
                ", select_id"+getSelect_id();
    }
}
