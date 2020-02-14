package warhammer.security.testing_inner_fragments;


import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import warhammer.security.R;
import warhammer.security.algorithms.Caesar;
import warhammer.security.algorithms.DES;
import warhammer.security.algorithms.Monoalphabetic;
import warhammer.security.algorithms.RC4;

/**
 * A simple {@link Fragment} subclass.
 */
public class DecryptFragment extends Fragment {
    private EditText et_before_text;
    private Button btn_process;
    private TextView tv_result;
    private TextView tv_shift;
    private int cipher;
    private Spinner spinner1;
    private ViewSwitcher vs_key;
    private EditText et_key;
    private Button btn_key;
    //these members are dedicated for the DEA
    private DES des;
    private RC4 rc4;
    private SecretKey key;

    public SecretKey getKey() {
        return key;
    }

    public void setKey(SecretKey key) {
        this.key = key;
    }

    public DES getDes() {
        return des;
    }

    public void setDes(DES des) {
        this.des = des;
    }

    public RC4 getRc4() {
        return rc4;
    }

    public void setRc4(RC4 rc4) {
        this.rc4 = rc4;
    }

    public DecryptFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.encrypt_fragment, container, false);
        et_before_text =(EditText)view.findViewById(R.id.et_before_text);
        btn_process=(Button)view.findViewById(R.id.btn_process);
        btn_key =(Button) view.findViewById(R.id.btn_key);
        tv_result=(TextView)view.findViewById(R.id.tv_result);
        tv_shift=(TextView)view.findViewById(R.id.tv_shift);
        spinner1=(Spinner) view.findViewById(R.id.spinner1);

        if(getCipher()==0){
            spinner1.setVisibility(View.VISIBLE);
            tv_shift.setVisibility(View.VISIBLE);
        }
        //case it's DES
        if(getCipher()==5){
            setDes(new DES());
            //disable the process button before generating the key
            btn_process.setEnabled(false);
            btn_process.setBackgroundResource(R.drawable.btn_passion_disabled);
            btn_process.setTextColor(Color.GRAY);
            vs_key=(ViewSwitcher)view.findViewById(R.id.vs_key);
            //show the next child --> EditText
            vs_key.showNext();
            //we reference the child EdtText to its father
            et_key =(EditText) vs_key.findViewById(R.id.et_key);
            btn_key.setText("Enter A key");
            btn_key.setVisibility(View.VISIBLE);
            btn_key.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        vs_key.setVisibility(View.VISIBLE);
                        //
                        btn_process.setEnabled(true);
                        btn_process.setBackgroundResource(R.drawable.btn_passion_enabled);
                        btn_process.setTextColor(Color.YELLOW);

                }
            });
        }
            if(getCipher()==6){
                setRc4(new RC4());
                btn_process.setEnabled(false);
                btn_process.setBackgroundResource(R.drawable.btn_passion_disabled);
                btn_process.setTextColor(Color.GRAY);
                btn_key =(Button) view.findViewById(R.id.btn_key);

                //the first child is the default one
                vs_key=(ViewSwitcher)view.findViewById(R.id.vs_key);
                vs_key.showNext();
                //we reference the child EdtText to its father
                et_key =(EditText)vs_key.findViewById(R.id.et_key);
                //
                btn_key.setVisibility(View.VISIBLE);
                btn_key.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            vs_key.setVisibility(View.VISIBLE);
                            btn_process.setEnabled(true);
                            btn_process.setBackgroundResource(R.drawable.btn_passion_enabled);
                            btn_process.setTextColor(Color.YELLOW);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        btn_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    processRequest(getCipher());
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
    public static DecryptFragment newInstance(int cipher) {
        DecryptFragment fragment = new DecryptFragment();
        fragment.setCipher(cipher);
        return fragment;
    }
    public int getCipher() {
        return cipher;
    }

    public void setCipher(int cipher) {
        this.cipher = cipher;
    }

    private void processRequest(int cipher) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        String plaintext=null;
        switch (cipher){
            case 0:
                Caesar caesar =new Caesar();
                spinner1.setVisibility(View.VISIBLE);
                tv_result.setText(caesar.decrypt(et_before_text.getText().toString(),spinner1.getSelectedItemPosition()));
                break;
            case 1:
                Monoalphabetic mono =new Monoalphabetic();
                tv_result.setText(mono.decrypt(et_before_text.getText().toString()));
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:
                if(et_before_text.getText().length()==0) {
                    Toast.makeText(getContext(),"Nothing to be decrypted",Toast.LENGTH_SHORT).show();
                    return;
                }

                String key= et_key.getText().toString();
                //converting the key back again to its byte[] form
                byte[] decodedKey = Base64.decode(key,Base64.NO_WRAP);
                setKey(new SecretKeySpec(decodedKey,0, decodedKey.length,"DES/ECB/PKCS5Padding"));
                Log.i("see",et_before_text.getText().toString().substring(5));
                plaintext=getDes().decrypt(et_before_text.getText().toString(),getKey());
                tv_result.setText(plaintext);
                //Log.i("result",plaintext);
                break;
            case 6:
                if(et_before_text.getText().length()==0) {
                    Toast.makeText(getContext(),"Nothing to be encrypted",Toast.LENGTH_SHORT).show();
                    return;
                }
                plaintext=getRc4().process(et_before_text.getText().toString(),et_key.getText().toString());
                tv_result.setText(plaintext);



                break;
            case 7:

                break;
        }
        //view the result box if something happened
        tv_result.setVisibility(View.VISIBLE);

    }

}
