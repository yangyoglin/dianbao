package com.dianbao.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
/**
 * 新增注解,在Controller的方法上使用此注解,该方法在映射时会检查用户是否登录
 * @author YangYongLin
 * @date 2020年7月2日上午10:20:45
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorization {
 
}