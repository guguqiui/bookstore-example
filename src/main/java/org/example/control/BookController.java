package org.example.control;

import org.example.entity.Books;
import org.example.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 获取分页书籍列表
    @GetMapping("/paged")
    public List<Books> listBooksPaged(
            @RequestParam(value = "name", defaultValue = "%") String name,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        // 计算偏移量
        int offset = page * size;
        // 调用服务层方法获取分页数据
        return bookService.getBooksPaged(name, offset, size);
    }

    // 获取所有书籍列表
    @GetMapping("/")
    public List<Books> listBooksByName(@RequestParam(value = "name", defaultValue = "%") String name) {
        return bookService.bookList(name);
    }


    // 根据ID查询书籍
    @GetMapping("/{id}")
    public Books findBookById(@PathVariable("id") int bookID) {
        return bookService.findByID(bookID);
    }

    // 添加书籍
    @PostMapping("/")
    public void addBook(@RequestParam("name") String name,
                        @RequestParam("count") int count,
                        @RequestParam("detail") String detail) {
        bookService.addBook(name, count, detail);
    }

    // 删除书籍
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") int bookID) {
        bookService.Del(bookID);
    }

    // 更新书籍信息
    @PutMapping("/{id}")
    public void updateBook(@PathVariable("id") int id,
                           @RequestParam("name") String name,
                           @RequestParam("counts") int counts,
                           @RequestParam("detail") String detail) {
        bookService.update(id, name, counts, detail);
    }
}
