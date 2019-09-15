package com.qunar.qfproxy.utils;

import com.qunar.qfproxy.constants.PackageXMLConstant;
import com.qunar.qfproxy.model.EmoPackXML;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import java.util.List;

public class XMLUtil {
    public static Document dom4jToXml(EmoPackXML emoPackXML) {
        EmoPackXML.FACESETTINGBean.DEFAULTFACEBean facesettingBean = emoPackXML.getFACESETTING().getDEFAULTFACE();
        Document document = DocumentHelper.createDocument();
        org.dom4j.Element ROOT = document.addElement(PackageXMLConstant.FACESETTING);
        org.dom4j.Element bookStore = ROOT.addElement(PackageXMLConstant.DEFAULTFACE);
        bookStore.addAttribute(PackageXMLConstant.EMOTIONNAME, facesettingBean.getEmotionName());
        bookStore.addAttribute(PackageXMLConstant.VERSION, facesettingBean.getVersion());
        bookStore.addAttribute(PackageXMLConstant.COUNT, facesettingBean.getCount());
        bookStore.addAttribute(PackageXMLConstant.SHOWALL, facesettingBean.getShowall());
        bookStore.addAttribute(PackageXMLConstant.LINE, facesettingBean.getLine());
        bookStore.addAttribute(PackageXMLConstant.Width, facesettingBean.getWidth());
        bookStore.addAttribute(PackageXMLConstant.Height, facesettingBean.getHeight());
        bookStore.addAttribute("package", facesettingBean.getPackageX());
        List<EmoPackXML.FACESETTINGBean.DEFAULTFACEBean.FACEBean> beans = facesettingBean.getFACE();

        if (beans == null || beans.size() < 0) {
            return null;
        }
        for (EmoPackXML.FACESETTINGBean.DEFAULTFACEBean.FACEBean bean : beans) {
            org.dom4j.Element face = bookStore.addElement(PackageXMLConstant.FACE);
            face.addAttribute(PackageXMLConstant.id, bean.getId());
            face.addAttribute(PackageXMLConstant.shortcut, bean.getShortcut());
            face.addAttribute(PackageXMLConstant.tip, bean.getTip());
            face.addAttribute(PackageXMLConstant.multiframe, bean.getMultiframe());
            org.dom4j.Element faceOrg = face.addElement(PackageXMLConstant.FILE_ORG);
            faceOrg.setText(bean.getFILE_ORG());
            org.dom4j.Element fileFix = face.addElement(PackageXMLConstant.FILE_FIXED);
            fileFix.setText(bean.getFILE_FIXED());
        }
        //return true;
//        OutputFormat format = OutputFormat.createPrettyPrint();
//        try {
//            XMLWriter writer = new XMLWriter(new FileOutputStream(new File("/Users/binzhang/Downloads/package/tets.xml")), format);
//            writer.write(document);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return document;
    }

}
