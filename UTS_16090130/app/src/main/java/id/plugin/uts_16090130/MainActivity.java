package id.plugin.uts_16090130;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import id.plugin.uts_16090130.api.RestApi;
import id.plugin.uts_16090130.api.RetroServer;
import id.plugin.uts_16090130.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText nama_makanan, kode_makanan, kategori;
    Button btnsave, btnTampildata;
    ProgressBar pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pd= (ProgressBar)findViewById(R.id.pd);
        pd.setIndeterminate(true);
        pd.setVisibility(View.GONE);

        nama_makanan = (EditText) findViewById(R.id.edt_nama);
        kode_makanan = (EditText) findViewById(R.id.edt_kode);
        kategori = (EditText) findViewById(R.id.edtkategori;
        btnTampildata = (Button) findViewById(R.id.btntampildata);
        btnsave = (EditText) findViewById(R.id.btn_insertdata;

        Intent data = getIntent();
        final  String iddata = data.getStringExtra("id");
        if (iddata != null) {
            btnsave.setVisibility(View.GONE);
            btnTampildata.setVisibility(View.GONE);
            nama_makanan.setText(data.getStringExtra("nama_makanan"));
            kode_makanan.setText(data.getStringExtra("kode_makanan"));
            kategori.setText(data.getStringExtra(kategori));
        }

        btnTampildata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new
                        Intent(MainActivity.this,TampilData.class));
            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String snama_makanan = nama_makanan.getText().toString();
                String skode_makanan = kode_makanan.getText().toString();
                String skategori = kategori.getText().toString();
                if (snama_makanan.isEmpty()) {
                    nama_makanan.setError("Nama Perlu Di Isi");
                }else if (skode_makanan.isEmpty()) {
                    kode_makanan.setError("Kode Perlu Di isi");
                }else if (skategori.isEmpty()) {
                    kategori.setError("kategori perlu di isi");
                }else {

                    RestApi api =
                            RetroServer.getClient().create(RestApi.class);
                    Call<ResponseModel> sendbio =
                            api.sendBiodata(snama_makanan,skode_makanan,skategori);
                    sendbio.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            log.d("RETRO", "response : " +
                                    response.body().toString());
                            String kode = response.body().getKode();

                            if (kode.equals("1"))
                            {
                                Toast.makeText(MainActivity.this, "Data berhasil di simpan",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, TampilData.class));
                                nama_makanan.getText().clear();
                                kode_makanan.getText().clear();
                                kategori.getText().clear();
                            }else
                            {
                               Toast.makeText(MainActivity.this, "Data" +
                                       "Error tidak berhasil di simpan", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                        }
                    });
                });
            });
    }
        @Override
        public void onBackPressed() {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("warning");
            alert.setMessage("do you wan to exit");

            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.this.finish();
                }
            });
            alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        }

}
