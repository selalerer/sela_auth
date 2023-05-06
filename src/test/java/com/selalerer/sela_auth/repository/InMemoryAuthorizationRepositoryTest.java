package com.selalerer.sela_auth.repository;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryAuthorizationRepositoryTest extends AuthorizationRepositoryBaseTest {

    @Override
    protected AuthorizationRepository getTestSubject() {
        return new InMemoryAuthorizationRepository();
    }
}