package com.example.recepti;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recepti.model.User;
import com.example.recepti.service.ServiceUtils;
import com.example.recepti.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private Button btnLogin;
    private SharedPreferences sharedPreferences;
    private UserService userService;
    private EditText editUsername;
    private EditText editPassword;
    public static final String Name = "name";
    public static final String MyPres = "MyPre";
    public static final String Username = "usernameKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        userService = ServiceUtils.userService;

        sharedPreferences = getSharedPreferences(MyPres, Context.MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(validate(username, password)){
                    editor.putString(Username, username);
                    editor.commit();
                    doLogin(username, password);
                }else{
                    editor.clear().commit();
                    Toast.makeText(getBaseContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean validate(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Wrong username or password", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Wrong username or password", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void doLogin(final String username, final String password){
        Call<User> call = userService.login(username, password);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if(user == null){
                    Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                    return;
                }
                if(username.equals(user.getUsername())&& password.equals(user.getPassword() )){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Name, user.getUsername());
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, ReceptiActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Greska", Toast.LENGTH_SHORT).show();
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
