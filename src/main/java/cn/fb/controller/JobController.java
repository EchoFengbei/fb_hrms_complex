package cn.fb.controller;

import cn.fb.pojo.Job;
import cn.fb.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class JobController {
    @Autowired
    JobService jobService;
    /*1.查询所有数据*/
    @RequestMapping(value="/job/selectJob")
    public ModelAndView selectJob(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo){
        ModelAndView mv = new ModelAndView("job/job");
        int limit = 5;
        // 记录的偏移量(即从第offset行记录开始查询)，
        // 如第1页是从第1行(offset=(21-1)*5=0,offset+1=0+1=1)开始查询；
        // 第2页从第6行(offset=(2-1)*5=5,offset+1=5+1=6)记录开始查询
        int offset = (pageNo-1)*limit;
        //获取指定页数包含的员工信息
        List<Job> jobs = jobService.selectJobList(offset, limit);
        //获取总的记录数
        int totalItems = jobService.getJobCount();
        //获取总的页数
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp+1;
        //当前页数
        int curPage = pageNo;

        mv.addObject("jobs", jobs)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPage", curPage);
        return mv;
    }
    /*删除*/
    @RequestMapping(value="/job/removeJob")
    public ModelAndView removeJob(String ids,ModelAndView mv){
        // 分解id字符串
        String[] idArray = ids.split(",");
        for(String id : idArray){
            // 根据id删除职位
            jobService.removeJobById(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/job/selectJob");
        // 返回ModelAndView
        return mv;
    }

    /*增加*/
    @RequestMapping(value="/job/addJob")
    public ModelAndView addJob(
            String flag,
            @ModelAttribute Job job,
            ModelAndView mv){
        if(flag.equals("1")){
            // 设置跳转到添加页面
            mv.setViewName("job/showAddJob");
        }else{
            // 执行添加操作
            jobService.addJob(job);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/job/selectJob");
        }
        // 返回
        return mv;
    }
    /*更新*/
    @RequestMapping(value="/job/updateJob")
    public ModelAndView updateDpet(
            String flag,
            @ModelAttribute Job job,
            ModelAndView mv){
        if(flag.equals("1")){
            // 根据id查询部门
            Job target = jobService.selectJobById(job.getId());
            // 设置Model数据
            mv.addObject("job", target);
            // 设置跳转到修改页面
            mv.setViewName("job/showUpdateJob");
        }else{
            // 执行修改操作
            jobService.updateJobById(job.getId(),job);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/job/selectJob");
        }
        // 返回
        return mv;
    }

}
