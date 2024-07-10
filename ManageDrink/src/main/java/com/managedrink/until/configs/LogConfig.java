package com.managedrink.until.configs;

import com.managedrink.until.constants.CommonConstant;
import com.managedrink.until.constants.MessageSourceConstant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * LogConfig là lớp cấu hình để đảm bảo thư mục logs tồn tại khi ứng dụng khởi động.
 * Lớp này implements CommandLineRunner để thực thi mã khi ứng dụng bắt đầu.
 */
@Component
public class LogConfig implements CommandLineRunner {

    /**
     * Phương thức run sẽ được thực thi khi ứng dụng Spring Boot khởi động.
     * Phương thức này kiểm tra và tạo thư mục logs nếu chưa tồn tại.
     *
     * @param args Các tham số dòng lệnh được truyền vào khi ứng dụng khởi động.
     */
    @Override
    public void run(String... args) {
        // Đảm bảo thư mục logs tồn tại
        File logDir = new File(CommonConstant.DEFAULT_FOLDER);
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
    }
}
