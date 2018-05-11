package cn.fb.service.Impl;

import cn.fb.mapper.DeptMapper;
import cn.fb.pojo.Dept;
import cn.fb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptMapper deptMapper;

    @Override
    public int getDeptCount() {
        return deptMapper.getCount();
    }

    @Override
    public List<Dept> selectDeptList(Integer offset, Integer limit) {
        return deptMapper.selectDeptsByLimitAndOffset(offset,limit);
    }
}
