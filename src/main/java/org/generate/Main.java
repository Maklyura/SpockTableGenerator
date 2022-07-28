package org.generate;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        SpockTablesGenerator generator = SpockTablesGenerator.builder()
                .quoteBoolean(true)
                .quoteNull(true)
                .quoteNumbers(false)
                .delimiter('|').build();

        generator.generate(
                new File("/Users/maria.klimenko/IdeaProjects/SpockTablesGenerator/src/main/resources/spockTable.csv"),
                new File("/Users/maria.klimenko/IdeaProjects/SpockTablesGenerator/src/main/resources/output.txt"));
    }
}