package warhammer.security.drawer_fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import warhammer.security.R;


public class TestingFragment extends Fragment {
    private ListView lv1;
    private TextView action_bar_header;
    public interface TestingInterface{
        void itemClicked(int position);
    }
    private TestingInterface mInterface;


    public TestingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.testing_fragment, container, false);
        lv1= view.findViewById(R.id.lv1);
        //to set the header back when returning via the back button
        if(!action_bar_header.getText().equals("Testing"))
            action_bar_header.setText(getString(R.string.testing));
        String[] ciphers = getResources().getStringArray(R.array.ciphers);
        lv1.setAdapter(new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, ciphers));
        lv1.setOnItemClickListener((parent, view1, position, id) -> mInterface.itemClicked(position));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterface=(TestingInterface)context;
        action_bar_header= ((Activity)context).findViewById(R.id.action_bar_header);
    }

//this one to clear the actionbar header when going back to the MainActivity using the back button
    @Override
    public void onDetach() {
        super.onDetach();
        action_bar_header.setText("");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //this to clear the back stack


    }
}
