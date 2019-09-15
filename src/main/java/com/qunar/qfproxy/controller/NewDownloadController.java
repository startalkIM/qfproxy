package com.qunar.qfproxy.controller;

import com.qunar.qfproxy.constants.StorageConfig;
import com.qunar.qfproxy.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Controller
@RequestMapping(value = {"/v1", "v2"})
public class NewDownloadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewDownloadController.class);

    @Autowired
    private DownloadService downloadService;

    @RequestMapping(value = "/download/{key:.*}", method = RequestMethod.GET)
    public void download(
            @PathVariable(value = "key") String key,
            @RequestParam(value = "name", required = false) String name,
            HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {

        String fileName = key;
        if (fileName == null || fileName.trim().length() == 0) {
            resp.reset();
            resp.setContentType("text/plain;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.write("error:can't get the file name! 不能获取文件名");
            writer.flush();
            return;
        }
        fileName = StorageConfig.SWIFT_FOLDER + fileName;
        downloadService.downloadService(fileName,req,resp);
    }
}
