package warhammer.security.testing_inner_fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import warhammer.security.R;
import warhammer.security.adapters.ViewPagerAdapter;



public class CipheringFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView action_bar_header;
    private Bundle bundle;
    private int cipher;
    private Button clear;

    public int getCipher() {
        return cipher;
    }

    public void setCipher(int cipher) {
        this.cipher = cipher;
    }



   public CipheringFragment(){
       // Required empty public constructor
   }

    public CipheringFragment(int cipher) {
        setCipher(cipher);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ciphering_fragment, container, false);
        Bundle bundle=getArguments();

        try {
            cipher=bundle.getInt("cipher");
        }catch (NullPointerException e){
            //in case we used the clear button instead of the usual way
            cipher=getCipher();
        }

        viewPager= view.findViewById(R.id.viewpager);
        tabLayout= view.findViewById(R.id.tabs);
        ViewPagerAdapter adapter=new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(EncryptFragment.newInstance(cipher),"Encrypt");
        adapter.addFragment(DecryptFragment.newInstance(cipher),"Decrypt");
        if(getCipher()==0)
            adapter.addFragment(AttackFragment.newInstance(cipher),"Attack");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //to change the header
        action_bar_header= ((Activity)context).findViewById(R.id.action_bar_header);
        action_bar_header.setText("Testing "+getResources().getStringArray(R.array.ciphers)[getCipher()]);
        // to add the capability of clearing the screen
        clear= ((Activity)context).findViewById(R.id.btn_clear);
        clear.setVisibility(View.VISIBLE);
        clear.setOnClickListener(v -> {
            FragmentManager manager=getFragmentManager();
            int count = manager.getBackStackEntryCount();
            FragmentTransaction fragmentTransaction=manager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame,new CipheringFragment(getCipher())).addToBackStack("Ciphering");
            manager.popBackStack();
            fragmentTransaction.commit();
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clear.setVisibility(View.GONE);
    }
}
