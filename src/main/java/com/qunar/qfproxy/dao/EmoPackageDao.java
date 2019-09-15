package com.qunar.qfproxy.dao;

import com.qunar.qfproxy.model.EmoPackConf;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmoPackageDao {
    List<EmoPackConf> getAllEmoPackage();

    void insertEmo(EmoPackConf emoPackConf);
}
