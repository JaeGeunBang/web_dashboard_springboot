package jgb.dashboard.chart;

import lombok.*;
import java.util.Random;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class ChartList{
    private String[] dateList ;
    private Chart[] chartData ;

    public String makeRandomColor() {
        String randomColor = "#";
        Random generator = new Random();   
        for(int i = 0 ; i < 6 ; i++){
            int num1= generator.nextInt(15) + 1;
            if(num1 == 10) randomColor += "A";
            else if(num1 == 11) randomColor += "B";
            else if(num1 == 12) randomColor += "C";
            else if(num1 == 13) randomColor += "D";
            else if(num1 == 14) randomColor += "E";
            else if(num1 == 15) randomColor += "F";
            else randomColor += num1;
        }  
        return randomColor;
    }

    public String makeRandomRgba(double alpha)
    {
        Random generator = new Random() ;
        int[] r_color = new int[3] ;
        for(int i = 0 ; i < r_color.length ; i++){
            int num = generator.nextInt(255) + 1;
            r_color[i] = num ;
        }

        return String.format("rgba(%d, %d, %d, %f)", r_color[0], r_color[1], r_color[2], alpha) ;
    }
}