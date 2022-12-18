package com.programmer.JwtBackend.user.dto.validator;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements Predicate<String> {

    final static String regex = "^(.+)@(.+)$";

    @Override
    public boolean test(String email) {
        final Pattern pattern = Pattern.compile(regex);

        final Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
