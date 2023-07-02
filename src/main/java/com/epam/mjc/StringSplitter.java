package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of resultult substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        int i=0;
        List<String> strs;
        List<String> temp = new ArrayList<>();
        List<String> result = new ArrayList<>();
        for (String d:
                delimiters) {
            if (i==0){
                strs = List.of(source.split(d));
                i++;
                result.addAll(strs);
            }
            else{
                temp.clear();
                for (String s:
                        result) {
                    for (String m:
                            s.split(d)) {
                        if (!m.equals("")){
                            temp.add(m);
                        }
                    }
                }
                result.clear();
                result.addAll(temp);
            }
        }
        return result;

    }
}
