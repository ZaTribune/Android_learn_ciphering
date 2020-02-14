package warhammer.security.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import warhammer.security.R;

/**
 * Created by Raid_2209ee on 29/03/2017.
 */

public class AllAdapter extends BaseAdapter {
    private int[] images;
    private String[] texts;
    private LayoutInflater inflater;

    public AllAdapter(Context context,int[] images,String[] texts){
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.images=images;
        this.texts=texts;
    }

    @Override
    public int getCount() {
        return texts.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(R.layout.my_list_item_1,null);
        ImageView img= view.findViewById(R.id.list_item_1_img);
        TextView txt= view.findViewById(R.id.list_item_1_txt);
        img.setImageResource(images[position]);
        txt.setText(texts[position]);
        return view;
    }
}
