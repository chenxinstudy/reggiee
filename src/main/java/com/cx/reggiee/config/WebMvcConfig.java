//package com.cx.reggiee.config;/**
// * Created with IntelliJ IDEA.
// *
// * @Author:cx
// * @Description:
// * @Date: 2022/04/26/15:27:31
// */
//
//import com.cx.reggiee.common.JacksonObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import java.util.List;
//
///**
// * @program: reggiee
// *
// * @description: 消息转换器
// *
// * @author: 科城小鑫
// *
// *
// **/
//@Slf4j
////@Configuration
//public class WebMvcConfig extends WebMvcConfigurationSupport {
//    /**
//    *设置静态资源映射
//    */
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        log.info("开始资源映射...");
//        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
//        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
//    }
//
//    /**
//    *自定义消息转换器
//    */
//    @Override
//    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        log.info("扩展消息转换器...");
//        //创建消息转换器
//        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
//        //设置对象转换器，底层使用jackson将Java对象转换为json对象
//        messageConverter.setObjectMapper(new JacksonObjectMapper());
//        //讲上面的消息转换器对象追加到webmvc中 index设置为0，第一个使用我们设置的
//        converters.add(0,messageConverter);
//
//    }
//}
