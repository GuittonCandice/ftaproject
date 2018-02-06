package com.esgi.guitton.candice.ftaproject.adapter;

import android.content.Context;
import android.content.res.Resources;
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

import java.util.ArrayList;


/**
 * Created by candiceguitton on 04/02/2018.
 */

public class ItemAdapter extends BaseAdapter {


    private ArrayList<Item> listItem;

    //Le contexte dans lequel est présent notre adapter
    private Context context;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    public ItemAdapter(ArrayList<Item> listItem, Context context) {
        this.context = context;
        this.listItem = listItem;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layoutItem;
        if (convertView == null) {
            layoutItem = (RelativeLayout) mInflater.inflate(R.layout.item_list_row, parent, false);
        } else {
            layoutItem = (RelativeLayout) convertView;
        }

        ImageView item_avatar = layoutItem.findViewById(R.id.item_avatar);
        TextView item_name = layoutItem.findViewById(R.id.item_name);
        String name = listItem.get(position).getName();
        name = name.replace(" ", "_");
        name = name.toLowerCase();


        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(name, "drawable", context.getPackageName());
        Drawable drawable = resources.getDrawable(resourceId);


        final Drawable avatar = drawable;
        item_avatar.setImageDrawable(avatar);
        item_name.setText(listItem.get(position).getName());


        return layoutItem;
    }

}
