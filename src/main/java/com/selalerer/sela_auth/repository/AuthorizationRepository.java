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

    /**
     * This can be used to check permission to do one action on one entity or to check in one transaction permission
     * to do multiple actions on multiple entities.
     *
     * @param requiredAuthorizations A collection of required authorization strings (e.g. "RU" or "CRUD") and an
     *                               authorization key that need to have these authorizations.
     * @return true if all authorization strings satisfied the authorization of their respective authorization key.
     */
    boolean hasAllAuthorizations(Collection<Map.Entry<String, AuthorizationKey>> requiredAuthorizations);
    boolean saveIfHasAllAuthorizations(Collection<Map.Entry<AuthorizationKey, String>> authorizationsToSave,
                                       Collection<Map.Entry<String, AuthorizationKey>> requiredAuthorizations);

    /**
     * Equivalent result to calling hasAllAuthorizations() for each of internal collections in requiredAuthorizations
     * parameter and return true when one of them returns true.
     */
    boolean hasAnyAuthorization(Collection<Collection<Map.Entry<String, AuthorizationKey>>> requiredAuthorizations);
}
