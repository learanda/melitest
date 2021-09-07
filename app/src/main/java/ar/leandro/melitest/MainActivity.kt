package ar.leandro.melitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import ar.leandro.melitest.model.Article
import ar.leandro.melitest.model.ArticleDescription
import ar.leandro.melitest.service.API
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var txtTitle: TextView
    lateinit var txtDescription: TextView
    lateinit var txtSoldQuantity: TextView
    lateinit var txtPrice: TextView
    lateinit var ivPicture: ImageView
    lateinit var btnSearch: Button
    lateinit var txtItemId: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getViews()
        setListeners()
    }

    private fun getViews() {
        txtTitle = findViewById(R.id.txtTitle)
        txtDescription = findViewById(R.id.txtDescription)
        txtSoldQuantity = findViewById(R.id.txtSoldQuantity)
        txtPrice = findViewById(R.id.txtPrice)
        ivPicture = findViewById(R.id.ivPicture)
        btnSearch = findViewById(R.id.btnSearch)
        txtItemId = findViewById(R.id.txtItemId)
    }

    private fun setListeners() {
        btnSearch.setOnClickListener {
            searchItem()
            searchDescription()
        }
    }

    //Esta funcion crea un objeto API que llama a getArticle y le pasa el itemID que escribí en el EditText
    //Como callback defino un objeto anonimo del tipo Callback de Article
    //El object me obliga a implementar los metodos de la clase abstracta callback
    private fun searchItem() {
        API().getArticle(txtItemId.text.toString(), object : Callback<Article> {
            override fun onResponse(call: Call<Article>, response: Response<Article>) {
                if (response.isSuccessful) {
                    response.body()!!.apply {
                        txtTitle.text = this.title
                        txtSoldQuantity.text = this.soldQuantity.toString()
                        txtPrice.text = this.price.toString()

                        Picasso.get()
                            .load(this.pictures[0].secureUrl)
                            .into(ivPicture)
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Falló con código ${response.code()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<Article>, t: Throwable) {
                Log.e("MainActivity", "Falló al obtener el artículo", t)
            }

        })
    }

    //Como para la descripción era una API distinta lo tengo que hacer aparte
    private fun searchDescription() {
        API().getDescription(txtItemId.text.toString(),object : Callback<ArticleDescription>{
            override fun onResponse(
                call: Call<ArticleDescription>,
                response: Response<ArticleDescription>
            ) {
                if (response.isSuccessful) {
                    response.body()!!.apply {
                        txtDescription.text = this.plainText
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Falló con código ${response.code()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ArticleDescription>, t: Throwable) {
                Log.e("MainActivity", "Falló al obtener la descripción", t)
            }

        })
    }
}