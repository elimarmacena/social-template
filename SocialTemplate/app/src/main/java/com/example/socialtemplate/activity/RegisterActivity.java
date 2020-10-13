package com.example.socialtemplate.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.socialtemplate.R;
import com.example.socialtemplate.util.Configurations;
import com.example.socialtemplate.util.HttpRequest;
import com.example.socialtemplate.util.Utils;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {
    final int PICK_IMAGE_REQUEST = 1;
    private Uri pickedPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Context context = this;
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setEnabled(false);
        ImageView userPic = findViewById(R.id.userPic);
        userPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickUserPic();
            }
        });
        // setting the register button action
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String token = Configurations.getToken(RegisterActivity.this);
                final Uri finalUriUserPic = pickedPic;
                EditText userLogin = findViewById(R.id.userLogin);
                final String userLoginString = userLogin.getText().toString();
                //Only letters and numbers allowed
                if(userLoginString.matches("^[a-zA-Z0-9]+$")){
                    EditText userPassword = findViewById(R.id.userPassword);
                    final String userPasswordString = userPassword.getText().toString();
                    EditText userRePassword = findViewById(R.id.userRePassword);
                    String userRePasswordString = userRePassword.getText().toString();
                    if(userPasswordString.equals(userRePasswordString)){
                        EditText userFullname = findViewById(R.id.userName);
                        final String userFullnameString = userFullname.getText().toString();
                        EditText userBirthdate = findViewById(R.id.userBirthDate);
                        final String userBirthdateString = userBirthdate.getText().toString();
                        EditText userCity = findViewById(R.id.userCity);
                        final String userCityString = userCity.getText().toString();
                        ExecutorService executorService = Executors.newSingleThreadExecutor();
                        executorService.execute(new Runnable(){
                            @Override
                            public void run() {
                                try{
                                    HttpRequest httpRequest = new HttpRequest(Configurations.BASE_URL + "cadastra_usuario.php","POST","UTF-8");
                                    httpRequest.addParam("login",userLoginString);
                                    httpRequest.addParam("senha",userPasswordString);
                                    httpRequest.addParam("nome",userFullnameString);
                                    httpRequest.addParam("cidade",userCityString);
                                    httpRequest.addParam("data_nascimento", ""+Utils.dateToLong(userBirthdateString));
                                    InputStream inputStream = getContentResolver().openInputStream(finalUriUserPic);
                                    File tempFile = Utils.createTempFile(inputStream,RegisterActivity.this);
                                    httpRequest.addFile("foto",tempFile);
                                    InputStream streamResult = httpRequest.execute();
                                    String result = Utils.inputStream2String(streamResult,"UTF-8");
                                    httpRequest.finish();

                                    JSONObject jsonObject = new JSONObject(result);
                                    int success = jsonObject.getInt("status");
                                    if(success == 0){
                                        Intent intent = new Intent(context, LoginActivity.class);
                                        intent.putExtra("CreationResult",0);
                                        startActivity(intent);
                                    }
                                    else{
                                        Intent intent = new Intent(context, LoginActivity.class);
                                        intent.putExtra("CreationResult",1);
                                        startActivity(intent);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"The passwords are different",Toast.LENGTH_LONG).show();
                        userPassword.setText("");
                        userRePassword.setText("");
                    }

                }
                else{
                    Toast.makeText(RegisterActivity.this,"The username must have only letters and numbers",Toast.LENGTH_LONG).show();
                    userLogin.setText("");
                }
            }
        });
    }

    private void assembleProfile(Uri uriUserPic){
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setEnabled(true);
        this.pickedPic = uriUserPic;
    }
    private void pickUserPic(){
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/");
        if(i.resolveActivity(getPackageManager()) != null){
            startActivityForResult(i, this.PICK_IMAGE_REQUEST);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK){
            ImageView userPic = findViewById(R.id.userPic);
            Uri imageUri = data.getData();
            try {
                userPic.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assembleProfile(imageUri);
        }
    }

}
