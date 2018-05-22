package com.example.watch.liveat500px.manager.http;

import com.example.watch.liveat500px.dao.PhotoItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by watch on 5/3/2018.
 */

public interface ApiService {
    @POST("list")
    Call<PhotoItemCollectionDao> loadPhotoList();

    @POST("list/after/{id}")
    Call<PhotoItemCollectionDao>loadPhotoListAfterID(@Path("id") int id);
}
