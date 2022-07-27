package com.buba.boot_phoenix.utils;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 代码生成器
 * @Author: zl,
 * Date:    2022/6/18 15:01,
 * Version: IntelliJ IDEA 2021.2.1
 *
 */
public class Generator {
    public static void main(String[] args) {
        generate();
    }

    private static void generate(){
        FastAutoGenerator.create("jdbc:mysql://localhost/phoenix_?serverTimezone=Asia/Shanghai&useUnicode=true&" +
                        "characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true",
                        "root", "root")
                .globalConfig(builder -> {
                    builder.author("ZL") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .commentDate("yyyy-MM-dd")
.outputDir("D:\\assignment\\Big_Data_Knowledge\\JAVA\\IDEAWorkSpace\\Vue_Boot\\smart_campus\\boot_phoenix\\src\\main\\java\\"); // 指定输出目录 *
                })
                .packageConfig(builder -> {
                    builder.parent("com.buba.boot_phoenix.grade") // 设置父包名 *
                            .moduleName("") // 设置父包模块名
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
"D:\\assignment\\Big_Data_Knowledge\\JAVA\\IDEAWorkSpace\\Vue_Boot\\smart_campus\\boot_phoenix\\src\\main\\resources\\mapper\\grade\\")); // 设置mapperXml生成路径 *
                })
                .strategyConfig(builder -> {
                    builder.addInclude("grade") // 设置需要生成的表名 *
                            .addTablePrefix("t_", "employee_")// 设置过滤表前缀 *
                            .serviceBuilder() //service生成策略
                            .formatServiceFileName("%sService")  //service类名，%s适配，根据表明替换
                            .formatServiceImplFileName("%sServiceImpl")  // service实现类
                            .entityBuilder()        //实体类生成策略
                            .enableLombok()         //开启lombok
//                            .logicDeleteColumnName("deleted")  //说明逻辑删除是哪个字段
                            .enableTableFieldAnnotation()  // 属性加上说明注解
                            .controllerBuilder()  //controller生成策略
                            .enableHyphenStyle() //开启驼峰转连字符
                            .formatFileName("%sController")
                            .enableRestStyle()  //开启RestController
                            .mapperBuilder()
                            .superClass(BaseMapper.class)  //dao层继承父类
                            .formatMapperFileName("%sMapper")
//                            .enableMapperAnnotation() //@Mapper注解开启
                            .formatXmlFileName("%sMapper");
                })

//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
