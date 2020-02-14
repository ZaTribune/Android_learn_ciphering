package warhammer.security.algorithms;

import java.io.Console;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA {
    //big integer because results are greatly high
  private  BigInteger n, d, e;
  private  BigInteger p,q;
  private  int bits;
 //minimum size of message to be encrypted

  /** Create an instance that can both encrypt and decrypt. */
  public RSA(int bits,BigInteger e) {
      this.bits=bits;
      this.e = e;// e must be prime and the greatest common divisor between e and phi =1

  }

  public String encrypt(String message) {
      SecureRandom r = new SecureRandom();//This class provides a cryptographically strong random number generator

      p = new BigInteger(bits / 2, 100, r);//Constructs a random BigInteger instance in the range [0,pow(2,bitLength)-1] which is probably prime.
      q = new BigInteger(bits / 2, 100, r);
      n = p.multiply(q);//n=p*q
      BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q
              .subtract(BigInteger.ONE));// phi= (p-1)*(q-1)

      while (phi.gcd(e).intValue() > 1) {
          e = e.add(new BigInteger("2"));// if phi.gcd(e)>1 then e=e+2;
      }
      d = e.modInverse(phi);// d=e^-1 mod(phi)

    return (new BigInteger(message.getBytes())).modPow(e, n).toString();//c = m^e mod(n)
  }


  /** Decrypt the given ciphertext message. */
  public synchronized String decrypt(String message) {


    return new String((new BigInteger(message)).modPow(d, n).toByteArray());
  }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }
}
