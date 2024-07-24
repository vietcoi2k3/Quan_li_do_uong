package com.managedrink.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation để yêu cầu quyền hạn cho một phương thức.
 * Annotation này được sử dụng để chỉ định quyền hạn cụ thể
 * mà một phương thức yêu cầu từ người dùng.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequirePermission {

    /**
     * Quyền hạn yêu cầu cho phương thức.
     *
     * @return quyền hạn yêu cầu
     */
    PermissionEnum value();
}
