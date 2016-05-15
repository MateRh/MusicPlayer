package com.mate_.musicplayer.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mate_.musicplayer.MainActivity;
import com.mate_.musicplayer.R;

public class AlubmImageAdapter extends BaseAdapter {
   private Context mContext;

   // Constructor
   public AlubmImageAdapter( Context c) {
      mContext = c;
   }

   public int getCount() {
      return MainActivity.self.getCointainer().getArtist( ).getAlbums().size();
   }

   public Object getItem(int position) {
      return null;
   }

   public long getItemId(int position) {
      return 0;
   }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View grid;
            LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {

                grid = new View(mContext);
                grid = inflater.inflate(R.layout.albumsgrid, null);
                TextView textView = (TextView) grid.findViewById(R.id.textView );
                ImageView imageView = (ImageView)grid.findViewById( R.id.grid_image );
                String artist = MainActivity.self.getCointainer().getArtist( ).getName( );
                String album = (String) MainActivity.self.getCointainer().getArtist().getAlbums().keySet().toArray()[position];
                textView.setText( album );

                Bitmap bitmap1 = BitmapFactory.decodeFile( MainActivity.self.getFilesDir( ).toString( ) + "/images/" + artist + "/" + album );
                    if ( bitmap1 != null ) {
                        imageView.setScaleType( ImageView.ScaleType.CENTER_CROP );
                        imageView.setImageBitmap(  bitmap1 );
                    } else {
                        imageView.setImageResource( R.drawable.art );
                    }


            } else {
                grid = (View) convertView;
            }

            return grid;
        }

   // Keep all Images in array
   public Integer[] mThumbIds = {
      R.drawable.art, R.drawable.art,
      R.drawable.art, R.drawable.art

   };
}