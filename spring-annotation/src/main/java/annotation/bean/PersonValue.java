package annotation.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *使用@Value赋值；
 *  1、基本数值
 *  2、可以写SpEL； #{}
 *  3、可以写${}；取出配置文件【properties】中的值（在运行环境变量里面的值）
 */
public class PersonValue {
    @Value("张三")
    private String name;

    @Value("#{30-3}")
    private int age;

    @Value("${person.sex}")
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "PersonValue{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
