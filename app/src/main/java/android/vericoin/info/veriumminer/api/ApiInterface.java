package android.vericoin.info.veriumminer.api;
import android.vericoin.info.veriumminer.models.PoolItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("pool/")
    Call<List<PoolItem>> getPools(@Query("include_stratums") Boolean stratums, @Query("include_last_statistic") Boolean statistic);
}
