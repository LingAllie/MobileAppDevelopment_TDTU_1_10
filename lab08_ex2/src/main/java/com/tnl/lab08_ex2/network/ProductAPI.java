package com.tnl.lab08_ex2.network;

import com.tnl.lab08_ex2.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductAPI {

    @GET("get-products.php")
    Call<BaseJSON<List<Product>>> getProduct();

    @POST("add-product.php")
    Call<BaseJSON<Long>> addNewProduct(@Body Product product);

    @POST("update-product.php")
    Call<BaseJSON<Long>> updateExistingProduct(@Body Product product);

    @POST("delete-product.php")
    Call<BaseJSON<Long>> deleteProduct(@Body Product product);
}
