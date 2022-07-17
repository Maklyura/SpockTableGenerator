# SpockTableGenerator

## Generates Spock table from csv files  through the following rules:

1. First line is considered as headers and fields will not be quoted
3. All fields wich are not numbers, boolean or null will be quoted
4. Numbers, "null", "true" and "false" will not be quoted
5. All fields separated with "," will be separeted with "|" 
6. The width of fields in column will be adgusted for longest field
7. If there is only one field in csv, empty second field will be added as required for spock

inputName1,inputName2,veryVeryLongInputName3,output1,output2,tuptou
somethingVeryVeryLooooong,everything,null,false,wholesome,10
22, ,-0.03927539873,"errr","20",""

TO 

inputName1                  | inputName2   | veryVeryLongInputName3 | output1 | output2     | tuptou 
"somethingVeryVeryLooooong" | "everything" | null                   | false   | "wholesome" | 10     
22                          | " "          | -0.03927539873         | "errr"  | 20          | ""     
