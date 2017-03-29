package com.odmudbone.cdek.calculator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Used to serialize price request data from java object to json
 */
public class RequestDataSerializer implements JsonSerializer<PriceRequestData> {

    /**
     * Generates secure code, as described in cdek documentation
     * @param authPassword integration password (taken from request data)
     * @param dateExecute date of delivery (taken from request data)
     * @return md5 hash as hex string
     */
    private String generateSecure(String authPassword, String dateExecute){

        String input = dateExecute + "&" + authPassword;
        String result = input;

        MessageDigest md;

        try {

            md = MessageDigest.getInstance("MD5");

            md.update(input.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            if ((result.length() % 2) != 0) {
                result = "0" + result;
            }

        } catch (NoSuchAlgorithmException ignored) {} // MD5 algorithm should exist

        return result;

    }

    /**
     * Formats date of delivery execution to format required by API
     * @param date date of delivery (taken from request data)
     * @return formatted date string
     */
    private String formatDateExecute(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * @inheritDoc
     */
    public JsonElement serialize(PriceRequestData src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject result = new JsonObject();

        result.addProperty("version", src.version);
        result.addProperty("modelId", src.modelId);

        if(src.senderCityId != 0)
            result.addProperty("senderCityId", src.senderCityId);
        else
            result.addProperty("senderCityPostCode", src.senderCityPostCode);

        if(src.receiverCityId != 0)
            result.addProperty("receiverCityId", src.receiverCityId);
        else
            result.addProperty("receiverCityPostCode", src.receiverCityPostCode);


        // If only one tariff set - sets tariffId field,
        // or ads tariffs array with it`s priorities to json
        if(src.tariffList.size() == 1)
            result.addProperty("tariffId", src.tariffList.get(0).getId());
        else
            result.add("tariffList", context.serialize(src.tariffList));

        result.add("goods", context.serialize(src.goods));

        // If date not set in request data, sets current date
        String formattedDateExecute = src.dateExecute != null ?
                                        formatDateExecute(src.dateExecute) :
                                        formatDateExecute(new Date());

        result.addProperty("dateExecute", formattedDateExecute);

        if(src.authLogin != null && src.authPassword != null){
            result.addProperty("secure", generateSecure(src.authPassword, formattedDateExecute));
        }

        return result;

    }

}
