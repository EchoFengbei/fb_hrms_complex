package cn.fb.service.Impl;

import cn.fb.mapper.JobMapper;
import cn.fb.pojo.Job;
import cn.fb.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobMapper jobMapper;
    @Override
    public List<Job> selectAllJob() {
        return jobMapper.selectAllJob();
    }

    @Override
    public List<Job> selectJobList(Integer offset, Integer limit) {
        return jobMapper.selectJobsByLimitAndOffset(offset,limit);
    }

    @Override
    public Integer getJobCount() {
        return jobMapper.count();
    }

    @Override
    public void addJob(Job job) {
        jobMapper.addDept(job);
    }

    @Override
    public void removeJobById(Integer id) {
        jobMapper.removeJobById(id);
    }

    @Override
    public void updateJobById(Integer id,Job job) {
        jobMapper.updateJobById(id,job);
    }

    @Override
    public Job selectJobById(Integer id) {
        return jobMapper.selectJobById(id);
    }
}
