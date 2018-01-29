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


public class InventoryFragment extends Fragment {
    ListView listItem;
    User user; // get connected user by sharedpreferences
    Item[] items = user.getItems();
    public InventoryFragment() {
    }


    public static InventoryFragment newInstance(String param1, String param2) {
        InventoryFragment fragment = new InventoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listItem = getActivity().findViewById(R.id.list_item);

        final ArrayAdapter<Item> itemAdapter = new ArrayAdapter<Item>(getActivity(),
                android.R.layout.simple_list_item_1, items);
        listItem.setAdapter(itemAdapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory, container, false);
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
