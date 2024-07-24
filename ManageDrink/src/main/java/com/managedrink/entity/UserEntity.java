package com.managedrink.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Đại diện cho một người dùng trong hệ thống.
 * Lớp này cũng thực hiện giao diện {@link UserDetails} để tích hợp với Spring Security.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails, PermissionHolder {

    /**
     * ID của người dùng, được sinh tự động và không thể thay đổi sau khi được tạo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    /**
     * Tên người dùng, không thể để trống và phải là duy nhất.
     */
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "username not empty")
    private String username;

    /**
     * Mật khẩu của người dùng, không thể để trống.
     */
    @Column(nullable = false)
    @NotEmpty(message = "password not empty")
    private String password;

    /**
     * Tập hợp các vai trò của người dùng.
     * Được ánh xạ với bảng `user_roles` để xác định các vai trò mà người dùng sở hữu.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    /**
     * Tập hợp các quyền của người dùng.
     * Quyền được lưu dưới dạng enum và ánh xạ vào bảng `permissions` của cơ sở dữ liệu.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<PermissionEnum> permissions = new HashSet<>();

    /**
     * Cung cấp các quyền được gán cho người dùng.
     * Kết hợp quyền từ vai trò và quyền trực tiếp.
     *
     * @return Tập hợp các {@link GrantedAuthority} của người dùng.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (RoleEntity role : this.roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        for (PermissionEnum permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.name()));
        }

        return authorities;
    }

    /**
     * Kiểm tra xem tài khoản có hết hạn hay không.
     * Trong trường hợp này, tài khoản không bao giờ hết hạn.
     *
     * @return true nếu tài khoản không hết hạn, false nếu hết hạn.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Kiểm tra xem tài khoản có bị khóa hay không.
     * Trong trường hợp này, tài khoản không bao giờ bị khóa.
     *
     * @return true nếu tài khoản không bị khóa, false nếu bị khóa.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Kiểm tra xem thông tin xác thực có hết hạn hay không.
     * Trong trường hợp này, thông tin xác thực không bao giờ hết hạn.
     *
     * @return true nếu thông tin xác thực không hết hạn, false nếu hết hạn.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Kiểm tra xem tài khoản có được kích hoạt hay không.
     * Trong trường hợp này, tài khoản luôn được kích hoạt.
     *
     * @return true nếu tài khoản được kích hoạt, false nếu không.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
