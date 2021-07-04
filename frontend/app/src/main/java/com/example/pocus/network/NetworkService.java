package com.example.pocus.network;
import java.util.List;

//import okhttp3.RequestBody;
//import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
//import retrofit2.http.Field;
//import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
//import retrofit2.http.PUT;
//import retrofit2.http.Part;
import retrofit2.http.Path;

public interface NetworkService {
    // keywords api interface
    @POST("/keywords/")
    Call<Keyword> post_keyword(@Body Keyword keyword);

    @PATCH("/keywords/{pk}/")
    Call<Keyword> patch_keyword(@Path("") int pk, @Body Keyword keyword);

    @DELETE("/keywords/{pk}/")
    Call<Keyword> delete_keyword(@Path("") int pk);

    @GET("/keywords/")
    Call<List<Keyword>> get_keyword();

    @GET("/keywords/{pk}/")
    Call<Keyword> get_pk_keyword(@Path("") int pk);

    // messages api interface
    @POST("/messages/")
    Call<Message> post_message(@Body Message message);

    @PATCH("/messages/{pk}/")
    Call<Message> patch_message(@Path("") int pk, @Body Message message);

    @DELETE("/messages/{pk}/")
    Call<Message> delete_message(@Path("") int pk);

    @GET("/messages/")
    Call<List<Message>> get_message();

    @GET("/messages/{pk}/")
    Call<Message> get_pk_message(@Path("") int pk);
}
