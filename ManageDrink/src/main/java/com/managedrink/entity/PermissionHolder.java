package com.managedrink.entity;

import java.util.Set;

/**
 * Giao diện cho các thực thể có quyền hạn.
 * Các lớp triển khai giao diện này sẽ cung cấp danh sách quyền hạn
 * mà thực thể đó sở hữu.
 */
public interface PermissionHolder {

    /**
     * Lấy tập hợp các quyền hạn của thực thể.
     *
     * @return tập hợp các quyền hạn (dưới dạng {@link Set} của {@link PermissionEnum})
     */
    Set<PermissionEnum> getPermissions();
}
