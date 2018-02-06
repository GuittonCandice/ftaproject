package com.esgi.guitton.candice.ftaproject.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.esgi.guitton.candice.ftaproject.HomePageActivity;
import com.esgi.guitton.candice.ftaproject.MainActivity;
import com.esgi.guitton.candice.ftaproject.R;
import com.esgi.guitton.candice.ftaproject.adapter.FriendAdapter;
import com.esgi.guitton.candice.ftaproject.cache.DataBaseHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import net.glxn.qrgen.android.QRCode;
import net.glxn.qrgen.core.scheme.VCard;

/**
 * Created by candiceguitton on 05/02/2018.
 */

public class UserProfileFragment extends Fragment{
    TextView user_lastname;
    TextView user_firstname;
    TextView user_email;
    ImageView user_qrcode;
    ImageButton inventory;
    private GoogleSignInClient mGoogleSignInClient;
    Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);


        user_firstname = view.findViewById(R.id.user_firstname);
        user_lastname = view.findViewById(R.id.user_lastname);
        user_email = view.findViewById(R.id.user_email);
        user_qrcode = view.findViewById(R.id.user_qrcode);
        inventory = view.findViewById(R.id.inventory_return_button);
        logout = view.findViewById(R.id.button_sign_out);

        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnInventory(getFragmentManager());
            }
        });

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());



        user_lastname.setText(account.getFamilyName());
        user_firstname.setText(account.getGivenName());
        user_email.setText(account.getEmail());


        VCard user = new VCard(account.getFamilyName()+ " " +account.getGivenName());
        user.setEmail(account.getEmail())
            .setAddress("")
                .setTitle("")
                .setCompany("")
                .setPhoneNumber("")
                .setWebsite("");
        Bitmap myBitmap = QRCode.from(user).bitmap();
        user_qrcode.setImageBitmap(myBitmap);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });


        return view;
    }

    private void returnInventory(FragmentManager fm) {
        InventoryFragment inventoryFragment = new InventoryFragment();
        fm.beginTransaction().replace(R.id.fragment_container, inventoryFragment).commitAllowingStateLoss();
    }

    private void returnHomePage(FragmentManager fm) {
        Intent intent = new Intent(getContext(), HomePageActivity.class);
        startActivity(intent); }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }


}
