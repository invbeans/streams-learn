/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rassafel.solution;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;
import ru.rassafel.solution.processor.ParseIndexProcessor;

import ru.rassafel.solution.model.Car;
import ru.rassafel.solution.model.CarMaker;

public class Application {
    
    public static void main(String args[]) throws URISyntaxException, IOException{
        IOProcessor IOhandler = new IOProcessor();
        ParseIndexProcessor processor = new ParseIndexProcessor();
        Map<String, Integer> map = IOhandler.readFile();
        List<Car> carList = new ArrayList<>();
        
        List<String> strArray = IOProcessor.makeStringArray(map);
        ListIterator<String> iter = strArray.listIterator();
        
        String header = iter.next();
        processor.registerClass(header, Car.class);
        while(iter.hasNext()){
            header = iter.next();
            carList.add(processor.parseObject(header, Car.class, 4));
        }
        ListIterator<Car> iterCarList = carList.listIterator();
        Iterable<Car> iterableCarList = () -> iterCarList;
        IOProcessor.writeFile(iterableCarList, "task5.txt");
        
        //----Task 6------------------------
        Map<String, List<String>> groupByColor = carList.stream()
            .collect(Collectors.groupingBy(
                    Car::getColor,
                    Collectors.mapping(Car::getString, Collectors.toList())));
        IOProcessor.writeFile(groupByColor.entrySet(), "task6.txt");
        
        
        //----Task 7-------------------------
        Map<String, List<String>> groupByCarMaker = carList.stream()
                .collect(Collectors.groupingBy(
                        Car::getCarMake,
                        Collectors.mapping(Car::getString, Collectors.toList())));
        
        List<CarMaker> carMakerList = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry: groupByCarMaker.entrySet()){
            List<Car> carObjects = new ArrayList<>();
            List<String> carInfoList = entry.getValue();
            String carMaker = entry.getKey();
            
            ListIterator<String> lineIter = carInfoList.listIterator();
            while(lineIter.hasNext()){
                carObjects.add(processor.parseObject(lineIter.next(), Car.class, 4));
            }
            
            CarMaker carMakerObject = new CarMaker();
            carMakerObject.setCarMakerName(carMaker);
            carMakerObject.setCarList(carObjects);
            carMakerList.add(carMakerObject);
        }
        ListIterator<CarMaker> iterator = carMakerList.listIterator();
        Iterable<CarMaker> iterable = () -> iterator;
        IOhandler.writeFile(iterable, "task7.txt");
        
        String carMakerToConsole = groupByCarMaker.entrySet()
                     .stream()
                     .map(e -> e.getKey())
                     .filter(e -> e.length() != 0)
                     .collect(Collectors.joining(", "));
        System.out.println(carMakerToConsole);
        
        //----Task 8-------------------------
        List<String> carMakerNames = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry: groupByCarMaker.entrySet()){
            List<String> values = entry.getValue();
            String key = entry.getKey();
            if(values.size() >= 2){
                if(key.length() == 0) key = "empty";
                carMakerNames.add(key);
            }
        }
        
        carMakerNames = carMakerNames.stream().sorted().collect(Collectors.toList());
        
        ListIterator<String> iterMakers = carMakerNames.listIterator();
        Iterable<String> iterableMakers = () -> iterMakers;
        IOhandler.writeFile(iterableMakers, "task8.txt");
    }
        
}
