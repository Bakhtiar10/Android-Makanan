package id.plugin.uts_16090130.model;

import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("kategori")
    private String mKategori;
    @SerializedName("id")
    private String mId;
    @SerializedName("nama_makanan")
    private String mNama_Makanan;
    @SerializedName("kode_makanan")
    private String mKode_Makanan;

    public String getmKategori() {
        return mKategori;
    }

    public void setmKategori(String kategori) {
        mKategori = kategori;
    }
    public String getmId() {
        return mId;
    }
    public void setmId(String id) {
        mId = id;
    }
    public String getmNama_Makanan() {
        return mNama_Makanan;
    }
    public void setmNama_Makanan(String nama_makanan) {
        mNama_Makanan = nama_makanan;
    }
    public String getmKode_Makanan() {
        return mKode_Makanan;
    }
    public void setmKode_Makanan(String kode_makanan) {
        mKode_Makanan = kode_makanan;
    }

}
