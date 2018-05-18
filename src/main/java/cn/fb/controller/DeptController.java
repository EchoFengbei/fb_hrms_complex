package cn.fb.controller;

import cn.fb.pojo.Dept;
import cn.fb.pojo.Employee;
import cn.fb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DeptController {
    @Autowired
    DeptService deptService;
    //1.展示所有dept信息,查询的一部分
    @RequestMapping(value="/dept/selectDept")
    public ModelAndView selectDept(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo){
        ModelAndView mv = new ModelAndView("dept/dept");
        int limit = 5;
        // 记录的偏移量(即从第offset行记录开始查询)，
        // 如第1页是从第1行(offset=(21-1)*5=0,offset+1=0+1=1)开始查询；
        // 第2页从第6行(offset=(2-1)*5=5,offset+1=5+1=6)记录开始查询
        int offset = (pageNo-1)*limit;
        //获取指定页数包含的员工信息
        List<Dept> depts = deptService.selectDeptList(offset, limit);
        //获取总的记录数
        int totalItems = deptService.getDeptCount();
        //获取总的页数
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp+1;
        //当前页数
        int curPage = pageNo;

        mv.addObject("depts", depts)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPage", curPage);
        return mv;
    }
    /*1.*根据部门名称,id查询*/
    /*2.删除dept信息*/
    /**
     * 处理删除部门请求,删除多个或者一个
     * */
    @RequestMapping(value="/dept/removeDept")
    public ModelAndView removeDept(String ids,ModelAndView mv){
        // 分解id字符串
        String[] idArray = ids.split(",");
        for(String id : idArray){
            // 根据id删除部门
            deptService.deleteDeptById(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/dept/selectDept");
        // 返回ModelAndView
        return mv;
    }
    //表单结尾实现删除一个
    @RequestMapping(value="/dept/removeDeptSingle")
    public ModelAndView removeDeptSingle(Integer id,ModelAndView mv){
        // 分解id字符串
        deptService.deleteDeptById(id);
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/dept/selectDept");
        // 返回ModelAndView
        return mv;
    }
    /*3.增加信息*/
    @RequestMapping(value = "/dept/addDept")
    public ModelAndView addDept(String flag,
                                @ModelAttribute Dept dept,
                                ModelAndView mv){
        if(flag.equals("1")){
            // 设置跳转到添加页面
            mv.setViewName("dept/showAddDept");
        }else{
            // 执行添加操作
            deptService.addDept(dept);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/dept/selectDept");
        }
        // 返回
        return mv;
    }
    /*4.更新信息*/
    @RequestMapping(value="/dept/updateDept")
    public ModelAndView updateDpet(
            String flag,
            @ModelAttribute Dept dept,
            ModelAndView mv){
        if(flag.equals("1")){
            // 根据id查询部门
            Dept target = deptService.selectDeptById(dept.getId());
            // 设置Model数据
            mv.addObject("dept", target);
            // 设置跳转到修改页面
            mv.setViewName("dept/showUpdateDept");
        }else{
            // 执行修改操作
            deptService.updateDeptById(dept.getId(),dept);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/dept/selectDept");
        }
        // 返回
        return mv;
    }
}
