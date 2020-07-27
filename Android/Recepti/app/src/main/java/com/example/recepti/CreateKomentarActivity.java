package com.example.recepti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.recepti.model.Komentar;
import com.example.recepti.model.KomentarDTO;
import com.example.recepti.model.Recept;
import com.example.recepti.service.KomentarService;
import com.example.recepti.service.ServiceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateKomentarActivity extends AppCompatActivity {

    private EditText komentator;
    private EditText tekst;

    private ImageView btnAdd;
    private ImageView btnCancel;

    private KomentarService komentarService;

   // Komentar komentar = new Komentar();
    Recept recept = new Recept();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_komentar);
        initView();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            recept = extras.getParcelable("Recept");
        }



    }

    private void initView() {
        komentator = findViewById(R.id.komentator);
        tekst = findViewById(R.id.tekst);
        komentarService = ServiceUtils.komentarService;

        btnAdd = findViewById(R.id.button_one);
        btnCancel = findViewById(R.id.button_two);


        btnAdd.setImageDrawable(getResources().getDrawable(R.drawable.ic_save));
        btnCancel.setImageDrawable(getResources().getDrawable(R.drawable.ic_back));

        btnAdd.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Komentar komentar = new Komentar();
                KomentarDTO komentarDTO = new KomentarDTO();
                if (komentator.getText().toString().isEmpty() || tekst.toString().isEmpty()) {
                    Toast.makeText(CreateKomentarActivity.this, "Sva polja moraju biti popunjena", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    komentar.setKomentator(komentator.getText().toString());
                    komentar.setTekst(tekst.getText().toString());

                    komentar.setKomentarRecept(recept);

                    komentarDTO.setKomentar_id(komentar.getKomentar_id());
                    komentarDTO.setKomentator(komentator.getText().toString());
                    komentarDTO.setTekst(tekst.getText().toString());
                    komentarDTO.setRecept_id(recept.getRecept_id());

                }


                Call<Komentar> call = komentarService.addKomentarDTO(komentarDTO);
                call.enqueue(new Callback<Komentar>() {
                    @Override
                    public void onResponse(Call<Komentar> call, Response<Komentar> response) {
                        Toast.makeText(CreateKomentarActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CreateKomentarActivity.this, ObicanUserReceptActivity.class);
                        i.putExtra("Recept", recept);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<Komentar> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateKomentarActivity.this, ObicanUserReceptiActivity.class);
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
