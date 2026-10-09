package api_tests;

import dto.Books;
import dto.TokenDto;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BaseApi;
import utils.IGetToken;

import java.io.IOException;

public class BooksApiTests implements BaseApi, IGetToken {
    TokenDto tokenDto;

    @BeforeClass
    public void login() {
        tokenDto = generateToken();
    }

    @Test
    public void getAllBooksPositiveApiTest() {
        Request request = new Request.Builder()
                .url(BASE_URL + GET_ALL_BOOKS_URL)
                .get()
                .build();

        Response response;
        Books books;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            books = GSON.fromJson(response.body().string(), Books.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(books);

        Assert.assertEquals(response.code(), 200);
    }

    @Test
    public void addBookCollectionPositiveApiTest() {
        Request request = new Request.Builder()
                .url(BASE_URL + GET_ALL_BOOKS_URL)
                .get()
                .build();

        Response response;
        Books books;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            books = GSON.fromJson(response.body().string(), Books.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(books);
    }
}
