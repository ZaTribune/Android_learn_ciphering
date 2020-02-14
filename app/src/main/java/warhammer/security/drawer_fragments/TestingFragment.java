package warhammer.security.drawer_fragments;


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
    private String[] ciphers;
    private ImageView img1;
    public interface TestingInterface{
        public void itemClicked(int position);
    }
    private TestingInterface mInterface;


    public TestingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.testing_fragment, container, false);
        img1= getActivity().findViewById(R.id.img1);
        img1.setVisibility(View.INVISIBLE);
        lv1=(ListView) view.findViewById(R.id.lv1);
        //this way to solve the problem of getting NullpointerException when using the normal way {using the View object}
        action_bar_header=(TextView)getActivity().findViewById(R.id.action_bar_header);
        //to set the header back when returning via the back button
        if(!action_bar_header.equals("Testing"))
        action_bar_header.setText("Testing");
        ciphers=getResources().getStringArray(R.array.ciphers);
        lv1.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,ciphers));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  mInterface.itemClicked(position);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterface=(TestingInterface)context;
    }

//this one to clear the actionbar header when going back to the MainActivity using the back button
    @Override
    public void onDetach() {
        super.onDetach();
        action_bar_header.setText("");
        img1.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //this to clear the back stack


    }
}
