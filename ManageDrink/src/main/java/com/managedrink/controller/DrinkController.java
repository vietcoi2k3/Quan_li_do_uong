package com.managedrink.controller;


import com.managedrink.dto.DrinkDTO;
import com.managedrink.services.implement.DrinkService;
import com.managedrink.until.constants.CommonConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drinks")
/**
 * Controller quản lý đồ uống
 */
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    /**
     * API tạo mới một đối tượng đồ uống.
     *
     * @param drinkDTO Đối tượng DTO của đồ uống để tạo mới.
     * @return ResponseEntity chứa thông tin về đối tượng đồ uống đã được tạo và HttpStatus.CREATED nếu thành công.
     */
    @Operation(summary = "Create a new drink", description = "API tạo mới một đối tượng đồ uống")
    @PostMapping("/create")
    public ResponseEntity<?> createDrink(
            @Parameter(description = "DTO của đồ uống để tạo mới", required = true)
            @Valid @RequestBody DrinkDTO drinkDTO) {
        return new ResponseEntity<>(drinkService.createDrink(drinkDTO), HttpStatus.CREATED);
    }

    /**
     * API cập nhật thông tin của một đồ uống.
     *
     * @param drinkDTO Đối tượng DTO của đồ uống để cập nhật thông tin.
     * @return ResponseEntity chứa thông tin về đối tượng đồ uống đã được cập nhật và HttpStatus.OK nếu thành công.
     */
    @Operation(summary = "Update an existing drink", description = "API cập nhật thông tin của một đồ uống")
    @PutMapping("/update")
    public ResponseEntity<?> updateDrink(
            @Parameter(description = "DTO của đồ uống để cập nhật thông tin", required = true)
            @RequestBody DrinkDTO drinkDTO) {
        return ResponseEntity.ok(drinkService.updateDrink(drinkDTO));
    }

    /**
     * API lấy danh sách các đồ uống.
     *
     * @param page Trang hiện tại (mặc định là 0 nếu không có giá trị).
     * @param size Số lượng bản ghi trên mỗi trang (mặc định là 10 nếu không có giá trị).
     * @return ResponseEntity chứa danh sách các đồ uống trên trang hiện tại và HttpStatus.OK nếu thành công.
     */
    @Operation(summary = "Get all drinks with pagination", description = "API lấy danh sách các đồ uống với phân trang")
    @GetMapping("/all")
    public ResponseEntity<?> getAllDrinks(
            @Parameter(description = "Trang hiện tại", example = "0", required = false)
            @RequestParam(defaultValue = CommonConstant.DEFAULT_PAGE) int page,
            @Parameter(description = "Số lượng bản ghi trên mỗi trang", example = "10", required = false)
            @RequestParam(defaultValue = CommonConstant.DEFAULT_SIZE) int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<DrinkDTO> drinkDTOS = drinkService.getDrinks(pageable);

        return new ResponseEntity<>(drinkDTOS.getContent(), HttpStatus.OK);
    }

    /**
     * API xóa một đồ uống dựa trên ID.
     *
     * @param id ID của đồ uống cần xóa.
     * @return ResponseEntity chứa thông tin về việc xóa đồ uống và HttpStatus tương ứng.
     */
    @Operation(summary = "Delete a drink by ID", description = "API xóa một đồ uống dựa trên ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDrink(
            @Parameter(description = "ID của đồ uống cần xóa", required = true)
            @PathVariable Long id) {
        return this.drinkService.deleteDrink(id);
    }
}
