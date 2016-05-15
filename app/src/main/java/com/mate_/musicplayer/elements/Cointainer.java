package com.mate_.musicplayer.elements;


import com.mate_.musicplayer.audio.Player;
import com.mate_.musicplayer.library.Album;
import com.mate_.musicplayer.library.Artist;
import com.mate_.musicplayer.library.Queue;

import java.util.TreeMap;

/**
 * Created by Mate_ on 01.05.2016.
 */
public class Cointainer {

    private TreeMap artists = new TreeMap( );
    private Queue queue;
    private Player player;
    private Artist artist;
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum( Album album ) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist( Artist artist ) {
        this.artist = artist;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer( Player player ) {
        this.player = player;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue( Queue queue ) {
        this.queue = queue;
    }

    public TreeMap getArtists() {
        return artists;
    }

    public void addArtists( String key, Artist artist ) {
        artists.put( key, artist );
    }

    public Artist getArtists( String key ) {
        return ( Artist ) artists.get( key );
    }

}
