package ece651.duke.invenstory;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by jonbuie on 2/21/16.
 * adapted from http://developer.android.com/guide/topics/media/camera.html#custom-camera
 */
public class PhotoActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if(null == savedInstanceState){
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, ThumbnailGenFragment.newInstance())
                    .commit();
        }
    }
}
