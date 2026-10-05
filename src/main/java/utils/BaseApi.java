package utils;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public interface BaseApi {
    String BASE_URL = "https://demoqa.com";
    String REG_URL = "/Account/v1/User";
    String LOGIN_URL = "/Account/v1/Authorized";
    String TOKEN_URL = "/Account/v1/GenerateToken";

    MediaType JSON = MediaType.get("application/json");
    OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
    Gson GSON = new Gson();
    String ACCEPT = "Accept";
    String CONTENT_TYPE = "Content-Type";
    String CONTENT_LANGUAGE = "Content-Language";
    String ACCEPT_LANGUAGE = "Accept-Language";
}
