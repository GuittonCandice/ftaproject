package com.esgi.guitton.candice.ftaproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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
    FragmentManager fragmentManager;

    public InventoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        userProfile = getActivity().findViewById(R.id.user_profile_button);
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
