package cl.ecreyes.paladinsretrofit.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaladinsService {

    public static Paladins getPaladinsApi(){
        return new Retrofit.Builder()
                .baseUrl(Paladins.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Paladins.class);
    }
}
