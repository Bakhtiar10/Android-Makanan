package id.plugin.uts_16090130.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.plugin.uts_16090130.MainActivity;
import id.plugin.uts_16090130.R;
import id.plugin.uts_16090130.model.DataModel;

public class RecylerAdapter extends
RecyclerView.Adapter<RecylerAdapter.MyHolder> {
    List<DataModel> mList;
    Context ctx;
public RecylerAdapter(Context ctx, List<DataModel> mList) {
    this.mList = mList;
    this.ctx = ctx;
}

@Override
    public RecylerAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View layout =
            LayoutInflater.from(parent.getContext()).inflate(R.layoutlist,parent, false);
    MyHolder holder = new MyHolder(layout);
    return holder;
}

@Override
    public void onBindViewHolder(RecylerAdapter.MyHolder holder,
                                 final int position) {
    holder.nama_makanan.setText(mList.get(position).getNama_Makanan());
    holder.kode_makanan.setText(mList.get(position).getKode_Makanan());
    holder.kategori.setText(mList.get(position).getKategori());

    holder.itemView.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            Intent goInput = new Intent(ctx, MainActivity.class);
            try {
                goInput.putExtra("id",
                        mList.get(position).getId());
                goInput.putExtra("nama_makanan",
                        mList.get(position).getNama_Makanan());
                goInput.putExtra("kategori",
                        mList.get(position).getKategori());
                ctx.startActivities(goInput);
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(ctx, "Error data " +e,
                        Toast.LENGTH_SHORT().show();
            )
            }
        });

    }
            @Override
            public int getItemCount()
            {
                return mList.size();
            }

            public class MyHolder extends RecyclerView.ViewHolder {
                TextView nama_makanan, kode_makanan, kategori;
                DataModel dataModel;
                public MyHolder(View v)
                {
                    super(v);

                    nama_makanan = (TextView) v.findViewById(R.id.tvNama);
                    kode_makanan = (TextView) v.findViewById(R.id.tvKode);
                    kategori = (TextView) v.findViewById(R.id.tvKategori);
                }
        }
}