package com.web2019.team4.system.Service;

import com.web2019.team4.system.Dao.Entity.Course;
import com.web2019.team4.system.Dao.Mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    CourseMapper courseMapper;
    //添加课程
    public boolean addCourse(Course course){
        return courseMapper.addCourse(course);
    }
    //查询所有课程
    public List<Course> selectAllCourse() {
        return courseMapper.selectAllCourse();
    }
    //通过ID查询课程
    public List<Course> selectCourseByID(String id) {
        return courseMapper.selectCourseByID(id);
    }
    //删除课程
    public boolean delCourse(Course course){
        return courseMapper.delCourse(course);
    }
    //更新课程
    public boolean updateCourse(Course course){
        return  courseMapper.updateCourse(course);
    }
}
