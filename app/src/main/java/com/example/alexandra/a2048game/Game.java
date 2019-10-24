package com.example.alexandra.a2048game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Random;


public class Game extends AppCompatActivity
{

    GridView grid = null;
    TextView textView = null;
    int score = 0;
    int side = 4;
    int[][] tiles = {{0,0,0,0},{0,0,0,0},{0,0,0,0 },{0,0,0,0 }};
    Random rand = new Random();


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        textView = (TextView) findViewById(R.id.Score);
        grid = (GridView) findViewById(R.id.gridView);

        GameAdapter gameAdapter = new GameAdapter(this,tiles);
        grid.setAdapter(gameAdapter);

        grid.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_SPACE))
                {

                    return true;

                }

                if ((event.getAction() == KeyEvent.ACTION_UP) && (i == KeyEvent.KEYCODE_SPACE))
                {

                    return true;

                }

                return false;
            }
        });


    }

    private  void addRandomTile()
    {
        int pos = rand.nextInt(side * side);
        int row, col;
        do {
            pos = (pos + 1) % (side * side);
            row = pos / side;
            col = pos % side;
        } while (tiles[row][col] != 0);

        int val = rand.nextInt(10) == 0 ? 4 : 2;
        tiles[row][col] = val;
    }

    private void start()
    {

    }

}
