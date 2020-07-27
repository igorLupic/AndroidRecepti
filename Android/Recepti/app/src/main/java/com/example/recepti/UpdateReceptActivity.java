package com.example.recepti;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.recepti.model.Recept;
import com.example.recepti.service.ReceptService;
import com.example.recepti.service.ServiceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateReceptActivity extends AppCompatActivity {

    Recept recept = new Recept();

    private EditText vreme;
    private EditText sastojci;
    private EditText priprema;
    private EditText tezina;
    private EditText naziv;

    private ImageView btnAdd;
    private ImageView btnCancel;

    private ReceptService receptService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recept);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            recept = extras.getParcelable("Recept");
        }


        EditText vreme = findViewById(R.id.vreme_edit);
        EditText sastojci = findViewById(R.id.sastojci_edit);
        EditText priprema = findViewById(R.id.priprema_edit);
        EditText tezina = findViewById(R.id.tezina_edit);
        EditText naziv = findViewById(R.id.naziv_edit);

        vreme.setText(String.valueOf(recept.getVreme()));
        priprema.setText(recept.getPriprema().toString());
        sastojci.setText(recept.getSastojci().toString());
        tezina.setText(recept.getTezina().toString());
        naziv.setText(recept.getNaziv().toString());

        btnAdd = findViewById(R.id.button_one);
        btnCancel = findViewById(R.id.button_two);


        btnAdd.setImageDrawable(getResources().getDrawable(R.drawable.ic_save));
        btnCancel.setImageDrawable(getResources().getDrawable(R.drawable.ic_back));

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Call<Recept> call = receptService.updateRecept(recept);
                call.enqueue(new Callback<Recept>() {
                    @Override
                    public void onResponse(Call<Recept> call, Response<Recept> response) {
                        Toast.makeText(UpdateReceptActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(UpdateReceptActivity.this, ReceptiActivity.class);
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
                Intent i = new Intent(UpdateReceptActivity.this, ReceptiActivity.class);
                startActivity(i);

            }
        });



    }
/*
    private void initView() {
        vreme = findViewById(R.id.vreme_edit);
        sastojci = findViewById(R.id.sastojci_edit);
        priprema = findViewById(R.id.priprema_edit);
        tezina = findViewById(R.id.tezina_edit);
        naziv = findViewById(R.id.naziv_edit);

          vreme.setText("Vreme: " + recept.getVreme());
        priprema.setText("Priprema: " + recept.getPriprema());
        sastojci.setText("Sastojci: " + recept.getSastojci());
        tezina.setText("Tezina:" + recept.getTezina());
        naziv.setText("Naziv:" + recept.getNaziv());

        receptService = ServiceUtils.receptService;

        btnAdd = findViewById(R.id.button_one);
        btnCancel = findViewById(R.id.button_two);


        btnAdd.setImageDrawable(getResources().getDrawable(R.drawable.ic_save));
        btnCancel.setImageDrawable(getResources().getDrawable(R.drawable.ic_back));

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Recept recept = new Recept();

                if (sastojci.getText().toString().isEmpty() || priprema.toString().isEmpty()  || tezina.toString().isEmpty() || naziv.toString().isEmpty()) {
                    Toast.makeText(UpdateReceptActivity.this, "Sva polja moraju biti popunjena", Toast.LENGTH_SHORT).show();
                    return;
                }else if(vreme.getText().toString().isEmpty() ){
                    Toast.makeText(UpdateReceptActivity.this, "Vreme mora biti broj!", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    recept.setVreme(Integer.parseInt(vreme.getText().toString()));
                    recept.setSastojci(sastojci.getText().toString());
                    recept.setPriprema(priprema.getText().toString());
                    recept.setTezina(tezina.getText().toString());
                    recept.setNaziv(naziv.getText().toString());
                }


                Call<Recept> call = receptService.updateRecept(recept.getRecept_id());
                call.enqueue(new Callback<Recept>() {
                    @Override
                    public void onResponse(Call<Recept> call, Response<Recept> response) {
                        Toast.makeText(UpdateReceptActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(UpdateReceptActivity.this, ReceptiActivity.class);
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
                Intent i = new Intent(UpdateReceptActivity.this, ReceptiActivity.class);
                startActivity(i);

            }
        });

    }



 */



    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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
