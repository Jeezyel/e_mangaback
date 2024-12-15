package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HashServiceImpl implements HashService {

    // sequencia aleatória a ser adicionada na senha
    private String salt = "#blahxyz17";
    // contagem de iteracoes
    private Integer iterationCount = 405;
    // comprimento do hash em bits
    private Integer keyLength = 512;    

    @ApplicationScoped
    public String getHashSenha(String senha) {
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(
                            new PBEKeySpec(senha.toCharArray(), salt.getBytes(), iterationCount, keyLength))
                    .getEncoded();
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }  
    }

    public static void main(String[] args) {
        HashService hashService = new HashServiceImpl();
        System.out.println("Hash para '123456': " + hashService.getHashSenha("123456")); //Para Usuario
        System.out.println("Hash para '123': " + hashService.getHashSenha("123")); //Para Admin
    }

}
