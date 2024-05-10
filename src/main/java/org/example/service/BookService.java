package org.example.service;

import org.example.entity.Books;
import org.example.mapper.MainMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {
    @Resource
    MainMapper mapper;

    @Cacheable(value = "booksPaged", key = "#name + '_' + #page + '_' + #pageSize")
    public List<Books> getBooksPaged(String name, int page, int pageSize) {
        int offset = page * pageSize;
        return mapper.bookListPaged(name, offset, pageSize);
    }

    @Cacheable(value = "bookList", key = "#name")
    public List<Books> bookList(String name) {
        return mapper.bookList("%");
    }

    @CacheEvict(value = {"booksPaged", "bookList"}, allEntries = true)
    public void Del(int bookID) {
        mapper.Del(bookID);
    }

    @CacheEvict(value = {"booksPaged", "bookList"}, allEntries = true)
    public void addBook(String name, int count, String detail) {
        mapper.addBook(name, count, detail);
    }

    @Cacheable(value = "books", key = "#bookID")
    public Books findByID(int bookID) {
        return mapper.findByID(bookID);
    }

    @CacheEvict(value = {"booksPaged", "bookList", "books"}, allEntries = true)
    public void update(int id, String name, int counts, String detail) {
        mapper.update(id, name, counts, detail);
    }
}
