# SpockTableGenerator

## Generates Spock table from csv files  through the following rules:

1. First line is considered as headers and fields will not be quoted
3. All fields wich are not boolean or null will be quoted by default
4. "null", "true" and "false" will not be quoted by default 
5. But you can change it by setting quoteBoolean, quoteNull and quoteNumbers
6. All fields separated with "|" will be separeted with "|" by default but you can change it by setting delimiter
7. The width of fields in column will be adgusted for longest field
8. If there is only one field in csv, empty second field will be added as required for spock

## Example

    SpockTablesGenerator generator = SpockTablesGenerator.builder()
        .quoteBoolean(true)
        .quoteNull(true)
        .quoteNumbers(false)
        .delimiter('|').build();

    generator.generate(
        new File("/Users/maria.klimenko/IdeaProjects/SpockTablesGenerator/src/main/resources/spockTable.csv"),
        new File("/Users/maria.klimenko/IdeaProjects/SpockTablesGenerator/src/main/resources/output.txt"));
    
