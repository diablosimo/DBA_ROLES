package util;

import java.util.List;

public class QueryUtil {

    public static String explodeStringList(List<String> list){
        String result="";
        for (int i = 0; i <list.size() ; i++) {
            String s=list.get(i);
            result+=s;
            if(i==(list.size()-1)){
                result+=" ";
            }else {
                result+=", ";
            }
        }
        return result;
    }
}
