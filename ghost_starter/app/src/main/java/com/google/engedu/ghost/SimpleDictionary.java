package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {

        if(prefix==null)
        {
          //  Random rand = new Random();
            int rand=(int)Math.random()*(words.size());
            String random = words.get(rand);
            return random;
         //   int random=Math.random();
        }
       else
        {
            int low=0,high=words.size()-1;
            while(low<high)
            {
                int mid=(low+high)/2;
                if((words.get(mid)).startsWith(prefix))
                {
                    return words.get(mid);
                }
               else
                {
                    int k=(words.get(mid)).compareTo(prefix);
                //    System.out.println("RAMYA  "+words.get(mid));
                    if(k>0)
                    {
                        high=mid-1;
                    }
                    else if(k<0)
                    {
                        low=mid+1;
                    }
                }

            }
            return null;
        }
    //    return null;
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        return null;
    }
}
