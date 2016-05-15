package com.mate_.musicplayer.engine;


import android.os.Environment;
import com.mate_.musicplayer.MainActivity;
import com.mate_.musicplayer.debug.Debug;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class SearchEngine {


    public SearchEngine( String name ) {

        try{
            String url_ = "http://www.picsearch.com/index.cgi?q=" + name + "%20band&sz=medium";
            URL url = new URL( url_ );
            URLConnection connection = url.openConnection();
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream()) );
            while((line = reader.readLine()) != null) {
                builder.append( line);
            }
            String object = builder.toString();
            object = object.substring( object.indexOf( "results_table" ) );
            object = object.substring( object.indexOf( "<a href=\"" )+9, object.indexOf( "\" onclick" ) );
            object = "http://www.picsearch.com/" + object;
            Debug.print( object );

            URL url2 = new URL( object );
            URLConnection urlConnection = url2.openConnection();
            StringBuilder builder2 = new StringBuilder();
            BufferedReader reader2 = new BufferedReader(new InputStreamReader( urlConnection.getInputStream()) );
            while((line = reader2.readLine()) != null) {
                builder2.append( line);
            }
            String object2 = builder2.toString();
            object2 = object2.substring( object2.indexOf( "Website for this image" ) );
            object2 = object2.substring( object2.indexOf( "href=" )+6, object2.indexOf( "\">Full-size" ) );
            Debug.print( object2 );


            String root = MainActivity.self.getFilesDir( ).getAbsolutePath();
            saveFile( object2, name );


            Debug.print( Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES ).getPath() );

        } catch(Exception e){

            e.printStackTrace();
        }
    }

    public void saveFile( String urlString, String name ) {
        try {
            URL wallpaperURL = new URL( urlString );
            URLConnection connection = wallpaperURL.openConnection();
            InputStream inputStream = new BufferedInputStream(wallpaperURL.openStream(), 10240);
            File cacheDir = MainActivity.self.getFilesDir();
            cacheDir = new File( cacheDir, name );
            cacheDir.mkdir();
            File cacheFile = new File( cacheDir, "artist" );
            FileOutputStream outputStream = new FileOutputStream(cacheFile);
            byte buffer[] = new byte[1024];
            int dataSize;
            int loadedSize = 0;
               while ((dataSize = inputStream.read(buffer)) != -1) {
                   loadedSize += dataSize;
                   outputStream.write(buffer, 0, dataSize);
               }

               outputStream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
