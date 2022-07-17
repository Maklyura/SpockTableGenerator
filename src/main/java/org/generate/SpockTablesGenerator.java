package org.generate;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.*;
import java.util.*;

public class SpockTablesGenerator {

    public static void generate(File input, File output) {

        List<List<String>> records = new ArrayList<>();
        List<List<String>> transposedRecords;
        try (CSVReader csvReader = new CSVReader(new FileReader((input)));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
            transposedRecords = transpose(records);
            new StringBuilder();
            StringBuilder sb;
            for (int i = 0; i < transposedRecords.size(); i++) {
                transposedRecords.set(i, getFormattedColumn(transposedRecords.get(i)));
            }
            transposedRecords = transpose(transposedRecords);
            for (int i = 0; i < transposedRecords.size(); i++) {
                transposedRecords.set(i, formatLastStringInTable(transposedRecords.get(i)));
            }
            FileWriter writer = new FileWriter(output);
            for (List<String> record : transposedRecords) {
                sb = new StringBuilder();
                for (String str : record) {
                    sb.append(str);
                }
                writer.write(sb.toString());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer findLongest(List<String> records) {
        int longest = 0;
        for (String value : records) {
            if (longest < value.length()) {
                longest = value.length();
            }
        }
        return longest;
    }

    public static <T> List<List<T>> transpose(List<List<T>> table) {
        List<List<T>> transposed = new ArrayList<List<T>>();
        final int size = table.get(0).size();
        for (int i = 0; i < size; i++) {
            List<T> column = new ArrayList<T>();
            for (List<T> row : table) {
                column.add(row.get(i));
            }
            transposed.add(column);
        }
        return transposed;
    }

    public static List<String> getFormattedColumn(List<String> values) {
        List<String> columns = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            if (i != 0) {
                if (!NumberUtils.isCreatable(values.get(i)) &&
                        !isBoolean(values.get(i)) &&
                        !values.get(i).equals("null")) {
                    values.set(i, "\"" + values.get(i) + "\"");
                }
            }
            values.set(i, values.get(i) + " ");
        }
        Integer longest = findLongest(values);
        for (String value : values) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            if (value.length() < longest) {
                sb.append(" ".repeat(Math.max(0, longest - value.length())));
            }
            sb.append("| ");
            value = sb.toString();
            columns.add(value);
        }
        return columns;
    }

    public static List<String> formatLastStringInTable(List<String> values) {
        String last = values.get(values.size() - 1);
        if (values.size() > 1) {
            last = last.trim();
            last = last.substring(0, last.length() - 1);
            last = last.concat("\n");
        } else {
            last = last.concat("_\n");
        }
        values.set(values.size() - 1, last);
        return values;
    }

    public static boolean isBoolean(String str) {
        return (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false"));
    }

}