package api_tests;

import dto.*;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BaseApi;
import utils.IGetToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        List<BookDto> booksToAdd = new ArrayList<BookDto>();
        booksToAdd.add(books.getBooks().get(0));
        booksToAdd.add(books.getBooks().get(2));
        booksToAdd.add(books.getBooks().get(4));
        AddListOfBooks addListOfBooks = new AddListOfBooks();
        addListOfBooks.setUserId("1b1b1c34-da63-4956-ad22-dd4224121ef9");
        List<CollectionOfISBNs> collectionOfISBNs = new ArrayList<CollectionOfISBNs>();
        collectionOfISBNs.add(new CollectionOfISBNs(booksToAdd.get(0).getIsbn()));
        collectionOfISBNs.add(new CollectionOfISBNs(booksToAdd.get(1).getIsbn()));
        collectionOfISBNs.add(new CollectionOfISBNs(booksToAdd.get(2).getIsbn()));
        addListOfBooks.setCollectionOfIsbns(collectionOfISBNs);

        System.out.println(tokenDto.getToken());

        RequestBody requestBody = RequestBody.create(GSON.toJson(addListOfBooks), JSON);
        Request request2 = new Request.Builder()
                .url(BASE_URL + ADD_BOOK_LIST_URL)
                .addHeader("Authorization", "Bearer " + tokenDto.getToken()) // <------
                .post(requestBody)
                .build();

        Response response2;

        try {
            response2 = OK_HTTP_CLIENT.newCall(request2).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response2.code(), 201);
    }
}
