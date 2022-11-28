package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiexamen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import clases.Nombres;
import clases.numeros;

public class AdaptadorGanadores extends RecyclerView.Adapter<AdaptadorGanadores.MyViewHolder> {

    List<Nombres> nombres;

    public AdaptadorGanadores(List<Nombres> nombres)
    {
        this.nombres=nombres;
    }

    @NonNull
    @Override
    public AdaptadorGanadores.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.numeros_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorGanadores.MyViewHolder holder, int position) {
      holder.setData(nombres.get(position));
      //      holder.setCount(nombres.get(position));
      // holder.setUrl(nombres.get(position));
      //  Nombres num=nombres.get(position);
      //  numeros p = nombres.get(position);
        Picasso.get().load("https://fer-uig.glitch.me/").into(holder.img);
    }

    @Override
    public int getItemCount() {
        return nombres.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        private TextView name,count,url;
        private ImageView img;

        public MyViewHolder(final View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.txtname);
           count=itemView.findViewById(R.id.txtcantidad);
            img=itemView.findViewById(R.id.imgview);

        }

        public void setData(Nombres namedato)
        {
            name.setText(namedato.getNombre());
            //name.setText("pablo");
            count.setText(namedato.getCantidad());
        }

    }
}
