package com.example.socialtemplate.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.DisplayMetrics;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

public class Utils {
    /*
        Verifica se a app possui determinadas permissoes

        context -> contexto atual, por exemplo a instancia da activity
        permissions -> lista de permissoes a serem verificadas

        Retorna true se a app possui todas as permissões e false se a app
        não possui pelo menos uma das permissões
     */
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
        Carrega uma imagem e a redimensiona.

        imageLocation -> caminho absoluto do arquivo de imagem. Não é um URI.
        newWidth -> largura da imagem carregada
        newHeight -> altura da imagem carregada

        retorna um Bitmap com a imagem carregada e redimensionada
     */
    public static Bitmap getBitmap(String imageLocation, int newWidth, int newHeight) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageLocation, bmOptions);

        int photoW = bmOptions.outWidth;
        int phothoH = bmOptions.outHeight;

        int scaleFactor = Math.max(photoW/newWidth, phothoH/newHeight);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeFile(imageLocation, bmOptions);
    }

    /*
        Carrega uma imagem e a redimensiona.

        context -> contexto de aplicacao ou activity
        imageLocation -> Uri para a imagem
        newWidth -> largura da imagem carregada
        newHeight -> altura da imagem carregada

        retorna um Bitmap com a imagem carregada e redimensionada
    */
    public static Bitmap getBitmap(Context context, Uri imageLocation, int newWidth, int newHeight) throws FileNotFoundException {

        InputStream is = context.getContentResolver().openInputStream(imageLocation);

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(photoW/newWidth, photoH/newHeight);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        is = context.getContentResolver().openInputStream(imageLocation);
        return BitmapFactory.decodeStream(is, null, bmOptions);
    }

    /*
        Calcula o numero de colunas / itens que podem ser colocados em uma
        linha de um RecycleView. Essa funcao deve ser usada quando os itens
        sao dispostos em um RecycleView na forma de grid (GridLayoutManager)

        context -> contexto atual, por exemplo a instancia da activity
        columnWidthDp -> tamanho da largura de um item em DP

        Retorna o numero de colunas / itens de uma linha do RecycleView
     */
    public static int calculateNoOfColums(Context context, float columnWidthDp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels;
        int noOfColums = (int) (screenWidthDp / columnWidthDp + 0.5);
        return noOfColums;
    }

    /* Converte um InputStream para uma String

        is -> InputStream a ser convertido
        charset -> char set a ser usado para o encode da String

        Retorna um String resultado da conversão do InputStream

    */
    public static String inputStream2String(InputStream is, String charset) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                is, charset), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        return sb.toString();
    }

    /* Converte um InputStream para um Bitmap

		is -> InputStream a ser convertido

		Retorna um Bitmap resultado da conversão do InputStream

	*/
    public static Bitmap inputStream2Bitmap(InputStream is) throws IOException {
        try {
            return BitmapFactory.decodeStream(is);
        } finally {
            is.close();
        }
    }

    public static boolean isLogged(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("myprefs", 0);
        return mPrefs.getBoolean("logged", false);
    }

    public static void setLogin(Context context, boolean value) {
        SharedPreferences mPrefs = context.getSharedPreferences("myprefs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean("logged", value).commit();
    }

    public static long dateToLong(String dateString){
        SimpleDateFormat pattern  = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date dateResult = pattern.parse(dateString);
            long dateLong = dateResult.getTime();
            return dateLong;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    static public File createTempFile(InputStream is, Context context) throws IOException {
        File f = File.createTempFile("tempPic", "temp", context.getCacheDir());
        FileOutputStream outputStream = new FileOutputStream(f);
        int read;
        byte[] bytes = new byte[1024];

        while ((read = is.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        return f;
    }
}