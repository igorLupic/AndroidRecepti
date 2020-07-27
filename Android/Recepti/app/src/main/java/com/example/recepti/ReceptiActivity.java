package com.example.recepti;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.PreferenceManager;

import com.example.recepti.adapter.DrawerListAdapter;
import com.example.recepti.adapter.ReceptListAdapter;
import com.example.recepti.model.NavItem;
import com.example.recepti.model.Recept;
import com.example.recepti.service.ReceptService;
import com.example.recepti.service.ServiceUtils;
import com.example.recepti.service.UserService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceptiActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerPane;
    private ListView mDrawerList;
    private AppBarLayout appBarLayout;
    private CharSequence mTitle;
    private ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private List<Recept> recepti = new ArrayList<>();
    private Recept recept = new Recept();
    private SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    private ReceptService receptService;
    private ListView listView;
    private String userPref;
    private UserService userService;

    private boolean sortRecepti;

    private ReceptListAdapter receptListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepti);

        prepareMenu(mNavItems);

        mTitle = getTitle();
        appBarLayout = findViewById(R.id.appbar);

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerList = findViewById(R.id.navList);
        listView = findViewById(R.id.recepti_list);


        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReceptiActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        mDrawerPane = findViewById(R.id.drawerPane);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setOnItemClickListener(new ReceptiActivity.DrawerItemClickListener());
        mDrawerList.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.recept_toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setHomeButtonEnabled(true);
        }
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        // Ispisivanje ulogovanog korisnika u draweru
        TextView userText = findViewById(R.id.userName);
        sharedPreferences = getSharedPreferences(LoginActivity.MyPres, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(LoginActivity.Username)) {
            userText.setText(sharedPreferences.getString(LoginActivity.Name, ""));
        }
        userPref = sharedPreferences.getString(LoginActivity.Username, "");

        receptService = ServiceUtils.receptService;
        userService = ServiceUtils.userService;

        // Floating action button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReceptiActivity.this, CreateReceptActivity.class);
                Toast.makeText(getBaseContext(), "Create recept", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


        // Pozivanje metode koja izlistava sve recepte
        receptService = ServiceUtils.receptService;
        Call call = receptService.getRecepte();
        call.enqueue(new Callback<List<Recept>>() {
            @Override
            public void onResponse(Call<List<Recept>> call, Response<List<Recept>> response) {
                if (response.isSuccessful()) {
                    recepti = response.body();
                    listView.setAdapter(new ReceptListAdapter(ReceptiActivity.this, recepti));
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
                Intent intent = new Intent(ReceptiActivity.this, ReceptActivity.class);
                intent.putExtra("Recept", recept);
                startActivity(intent);

            }
        });
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ReceptiActivity.this);
        consultPreferences();
    }



    // Metoda koja izlistava sve recepte
    public void getRecept() {
        Call<List<Recept>> call = receptService.getRecepte();

        call.enqueue(new Callback<List<Recept>>() {
            @Override
            public void onResponse(Call<List<Recept>> call, Response<List<Recept>> response) {
                recepti = response.body();
                ReceptListAdapter receptListAdapter = new ReceptListAdapter(ReceptiActivity.this, recepti);
                listView.setAdapter(receptListAdapter);
            }

            @Override
            public void onFailure(Call<List<Recept>> call, Throwable t) {

            }
        });
    }

    // Ikonice i naslovi u navigation drawer-u
    private void prepareMenu(ArrayList<NavItem> mNavItems) {
        mNavItems.add(new NavItem("Logout", null, R.drawable.ic_icon));
        mNavItems.add(new NavItem("Settings", null, R.drawable.ic_settings));

    }

    // Listener za navigation drawer
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItemFromDrawer(position);
        }
    }

    // Prelazak na druge aktivnosti klikom na odredjenu poziciju odnosno stavku u draweru
    private void selectItemFromDrawer(int position) {
        if (position == 0) {
            Intent ite = new Intent(this, ObicanUserReceptiActivity.class);
            sharedPreferences.edit().clear().apply();
            startActivity(ite);
            finish();
        }else if (position == 1) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
        }
        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).getmTitle());
        mDrawerLayout.closeDrawer(mDrawerPane);
    }


    private void consultPreferences() {
        sortRecepti = sharedPreferences.getBoolean(getString(R.string.pref_sort_recepti_by_tezina_key_list_asc), false);
        if (sortRecepti) {
            sortTezinaAsc();
        } else {
            sortTezinaDesc();
        }

    }

    private void sortTezinaAsc() {
        Call<List<Recept>> callMessage = receptService.sortRecepteAsc();

        callMessage.enqueue(new Callback<List<Recept>>() {
            @Override
            public void onResponse(Call<List<Recept>> call, Response<List<Recept>> response) {
                recepti = response.body();

                ReceptListAdapter adapter = new ReceptListAdapter(ReceptiActivity.this, recepti);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Recept>> call, Throwable t) {

            }
        });
    }

    private void sortTezinaDesc() {
        Call<List<Recept>> callMessage = receptService.sortRecepteDesc();

        callMessage.enqueue(new Callback<List<Recept>>() {
            @Override
            public void onResponse(Call<List<Recept>> call, Response<List<Recept>> response) {
                recepti = response.body();

                ReceptListAdapter adapter = new ReceptListAdapter(ReceptiActivity.this, recepti);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Recept>> call, Throwable t) {

            }
        });
    }

    public List<Recept> sortedListOfMessages(List<Recept> messages, boolean isSortAscending) {
        List<Recept> sortedListOfRecepti = recepti;
        if (isSortAscending) {
            Collections.sort(recepti, new Comparator<Recept>() {
                public int compare(Recept o1, Recept o2) {
                    return o2.getTezina().compareTo(o1.getTezina());
                }
            });
        } else {
            Collections.sort(recepti, new Comparator<Recept>() {
                public int compare(Recept o1, Recept o2) {
                    return o1.getTezina().compareTo(o2.getTezina());
                }
            });
        }


        return sortedListOfRecepti;
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
