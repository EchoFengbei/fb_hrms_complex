package cn.fb.mapper;

import cn.fb.pojo.Dept;
import cn.fb.pojo.Job;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface JobMapper {
    //查询所有表中的数据
    @Select("select * from job_inf")
    List<Job> selectAllJob();
    @Select("select * from job_inf limit #{offset}, #{limit}")
    List<Job> selectJobsByLimitAndOffset(@Param("offset") Integer offset,
                                           @Param("limit") Integer limit);
    @Select("select count(*) from job_inf")
    Integer count();
    //添加
    @Insert("insert into job_inf (name,remark) values (#{job.name},#{job.remark})")
    void addDept( @Param("job") Job job);
    /*删除*/
    @Delete("delete from job_inf where id = #{id}")
    void removeJobById(@Param("id") Integer id);
    /*更新数据*/
    @Update("update job_inf set name = #{job.name} ,remark = #{job.remark} where id = #{job.id}")
    void updateJobById(@Param("id") Integer id,@Param("job") Job job);
    /*查询*/
    @Select("select * from job_inf where id = #{id}")
    Job selectJobById(@Param("id") Integer id);
}
