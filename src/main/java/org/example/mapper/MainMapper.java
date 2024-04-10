package org.example.mapper;


import org.example.entity.Books;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MainMapper {

    // 查看所有数据，支持分页查询
    @Select("SELECT * FROM books WHERE bookName LIKE #{name} LIMIT #{offset}, #{pageSize}")
    List<Books> bookListPaged(@Param("name") String name, @Param("offset") int offset, @Param("pageSize") int pageSize);


    //    查看所有数据，这里用到了模糊查询
    @Select("select *from books where bookName like#{name}")
    List<Books> bookList(String name);

    //    查找书籍，用于修改书籍时候的数据回显
    @Select("select * from books where bookID=#{bookID}")
    Books findByID(@Param("bookID") int bookID);


    //    删除书籍
    @Delete("delete from books where bookID=#{bookID}")
    void Del(@Param("bookID") int bookID);

    //    增加书籍
    @Insert("insert into books (bookName,bookCounts,detail) values ( #{bookName} , #{bookCounts} , #{detail} )")
    void addBook(@Param("bookName") String bookName, @Param("bookCounts") int bookCounts, @Param("detail") String Detail);

    //    修改书籍
    @Update("update books set bookName=#{bookName} , bookCounts=#{bookCounts} , detail=#{detail} where bookID=#{bookID}")
    void update(@Param("bookID")int bookID,@Param("bookName") String bookName, @Param("bookCounts") int bookCounts, @Param("detail") String Detail);


}

