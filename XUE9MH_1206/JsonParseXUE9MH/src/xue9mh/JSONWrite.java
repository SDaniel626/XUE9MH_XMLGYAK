package xue9mh;

import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONWrite {
    public static void main(String[] args)
    {
        JSONArray lessons = new JSONArray();
        lessons.add(createLesson("Korszeru Informacios Technologiak", "Kedd", "8", "10", "Inf. 103", "Árvai László Lajos"));
        lessons.add(createLesson("Adatkezeles XML-ben", "Kedd", "12", "14", "XXXII eloado", "Dr. Bendarik László"));
        lessons.add(createLesson("Webes alkalmazasok java", "Kedd", "14", "16", "online", "Viktor Selmeci"));
        lessons.add(createLesson("Webes alkalmazasok java", "Kedd", "16", "18", "online", "Viktor Selmeci"));
        lessons.add(createLesson("Szamitogepes Halozatok", "Szerda", "8", "10", "II eloado", "Dr. Kovacs Szilveszter"));
        lessons.add(createLesson("Adatkezeles XML-ben", "Szerda", "10", "12", "Inf. 101", "Dr. Bednarik László"));
        lessons.add(createLesson("Szamitogepes Halozatok", "Csutortok", "12", "14", "III eloado", "Dr. Kovacs Szilveszter"));
        lessons.add(createLesson("Korszeru Informacios Technologiak", "Csutortok", "16", "18", "Inf. 114", "Soós Róbert"));
        JSONObject root = new JSONObject();
        root.put("ora", lessons);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("SD_orarend", root);

        fileWrite(jsonObject, "orarendXUE9MH1.json");
        consoleWrite(jsonObject);
    }

    private static void fileWrite(JSONObject jsonObject, String fileName)
    {
        try(FileWriter writer = new FileWriter(fileName))
        {
            writer.write(indentJson(jsonObject.toJSONString()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void consoleWrite(JSONObject jsonObject)
    {
        System.out.println("A felépített JSON dokumentum tartalma:\n");
        JSONObject root = (JSONObject) jsonObject.get("SD_orarend");
        JSONArray lessons = (JSONArray) root.get("ora");
        for(int i=0; i<lessons.size(); i++)
        {
            JSONObject lesson = (JSONObject) lessons.get(i);
            JSONObject time = (JSONObject) lesson.get("idopont");
            System.out.println("Tárgy: "+lesson.get("targy"));
            System.out.println("Időpont: "+time.get("nap")+" "+time.get("tol")+"-"+time.get("ig"));
            System.out.println("Helyszín: "+lesson.get("helyszin"));
            System.out.println("Oktató: "+lesson.get("oktato"));
            System.out.println("Szak: "+lesson.get("szak")+"\n");
        }
    }

    //Indentálás fájlba íráshoz
    private static String indentJson(String json)
    {
        String out = "";
        int indent = 0;
        for (int i = 0; i < json.length()-1; i++)
        {
            out += json.charAt(i);
            if (json.charAt(i) == ',')
                out += "\n" + "  ".repeat(indent>0 ? indent : 0);
            else if (json.charAt(i) == '{' | json.charAt(i) == '[')
            {
                indent++;
                out += "\n" + "  ".repeat(indent>0 ? indent : 0);
            }
            else if ((json.charAt(i+1) == '}' || json.charAt(i+1) == ']'))
            {
                indent--;
                out += "\n" + "  ".repeat(indent>0 ? indent : 0);
            }
        }
        out+=json.charAt(json.length()-1);
        return out;
    }

    //Óra objektum készítése
    private static JSONObject createLesson(String subject, String day, String from, String to, String location, String teacher)
    {
        String major ="Mernök info";
        JSONObject lesson = new JSONObject();
        JSONObject time = new JSONObject();
        time.put("nap", day);
        time.put("tol", from);
        time.put("ig", to);
        lesson.put("targy", subject);
        lesson.put("idopont", time);
        lesson.put("helyszin", location);
        lesson.put("oktato", teacher);
        lesson.put("szak", "Mernök info");
        System.out.println(time.toJSONString());
        return lesson;
    }
}
