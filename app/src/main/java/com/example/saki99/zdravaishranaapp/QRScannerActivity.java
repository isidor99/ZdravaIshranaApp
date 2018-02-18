package com.example.saki99.zdravaishranaapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

/**
 * Created by Saki99 on 13.2.2018..
 */

public class QRScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    public static final int RESULT_CODE = 1;
    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            
            if (checkPermission()) {

                Toast.makeText(QRScannerActivity.this, "Dozvoljeno!", Toast.LENGTH_SHORT).show();
            
            } else {
                requestPermission();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (scannerView == null) {

                scannerView = new ZXingScannerView(this);
                setContentView(scannerView);
            }

            scannerView.setResultHandler(this);
            scannerView.startCamera();

        } else {

            requestPermission();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }


    @Override
    public void handleResult(Result result) {

        String scanResult = result.getText();
    }

    private boolean checkPermission() {

        return (ContextCompat.checkSelfPermission(QRScannerActivity.this, CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(QRScannerActivity.this, new String[] { CAMERA }, REQUEST_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case REQUEST_CAMERA:

                if (grantResults.length > 0) {

                    boolean cameraAcepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAcepted) {

                        Toast.makeText(QRScannerActivity.this, "Dozvoljeno!", Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(QRScannerActivity.this, "Odbijen pristup kameri!", Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {

                                displayAlertMessage("Morate omoguciti dozvolu za kameru",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                                }

                                            }
                                        });
                            }
                        }
                    }

                    break;
                }
        }
    }

    private void displayAlertMessage(String message, DialogInterface.OnClickListener listener) {

        new AlertDialog.Builder(QRScannerActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", listener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
