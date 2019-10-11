package com.dioco.groceyapp.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.dioco.groceyapp.R;
import com.dioco.groceyapp.interfaces.GetProductListInterface;
import com.dioco.groceyapp.interfaces.GetProductWeightInterface;
import com.dioco.groceyapp.interfaces.Services;
import com.dioco.groceyapp.pojo.ResGetProductList;
import com.dioco.groceyapp.pojo.ResGetProductWeight;

import java.security.cert.CertificateException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCallHelper {

    public GetProductListInterface getProductDelegate = null;
    public GetProductWeightInterface getProductWeightDelegate = null;

    //GET PRODUCT LIST
    public void getProductList(final Context context) {

        Services services = getServiceRetrofit(context);

        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage(context.getResources().getString(R.string.alert_loading));
        pDialog.show();

        try {
            //RequestBody name = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), getSyncJson().toString());
            Call<List<ResGetProductList>> req = services.getProductList();
            req.enqueue(new Callback<List<ResGetProductList>>() {
                @Override
                public void onResponse(Call<List<ResGetProductList>> call, Response<List<ResGetProductList>> response) {
                    Log.d("response-getProductList", "Upload response:" + response);
                    pDialog.dismiss();

                    if (response.code() == 200) {
                        Log.d("response-getProductList", "Upload response:" + response.message());
                        Log.d("response-getProductList", "Upload response_code:" + response.code());
                        try {
                            //JSONObject jsonObject = response.body();
                            Log.d("response-getProductList", "Upload response_body:" + response.body().toString());

                            if (response.body() != null) {
                                List<ResGetProductList> resGetProductList = response.body();
                                Log.d("response-getProduct:", String.valueOf(resGetProductList.size()));
                                getProductDelegate.processFinishGetProductList(resGetProductList);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("response-error", e.getMessage());
                        }
                    } else {
                        Log.d("response-getProductList", "Upload response:" + response.message());
                        PopUpHelper.displayAlertDialog(context, "Getting Some Server Error..!");
                    }
                }

                @Override
                public void onFailure(Call<List<ResGetProductList>> call, Throwable t) {
                    Log.d("response-getProductList", "Fail:");
                    Log.d("response-getProductList", t.toString());
                    System.out.println(t.toString());

                    pDialog.dismiss();
                    PopUpHelper.displayAlertDialog(context, context.getResources().getString(R.string.alert_fail_getProductList));
                    t.printStackTrace();
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getVegetableList(final Context context) {

        Services services = getServiceRetrofit(context);

        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage(context.getResources().getString(R.string.alert_loading));
        pDialog.show();

        try {
            //RequestBody name = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), getSyncJson().toString());
            Call<List<ResGetProductList>> req = services.getVegetableList();
            req.enqueue(new Callback<List<ResGetProductList>>() {
                @Override
                public void onResponse(Call<List<ResGetProductList>> call, Response<List<ResGetProductList>> response) {
                    Log.d("response-getProductList", "Upload response:" + response);
                    pDialog.dismiss();

                    if (response.code() == 200) {
                        Log.d("response-getProductList", "Upload response:" + response.message());
                        Log.d("response-getProductList", "Upload response_code:" + response.code());
                        try {
                            //JSONObject jsonObject = response.body();
                            Log.d("response-getProductList", "Upload response_body:" + response.body().toString());

                            if (response.body() != null) {
                                List<ResGetProductList> resGetProductList = response.body();
                                Log.d("response-getProduct:", String.valueOf(resGetProductList.size()));
                                getProductDelegate.processFinishGetProductList(resGetProductList);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("response-error", e.getMessage());
                        }
                    } else {
                        Log.d("response-getProductList", "Upload response:" + response.message());
                        PopUpHelper.displayAlertDialog(context, "Getting Some Server Error..!");
                    }
                }

                @Override
                public void onFailure(Call<List<ResGetProductList>> call, Throwable t) {
                    Log.d("response-getProductList", "Fail:");
                    Log.d("response-getProductList", t.toString());
                    System.out.println(t.toString());

                    pDialog.dismiss();
                    PopUpHelper.displayAlertDialog(context, context.getResources().getString(R.string.alert_fail_getProductList));
                    t.printStackTrace();
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //GET PRODUCT LIST

    /*
    * Get Product Weight Variations
    * */
    public void getProductWeightVariation(final Context context, final String productId) {

        Services services = getServiceRetrofit(context);

        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage(context.getResources().getString(R.string.alert_loading));
        pDialog.show();

        try {
            //RequestBody name = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), getSyncJson().toString());
            Call<List<ResGetProductWeight>> req = services.getProductWeightVariation(productId);
            req.enqueue(new Callback<List<ResGetProductWeight>>() {
                @Override
                public void onResponse(Call<List<ResGetProductWeight>> call, Response<List<ResGetProductWeight>> response) {
                    Log.d("response-getProductWgt", "Upload response:" + response);
                    pDialog.dismiss();

                    if (response.code() == 200) {
                        Log.d("response-getProductWgt", "Upload response:" + response.message());
                        Log.d("response-getProductWgt", "Upload response_code:" + response.code());
                        try {
                            //JSONObject jsonObject = response.body();
                            Log.d("response-getProductWgt", "Upload response_body:" + response.body().toString());

                            if (response.body() != null) {
                                List<ResGetProductWeight> resGetProductWeights = response.body();
                                Log.d("response-getProductWgt:", String.valueOf(resGetProductWeights.size()));
                                getProductWeightDelegate.processFinishGetProductWeight(resGetProductWeights);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("response-error", e.getMessage());
                        }
                    } else {
                        Log.d("response-getProductWgt", "Upload response:" + response.message());
                        PopUpHelper.displayAlertDialog(context, "Getting Some Server Error..!");
                    }
                }

                @Override
                public void onFailure(Call<List<ResGetProductWeight>> call, Throwable t) {
                    Log.d("response-getProductWgt", "Fail:");
                    Log.d("response-getProductWgt", t.toString());
                    System.out.println(t.toString());

                    pDialog.dismiss();
                    PopUpHelper.displayAlertDialog(context, context.getResources().getString(R.string.alert_fail_getProductWeight));
                    t.printStackTrace();
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * Get Product Weight Variations
     * */



    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    //from service class get URLs to POST and GET data
    private Services getServiceRetrofit(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(300, TimeUnit.SECONDS)
                .connectTimeout(300, TimeUnit.SECONDS)
                .build();

        String url = context.getResources().getString(R.string.api_host); /*+ "/inspection-info-add/"*/
        Log.d("url:", url);

        Services services = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url).client(getUnsafeOkHttpClient().build()).build().create(Services.class);

        return services;
    }
    //from service class get URLs to POST and GET data
}
