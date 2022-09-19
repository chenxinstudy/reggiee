package com.cx.reggiee.controller;
/**
 * Created with IntelliJ IDEA. @Author:cx @Description: @Date: 2022/04/27/22:12:56
 */

import com.cx.reggiee.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @program: reggiee
 * @description: 文件上传下载
 * @author: 科城小鑫
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;

    @RequestMapping("/upload")
    public R<String> upload(MultipartFile file) {
        // file是一个临时文件，请求结束后会自动消除
        log.info(file.toString());

        // 原始文件名
        String originalFilename = file.getOriginalFilename();

        // 获取原始文件名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 使用UUID重新生成文件名，防止文件名重复造成文件覆盖
        String fileName = UUID.randomUUID() + suffix;

        // 创建一个目录对象file,判断是否存在目录结构，没有就创建一个
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 将文件存到指定的位置
        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }

    /** 文件下载 */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        try {
            // 输入流，读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            // 输出流，写回浏览器
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            //读数据
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                //o,len 从第一个写，到len
                outputStream.write(bytes, 0, len);
                //刷新一下
                outputStream.flush();
            }
            //关闭资源
            outputStream.close();
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
