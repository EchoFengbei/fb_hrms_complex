package cn.fb.controller;

import cn.fb.pojo.Notice;
import cn.fb.pojo.User;
import cn.fb.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NoticeController {

    @Autowired
    NoticeService noticeService;
/*查询*/
    @RequestMapping(value="/notice/selectNotice")
    public ModelAndView selectNotice(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        ModelAndView mv = new ModelAndView("notice/notice");
        int limit = 5;
        // 记录的偏移量(即从第offset行记录开始查询)，
        // 如第1页是从第1行(offset=(21-1)*5=0,offset+1=0+1=1)开始查询；
        // 第2页从第6行(offset=(2-1)*5=5,offset+1=5+1=6)记录开始查询
        int offset = (pageNo - 1) * limit;
        //获取指定页数包含的员工信息
        List<Notice> notices = noticeService.selectNoticeList(offset, limit);

        //获取总的记录数
        int totalItems = noticeService.getNoticeCount();
        //获取总的页数
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp + 1;
        //当前页数
        int curPage = pageNo;

        mv.addObject("notices", notices)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPage", curPage);

        return mv;
    }
    /*根据id预览*/
    @RequestMapping(value="/notice/previewNotice")
    public String previewNotice(
            Integer id,Model model){

        Notice notice = noticeService.findNoticeById(id);

        model.addAttribute("notice", notice);
        // 返回
        return "notice/previewNotice";
    }
    /*删除*/
    @RequestMapping(value="/notice/removeNotice")
    public ModelAndView removeNotice(String ids,ModelAndView mv){
        // 分解id字符串
        String[] idArray = ids.split(",");
        for(String id : idArray){
            // 根据id删除公告
            noticeService.removeNoticeById(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/notice/selectNotice");
        // 返回ModelAndView
        return mv;
    }
    /*添加*/
    @RequestMapping(value="/notice/addNotice")
    public ModelAndView addNotice(
            String flag,
            @ModelAttribute Notice notice,
            ModelAndView mv,
            HttpSession session){
//        System.out.println("111111111111111111111NOTICE........"+flag);
        if(flag.equals("1")){
            mv.setViewName("notice/showAddNotice");
//            System.out.println("2222222222222222222NOTICE........"+flag);
        }else{
//            System.out.println("33333333333333333333NOTICE........"+flag);
            User user = (User) session.getAttribute("user_session");
            notice.setUser(user);
            noticeService.addNotice(notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }
        // 返回
        return mv;
    }
    /*更新*/
    @RequestMapping(value="/notice/updateNotice")
    public ModelAndView updateNotice(
            String flag,
            @ModelAttribute Notice notice,
            ModelAndView mv,
            HttpSession session){
        if(flag.equals("1")){
            Notice target = noticeService.findNoticeById(notice.getId());
            mv.addObject("notice",target);
            mv.setViewName("notice/showUpdateNotice");
        }else{
            noticeService.updateNotice(notice.getId(),notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }
        // 返回
        return mv;
    }
}
