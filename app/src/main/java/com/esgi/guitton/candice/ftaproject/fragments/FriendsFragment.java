package com.esgi.guitton.candice.ftaproject.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.esgi.guitton.candice.ftaproject.R;
import com.esgi.guitton.candice.ftaproject.User;
import com.esgi.guitton.candice.ftaproject.adapter.FriendAdapter;
import com.esgi.guitton.candice.ftaproject.cache.DataBaseHelper;

import java.util.ArrayList;


public class FriendsFragment extends Fragment {

    ListView listFriend;
    ArrayList<User> friends;


    public FriendsFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends, container, false);


        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), DataBaseHelper.DATABASE_NAME, DataBaseHelper.VERSION);

        friends = dataBaseHelper.getFriends();

        listFriend = view.findViewById(R.id.list_friend);

        //Création et initialisation de l'Adapter pour les personnes
        FriendAdapter friendAdapter = new FriendAdapter(friends, getContext());

        //Initialisation de la liste avec les données
        listFriend.setAdapter(friendAdapter);
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
