package com.web.backend.config;


import com.web.backend.application.service.Investment.InvestmentRecommendationService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final InvestmentRecommendationService recommendationService;
    @Autowired
    public DataInitializer(InvestmentRecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

//    @PostConstruct
//    public void initData() {
//        try {
//            recommendationService.populateAssetsFromApi("tech"); // Usando un keyword como "tech"
//
//            System.out.println("DB INICIALIZADA CON DATOS DE API");
//        } catch (Exception e) {
//            System.err.println("Error poblando datos iniciales: " + e.getMessage());
//        }
//    }
}