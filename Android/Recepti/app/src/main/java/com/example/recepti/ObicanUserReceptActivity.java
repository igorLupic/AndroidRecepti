package com.example.recepti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import com.example.recepti.R;
import com.example.recepti.ReceptActivity;
import com.example.recepti.ReceptiActivity;
import com.example.recepti.adapter.KomentarListAdapter;
import com.example.recepti.adapter.ReceptListAdapter;
import com.example.recepti.model.Komentar;
import com.example.recepti.model.Recept;
import com.example.recepti.service.KomentarService;
import com.example.recepti.service.ReceptService;
import com.example.recepti.service.ServiceUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObicanUserReceptActivity extends AppCompatActivity {

    private ReceptService receptService;
    private Recept recept = new Recept();

    private SharedPreferences sharedPreferences;

    private ImageView btnBack;

    private ListView listView;
    private ReceptListAdapter receptListAdapter;
    private KomentarService komentarService;
    private List<Komentar> komentari = new ArrayList<>();
    Komentar  komentar = new Komentar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recept_obican);

        Toolbar toolbar = findViewById(R.id.recept_toolbar_obican);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            recept = extras.getParcelable("Recept");
        }

        receptService = ServiceUtils.receptService;

        TextView vreme = findViewById(R.id.vreme_view);
        TextView priprema = findViewById(R.id.priprema_view);
        TextView sastojci = findViewById(R.id.sastojci_view);
        TextView tezina = findViewById(R.id.tezina_view);
        TextView naziv = findViewById(R.id.naziv_view);

        vreme.setText("Vreme: " + recept.getVreme());
        priprema.setText("Priprema: " + recept.getPriprema());
        sastojci.setText("Sastojci: " + recept.getSastojci());
        tezina.setText("Tezina:" + recept.getTezina());
        naziv.setText("Naziv:" + recept.getNaziv());

        // Floating action button
        FloatingActionButton fab = findViewById(R.id.faba);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ObicanUserReceptActivity.this, CreateKomentarActivity.class);
                intent.putExtra("Recept", recept);
                Toast.makeText(getBaseContext(), "Create komentar", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });



        listView = findViewById(R.id.komentari_list);
        // Pozivanje metode koja izlistava sve komentare
        komentarService = ServiceUtils.komentarService;

        Call call = komentarService.getKomentarRecept(recept.getRecept_id());

        call.enqueue(new Callback<List<Komentar>>() {
            @Override
            public void onResponse(Call<List<Komentar>> call, Response<List<Komentar>> response) {

                if (response.isSuccessful()) {
                    komentari = response.body();
                    listView.setAdapter(new KomentarListAdapter(ObicanUserReceptActivity.this, komentari));
                }
            }

            @Override
            public void onFailure(Call<List<Komentar>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Otvaranje selektovanog komentara
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                komentar = komentari.get(i);
                Intent intent = new Intent(ObicanUserReceptActivity.this, KomentarActivity.class);
                intent.putExtra("Komentar", komentar);
                startActivity(intent);

            }
        });
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ObicanUserReceptActivity.this);
    }

    // Metoda koja izlistava sve komentare
    public void getKomentare() {

        Call<List<Komentar>> call = komentarService.getKomentare();

        call.enqueue(new Callback<List<Komentar>>() {
            @Override
            public void onResponse(Call<List<Komentar>> call, Response<List<Komentar>> response) {

                komentari = response.body();
                KomentarListAdapter komentarListAdapter = new KomentarListAdapter(ObicanUserReceptActivity.this, komentari);
                listView.setAdapter(komentarListAdapter);
            }

            @Override
            public void onFailure(Call<List<Komentar>> call, Throwable t) {

            }
        });
    }

    //meni na toolbaru, odnosno ikonice za prelazak na ostale aktivnosti
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_item_recept_obican, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //funkcionalnost opcija iz menija gore navedenog
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                Intent in = new Intent(this, ObicanUserReceptiActivity.class);
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
