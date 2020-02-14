package warhammer.security.algorithms;


import android.util.Base64;
import android.util.Log;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Created by Raid_2209ee on 21/03/2017.
 */

public class DES {
//A secret (symmetric) key. The purpose of this interface is to group (and provide type safety for) all secret key interfaces.
    public SecretKey getKey() throws NoSuchAlgorithmException {
        /*
        This class provides the functionality of a secret (symmetric) key generator.
        Key generators are constructed using one of the getInstance class methods of this class.
        */
        KeyGenerator keygenerator = KeyGenerator.getInstance("DES");//Returns a KeyGenerator object that generates secret keys for the specified algorithm. بحيث ان كل الجوزم للتشفير بيكون ليها كود سترينج
        //Generates a secret key.
        SecretKey myDesKey = keygenerator.generateKey();
        return myDesKey;
    }

    public String encrypt(String plaintext,SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        //This class provides the functionality of a cryptographic cipher for encryption and decryption.
        //In order to create a Cipher object, the application calls the Cipher's getInstance method, and passes the name of the requested transformation to it
        //A transformation is a string that describes the operation (or set of operations) to be performed on the given input, to produce some output.
        //(Algorithm /Modes /Paddings)
        Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");//Electronic Code Book
        c.init(Cipher.ENCRYPT_MODE, key);//Constant used to initialize cipher to encryption mode.
        byte[] encVal = c.doFinal(plaintext.getBytes());//Finishes a multiple-part encryption or decryption operation, depending on how this cipher was initialized.
        return Base64.encodeToString(encVal, Base64.NO_WRAP);
    }

    public String decrypt(String ciphertext,SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, key);
        //converting the ciphertext String back to byte[]
        byte[] decode1 = Base64.decode(ciphertext.getBytes(),Base64.NO_WRAP);
        //then decrypting the byte[]
        byte[] decrypt = c.doFinal(decode1);
        //back again to String
        String encode1=Base64.encodeToString(decrypt,Base64.NO_WRAP);
        //but this String still represents a byte[] values that are not recognisable
        return new String(Base64.decode(encode1.getBytes(),0,encode1.length(),Base64.NO_WRAP));

    }


}

