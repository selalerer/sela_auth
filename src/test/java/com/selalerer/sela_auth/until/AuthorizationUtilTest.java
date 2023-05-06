package com.selalerer.sela_auth.until;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationUtilTest {

    @Test
    public void hasAuth() {
        assertTrue(AuthorizationUtil.hasAuth(null, "CRUD"));
        assertTrue(AuthorizationUtil.hasAuth("", "CRUD"));
        assertFalse(AuthorizationUtil.hasAuth("RU", ""));
        assertFalse(AuthorizationUtil.hasAuth("RU", null));

        assertTrue(AuthorizationUtil.hasAuth("C", "C"));
        assertTrue(AuthorizationUtil.hasAuth("R", "CR"));
        assertTrue(AuthorizationUtil.hasAuth("R", "CRU"));
        assertTrue(AuthorizationUtil.hasAuth("R", "CRUD"));

        assertFalse(AuthorizationUtil.hasAuth("UR", "C"));
        assertFalse(AuthorizationUtil.hasAuth("UR", "CR"));
        assertTrue(AuthorizationUtil.hasAuth("UR", "CRU"));
        assertTrue(AuthorizationUtil.hasAuth("UR", "CRUD"));
    }

    @Test
    public void isValid() {
        assertFalse(AuthorizationUtil.isValid(null));
        assertFalse(AuthorizationUtil.isValid(""));

        assertTrue(AuthorizationUtil.isValid("C"));
        assertTrue(AuthorizationUtil.isValid("R"));
        assertTrue(AuthorizationUtil.isValid("U"));
        assertTrue(AuthorizationUtil.isValid("D"));

        assertFalse(AuthorizationUtil.isValid("T"));
        assertFalse(AuthorizationUtil.isValid("TA"));
        assertFalse(AuthorizationUtil.isValid("TR"));
        assertFalse(AuthorizationUtil.isValid("B"));

        assertTrue(AuthorizationUtil.isValid("CRUD"));
        assertTrue(AuthorizationUtil.isValid("RUD"));
        assertTrue(AuthorizationUtil.isValid("UD"));
        assertTrue(AuthorizationUtil.isValid("CD"));

        assertFalse(AuthorizationUtil.isValid("TRUD"));
        assertFalse(AuthorizationUtil.isValid("CRUG"));
        assertFalse(AuthorizationUtil.isValid("CRUDTR"));
        assertFalse(AuthorizationUtil.isValid("BUD"));
    }

    @Test
    public void validate() {
        AuthorizationUtil.validate("CRUD");

        var e = assertThrows(ResponseStatusException.class,
                () -> AuthorizationUtil.validate("CRUG"));

        assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        assertTrue(e.getMessage().contains("CRUG"));

        assertThrows(ResponseStatusException.class,
                () -> AuthorizationUtil.validate(null));
        assertThrows(ResponseStatusException.class,
                () -> AuthorizationUtil.validate(""));
    }
}