package com.example.iphone_contects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.iphone_contects.Database.ContactViewModel;
import com.example.iphone_contects.Fragments.ContactFragment;
import com.example.iphone_contects.Fragments.FavoritesFragment;
import com.example.iphone_contects.Fragments.KeypadFragment;
import com.example.iphone_contects.Fragments.RecentFragment;
import com.example.iphone_contects.Fragments.VoicemailFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static ContactViewModel mainViewModel;
    public static boolean main_permition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkpermission()) {
            } else {
                requestpermissoin();
            }
        }

        getSupportActionBar().hide();

//        Hide the Status Bar
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
//                , WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //fragment management
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("main_fram", "fav");
        String main_frame = sp.getString("main_fram", "defult");
        switch (main_frame) {
            case "fav":
                getSupportFragmentManager().beginTransaction().replace(R.id.mian_frame_layout, new FavoritesFragment()).commit();

                break;
            case "Recent":
                getSupportFragmentManager().beginTransaction().replace(R.id.mian_frame_layout, new RecentFragment()).commit();
                break;
            case "Contacts":
                getSupportFragmentManager().beginTransaction().replace(R.id.mian_frame_layout, new ContactFragment()).commit();
                break;
            case "Keypad":
                getSupportFragmentManager().beginTransaction().replace(R.id.mian_frame_layout, new KeypadFragment()).commit();
                break;
            case "Voicemail":
                getSupportFragmentManager().beginTransaction().replace(R.id.mian_frame_layout, new VoicemailFragment()).commit();
                break;
        }


        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_Favorites:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mian_frame_layout, new FavoritesFragment()).commit();
                        editor.putString("main_fram", "fav");
                        editor.apply();
                        break;
                    case R.id.item_Recents:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mian_frame_layout, new RecentFragment()).commit();
                        editor.putString("main_fram", "Recent");
                        editor.apply();
                        break;
                    case R.id.item_Contacts:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mian_frame_layout, new ContactFragment()).commit();
                        editor.putString("main_fram", "Contacts");
                        editor.apply();
                        break;
                    case R.id.item_Keypad:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mian_frame_layout, new KeypadFragment()).commit();
                        editor.putString("main_fram", "Keypad");
                        editor.apply();
                        break;
                    case R.id.item_Voicemail:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mian_frame_layout, new VoicemailFragment()).commit();
                        editor.putString("main_fram", "Voicemail");
                        editor.apply();
                        break;
                }
                return true;
            }
        });
        mainViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (requestCode == 1) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    main_permition = true;
                } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {

                }
            }

        }
    }



    private void requestpermissoin() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CALL_PHONE}, 1);

    }


    private boolean checkpermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);
        return result == PackageManager.PERMISSION_GRANTED;

    }
}