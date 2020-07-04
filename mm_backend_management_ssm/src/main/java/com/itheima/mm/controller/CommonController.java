package com.itheima.mm.controller;

import com.itheima.mm.entity.Result;
import com.itheima.mm.utils.UploadUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-21 10:59
 */
@RestController
@RequestMapping("common")
public class CommonController {

    /**
     * @Description: 处理文件上传请求的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/6/21/0021
     */
    @RequestMapping("upload")
    public Result upload(MultipartFile icon, HttpServletRequest request){
        try {
            //获取上传文件名
            String originalFilename = icon.getOriginalFilename();
            //创建唯一的文件名
            String uuidName = UploadUtils.getUUIDName(originalFilename);

            //3. 创建一个目录(我们项目中的某一个目录)用于存储该文件
            String realPath = request.getServletContext().getRealPath("upload");
            File file = new File(realPath);
            //该文件夹不存在
            if (!file.exists()) {
                //创建文件夹
                file.mkdirs();
            }

            //4. 往文件夹中写文件
            icon.transferTo(new File(file, uuidName));

            //文件已经存入到文件夹中
            //5. 获取文件存储路径
            String storePath = "/upload/" + uuidName;
            return new Result(true, "图片上传成功", storePath);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "图片上传失败");
        }
    }
}
