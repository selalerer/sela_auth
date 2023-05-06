package com.selalerer.sela_auth.model;

import lombok.*;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorizationKey {
    private String application;
    private String accessedEntityType;
    // E.g. createdBy, tradingParty, counterParty, organization ID
    private String accessedEntityProperty;
    private String accessedEntityPropertyValue;
    // E.g. user ID, group ID, role ID, organization ID
    private String userAccessKey;
}
