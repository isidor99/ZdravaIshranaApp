package com.example.saki99.zdravaishranaapp.Activityes;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.saki99.zdravaishranaapp.DBHelper;
import com.example.saki99.zdravaishranaapp.POJO.Recept;
import com.example.saki99.zdravaishranaapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StartingActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        dbHelper = new DBHelper(StartingActivity.this);

        writeToLocalDatabase();

        openNextActivity();
    }

    private void writeToLocalDatabase() {
        Bitmap bit1 = BitmapFactory.decodeResource(getResources(), R.drawable.sunka);
        Bitmap bit2 = BitmapFactory.decodeResource(getResources(), R.drawable.jagoda);
        Bitmap bit3 = BitmapFactory.decodeResource(getResources(), R.drawable.banana);
        Bitmap bit4 = BitmapFactory.decodeResource(getResources(), R.drawable.riba);
        Bitmap bit5 = BitmapFactory.decodeResource(getResources(), R.drawable.musaka);

        dbHelper.addRecept(new Recept(
                "Sunka",
                "Socna, ukusna i mnogo mesnata!",
                "123",
                "879",
                "20",
                "sunka",
                saveToInternalStorage(bit1, "sunka"),
                true));

        dbHelper.addRecept(new Recept(
                "Soothie od jagode",
                "Jako zdravo i osvjezavajuce pice",
                "88",
                "0",
                "133",
                "jagoda",
                saveToInternalStorage(bit2, "jagoda"),
                false
        ));

        dbHelper.addRecept(new Recept(
                "Smoothie od banane",
                "Osvjezavajuce bice od banane za sve uzraste",
                "150",
                "11",
                "167",
                "banana",
                saveToInternalStorage(bit3, "banana"),
                true
        ));

        dbHelper.addRecept(new Recept(
                "Riba",
                "Zdrava riba bogata sa omega 3",
                "788",
                "240",
                "19",
                "riba",
                saveToInternalStorage(bit4, "riba"),
                false
        ));

        dbHelper.addRecept(new Recept(
                "Musaka",
                "Mocna rucak sa krompirom i mesom",
                "470",
                "1200",
                "99",
                "musaka",
                saveToInternalStorage(bit5, "musaka"),
                false
        ));
    }

    private String saveToInternalStorage(Bitmap bitmapImage, String ime){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());

        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

        // Create imageDir
        File myPath = new File(directory,ime + ".jpg");

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(myPath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                fos.close();

            } catch (IOException e) {

                e.printStackTrace();

            }
        }

        return directory.getAbsolutePath();
    }

    private void openNextActivity() {

        Intent intent = new Intent(StartingActivity.this, TabbedActivity.class);
        startActivity(intent);

    }
}
