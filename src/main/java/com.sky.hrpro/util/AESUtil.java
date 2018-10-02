package com.sky.hrpro.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * @Author: CarryJey
 * @Date: 2018/9/27 下午4:55
 *
 * web端传输数据AES加密工具
 *
 */

public class AESUtil {

    private static final String TAG = AESUtil.class.getName();
    private static final String salt = "a8jLOIwW0+1PfSgPtuNN/A==";
    private static final String password = "O8UEYUt3w0KLg|@E";

    private static final String key = "c20690f234fad460";
    private static final String iv = "4e9ca3944a485a01";

    public static String generatKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] key = secretKey.getEncoded();


            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            return Base64.getEncoder().encodeToString(key);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String encrypt(String value) {

        try {
            byte[] secretKeyBytes = getSecretKey(salt);
            SecretKey key = new SecretKeySpec(secretKeyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(getIV(secretKeyBytes)));
            byte[] encodeValue = cipher.doFinal(value.getBytes());

            return Base64.getEncoder().encodeToString(encodeValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String value) {
        try {
            byte[] secretKeyBytes = getSecretKey(salt);
            SecretKey key = new SecretKeySpec(secretKeyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(getIV(secretKeyBytes)));
            byte[] decryptValue = cipher.doFinal(Base64.getDecoder().decode(value));

            LoggerUtils.info(TAG, "De:" + new String(decryptValue));
            return new String(decryptValue);
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.error(TAG, e.getMessage());
        }
        return null;
    }

    private static byte[] getSecretKey(String salt) {
        try {
            int keyLength = 128;
            int iterationCount = 1000;
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), Base64.getDecoder().decode(salt), iterationCount, keyLength);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();
            SecretKey key = new SecretKeySpec(keyBytes, "AES");

            return key.getEncoded();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.error(TAG, e.getMessage());
        }

        return null;
    }

    private static byte[] getIV(byte[] key) {
        byte[] iv = new byte[16];
        System.arraycopy(key, 0, iv, 0, 16);
        return iv;
    }


    public static byte[] AES_CBC_Encrypt(byte[]content, byte[] keyBytes, byte[] iv){
        try{
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,key, new IvParameterSpec(iv));
            byte[]result=cipher.doFinal(content);
            return result;
        }catch (Exception e) {
            System.out.println("exception:"+e.toString());
        }
        return null;
    }

    public static byte[] AES_CBC_Decrypt(byte[]content, byte[] keyBytes, byte[] iv){
        try{
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,key, new IvParameterSpec(iv));
            byte[]result=cipher.doFinal(content);
            return result;
        }catch (Exception e) {
            // TODO Auto-generated catchblock
            System.out.println("exception:"+e.toString());
        }
        return null;
    }


}
