package ujcv.edu.lista_videojuegos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_recycler.view.*
import java.util.ArrayList

class Adaptador(private val items: ArrayList<VideoJuegos> = ArrayList()) : RecyclerView.Adapter<Adaptador.AdaptadorViewHolder>() {

    class AdaptadorViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_recycler, parent, false)
        return AdaptadorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AdaptadorViewHolder, position: Int) {

        holder.itemView.nombre.text = items[position].nombre

    }


}