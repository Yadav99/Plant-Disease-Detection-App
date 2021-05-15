package com.example.epaper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity{
    public static final int camera_perm_code = 101;
    public static final int camera_request_code = 102;
    public static final int gallery_request_code = 105;
    private Toolbar toolbar;
    ImageView selectedImage;
    Button cameraBtn,galleryBtn;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.bar);
        setSupportActionBar(toolbar);

        selectedImage = findViewById(R.id.image);

        cameraBtn = findViewById(R.id.camera);
        galleryBtn = findViewById(R.id.gallery);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Clicked Camera button",Toast.LENGTH_SHORT).show();
                askCameraPermission();
            }
        });

        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"gallery button is clicked",Toast.LENGTH_SHORT).show();
                Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, gallery_request_code);
            }
        });

    }

    private void askCameraPermission() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, camera_perm_code);
        }
        else{
            dispatchTakePictureIntent();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == camera_perm_code)
        {
            if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                dispatchTakePictureIntent();
            }
            else
            {
                Toast.makeText(MainActivity.this,"camera access needed",Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == camera_request_code) {
            if( resultCode == Activity.RESULT_OK) {
                File f= new File(currentPhotoPath);
                selectedImage.setImageURI(Uri.fromFile(f));
                Log.d("tag","Absolute Uri of image is"+Uri.fromFile(f));
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                sendBroadcast(mediaScanIntent);
            }
        }

        if (requestCode == gallery_request_code) {
            if( resultCode == Activity.RESULT_OK) {
                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName= "JPEG_"+timeStamp+"."+getFileExt(contentUri);
                Log.d("tag","onActivityResult: Gallery image Uri"+imageFileName);
                selectedImage.setImageURI(contentUri);
            }
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, camera_request_code);
            }
        }
    }


}

//public class MainActivity extends AppCompatActivity {
//    private Toolbar toolbar;
//    ImageView imageViewProfilePicture;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        toolbar=findViewById(R.id.bar);
//        setSupportActionBar(toolbar);
//
//        imageViewProfilePicture = findViewById(R.id.imageProfile);
//        imageViewProfilePicture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                chooseProfilePicture();
//
//            }
//        });
//
//    }
//
//    private void chooseProfilePicture() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.alert_dialog_profile_picture,null);
//        builder.setCancelable(false);
//        builder.setView(dialogView);
//
//        ImageView imageCamera = findViewById(R.id.imageCamera);
//        ImageView imageGallery = findViewById(R.id.imageGallery);
//
//        final AlertDialog alertDialogProfilePicture = builder.create();
//        alertDialogProfilePicture.show();
//
//        imageCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Capture image from camera
//                if(checkAndRequestPermissions()){
//                    takePictureFromCamera();
//                    alertDialogProfilePicture.cancel();
//                }
//
//
//            }
//        });
//
//        imageGallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //select image from gallery
//                takePictureFromGallery();
//                alertDialogProfilePicture.cancel();
//
//            }
//        });
//
//    }
//
//
//    private void takePictureFromGallery() {
//        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickPhoto,1);
//    }
//
//    private void takePictureFromCamera() {
//        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if(takePicture.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePicture,2);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode)
//        {
//            case 1:
//                if(resultCode == RESULT_OK){
//                    Uri selectedImageUri = data.getData();
//                    imageViewProfilePicture.setImageURI(selectedImageUri);
//                }
//                break;
//            case 2:
//                if(resultCode == RESULT_OK){
//                    Bundle bundle = data.getExtras();
//                    Bitmap bitmapImage = (Bitmap)bundle.get("data");
//                    imageViewProfilePicture.setImageBitmap(bitmapImage);
//                }
//                break;
//        }
//    }
//
//    private boolean checkAndRequestPermissions() {
//        if(Build.VERSION.SDK_INT >= 23) {
//            int cameraPermission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
//            if(cameraPermission == PackageManager.PERMISSION_DENIED) {
//                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},20);
//                return false;
//            }
//
//        }
//        return true;
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == 20 && grantResults[0]==PackageManager.PERMISSION_GRANTED ) {
//            takePictureFromCamera();
//        }
//        else{
//           Toast.makeText(MainActivity.this,"Permission not granted",Toast.LENGTH_SHORT).show();
//        }
//    }
//}