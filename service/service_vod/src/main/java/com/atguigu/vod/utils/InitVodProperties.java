package com.atguigu.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InitVodProperties implements InitializingBean {
    @Value("${aliyun.vod.file.keyid}")
    private String keyId;

    @Value("${aliyun.vod.file.keysecret}")
    private String keySecret;

    public static String KEYID;
    public static String KEYSECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        KEYID = keyId;
        KEYSECRET =keySecret;
    }
}
