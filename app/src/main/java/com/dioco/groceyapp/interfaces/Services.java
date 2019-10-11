package com.dioco.groceyapp.interfaces;

import com.dioco.groceyapp.pojo.ResGetProductList;
import com.dioco.groceyapp.pojo.ResGetProductWeight;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Services {

    /*Get Product List*/
    @GET("/wp-json/wc/v3/products?category=15&per_page=100&consumer_key=ck_1b062817f1c5004d24eba4a1e9c44814835462a1&consumer_secret=cs_53ab3dec9690d53f0323f729d874406d4782e4cb")
    Call<List<ResGetProductList>> getProductList();

    @GET("/wp-json/wc/v3/products?category=203&per_page=100&consumer_key=ck_1b062817f1c5004d24eba4a1e9c44814835462a1&consumer_secret=cs_53ab3dec9690d53f0323f729d874406d4782e4cb")
    Call<List<ResGetProductList>> getVegetableList();

    @GET("/wp-json/wc/v3/products/product_id/variations?&consumer_key=ck_1b062817f1c5004d24eba4a1e9c44814835462a1&consumer_secret=cs_53ab3dec9690d53f0323f729d874406d4782e4cb")
    Call<List<ResGetProductWeight>> getProductWeightVariation(@Field("product_id") String productId);
}
