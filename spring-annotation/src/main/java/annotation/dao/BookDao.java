package annotation.dao;

import org.springframework.stereotype.Component;
//名字默认是类名首字母小写
@Component
public class BookDao {
    private String lable = "1";

    public BookDao(){
        super();
    }

    public BookDao(String lable){
        this.lable = lable;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return "BookDao [lable=" + lable + "]";
    }
}
