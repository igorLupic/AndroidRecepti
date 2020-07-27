package com.example.recepti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.recepti.model.Komentar;
import com.example.recepti.model.Recept;
import com.example.recepti.service.KomentarService;
import com.example.recepti.service.ServiceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminKomentarActivity extends AppCompatActivity {

    private KomentarService komentarService;
    private Komentar komentar = new Komentar();
    Recept recept = new Recept();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komentar);

        Toolbar toolbar = findViewById(R.id.komentar_toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            komentar = extras.getParcelable("Komentar");
        }

        komentarService = ServiceUtils.komentarService;

        TextView komentator = findViewById(R.id.komentator);
        TextView tekst = findViewById(R.id.tekst);

        komentator.setText("Komentator: " + komentar.getKomentator());
        tekst.setText("Tekst: " + komentar.getTekst());

    }

    //meni na toolbaru, odnosno ikonice za prelazak na ostale aktivnosti
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_item_komentar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //funkcionalnost opcija iz menija gore navedenog
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                Intent in = new Intent(this,ReceptiActivity.class);
                in.putExtra("Recept", recept);
                startActivity(in);
                return true;
            case R.id.action_delete:
                deleteKomentar();
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,ReceptiActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Metoda koja brise izabranog kontakta
    public void deleteKomentar() {
        Call<Komentar> call = komentarService.deleteKomentar(komentar.getKomentar_id());

        call.enqueue(new Callback<Komentar>() {
            @Override
            public void onResponse(Call<Komentar> call, Response<Komentar> response) {
                Intent intent = new Intent(AdminKomentarActivity.this, ReceptActivity.class);
                intent.putExtra("Recept", recept);
                startActivity(intent);
                Toast.makeText(AdminKomentarActivity.this,"Deleted", Toast.LENGTH_SHORT).show();
                // finish();
            }
            @Override
            public void onFailure(Call<Komentar> call, Throwable t) {
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
