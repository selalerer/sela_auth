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

    @Test
    public void hasAllAuthorizations() {
        var testSubject = getTestSubject();

        assertFalse(testSubject.hasAllAuthorizations(List.of(Map.entry("R", AuthorizationKey.builder()
                .application("bank")
                .accessedEntityType("bankAccount")
                .userAccessKey("bankEmployee")
                .build()))));

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

        assertTrue(testSubject.hasAllAuthorizations(List.of(Map.entry("R", AuthorizationKey.builder()
                .application("bank")
                .accessedEntityType("bankAccount")
                .userAccessKey("bankEmployee")
                .build()))));

        assertTrue(testSubject.hasAllAuthorizations(List.of(Map.entry("R", AuthorizationKey.builder()
                .application("bank")
                .accessedEntityType("bankAccount")
                .accessedEntityProperty("accountOwner")
                .accessedEntityPropertyValue("555667")
                .userAccessKey("555667")
                .build()))));

        assertFalse(testSubject.hasAllAuthorizations(List.of(Map.entry("R", AuthorizationKey.builder()
                .application("bank")
                .accessedEntityType("bankAccount")
                .accessedEntityProperty("accountOwner")
                .accessedEntityPropertyValue("555667")
                .userAccessKey("12314")
                .build()))));

        assertFalse(testSubject.hasAllAuthorizations(List.of(
                Map.entry("R", AuthorizationKey.builder()
                        .application("bank")
                        .accessedEntityType("bankAccount")
                        .accessedEntityProperty("accountOwner")
                        .accessedEntityPropertyValue("555667")
                        .userAccessKey("12314")
                        .build()),
                Map.entry("R", AuthorizationKey.builder()
                        .application("bank")
                        .accessedEntityType("bankAccount")
                        .accessedEntityProperty("accountOwner")
                        .accessedEntityPropertyValue("555667")
                        .userAccessKey("555667")
                        .build())
        )));

        assertTrue(testSubject.hasAllAuthorizations(List.of(
                Map.entry("R", AuthorizationKey.builder()
                        .application("bank")
                        .accessedEntityType("bankAccount")
                        .accessedEntityProperty("accountOwner")
                        .accessedEntityPropertyValue("555667")
                        .userAccessKey("555667")
                        .build()),
                Map.entry("R", AuthorizationKey.builder()
                        .application("bank")
                        .accessedEntityType("bankAccount")
                        .userAccessKey("bankEmployee")
                        .build())
        )));
    }

    @Test
    public void saveIfHasAllAuthorizations() {
        var testSubject = getTestSubject();

        assertFalse(testSubject.saveIfHasAllAuthorizations(List.of(Map.entry(AuthorizationKey.builder()
                                .application("bank")
                                .accessedEntityType("bankAccount")
                                .accessedEntityProperty("accountOwner")
                                .accessedEntityPropertyValue("555667")
                                .userAccessKey("555667")
                                .build(),
                        "RU")),
                List.of(
                        Map.entry("R", AuthorizationKey.builder()
                                .application("bank")
                                .accessedEntityType("bankAccount")
                                .accessedEntityProperty("accountOwner")
                                .accessedEntityPropertyValue("555667")
                                .userAccessKey("555667")
                                .build())
                )));

        assertTrue(testSubject.saveIfNoneExist(List.of(Map.entry(AuthorizationKey.builder()
                        .application("bank")
                        .accessedEntityType("bankAccount")
                        .accessedEntityProperty("accountOwner")
                        .accessedEntityPropertyValue("555667")
                        .userAccessKey("555667")
                        .build(),
                "RU"))));

        assertTrue(testSubject.hasAllAuthorizations(List.of(
                Map.entry("R", AuthorizationKey.builder()
                        .application("bank")
                        .accessedEntityType("bankAccount")
                        .accessedEntityProperty("accountOwner")
                        .accessedEntityPropertyValue("555667")
                        .userAccessKey("555667")
                        .build())
        )));

        assertFalse(testSubject.hasAllAuthorizations(List.of(
                Map.entry("RUD", AuthorizationKey.builder()
                        .application("bank")
                        .accessedEntityType("bankAccount")
                        .accessedEntityProperty("accountOwner")
                        .accessedEntityPropertyValue("555667")
                        .userAccessKey("555667")
                        .build())
        )));

        assertTrue(testSubject.saveIfHasAllAuthorizations(
                List.of(Map.entry(AuthorizationKey.builder()
                                .application("bank")
                                .accessedEntityType("bankAccount")
                                .accessedEntityProperty("accountOwner")
                                .accessedEntityPropertyValue("555667")
                                .userAccessKey("555667")
                                .build(),
                        "RUD")),
                List.of(
                        Map.entry("UR", AuthorizationKey.builder()
                                .application("bank")
                                .accessedEntityType("bankAccount")
                                .accessedEntityProperty("accountOwner")
                                .accessedEntityPropertyValue("555667")
                                .userAccessKey("555667")
                                .build()))
        ));

        assertTrue(testSubject.hasAllAuthorizations(List.of(
                Map.entry("RUD", AuthorizationKey.builder()
                        .application("bank")
                        .accessedEntityType("bankAccount")
                        .accessedEntityProperty("accountOwner")
                        .accessedEntityPropertyValue("555667")
                        .userAccessKey("555667")
                        .build())
        )));

    }
}
