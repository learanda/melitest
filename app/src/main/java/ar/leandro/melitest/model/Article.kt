package ar.leandro.melitest.model

import com.google.gson.annotations.SerializedName

data class Article (
    var id: String,
    var title: String,
    @SerializedName("sold_quantity")
    var soldQuantity: Long,
    var pictures: List<ArticlePicture>
)

//Para la descripción va aparte porque le tengo que pegar a otra API distinta a la de artículos.
data class ArticleDescription (
    @SerializedName("plain_text")
    var plainText: String
)

data class ArticlePicture (
    @SerializedName("secure_url")
    var secureUrl: String
)