package com.selalerer.sela_auth.repository;

import com.selalerer.sela_auth.model.AuthorizationKey;
import com.selalerer.sela_auth.util.AuthorizationUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InMemoryAuthorizationRepository implements AuthorizationRepository {
    private final Map<AuthorizationKey, String> repo = new HashMap<>();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    @Override
    public void save(AuthorizationKey key, String authorization) {
        AuthorizationUtil.validate(authorization);
        synchronized (rwLock.writeLock()) {
            repo.put(key, authorization);
        }
    }

    @Override
    public boolean hasAuthorization(AuthorizationKey key, String authorization) {
        synchronized (rwLock.readLock()) {
            return AuthorizationUtil.hasAuth(authorization, repo.get(key));
        }
    }
}
