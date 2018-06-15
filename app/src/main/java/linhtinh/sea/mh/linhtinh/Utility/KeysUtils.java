package linhtinh.sea.mh.linhtinh.Utility;

import android.util.Base64;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by WIN-HAIVM on 5/14/18.
 */

public class KeysUtils {
    public static KeyPair getKeyPair() {
        KeyPair kp = null;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            kp = kpg.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kp;
    }

    public static String encryptToRSAString(String clearText, Key publicKey) {
        String encryptedBase64 = "";
        try {
//            KeyFactory keyFac = KeyFactory.getInstance("RSA");
//            KeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKey.trim().getBytes(), Base64.DEFAULT));
//            Key key = keyFac.generatePublic(keySpec);
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance("RSA");
            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(clearText.getBytes("UTF-8"));
            encryptedBase64 = new String(Base64.encode(encryptedBytes, Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedBase64.replaceAll("(\\r|\\n)", "");
    }

    public static String decryptToRSAString(String encryptText, Key privateKey) {
        String encryptedBase64 = encryptText;
        encryptText = encryptText.substring(0, 15);
        try {
//            KeyFactory keyFac = KeyFactory.getInstance("RSA");
//            KeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKey.trim().getBytes(), Base64.DEFAULT));
//            Key key = keyFac.generatePublic(keySpec);
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance("RSA");
            // encrypt the plain text using the public key
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] enc = encryptText.getBytes("UTF-8");
            byte[] encryptedBytes = cipher.doFinal(enc);
            encryptedBase64 = new String(encryptedBytes);


//            cipher1 = Cipher.getInstance("RSA");
//            cipher1.init(Cipher.DECRYPT_MODE, privateKey);
//            decryptedBytes = cipher1.doFinal(encryptedBytes);
//            decrypted = new String(decryptedBytes);
//            System.out.println("DDecrypted?????" + decrypted);
//            return decrypted;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedBase64/*.replaceAll("(\\r|\\n)", "")*/;
    }
}
