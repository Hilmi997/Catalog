package com.example.hilmi.catalogcupang.view;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hilmi.catalogcupang.GlobalVars;
import com.example.hilmi.catalogcupang.MenuParceble;
import com.example.hilmi.catalogcupang.MenuAdapter;
import com.example.hilmi.catalogcupang.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

    private RecyclerView rv;
    private MenuAdapter adapter;
    private Gson gson;
    private List<MenuParceble> allList;

    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_home, container, false);



        gson = new Gson();
        allList = new ArrayList<>();
        rv =  rootView. findViewById(R.id.rv);

        adapter = new MenuAdapter(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        //gridveiew
        int numberOfColumns = 2;
        rv.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));

        rv.setAdapter(adapter);


        loadData();


        return rootView;

    }

    private void loadData() {

        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setIndeterminate(true);
        progress.setMessage(getString(R.string.please_wait_onprocess));
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setTitle(getString(R.string.please_wait));

        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        if (adapter!=null)
            adapter.clearAll();



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
                            else if (dataArr.length() >1){
                                System.out.print("koneksi tidak ada");
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




