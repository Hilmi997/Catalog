package com.example.hilmi.catalogcupang;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LihatMenu extends AppCompatActivity {

    private RecyclerView rv;
    private MenuAdapter adapter;
    private Gson gson;
    private List<MenuParceble> allList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_menu);

        gson = new Gson();
        allList = new ArrayList<>();
        rv =  findViewById(R.id.rv);

        adapter = new MenuAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);


        loadData();

    }

    private void loadData() {

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage(getString(R.string.please_wait_onprocess));
        progress.setTitle(getString(R.string.please_wait));
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        if (adapter!=null)
            adapter.clearAll();


//        AndroidNetworking.get("http://192.168.0.13/api-cupang/read_all_json.php")
//                .addPathParameter("id", "1")
//                .setTag(this)
//                .setPriority(Priority.LOW)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//
//                    public  final String TAG = null;
//
//                    public void onResponse(MenuParceble user) {
//                        // do anything with response
//                        Log.d(TAG, "id : " + user.getId());
//                        Log.d(TAG, "foto : " + user.getFoto());
//                        Log.d(TAG, "title : " + user.getTitle());
//                        Log.d(TAG, "harga : " + user.getHarga());
//                    }
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        // handle error
//                    }
//                });
//



        AndroidNetworking.get(GlobalVars.BASE_IP+"api/read_all_json.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<MenuParceble> results = new ArrayList<>();
                        try {

                            if (results!=null)
                                results.clear();

                            //String message = response.getString("data");


                                String records = response.getString("data");



                                JSONArray dataArr = new JSONArray(records);

                                if (dataArr.length()>0){

                                    for (int i = 0; i < dataArr.length(); i++) {
                                        MenuParceble menu = gson.fromJson(dataArr.getJSONObject(i).toString(), MenuParceble.class);
                                        results.add(menu);


                                    }
                            }

                            progress.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();

                            progress.dismiss();
                        }

                        adapter.addAll(results);

                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        progress.dismiss();
                    }
                });




    }

}


