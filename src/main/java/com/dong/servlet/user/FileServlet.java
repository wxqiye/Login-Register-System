/*
* 具体实现了头像文件的上传，并将路径存到session中。
* */
package com.dong.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dong.service.user.UserService;
import com.dong.service.user.UserServiceImpl;
import com.dong.utl.Constants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.io.*;
import java.util.List;
import java.util.UUID;
public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //创建上传文件的路径，不存在则创建
        String uploadPath = this.getServletContext().getRealPath("/img");//获取/WEB-INF下的/upload路径
        File uploadFile = new File(uploadPath);//创建一个文件对象
        if (!uploadFile.exists()){
            uploadFile.mkdir();
        }

        //创建临时路径，不存在则创建
        String tmpPath = this.getServletContext().getRealPath("WEB-INF/tmp");
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdir();
        }


        /*
        *使用fileUpload固定步骤：
        *1.创建工厂类
        *2.创建解析器
        *3.使用解析器解析request对象
        * */
        //实现了文件的上传。
        DiskFileItemFactory factory = getDiskFileItemFactory(file);
        ServletFileUpload upload = getServletFileUpload(factory);
        String msg = uploadParasRequest(upload, req, uploadPath);


        //存msg信息，提示文件上传成功，不过这里我没有去用到。
        req.setAttribute("msg",msg);
        //跳回个人信息页面
        req.getRequestDispatcher("information.jsp").forward(req,resp);
    }

    public  DiskFileItemFactory getDiskFileItemFactory(File file){

        /*
            1.由于在使用ServletFileUpload对象解析请求时，需要DiskFileItemFactory对象。
            所以我们需要在进行解析工作前构造好DiskFileItemFactory对象。
            2.通过ServletFileUpload对象的构造方法或setFileItemFactory()
            方法设置ServletFileUpload对象的fileItemFactory属性。
            */

        //1、创建DiskFileItemFactory对象，获取磁盘对象
        DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024,file);
        return factory;
    }
        //2、创建ServletFileUpload对象，获取上传文件的解析对象
    public  ServletFileUpload getServletFileUpload(DiskFileItemFactory factory){
        ServletFileUpload upload = new ServletFileUpload(factory);//DiskFileItemFactory对象作为参数传入ServletFileUpload的构造中
        return upload;
    }
        //3、正式解析表单中上传的文件，并将其存储在服务器上指定的位置
    public  String uploadParasRequest(ServletFileUpload upload,HttpServletRequest req,String uploadPath) throws UnsupportedEncodingException {
        String msg = null;
        try {
        //使用文件解析对象parseRequest()，他会将req中的表单项变成FileItem对象来进行封装
            List<FileItem> fileItems = upload.parseRequest(req);

        //遍历。
            for (FileItem fileItem : fileItems) {//遍历，找到表单中每一个文件对应的<input>上传的文件数据

                //如果此数据不是文件，输出显示
                if (fileItem.isFormField()) {
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("utf-8");
                    System.out.println(name + ":" + value);//输出显示
                }

                //如果这个<input>中的数据是文件
                else {

                    //处理文件：获取文上传的文件的文件名+文件类型
                    String uploadFileName = fileItem.getName();
                    System.out.println("上传的文件名：" + uploadFileName);
                    if (uploadFileName.trim().equals("") || uploadFileName == null) {//为空则跳过
                        continue;
                    }
                    //获取具体的文件名
                    String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                    String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
                    System.out.println("文件信息[文件名：" + fileName + "----文件类型" + fileExtName+"]");



                    //使用UUID，保证文件名唯一。
                    String uuidPath = UUID.randomUUID().toString() ;

                    //创建文件真正存放路径realPath = /WEB-INF/upload + 文件上传时生成的唯一的UUID
                    String realPath = uploadPath + "/" + uuidPath;
                    File realPathFile = new File(realPath);
                    if (!realPathFile.exists()) {
                        realPathFile.mkdir();
                    }



                    //文件传输：配合工具类fileName+文件IO操作，实现文件存储在服务器上
                        //获取数据流
                    InputStream in = null;
                    try {
                        in = fileItem.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        //创建一个文件输入流，读取
                    FileOutputStream fos = new FileOutputStream(realPath + "/" + fileName);//获取文件输入流
                    byte[] buffer = new byte[1024];//创建一个缓冲区
                    int len = 0;//定义一个变量存储一次读到的实际数据量
                    while ((len = in.read(buffer)) > 0) {//通过判断实际读取的数据量是不是>0就可以判断文件是不是读完了
                        fos.write(buffer, 0, len);//将文件流写到这个文件中 ——“realPath + "/" + fileName”
                    }
                    //关闭流
                    in.close();
                    fos.close();

                    //上传成功，清除临时文件
                    msg="文件上传成功!";
                    fileItem.delete();

                    //将头像存储路径存到session中
                    String path = "img/"+uuidPath+"/"+fileName;
                    req.getSession().setAttribute(Constants.USER_PATH,path);



                }

            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
