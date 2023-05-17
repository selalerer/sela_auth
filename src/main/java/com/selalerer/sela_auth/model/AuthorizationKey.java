package com.selalerer.sela_auth.model;

import lombok.*;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AuthorizationKey {
    private String application;
    private String accessedEntityType;
    private String accessedEntityId;
    private String accessingEntityType;
    private String accessingEntityId;
}
