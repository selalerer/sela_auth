package com.selalerer.sela_auth.repository;

import com.selalerer.sela_auth.model.AuthorizationKey;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AuthorizationRepositoryBaseTest {

    protected abstract AuthorizationRepository getTestSubject();

    @Test
    public void saveIfNoneExist() {
        var testSubject = getTestSubject();

        assertTrue(testSubject.saveIfNoneExist(List.of()));

        assertTrue(testSubject.saveIfNoneExist(List.of(Map.entry(AuthorizationKey.builder()
                        .application("bank")
                        .accessedEntityType("bankAccount")
                        .accessedEntityProperty("accountOwner")
                        .accessedEntityPropertyValue("555667")
                        .userAccessKey("555667")
                        .build(),
                "RU"))));

        assertTrue(testSubject.saveIfNoneExist(List.of(Map.entry(AuthorizationKey.builder()
                        .application("bank")
                        .accessedEntityType("bankAccount")
                        .userAccessKey("bankEmployee")
                        .build(),
                "R"))));

        assertFalse(testSubject.saveIfNoneExist(List.of(Map.entry(AuthorizationKey.builder()
                        .application("bank")
                        .accessedEntityType("bankAccount")
                        .userAccessKey("bankEmployee")
                        .build(),
                "RU"))));
    }
}
