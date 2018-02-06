package com.esgi.guitton.candice.ftaproject;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;

import com.esgi.guitton.candice.ftaproject.cache.DataBaseHelper;

/**
 * Implementation of App Widget functionality.
 */
public class HomePageAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.home_page_app_widget);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, DataBaseHelper.DATABASE_NAME, DataBaseHelper.VERSION);
        Item lastItem = dataBaseHelper.getLastItem();
        String name = lastItem.getName();
        views.setTextViewText(R.id.item_name, name);

        /*name = name.replace(" ", "_");
        name = name.toLowerCase();

        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(name, "drawable", context.getPackageName());
        Drawable drawable = resources.getDrawable(resourceId);


        final Drawable avatar = drawable;
        Bitmap bitmapAvatar = ((BitmapDrawable)avatar).getBitmap();
        views.setImageViewBitmap(R.id.item_avatar, bitmapAvatar);*/
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

