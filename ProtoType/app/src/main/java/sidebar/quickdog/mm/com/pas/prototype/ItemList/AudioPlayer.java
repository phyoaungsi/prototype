package sidebar.quickdog.mm.com.pas.prototype.ItemList;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.util.Log;

import java.io.IOException;

/**
 * Created by phyo on 06/10/2017.
 */

public class AudioPlayer {
    private static final AudioPlayer ourInstance = new AudioPlayer();
    private static final String TAG ="PLAYER" ;
    private static MediaPlayer mMediaPlayer;
    public static int currentRes;
    public static MediaPlayer getInstance() {

        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }
        return mMediaPlayer;
    }

    private AudioPlayer() {
    }


    public static void initMediaPlayer(int resid,Context context) {
        // Setup media player, but don't start until user clicks button!
        currentRes=resid;
        try {
            if (mMediaPlayer == null) {
                mMediaPlayer = new MediaPlayer();
            } else {
                mMediaPlayer.reset();   // so can change data source etc.
            }
            //mMediaPlayer.setOnErrorListener();
           // AssetFileDescriptor afd = context.getAssets().openFd(mediafile);
            AssetFileDescriptor afd=context.getResources().openRawResourceFd(resid);
            mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
        }
        catch (IllegalStateException e) {
            Log.d(TAG, "IllegalStateException: " + e.getMessage());
        }
        catch (IOException e) {
            Log.d(TAG, "IOException: " + e.getMessage());
        }
        catch (IllegalArgumentException e) {
            Log.d(TAG, "IllegalArgumentException: " + e.getMessage());
        }
        catch (SecurityException e) {
            Log.d(TAG, "SecurityException: " + e.getMessage());
        }

        MediaPlayer.OnPreparedListener listener=new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
               mMediaPlayer.start();
            }
        };
        mMediaPlayer.setOnPreparedListener(listener);
        mMediaPlayer.prepareAsync(); // prepare async to not block main thread
       // mMediaPlayer.setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK);  // Keep playing when screen goes off!
    }
}
