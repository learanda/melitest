package ar.leandro.melitest.service

import ar.leandro.melitest.model.Article
import ar.leandro.melitest.model.ArticleDescription
import com.google.gson.Gson
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//En esta clase se crea el objeto de Retrofit
class API {

    private fun getAPI(): MercadoLibreApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        //Retrofit tiene que generar una implementación para MercadoLibreAPI
        return retrofit.create(MercadoLibreApi::class.java)
    }
    //Con este objeto Retrofit hace una implementacion para la interfaz MercadoLibreApi

    //Esta API debe tener 2 fun publicas para poder llamar a los métodos de el objeto retrofit
    fun getArticle(id: String, callback: Callback<Article>) {
        getAPI().getArticle(id).enqueue(callback)
    }

    fun getDescription(id: String, callback: Callback<ArticleDescription>) {
        getAPI().getDescription(id).enqueue(callback)
    }
}

//Con esto ya tengo implementado Retrofit para llamar a la API de Mercado Libre