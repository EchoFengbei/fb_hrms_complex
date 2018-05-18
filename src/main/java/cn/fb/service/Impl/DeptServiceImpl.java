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

    @Override
    public Dept selectDeptById(Integer id) {
        return deptMapper.selectDeptById(id);
    }

    @Override
    public int deleteDeptById(Integer id) {
        return deptMapper.deleteDeptById(id);
    }

    /*插入*/
    @Override
    public void addDept(Dept dept) {
        deptMapper.addDept(dept);
    }

    /*根据id更新整个dept信息*/
    @Override
    public int updateDeptById(Integer id,Dept dept) {
        return deptMapper.updateDeptById(id,dept);
    }
}
