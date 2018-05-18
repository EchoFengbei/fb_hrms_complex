package cn.fb.mapper;

import cn.fb.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DeptMapper {

    //查询所有的dept表中的数据
    @Select("select * from dept_inf" )
    List<Dept> selectAllDept();
    //获取表中的所有数据总数
    @Select("select count(*) from dept_inf " )
    int getCount();
    @Select("select * from dept_inf limit #{offset}, #{limit}")
    List<Dept> selectDeptsByLimitAndOffset(@Param("offset") Integer offset,
                                           @Param("limit") Integer limit);

    @Select("select * from dept_inf where id = #{id}")
    Dept selectDeptById(@Param("id") Integer id);

    /*删除dept信息*/
    @Delete("delete from dept_inf where id= #{id}")
    int deleteDeptById(@Param("id") Integer id);

    /*插入信息*/
    @Insert("insert into dept_inf (name,remark) values (#{dept.name},#{dept.remark})")
    void addDept( @Param("dept") Dept dept);

    /*根据id更新dept的信息*/
    @Update("update dept_inf set name = #{dept.name},remark = #{dept.remark} where id = #{dept.id}")
    int updateDeptById(@Param("id") Integer id,@Param("dept") Dept dept);
}
