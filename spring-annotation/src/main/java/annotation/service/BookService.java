package annotation.service;

import annotation.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

@Service
public class BookService {
    //@Qualifier("bookDao3")
    //@Autowired(required = false)
    //@Resource
    @Inject
    private BookDao bookDao1;

    public void print(){
        System.out.println(bookDao1);
    }

    @Override
    public String toString() {
        return "BookService [bookDao=" + bookDao1 + "]";
    }
}
