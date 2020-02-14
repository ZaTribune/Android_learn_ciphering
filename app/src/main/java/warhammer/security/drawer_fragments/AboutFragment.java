package warhammer.security.drawer_fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import warhammer.security.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
    private ImageView img1;
    private TextView action_bar_header;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        img1=(ImageView)getActivity().findViewById(R.id.img1);
        img1.setVisibility(View.INVISIBLE);
        View view=inflater.inflate(R.layout.learning_fragment, container, false);
        action_bar_header=(TextView)getActivity().findViewById(R.id.action_bar_header);


        return view;
    }


    //this one to clear the actionbar header when going back to the MainActivity using the back button
    @Override
    public void onDetach() {
        super.onDetach();
        action_bar_header.setText("");
        img1.setVisibility(View.VISIBLE);
    }

}
