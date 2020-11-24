package pho.formulaone.batch.integration;

import pho.formulaone.batch.integration.pojo.ErgastResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ErgastAPI {

    @GET("f1/{season}/{race}/results.json")
    Call<ErgastResult> getSeasonInformation(@Path("season") int season, @Path("race") int race);

}
