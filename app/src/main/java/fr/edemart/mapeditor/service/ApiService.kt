package fr.edemart.mapeditor.service

import fr.edemart.mapeditor.entity.Point
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("iut/game-list")
    suspend fun getMapList(): Response<MutableList<String>>
    @GET("iut/game/{id}")
    suspend fun getMapById(@Path("id") id : String): Response<List<Point>>
    @POST("iut/game/{id}")
    suspend fun createMap(@Path("id") id : String): Response<List<Point>>
    @DELETE("iut/game/{id}")
    suspend fun deleteMap(@Path("id") id : String): Response<DELETE>
}
