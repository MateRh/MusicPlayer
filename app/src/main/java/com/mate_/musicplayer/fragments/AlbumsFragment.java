package com.mate_.musicplayer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import com.mate_.musicplayer.MainActivity;
import com.mate_.musicplayer.R;
import com.mate_.musicplayer.adapters.AlubmImageAdapter;
import com.mate_.musicplayer.audio.Player;
import com.mate_.musicplayer.debug.Debug;
import com.mate_.musicplayer.library.Album;
import com.mate_.musicplayer.library.Artist;
import com.mate_.musicplayer.library.Queue;


public class AlbumsFragment extends Fragment {
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {


	        return inflater.inflate( R.layout.albums, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.main);

        final GridView gridview = (GridView) this.getActivity().findViewById(R.id.gridView1);
    //    gridview.setAdapter(new ImageAdapter(this));
        gridview.setAdapter(new AlubmImageAdapter( this.getContext() ));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View v, int position, long id) {

                TextView textView = (TextView) v.findViewById( R.id.textView );
                Artist artist = MainActivity.self.getCointainer().getArtist( );
                Album album = artist.getAlbum( (String) textView.getText() );
                Debug.print( "Album: " + textView.getText() );

                MainActivity.self.getCointainer().setPlayer( new Player( new Queue(  album.getSongs().values().toArray() ) ) );
                MainActivity.self.getCointainer().setAlbum( album );


            }
        });

    }

}