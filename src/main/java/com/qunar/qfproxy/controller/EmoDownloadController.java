package com.qunar.qfproxy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import com.google.common.net.HttpHeaders;
import com.qunar.qfproxy.constants.Config;
import com.qunar.qfproxy.constants.QMonitorConstants;
import com.qunar.qfproxy.constants.StorageConfig;
import com.qunar.qfproxy.model.EmoPackConf;
import com.qunar.qfproxy.model.JsonResult;
import com.qunar.qfproxy.service.DownloadService;
import com.qunar.qfproxy.service.InsertEmo;
import com.qunar.qfproxy.utils.EmoPackageUtil;
import com.qunar.qfproxy.utils.ErrorCodeUtil;
import com.qunar.qfproxy.utils.HttpUtils;
import com.qunar.qfproxy.utils.JacksonUtil;
import com.qunar.qfproxy.utils.imgtype.ImgTypeUtils;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * EmoDownloadController
 *
 * @author binz.zhang
 * @date 2019/1/23
 */
@Controller
@RequestMapping(value = {"/v1/emo/", "/v2/emo"})
public class EmoDownloadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmoDownloadController.class);


    @Autowired
    private DownloadService downloadService;

    @Resource
    private EmoPackageUtil emoPackageUtil;

    @Resource
    private InsertEmo insertEmo;

    @RequestMapping("/d/z/{packageName:.*}")
    @ResponseBody
    public void downloadEmotions(@PathVariable(value = "packageName") String packageName,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        if (!StringUtils.endsWithIgnoreCase(packageName, ".zip")) {
            packageName = StringUtils.join(packageName, ".zip");
        }
        LOGGER.info("get the emo package:{}", packageName);
        try {
            downloadService.downloadService(StorageConfig.SWIFT_FOLDER_EMO_PACKAGE + packageName, request, response);
        } catch (IOException e) {
            LOGGER.error("emo download fail", e);
        }
    }

    @RequestMapping("/d/e/{packageKey}/{shortcut}/{typeName}")
    @ResponseBody
    public void downloadEmotion(@PathVariable(value = "packageKey") String packageKey,
                                @PathVariable(value = "shortcut") String shortcut,
                                @PathVariable(value = "typeName") String typeName,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        StringBuilder emoPostion = new StringBuilder();
        emoPostion.append(StorageConfig.SWIFT_FOLDER_EMO_PACKAGE).append(packageKey).append("/");
        try {
            File files = new File(emoPostion.toString());
            String[] names = files.list();    //获取F盘根目录所有文件和路径,并以字符串数组返回
            boolean findFlag = false;
            for (String name : names) {
                if (name.startsWith(shortcut + ".")) {
                    emoPostion.append(name);
                    findFlag = true;
                    break;
                }
            }
            if (!findFlag) {
                response.reset();
                response.setContentType("text/plain;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write("error: file not exist! 文件不存在");
                writer.flush();
                return;
            }
            downloadService.downloadService(emoPostion.toString(), request, response);

        } catch (IOException e) {
            ErrorCodeUtil.catchExceptionAndSet(request, response, e);
            LOGGER.error("download emotion error,packageKey:{},shortcut:{},typeName:{}", packageKey, shortcut, typeName, e);
        }
    }

    @RequestMapping("/d/e/{key:.*}")
    @ResponseBody
    public void downloadEmotion(
            @PathVariable(value = "key") String typeName,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String fileName = StorageConfig.SWIFT_FOLDER_EMO_PACKAGE + typeName;
        downloadService.downloadService(fileName, request, response);
    }

//    @RequestMapping("/d/getEmoConfig")
//    @ResponseBody
//    public Object getPackageName() {
//        LOGGER.info("download the emoConfig");
//        EmoPackConf emoPackConf;
//        {
//            try {
//                emoPackConf = genEmoConfig();
//            } catch (IOException e) {
//                LOGGER.error("get emo config error", e);
//                return "";
//            }
//        }
//        return emoPackConf.getData();
//    }

    @RequestMapping("/d/e/config")
    @ResponseBody
    public String downForOldVersionNEW() {
        return JacksonUtil.obj2String(insertEmo.getEmoConfig());
    }


    @RequestMapping(value = "/u/u/z/", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult downloadEmotions(@RequestParam(value = "packageName") String packageName,
                                       @RequestParam(value = "thumb") String thumb,
                                       @RequestParam(value = "desc") String desc,
                                       HttpServletRequest req, HttpServletResponse resp) throws IOException, BadHanyuPinyinOutputFormatCombination {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) req;
        return emoPackageUtil.uploadEmo(multipartHttpServletRequest, resp, packageName, thumb, desc);
    }

    @RequestMapping(value = "/u/u/limit", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getEmoLimit() {
        HashMap<String, Integer> WH = new HashMap<>(2);
        WH.put("w", Integer.valueOf(EmoPackageUtil.EMO_SIZE));
        WH.put("h", Integer.valueOf(EmoPackageUtil.EMO_SIZE));
        return JsonResult.newSuccJsonResult(WH);
    }

    private EmoPackConf genEmoConfig() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("emoConfig.json");
        InputStream read = classPathResource.getInputStream();
        String config = new String(ByteStreams.toByteArray(read));
        ObjectMapper mapper = new ObjectMapper();
        EmoPackConf params = mapper.readValue(config, EmoPackConf.class);
        return params;
    }

}