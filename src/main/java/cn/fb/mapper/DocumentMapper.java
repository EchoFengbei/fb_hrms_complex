package cn.fb.mapper;

import cn.fb.pojo.Document;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DocumentMapper {
    @Select("select count(*) from document_inf")
    int getDocCount();

    List<Document> selectDocList(@Param("offset") Integer offset,@Param("limit") Integer limit);

    /*添加*/

    void addDocument(@Param("document") Document document);

    Document findDocumentById(@Param("id") Integer id);

    @Update("update document_inf set title = #{document.title},remark = #{document.remark} where id = #{document.id}")
    void updateDocument(@Param("id") Integer id,@Param("document") Document document);
}
