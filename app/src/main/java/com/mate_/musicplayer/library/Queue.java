package com.mate_.musicplayer.library;

/**
 * Created by Mate_ on 06.05.2016.
 */
public class Queue {

    private int current = 0;
    private Object[] songs;

    public Queue( Object[] songs ) {
        this.songs = songs;
    }

    public int getSize( ) {
        return songs.length;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent( int current ) {
        this.current = Math.min( songs.length, Math.max( 1, current ) );
    }

    public String getCurrentSong() {
        return (String)songs[ current ];
    }
}
