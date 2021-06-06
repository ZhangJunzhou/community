package com.zjz.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public class CommunityUtil {

    // 生成随机的字符串，电子邮箱注册使用
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // MD5加密，对密码进行加密，保证密码的安全
    // hello -> abc123def456  只能加密，不能够解密，但是设置过于简单的话，很容易被破解
    // 网站一般在密码后面加上随机数，hello + 随机字符 ， 然后在进行加密，为了保护密码的安全性，比如 3e4a8  -> hewifewjfjrwpj
    public static String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

}
