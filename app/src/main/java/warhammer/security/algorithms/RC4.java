package warhammer.security.algorithms;

public class RC4 {

    public String process(String text,String key) {


        int temp = 0;
        //this is the state vector with a size of 256 bytes and contains numbers  from 0->255
        //its size can be changed but has a maximum of 255
        int S[] = new int[256];
        //this is the Temporary vector ,must be the same size as the state vector
        int T[] = new int[256];
        char ptextc[] = text.toCharArray();// the char[] representation of our message
        char keyc[] = key.toCharArray();// the char[] representation of our key
        int ptexti[] = new int[text.length()];// the int[] representation of our message
        int keyi[] = new int[key.length()];// the char[] representation of our message

        //initializing an int[] for the encryption result
        int cipher[] = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            //here we convert all the characters into their corresponding integers
            ptexti[i] = (int) ptextc[i];
        }
        for (int i = 0; i < key.length(); i++) {
            //here we convert all the characters into their corresponding integers
            keyi[i] = (int) keyc[i];
        }
        for (int i = 0; i < 255; i++) {//because the state vector size is 256 bytes
            S[i] = i;
            T[i] = keyi[i % key.length()];//this operation ensures that the key will fill the Temporary vector's value and cover it all
            /*
            S=[1,2,3,4,5]
            K=[2,5,7]
            then
            T=[2,5,7,2,5,7]
             */
        }

        //starting the initial permutation
        int j = 0;
        for (int i = 0; i < 255; i++) {//# of iterations is equal to the S[] or T[] -->logically both these vectors are equal
            // indeed at iteration (1) both values of j&i =0
            j = (j + S[i] + T[i]) % 256;
            //followed by a swap operation
            temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }//resulting S[] vector will be used later with the message
        int i = 0;
        j = 0;

        int temp2 = 0;//this is another temporary int
        for (int l = 0; l < text.length(); l++) {
            i = (l + 1) % 256;
            j = (j + S[i]) % 256;
            //followed by a swap operation
            temp = S[i];
            S[i] = S[j];
            S[j] = temp;

            temp2 = (S[i] + S[j]) % 256;// the result is XORed with s[Temp2]
              //cipher[l] = temp2 ^ ptexti[l]; // the operator ^ is used to perform XOR
            cipher[l] = S[temp2] ^ ptexti[l];
        }
            return String.copyValueOf(convert(cipher));

    }

    static char[] convert(int intArray[]) {
        //converts an int[] into char[]
        char converted[] = new char[intArray.length];
        for (int l = 0; l < intArray.length; l++) {
            converted[l] = (char) intArray[l];
        }
        return converted;



    }

}
