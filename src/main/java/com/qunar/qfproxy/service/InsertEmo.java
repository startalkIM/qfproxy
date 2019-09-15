package com.qunar.qfproxy.service;

import com.qunar.qfproxy.dao.EmoPackageDao;
import com.qunar.qfproxy.model.EmoPackConf;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InsertEmo {

    @Resource
    private EmoPackageDao emoPackageDao;


    public void InsertEmo(EmoPackConf emoPackConf) {
        emoPackageDao.insertEmo(emoPackConf);
    }

    public List<EmoPackConf> getEmoConfig() {
        return emoPackageDao.getAllEmoPackage();
    }


}
