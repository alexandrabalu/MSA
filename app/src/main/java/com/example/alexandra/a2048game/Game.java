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
                        titles = leftMove(titles);
                        int pos = pickRandom(titles);
                        titles[pos] = 2;
                        changeGrid(titles);
                    }

                    if( i == KeyEvent.KEYCODE_DPAD_UP)
                    {
                        titles = upMove(titles);
                        int pos = pickRandom(titles);
                        titles[pos] = 2;
                        changeGrid(titles);
                    }

                    if( i == KeyEvent.KEYCODE_DPAD_DOWN)
                    {
                        titles = downMove(titles);
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
        int col = 4;

        for (int i = 0 ; i < col - 1; i++)
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


    private Integer[] leftMove(Integer[] array)
    {
        int[][] matrix = convertArray(array);
        int col = 4;
        for(int i = 0 ; i < col; i++)
            matrix = leftMoveOnRow(i, matrix);

        return convertMatrix(matrix);

    }

    private int[][] leftMoveOnRow(int row, int[][] matrix)
    {
        int col = 4;

        for (int i = col ; i > 1; i--)
        {
            if(matrix[row][i] == matrix[row][i-1] || matrix[row][i-1] == 0)
            {

                matrix[row][i-1] += matrix[row][i];
                score = Math.max(score, matrix[row][i-1]);
                if(i == col)
                {
                    matrix[row][i] = 0;
                }
                else
                {
                    matrix[row][i] = matrix[row][i+1]; // ar trebui sa se propage in lant.
                }

            }
        }
        return matrix;
    }

    private Integer[] upMove(Integer[] array)
    {
        int[][] matrix = convertArray(array);
        int side = 4;
        for(int i = 0 ; i < side; i++)
            matrix = upMoveOnColumn(i, matrix);

        return convertMatrix(matrix);

    }

    private int[][] upMoveOnColumn(int col, int[][] matrix)
    {
        int row = 4;

        for (int i = row  ; i > 0; i--)
        {
            if(matrix[i][col] == matrix[i-1][col] || matrix[i-1][col] == 0)
            {

                matrix[i-1][col] += matrix[i][col];
                score = Math.max(score, matrix[i-1][col]);

                if(i == row)
                {
                    matrix[i][col] = 0;
                }
                else
                {
                    matrix[i][col] = matrix[i+1][col]; // ar trebui sa se propage in lant.
                }

            }
        }
        return matrix;
    }

    private Integer[] downMove(Integer[] array)
    {
        int[][] matrix = convertArray(array);
        int side = 4;
        for(int i = 0 ; i < side; i++)
            matrix = downMoveOnColumn(i, matrix);

        return convertMatrix(matrix);

    }

    private int[][] downMoveOnColumn(int col, int[][] matrix)
    {
        int row = 4;

        for (int i = 0  ; i < row - 1; i++)
        {
            if(matrix[i][col] == matrix[i+1][col] || matrix[i+1][col] == 0)
            {

                matrix[i+1][col] += matrix[i][col];
                score = Math.max(score, matrix[i-1][col]);

                if(i == 0)
                {
                    matrix[i][col] = 0;
                }
                else
                {
                    matrix[i][col] = matrix[i-1][col]; // ar trebui sa se propage in lant.
                }

            }
        }
        return matrix;
    }

    private boolean isFull(int[][] matrix)
    {
        for(int i = 0 ; i < side; i++)
        {
            for(int j = 0 ; j < side ; j++)
            {
                if(matrix[i][j] == 0)
                    return false;
            }
        }

        return true;
    }

    public void changeGrid(Integer[] titls)
    {
        if((score != 2048) || !isFull(convertArray(titls)) ) {
            ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titls);
            grid.setAdapter(arrayAdapter);
        }
        else
        {
            String[] array  = new String[1];

            if(score == 2048)
            {
                array[0] = "CONGRATULATION !! YOU WON";
            }

            else
            {
                array[0] = "OOPS !! YOU LOST :( ";
            }


            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        }
    }
{

}


}
