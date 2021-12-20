package memeyapp.memey;

import android.Manifest;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.graphics.drawable.Drawable;

import android.os.Build;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;

import com.bumptech.glide.request.transition.Transition;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static android.service.controls.ControlsProviderService.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class image extends DialogFragment {
    Bundle bundle;
    Button backButton;
    Button download;



    public image() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_image, container, false);
        final ConstraintLayout background=getActivity().findViewById(R.id.container);
        background.setVisibility(View.INVISIBLE);
         bundle = this.getArguments();
         backButton = root.findViewById(R.id.close);
         download=root.findViewById(R.id.download);
         final String link = bundle.getString("link");




       if (container != null) {
            container.removeAllViews();
        }
        backButton.setOnClickListener(v -> {
            background.setVisibility(View.VISIBLE);
            dismiss();
        });

      download.setOnClickListener(v -> {
           if(isStoragePermissionGranted()) {
               Glide.with(this)
                       .asBitmap()
                       .load(link)
                       .into(new CustomTarget<Bitmap>() {
                           @Override
                           public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                               MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), resource, "mm"/* new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date())*/, "image");

                               Toast.makeText(getActivity(), "image downloaded in gallery", Toast.LENGTH_SHORT).show(); }

                           @Override
                           public void onLoadCleared(@Nullable Drawable placeholder) {
                           }
                       });

           }
           else {
               Toast.makeText(getActivity(), "something go wrong try again ..", Toast.LENGTH_SHORT).show();
           }

      });
       ImageView image=root.findViewById(R.id.image_screen);

        if (bundle != null) {

            Glide.with(this)
                    .load(link)
                    .into(image);
        }
        else {
            Glide.with(this)
                    .load(R.drawable.loading)
                    .into(image);

        }

        return  root;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }



}
