package com.managedrink.exception;

import com.managedrink.until.constants.LogMessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler xử lý các ngoại lệ được ném bởi các controller và cung cấp việc xử lý lỗi tập trung.
 * Nó sử dụng @RestControllerAdvice của Spring để chặn và xử lý các ngoại lệ một cách toàn cục trên tất cả các controller.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * Xử lý các ngoại lệ validation được ném bởi các phương thức được chú thích bằng @Valid hoặc @Validated.
     * Trích xuất lỗi trường và trả về chúng dưới dạng ResponseEntity với HttpStatus.BAD_REQUEST.
     *
     * @param ex Đối tượng MethodArgumentNotValidException được ném bởi Spring MVC.
     * @return ResponseEntity chứa một bản đồ các lỗi trường.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Xử lý các ngoại lệ runtime.
     * Ghi log ngoại lệ và trả về một ResponseEntity với HttpStatus.INTERNAL_SERVER_ERROR.
     *
     * @param ex Đối tượng RuntimeException được ném bởi ứng dụng.
     * @return ResponseEntity chứa thông báo ngoại lệ.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Ghi log ngoại lệ
        log.error(LogMessageConstants.RUNTIME_EXCEPTION, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    /**
     * Xử lý các ngoại lệ NotNullException.
     * Ghi log ngoại lệ và trả về một ResponseEntity với HttpStatus.BAD_REQUEST.
     *
     * @param ex Đối tượng NotNullException được ném bởi ứng dụng.
     * @return ResponseEntity chứa thông báo ngoại lệ.
     */
    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<String> handleNotNullException(NotNullException ex) {
        // Ghi log ngoại lệ
        log.error(LogMessageConstants.NOT_NULL_EXCEPTION, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Xử lý các ngoại lệ NotFoundException.
     * Ghi log ngoại lệ và trả về một ResponseEntity với HttpStatus.BAD_REQUEST.
     *
     * @param ex Đối tượng NotFoundException được ném bởi ứng dụng.
     * @return ResponseEntity chứa thông báo ngoại lệ.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        // Ghi log ngoại lệ
        log.error(LogMessageConstants.NOT_FOUND_EXCEPTION, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
