package com.example.alexandra.a2048game;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GameAdapter extends BaseAdapter {

    private final Context mContext;
    private final int[][] tiles;

    public GameAdapter(Context context,int[][] tiles )
    {
        this.mContext = context;
        this.tiles = tiles;
    }

    public int getCount()
    {
        return tiles.length;
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       TextView dummyTextView = new TextView(mContext);
       String st = "0 ";

       dummyTextView.setText(st);

       return dummyTextView;

    }


}
