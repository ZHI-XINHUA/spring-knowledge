package annotation.configure;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;


import java.io.IOException;

/**
 * 扫描，自定义filter
 */
public class MyTypeFilter implements TypeFilter {
    /**
     * @param metadataReader 读取到的当前正在扫描的类的信息
     * @param metadataReaderFactory 可以获取到其他任何类信息的
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        //获取当前正在扫描的类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        String className = classMetadata.getClassName();
        //System.out.println(className);

        //获取当前类资源（类的路径）
        Resource resource =  metadataReader.getResource();

        //System.out.println( resource.getURL());


        //System.out.println("className="+className);
        if(className.contains("Controller")){
            return true;
        }
        return false;
    }
}
