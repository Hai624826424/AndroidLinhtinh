package linhtinh.sea.mh.linhtinh.View;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import linhtinh.sea.mh.linhtinh.Adapter.ChatAdapter;
import linhtinh.sea.mh.linhtinh.Utility.KeysUtils;
import linhtinh.sea.mh.linhtinh.Utility.Utils;

/**
 * Created by WIN-HAIVM on 5/14/18.
 */

public class ChatScreen {
    String name = "";
    ChatScreen mFriend = null;

    RecyclerView rcl;
    ChatAdapter mAdapter;

    private ArrayList<String> mMessageList = new ArrayList<>();

    public ChatScreen(RecyclerView rcl, String name) {
        this.rcl = rcl;
        this.name = name;
        init();
    }

    private void init() {
        mAdapter = new ChatAdapter(mMessageList);
        rcl.setAdapter(mAdapter);
        generateAESKey();
        generateKeys();
    }

    public void setFriend(ChatScreen friend) {
        mFriend = friend;
    }


    public void sendMessage(String s) {
        String encrypt = KeysUtils.encryptToRSAString(s, mFriend.getPublicKey());
        mFriend.receiveMessage(encrypt);
    }

    public void receiveMessage(String s) {
        String decrypt = KeysUtils.decryptToRSAString(s, privateKey);
        mMessageList.add(decrypt);
        mAdapter.notifyDataSetChanged();
    }

    private PrivateKey privateKey;
    private PublicKey publicKey;

    private void generateKeys() {
        KeyPair kp = KeysUtils.getKeyPair();
        privateKey = kp.getPrivate();
        publicKey = kp.getPublic();
    }

    SecretKey mAESKey;
    private void generateAESKey() {
        try {
//            Get the KeyGenerator instance for AES
            KeyGenerator keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

//            Create a KeyGenParameterSpec builder and
//            set the alias and different purposes of the key
            KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(
                    "mySecretKey01",
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT);

//            The KeyGenParameterSpec is how parameters for your key are passed to the
//            generator.Choose wisely !
            builder
                    .setKeySize(256)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);

//            Generate and store the key
            keyGenerator.init(builder.build());
            mAESKey = keyGenerator.generateKey();
            Log.e("AESKEY", "123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
