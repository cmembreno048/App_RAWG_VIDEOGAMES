package ujcv.edu.listavideojuegos.recycler_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ujcv.edu.listavideojuegos.R;

public class adaptadore_items extends RecyclerView.Adapter<adaptadore_items.ItemsViewHolder> {
    private Context mContext;
    private ArrayList<ItemsRecycleView> mItemsList;

    public adaptadore_items(Context mContext, ArrayList<ItemsRecycleView> mItemsList) {
        this.mContext = mContext;
        this.mItemsList = mItemsList;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.items_recycler, parent, false);
        return new ItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        ItemsRecycleView currenItem = mItemsList.get(position);

        String urlImage = currenItem.getUrlImage();
        String nombre_videojuego = currenItem.getNombre_Juego();
        Double Rating = currenItem.getRating();

        holder.mtextvideojuego.setText(nombre_videojuego);
        holder.mtextrating.setText("Rating: "+Rating);
        Picasso.get().load(urlImage).into(holder.urlImagen);


    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder{
        public ImageView urlImagen;
        public TextView mtextvideojuego;
        public TextView mtextrating;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            urlImagen = itemView.findViewById(R.id.image_view);
            mtextvideojuego = itemView.findViewById(R.id.text_videojuego);
            mtextrating = itemView.findViewById(R.id.rating);

        }
    }

}
