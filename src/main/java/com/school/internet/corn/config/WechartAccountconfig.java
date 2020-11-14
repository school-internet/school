package com.school.internet.corn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class WechartAccountconfig {

    private String appId;

    /**
     * 设置微信公众号的app secret
     */
    private String secret;

    /**
     * 设置微信公众号的token
     */
    private String token;

    /**
     * 设置微信公众号的EncodingAESKey
     */
    private String aesKey;
}
