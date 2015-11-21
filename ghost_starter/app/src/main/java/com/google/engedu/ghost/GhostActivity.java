package com.google.engedu.ghost;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class GhostActivity extends ActionBarActivity   {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        try
        {
            System.out.println("init");
            InputStream i= getAssets().open("words.txt");
            dictionary =new SimpleDictionary(i);
        }
        catch(Exception a)
        {
            System.out.println(a+"occured ramya");
        }
        onStart(null);

    //    userTurn = random.nextBoolean();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }
    public void challenge(View v)
    {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        String s=(String)text.getText();
        if(s!=null)
        {
            if(s.length()>3 && dictionary.isWord(s))
            {
                label.setText("user won");
                Toast.makeText(getApplicationContext(),"word formed "+s, Toast.LENGTH_SHORT).show();
            }
            else
            {
                String word=dictionary.getAnyWordStartingWith(s);
                if(word==null)
                {
                    label.setText("user won");
                    Toast.makeText(getApplicationContext(),"no word with prefix "+s, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    label.setText("computer won");
                    Toast.makeText(getApplicationContext(),"word having prefix "+s+" is "+word, Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
    public void restart(View v)
    {

        Intent intent = new Intent(GhostActivity.this,
                GhostActivity.class);

        startActivity(intent);
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

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        // Do computer turn stuff then make it the user's turn again
    //    System.out.println("RAMYA: computer turn");
        String s=(String)text.getText();
        if(s.length()>3 && dictionary.isWord(s))
        {
                label.setText("Computer won");
                Toast.makeText(getApplicationContext(),"word formed "+s, Toast.LENGTH_SHORT).show();
        }
        else
        {
           String word=dictionary.getAnyWordStartingWith(s);
            if(word==null)
            {
                label.setText("Computer won");
                Toast.makeText(getApplicationContext(),"no word with prefix "+s, Toast.LENGTH_SHORT).show();
            }
            else
            {
               // int len=0;
             //   if(s.length()!=0)
                 int len=s.length();
                String com="";
                if(len!=0)
                {
                    char ch=word.charAt(len);
                    String cat=String.valueOf(ch);
                     com=s.concat(cat);
                }
                else
                {
                    final String alphabet = "abcdefghijklmnopqrstuvwxyz";
                    final int N = alphabet.length();

                    Random r = new Random();
                       char ch= alphabet.charAt(r.nextInt(N));
                    com=String.valueOf(ch);
                }
                text.setText(com);
                userTurn = true;
                label.setText(USER_TURN);
            }
        }
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
         text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
      //  Toast.makeText(getApplicationContext(),
     //           "activity key up..", Toast.LENGTH_SHORT).show();
        TextView label = (TextView) findViewById(R.id.gameStatus);

        if(keyCode<29 || keyCode >54)
            return super.onKeyUp(keyCode,event);
        char unicodeChar = (char)event.getUnicodeChar();
        String a=String.valueOf(unicodeChar);
         String s=(String)text.getText();
       String ab= s.concat(a);
        //   System.out.println("RAMYA "+ab);
        text.setText(ab);
        userTurn=false;
        label.setText(COMPUTER_TURN);
        computerTurn();
        return super.onKeyUp(keyCode, event);
    }
}
