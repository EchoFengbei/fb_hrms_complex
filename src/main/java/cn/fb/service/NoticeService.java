package cn.fb.service;

import cn.fb.pojo.Notice;
import cn.fb.pojo.User;

import java.util.List;

public interface NoticeService {
    List<Notice> selectNoticeList(Integer offset,Integer limit);
    int getNoticeCount();

    Notice findNoticeById(Integer id);

    void removeNoticeById(Integer id);

    void addNotice(Notice notice);

    void updateNotice(Integer id, Notice notice);
}
