package com.esgi.guitton.candice.ftaproject.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esgi.guitton.candice.ftaproject.R;
import com.esgi.guitton.candice.ftaproject.adapter.FriendAdapter;
import com.esgi.guitton.candice.ftaproject.cache.DataBaseHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);


        user_firstname = getActivity().findViewById(R.id.user_firstname);
        user_lastname = getActivity().findViewById(R.id.user_lastname);
        user_email = getActivity().findViewById(R.id.user_email);
        user_qrcode = getActivity().findViewById(R.id.user_qrcode);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());

        user_lastname.setText(account.getFamilyName());
        user_firstname.setText(account.getGivenName());
        user_email.setText(account.getEmail());

        VCard user = new VCard();
        user.setName(user_firstname.toString()+" "+user_lastname.toString());
        user.setEmail(user_email.toString());
        Bitmap myBitmap = QRCode.from(user).bitmap();
        user_qrcode.setImageBitmap(myBitmap);
        
        return view;
    }
}
