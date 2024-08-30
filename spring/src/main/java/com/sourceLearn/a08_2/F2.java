package com.sourceLearn.a08_2;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @Description: 设置@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
 * @Date: 2024/8/19 15:50
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class F2 {
}
