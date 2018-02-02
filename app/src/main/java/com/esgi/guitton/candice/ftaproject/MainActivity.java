package com.esgi.guitton.candice.ftaproject;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.esgi.guitton.candice.ftaproject.fragments.FriendsFragment;
import com.esgi.guitton.candice.ftaproject.fragments.InventoryFragment;
import com.esgi.guitton.candice.ftaproject.fragments.QrCodeFragment;

import net.glxn.qrgen.android.QRCode;
import net.glxn.qrgen.core.scheme.VCard;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                switch (item.getItemId()) {

                    case R.id.inventory:
                        InventoryFragment inventoryFragment = new InventoryFragment();
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, inventoryFragment).commit();
                        break;
                    case R.id.camera:
                        QrCodeFragment qrCodeFragment = new QrCodeFragment();
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, qrCodeFragment).commit();
                        break;
                    case R.id.friends:
                        FriendsFragment friendsFragment = new FriendsFragment();
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, friendsFragment).commit();
                        break;

                    default:
                        break;
                }
                return false;
            }

            ;

        });
    }
}