package com.selalerer.sela_auth.repository;

import com.selalerer.sela_auth.model.AuthorizationKey;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface AuthorizationRepository {
    /**
     * @param authorizations The values are the string "CRUD" or a part of it
     */
    void save(Collection<Map.Entry<AuthorizationKey, String>> authorizations);
    boolean saveIfNoneExist(Collection<Map.Entry<AuthorizationKey, String>> authorizations);

    boolean hasAllAuthorizations(Collection<Map.Entry<String, AuthorizationKey>> requiredAuthorizations);

    boolean saveIfHasAllAuthorizations(Collection<Map.Entry<AuthorizationKey, String>> authorizationsToSave,
                                       Collection<Map.Entry<String, AuthorizationKey>> requiredAuthorizations);
}
