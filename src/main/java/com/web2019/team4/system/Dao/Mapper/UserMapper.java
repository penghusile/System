package com.web2019.team4.system.Dao.Mapper;

import com.web2019.team4.system.Dao.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
     void insertUser(User user);
     int selectUser(User user);
     User selectUserById(String id);
}
