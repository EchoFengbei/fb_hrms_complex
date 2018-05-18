package cn.fb.mapper;

import cn.fb.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user_inf where loginname = #{loginname}" +
            "and password = #{password}")
    User selectByLogin(@Param("loginname") String loginname, @Param("password") String password);
}
