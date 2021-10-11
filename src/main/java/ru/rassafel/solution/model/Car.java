package ru.rassafel.solution.model;

import lombok.Data;
import ru.rassafel.solution.annotation.ParseIndex;

@Data
public class Car {
    @ParseIndex(headerIndex = 0)
    private String carModel;
    
    @ParseIndex(headerIndex = 1)
    private String carMake;
    
    @ParseIndex(headerIndex = 2)
    private String carModelYear;
    
    @ParseIndex(headerIndex = 3)
    private String color;
    
    @Override
    public String toString(){
        return this.carModel + ", " + this.carMake + ", " + this.carModelYear + ", " + this.color;

    }
    
    public String getString(){
        return this.carModel + " " + this.carMake + " " + this.carModelYear + " " + this.color;

    }

    
}
