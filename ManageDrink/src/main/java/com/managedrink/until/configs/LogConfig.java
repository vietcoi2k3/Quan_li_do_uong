package com.managedrink.until.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class LogConfig implements CommandLineRunner {

    @Override
    public void run(String... args) {
        // Đảm bảo thư mục logs tồn tại
        File logDir = new File("logs");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
    }
}
