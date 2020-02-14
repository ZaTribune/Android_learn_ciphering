package warhammer.security.algorithms;

import android.util.Log;

/**
 * Created by Raid_2209ee on 29/03/2017.
 */

public class PlayFair {

    private String KeyWord = new String();
    private String Key = new String();
    //having size of matrix 5*5 this
    private char matrix_arr[][] = new char[5][5];

    //
    public void setKey(String key) {
        String adjusted_Key = new String();
        boolean flag = false;
        //will take the first char as initial value
        adjusted_Key = adjusted_Key + key.charAt(0);
        //first we'll have this loop to remove duplicates
        // loops for each char in the key
        for (int i = 1; i < key.length(); i++) {
            //loops with # equals chars I have read
            for (int j = 0; j < adjusted_Key.length(); j++) {
                if (key.charAt(i) == adjusted_Key.charAt(j)) {
                    flag = true;
                }
            }
            if (flag == false)
                //here we add chars to the adjusted_Key if it's not duplicate
                adjusted_Key = adjusted_Key + key.charAt(i);

            flag = false;
        }//end of the first loop

        KeyWord = adjusted_Key;
    }

    public void KeyGen() {
        //here we fulfill the playfair char matrix

        boolean flag = true;
        char current;
        Key = KeyWord;
        for (int i = 0; i < 26; i++) {
            //the first character in the ASCII
            current = (char) (i + 97);
            //special case of j as we combine it eith i in a single cell in the matrix
            if (current == 'j')
                continue;//break this loop and move to the next one
            for (int j = 0; j < KeyWord.length(); j++) {
                //here we compare letters of the key with all alphabets
                if (current == KeyWord.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag)//is true
                //now it will concatenate the new char to my adjustedKey
                Key = Key + current;
            flag = true;
        }
        Log.i("key", Key);
        matrix();
    }

    //here it creates a 5*5 matrix and store the whole new key
    private void matrix() {
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix_arr[i][j] = Key.charAt(counter);
                System.out.print(matrix_arr[i][j] + " ");
                counter++;
            }
            System.out.println();
        }
    }

    private String format(String old_text) {
        int i = 0;
        int length = 0;
        String text = new String();
        length = old_text.length();
        //here we check for (j)
        for (int tmp = 0; tmp < length; tmp++) {
            //if there's a (j) replace it with (i)
            if (old_text.charAt(tmp) == 'j') {
                text = text + 'i';
            } else
                text = text + old_text.charAt(tmp);
        }
        length = text.length();
        for (i = 0; i < length; i = i + 2) {
            //if there's two similar chars-->insert x between them
            if (text.charAt(i + 1) == text.charAt(i)) {
                text = text.substring(0, i + 1) + 'x' + text.substring(i + 1);
            }
        }
        return text;
    }

    //text is divided into pairs of chars
    private String[] Divid2Pairs(String new_string) {
        String Original = format(new_string);
        int size = Original.length();
        //if their number is odd
        if (size % 2 != 0) {
            size++;
            Original = Original + 'x';
        }
        String x[] = new String[size / 2];
        int counter = 0;
        for (int i = 0; i < size / 2; i++) {
            x[i] = Original.substring(counter, counter + 2);
            counter = counter + 2;
        }
        return x;
        //so, if we finally had 10 chars we divide them to 5 strings each has 2 char size
    }

    public int[] GetDiminsions(char letter) {

        //to get the position of a char
        int[] key = new int[2];
        if (letter == 'j')
            letter = 'i';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix_arr[i][j] == letter) {
                    key[0] = i;
                    key[1] = j;
                    break;
                }
            }
        }
        return key;
    }

    public String encrypt(String Source) {
        String src_arr[] = Divid2Pairs(Source);
        String Code = new String();
        char one;
        char two;
        int part1[] = new int[2];
        int part2[] = new int[2];
        for (int i = 0; i < src_arr.length; i++) {
            one = src_arr[i].charAt(0);
            two = src_arr[i].charAt(1);
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
            if (part1[0] == part2[0]) {
                if (part1[1] < 4)
                    part1[1]++;
                else
                    part1[1] = 0;
                if (part2[1] < 4)
                    part2[1]++;
                else
                    part2[1] = 0;
            } else if (part1[1] == part2[1]) {
                if (part1[0] < 4)
                    part1[0]++;
                else
                    part1[0] = 0;
                if (part2[0] < 4)
                    part2[0]++;
                else
                    part2[0] = 0;
            } else {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Code = Code + matrix_arr[part1[0]][part1[1]]
                    + matrix_arr[part2[0]][part2[1]];
        }
        return Code;
    }
}
