package com.ponyu.botanical.data.remote

import com.ponyu.botanical.BuildConfig
import com.ponyu.botanical.data.remote.plant.PlantInfo
import com.ponyu.botanical.data.remote.plants.PlantsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface PlantsService {

    /**
     * When you query a collection (ex: /api/v1/plants), you'll notice that you have only 20 items returned.
     * That's because results are paginated. You have links for the next page in the links attribute of the JSON response.
     * You can specify the page you want with the page parameter. To query the second page, we have add the page parameter as follows: page=2.
     * [See api](https://docs.trefle.io/reference/#tag/Plants/operation/listPlants)
     */
    @GET("/api/v1/plants?token=${BuildConfig.TREFLE_API_KEY}")
    suspend fun getPlants(
        @Query("page") page: Int,
        @QueryMap options: Map<String, String>
    ) : PlantsResponse

    @GET("/api/v1/plants/search?token=${BuildConfig.TREFLE_API_KEY}")
    suspend fun searchPlants(
        @Query("page") page: Int,
        @Query("q") q: String,
        @QueryMap options: Map<String, String>
    ) : PlantsResponse

    /**
     * [See documentation](https://docs.trefle.io/docs/advanced/plants-fields)
     * [See api](https://docs.trefle.io/reference/#tag/Plants/operation/getPlant)
     */
    @GET("/api/v1/plants/{id}?token=${BuildConfig.TREFLE_API_KEY}")
    suspend fun getPlant(@Path("id") plantId: Int) : PlantInfo
}