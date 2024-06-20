package rw.ac.app.employeeservice.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rw.ac.app.employeeservice.responses.ApiResponse;

public class ResponseHandler {
    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return ResponseEntity.ok(new ApiResponse<>(data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(String errorMessage, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(errorMessage);
        return ResponseEntity.status(status).body(response);
    }
}
