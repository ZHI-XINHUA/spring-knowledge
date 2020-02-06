package com.zxh.filter;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;


import java.io.IOException;

/**
 * 自定义的TypeFilter
 */
public class MyTypeFilter implements TypeFilter
{
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类注解信息
        AnnotatedTypeMetadata annotatedTypeMetadata = metadataReader.getAnnotationMetadata();


        //获取当前正在扫描的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();

        //获取当前类资源（类路径）
        Resource resource =  metadataReader.getResource();

        if(classMetadata.getClassName().contains("BookService")){
            return true;
        }

        return false;
    }
}
