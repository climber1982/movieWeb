package com.lovo.web.util;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * 请求拦截器
 */
public class HeaderInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("token","123456");
    }
}
