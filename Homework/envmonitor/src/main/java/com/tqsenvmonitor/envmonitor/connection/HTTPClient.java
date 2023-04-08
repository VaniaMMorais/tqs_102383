package com.tqsenvmonitor.envmonitor.connection;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class HTTPClient {

    private static final Logger log = LoggerFactory.getLogger(HTTPClient.class);
    private final String API_KEY = "6be3ea4da4135b4533844adbb662252b";

    public String getLocation(String cityName, int limit) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            String url = "http://api.openweathermap.org/geo/1.0/direct" + "?q=" + cityName + "&limit=" + limit + "&appid=" + API_KEY;
            log.info("Requesting data from {}", url);

            HttpGet request = new HttpGet(url);
            client = HttpClients.createDefault();
            response = client.execute(request);

            HttpEntity entity = response.getEntity();

            return EntityUtils.toString(entity);

        } catch (Exception e) {
            log.error("Error while requesting data from OpenWeatherMap API", e);
            throw new RuntimeException(e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    log.error("Error while closing response", e);
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {
                    log.error("Error while closing client", e);
                }
            }
        }
    }

    public String getAirQuality(Double lat, Double lon, String cityName) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            String url = "http://api.openweathermap.org/data/2.5/air_pollution" + "?lat=" + lat + "&lon=" + lon + "&appid=" + API_KEY;
            log.info("Requesting data from {}", url);

            HttpGet request = new HttpGet(url);
            client = HttpClients.createDefault();
            response = client.execute(request);

            HttpEntity entity = response.getEntity();

            return EntityUtils.toString(entity);

        } catch (Exception e) {
            log.error("Error while requesting data from OpenWeatherMap API", e);
            throw new RuntimeException(e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    log.error("Error while closing response", e);
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {
                    log.error("Error while closing client", e);
                }
            }
        }
    }

    public String getFutureAirQuality(Double lat, Double lon, String cityName){
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            String url = "http://api.openweathermap.org/data/2.5/air_pollution/forecast" + "?lat=" + lat + "&lon=" + lon + "&appid=" + API_KEY;
            log.info("Requesting data from {}", url);

            HttpGet request = new HttpGet(url);
            client = HttpClients.createDefault();
            response = client.execute(request);

            HttpEntity entity = response.getEntity();

            return EntityUtils.toString(entity);

        } catch (Exception e) {
            log.error("Error while requesting data from OpenWeatherMap API", e);
            throw new RuntimeException(e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    log.error("Error while closing response", e);
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {
                    log.error("Error while closing client", e);
                }
            }
        }
    }
    
}
