package com.zjz.community.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {

    // 生成验证码的用途
    @Bean
    public Producer kaptchaProducer() {
        Properties properties = new Properties();
        // 配置图片的长宽
        properties.setProperty("kaptcha.image.width", "100");
        properties.setProperty("kaptcha.image.height", "40");
        // 配置字体的大小
        properties.setProperty("kaptcha.textproducer.font.size", "32");
        // 0 0 0配置的是黑色
        properties.setProperty("kaptcha.textproducer.font.color", "0,0,0");
        // 生成验证码的随机字符
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYAZ");
        // 生成几个的随机字符，4个
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 采用哪个干扰类，就是字符串上面 的遮掩
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }

}
