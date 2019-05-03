package id.plugin.uts_16090130;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import id.plugin.uts_16090130.adapter.RecylerAdapter;
import id.plugin.uts_16090130.api.RestApi;
import id.plugin.uts_16090130.api.RetroServer;
import id.plugin.uts_16090130.model.ResponseModel;
import okhttp3.Response;

public class TampilData extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<DataModel> mItems = new ArrayList<>();
    ProgressBar pd;

@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tampil_data);

    pd = (ProgressBar) findViewById(R.id.pd);
    pd.setIndeterminate(true);
    pd.setVisibility(View.VISIBLE);

    mRecycler = (RecyclerView) findViewById(R.id.recyclerTemp);
    mManager = new LinearLayoutManager(this);
    mRecycler.setLayoutManager(mManager);

    RestApi api = RetroServer.getClient().create(RestApi.class);
    Call<ResponseModel> getdata = api.getBiodata();
    getdata.enqueue(new Callback<ResponseModel> () {
        @Override
        public void onResponse(Call<ResponseModel> call,
                               Response<ResponseModel> response) {
            pd.setVisibility(View.GONE);
            Log.d("RETRO", "RESPONSE :" +
                    response.body().getKode());
            mItems = response.body().getReult();
            mAdapter = new
                    RecylerAdapter(TampilData.this,mItems);
        }
        @Override
        public void onFailure(Call<ResponseModel> call, Throwable t) {
            pd.setVisibility(View.GONE);
            Log.d(("RETRO", "FAILED : respon gagal");
        }
     });
   }
}
