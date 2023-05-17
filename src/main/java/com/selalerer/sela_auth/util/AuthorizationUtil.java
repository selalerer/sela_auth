package com.selalerer.sela_auth.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

public class AuthorizationUtil {
    private final static String ALL_AUTHS = "CRUD";

    private static Stream<String> streamAuths(String auths) {
        if (auths == null) {
            return Stream.of();
        }
        return auths.chars().mapToObj(i -> (char) i).map(c -> c.toString());
    }

    public static boolean isValid(String auths) {
        if (auths == null || auths.equals("")) {
            return false;
        }
        return hasAuth(auths, ALL_AUTHS);
    }

    public static void validate(String auths) {
        if (!isValid(auths)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid authorization string: " + auths);
        }
    }

    public static boolean hasAuth(String requiredAuths, String actualAuths) {
        return streamAuths(requiredAuths)
                .allMatch(c -> ofNullable(actualAuths).map(a -> a.contains(c)).orElse(false));
    }
}
