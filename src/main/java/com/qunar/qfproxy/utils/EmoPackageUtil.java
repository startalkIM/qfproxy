package com.qunar.qfproxy.utils;

import com.google.common.base.Strings;
import com.qunar.qfproxy.constants.Config;
import com.qunar.qfproxy.constants.StorageConfig;
import com.qunar.qfproxy.model.EmoPackConf;
import com.qunar.qfproxy.model.EmoPackXML;
import com.qunar.qfproxy.model.JsonResult;

import com.qunar.qfproxy.service.InsertEmo;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qunar.qfproxy.constants.Config.getProperty;

@Component
public class EmoPackageUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmoPackageUtil.class);
    public static final String EMO_SIZE = getProperty("emo.size");
    private static final String DOWNLOAD_PACKAGE_FORMAT = "%s/file/v2/emo/d/z/%s"; //http://127.0.0.1:8080/file/v2/emo/d/z/4018
    @Resource
    private InsertEmo insertEmo;

    public JsonResult<?> uploadEmo(MultipartHttpServletRequest req, HttpServletResponse resp, String packageName, String thumb, String desc) throws IOException, BadHanyuPinyinOutputFormatCombination {

        List<MultipartFile> files = new ArrayList<>();
        Iterator<String> a = req.getFileNames();//返回的数量与前端input数量相同, 返回的字符串即为前端input标签的name
        while (a.hasNext()) {
            String name = a.next();
            List<MultipartFile> multipartFiles = req.getFiles(name);//获取单个input标签上传的文件，可能为多个
            files.addAll(multipartFiles);
        }

        // 获取上传的文件列表，Part对象就是Servlet3对文件上传支持中对文件数据的抽象结构
        if (files == null || files.isEmpty()) {
            LOGGER.error("======>上传文件为空");
            return JsonResult.newFailResult("上传文件列表为空");
        }

        String pkgId = getPinYin(packageName);
        EmoPackXML emoPackXML = new EmoPackXML();
        EmoPackXML.FACESETTINGBean facesettingBean = new EmoPackXML.FACESETTINGBean();
        List<EmoPackXML.FACESETTINGBean.DEFAULTFACEBean.FACEBean> faceBeans = new LinkedList<>();
        List<String> fileNameList = new ArrayList<String>();
        EmoPackXML.FACESETTINGBean.DEFAULTFACEBean defaultfaceBean = new EmoPackXML.FACESETTINGBean.DEFAULTFACEBean();
        String packagePosition = StorageConfig.SWIFT_FOLDER_EMO_PACKAGE + pkgId;
        String thumbMd5 = null;
        String downloadURI = null;
        try {
            for (MultipartFile part : files) {
                EmoPackXML.FACESETTINGBean.DEFAULTFACEBean.FACEBean faceBean = new EmoPackXML.FACESETTINGBean.DEFAULTFACEBean.FACEBean();
                if (part == null) {
                    continue;
                }
                String fileName = part.getOriginalFilename();
                String imgSuff = FileUtil.getExtension(fileName);

                if (Strings.isNullOrEmpty(fileName)) {
                    LOGGER.error("======>表情包里面表情未命名");
                    return JsonResult.newFailResult("表情未命名");
                }
                String emoNameWNSuff = fileName.substring(0, fileName.indexOf(imgSuff) - 1);
                InputStream is = part.getInputStream();
                byte[] contentImg = StreamUtil.InputStreamToBytes(is);
                String originFullName = packagePosition + "/" + fileName;

                String md5Value;
                String md5FullName;
                File file = new File(originFullName);
                writeFileIntoTarFile(StreamUtil.bytesToInputStream(contentImg), originFullName);
                String md5 = Md5CaculateUtil.getMD5(file);
                md5FullName = packagePosition + "/" + md5 + "." + imgSuff;
                md5Value = md5 + "." + imgSuff;
                if (thumb.equalsIgnoreCase(fileName)) {
                    thumbMd5 = md5;
                }
                fileRename(file, md5FullName);
                faceBean.setFILE_FIXED(md5Value);
                faceBean.setFILE_ORG(md5Value);
                faceBean.setId(md5);
                faceBean.setShortcut("/" + md5);
                faceBean.setTip(emoNameWNSuff);
                faceBean.setMultiframe("4");
                faceBeans.add(faceBean);
            }
            defaultfaceBean.setCount(String.valueOf(faceBeans.size()));
            defaultfaceBean.setEmotionName(packageName);
            defaultfaceBean.setHeight(String.valueOf(EMO_SIZE));
            defaultfaceBean.setWidth(String.valueOf(EMO_SIZE));
            defaultfaceBean.setVersion("1");
            defaultfaceBean.setShowall("0");
            defaultfaceBean.setLine("5");
            defaultfaceBean.setPackageX(pkgId);
            defaultfaceBean.setFACE(faceBeans);
            facesettingBean.setDEFAULTFACE(defaultfaceBean);
            emoPackXML.setFACESETTING(facesettingBean);
            Document xml = XMLUtil.dom4jToXml(emoPackXML);
            writeXMLTofile(xml, packagePosition + "/" + pkgId + ".xml");
            File packageFile = new File(packagePosition);
            String zipName = packagePosition + "/" + defaultfaceBean.getPackageX() + ".zip";
            compressFilesZip(packageFile.listFiles(), zipName);
            File zipPackFile = new File(zipName);
            String md5Zip = Md5CaculateUtil.getMD5(zipPackFile);
            long lengthZip = zipPackFile.length();
            EmoPackConf emoPackConf = new EmoPackConf();
            emoPackConf.setDesc(desc);
            emoPackConf.setName(packageName);
            emoPackConf.setPkgid(pkgId);
            emoPackConf.setMd5(md5Zip);
            emoPackConf.setFileSize(lengthZip);
            downloadURI = String.format(DOWNLOAD_PACKAGE_FORMAT,Config.PROJECT_HOST_AND_PORT,packageName);
            emoPackConf.setFile(downloadURI);
            String thumbDown = String.format(StringUtils.join(Config.PROJECT_HOST_AND_PORT, "/file/v1/emo/d/e/%s/%s/org"), pkgId,thumbMd5);
            emoPackConf.setThumb(thumbDown);
            insertEmo.InsertEmo(emoPackConf);
        } catch (Exception e) {
            LOGGER.error("upload package error package is {}", packageName, e);

        }
        return JsonResult.newSuccJsonResult(downloadURI);
    }

    private boolean writeFileIntoTarFile(InputStream is, String target) throws IOException {
        if (is == null || Strings.isNullOrEmpty(target)) {
            LOGGER.warn("write file to target file fail due to is or target is null");
            return false;
        }
        File file = new File(target);
        try {
            FileUtils.copyInputStreamToFile(is, file);
        } catch (Exception e) {
            LOGGER.error("write file to target file fail", e);
            throw e;
        } finally {
            is.close();
        }
        return true;
    }


    private boolean fileRename(File file, String newName) throws IOException {
        if (file == null || Strings.isNullOrEmpty(newName)) {
            return false;
        }
        file.renameTo(new File(newName));
        return true;
    }


    private boolean deleteFile(String newName) throws IOException {
        File deletFile = new File(newName);
        if (deletFile != null && deletFile.isFile() && deletFile.exists()) {
            deletFile.delete();
            return true;
        }
        File[] files = deletFile.listFiles();
        boolean flag = true;
        for (int i = 0; i < files.length; i++) {
            deleteFile(files[i].getAbsolutePath());
        }
        deletFile.delete();
        return true;

    }



    private boolean writeXMLTofile(Document document, String file) {
        OutputFormat format = OutputFormat.createPrettyPrint();
        try {
            XMLWriter writer = new XMLWriter(new FileOutputStream(new File(file)), format);
            writer.write(document);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }



    public static void compressFilesZip(File[] files, String zipFilePath) {
        if (files != null && files.length > 0) {
            if (isEndsWithZip(zipFilePath)) {
                ZipArchiveOutputStream zaos = null;
                try {
                    File zipFile = new File(zipFilePath);
                    zaos = new ZipArchiveOutputStream(zipFile);
                    //Use Zip64 extensions for all entries where they are required
                    zaos.setUseZip64(Zip64Mode.AsNeeded);
                    //将每个文件用ZipArchiveEntry封装
                    //再用ZipArchiveOutputStream写到压缩文件中
                    for (File file : files) {
                        if (file != null) {
                            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
                            zaos.putArchiveEntry(zipArchiveEntry);
                            InputStream is = null;
                            try {
                                is = new BufferedInputStream(new FileInputStream(file));
                                byte[] buffer = new byte[1024 * 5];
                                int len = -1;
                                while ((len = is.read(buffer)) != -1) {
                                    //把缓冲区的字节写入到ZipArchiveEntry
                                    zaos.write(buffer, 0, len);
                                }
                                //Writes all necessary data for this entry.
                                zaos.closeArchiveEntry();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            } finally {
                                if (is != null)
                                    is.close();
                            }
                        }
                    }
                    zaos.finish();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        if (zaos != null) {
                            zaos.close();
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static boolean isEndsWithZip(String fileName) {
        boolean flag = false;
        if (fileName != null && !"".equals(fileName.trim())) {
            if (fileName.endsWith(".ZIP") || fileName.endsWith(".zip")) {
                flag = true;
            }
        }
        return flag;
    }



    private String getPinYin(String name) throws BadHanyuPinyinOutputFormatCombination {
        String regEx = "^-?[0-9]+$";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(name);
        if (mat.find()) {
            return name;
        }
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < name.length(); i++) {
            String  regex = "[\u4e00-\u9fa5]";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(String.valueOf(name.charAt(i)));
            if(!m.matches()){
                sb.append(name.charAt(i));
                continue;
            }
            String str[] = PinyinHelper.toHanyuPinyinStringArray(name.charAt(i), hanyuPinyinOutputFormat);
            if (str != null && str.length > 0) {
                sb.append(str[0]);
            }
        }
        return sb.toString();
    }


}



