package com.example.recepti;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.recepti.model.Recept;
import com.example.recepti.service.ReceptService;
import com.example.recepti.service.ServiceUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateReceptActivity extends AppCompatActivity {


    private EditText vreme;
    private EditText sastojci;
    private EditText priprema;
    private EditText tezina;
    private EditText naziv;

    private ImageView btnAdd;
    private ImageView btnCancel;

    private ReceptService receptService;
    Recept recept = new Recept();

    private final String CHANNEL_ID = "my_channel";
    private final int NOTIFICATION_ID = 001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recept);
        initView();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initView() {
        vreme = findViewById(R.id.vreme);
        sastojci = findViewById(R.id.sastojci);
        priprema = findViewById(R.id.priprema);
        tezina = findViewById(R.id.tezina);
        naziv = findViewById(R.id.naziv);
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
                    Toast.makeText(CreateReceptActivity.this, "Sva polja moraju biti popunjena", Toast.LENGTH_SHORT).show();
                    return;
                }else if(vreme.getText().toString().isEmpty() ){
                    Toast.makeText(CreateReceptActivity.this, "Vreme mora biti broj!", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    recept.setVreme(Integer.parseInt(vreme.getText().toString()));
                    recept.setSastojci(sastojci.getText().toString());
                    recept.setPriprema(priprema.getText().toString());
                    recept.setTezina(tezina.getText().toString());
                    recept.setNaziv(naziv.getText().toString());
                }


                Call<Recept> call = receptService.addRecept(recept);
                call.enqueue(new Callback<Recept>() {
                    @Override
                    public void onResponse(Call<Recept> call, Response<Recept> response) {

                        Recept recepti1 = response.body();
                        if (recepti1 == null) {
                            Toast.makeText(CreateReceptActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        } else {


                            // notifikacije
                            final NotificationCompat.Builder builder = new NotificationCompat.Builder(CreateReceptActivity.this, CHANNEL_ID);

                                    Intent intent = new Intent(CreateReceptActivity.this, ReceptActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("Recept",recepti1);
                                    intent.removeExtra("Recept");
                                    intent.putExtras(bundle);

                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    final PendingIntent intentPending = PendingIntent.getActivity(CreateReceptActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                                    builder.setSmallIcon(R.drawable.notification);
                                    builder.setContentTitle(recepti1.getNaziv());


                                    builder.setContentText(recepti1.getTezina());
                                    builder.setPriority(NotificationCompat.PRIORITY_HIGH);
                                    builder.setContentIntent(intentPending);
                                    builder.setAutoCancel(true);
                                    builder.build().flags |= Notification.FLAG_AUTO_CANCEL;

                                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(CreateReceptActivity.this);
                                    notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
                            }

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            CharSequence name = "Recept notification";
                            String description = "Include all recept notifications";

                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);

                            notificationChannel.setDescription(description);

                            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            notificationManager.createNotificationChannel(notificationChannel);
                        }

                        Toast.makeText(CreateReceptActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CreateReceptActivity.this, ReceptiActivity.class);
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
                Intent i = new Intent(CreateReceptActivity.this, ReceptiActivity.class);
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
