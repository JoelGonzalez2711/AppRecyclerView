package com.example.apprecyclerview


import com.google.gson.annotations.SerializedName

data class PaisesResponse(
    @SerializedName("name") var nombre:Map<String, String>,
    @SerializedName("capital") var capital:String,
    @SerializedName("population") var poblacion:Long,
    @SerializedName("flags") var bandera:Flags

)

data class Flags(
    @SerializedName("png") var png:String
)

