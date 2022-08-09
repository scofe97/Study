package 자바공부.모던자바.Chapter3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Chapter3_Map {
    Map<String, String> hMap = new HashMap<>();

    private void addMethod(){
        hMap.put("andy", "1234");
        hMap.put("andy2", "4567");

        hMap.put("kase","1234");

        hMap.putIfAbsent("kate", "1234");
        hMap.putIfAbsent("kate", "4567");

        hMap.put("henry","4567");

        System.out.println(hMap);
    }

    private void retrieveMethod(){
        System.out.println("kate 존재여부 " + hMap.containsKey("kate"));

        Set<String> keys = hMap.keySet();
        for (String key : keys) {
            System.out.println(key +" "+hMap.get(key));
        }

        Set<Map.Entry<String, String>> keys2 = hMap.entrySet();
        for(Map.Entry<String, String> entry : keys2){
            if(entry.getValue().equals("4567")){
                System.out.println("값 4567인 사람 " + entry.getKey());
            }
        }
    }

    private void removeMethod(){

    }


    public static void main(String[] args) {
        Chapter3_Map hmt = new Chapter3_Map();
        hmt.addMethod();
        hmt.retrieveMethod();
        hmt.removeMethod();
    }
}
