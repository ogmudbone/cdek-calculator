package com.odmudbone.cdek.calculator;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Request data object.
 * Contains request fields, needed to get price
 */
class PriceRequestData {

    /**
     * List of goods to deliver
     */
    List<Goods> goods = new LinkedList<Goods>();
    /**
     * List of tariffs with it`s ids and priorities
     */
    List<Tariff> tariffList = new LinkedList<Tariff>();

    /**
     * CDEK price calculator API version
     */
    String version = "1.0";

    /**
     * Login of CDEK integration account
     * can be skipped
     */
    String authLogin;
    /**
     * Password of CDEK integration account
     * can be skipped
     */
    String authPassword;

    /**
     * Date of delivery execution.
     */
    Date dateExecute;
    /**
     * CDEK API id of sender city.
     * Has more priority, than post code
     */
    int senderCityId;
    /**
     * Post code of sender city.
     * Has less priority, than id
     */
    int senderCityPostCode;
    /**
     * CDEK API id of receiver city.
     * Has more priority, than post code
     */
    int receiverCityId;
    /**
     * Post code of sender city.
     * Has less priority, than id
     */
    int receiverCityPostCode;
    /**
     * Mode of delivery.
     * See CDEK API documentation for details
     */
    int modelId;

}
