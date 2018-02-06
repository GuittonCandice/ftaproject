package com.esgi.guitton.candice.ftaproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.esgi.guitton.candice.ftaproject.Item;
import com.esgi.guitton.candice.ftaproject.MainActivity;
import com.esgi.guitton.candice.ftaproject.R;
import com.esgi.guitton.candice.ftaproject.User;
import com.esgi.guitton.candice.ftaproject.adapter.ItemAdapter;
import com.esgi.guitton.candice.ftaproject.cache.DataBaseHelper;

import java.util.ArrayList;


public class InventoryFragment extends Fragment {

    ListView listItem;
    ArrayList<Item> items;
    ImageButton userProfile;
    ImageView item_avatar;
    public InventoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        userProfile = view.findViewById(R.id.user_profile_button);
        item_avatar = view.findViewById(R.id.item_avatar);


        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProfileFragment(getFragmentManager());
            }
        });


        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), DataBaseHelper.DATABASE_NAME, DataBaseHelper.VERSION);

        items = dataBaseHelper.getItems();

        listItem = view.findViewById(R.id.list_item);

        //Création et initialisation de l'Adapter pour les personnes
        ItemAdapter itemAdapter = new ItemAdapter(items, getContext());

        //Initialisation de la liste avec les données
        listItem.setAdapter(itemAdapter);
        return view;
    }
    private void userProfileFragment(FragmentManager fm) {
        UserProfileFragment userProfileFragment = new UserProfileFragment();
        fm.beginTransaction().replace(R.id.fragment_container, userProfileFragment).commitAllowingStateLoss();
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
