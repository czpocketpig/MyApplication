package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/10/13.
 */


public class SurfaceViewDemo extends Activity {
     private ImageView imageView;
     private ImageLoader.ImageListener imageListener;
    private RequestQueue requestQueue;
    TextView textview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: "+android.os.Process.myTid());
        setContentView(R.layout.activity_demo1);
//        imageView=findViewById(R.id.pic);
//        textview =findViewById(R.id.message);
//        ImageLoader imageLoader =VolleySingleton.getVolleySingleton(this).getImageLoader();
//        imageListener = ImageLoader.getImageListener(imageView,
//                R.mipmap.ic_launcher, R.mipmap.ic_launcher_round);
//        imageLoader.get("https://gss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/279759ee3d6d55fbd1b78c2a6e224f4a20a4dd4d.jpg",imageListener);
//        requestQueue= VolleySingleton.getVolleySingleton(this).getRequestQueue();
//        StringRequest stringRequest =new StringRequest("https://www.baidu.com", new Response.Listener<String>() {

//            @Override
//            public void onResponse(String response) {
//                Log.i(TAG, "onResponse: "+android.os.Process.myTid());
//
//                textview.setText(response.toString());
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i(TAG, "onErrorResponse: "+error.toString());
//            }
//        }
//        );
//        requestQueue.add(stringRequest);
        OkHttpClient okHttpClient =new OkHttpClient();
       Request request =new Request.Builder().url("https://github.com/hongyangAndroid").build();
        Call call=okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                Log.i(TAG, "onResponse: "+android.os.Process.myTid());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "run: "+android.os.Process.myTid());
                    }
                });

            }
        });






    }
}
