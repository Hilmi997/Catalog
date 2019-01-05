package com.example.hilmi.catalogcupang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hilmi.catalogcupang.akun.Akun;
import com.example.hilmi.catalogcupang.tentang.Tentang;
import com.example.hilmi.catalogcupang.view.FragmentHome;


public class Index extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

//        //tolbar
//        Toolbar ToolBarAtas = (Toolbar) findViewById(R.id.toolbar_options);
//        ToolBarAtas.showOverflowMenu();


        //home pertama
        FragmentManager fm1 = getSupportFragmentManager();
        fm1.beginTransaction().replace(R.id.fl_container, new FragmentHome()).addToBackStack("").commit();



        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bn_main);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                                                    @Override
                                                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                        switch (item.getItemId()) {
                                                            case R.id.bnv1:
                                                                FragmentManager fm1 = getSupportFragmentManager();
                                                                fm1.beginTransaction().replace(R.id.fl_container, new FragmentHome()).addToBackStack("").commit();
                                                                break;

                                                            case R.id.bnv2:
                                                                FragmentManager fm2 = getSupportFragmentManager();
                                                                fm2.beginTransaction().replace(R.id.fl_container, new Akun()).commit();
                                                                break;


                                                        }
                                                        return true;
                                                    }
                                                });
    }





            //optionsMenu titik 3
            @Override
            public boolean onCreateOptionsMenu(Menu menu)
            {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.menu_options, menu);

                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.options_shared:
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plaint");
                        String shared = "https://www.rajeg.tk/";
                        i.putExtra(android.content.Intent.EXTRA_TEXT, shared);
                        startActivity(i);
                        break;

                    case R.id.options_tentang:
                        Intent i2 = new Intent(getApplicationContext(), Tentang.class);
                        startActivity(i2);

                }
                return true;
            }


}