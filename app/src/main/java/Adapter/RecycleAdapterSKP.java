package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import glory.dataskp.R;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleAdapterSKP extends RecyclerView.Adapter<RecycleViewHolder> {

    private final Context context;
    LayoutInflater inflater;
    Intent i;
    int gambarKirim;

    //dekalrasi buat List nya
    String[] nama={"Mengendalikan Mesin Sesuai Prosedur","Mempersiapkan Mesin sesuai prosedur","Hiking ke gunung rajabasa"};
    int[]icon = {R.drawable.greencircle,R.drawable.greencircle,R.drawable.greencircle};

    public RecycleAdapterSKP(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list,parent,false);
        RecycleViewHolder viewHolder = new RecycleViewHolder(v);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {

        holder.txtListJudul.setText(nama[position]);
        holder.gmbrList.setImageResource(icon[position]);

        holder.gmbrList.setOnClickListener(clicklistener);
        holder.txtListJudul.setOnClickListener(clicklistener);

        holder.txtListJudul.setTag(holder);
        holder.gmbrList.setTag(holder);

    }

    View.OnClickListener clicklistener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {

            RecycleViewHolder vHolder = (RecycleViewHolder) v.getTag();
            int position = vHolder.getPosition();


            switch (position)
            {

                case 0:


                    break;

                case 1:

                    break;

                case 2:
                    //i= new Intent(context,SubMenuBelajarNun.class);
                   // context.startActivity(i);
                    break;


            }

        }
    };


    public int getItemCount() {
        return nama.length;
    }
}
