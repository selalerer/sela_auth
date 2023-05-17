package com.selalerer.sela_auth.repository;

import com.selalerer.sela_auth.model.AuthorizationKey;

import java.util.Collection;
import java.util.Map;

public interface AuthorizationRepository {
    /**
     * @param authorization The values are the string "CRUD" or a part of it
     */
    void save(AuthorizationKey key, String authorization);
    boolean hasAuthorization(AuthorizationKey key, String authorization);
}
