package pho.formulaone.batch.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pho.formulaone.batch.integration.pojo.ErgastResult;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;

@Component
public class ErgastIntegration {

    @Autowired
    private Retrofit ergastAPI;

    public ErgastResult getRaceInformation(int season, int race) throws IOException {
        ErgastAPI api = ergastAPI.create(ErgastAPI.class);
        Call<ErgastResult> data = api.getSeasonInformation(season, race);

        return data.execute().body();
    }
}
