package ru.rassafel.solution.model;
import java.util.List;
import java.util.ListIterator;
import lombok.Data;
import ru.rassafel.reflection.annotation.ParseIndex;

@Data
public class CarMaker {
    @ParseIndex(headerName = "array")
    private List<Car> carList;
    
    @ParseIndex(headerIndex = 0)
    private String carMakerName;
    
    public void setCarList(List<Car> other){
        this.carList = other;
    }
    
    @Override
    public String toString(){
        String result = this.carMakerName + "=[";
        ListIterator<Car> iter = carList.listIterator();
        while(iter.hasNext()){
            result += iter.next().getString();
            if(iter.hasNext()) result += ", ";
        }
        result += "]";
        return result;
    }
}
