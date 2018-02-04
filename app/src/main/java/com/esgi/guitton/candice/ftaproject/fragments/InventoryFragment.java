package com.esgi.guitton.candice.ftaproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.esgi.guitton.candice.ftaproject.Item;
import com.esgi.guitton.candice.ftaproject.MainActivity;
import com.esgi.guitton.candice.ftaproject.R;
import com.esgi.guitton.candice.ftaproject.User;
import com.esgi.guitton.candice.ftaproject.cache.DataBaseHelper;

import java.util.ArrayList;


public class InventoryFragment extends Fragment {

    ListView listItem;
    ArrayList<Item> items;


    public InventoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);


        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), DataBaseHelper.DATABASE_NAME, DataBaseHelper.VERSION);

        items = dataBaseHelper.getItems();

        listItem = view.findViewById(R.id.list_item);

        final ArrayAdapter<Item> itemAdapter = new ArrayAdapter<Item>(getContext(), android.R.layout.simple_list_item_1, items);
        listItem.setAdapter(itemAdapter);

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
