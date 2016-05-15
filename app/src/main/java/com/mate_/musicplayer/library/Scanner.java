package com.mate_.musicplayer.library;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import com.mate_.musicplayer.elements.Cointainer;
import com.mate_.musicplayer.engine.InformationParser;
import com.mate_.musicplayer.engine.SearchEngine;


import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mate_ on 01.05.2016.
 */
public class Scanner {

    public Scanner( Cointainer cointainer, ContentResolver cr ) {

            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
            String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
            Cursor cur = cr.query(uri, null, selection, null, sortOrder);
            int count = 0;

            if(cur != null)
            {
                count = cur.getCount();

                if(count > 0)
                {
                    while(cur.moveToNext())
                    {
                        String data = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                        String display = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA));
                        String album = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                        String title = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE));
                        // Add code to get more column here
                        if ( data.indexOf( "unknow" ) != -1 || data.indexOf( "no artist" ) != -1 ) {
                            continue;
                        }

                        if ( data != null ){
                            if ( cointainer.getArtists( data ) == null ) {
                                Artist artist =  new Artist( data );
                                Album album_ = new Album( album );
                                album_.addSong( title, display );
                                artist.addAlbum( album, album_ );
                                cointainer.addArtists( data, new Artist( data ) );
                                if ( data.indexOf( "unknow" ) == -1 ) {
                                    InformationParser.getImageArtist( data );
                                }

                            } else {

                                Artist artist = cointainer.getArtists( data );

                                    if ( artist.getAlbum( album ) == null ) {
                                        Album album_ = new Album( album );
                                        album_.addSong( title, display );
                                        artist.addAlbum( album, album_ );
                                        InformationParser.getAlbumImage( data, album );
                                    } else {

                                        artist.getAlbum( album ).addSong( title, display );
                                    }

                            }
                        }

                        // Save to your list here
                    }

                }
            }

            cur.close();




    }

}
