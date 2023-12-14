package com.example.apprecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecyclerview.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnQueryTextListener{

    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter: PaisAdapter
    private val paisImage = mutableListOf<PaisesResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchView.setOnQueryTextListener(this)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        adapter= PaisAdapter(paisImage)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter=adapter
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/name/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val llamada:Response<PaisesResponse> =getRetrofit().create(APIService::class.java).getPaisesByName("$query?fullText=true")
            val paises:PaisesResponse? =llamada.body()
            runOnUiThread{
                if(llamada.isSuccessful){
                    val pais = paises?.bandera ?: emptyList<PaisesResponse>()
                    paisImage.clear()
                    paisImage.addAll(pais as Collection<PaisesResponse>)
                    adapter.notifyDataSetChanged()
                }else{
                    //error
                    showError()
                }
            }
        }
    }

    private fun showError(){
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty())
            searchByName(query.lowercase())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}