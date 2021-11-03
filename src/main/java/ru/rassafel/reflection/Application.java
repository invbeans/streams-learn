/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rassafel.reflection;

import lombok.SneakyThrows;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import ru.rassafel.reflection.model.Person;
import ru.rassafel.reflection.processor.ParseIndexProcessor;

/**
 *
 * @author Buzuluchanka
 */
public class Application {
    @SneakyThrows
    public static void main(String[] args){
        ParseIndexProcessor processor = new ParseIndexProcessor();
        
        Path path = Paths.get(Application.class
                .getClassLoader()
                .getResource("PERSON.csv")
                .toURI());
        BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        
        String header = br.readLine();
        processor.registerClass(header, Person.class);
        
        String line;
        
        while((line = br.readLine()) != null){
            System.out.println(processor.parseObject(line, Person.class));
        }
        
        br.close();
    }
}
