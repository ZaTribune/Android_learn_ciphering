package warhammer.security.drawer_fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
    private TextView action_bar_header;
    private TextView tv_twitter,tv_github,tv_linkedin;
    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.about_fragment, container, false);
        tv_twitter=view.findViewById(R.id.tv_twitter);
        tv_github=view.findViewById(R.id.tv_github);
        tv_linkedin=view.findViewById(R.id.tv_linkedin);
        tv_twitter.setOnClickListener(v -> {
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(getString(R.string.twitter)));
            startActivity(intent);
        });
        tv_github.setOnClickListener(v -> {
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(getString(R.string.github)));
            startActivity(intent);
        });
        tv_linkedin.setOnClickListener(v -> {
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(getString(R.string.linkedin)));
            startActivity(intent);
        });

        return view;
    }


    //this one to clear the actionbar header when going back to the MainActivity using the back button

    @Override
    public void onDetach() {
        super.onDetach();
        action_bar_header.setText("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        action_bar_header= ((Activity)context).findViewById(R.id.action_bar_header);
        action_bar_header.setText(getString(R.string.about));
    }
}
