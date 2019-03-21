package com.vishnu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    enum player{
        one,two,no;
    }
    player[] playerchoices=new player[9];
    player currentplayer;
    int[][] winner={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    private Button reset;
    private GridLayout gridLayout;
    //gridLayout=findViewById(R.id.gridLayout);
    //GridLayout gridLayout=findViewById(R.id.gridLayout);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentplayer=player.one;
        text=findViewById(R.id.textView);

        for(int index=0;index<playerchoices.length;index++){
            playerchoices[index]=player.no;
        }

        reset=findViewById(R.id.reset);
        gridLayout=findViewById(R.id.gridLayout);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }
    boolean gameover=false;
    public void clicked(View view) {
        ImageView ima = (ImageView) view;

        if (playerchoices[Integer.parseInt(ima.getTag().toString())] == player.no && gameover==false) {
            playerchoices[Integer.parseInt(ima.getTag().toString())] = currentplayer;
            ima.setTranslationX(-2000);
            int flag=0;
            for(int index=0;index<playerchoices.length;index++)
            {
                if(playerchoices[index]==player.no){
                    flag++;
                    break;
                }
            }
            if(flag==0){
                reset.setVisibility(View.VISIBLE);
                text.setBackgroundColor(0xFFD81B37);
                text.setText("DRAW");
            }

            if (currentplayer == player.one) {
                ima.setImageResource(R.drawable.tiger);
                ima.animate().translationXBy(2000).rotation(3600).alpha(1);
                //  Toast.makeText(this,ima.getTag().toString(),Toast.LENGTH_SHORT).show();
                currentplayer = player.two;
            } else if (currentplayer == player.two) {
                ima.setImageResource(R.drawable.lion);
                ima.animate().translationXBy(2000).rotation(3600).alpha(1);
                currentplayer = player.one;
            }

            for (int[] x : winner) {
                if (playerchoices[x[0]] == playerchoices[x[1]] && playerchoices[x[1]] == playerchoices[x[2]] && playerchoices[x[0]] != player.no) {
                    gameover=true;
                    reset.setVisibility(View.VISIBLE);
                    if(currentplayer==player.two)
                    {
                        //Toast.makeText(this,"tiger is winner",Toast.LENGTH_SHORT).show();
                        text.setBackgroundColor(0xFFD81B37);
                        text.setText("TIGER IS WINNER");
                    }
                    else{
                       // Toast.makeText(this,"Lion is winner",Toast.LENGTH_SHORT).show();
                        text.setBackgroundColor(0xFFD81B37);
                        text.setText("LION IS WINNER");
                    }
                }
            }

        }
    }
    public void reset(){
        reset.setVisibility(View.INVISIBLE);
        text.setBackgroundColor(0xB61F65);
        text.setText("Lion or Tiger");
      for(int index=0;index<gridLayout.getChildCount();index++) {
            ImageView ima= (ImageView) gridLayout.getChildAt(index);
            ima.setImageDrawable(null);
           ima.setAlpha(0.2f);
        }
        currentplayer=player.one;
            for(int index=0;index<playerchoices.length;index++){
                playerchoices[index]=player.no;
            }
        gameover=false;

    }

}
