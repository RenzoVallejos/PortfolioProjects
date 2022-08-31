import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.Scanner;

import com.google.gson.*;
import com.google.gson.reflect.*;

public class WeatherAPI{
    
    // Creating a method in order to convert a json file to a HashMap
    public static Map<String, Object> jsonToMap(String str){
        Map<String, Object> map = new Gson().fromJson(
            str, new TypeToken<HashMap<String, Object>>() {}.getType()
        );
        return map;
        }
        
    public static String city(){
        // Creating a method in order to create a selection menu from different cities around the world
        String city;
        String country;
        Scanner sc = new Scanner(System.in); //Creating a scanner
        
        System.out.println("Select a city");
        System.out.println("1. Bellevue");
        System.out.println("2. Paris");
        System.out.println("3. Cancun");
        System.out.println("4. Tokyo");
        System.out.println("5. Seoul");
        System.out.println("6. Rome");
        System.out.println("7. Lima");
        System.out.println("8. Beijing");
        System.out.println("9. Hanoi");
        System.out.println("10. London");
        int n = sc.nextInt();
        
        // Creating a switch statement
        switch(n){
            case 0:
                System.out.println("Exiting the program. Goodbye");
                System.exit(0);
            case 1:
                city = "Bellevue";
                country = "US";
                break;
            case 2:
                city = "Paris";
                country = "France";    
                break;
            case 3:
                city = "Cancun";
                country = "Mexico";
                break;
            case 4:
                city = "Tokyo";
                country = "Japan";
                break;
            case 5:
                city = "Seoul";
                country = "Korea";
                break;
            case 6:
                city = "Rome";
                country = "Italy";
                break;
            case 7:
                city= "Lima";
                country = "Peru";
                break;
            case 8:
                city = "Beijing";
                country = "China";
                break;
            case 9:
                city = "Hanoi";
                country = "Vietnam";
                break;
            case 10:
                city = "London";
                country = "England";
                break;
            default:
                city = "not an option";
                country = "not available";
                System.exit(0);
        }
        System.out.println("city: " + city);
        System.out.println("country: " + country);
        return  city;          
    }

    public static String category(){
        //Creating a method in order to create a selection menu from different categories such as Weather, Temperature, etc.
        String category;
        Scanner scan = new Scanner(System.in); // Creating a scanner
        
        System.out.println("Select one");
        System.out.println("1. Weather");
        System.out.println("2. Pressure");
        System.out.println("3. Temperature");
        System.out.println("4. Humidity");
        System.out.println("5. Wind Speed");
        System.out.println("6. Wind Degree");
        System.out.println("7. Wind Gust");
        
        int m = scan.nextInt();
        // Creating a switch statement
        switch(m){
            case 0:
                System.out.println("Exiting the program. Goodbye");
                System.exit(0);
            case 1:
                category = "Weather";
                break;
            case 2:
                category = "Pressure";
                break;
            case 3:
                category = "Temperature";
                break;
            case 4:
                category = "Humidity";
                break;
            case 5:
                category = "Wind Speed";
                break;
            case 6:
                category = "Wind Degree";
                break;
            case 7:
                category= "Wind Gust";
                break;
            default:
                category = "not an option";
                System.exit(0);
         }
        return  category;       
    }
} 
        
