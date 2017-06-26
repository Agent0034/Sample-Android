package sample.network;

import android.content.Context;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;




public class ApiClient {

    final static String BASE_URL = "https://example.com/";
    private static  RestAdapter restAdapter;
    static ExecutorService mExecutorService;
    public static boolean cancelledRequest = false;

    private static Throwable nextError;
    private static  RestAdapter getRestAdapter(){
        //Bus eventBus = new BusProvider().get();
        //mExecutorService = Executors.newCachedThreadPool();
        OkHttpClient client = new OkHttpClient();


        client.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        restAdapter = new RestAdapter.Builder()
                .setClient(new ConnectivityAwareUrlClient( new OkClient(client), new NetworkConnectivityManager() ))
                .setEndpoint(API_URL)
                .setExecutors(mExecutorService,  new MainThreadExecutor())

                .build();



        return restAdapter;
    }
    public static  class ImmediateExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    public static  void stopAll(){
        mExecutorService.shutdownNow();
        cancelledRequest = true;
    }

    public static getCoordinateList getCoordinateListInterface(){

        getCoordinateList coordinateList =null;
        try {
            mExecutorService = Executors.newCachedThreadPool();
            if(restAdapter==null){
                restAdapter=getRestAdapter();
            }
            coordinateList = restAdapter.create(ApiResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coordinateList;
    }


    public interface getCoordinateList {
        @FormUrlEncoded
        @POST("/mobile/getCoordinates")
        void getCoorList(@Field("businessName") String businessName,Callback<List<ApiResponse>> callback);

    }



}