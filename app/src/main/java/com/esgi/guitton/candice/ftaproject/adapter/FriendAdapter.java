package com.esgi.guitton.candice.ftaproject.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.esgi.guitton.candice.ftaproject.Item;
import com.esgi.guitton.candice.ftaproject.R;
import com.esgi.guitton.candice.ftaproject.User;

import java.util.ArrayList;

/**
 * Created by candiceguitton on 04/02/2018.
 */

public class FriendAdapter extends BaseAdapter {


    private ArrayList<User> listFriend;

    //Le contexte dans lequel est présent notre adapter
    private Context context;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    public FriendAdapter(ArrayList<User> listFriend, Context context) {
        this.context = context;
        this.listFriend = listFriend;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listFriend.size();
    }

    @Override
    public Object getItem(int position) {
        return listFriend.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layoutFriend;

        if (convertView == null) {
            layoutFriend = (RelativeLayout) mInflater.inflate(R.layout.friend_list_row, parent, false);
        } else {
            layoutFriend = (RelativeLayout) convertView;
        }

        TextView friend_lastname = (TextView) layoutFriend.findViewById(R.id.friend_lastname);
        TextView friend_firstname = (TextView)layoutFriend.findViewById(R.id.friend_firstname);
        friend_lastname.setText(listFriend.get(position).getLastname());
        friend_firstname.setText(listFriend.get(position).getFirstname());



        //On retourne l'item créé.
        return layoutFriend;
    }
}