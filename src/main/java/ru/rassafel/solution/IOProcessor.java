package ru.rassafel.solution;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class IOProcessor {
    
    public Map<String, Integer> readFile() throws URISyntaxException, IOException{
        URL resource = IOProcessor.class.getClassLoader().getResource("CAR_DATA.csv");
        Path path = Paths.get(resource.toURI());
        
        Map<String, Integer> bag = Files.lines(path, StandardCharsets.UTF_8)
            .map(str -> str.replaceAll("[,]", " "))
            .map(String::trim)
            .flatMap(line -> Arrays.stream(line.split("\\r?\\n")))
            .collect(Collectors.groupingBy(str -> str))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey, ent -> ent.getValue().size())); 
        return bag;
    }
    
    public static List<String> makeStringArray(Map<String, Integer> map){
        List<String> strArr = map.keySet().stream()
            .collect(Collectors.groupingBy(str -> str))
            .entrySet()
            .stream()
            .flatMap(ent -> ent.getKey().lines())
            .toList();
        return strArr;
    }
    
    public static void writeFile(Iterable<?> iterable, String filename) throws IOException {
        Path path = Paths.get("")
            .toAbsolutePath()
            .resolve("results")
            .resolve(filename);
        Files.deleteIfExists(path);
        
        try(BufferedWriter bw = Files.newBufferedWriter(path)){
            for(Object elem : iterable){
                bw.write(elem.toString());
                bw.newLine();
            }
        }
    }
    
    
}
