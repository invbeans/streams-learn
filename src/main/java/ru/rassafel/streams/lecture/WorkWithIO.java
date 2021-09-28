package ru.rassafel.streams.lecture;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author rassafel
 */
public class WorkWithIO {
    public static void main(String[] args) throws URISyntaxException, IOException {
        WorkWithIO workWithIO = new WorkWithIO();
        Map<String, Integer> map = workWithIO.readFile();
        workWithIO.writeFile(map.entrySet(), "words.txt");
    }

    //  ToDo:  ooChaThee4
    public Map<String, Integer> readFile() throws URISyntaxException, IOException {
        URL resource = WorkWithIO.class.getClassLoader().getResource("file.txt");
        Path path = Paths.get(resource.toURI());

        Map<String, Integer> bag = Files.lines(path, StandardCharsets.UTF_8)
            .map(str -> str.replaceAll("[,\\.\\-]", " "))
            .map(str -> str.replaceAll("\\s+", " "))
            .map(String::trim)
            .map(String::toLowerCase)
            .flatMap(line -> Arrays.stream(line.split("\\s+")))
            .collect(Collectors.groupingBy(str -> str))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey, ent -> ent.getValue().size()));

        return bag;
    }

    public void writeFile(Iterable<?> iterable, String filename) throws IOException {
        Path path = Paths.get("")
            .toAbsolutePath()
            .resolve("results")
            .resolve(filename);
        Files.deleteIfExists(path);

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Object elem : iterable) {
                bw.write(elem.toString());
                bw.newLine();
            }
        }
    }
}
