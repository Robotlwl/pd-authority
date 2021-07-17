package com.pd.auth.client.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 客户端认证配置
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = AuthClientProperties.PREFIX)
public class AuthClientProperties {
    public static final String PREFIX = "authentication";
    private TokenInfo user;

    @Data
    public static class TokenInfo {
        /**
         * 请求头名称
         */
        private String headerName;
        /**
         * 解密 网关使用
         */
        private String pubKey;
    }
}
