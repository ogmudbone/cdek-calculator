package com.odmudbone.cdek.calculator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Success response object of price request
 */
public class PriceResponse {

    /**
     * Price for delivery.
     * Always in RUB.
     * See also:
     * {@link PriceResponse#currency} / {@link #currency}
     */
    private double price;
    /**
     * Minimal delivery time in days
     */
    private int deliveryPeriodMin;
    /**
     * Maximal delivery time in days
     */
    private int deliveryPeriodMax;
    /**
     * Minimal delivery date in format yyyy-MM-dd
     * Returns from getter as java.util.Date object
     */
    private String deliveryDateMin;
    /**
     * Maximal delivery date in format yyyy-MM-dd
     * Returns from getter as java.util.Date object
     */
    private String deliveryDateMax;
    /**
     * Id of tariff that CDEK choose from tariff list in request
     * See calculator api documentation, to know how it was chosen
     */
    private int tariffId;
    /**
     * Restriction of payment in cash, appears only if it exists.
     * equals 0.0 if there is no restriction
     */
    private float cashOnDelivery;
    /**
     * Currency of CDEK account, from witch request was made.
     * Default currency (if no authorization used) is RUB
     */
    private float priceByCurrency;
    /**
     * Price of delivery in CDEK account currency
     * See also:
     * {@link PriceResponse#priceByCurrency} / {@link #priceByCurrency}
     */
    private String currency;

    /**
     * {@link PriceResponse#price}
     */
    public double getPrice() {
        return price;
    }

    /**
     * {@link PriceResponse#deliveryPeriodMin}
     */
    public int getDeliveryPeriodMin() {
        return deliveryPeriodMin;
    }

    /**
     * {@link PriceResponse#deliveryPeriodMax}
     */
    public int getDeliveryPeriodMax() {
        return deliveryPeriodMax;
    }

    /**
     * {@link PriceResponse#deliveryDateMin}
     */
    public Date getDeliveryDateMin() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(deliveryDateMin);
        } catch (ParseException ignored) { // CDEK should return correct date (Sorry if it`s not true)
            return null;
        }
    }

    /**
     * {@link PriceResponse#deliveryDateMax}
     */
    public Date getDeliveryDateMax() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(deliveryDateMax);
        } catch (ParseException ignored) { // CDEK should return correct date (Sorry if it`s not true)
            return null;
        }
    }

    /**
     * {@link PriceResponse#tariffId}
     */
    public int getTariffId() {
        return tariffId;
    }

    /**
     * {@link PriceResponse#cashOnDelivery}
     */
    public float getCashOnDelivery() {
        return cashOnDelivery;
    }

    /**
     * {@link PriceResponse#priceByCurrency}
     */
    public float getPriceByCurrency() {
        return priceByCurrency;
    }

    /**
     * {@link PriceResponse#currency}
     */
    public String getCurrency() {
        return currency;
    }

}
