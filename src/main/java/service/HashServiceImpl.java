package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HashServiceImpl implements HashService {

    @Override
    public String getHashSenha(String senha) {
        try {
            // Usar SHA-256 para gerar o hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(senha.getBytes());
            StringBuilder hexString = new StringBuilder();

            // Converter o hash em uma string hexadecimal
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }

    public static void main(String[] args) {
        HashServiceImpl hashService = new HashServiceImpl();

        // Exemplos de hash
        System.out.println("Hash para '123456': " + hashService.getHashSenha("123456"));
        System.out.println("Hash para '123': " + hashService.getHashSenha("123"));

    }
}
