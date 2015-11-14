package com.example.ramya.dice3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int turn=1,score1=0,score2=0,win=0;
    String status=new String("player1's turn");
    Button b1,b2,b3;
    TextView t1,t2,t3;
    ImageView i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i=(ImageView)findViewById(R.id.imageView);
        t1=(TextView)findViewById(R.id.score1);
        t2=(TextView)findViewById(R.id.status);
        t3=(TextView)findViewById(R.id.score2);
        b1=(Button)findViewById(R.id.roll);
        b2=(Button)findViewById(R.id.hold);
        b3=(Button)findViewById(R.id.reset);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void func(View v)
    {
        if(v.getId()==R.id.roll)
        {
            int rand=(int)Math.random()*6+1;
            Random r=new Random();
            rand=r.nextInt(6)+1;
            System.out.println(rand);
            switch(rand)
            {
                case 1:
                    if(turn==1)
                    {
                       status="player2's turn";
                        score1=0;
                        turn=0;
                    }
                    else if(turn==0)
                    {
                        status="player1's turn";
                        turn=1;
                        score2=0;
                    }
                    i.setImageResource(R.drawable.one);
                    break;
                case 2:
                //    status="new game";
                    if(turn==1)
                    {
             //           status="player2's turn";
                        score1+=2;
               //         turn=0;
                    }
                    else if(turn==0)
                    {
                 //       status="player1's turn";
                   //     turn=1;
                        score2+=2;
                    }
                    i.setImageResource(R.drawable.two);
                    break;
                case 3:
                    if(turn==1)
                    {
                  //      status="player2's turn";
                        score1+=3;
                    //    turn=0;
                    }
                    else if(turn==0)
                    {
                      //  status="player1's turn";
                       // turn=1;
                        score2+=3;
                    }
                    i.setImageResource(R.drawable.three);
                    break;
                case 4:
                    if(turn==1)
                    {
                     //   status="player2's turn";
                        score1+=4;
                    //    turn=0;
                    }
                    else if(turn==0)
                    {
                      //  status="player1's turn";
                       // turn=1;
                        score2+=4;
                    }
                    i.setImageResource(R.drawable.four);
                    break;
                case 5:
                    if(turn==1)
                    {
                      //  status="player2's turn";
                        score1+=5;
                     //   turn=0;
                    }
                    else if(turn==0)
                    {
                    //    status="player1's turn";
                    //    turn=1;
                        score2+=5;
                    }
                    i.setImageResource(R.drawable.five);
                    break;
                case 6:
                    if(turn==1)
                    {
                      //  status="player2's turn";
                        score1+=6;
                      //  turn=0;
                    }
                    else if(turn==0)
                    {
                    //    status="player1's turn";
                    //    turn=1;
                        score2+=6;
                    }
                    i.setImageResource(R.drawable.six);
                    break;

            }
            if(turn==0 && score1>=60)
            {
                win=1;
                status="player1 won";
            }
            else if(turn==1 && score2>=60)
            {
                win=1;
                status="player2 won";
            }
            t2.setText(status);
            String a=String.valueOf(score1);
            t1.setText(a);
            a=String.valueOf(score2);
            t3.setText(a);
       /*     if(win==1)
            {
                Intent intent = new Intent(MainActivity.this,
                        MainActivity.class);

                startActivity(intent);
            }*/
        }
        else if(v.getId()==R.id.hold)
        {
            if(turn==1)
            {
                turn=0;
                status="player2's turn";
            }
            else if(turn==0)
            {
                turn=1;
                status="player1's turn";
            }
            t2.setText(status);
        }
        else if(v.getId()==R.id.reset)
        {
            Intent intent = new Intent(MainActivity.this,
                    MainActivity.class);

            startActivity(intent);
        }
    }
}
