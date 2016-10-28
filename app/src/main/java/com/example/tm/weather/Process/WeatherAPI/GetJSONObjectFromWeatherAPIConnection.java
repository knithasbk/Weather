package com.example.tm.weather.Process.WeatherAPI;

import android.util.Log;

import com.example.tm.weather.Model.WeatherFinalStaticData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by TM on 28/10/2016.
 */

/**
 * Created by TM on 05/10/2016.
 * Start a connect with weather API - Then get JSON String from Weather API
 * <p>
 * Input : int cityId
 * Output: JSONObject jsonObject
 */

public class GetJSONObjectFromWeatherAPIConnection {
    private WeatherFinalStaticData weatherFinalStaticData = new WeatherFinalStaticData();
    JSONObject jsonObject;
    private int cityId;

    public GetJSONObjectFromWeatherAPIConnection() {
    }

    public GetJSONObjectFromWeatherAPIConnection(int cityId) {
        this.cityId = cityId;
        Log.i("TEST_TRACE", "city ID at GetJSONObjectFromWeatherAPIConnection constructor:  " + String.valueOf(cityId));
    }

    //{"city":{"id":713514,"name":"Alupka","coord":{"lon":34.049999,"lat":44.416668},"country":"UA","population":0,"sys":{"population":0}},"cod":"200","message":0.011,"cnt":16,"list":[{"dt":1477051200,"main":{"temp":286.14,"temp_min":286.139,"temp_max":286.14,"pressure":1014.91,"sea_level":1036.05,"grnd_level":1014.91,"humidity":100,"temp_kf":0},"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"clouds":{"all":56},"wind":{"speed":6.56,"deg":64.0091},"rain":{"3h":0.02},"sys":{"pod":"d"},"dt_txt":"2016-10-21 12:00:00"},{"dt":1477062000,"main":{"temp":286.31,"temp_min":286.31,"temp_max":286.311,"pressure":1014.55,"sea_level":1035.72,"grnd_level":1014.55,"humidity":100,"temp_kf":0},"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10n"}],"clouds":{"all":92},"wind":{"speed":6.46,"deg":73.5005},"rain":{"3h":0.04},"sys":{"pod":"n"},"dt_txt":"2016-10-21 15:00:00"},{"dt":1477072800,"main":{"temp":286.81,"temp_min":286.81,"temp_max":286.81,"pressure":1014.3,"sea_level":1035.47,"grnd_level":1014.3,"humidity":100,"temp_kf":0},"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10n"}],"clouds":{"all":92},"wind":{"speed":6.31,"deg":82.0007},"rain":{"3h":0.045},"sys":{"pod":"n"},"dt_txt":"2016-10-21 18:00:00"},{"dt":1477083600,"main":{"temp":287.14,"temp_min":287.14,"temp_max":287.141,"pressure":1013.92,"sea_level":1034.89,"grnd_level":1013.92,"humidity":100,"temp_kf":0},"weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03n"}],"clouds":{"all":36},"wind":{"speed":5.62,"deg":90.0004},"rain":{},"sys":{"pod":"n"},"dt_txt":"2016-10-21 21:00:00"},{"dt":1477094400,"main":{"temp":287.078,"temp_min":287.078,"temp_max":287.078,"pressure":1013.51,"sea_level":1034.54,"grnd_level":1013.51,"humidity":100,"temp_kf":0},"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02n"}],"clouds":{"all":24},"wind":{"speed":5.51,"deg":83.001},"rain":{},"sys":{"pod":"n"},"dt_txt":"2016-10-22 00:00:00"},{"dt":1477105200,"main":{"temp":286.603,"temp_min":286.603,"temp_max":286.603,"pressure":1013,"sea_level":1034.12,"grnd_level":1013,"humidity":100,"temp_kf":0},"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02n"}],"clouds":{"all":24},"wind":{"speed":6.41,"deg":74.0002},"rain":{},"sys":{"pod":"n"},"dt_txt":"2016-10-22 03:00:00"},{"dt":1477116000,"main":{"temp":285.947,"temp_min":285.947,"temp_max":285.947,"pressure":1013.13,"sea_level":1034.2,"grnd_level":1013.13,"humidity":100,"temp_kf":0},"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02d"}],"clouds":{"all":24},"wind":{"speed":6.47,"deg":69.0007},"rain":{},"sys":{"pod":"d"},"dt_txt":"2016-10-22 06:00:00"},{"dt":1477126800,"main":{"temp":286.355,"temp_min":286.355,"temp_max":286.355,"pressure":1013.21,"sea_level":1034.17,"grnd_level":1013.21,"humidity":100,"temp_kf":0},"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02d"}],"clouds":{"all":12},"wind":{"speed":5.46,"deg":70.002},"rain":{},"sys":{"pod":"d"},"dt_txt":"2016-10-22 09:00:00"},{"dt":1477137600,"main":{"temp":286.808,"temp_min":286.808,"temp_max":286.808,"pressure":1012.6,"sea_level":1033.61,"grnd_level":1012.6,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":4.89,"deg":66.0076},"rain":{},"sys":{"pod":"d"},"dt_txt":"2016-10-22 12:00:00"},{"dt":1477148400,"main":{"temp":286.44,"temp_min":286.44,"temp_max":286.44,"pressure":1012.86,"sea_level":1033.85,"grnd_level":1012.86,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"02n"}],"clouds":{"all":8},"wind":{"speed":4.67,"deg":68.0078},"rain":{},"sys":{"pod":"n"},"dt_txt":"2016-10-22 15:00:00"},{"dt":1477159200,"main":{"temp":286.206,"temp_min":286.206,"temp_max":286.206,"pressure":1013.17,"sea_level":1034.21,"grnd_level":1013.17,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":4.16,"deg":68.5012},"rain":{},"sys":{"pod":"n"},"dt_txt":"2016-10-22 18:00:00"},{"dt":1477170000,"main":{"temp":285.933,"temp_min":285.933,"temp_max":285.933,"pressure":1013.16,"sea_level":1034.17,"grnd_level":1013.16,"humidity":100,"temp_kf":0},"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02n"}],"clouds":{"all":12},"wind":{"speed":2.96,"deg":69.0007},"rain":{},"sys":{"pod":"n"},"dt_txt":"2016-10-22 21:00:00"},{"dt":1477180800,"main":{"temp":286.314,"temp_min":286.314,"temp_max":286.314,"pressure":1013.33,"sea_level":1034.43,"grnd_level":1013.33,"humidity":100,"temp_kf":0},"weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03n"}],"clouds":{"all":36},"wind":{"speed":3.27,"deg":61.5005},"rain":{},"sys":{"pod":"n"},"dt_txt":"2016-10-23 00:00:00"},{"dt":1477191600,"main":{"temp":286.6,"temp_min":286.6,"temp_max":286.6,"pressure":1013.54,"sea_level":1034.63,"grnd_level":1013.54,"humidity":100,"temp_kf":0},"weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03n"}],"clouds":{"all":36},"wind":{"speed":4.53,"deg":63.5004},"rain":{},"sys":{"pod":"n"},"dt_txt":"2016-10-23 03:00:00"},{"dt":1477202400,"main":{"temp":286.432,"temp_min":286.432,"temp_max":286.432,"pressure":1014.42,"sea_level":1035.63,"grnd_level":1014.42,"humidity":100,"temp_kf":0},"weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03d"}],"clouds":{"all":48},"wind":{"speed":4.76,"deg":61.0009},"rain":{},"sys":{"pod":"d"},"dt_txt":"2016-10-23 06:00:00"},{"dt":1477213200,"main":{"temp":286.336,"temp_min":286.336,"temp_max":286.336,"pressure":1014.99,"sea_level":1036.19,"grnd_level":1014.99,"humidity":100,"temp_kf":0},"weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03d"}],"clouds":{"all":44},"wind":{"speed":4.03,"deg":54.5004},"rain":{},"sys":{"pod":"d"},"dt_txt":"2016-10-23 09:00:00"}]}
    public JSONObject execute() {
        Log.i("TEST_TRACE", "Start execute()  GetJSONObjectFromWeatherAPIConnection class ");

        String jStr = getWeatherJSONStringByCityId();
        jsonObject = ParseStringToJSONObject(jStr);
        Log.i("TEST_TRACE", "End execute()  GetJSONObjectFromWeatherAPIConnection class ");

        return jsonObject;
    }

    public String getWeatherJSONStringByCityId() {
        String jsonString;

        StringBuilder sb = new StringBuilder(weatherFinalStaticData.BASIC_URL);
        StringBuilder urlBuilt = sb.append(String.valueOf(cityId)).append(WeatherFinalStaticData.DATE_QUERY)
                .append(weatherFinalStaticData.DATE_NUMBER).append(WeatherFinalStaticData.APPID).append(WeatherFinalStaticData.USER_ID);
        String urlString = urlBuilt.toString();
        jsonString = executeDownloadUrl(urlString);
        Log.i("TEST_TRACE", "urlString at getWeatherJSONStringByCityId function GetJSONObjectFromWeatherAPIConnection :" + urlString);
        return jsonString;

    }

    public String executeDownloadUrl(String urlString) {
        Log.i("TEST_TRACE", "Start of executeDownloadUrl() GetJSON class ");

        String jsonString = null;
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            url = new URL(urlString);

            /*Create a connection to Weather API */
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            /* Get InputStream from connection */
            InputStream in = urlConnection.getInputStream();

            StringBuffer buffer = new StringBuffer();
            if (in == null) {
                // Nothing to do.
                return null;
            }

            String line;
            reader = new BufferedReader(new InputStreamReader(in));

            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.

                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            jsonString = buffer.toString();

        } catch (MalformedURLException e) {

            System.out.print(e);

        } catch (IOException e) {
            System.out.print(e);

        } finally {
            urlConnection.disconnect();
        }
        Log.i("TEST_TRACE", "End of executeDownloadUrl() GetJSON class " + jsonString);
        return jsonString;
    }

    /*Input String and parse to JSONObject */
    public JSONObject ParseStringToJSONObject(String jsonString) {
        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            System.out.print(e);
        }
        return jsonObject;
    }


}





