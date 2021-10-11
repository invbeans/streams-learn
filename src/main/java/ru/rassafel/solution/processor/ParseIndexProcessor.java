package ru.rassafel.solution.processor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import lombok.SneakyThrows;
import ru.rassafel.solution.annotation.ParseIndex;

public class ParseIndexProcessor {
    public final String delimeter = " ";
    private Map<Class, Map<Integer, Method>> map = new HashMap<>();
    
    @SneakyThrows
    public boolean registerClass(String header, Class<?> clazz){
        String[] headers = header.split(delimeter);
        
        for(Field field : clazz.getDeclaredFields()){
            ParseIndex annotation = field.getAnnotation(ParseIndex.class);
            if(annotation != null){
                String setterName = "set" + field.getName().substring(0, 1).toUpperCase()
                    + field.getName().substring(1);
                
                Method setter = clazz.getDeclaredMethod(setterName, field.getType());
                int index = annotation.headerIndex();
                if(index < 0){
                    String name = annotation.headerName();
                    index = Arrays.asList(headers).indexOf(name);
                }
                Map<Integer, Method> setters = map.getOrDefault(clazz, new HashMap<>());
                if(setters.containsKey(index)){
                    return false;
                }
                setters.put(index, setter);
                map.put(clazz, setters);
            }
        }
        return true;
    }
    
    
    @SneakyThrows
    public <T> T parseObject(String line, Class<T> clazz, int maxValues){
        Constructor<T> constructor = clazz.getConstructor();
        
        T instance = constructor.newInstance();
        
        String[] tempValues = line.split(delimeter);
        String[] values = new String[maxValues];
        if(tempValues.length == maxValues + 1){
            values[0] = tempValues[0] + " " + tempValues[1];
            for(int i = 1; i < maxValues; i++) values[i] = tempValues[i + 1];
        }
        else{
            values = tempValues;
        }
        
        Map<Integer, Method> integerMethodMap = map.get(clazz);
        for(Map.Entry<Integer, Method> entry : integerMethodMap.entrySet()){
            int key = entry.getKey();
            entry.getValue().invoke(instance, values[key]); 
        }
        return instance;
    }
    
   
}

