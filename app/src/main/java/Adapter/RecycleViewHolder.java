package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import glory.dataskp.R;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleViewHolder extends RecyclerView.ViewHolder {

    public TextView txtListJudul;
    public ImageView gmbrList;

    public RecycleViewHolder(View itemView) {
        super(itemView);

        txtListJudul = (TextView) itemView.findViewById(R.id.txtJudul);
        gmbrList = (ImageView) itemView.findViewById(R.id.listIcon);
    }
}
