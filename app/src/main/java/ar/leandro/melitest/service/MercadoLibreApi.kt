package ar.leandro.melitest.service

import ar.leandro.melitest.model.Article
import ar.leandro.melitest.model.ArticleDescription
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//En esta interfaz hago las 2 llamadas
//Acá se mapean los recursos de la API
//Los recursos son: el item(items), con su path variable; y items con path variable del item y descripción
interface MercadoLibreApi {

    @GET("items/{itemId}")
    fun getArticle(@Path("itemId") id: String): Call<Article>

    @GET("items/{itemId}/description")
    fun getDescription(@Path("itemId") id: String): Call<ArticleDescription>
}