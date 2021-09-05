package com.web2019.team4.system.Dao.Mapper;

import com.web2019.team4.system.Dao.Entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseMapper {
//    添加课程
    boolean addCourse(Course course);
//    查询课程
    List<Course> selectAllCourse();
//    删除课程
    boolean delCourse(Course course);
//    更新课程
    boolean updateCourse(Course course);
//    通过ID查询课程
    List<Course> selectCourseByID(String id);
}
