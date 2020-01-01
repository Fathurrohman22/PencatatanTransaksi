package com.example.pencatatantransaksi.Contoller;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class  AppController extends Application{
    public static final String TAG = AppController
            .class.getSimpleName();

    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private static AppController appController;

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
    }

    public  static synchronized AppController getAppController(){
        return appController;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

//    public ImageLoader getImageLoader(){
//        getRequestQueue();
//        if (imageLoader == null){
//            imageLoader = new ImageLoader(this.requestQueue
//                    , new LruBitmapCache());
//        }
//        return this.imageLoader;
//    }

    public <T> void addToRequestQueue(Request<T> request, String tag){
        // set the default tag if tag is empty
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequest(Object object){
        if (requestQueue != null){
            requestQueue.cancelAll(object);
        }
    }
}