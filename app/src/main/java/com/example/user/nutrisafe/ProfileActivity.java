package com.example.user.nutrisafe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datatypes.User;

public class ProfileActivity extends AppCompatActivity {

    private ImageView imagePlace;
    private static Bitmap mImageBitmap;
    private static String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE=1;
    private static File photoFile;

    User user;
    List<String> allergies;

    TextView nameID;
    EditText allergyID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //display user's information
        Intent i=getIntent();
        user = (User) i.getSerializableExtra("user");
        allergies=new ArrayList<>();

        //get id of items
        nameID = findViewById(R.id.name_tv2);
        nameID.setText(user.getName().toUpperCase());
        allergyID = findViewById(R.id.allergy_et);
        imagePlace = findViewById(R.id.imageView);
        Log.d("ProfileActivity ","allergies are "+user.getAllergies());

        //TODO:
        //capture the image
        //using the displayCaptureImageIntent click listener for the camera button
        //load the captured image
        //display captured image

        //send it to the python script

        //get text from the python script

    }


    public void addAllergy(View v)
    {
        String allergy = allergyID.getText().toString();
        allergies.add(allergy);
        allergyID.setText("");
    }


    /**
     * executed when the user clicks the camera icon
     */
    public void displayCaptureImageIntent(View v)
    {
        user.setAllergies(allergies);
        Log.d("ProfileActivity ","displayCaptureImageIntent allergies are "+user.getAllergies());
        dispatchTakePictureIntent();
    }

    /**
     * this intent is to get the request to access camera and take a picture
     */
    private void dispatchTakePictureIntent()
    {
        Intent captureImageIntent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if(captureImageIntent.resolveActivity(getPackageManager()) != null)
        {
            try
            {photoFile=createImageFile();}
            catch(IOException ioException)
            {Log.i("ProfileActivity ","io exception in creating image file");}
            if(photoFile !=null)
            {
                captureImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(captureImageIntent,REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    /**
     *
     *we are just creating an image file in which we will save our image later
     */

    private File createImageFile() throws IOException
    {
        Log.d("ProfileActivity ","entered create image file");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName= "JPEG_"+timeStamp+"_";
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Log.d("ProfileActivity ","image file name is "+imageFileName);
        File image=File.createTempFile(imageFileName,".jpg",storageDirectory);

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath="file:"+image.getAbsolutePath();
        Log.d("ProfileActivity","create image file photo path is "+mCurrentPhotoPath);
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("ProfileActivity ","onActivityResult request code is "+requestCode);
        Log.d("ProfileActivity ","onActivityResult result code is "+resultCode);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            try
            {
                Log.d("ProfileActivity ","photo file path using mCurrent is "+mCurrentPhotoPath);
                mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));

                //scale the image to be less than 2048x2048

//                mImageBitmap = new BitmapDrawable(getApplicationContext().getResources() , mCurrentPhotoPath).getBitmap();
//                int nh = (int) ( mImageBitmap.getHeight() * (512.0 / mImageBitmap.getWidth()) );
//                Bitmap scaledImage = Bitmap.createScaledBitmap(mImageBitmap, 512, nh, true);

                imagePlace.setImageBitmap(mImageBitmap);
            }
            catch (IOException ioEx2)
            {ioEx2.printStackTrace();}
        }
    }
}






