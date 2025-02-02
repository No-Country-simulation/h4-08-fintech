import { URL_API } from "../../../../vars";

export const getRecomendedAssets = async () => {
    try {
        const response = await fetch(`${URL_API}/investment/recommended`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: "include",
        });

        const data = await response.json();

        const adaptedData = data.map((item) => {
            const potentialReturns = item.asset.potentialReturns || 0;
            const state = Math.min(potentialReturns / 100, 1);

            return {
                company: item.asset.assetName,
                name: item.asset.tickerSymbol,
                costUSD: item.asset.currentPrice,
                state,
                compatibilityScore: item.score,
                riskLevel: item.asset.riskLevel,
                potentialReturns,
                sector: item.asset.sector,
                currency: item.asset.currency,
            };
        });

        console.log('Datos Adaptados de Inversiones:', adaptedData);
        return adaptedData;

    } catch (error) {
        console.error(error);
    }
}