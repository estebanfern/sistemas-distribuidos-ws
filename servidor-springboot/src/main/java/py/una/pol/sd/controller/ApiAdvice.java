package py.una.pol.sd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import py.una.pol.sd.dto.ApiResponse;

@RestControllerAdvice
public class ApiAdvice {

    @ExceptionHandler(Throwable.class)
    public final ResponseEntity<Object> globalHandler(Throwable e) {
        return ResponseEntity.badRequest().body(
                new ApiResponse<>("Error processing request", e.getMessage())
        );
    }

}
