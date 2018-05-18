package cn.fb.mapper;

import cn.fb.pojo.Notice;
import cn.fb.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface NoticeMapper {
//    @Select("select * from notice_inf limit #{offset}, #{limit}")
    List<Notice> selectNoticeList(@Param("offset") Integer offset,@Param("limit") Integer limit);
    @Select("select count(*) from notice_inf")
    int getCount();
//    @Select("select * from notice_inf where id = #{id}")
    Notice findNoticeById(@Param("id") Integer id);

    @Delete("delete from notice_inf where id= #{id}")
    void removeNoticeById(@Param("id") Integer id);

//    @Insert("insert into notice_inf (title,content,user_id) values (#{notice.title},#{notice.content},#{notice.user.id})")
    void addNotice(@Param("notice") Notice notice);

    @Update("update notice_inf set title = #{notice.title},content = #{notice.content} where id = #{notice.id}")
    void updateNotice(@Param("id") Integer id, @Param("notice") Notice notice);
}
