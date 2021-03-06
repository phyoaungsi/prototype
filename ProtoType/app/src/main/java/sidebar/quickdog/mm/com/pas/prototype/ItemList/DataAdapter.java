package sidebar.quickdog.mm.com.pas.prototype.ItemList;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import sidebar.quickdog.mm.com.pas.prototype.R;

/**
 * Created by phyo on 06/10/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private String[] mDataset;
    private Context mContext;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = v;
        }
    }


    public DataAdapter(String[] myDataset) {
        mDataset = myDataset;
    }


    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
       // TextView v = (TextView) LayoutInflater.from(parent.getContext())
       //         .inflate(R.layout.my_text_view, parent, false);
        mContext=parent.getContext();
        View v= (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_view,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        TextView tv = (TextView) holder.mTextView.findViewById(R.id.info_text);

        tv.setText(mDataset[position]);
        ImageView iv = (ImageView) holder.mTextView.findViewById(R.id.imgProduct);
        int[] imageSet = {R.drawable.star_war, R.drawable.image2};
        iv.setImageResource(imageSet[position]);


        final ImageButton b1 = (ImageButton) holder.mTextView.findViewById(R.id.btnPlayer);
        int[] audioSet = {R.raw.blues, R.raw.music};
        final int audioFile = audioSet[position];
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    AudioPlayer.initMediaPlayer(audioFile, mContext);




            }
        };
        b1.setOnClickListener(clickListener);


        ImageButton b2 = (ImageButton) holder.mTextView.findViewById(R.id.btnPause);


        View.OnClickListener clickListener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int icon;
                if (AudioPlayer.getInstance().isPlaying()) {
                    if (AudioPlayer.currentRes == audioFile) {
                        AudioPlayer.getInstance().pause();
                    }
                }
            }
        };
        b2.setOnClickListener(clickListener2);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

