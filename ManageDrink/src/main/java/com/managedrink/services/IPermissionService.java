package com.managedrink.services;

import com.managedrink.entity.PermissionEnum;
import jakarta.servlet.http.HttpServletRequest;

public interface IPermissionService {

    boolean hasPermission(HttpServletRequest request, PermissionEnum requiredPermission);
}
