package com.example.saini.mvp.interfaces;


import com.example.saini.mvp.pojo.ResponsePojo;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by nazer on 27/9/17.
 */

public interface ApiInterface {

    @Multipart
    @PUT("Users/updateAddress")
    Call<ResponsePojo> updateProfile(@Part("address") String address,
                                     @Part("city") String logintoken,
                                     @Part("postal_code") String mobile_number,
                                     @Part("state") String firstName,
                                     @Part("country") String lastName,
                                     @Part("addressProof\"; filename=\"image.png") RequestBody body);
    @Multipart
    @PUT("Users/updateAddress")
    Call<ResponsePojo> updateProfile(@Part("address") RequestBody address,
                                     @Part("city") RequestBody logintoken,
                                     @Part("postal_code") RequestBody mobile_number,
                                     @Part("state") RequestBody firstName,
                                     @Part("country") RequestBody lastName,
                                     @Part MultipartBody.Part ription);


    @Multipart
    @PUT("Users/updateAddress")
    Call<ResponsePojo> updateProfile(@PartMap Map<String, RequestBody> map);


    @Multipart
    @POST("Albums/addPhotosOrVideos")
    Call<ResponsePojo> addPhotoVideo(@Part("type") int type, @PartMap Map<String, RequestBody> map, @Part MultipartBody.Part file);

}
