package com.zxh.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义逻辑返回需要导入的组件
 */
public class MyImportSelector implements ImportSelector
{
    /**
     * 返回需要导入组件的数组
     * @param importingClassMetadata 当前标注@Import注解类的所有注解信息
     * @return
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.zxh.bean.Green","com.zxh.bean.Red"};
    }
}
