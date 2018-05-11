package cn.fb.service;

import cn.fb.pojo.Dept;

import java.util.List;

public interface DeptService {

    int getDeptCount();
    List<Dept> selectDeptList(Integer offset,Integer limit);
}
