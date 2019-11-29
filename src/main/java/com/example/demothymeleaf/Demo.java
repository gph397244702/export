package com.example.demothymeleaf;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: gph
 * @Description:
 * @date: Created in 2019/7/19 16:52
 * @Modified By:
 */
@Controller
@RequestMapping("/demo")
public class Demo {
    /**
     * 得到request对象
     */
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;


    @Resource(name = "xmlTemplateEngine")
    private SpringTemplateEngine xmlTemplateEngine;

    @ResponseBody
    @RequestMapping()
    public String index(){
        return "hello";
    }
    @RequestMapping("/download")
    @ResponseBody
    public void download() throws IOException {
        Map<String, Object> info = new HashMap<>();
        Context context = new Context();
        ArrayList<String> list = new ArrayList<>();
        context.setVariable("product", info);
        context.setVariable("newList", list);
        info.put("name", "测试产品名称");
        info.put("company", "测试单位名称");
        list.add("测试1<w:br/>");
        list.add("测试2<w:br/>");
        list.add("测试3<w:br/>");
        String content = xmlTemplateEngine.process("application-form", context);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/msword");
        // 设置浏览器以下载的方式处理该文件默认名为resume.doc
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(("重大新产品申请表（企业提交后）").getBytes(), "iso8859-1") + ".doc");
        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(content.getBytes()));
        IOUtils.copy(bis, response.getOutputStream());
        bis.close();

    }


}
