package com.web.backend.application.exception;

import com.web.backend.application.exception.investment.InvestmentNotFoundException;
import com.web.backend.application.exception.investment.InvestmentTypeNotFoundException;
import com.web.backend.application.exception.news.NewsCategoryNotFoundException;
import com.web.backend.application.exception.news.NewsNotFoundException;
import com.web.backend.application.exception.news.NewsSourceNotFoundException;
import com.web.backend.application.exception.objective.ObjectiveNotFoundException;
import com.web.backend.application.exception.objective.ObjectiveStatusNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvestmentNotFoundException.class)
    public ResponseEntity<String> handleInvestmentNotFound(InvestmentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvestmentTypeNotFoundException.class)
    public ResponseEntity<String> handleInvestmentTypeNotFound(InvestmentTypeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<String> handleNewsNotFound(NewsNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NewsCategoryNotFoundException.class)
    public ResponseEntity<String> handleNewsCategoryNotFound(NewsCategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NewsSourceNotFoundException.class)
    public ResponseEntity<String> handleNewsSourceNotFound(NewsSourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ObjectiveNotFoundException.class)
    public ResponseEntity<String> handleObjectiveNotFound(ObjectiveNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ObjectiveStatusNotFoundException.class)
    public ResponseEntity<String> handleObjectiveStatusNotFound(ObjectiveStatusNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
