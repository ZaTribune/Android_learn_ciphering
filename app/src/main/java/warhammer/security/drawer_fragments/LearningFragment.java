package warhammer.security.drawer_fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import warhammer.security.R;

public class LearningFragment extends Fragment {
    private TextView action_bar_header;

    public LearningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.learning_fragment, container, false);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        action_bar_header= ((Activity)context).findViewById(R.id.action_bar_header);
    }

    //this one to clear the actionbar header when going back to the MainActivity using the back button
    @Override
    public void onDetach() {
        super.onDetach();
    }

}
