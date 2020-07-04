package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.utils.JsonUtils;
import com.itheima.mm.utils.UuidUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-21 10:59
 */
@Controller
public class CommonController {

    /**
    * @Description: 处理文件上传请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/21/0021
    */
    @RequestMapping("/common/upload")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取客户端上传的图片
            //创建磁盘工厂对象
            DiskFileItemFactory itemFactory = new DiskFileItemFactory();
            //创建Servlet的上传解析对象,构造方法中,传递磁盘工厂对象
            ServletFileUpload fileUpload = new ServletFileUpload(itemFactory);
            /*
             * fileUpload调用方法 parseRequest,解析request对象
             * 页面可能提交很多内容 文本框,文件,菜单,复选框 是为FileItem对象
             * 返回集合,存储的文件项对象
             */
            List<FileItem> list = fileUpload.parseRequest(request);
            String storePath = null;
            for (FileItem fileItem : list) {
                //判断fileItem是否是上传组件
                if (!fileItem.isFormField()) {
                    //1. 获取文件名
                    String fileName = fileItem.getName();
                    //2. 此时要给文件一个唯一的名字（UUID）
                    fileName = UuidUtil.getUuid()+fileName.substring(fileName.lastIndexOf("."));

                    //3. 创建一个目录(我们项目中的某一个目录)用于存储该文件
                    String uploadPath = request.getServletContext().getRealPath("upload");
                    File file = new File(uploadPath);
                    //该文件夹不存在
                    if (!file.exists()) {
                        //创建文件夹
                        file.mkdirs();
                    }

                    //4. 往文件夹中写文件,使用IO流
                    InputStream is = fileItem.getInputStream();
                    FileOutputStream os = new FileOutputStream(new File(file, fileName));
                    IOUtils.copy(is,os);

                    //文件已经存入到文件夹中
                    //5. 获取文件存储路径
                    storePath = "/upload/"+fileName;
                }
            }
            JsonUtils.printResult(response,new Result(true,"图片上传成功",storePath));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(true,"图片上传失败"));
        }
    }
}
