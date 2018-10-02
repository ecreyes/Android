package cl.ecreyes.paladinsretrofit.Api;

import cl.ecreyes.paladinsretrofit.Model.CreateSession;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Paladins {
    String URL = "http://api.paladins.com/paladinsapi.svc/";

    @GET("{methodjson}/{devid}/{signature}/{timestamp}")
    Call<CreateSession> createsession(@Path("methodjson") String methodjson,
                                      @Path("devid") String devid,
                                      @Path("signature") String signature,
                                      @Path("timestamp") String timestamp);
}
