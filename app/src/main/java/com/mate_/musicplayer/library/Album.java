package com.mate_.musicplayer.library;

import java.util.TreeMap;

/**
 * Created by Mate_ on 01.05.2016.
 */
public class Album {

    private String name;
    private TreeMap songs = new TreeMap( );

    public TreeMap getSongs() {
        return songs;
    }

    public Album( String name ) {

        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSong( String key, String song ) {
        songs.put( key, song );
    }

}
