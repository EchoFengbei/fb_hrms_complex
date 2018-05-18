package cn.fb.service.Impl;

import cn.fb.mapper.DocumentMapper;
import cn.fb.pojo.Document;
import cn.fb.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentMapper documentMapper;
    @Override
    public List<Document> selectDocList(Integer offset,Integer limit) {
        return documentMapper.selectDocList(offset,limit);
    }

    @Override
    public int getDocCount() {
        return documentMapper.getDocCount();
    }

    @Override
    public void addDocument(Document document) {
        documentMapper.addDocument(document);
    }

    @Override
    public Document findDocumentById(Integer id) {
        return documentMapper.findDocumentById(id);
    }

    @Override
    public void updateDocument(Integer id,Document document) {
        documentMapper.updateDocument(id,document);
    }
}
