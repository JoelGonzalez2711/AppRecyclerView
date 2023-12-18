package com.example.apprecyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PaisAdapter(private val paises: List<PaisesResponse>) : RecyclerView.Adapter<PaisAdapter.PaisViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_paises, parent, false)
        return PaisViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaisViewHolder, position: Int) {
        val pais = paises[position]
        holder.bind(pais)
    }

    override fun getItemCount(): Int {
        return paises.size
    }

    class PaisViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val capitalTextView: TextView = itemView.findViewById(R.id.capitalTextView)
        private val populationTextView: TextView = itemView.findViewById(R.id.populationTextView)
        private val flagImageView: ImageView = itemView.findViewById(R.id.flagImageView)
        @SuppressLint("SetTextI18n")
        fun bind(country: PaisesResponse) {
            this.nameTextView.text = "Name: ${country.nombre}"
            capitalTextView.text = "Capital: ${country.capital}"
            populationTextView.text = "Population: ${country.poblacion}"
            Picasso.get().load(country.bandera.png).into(flagImageView)
        }
    }
}