package cn.fb.service;

import cn.fb.pojo.Dept;

import java.util.List;

public interface DeptService {

    int getDeptCount();
    List<Dept> selectDeptList(Integer offset,Integer limit);
    Dept selectDeptById(Integer id);

    int deleteDeptById(Integer id);

    void addDept(Dept dept);

    int updateDeptById(Integer id,Dept dept);
}
