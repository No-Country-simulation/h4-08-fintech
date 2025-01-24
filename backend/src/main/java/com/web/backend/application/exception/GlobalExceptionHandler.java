package com.web.backend.application.exception;

import com.web.backend.application.exception.asset.AssetNotFoundException;
import com.web.backend.application.exception.asset.InvalidTickerException;
import com.web.backend.application.exception.investment.InvestmentNotFoundException;
import com.web.backend.application.exception.asset.AssetTypeNotFoundException;
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

    @ExceptionHandler(AssetNotFoundException.class)
    public ResponseEntity<String> handleAssetNotFound(AssetNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AssetTypeNotFoundException.class)
    public ResponseEntity<String> handleAssetTypeNotFound(AssetTypeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidTickerException.class)
    public ResponseEntity<String> handleInvalidTicker(InvalidTickerException ex) {
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
