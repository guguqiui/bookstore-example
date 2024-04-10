package org.example.service;

import org.example.entity.Books;
import org.example.mapper.MainMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {
    //    数据源
    @Resource
    MainMapper mapper;

    // 分页查询书籍列表
    public List<Books> getBooksPaged(String name, int page, int pageSize) {
        int offset = page * pageSize;
        return mapper.bookListPaged(name, offset, pageSize);
    }

    //    显示所有书籍的列表
    public List<Books> bookList(String name) {
        return mapper.bookList("%");
    }

    //    删除书籍操作
    public void Del(int bookID) {
        mapper.Del(bookID);
    }

    //    添加书籍操作
    public void addBook(String name, int count, String detail) {
        mapper.addBook(name, count, detail);
    }

    //    修改书籍操作，这里由于是涉及到了修改，所以自然要回显数据
    public Books findByID(int bookID) {
        return mapper.findByID(bookID);
    }

    //    更新操作
    public void update(int id, String name, int counts, String detail) {
        mapper.update(id, name, counts, detail);
    }


}

