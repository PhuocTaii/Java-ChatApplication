/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.service;

import com.btv.User.ClientSocket;
import com.btv.User.helper.MessageType;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONObject;

/**
 *
 * @author tvan
 */
public class SecurityService {
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(3072);
        return keyPairGenerator.generateKeyPair();
    }
    
    public static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
    }
    
    public static String decrypt(byte[] cipherText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(cipherText);
        return new String(result);
    }
    
    public static void storeKey(Key key, Path filePath) throws Exception {
        byte[] keyBytes = key.getEncoded();
        Files.write(filePath, keyBytes);
    }
    
    public static PrivateKey readPrivateKey(Path filePath) throws Exception {
        byte[] keyBytes = Files.readAllBytes(filePath);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }
    
    public static PublicKey readPublicKey(Byte[] keyBytesObj) throws Exception {
        byte[] keyBytes = ArrayUtils.toPrimitive(keyBytesObj);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }
    
    public static void storePublicKeyInDB(PublicKey key) {
        byte[] keyBytes = key.getEncoded();
        
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try {
            clientSocket.dataOut.write(MessageType.STORE_PUBLIC_KEY.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            
            JSONObject messObj = new JSONObject();
            messObj.put("key", Base64.getEncoder().encodeToString(keyBytes));
            clientSocket.dataOut.write(messObj.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
