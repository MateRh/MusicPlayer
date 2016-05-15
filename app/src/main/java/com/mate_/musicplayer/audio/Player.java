package com.mate_.musicplayer.audio;

import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;
import android.net.Uri;
import com.mate_.musicplayer.MainActivity;
import com.mate_.musicplayer.debug.Debug;
import com.mate_.musicplayer.library.Queue;

import java.io.IOException;

/**
 * Created by Mate_ on 06.05.2016.
 */
public class Player {

    private Queue queue;
    private MediaPlayer player;

    public MediaMetadataRetriever getMetadataRetriever() {
        return metadataRetriever;
    }

    private MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();

    public MediaPlayer getPlayer() {
        return player;
    }

    public void setQueue( Queue queue ) {
        this.queue = queue;
    }

    public Player( Queue queue ) {
        this.queue = queue;
        this.create( );
    }

    public void create( ) {
        if ( player != null ) {
            player.stop();
            player.release();
        }
        metadataRetriever.setDataSource( MainActivity.self.getApplication().getApplicationContext(), Uri.parse( queue.getCurrentSong() ) );
        player = MediaPlayer.create( MainActivity.self.getApplication().getApplicationContext(), Uri.parse( queue.getCurrentSong() ) );
        player.setAudioStreamType( AudioManager.STREAM_MUSIC );
        int n = new Equalizer( 1, player.getAudioSessionId() ).getNumberOfBands();
        Debug.print( "Bands: " + n );
    }

    public void play( ) {
        if ( player.isPlaying() == false ) {
            player.start();

        } else {
            player.pause();
        }
    }

    public void next( ) {
        queue.setCurrent( queue.getCurrent()+1 );
        create( );
        play();
    }

    public void prev( ) {
        queue.setCurrent( queue.getCurrent()-1 );
        create( );
        play();
    }

    public void rewind( float ms ) {
        player.seekTo( (int) ( getPlayer().getDuration() * ms ) );
    }

}
