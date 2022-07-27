package com.buba.boot_phoenix.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: zl,
 * Date:    2022/6/30 09:36,
 * Version: IntelliJ IDEA 2021.2.1
 * 自动填充策略 Component注解
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 功能描述: 插入数据自动更新时间
     *
     * @param metaObject
     * @return  void 
     * @throws  
     * @Author: zl
     * @Date:   2022/6/30 9:38
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    /**
     * 功能描述: 更新数据自动更新时间
     *
     * @param metaObject
     * @return  void 
     * @throws  
     * @Author: zl
     * @Date:   2022/6/30 9:38
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
