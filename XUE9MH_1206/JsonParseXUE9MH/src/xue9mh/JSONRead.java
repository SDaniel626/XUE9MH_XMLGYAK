package xue9mh;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONRead {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("orarendXUE9MH.json");

            //Parse
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);

            //Root, majd óra lista lekérése
            JSONObject root = (JSONObject) jsonObject.get("SD_orarend");
            JSONArray lessons = (JSONArray) root.get("ora");

            //Óra adatok kiírása
            System.out.println("Órarend:\n");
            for(int i=0; i<lessons.size(); i++) {
                JSONObject lesson = (JSONObject) lessons.get(i);
                JSONObject time = (JSONObject) lesson.get("idopont");
                System.out.println("Tárgy: "+lesson.get("targy"));
                System.out.println("Időpont: "+time.get("nap")+" "+time.get("tol")+"-"+time.get("ig"));
                System.out.println("Helyszín: "+lesson.get("helyszin"));
                System.out.println("Oktató: "+lesson.get("oktato"));
                System.out.println("Szak: "+lesson.get("szak")+"\n");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


}
