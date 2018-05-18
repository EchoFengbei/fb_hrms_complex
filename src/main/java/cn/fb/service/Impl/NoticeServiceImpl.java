package cn.fb.service.Impl;

import cn.fb.mapper.NoticeMapper;
import cn.fb.pojo.Notice;
import cn.fb.pojo.User;
import cn.fb.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<Notice> selectNoticeList(Integer offset, Integer limit) {
        return noticeMapper.selectNoticeList(offset,limit);
    }

    @Override
    public int getNoticeCount() {
        return noticeMapper.getCount();
    }

    @Override
    public Notice findNoticeById(Integer id) {
        return noticeMapper.findNoticeById(id);
    }

    @Override
    public void removeNoticeById(Integer id) {
        noticeMapper.removeNoticeById(id);
    }

    @Override
    public void addNotice(Notice notice) {
        noticeMapper.addNotice(notice);
    }

    @Override
    public void updateNotice(Integer id, Notice notice) {
        noticeMapper.updateNotice(id,notice);
    }
}
