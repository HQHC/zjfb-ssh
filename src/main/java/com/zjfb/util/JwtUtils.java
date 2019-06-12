package com.zjfb.util;

import java.sql.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JwtUtils {
	/**
     * ǩ��JWT
     * @param id
     * @param subject ������JSON���� ��������
     * @param ttlMillis
     * @return  String
     *
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)   // ����
                .setIssuer("user")     // ǩ����
                .setIssuedAt(now)      // ǩ��ʱ��
                .signWith(signatureAlgorithm, secretKey); // ǩ���㷨�Լ��ܳ�
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // ����ʱ��
        }
        return builder.compact();
    }


    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64("zjfb");
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    
    /**
     * 
     * ����JWT�ַ���
     * @param jwt
     * @return
     * @throws Exception
     */
    public static String parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(jwt)
            .getBody()
            .getSubject();
    }

}
