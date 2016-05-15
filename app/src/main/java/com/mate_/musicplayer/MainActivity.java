package com.mate_.musicplayer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import com.mate_.musicplayer.elements.Cointainer;
import com.mate_.musicplayer.fragments.ArtistsFragment;
import com.mate_.musicplayer.fragments.PlayerFragment;
import com.mate_.musicplayer.library.Scanner;


public class MainActivity extends AppCompatActivity {

    public static MainActivity self;
    private Cointainer cointainer = new Cointainer();
    private VelocityTracker mVelocityTracker = null;

    public Cointainer getCointainer() {
        return cointainer;
    }


    public void switchFragment( Object element, boolean backStack ) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.fragment, (Fragment ) element );
            if ( backStack == true ) {
                transaction.addToBackStack( null );
            }
        transaction.commit();
    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
      /*  Toolbar myToolbar = (Toolbar ) findViewById(R.id.my_toolbar);
        myToolbar.setLogo( R.mipmap.ic_launcher );
        myToolbar.setTitle( "MusicPlayer" );
        myToolbar.setCollapsible( true );
         setSupportActionBar(myToolbar);*/
        self = this;
        new Thread(new Runnable() {
            public void run() {

                new Scanner( cointainer, getContentResolver() );


            }
        }).start();
        switchFragment( new PlayerFragment(), false );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action1:

                switchFragment(  new PlayerFragment(), true );

                return true;
            case R.id.action0:

                switchFragment(  new ArtistsFragment(), true );

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}


