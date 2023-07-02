package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        int i=0;
        List<String> strs = new ArrayList<>();
        List<String> tempstrs = new ArrayList<>();
        List<String> res = new ArrayList<>();
        for (String d:
                delimiters) {
            if (i==0){
                strs = List.of(source.split(d));
                i++;
                res.addAll(strs);
            }
            else{
                tempstrs.clear();
                for (String s:
                        res) {
                    for (String m:
                            s.split(d)) {
                        if (!m.equals("")){
                            tempstrs.add(m);
                        }
                    }
                }
                res.clear();
                res.addAll(tempstrs);
            }
        }
        return res;

    }
}
