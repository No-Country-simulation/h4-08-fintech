package com.web.backend.application.service.Investment;

import com.web.backend.application.DTO.Recommendation.AssetRecommendation;
import com.web.backend.domain.model.Financials.FinancialProfile;

import java.util.Collection;
import java.util.List;

public interface ISInvestmentRecommendationService {
    /**
     * Obtiene recomendaciones de inversión basadas en el perfil financiero del cliente y los activos disponibles en la base de datos.
     *
     * @param customerId ID del cliente para obtener su perfil financiero.
     * @return Una colección de recomendaciones de activos ordenados por puntaje.
     */
    Collection<AssetRecommendation> getRecommendations(Long customerId);

    /**
     * Obtiene recomendaciones de inversión basadas en un término de búsqueda (keyword) en una API externa,
     * considerando el perfil financiero del cliente.
     *
     * @param keyword Palabra clave para buscar activos en la API externa.
     * @param profile Perfil financiero del cliente que influye en las recomendaciones.
     * @return Una lista de recomendaciones de activos externos ordenados por puntaje.
     */
    List<AssetRecommendation> getExternalRecommendations(String keyword, FinancialProfile profile);

    /**
     * Llena la base de datos con información de activos obtenida de una API externa utilizando una palabra clave.
     *
     * @param keyword Palabra clave para buscar activos en la API externa.
     */
    void populateAssetsByKeywordFromApi(String keyword, int limit) ;
}
