package com.managedrink.services.implement;

import com.managedrink.entity.PermissionEnum;
import com.managedrink.jwt.JwtUtil;
import com.managedrink.services.IPermissionService;
import com.managedrink.until.constants.CommonConstant;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Triển khai dịch vụ kiểm tra quyền hạn của người dùng.
 * Lớp này thực hiện giao diện {@link IPermissionService}
 * để kiểm tra xem người dùng có quyền hạn cụ thể hay không.
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Kiểm tra quyền hạn của người dùng dựa trên yêu cầu quyền hạn cụ thể.
     * Phương thức này lấy token từ yêu cầu HTTP, trích xuất quyền hạn từ token,
     * và kiểm tra xem quyền hạn yêu cầu có nằm trong danh sách quyền hạn của người dùng hay không.
     *
     * @param request yêu cầu HTTP chứa token xác thực
     * @param requiredPermission quyền hạn yêu cầu cần kiểm tra
     * @return true nếu người dùng có quyền hạn yêu cầu, false nếu không
     */
    @Override
    public boolean hasPermission(HttpServletRequest request, PermissionEnum requiredPermission) {
        String token = request.getHeader(CommonConstant.HEADER_AUTHORIZATION);
        if (token != null && token.startsWith(CommonConstant.BEARER)) {
            token = token.substring(CommonConstant.SEVEN);
            List<String> permissions = jwtUtil.getPermissionsFromToken(token);
            return permissions.contains(requiredPermission.name());
        }
        return false;
    }
}
