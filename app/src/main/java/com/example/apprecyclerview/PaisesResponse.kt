package com.example.apprecyclerview


import com.google.gson.annotations.SerializedName

data class PaisesResponse(
    @SerializedName("name") var nombre:String,
    @SerializedName("capital") var capital:String,
    @SerializedName("population") var poblacion:Int,
    @SerializedName("flags") var bandera:Flags

)

data class Flags(
    @SerializedName("png") var png:String
)

