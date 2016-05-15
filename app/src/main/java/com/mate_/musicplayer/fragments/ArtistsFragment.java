package com.mate_.musicplayer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.mate_.musicplayer.MainActivity;
import com.mate_.musicplayer.R;
import com.mate_.musicplayer.adapters.ImageAdapter;
import com.mate_.musicplayer.audio.Player;
import com.mate_.musicplayer.library.Album;
import com.mate_.musicplayer.library.Artist;
import com.mate_.musicplayer.library.Queue;


public class ArtistsFragment extends Fragment {
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {


	        return inflater.inflate( R.layout.artists, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.main);

        final GridView gridview = (GridView) this.getActivity().findViewById(R.id.gridView1);
    //    gridview.setAdapter(new ImageAdapter(this));
        gridview.setAdapter(new ImageAdapter( this.getContext() ));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick( AdapterView<?> parent, View v, int position, long id) {

            TextView textView = (TextView) v.findViewById( R.id.textView );
            Artist artist = MainActivity.self.getCointainer().getArtists( (String)textView.getText() );
          //  Album album = ( Album ) artist.getAlbums().firstEntry().getValue();
         //   MainActivity.self.getCointainer().setPlayer( new Player( new Queue(  album.getSongs().values().toArray() ) ) );
            MainActivity.self.getCointainer().setArtist( artist );
            MainActivity.self.switchFragment( new AlbumsFragment(), true );

            }
        });

            //
              //  MainActivity.self.getCointainer().setQueue( new Queue( MainActivity.self.getCointainer().getArtists().keySet().toArray() ) );


    }

}