package cn.fb.service;

import cn.fb.pojo.Document;

import java.util.List;

public interface DocumentService {
    List<Document> selectDocList(Integer offset,Integer limit);
    int getDocCount();
    void addDocument(Document document);

    Document findDocumentById(Integer id);
    void updateDocument(Integer id,Document document);
}
