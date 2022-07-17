# SpockTableGenerator

Generates Spock table from csv files  through the following rules:

First line is considered as headers and fields will not be quoted
All fields wich are not numbers, boolean or null will be quoted
Numbers, "null", "true" and "false" will not be quoted
All fields separated with "," will be separeted with "|" 
The width of fields in column will be adgusted for longest field
If there is only one field in csv, empty second field will be added as required for spock
