package com.example.socialtemplate.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.socialtemplate.R;
import com.example.socialtemplate.util.Configurations;
import com.example.socialtemplate.util.HttpRequest;
import com.example.socialtemplate.util.Utils;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {
    static  int REQUEST_PERMISSION_RESULT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        Intent activityIntent = getIntent();
        int stateActivity = activityIntent.getIntExtra("CreationResult",-1);
        switch(stateActivity){
            case 0:
                Toast.makeText(getApplicationContext(), "Registration Completed" , Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(getApplicationContext(), "Unable to register" , Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(), "Unable To Connect" , Toast.LENGTH_LONG).show();
            default:
                break;
        }

        List<String> permissionToRequest = permissionsToRequest(permissions);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(permissionToRequest.size() > 0){
                requestPermissions(permissionToRequest.toArray(new String[permissionToRequest.size()]),this.REQUEST_PERMISSION_RESULT);
            }
        }
        final Button loginButton = findViewById(R.id.loginButton);
        final Context context = this;
        // setting the login button action
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(Utils.isLogged(context)){
                    Intent intent = new Intent(context,HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    EditText userLogin = findViewById(R.id.loginInput);
                    EditText userPassword = findViewById(R.id.passwordInput);
                    final String userLoginString = userLogin.getText().toString();
                    final String userPasswordString = userPassword.getText().toString();
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                HttpRequest httpRequest = new HttpRequest(Configurations.BASE_URL + "login.php", "POST", "UTF-8");
                                httpRequest.addParam("login", userLoginString);
                                httpRequest.addParam("senha", userPasswordString);
                                httpRequest.addParam("appid", Configurations.APPID);
                                InputStream streamResult = httpRequest.execute();
                                String result = Utils.inputStream2String(streamResult,"UTF-8");
                                httpRequest.finish();

                                JSONObject jsonObject = new JSONObject(result);
                                int success = jsonObject.getInt("status");
                                if(success == 0) {
                                    String token = jsonObject.getString("token");
                                    Configurations.setToken(LoginActivity.this,token);
                                    Intent intent = new Intent(context, HomeActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    Intent intent = new Intent(context, LoginActivity.class);
                                    intent.putExtra("CreationResult",2);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    //Utils.setLogin(context,true);

                    //Intent intent = new Intent(context,HomeActivity.class);
                    //startActivity(intent);
                }
            }
        });

        Button registerButton = findViewById(R.id.registerButton);

        // setting the register button action
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean hasPermission(String permission){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return ActivityCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    List<String> permissionsToRequest(List<String> wantedPermissions){
        List<String> result = new ArrayList<>();
        for( String permission : wantedPermissions){
            if(!hasPermission(permission)){
                result.add(permission);
            }
        }
        return result;
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        final List<String> permissionRejected = new ArrayList<>();
        if(requestCode == REQUEST_PERMISSION_RESULT){
            for(String permission : permissions){
                if(!hasPermission(permission)){
                    permissionRejected.add((permission));
                }
            }
        }

        if(permissionRejected.size() > 0){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(shouldShowRequestPermissionRationale(permissionRejected.get(0))){
                    new AlertDialog.Builder(this).setMessage("Para usar ChatIfes é preciso conceder essas permissoes").
                            setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                        requestPermissions(permissionRejected.toArray(new String[permissionRejected.size()]),REQUEST_PERMISSION_RESULT);
                                    }
                                }
                            }).create().show();
                }
            }
        }
    }
}
