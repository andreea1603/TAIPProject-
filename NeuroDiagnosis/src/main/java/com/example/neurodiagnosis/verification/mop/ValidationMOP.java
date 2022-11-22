package com.example.neurodiagnosis.verification.mop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ValidationMOP {
    public static void main(String[] args) throws IOException {
        Map<String, String> validatePairs =
                new HashMap<>(){
                    {
                        put("hasNext()", "next()");
                        put("isPresent()", "get()");
                        put("authorize(*)", "access(*)");
                    }};
        Path fileName
                = Path.of("src/main/java/com/example/neurodiagnosis/verification/mop/Test2.java");

//        Path fileName
//                = Path.of("src/main/java/com/example/neurodiagnosis/verification/mop/Test.java");

//        Path fileName = Path.of("src/main/java/com/example/neurodiagnosis/verification/mop/monitor3/TestIteratorRule.java");

        String fileContent = Files.readString(fileName);
        String[] arrOfStr = fileContent.split("[; .!]+");
        for ( int i=0; i< arrOfStr.length; i++ ) {
            if (validatePairs.containsValue(arrOfStr[i]) ) {
                boolean ok = false;
                for ( int j =0; j<i; j++ ) {
                    if (validatePairs.containsKey(arrOfStr[j]) ) {
                        ok = true;
                        break;
                    }
                }
                if (!ok) {
                    System.out.println("Programul nu valideaza");
                    return;
                }

            }
        }
        System.out.println("Programul valideaza");
    }

}
