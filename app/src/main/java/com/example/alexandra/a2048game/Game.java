package com.example.alexandra.a2048game;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;


public class Game extends AppCompatActivity
{

    GridView grid = null;
    TextView textView = null;
    int score = 0;
    int side = 4;
    Integer[] titles = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    Random rand = new Random();


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textView = (TextView) findViewById(R.id.Score);
        grid = (GridView) findViewById(R.id.gridView);

        int pos = pickRandom(titles);
        titles[pos] = 2;

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        grid.setAdapter(arrayAdapter);

        grid.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN )
                {
                    if( i == KeyEvent.KEYCODE_DPAD_RIGHT)
                    {
                        titles = rightMove(titles);
                        int pos = pickRandom(titles);
                        titles[pos] = 2;
                        changeGrid(titles);
                    }

                    if( i == KeyEvent.KEYCODE_DPAD_LEFT)
                    {
                        int pos = pickRandom(titles);
                        titles[pos] = 2;
                        changeGrid(titles);
                    }

                    if( i == KeyEvent.KEYCODE_DPAD_UP)
                    {
                        int pos = pickRandom(titles);
                        titles[pos] = 2;
                        changeGrid(titles);
                    }

                    if( i == KeyEvent.KEYCODE_DPAD_DOWN)
                    {
                        int pos = pickRandom(titles);
                        titles[pos] = 2;
                        changeGrid(titles);
                    }
                }
                return false;
            }
        });

    }

    private int pickRandom(Integer[] array)
    {
        Random rand = new Random();
        int pos = -1;
        while(true)
        {
            pos = rand.nextInt(16);
            if(array[pos] == 0)
            {
                return pos;
            }
        }


    }
    private int[][] convertArray(Integer[] array)
    {
        int side = 4 ;
        int[][] matrix = new int[4][4];

        int count = 0;

        for(int i = 0; i < side; i++)
        {
            for(int j = 0 ; j < side ; j++)
            {
                matrix[i][j] = array[count];
                count++;
            }
        }

        return matrix;

    }

    private Integer[] convertMatrix(int[][] matrix)
    {
        int side = 4 ;
        Integer[] array = new Integer[side*side];

        int count = 0;

        for(int i = 0; i < side; i++)
        {
            for(int j = 0 ; j < side ; j++)
            {
                 array[count] = matrix[i][j] ;
                count++;
            }
        }

        return array;
    }


    private Integer[] rightMove(Integer[] array)
    {
        int[][] matrix = convertArray(array);
        int side = 4;
        for(int i = 0 ; i < side; i++)
            matrix = rightMoveOnRow(i, matrix);

        return convertMatrix(matrix);

    }

    private int[][] rightMoveOnRow(int row, int[][] matrix)
    {
        int side = 4;

        for (int i = 0 ; i < side - 1; i++)
        {
            if(matrix[row][i] == matrix[row][i+1] || matrix[row][i+1] == 0)
            {

                matrix[row][i+1] += matrix[row][i];
                score = Math.max(score, matrix[row][i+1]);

                if(i == 0)
                {
                    matrix[row][i] = 0;
                }
                else
                {
                    matrix[row][i] = matrix[row][i-1]; // ar trebui sa se propage in lant.
                }

            }
        }
        return matrix;
    }
    private void leftMove(Integer[] array)
    {
        int[][] matrix = convertArray(array);
        for(int i = 0; i < side; i++)
            matrix = leftMoveOnRow(i,matrix);


    }

    private int[][] leftMoveOnRow(int row, int[][] matrix)
    {
        int side = 4;
        for (int j = side - 2; j >=0; j-- )
        {
            if(matrix[row][j] == matrix[row][j-1] || matrix[row][j-1] == 0)
            {
                matrix[row][j - 1] = matrix[row][j] + matrix[row][j - 1];
                matrix[row][j] = matrix[row][j-1];
            }
        }
        return matrix;
    }

    public void changeGrid(Integer[] titls)
    {
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titls);
        grid.setAdapter(arrayAdapter);
    }
{

}


}
