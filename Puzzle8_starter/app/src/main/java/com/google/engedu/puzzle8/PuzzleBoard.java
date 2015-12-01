package com.google.engedu.puzzle8;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;


public class PuzzleBoard {

    private static final int NUM_TILES = 3;
    private static final int[][] NEIGHBOUR_COORDS = {
            { -1, 0 },
            { 1, 0 },
            { 0, -1 },
            { 0, 1 }
    };
    private ArrayList<PuzzleTile> tiles;

    PuzzleBoard(Bitmap bitmap, int parentWidth,int parentHeight) {
        tiles=new ArrayList<>();
        int no=0;
      //  Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 240, 240, true);
       /* Bitmap[] imgs = new Bitmap[9];
        imgs[0] = Bitmap.createBitmap(scaledBitmap, 0, 0, 80 , 80);
        imgs[1] = Bitmap.createBitmap(scaledBitmap, 80, 0, 80, 80);
        imgs[2] = Bitmap.createBitmap(scaledBitmap,160, 0, 80,80);
        imgs[3] = Bitmap.createBitmap(scaledBitmap, 0, 80, 80, 80);
        imgs[4] = Bitmap.createBitmap(scaledBitmap, 80, 80, 80,80);
        imgs[5] = Bitmap.createBitmap(scaledBitmap, 160, 80,80,80);
        imgs[6] = Bitmap.createBitmap(scaledBitmap, 0, 160, 80,80);
        imgs[7] = Bitmap.createBitmap(scaledBitmap, 80, 160,80,80);
        imgs[8] = Bitmap.createBitmap(scaledBitmap, 160,160,80,80);*/
     /*   for(int i=0;i<9;i++)
        {
            PuzzleTile tile=new PuzzleTile(imgs[i],no);
            tiles.add(tile);
        }*/
        for (int i = 0; i < NUM_TILES; i++) {
            for(int j=0;j< NUM_TILES; j++)
            {
                no++;
                if( no!=(NUM_TILES*NUM_TILES))
                {
                    Bitmap part = bitmap.createBitmap(bitmap,(i*parentWidth/NUM_TILES),(j*parentHeight/NUM_TILES),(parentWidth/NUM_TILES),(parentHeight/NUM_TILES));
                    PuzzleTile tile=new PuzzleTile(part,no-1);
                    tiles.add(tile);
                }
                else
                    tiles.add(null);

            }
        }


    }

    PuzzleBoard(PuzzleBoard otherBoard) {
        tiles = (ArrayList<PuzzleTile>) otherBoard.tiles.clone();
    }

    public void reset() {
        // Nothing for now but you may have things to reset once you implement the solver.
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        return tiles.equals(((PuzzleBoard) o).tiles);
    }

    public void draw(Canvas canvas) {
        if (tiles == null) {
            return;
        }
        for (int i = 0; i < NUM_TILES * NUM_TILES; i++) {
            PuzzleTile tile = tiles.get(i);
            if (tile != null) {
                tile.draw(canvas, i % NUM_TILES, i / NUM_TILES);
            }
        }
    }

    public boolean click(float x, float y) {
        for (int i = 0; i < NUM_TILES * NUM_TILES; i++) {
            PuzzleTile tile = tiles.get(i);
            if (tile != null) {
                if (tile.isClicked(x, y, i % NUM_TILES, i / NUM_TILES)) {
                    return tryMoving(i % NUM_TILES, i / NUM_TILES);
                }
            }
        }
        return false;
    }

    public boolean resolved() {
        for (int i = 0; i < NUM_TILES * NUM_TILES - 1; i++) {
            PuzzleTile tile = tiles.get(i);
            if (tile == null || tile.getNumber() != i)
                return false;
        }
        return true;
    }

    private int XYtoIndex(int x, int y) {
        return x + y * NUM_TILES;
    }

    protected void swapTiles(int i, int j) {
        PuzzleTile temp = tiles.get(i);
        tiles.set(i, tiles.get(j));
        tiles.set(j, temp);
    }

    private boolean tryMoving(int tileX, int tileY) {
        for (int[] delta : NEIGHBOUR_COORDS) {
            int nullX = tileX + delta[0];
            int nullY = tileY + delta[1];
            if (nullX >= 0 && nullX < NUM_TILES && nullY >= 0 && nullY < NUM_TILES &&
                    tiles.get(XYtoIndex(nullX, nullY)) == null) {
                swapTiles(XYtoIndex(nullX, nullY), XYtoIndex(tileX, tileY));
                return true;
            }

        }
        return false;
    }

    public ArrayList<PuzzleBoard> neighbours() {

        ArrayList<PuzzleBoard> newboards= new ArrayList<>();
        //find free tile
        int free=0;
        int i=0;
        while (i<NUM_TILES*NUM_TILES)
        {
            if(tiles.get(i)==null)
            {
                free=i;
                break;
            }
            i++;
        }
        int xcord=free/NUM_TILES;
        int ycord=free%NUM_TILES;
        for( i=0;i<NEIGHBOUR_COORDS.length;i++)
        {
           int newx=xcord+NEIGHBOUR_COORDS[i][0];
            int newy=ycord+NEIGHBOUR_COORDS[i][1];
            if(newx>=0 && newx<NUM_TILES && newy>=0 && newy<NUM_TILES)
            {
                int swap=newx*NUM_TILES+newy;
                swapTiles(free,swap);
                newboards.add(new PuzzleBoard(this));
                swapTiles(free,swap)   ;    //restore again
            }
        }
        return newboards;
    }

    public int priority() {
        return 0;
    }

}
