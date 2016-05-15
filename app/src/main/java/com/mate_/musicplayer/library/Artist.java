package com.mate_.musicplayer.library;

import java.util.TreeMap;

/**
 * Created by Mate_ on 01.05.2016.
 */
public class Artist {

    private String name;
    private TreeMap albums = new TreeMap( );

    public TreeMap getAlbums() {
        return albums;
    }

    public Artist( String name ) {

        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAlbum( String key, Album album ) {
        albums.put( key, album );
    }

    public Album getAlbum( String key ) {
        return ( Album ) albums.get( key );
    }

}
