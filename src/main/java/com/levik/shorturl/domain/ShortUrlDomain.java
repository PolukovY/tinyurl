package com.levik.shorturl.domain;

import org.springframework.stereotype.Component;

@Component
public class ShortUrlDomain {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final char[] CHARACTERS = ALPHABET.toCharArray();
    private static final int BASE_62 = ALPHABET.length();

    public String identityToShortUrl(long identity) {
        StringBuilder hash = new StringBuilder();

        if (identity == 0) {
            hash.append(CHARACTERS[0]);
        }

        while (identity > 0) {
            int mode = (int) (identity % BASE_62);
            identity /= BASE_62;

            hash.append(CHARACTERS[mode]);
        }

        return hash.reverse().toString();
    }

    public long shortURLtoIdentity(String shortURL) {
        long identity = 0;
        char[] chars = shortURL.toCharArray();

        for (int index = 0; index < shortURL.length(); index++ ) {
            identity = identity * BASE_62 + ALPHABET.indexOf(chars[index]);
        }

        return identity;
    }
}
