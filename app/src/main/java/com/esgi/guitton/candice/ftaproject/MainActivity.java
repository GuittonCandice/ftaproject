package com.esgi.guitton.candice.ftaproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.esgi.guitton.candice.ftaproject.cache.DataBaseHelper;
import com.esgi.guitton.candice.ftaproject.fragments.FriendsFragment;
import com.esgi.guitton.candice.ftaproject.fragments.InventoryFragment;
import com.esgi.guitton.candice.ftaproject.fragments.QrCodeFragment;
import com.esgi.guitton.candice.ftaproject.qrcode.QRCodeCaptureActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import net.glxn.qrgen.android.QRCode;
import net.glxn.qrgen.core.scheme.VCard;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private static final int RC_BARCODE_CAPTURE = 9001;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        inventoryFragment(fragmentManager);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.inventory:
                        inventoryFragment(fragmentManager);
                        break;
                    case R.id.camera:
                        launchScanner();
                        break;
                    case R.id.friends:
                        friendFragment(fragmentManager);
                        break;

                    default:
                        break;
                }
                return false;
            }

            ;

        });
    }

    private void friendFragment(FragmentManager fm) {
        FriendsFragment friendsFragment = new FriendsFragment();
        fm.beginTransaction().replace(R.id.fragment_container, friendsFragment).commitAllowingStateLoss();
    }

    private void inventoryFragment(FragmentManager fm) {
        InventoryFragment inventoryFragment = new InventoryFragment();
        fm.beginTransaction().replace(R.id.fragment_container, inventoryFragment).commitAllowingStateLoss();
    }

    private void launchScanner() {
        final CharSequence[] items = {"Auto Focus", " Flash"};
        final ArrayList seletedItems = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.select_options);
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            seletedItems.add(indexSelected);
                        } else if (seletedItems.contains(indexSelected)) {
                            seletedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(MainActivity.this, QRCodeCaptureActivity.class);
                        intent.putExtra(QRCodeCaptureActivity.AutoFocus, seletedItems.contains(0));
                        intent.putExtra(QRCodeCaptureActivity.UseFlash, seletedItems.contains(1));
                        startActivityForResult(intent, RC_BARCODE_CAPTURE);

                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(QRCodeCaptureActivity.BarcodeObject);
                    Barcode.ContactInfo contact = barcode.contactInfo;

                    if (contact != null) {
                        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
                        if(account != null) {

                            User user = new User(contact.name.first, contact.name.last, contact.emails[0].address,account.getId());
                            DataBaseHelper dataBaseHelper = new DataBaseHelper(this, DataBaseHelper.DATABASE_NAME, DataBaseHelper.VERSION);
                            dataBaseHelper.addFriend(user);

                            friendFragment(getSupportFragmentManager());

                        }
                        //statusMessage.setText("Nouvel utilisateur trouvé !");
                        /*barcodeValue.setText(contact.name.first+" "+contact.name.last+"\n"+contact.emails[0].address+"\n" +
                                contact.addresses[0].addressLines[0]+"\n"+contact.title+"\n"+contact.organization+"\n"+
                                contact.phones[0].number+"\n"+contact.urls[0]);*/

                        //create a new QRCode visite card with the field get from the QRCode scanned
                        VCard user = new VCard(contact.name.first + " " + contact.name.last)
                                .setEmail(contact.emails[0].address)
                                .setAddress(contact.addresses[0].addressLines[0])
                                .setTitle(contact.title)
                                .setCompany(contact.organization)
                                .setPhoneNumber(contact.phones[0].number)
                                .setWebsite(contact.urls[0]);


                        friendFragment(getSupportFragmentManager());


                        Bitmap myBitmap = QRCode.from(user).bitmap();
                        // myImageView.setImageBitmap(myBitmap);

                        // Log.d(TAG, "The barcode is a new contact");
                    } else {
                        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
                        if(account != null) {
                            Item item = new Item(barcode.rawValue, null, account.getId());
                            DataBaseHelper dataBaseHelper = new DataBaseHelper(this, DataBaseHelper.DATABASE_NAME, DataBaseHelper.VERSION);
                            dataBaseHelper.addItem(item);

                            inventoryFragment(getSupportFragmentManager());
                        }
                    }
                } else {
                    //statusMessage.setText("Le QR Code n'a pas pu être lu !");
                    // Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                //statusMessage.setText("Erreur de lecture...");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}