package cn.fb.controller;

import cn.fb.pojo.Dept;
import cn.fb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DeptController {
    @Autowired
    DeptService deptService;

    @RequestMapping(value="/dept/selectDept")
    public ModelAndView selectDept(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo){
        ModelAndView mv = new ModelAndView("dept/dept");
        //每页显示的记录行数
        int limit = 5;
        //总记录数
        int totalItems = deptService.getDeptCount();
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit== 0) ? temp : temp+1;
        //每页的起始行(offset+1)数据，如第一页(offset=0，从第1(offset+1)行数据开始)
        int offset = (pageNo - 1)*limit;
        List<Dept> depts = deptService.selectDeptList(offset, limit);

        mv.addObject("depts", depts)
                .addObject("recordCount", totalItems)
                .addObject("pageSize",limit)
                .addObject("totalSize", totalPages)
                .addObject("pageIndex", pageNo);
        return mv;
    }

}
