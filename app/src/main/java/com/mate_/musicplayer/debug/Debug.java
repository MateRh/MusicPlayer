package com.mate_.musicplayer.debug;

import android.util.Log;

/**
 * Created by Mate_ on 09.05.2016.
 */
public class Debug {

    public static void print( String text ) {
        Log.wtf( "[MUSICPLAYER] Debug", text );
    }


}
