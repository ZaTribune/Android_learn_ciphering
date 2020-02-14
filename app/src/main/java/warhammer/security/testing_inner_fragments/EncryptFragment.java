package warhammer.security.testing_inner_fragments;


import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import warhammer.security.R;
import warhammer.security.algorithms.Caesar;
import warhammer.security.algorithms.DES;
import warhammer.security.algorithms.Monoalphabetic;
import warhammer.security.algorithms.PlayFair;
import warhammer.security.algorithms.RC4;
import warhammer.security.algorithms.RSA;

/**
 * A simple {@link Fragment} subclass.
 */
public class EncryptFragment extends Fragment {
    private EditText et_before_text;
    private Button btn_process;
    private TextView tv_result;
    private TextView tv_shift;
    private int cipher;
    private Spinner spinner1;
    private Button btn_key;
    private ViewSwitcher vs_key;
    private TextView tv_key;
    private EditText et_key;
    //these members are dedicated for the DEA
    private DES des;
    private RC4 rc4;
    private RSA rsa;
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

    public RSA getRsa() {
        return rsa;
    }

    public void setRsa(RSA rsa) {
        this.rsa = rsa;
    }

    public EncryptFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.encrypt_fragment, container, false);
        et_before_text =(EditText)view.findViewById(R.id.et_before_text);
        btn_process =(Button)view.findViewById(R.id.btn_process);
        tv_result =(TextView)view.findViewById(R.id.tv_result);
        prepareLayout(view);
        btn_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    processRequest(getCipher());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    public static EncryptFragment newInstance(int cipher) {
        EncryptFragment fragment = new EncryptFragment();
        fragment.setCipher(cipher);
        return fragment;
    }

    public int getCipher() {
        return cipher;
    }

    public void setCipher(int cipher) {
        this.cipher = cipher;
    }

    public View prepareLayout(View view){
        switch(getCipher()){
            case 0://case it's Caesar
                tv_shift =(TextView)view.findViewById(R.id.tv_shift);
                spinner1=(Spinner) view.findViewById(R.id.spinner1);
                spinner1.setVisibility(View.VISIBLE);
                tv_shift.setVisibility(View.VISIBLE);
                break;
            case 1:


                break;
            case 2:

                break;
            case 3://case it's PlayFair
                btn_key =(Button) view.findViewById(R.id.btn_key);
                btn_key.setVisibility(View.VISIBLE);
                btn_key.setEnabled(false);
                btn_key.setBackgroundResource(R.drawable.btn_passion_disabled);
                btn_key.setTextColor(Color.GRAY);
                vs_key=(ViewSwitcher)view.findViewById(R.id.vs_key);
                et_key=(EditText)view.findViewById(R.id.et_key);
                btn_process.setEnabled(false);
                btn_process.setBackgroundResource(R.drawable.btn_passion_disabled);
                btn_process.setTextColor(Color.GRAY);
                btn_key.setText("Enter a key");
                vs_key.showNext();

                et_before_text.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(count==0){
                            btn_key.setEnabled(false);
                            btn_key.setBackgroundResource(R.drawable.btn_passion_disabled);
                            btn_key.setTextColor(Color.GRAY);
                        }else{
                            btn_key.setEnabled(true);
                            btn_key.setBackgroundResource(R.drawable.btn_passion_enabled);
                            btn_key.setTextColor(Color.YELLOW);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable s) {}
                });
                btn_key.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vs_key.setVisibility(View.VISIBLE);
                    }
                });
                et_key.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(count==0){
                            btn_process.setEnabled(false);
                            btn_process.setBackgroundResource(R.drawable.btn_passion_disabled);
                            btn_process.setTextColor(Color.GRAY);
                        }else {
                            btn_process.setEnabled(true);
                            btn_process.setBackgroundResource(R.drawable.btn_passion_enabled);
                            btn_process.setTextColor(Color.YELLOW);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable s) {}
                });
                break;
            case 4:

                break;
            case 5://case it's DES
                setDes(new DES());
                //disable the process button before generating the key
                btn_process.setEnabled(false);
                btn_process.setBackgroundResource(R.drawable.btn_passion_disabled);
                btn_process.setTextColor(Color.GRAY);
                btn_key =(Button) view.findViewById(R.id.btn_key);
                //the first child is the default one
                vs_key=(ViewSwitcher)view.findViewById(R.id.vs_key);
                //we reference the child EdtText to its father
                tv_key =(TextView) vs_key.findViewById(R.id.tv_key);
                //

                btn_key.setVisibility(View.VISIBLE);
                btn_key.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            setKey(getDes().getKey());
                            vs_key.setVisibility(View.VISIBLE);

                            tv_key.setText(Base64.encodeToString(getKey().getEncoded(),Base64.NO_WRAP));
                            btn_process.setEnabled(true);
                            btn_process.setBackgroundResource(R.drawable.btn_passion_enabled);
                            btn_process.setTextColor(Color.YELLOW);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case 6:
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
                break;
            case 7:

                btn_process.setEnabled(false);
                btn_process.setBackgroundResource(R.drawable.btn_passion_disabled);
                btn_process.setTextColor(Color.GRAY);
                btn_key =(Button) view.findViewById(R.id.btn_key);
                btn_key.setText("Enter Encryption Key");
                //the first child is the default one
                vs_key=(ViewSwitcher)view.findViewById(R.id.vs_key);
                vs_key.showNext();
                et_key =(EditText)vs_key.findViewById(R.id.et_key);
                et_key.setInputType(InputType.TYPE_CLASS_NUMBER);//takes numbers only
                et_key.setHint(new String("prime"));
                //we reference the child EdtText to its father

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

                break;

        }

        return view;
    }


    private void processRequest(int cipher) throws NoSuchAlgorithmException, IllegalBlockSizeException,
            InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        String ciphertext=null;
        switch (cipher){
            case 0:
                Caesar caesar =new Caesar();
                tv_result.setText(caesar.encrypt(et_before_text.getText().toString(),spinner1.getSelectedItemPosition()));
                break;
            case 1:
                Monoalphabetic mono =new Monoalphabetic();
                tv_result.setText(mono.encrypt(et_before_text.getText().toString()));
                break;
            case 2:

                break;
            case 3:
                PlayFair playFair = new PlayFair();
                if(et_key.getText().length()!=0){
                    playFair.setKey(et_key.getText().toString());
                }else {
                    Toast.makeText(getContext(),"Enter a key",Toast.LENGTH_SHORT).show();
                    return;
                }
                playFair.KeyGen();
                tv_result.setText(playFair.encrypt(et_before_text.getText().toString()));
                break;
            case 4:

                break;
            case 5://DES
                if(et_before_text.getText().length()==0) {
                    Toast.makeText(getContext(),"Nothing to be encrypted",Toast.LENGTH_SHORT).show();
                    return;
                }
                ciphertext=getDes().encrypt(et_before_text.getText().toString(),getKey());
                tv_result.setText(ciphertext);
                break;
            case 6://RC4
                if(et_before_text.getText().length()==0) {
                    Toast.makeText(getContext(),"Nothing to be encrypted",Toast.LENGTH_SHORT).show();
                    return;
                }
                ciphertext=getRc4().process(et_before_text.getText().toString(),et_key.getText().toString());
                tv_result.setText(ciphertext);

                break;
            case 7:
                setRsa(new RSA(1024,new BigInteger(et_key.getText().toString())));
                int check=Integer.parseInt(String.valueOf(et_key.getText().toString()));

                if(!isPrime(check)){
                    Toast.makeText(getContext(),"specify a prime number",Toast.LENGTH_LONG).show();
                    return;
                }
                if(et_before_text.getText().length()==0) {
                    Toast.makeText(getContext(),"Nothing to be encrypted",Toast.LENGTH_SHORT).show();
                    return;
                }
                ciphertext=getRsa().encrypt(et_before_text.getText().toString());
                tv_result.setText(ciphertext);
                break;
        }
        //view the result box if something happened
        tv_result.setVisibility(View.VISIBLE);
    }
    boolean isPrime(int n) {
        for(int i=2;i<n;i++) {
            if(n%i==0)
                return false;
        }
        return true;
    }


}
