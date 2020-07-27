package com.example.recepti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.PreferenceManager;

import com.example.recepti.adapter.ReceptListAdapter;
import com.example.recepti.model.NavItem;
import com.example.recepti.model.Recept;
import com.example.recepti.service.ReceptService;
import com.example.recepti.service.ServiceUtils;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObicanUserReceptiActivity extends AppCompatActivity {

    private List<Recept> recepti = new ArrayList<>();
    private Recept recept = new Recept();
    private SharedPreferences sharedPreferences;
    private ReceptService receptService;
    private ListView listView;

    private ReceptListAdapter receptListAdapter;

    private ImageView btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepti_obican);

        listView = findViewById(R.id.recepti_list);

        Toolbar toolbar = findViewById(R.id.recepti_toolbar_obican);
        setSupportActionBar(toolbar);

        // Pozivanje metode koja izlistava sve recepte
        receptService = ServiceUtils.receptService;
        Call call = receptService.getRecepte();

        call.enqueue(new Callback<List<Recept>>() {
            @Override
            public void onResponse(Call<List<Recept>> call, Response<List<Recept>> response) {
                if (response.isSuccessful()) {
                    recepti = response.body();
                    listView.setAdapter(new ReceptListAdapter(ObicanUserReceptiActivity.this, recepti));
                }
            }

            @Override
            public void onFailure(Call<List<Recept>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Otvaranje selektovanog recepta
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                recept = recepti.get(i);
                Intent intent = new Intent(ObicanUserReceptiActivity.this, ObicanUserReceptActivity.class);
                intent.putExtra("Recept", recept);
                startActivity(intent);

            }
        });
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ObicanUserReceptiActivity.this);
    }



    // Metoda koja izlistava sve recepte
    public void getRecept() {
        Call<List<Recept>> call = receptService.getRecepte();

        call.enqueue(new Callback<List<Recept>>() {
            @Override
            public void onResponse(Call<List<Recept>> call, Response<List<Recept>> response) {
                recepti = response.body();
                ReceptListAdapter receptListAdapter = new ReceptListAdapter(ObicanUserReceptiActivity.this, recepti);
                listView.setAdapter(receptListAdapter);
            }

            @Override
            public void onFailure(Call<List<Recept>> call, Throwable t) {

            }
        });
    }

    //meni na toolbaru, odnosno ikonice za prelazak na ostale aktivnosti
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_item_recepti_obican, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //funkcionalnost opcija iz menija gore navedenog
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                Intent in = new Intent(this, LoginActivity.class);
                startActivity(in);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
