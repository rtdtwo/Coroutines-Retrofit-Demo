package blog.rishabh.coroutinesretrofitdemo

import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Rishabh Tatiraju
 *
 * An interface to used as the Retrofit service
 * Holds all APIs
 *
 * How to use:
 * @sample ApiAdapter.apiClient.getRandomDogImage()
 * */

interface ApiClient {
    @GET("/api/breeds/image/random")
    suspend fun getRandomDogImage(): Response<DogImageModel>
}