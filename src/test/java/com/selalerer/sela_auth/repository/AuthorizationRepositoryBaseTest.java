package com.selalerer.sela_auth.repository;

import com.selalerer.sela_auth.model.AuthorizationKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AuthorizationRepositoryBaseTest {

    protected abstract AuthorizationRepository getTestSubject();

    private static final AuthorizationKey ANY_BANK_EMPLOYEE_TO_ANY_ACCOUNT = AuthorizationKey.builder()
            .application("bank")
            .accessedEntityType("bankAccount")
            .accessedEntityId("*")
            .accessingEntityType("employeeRole")
            .accessingEntityId("*")
            .build();

    private static final AuthorizationKey BANK_MANAGER_TO_ANY_ACCOUNT = AuthorizationKey.builder()
            .application("bank")
            .accessedEntityType("bankAccount")
            .accessedEntityId("*")
            .accessingEntityType("employeeRole")
            .accessingEntityId("manager")
            .build();

    private static final AuthorizationKey CLIENT_887_ACCESS_TO_ACCOUNT_555667 = AuthorizationKey.builder()
            .application("bank")
            .accessedEntityType("bankAccount")
            .accessedEntityId("555667")
            .accessingEntityType("bankClient")
            .accessingEntityId("7878")
            .build();

    @Test
    public void hasAuth() {
        var testSubject = getTestSubject();

        testSubject.save(ANY_BANK_EMPLOYEE_TO_ANY_ACCOUNT, "R");
        testSubject.save(BANK_MANAGER_TO_ANY_ACCOUNT, "CRUD");
        testSubject.save(CLIENT_887_ACCESS_TO_ACCOUNT_555667, "RU");

        assertFalse(testSubject.hasAuthorization(ANY_BANK_EMPLOYEE_TO_ANY_ACCOUNT, "C"));
        assertTrue(testSubject.hasAuthorization(BANK_MANAGER_TO_ANY_ACCOUNT, "C"));
        assertFalse(testSubject.hasAuthorization(CLIENT_887_ACCESS_TO_ACCOUNT_555667, "C"));

        assertTrue(testSubject.hasAuthorization(ANY_BANK_EMPLOYEE_TO_ANY_ACCOUNT, "R"));
        assertTrue(testSubject.hasAuthorization(BANK_MANAGER_TO_ANY_ACCOUNT, "R"));
        assertTrue(testSubject.hasAuthorization(CLIENT_887_ACCESS_TO_ACCOUNT_555667, "R"));

        assertFalse(testSubject.hasAuthorization(ANY_BANK_EMPLOYEE_TO_ANY_ACCOUNT, "U"));
        assertTrue(testSubject.hasAuthorization(BANK_MANAGER_TO_ANY_ACCOUNT, "U"));
        assertTrue(testSubject.hasAuthorization(CLIENT_887_ACCESS_TO_ACCOUNT_555667, "U"));
    }

}
