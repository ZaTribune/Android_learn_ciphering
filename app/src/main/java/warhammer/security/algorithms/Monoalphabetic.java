package warhammer.security.algorithms;

/**
 * Created by Raid_2209ee on 21/03/2017.
 */

public class Monoalphabetic {
    public static char chars[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z'};
    public static char shuffle[] = {'Q','W','E','R','D',
                               'T','Y','U','I','F',
                               'O','P','A','S','G',
                               'H','J','K','L','Z',
                               'X','C','V','B','N','M'
            };

    public static String encrypt(String plain) {
        char cipher[] = new char[(plain.length())];
        for (int i = 0; i < plain.length(); i++) {
            //i is the counter for the plaintext
            for (int j = 0; j < 26; j++) {
                //for each character in the plaintext...if both
                if (chars[j] == plain.charAt(i)) {
                    cipher[i] = shuffle[j];
                    break;
                }
            }
        }
        return (new String(cipher));
    }

    public static String decrypt(String cipher) {
        char plain[] = new char[(cipher.length())];
        for (int i = 0; i < cipher.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (shuffle[j] == cipher.charAt(i)) {
                    plain[i] = chars[j];
                    break;
                }
            }
        }
        return (new String(plain));
    }
}
