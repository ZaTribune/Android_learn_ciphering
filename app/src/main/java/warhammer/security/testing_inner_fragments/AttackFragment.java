package warhammer.security.testing_inner_fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import warhammer.security.R;
import warhammer.security.algorithms.Caesar;

public class AttackFragment extends Fragment {

    private EditText et_attack;
    private Button btn_attack;
    private RelativeLayout layout;
    private TextView[] attempts;

    private int cipher;
    public int getCipher() {
        return cipher;
    }

    public void setCipher(int cipher) {
        this.cipher = cipher;
    }
    public AttackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.attack_fragment, container, false);
        et_attack=(EditText)view.findViewById(R.id.et_attack);
        btn_attack=(Button)view.findViewById(R.id.btn_attack);
        layout=(RelativeLayout)view.findViewById(R.id.attack_fragment);
        attempts=new TextView[26];
        et_attack.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                   if(count==0){
                       btn_attack.setEnabled(false);
                       btn_attack.setClickable(false);
                       btn_attack.setBackgroundResource(R.drawable.btn_passion_disabled);
                       btn_attack.setTextColor(Color.GRAY);
                   }else{
                       btn_attack.setEnabled(true);
                       btn_attack.setClickable(true);
                       btn_attack.setBackgroundResource(R.drawable.btn_passion_enabled);
                       btn_attack.setTextColor(Color.YELLOW);
                   }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Caesar caesar=new Caesar();

                for (int i=1; i<=25; i++){
                    //case u made another process
                    if(attempts[i]!=null)
                        layout.removeView(attempts[i]);

                    attempts[i] = new TextView(getContext());
                    attempts[i].setId(i+1);
                    attempts[i].setText("shift "+i+" : "+caesar.decrypt(et_attack.getText().toString(),i)+"   <<");
                    RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
                            LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                    if(i==1){
                        relativeParams.addRule(RelativeLayout.BELOW,R.id.btn_attack);
                        attempts[i].setLayoutParams(relativeParams);
                        layout.addView(attempts[i]);
                        //The continue keyword causes the loop to immediately jump to the next iteration of the loop
                        continue;
                    }
                    relativeParams.addRule(RelativeLayout.BELOW,attempts[i-1].getId());
                    Log.i(attempts[i-1].getText().toString(),""+attempts[i-1].getId());
                    attempts[i].setLayoutParams(relativeParams);
                    layout.addView(attempts[i]);

                }
            }
        });

        return view;
    }
    public static AttackFragment newInstance(int cipher) {
        AttackFragment fragment = new AttackFragment();
        fragment.setCipher(cipher);
        return fragment;
    }


}
