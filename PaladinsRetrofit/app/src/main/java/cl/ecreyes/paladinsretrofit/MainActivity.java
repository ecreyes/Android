package cl.ecreyes.paladinsretrofit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import cl.ecreyes.paladinsretrofit.Api.Paladins;
import cl.ecreyes.paladinsretrofit.Api.PaladinsConfig;
import cl.ecreyes.paladinsretrofit.Api.PaladinsService;
import cl.ecreyes.paladinsretrofit.Model.CreateSession;
import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            Call<CreateSession> createSessionCall = PaladinsService.getPaladinsApi().
                    createsession("createsessionJson",
                            PaladinsConfig.DEVID,
                            PaladinsConfig.getSignature("createsession"),
                            PaladinsConfig.getTimeStamp());
            createSessionCall.enqueue(new Callback<CreateSession>() {
                @Override
                public void onResponse(Call<CreateSession> call, Response<CreateSession> response) {
                    Toast.makeText(MainActivity.this, "Session id: "+response.body().getSession_id(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<CreateSession> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error de llamada", Toast.LENGTH_SHORT).show();
                }
            });
        }catch(Exception e){
            Log.d(TAG,"error todo D:");
        }
    }
}
