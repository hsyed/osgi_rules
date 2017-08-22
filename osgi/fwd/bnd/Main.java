package fwd.bnd;

import aQute.bnd.osgi.Builder;
import aQute.bnd.osgi.Jar;
import aQute.lib.strings.Strings;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final String[] MANDATORY_HEADERS = {"Bundle-SymbolicName"};

    private static final Map<String, String> DEFAULT_HEADERS = new HashMap<>();
    static {}

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new RuntimeException("first arg should be the input file, second arg should be the output jar, the third arg should be the instructions");
        }
        Builder b = new Builder();
        try {
            b.setJar(new Jar(new File(args[0])));
        } catch (IOException e) {
            throw new RuntimeException("could not load target jar: " + args[0], e);
        }
        getValidatedInstructions(args[2]).forEach((k, v) -> b.setProperty(k,v));

        Jar outJar;
        try {
            outJar = b.build();
        } catch (Exception e) {
            throw new RuntimeException("could not build osgi jar", e);
        }

        try {
            final File outFile = new File(args[1]);
            if(!outFile.exists()) {
                outFile.getParentFile().mkdirs();
                outFile.createNewFile();
            }
            outJar.write(outFile);
        } catch (Exception e) {
            throw new RuntimeException("could not write out osgi jar", e);
        }
    }

    private static Map<String,String> getValidatedInstructions(String args) {
        final Map<String,String> properties = new HashMap<>();
        for (String s : args.split("\n")) {
            final String[] parts = s.split("=");
            if(parts.length < 2) {
                throw new RuntimeException("headers should be kv pairs seperated by =");
            } else {
                String value = parts[1];
                if(parts.length > 2) {
                    for (int i = 2; i < parts.length; i++) {
                        value += parts[i];
                    }
                }
                properties.put(parts[0], value);
            }
        }
        DEFAULT_HEADERS.forEach(properties::putIfAbsent);

        List<String> missing = new ArrayList<>();
        for (String h : MANDATORY_HEADERS) {
            if(!properties.containsKey(h)) {
                missing.add(h);
            }
        }
        if(!missing.isEmpty()) {
            throw new RuntimeException("missing bnd headers: [" + Strings.join(",",missing) + "]");
        }

        return properties;
    }
}



