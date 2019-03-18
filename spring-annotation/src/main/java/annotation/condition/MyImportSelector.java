package annotation.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义要导入的组件
 */
public class MyImportSelector implements ImportSelector {
    /**
     * 返回值，就是到导入到容器中的组件全类名
     * 	AnnotationMetadata:当前标注@Import注解的类的所有注解信息
     * @param annotationMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //System.out.println("=====MyImportSelector====");
        //String className = annotationMetadata.getClassName();
        //annotation.configure.MainConfig02
        //System.out.println(className);
        //System.out.println("=====MyImportSelector====");

        //方法不要返回null值
        return new String[]{"annotation.bean.Blue","annotation.bean.Color"};
    }
}
