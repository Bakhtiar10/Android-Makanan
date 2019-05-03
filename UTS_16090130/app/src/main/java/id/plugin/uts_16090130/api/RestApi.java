package id.plugin.uts_16090130.api;

import id.plugin.uts_16090130.model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    //insert
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseModel> sendBiodata(@Field("nama_makanan") String nama_makanan,
                                    @Field("kode_makanan") String kode_makanan,
                                    @Field("kategori") String kategori);

    //read
    @GET("read.php")
    Call<ResponseModel> getBiodata();

}
