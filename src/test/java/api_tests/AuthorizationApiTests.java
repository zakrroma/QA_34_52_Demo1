package api_tests;

import dto.CreateUserResult;
import dto.UserData;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;

import static utils.UserFactory.*;

public class AuthorizationApiTests implements BaseApi {
    @Test
    public void registrationPositiveTest() {
        UserData user = positiveUser();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + REG_URL)
                .post(requestBody)
                .build();

        Response response;

        CreateUserResult result;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            result = GSON.fromJson(response.body().string(), CreateUserResult.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(result);

        Assert.assertEquals(response.code(), 201);
    }

    @Test
    public void loginPositiveTest() {
        UserData user = UserData.builder()
                .userName("test_user2")
                .password("Qwerty123!")
                .build();
        //userID 1b1b1c34-da63-4956-ad22-dd4224121ef9

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 200);
    }

    @Test
    public void login404NegativeTest() {
        UserData user = UserData.builder()
                .userName("string")//("test_user2")
                .password("string")//("Qwerty123!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 404);
    }
}
