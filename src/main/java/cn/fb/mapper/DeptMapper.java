package cn.fb.mapper;

import cn.fb.pojo.Dept;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeptMapper {

    //查询所有的dept表中的数据
    @Select("select * from dept_inf" )
    List<Dept> selectAllDept();
    //获取表中的所有数据总数
    @Select("select count(*) from dept_inf " )
    int getCount();

    List<Dept> selectDeptsByLimitAndOffset(@Param("offset") Integer offset,
                                           @Param("limit") Integer limit);
}
