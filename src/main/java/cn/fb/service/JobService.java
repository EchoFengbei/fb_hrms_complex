package cn.fb.service;

import cn.fb.pojo.Job;

import java.util.List;

public interface JobService {
    /*查询*/
    List<Job> selectAllJob();
    List<Job> selectJobList(Integer offset,Integer limit);
    Integer getJobCount();
    /*增加*/
    void addJob(Job job);
    /*删除*/
    void removeJobById(Integer id);
    /*更新*/
    void updateJobById(Integer id,Job job);
    /*查找*/
    Job selectJobById(Integer id);
}
