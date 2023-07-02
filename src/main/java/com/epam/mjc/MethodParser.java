package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<String> str;
        MethodSignature methodSignature;
        if (signatureString.lastIndexOf("(")+1==signatureString.lastIndexOf(")")){
            signatureString = signatureString.replace("("," ");
            signatureString = signatureString.replace(")"," ");
            str = List.of(signatureString.split(" "));
            methodSignature = new MethodSignature(str.get(2));
            methodSignature.setReturnType(str.get(1));
            methodSignature.setAccessModifier(str.get(0));
        }
        else {
            List<MethodSignature.Argument> arguments = new ArrayList<>();
            StringSplitter splitter = new StringSplitter();
            signatureString = signatureString.replace("("," ");
            signatureString = signatureString.replace(")"," ");
            str = splitter.splitByDelimiters(signatureString, List.of(new String[]{",", " "}));
            if (str.get(0).equals("private")||str.get(0).equals("public")||str.get(0).equals("protected")){
                String name = str.get(2);
                String type =str.get(1);
                String modifier = str.get(0);
                for (int i=0;i<3;i++) str.remove(0);
                for (int i = 0;i<str.size();i=i+2){
                    arguments.add(new MethodSignature.Argument(str.get(i),str.get(i+1)));
                }
                methodSignature = new MethodSignature(name,arguments);
                methodSignature.setReturnType(type);
                methodSignature.setAccessModifier(modifier);
            }
            else {
                String name = str.get(1);
                String type =str.get(0);
                for (int i=0;i<2;i++) str.remove(0);
                for (int i = 0;i<str.size();i=i+2){
                    arguments.add(new MethodSignature.Argument(str.get(i),str.get(i+1)));
                }
                methodSignature = new MethodSignature(name,arguments);
                methodSignature.setReturnType(type);
            }
        }
        return methodSignature;
    }
}
