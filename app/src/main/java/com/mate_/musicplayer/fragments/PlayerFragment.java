package com.mate_.musicplayer.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.mate_.musicplayer.MainActivity;
import com.mate_.musicplayer.R;


public class PlayerFragment extends Fragment {
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {

       // updateTrackInfo( );
        return inflater.inflate( R.layout.player, container, false );
    }

    public void updateTrackInfo( ) {
        if ( MainActivity.self.getCointainer().getPlayer() != null ) {
            String artist = MainActivity.self.getCointainer().getArtist().getName();
            String album = MainActivity.self.getCointainer().getAlbum().getName();
            ImageView image = ( ImageView ) getActivity().findViewById( R.id.imageView4 );
            Bitmap bitmap = BitmapFactory.decodeFile( MainActivity.self.getFilesDir().toString() + "/images/" + artist + "/" + album );
            if ( bitmap != null ) {
                image.setScaleType( ImageView.ScaleType.CENTER_INSIDE );
                image.setImageBitmap( bitmap );
                image.setBackgroundColor( 0 );
            }

            TextView title = ( TextView ) getActivity().findViewById( R.id.title );

            title.setText( MainActivity.self.getCointainer().getPlayer().getMetadataRetriever().extractMetadata( MediaMetadataRetriever.METADATA_KEY_ARTIST ) );
            TextView albumArtist = ( TextView ) getActivity().findViewById( R.id.artistalbum );
            albumArtist.setText( MainActivity.self.getCointainer().getPlayer().getMetadataRetriever().extractMetadata( MediaMetadataRetriever.METADATA_KEY_TITLE ) );
            TextView album_ = ( TextView ) getActivity().findViewById( R.id.textView2 );
            album_.setText(  MainActivity.self.getCointainer().getPlayer().getMetadataRetriever().extractMetadata( MediaMetadataRetriever.METADATA_KEY_ALBUM ) );
            TextView trackLenght = ( TextView ) getActivity().findViewById( R.id.trackLenght );
            trackLenght.setText( DateUtils.formatElapsedTime( MainActivity.self.getCointainer().getPlayer().getPlayer().getDuration() / 1000 ) );
        }
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        ImageButton play = (ImageButton) getActivity().findViewById( R.id.playButton );
        play.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                updateTrackInfo( );
                MainActivity.self.getCointainer().getPlayer().play();

            }
        } );
        ImageButton next = (ImageButton) getActivity().findViewById( R.id.next );
        next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                MainActivity.self.getCointainer().getPlayer().next();
                updateTrackInfo( );
            }
        } );

        ImageButton prev = (ImageButton) getActivity().findViewById( R.id.prev );
        prev.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                MainActivity.self.getCointainer().getPlayer().prev();
                updateTrackInfo( );
            }
        } );
        SeekBar seekBar = ( SeekBar ) getActivity().findViewById( R.id.seekBar2 );
        seekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged( SeekBar seekBar, int progress, boolean fromUser ) {
                float p = progress/1000000.0f;
                MainActivity.self.getCointainer().getPlayer().rewind( p );
            }

            @Override
            public void onStartTrackingTouch( SeekBar seekBar ) {

            }

            @Override
            public void onStopTrackingTouch( SeekBar seekBar ) {

            }
        } );
       Typeface custom_font = Typeface.createFromAsset( MainActivity.self.getAssets(), "font.ttf");
        TextView title = ( TextView ) getActivity().findViewById( R.id.title );
        title.setTypeface( custom_font );
        title = ( TextView ) getActivity().findViewById( R.id.artistalbum );
        title.setTypeface( custom_font );
        title = ( TextView ) getActivity().findViewById( R.id.textView2 );
        title.setTypeface( custom_font );
        title = ( TextView ) getActivity().findViewById( R.id.trackTime );
        title.setTypeface( custom_font );
        title = ( TextView ) getActivity().findViewById( R.id.trackLenght );
        title.setTypeface( custom_font );
        title = ( TextView ) getActivity().findViewById( R.id.info );
        title.setTypeface( custom_font );
    }



}

