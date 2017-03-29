package com.odmudbone.cdek.calculator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PriceRequest{

    private final static String requestUrl = "http://api.edostavka.ru/calculator/calculate_price_by_json.php";

    private String content;

    PriceRequest(String content){
        this.content = content;
    }

    public PriceResponse send() throws PriceRequestException, IOException{

        HttpURLConnection connection = null;

        try {

            connection = (HttpURLConnection) new URL(requestUrl).openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length",
                    Integer.toString(content.getBytes().length));
            connection.setDoOutput(true);
            connection.setDoInput(true);

            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());

            wr.writeBytes(content);
            wr.close();

            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            JsonObject responseJsonObject = (JsonObject) new JsonParser().parse(response.toString());
            if(responseJsonObject.has("result"))
                return new Gson().fromJson(responseJsonObject.get("result"), PriceResponse.class);
            else
                throw new PriceRequestException(responseJsonObject);


        } catch (MalformedURLException e) {
            return null;
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }


}
