package cn.fb.controller;

import cn.fb.pojo.Document;
import cn.fb.pojo.User;
import cn.fb.service.DocumentService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
public class DocumentController {

    @Autowired
    DocumentService documentService;
    @RequestMapping(value="/document/selectDocument")
    public ModelAndView selectDocument(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo){
        ModelAndView mv = new ModelAndView("document/document");
        int limit = 5;
        // 记录的偏移量(即从第offset行记录开始查询)，
        // 如第1页是从第1行(offset=(21-1)*5=0,offset+1=0+1=1)开始查询；
        // 第2页从第6行(offset=(2-1)*5=5,offset+1=5+1=6)记录开始查询
        int offset = (pageNo-1)*limit;
        //获取指定页数包含的员工信息
        List<Document> documents = documentService.selectDocList(offset, limit);
        //获取总的记录数
        int totalItems = documentService.getDocCount();
        //获取总的页数
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp+1;
        //当前页数
        int curPage = pageNo;

        mv.addObject("documents", documents)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPage", curPage);
        return mv;
    }
    /*添加，即上传文件*/
    @RequestMapping(value="/document/addDocument")
    public ModelAndView addDocument(String flag,
                                    @ModelAttribute Document document,
                                    ModelAndView mv,
                                    HttpSession session,
                                    HttpServletRequest request)throws Exception {
        System.out.println("111111111111111111111DOCUMENT........"+flag);

        if (flag.equals("1")) {
            mv.setViewName("document/showAddDocument");
            System.out.println("2222222222222222222DOCUMENT........"+flag);
        } else {
            // 上传文件路径
            System.out.println("33333333333333333333DOCUMENT........"+flag);
            String path = session.getServletContext().getRealPath(
                    "upload");
            System.out.println(path);
            // 上传文件名
            String fileName = document.getFile().getOriginalFilename();
            // 将上传文件保存到一个目标文件当中
            document.getFile().transferTo(new File(path + File.separator + fileName));

            // 插入数据库
            // 设置fileName
            document.setFileName(fileName);
            // 设置关联的User对象
            User user = (User) session.getAttribute("user_session");
            document.setUser(user);
            // 插入数据库
            documentService.addDocument(document);
            // 返回
            mv.setViewName("redirect:/document/selectDocument");
        }
        // 返回
        return mv;
    }
    /*修改*/
    @RequestMapping(value="/document/updateDocument")
    public ModelAndView updateDocument(
            String flag,
            @ModelAttribute Document document,
            ModelAndView mv){
        if(flag.equals("1")){
            // 根据id查询文档
            Document target = documentService.findDocumentById(document.getId());
            // 设置Model数据
            mv.addObject("document", target);
            // 设置跳转到修改页面
            mv.setViewName("document/showUpdateDocument");
        }else{
            // 执行修改操作
            documentService.updateDocument(document.getId(),document);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/document/selectDocument");
        }
        // 返回
        return mv;
    }
//
//    /**
//     * 处理文档下载请求
//     * */
    @RequestMapping(value="/document/downLoad")
    public ResponseEntity<byte[]> downLoad(
            Integer id,
            HttpSession session) throws Exception{
        // 根据id查询文档


        Document target = documentService.findDocumentById(id);
        String fileName = target.getFileName();
        // 上传文件路径
        String path = session.getServletContext().getRealPath(
                "/upload/");
        // 获得要下载文件的File对象
        File file = new File(path+File.separator+ fileName);
        // 创建springframework的HttpHeaders对象
        HttpHeaders headers = new HttpHeaders();
        // 下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        // 通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        // application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 201 HttpStatus.CREATED
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
}
