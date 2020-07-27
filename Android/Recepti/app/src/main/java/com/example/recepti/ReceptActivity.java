package com.example.recepti;

import android.content.Context;
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

import com.example.recepti.adapter.KomentarListAdapter;
import com.example.recepti.adapter.ReceptListAdapter;
import com.example.recepti.model.Komentar;
import com.example.recepti.model.Recept;
import com.example.recepti.service.KomentarService;
import com.example.recepti.service.ReceptService;
import com.example.recepti.service.ServiceUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceptActivity extends AppCompatActivity {


    private ImageView btnAdd;
    private ImageView btnCancel;
    private ReceptService receptService;
    private Recept recept = new Recept();
    private SharedPreferences sharedPreferences;



    private ListView listView;
    private ReceptListAdapter receptListAdapter;
    private KomentarService komentarService;
    private List<Komentar> komentari = new ArrayList<>();
    Komentar  komentar = new Komentar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recept);


        Toolbar toolbar = findViewById(R.id.recept_toolbar);
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

        listView = findViewById(R.id.komentari_list);


        // Pozivanje metode koja izlistava sve komentare
        komentarService = ServiceUtils.komentarService;

        Call call = komentarService.getKomentarRecept(recept.getRecept_id());

        call.enqueue(new Callback<List<Komentar>>() {
            @Override
            public void onResponse(Call<List<Komentar>> call, Response<List<Komentar>> response) {

                if (response.isSuccessful()) {
                    komentari = response.body();
                    listView.setAdapter(new KomentarListAdapter(ReceptActivity.this, komentari));
                }
            }

            @Override
            public void onFailure(Call<List<Komentar>> call, Throwable t) {
                Toast.makeText(ReceptActivity.this, "nece", Toast.LENGTH_SHORT).show();
            }
        });

        // Otvaranje selektovanog komentara
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                komentar = komentari.get(i);
                Intent intent = new Intent(ReceptActivity.this, AdminKomentarActivity.class);
                intent.putExtra("Komentar", komentar);
                startActivity(intent);

            }
        });
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ReceptActivity.this);

    }

    // Metoda koja izlistava sve komentare
    public void getKomentare() {

            Call<List<Komentar>> call = komentarService.getKomentare();

            call.enqueue(new Callback<List<Komentar>>() {
                @Override
                public void onResponse(Call<List<Komentar>> call, Response<List<Komentar>> response) {

                    komentari = response.body();
                    KomentarListAdapter komentarListAdapter = new KomentarListAdapter(ReceptActivity.this, komentari);
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
        inflater.inflate(R.menu.activity_item_recept, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //funkcionalnost opcija iz menija gore navedenog
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                Intent in = new Intent(this, ReceptiActivity.class);
                startActivity(in);
                return true;
            case R.id.action_delete:
                deleteRecept();
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,ReceptiActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_update:
                updateRecept();
               // Intent intenti = new Intent(this, ReceptiActivity.class);
                //startActivity(intenti);
                Toast.makeText(this, "Update recept", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    // Metoda koja brise izabrani recept
    public void deleteRecept() {
        Call<Recept> call = receptService.deleteRecept(recept.getRecept_id());

        call.enqueue(new Callback<Recept>() {
            @Override
            public void onResponse(Call<Recept> call, Response<Recept> response) {
                Intent intent = new Intent(ReceptActivity.this, ReceptiActivity.class);
                startActivity(intent);
                Toast.makeText(ReceptActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
               // finish();
            }

            @Override
            public void onFailure(Call<Recept> call, Throwable t) {
            }
        });
    }
    public void updateRecept(){

        setContentView(R.layout.activity_update_recept);


        Toolbar toolbar = findViewById(R.id.toolbar);

        EditText vreme = findViewById(R.id.vreme_edit);
        EditText priprema = findViewById(R.id.priprema_edit);
        EditText sastojci = findViewById(R.id.sastojci_edit);
        EditText tezina = findViewById(R.id.tezina_edit);
        EditText naziv = findViewById(R.id.naziv_edit);


        vreme.setText(String.valueOf(recept.getVreme()));
        priprema.setText(recept.getPriprema().toString());
        sastojci.setText(recept.getSastojci().toString());
        tezina.setText(recept.getTezina().toString());
        naziv.setText(recept.getNaziv().toString());

        recept.setVreme(Integer.parseInt(vreme.getText().toString()));
        recept.setPriprema(priprema.getText().toString());
        recept.setSastojci(sastojci.getText().toString());
        recept.setTezina(tezina.getText().toString());
        recept.setNaziv(naziv.getText().toString());

        btnAdd = findViewById(R.id.button_one);
        btnCancel = findViewById(R.id.button_two);


        btnAdd.setImageDrawable(getResources().getDrawable(R.drawable.ic_save));
        btnCancel.setImageDrawable(getResources().getDrawable(R.drawable.ic_back));

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText vreme = findViewById(R.id.vreme_edit);
                EditText priprema = findViewById(R.id.priprema_edit);
                EditText sastojci = findViewById(R.id.sastojci_edit);
                EditText tezina = findViewById(R.id.tezina_edit);
                EditText naziv = findViewById(R.id.naziv_edit);

                recept.setVreme(Integer.parseInt(vreme.getText().toString()));
                recept.setPriprema(priprema.getText().toString());
                recept.setSastojci(sastojci.getText().toString());
                recept.setTezina(tezina.getText().toString());
                recept.setNaziv(naziv.getText().toString());


                Call<Recept> call = receptService.updateRecept(recept);
                call.enqueue(new Callback<Recept>() {
                    @Override
                    public void onResponse(Call<Recept> call, Response<Recept> response) {

                        Toast.makeText(ReceptActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ReceptActivity.this, ReceptiActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<Recept> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReceptActivity.this, ReceptiActivity.class);
                startActivity(i);

            }
        });



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
