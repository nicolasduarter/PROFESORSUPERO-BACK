package eci.edu.dows.profesorSuperO.SecurityTest;

import eci.edu.dows.profesorSuperO.Util.Security.PasswordSecurityUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordSecurityUtilTest {

    private final PasswordSecurityUtil passwordSecurityUtil = new PasswordSecurityUtil();

    @Test
    void hashPassword_deberiaGenerarValorDistintoYValidable() {
        String raw = "ClaveSegura123!";

        String hashed = passwordSecurityUtil.hashPassword(raw);

        assertNotEquals(raw, hashed);
        assertTrue(passwordSecurityUtil.matches(raw, hashed));
        assertFalse(passwordSecurityUtil.matches("otra-clave", hashed));
    }
}
