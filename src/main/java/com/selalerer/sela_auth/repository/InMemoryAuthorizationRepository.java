package com.selalerer.sela_auth.repository;

import com.selalerer.sela_auth.model.AuthorizationKey;
import com.selalerer.sela_auth.until.AuthorizationUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InMemoryAuthorizationRepository implements AuthorizationRepository {
    private final Map<AuthorizationKey, String> repo = new HashMap<>();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    @Override
    public void save(Collection<Map.Entry<AuthorizationKey, String>> authorizations) {
        authorizations.forEach(e -> AuthorizationUtil.validate(e.getValue()));
        synchronized (rwLock.writeLock()) {
            authorizations.forEach(e -> repo.put(e.getKey(), e.getValue()));
        }
    }

    @Override
    public boolean saveIfNoneExist(Collection<Map.Entry<AuthorizationKey, String>> authorizations) {
        synchronized (rwLock.writeLock()) {
            if (authorizations.stream().anyMatch(e -> repo.containsKey(e.getKey()))) {
                return false;
            }
            save(authorizations);
            return true;
        }
    }

    @Override
    public boolean hasAllAuthorizations(String requiredAuthorizations, Collection<AuthorizationKey> entities) {
        synchronized (rwLock.readLock()) {
            return entities.stream().map(repo::get)
                    .allMatch(entityAuth -> AuthorizationUtil.hasAuth(requiredAuthorizations, entityAuth));
        }
    }

    @Override
    public boolean saveIfHasAllAuthorizations(Collection<Map.Entry<AuthorizationKey, String>> authorizationsToSave,
                                              String requiredAuthorizations,
                                              Collection<AuthorizationKey> entities) {
        synchronized (rwLock.writeLock()) {
            if (!hasAllAuthorizations(requiredAuthorizations, entities)) {
                return false;
            }
            save(authorizationsToSave);
            return true;
        }
    }
}
