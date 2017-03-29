package com.odmudbone.cdek.calculator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * Builds request to CDEK
 * price calculator
 */
public class PriceRequestBuilder {

    /**
     * Data of request
     */
    private PriceRequestData data = new PriceRequestData();

    /**
     * Sets api version.
     * Has default value (1.0)
     * @param version version of api
     * @return this
     */
    public PriceRequestBuilder setVersion(String version){
        data.version = version;
        return this;
    }

    /**
     * Sets auth login of CDEK account.
     * Not required
     * @param authLogin CDEK login
     * @return this
     */
    public PriceRequestBuilder setAuthLogin(String authLogin){
        data.authLogin = authLogin;
        return this;
    }

    /**
     * Sets auth password of CDEK account.
     * Not required
     * @param authPassword CDEK login
     * @return this
     */
    public PriceRequestBuilder setAuthPassword(String authPassword){
        data.authPassword = authPassword;
        return this;
    }

    /**
     * Sets delivery mode.
     * Required
     * @param modelId delivery mode
     * @return this
     */
    public PriceRequestBuilder setModelId(int modelId){
        data.modelId = modelId;
        return this;
    }

    /**
     * Sets sender city CDEK API id.
     * Has more priority, than post code.
     * Required (if post code not set).
     * @see <a href="http://www.edostavka.ru/website/edostavka/upload/custom/files/CDEK_city.zip">
     *          Lists of cities wiht ID`s
     *      </a>
     * @param senderCityId CDEK API id of city
     * @return this
     */
    public PriceRequestBuilder setSenderCityId(int senderCityId){
        data.senderCityId = senderCityId;
        return this;
    }

    public PriceRequestBuilder setReceiverCityId(int receiverCityId){
        data.receiverCityId = receiverCityId;
        return this;
    }

    /**
     * Sets sender city post code.
     * Has less priority, than city id
     * Required (if cityId not set)
     * @param senderCityPostCode
     * @return
     */
    public PriceRequestBuilder setSenderCityPostCode(int senderCityPostCode){
        data.senderCityPostCode = senderCityPostCode;
        return this;
    }

    public PriceRequestBuilder setReceiverCityPostCode(int receiverCityPostCode){
        data.receiverCityPostCode = receiverCityPostCode;
        return this;
    }

    public PriceRequestBuilder addTariff(int tariffId, int priority){
        data.tariffList.add(new Tariff(tariffId, priority));
        return this;
    }

    public PriceRequestBuilder addTariff(int tariffId){
        return addTariff(tariffId, 0);
    }

    public PriceRequestBuilder addGoods(float weight, int length, int width, int height){
        data.goods.add(new SizeGoods(weight, length, width, height));
        return this;
    }

    public PriceRequestBuilder addGoods(float weight, float volume){
        data.goods.add(new VolumeGoods(weight, volume));
        return this;
    }

    public PriceRequestBuilder setDateExecute(Date dateExecute){
        data.dateExecute = dateExecute;
        return this;
    }

    public PriceRequest build(){

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(PriceRequestData.class, new RequestDataSerializer())
                .create();

        return new PriceRequest(gson.toJson(data));

    }

}
