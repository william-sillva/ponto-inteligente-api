package com.nautilus.pontointeligente.api.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {

    private static final String SENHA = "123456";

    private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();

    @Test
    public void testSenhaNull() throws Exception {
        Assertions.assertNull(PasswordUtils.gerarBcrypt(null));
    }

    @Test
    public void testGerarHashSenha() throws Exception {
        String hash = PasswordUtils.gerarBcrypt(SENHA);
        Assertions.assertTrue(bCryptEncoder.matches(SENHA, hash));
    }
}
