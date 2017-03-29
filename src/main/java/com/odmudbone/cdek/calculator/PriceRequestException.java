package com.odmudbone.cdek.calculator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Trows in {@link PriceRequest#send()} method
 * if request data is invalid or there is no proper tariff for chosen sender and receiver cities
 */
public class PriceRequestException extends Exception {

    /**
     * CDEK API error code
     */
    private int apiErrorCode;
    /**
     * CDEK API error message
     */
    private String apiErrorMessage;

    PriceRequestException(JsonObject errorResponse){
        super();
        JsonObject errorDescription = ((JsonObject) ((JsonArray) errorResponse.get("error")).get(0));
        this.apiErrorCode = errorDescription.get("code").getAsInt();
        this.apiErrorMessage = errorDescription.get("text").getAsString();
    }

    /**
     * @return CDEK API error code
     */
    public int getApiErrorCode() {
        return apiErrorCode;
    }

    /**
     * @return CDEK API error message
     */
    public String getApiErrorMessage() {
        return apiErrorMessage;
    }

    /**
     * Overwritten for displaying CDEK error message
     * @return exception message
     */
    public String getMessage(){
        return "Failed to get price data. Error code : " + apiErrorCode + ". Message : " + apiErrorMessage;
    }

}
