package com.levik.shorturl.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TemplateAggregator {

    private static final String ROOT_PATH = "/Users/levik/Downloads/shorturl/src/main/resources";
    private static final String FRAGMENT_PATH = "/fragment";
    private static final String OUT = "/html";
    private static final String STATIC = "/static/";

    @SneakyThrows
    public static void main(String[] args) {
        Set<FragmentDetails> fragmentDetails;

        try (Stream<Path> stream = Files.walk(Paths.get(ROOT_PATH + FRAGMENT_PATH), 1)) {
            fragmentDetails = stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(it -> {
                        try {
                            return new FragmentDetails(it.getFileName().toString(), Files.readString(it, StandardCharsets.UTF_8));
                        }catch (Exception exe) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
        }


        try (Stream<Path> stream = Files.walk(Paths.get(ROOT_PATH + OUT), 1)) {
            stream.filter(file -> !Files.isDirectory(file))
                    .forEach(it -> {
                        try {
                            String content = Files.readString(it, StandardCharsets.UTF_8);

                            for (FragmentDetails fragment : fragmentDetails) {
                                String name = "<" + fragment.getName() + "/>";
                                String fragmentContent = fragment.getContent();
                                content = content.replaceAll(name, fragmentContent);
                            }

                            Files.write(Paths.get(ROOT_PATH + STATIC + it.getFileName().toString()), content.getBytes());
                        } catch (Exception exe) {
                            System.err.println("Can't read file " + it.toString());
                        }
                    });
        }
    }

    @AllArgsConstructor
    @Getter
    static class FragmentDetails {
        private final String name;
        private final String content;

    }
}
