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
/**
 * In this program I am creating an app for selecting weather data in different cities around the world.
 * In order to create this app I am using HashMaps, Lists, switch statements, scanner, and catch statements.
 * @author (Renzo Vallejos)
 * @StudentID (950-62-6822)
 * @Date (11/16/2021
 */

public class WeatherTest{ 
    public static void main(String[]args){ //main test program
        WeatherAPI g = new WeatherAPI();  // creating an instance g for the WeatherAPI class
        String city = g.city(); // Calling the city method from the WeatherAPI class and storing it as a string
        System.out.println();
        System.out.println();
        String category = g.category(); // Calling the category method from the WeatherAPI class and storing it as a string
                
        String API_KEY = "6ba414f56f7c1725c57521630ce2c21d"; //API_KEY
    
        String urlstring = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=6ba414f56f7c1725c57521630ce2c21d" + "&units=imperial";
        
        try { // Using a try and catch statement
            // Using the StringBuilder and BufferedReader in order to read the json file
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlstring);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            
            // Creating Hashmaps for main and wind 
            Map<String, Object> respMap = g.jsonToMap(result.toString());
            Map<String, Object> mainMap = g.jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = g.jsonToMap(respMap.get("wind").toString());
            // Creating a List for weather
            List<Map<String, Object >> weather = (List<Map<String, Object>>) (respMap.get("weather"));
            Map<String, Object> weatherMap = weather.get(0);
            
            // Creating an else if loop and getting the strings into the Hashmaps and weather List
            if(category == "Temperature"){
            System.out.println("Current " + category + ": " + mainMap.get("temp"));
            } 
            else if(category == "Weather"){ 
            System.out.println("Weather as map: "+ weatherMap);
            }  
            else if(category == "Pressure"){ 
                   System.out.println("Current " + category + ": " + mainMap.get("pressure"));
            }
            else if(category == "Humidity"){
                    System.out.println("Current " + category + ": " + mainMap.get("humidity"));
            }
            else if(category == "Wind Speed"){
                    System.out.println("Current " + category + ": " + windMap.get("speed"));
            }
            else if(category == "Wind Degree"){
                    System.out.println("Current " + category + ": " + windMap.get("deg"));
            } 
            else if(category == "Wind Gust"){
                    System.out.println("Current " + category     + ": " + windMap.get("gust"));
            }          
        } catch(IOException e){ // If there is an exception, printing out an error message
            System.out.println(e.getMessage());
        }
    }
}
