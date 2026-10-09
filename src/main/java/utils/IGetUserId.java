package utils;

import dto.CreateUserResult;
import dto.UserData;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import static utils.UserFactory.positiveUser;

public interface IGetUserId extends BaseApi {
    default String getUserId() {
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
            return result.getUserId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
