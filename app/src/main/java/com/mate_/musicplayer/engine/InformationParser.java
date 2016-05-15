package com.mate_.musicplayer.engine;

import com.mate_.musicplayer.MainActivity;
import com.mate_.musicplayer.debug.Debug;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Mate_ on 09.05.2016.
 */
public class InformationParser {

    private static String API_KEY = "ed76870808ad65e09858aad8877e0607";

    public static void saveFile( String urlString, String name, String prefix ) {
        try {
            URL wallpaperURL = new URL( urlString );
            URLConnection connection = wallpaperURL.openConnection();
            InputStream inputStream = new BufferedInputStream(wallpaperURL.openStream(), 10240);
            File cacheDir = MainActivity.self.getFilesDir();
            cacheDir = new File( cacheDir, "images" );
            cacheDir.mkdir();
            cacheDir = new File( cacheDir, name );
            cacheDir.mkdir();
            File cacheFile = new File( cacheDir, prefix );
            FileOutputStream outputStream = new FileOutputStream(cacheFile);
            byte buffer[] = new byte[1024];
            int dataSize;
            int loadedSize = 0;
               while ((dataSize = inputStream.read(buffer)) != -1) {
                   loadedSize += dataSize;
                   outputStream.write(buffer, 0, dataSize);
               }

               outputStream.close();
            Debug.print( cacheFile.getPath() );
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isExist( String name, String prefix ) {
        return  new File(  MainActivity.self.getFilesDir( ), "images/" + name + "/" + prefix ).exists();
    }

    public static void getImageArtist( String name ) {
        if ( isExist( name, "artist" ) == false ) {
            try {
                String nameUrl = name.replaceAll( " ", "%20" );
                URL url = new URL( "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=" + nameUrl + "&api_key=" + API_KEY + "&format=json" );
                URLConnection connection = url.openConnection();
                String line;
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
                while ( ( line = reader.readLine() ) != null ) {
                    builder.append( line );
                }
                JSONObject json = new JSONObject( builder.toString() );
                String imageUrl = json.getJSONObject( "artist" ).getJSONArray( "image" ).getJSONObject( 3 ).getString( "#text" );
                Debug.print( imageUrl );
                saveFile( imageUrl, name, "artist" );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }

    public static void getAlbumImage( String name, String album ) {
        if ( isExist( name, album ) == false ) {
            try {
                String nameUrl = name.replaceAll( " ", "%20" );
                String albumUrl = album.replaceAll( " ", "%20" );
                URL url = new URL( "http://ws.audioscrobbler.com/2.0/?method=album.getinfo&&artist=" + nameUrl + "&album=" + albumUrl +"&api_key=" + API_KEY + "&format=json" );
                URLConnection connection = url.openConnection();
                String line;
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
                while ( ( line = reader.readLine() ) != null ) {
                    builder.append( line );
                }
                JSONObject json = new JSONObject( builder.toString() );
                String imageUrl = json.getJSONObject( "album" ).getJSONArray( "image" ).getJSONObject( 4 ).getString( "#text" );
                Debug.print( imageUrl );
                saveFile( imageUrl, name, album );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }

}
