package org.example;

import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;

import java.io.InputStream;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NlpService {
    private final Tokenizer tokenizer;
    private final DocumentCategorizerME categorize;

    public NlpService() throws Exception {
        this.tokenizer = SimpleTokenizer.INSTANCE;

        try (InputStream modelIn = getClass().getResourceAsStream("/models/en-leave-intent.bin")) {
            DoccatModel model = new DoccatModel(Objects.requireNonNull(modelIn));
            this.categorize = new DocumentCategorizerME(model);
        }
    }

    public String getIntent(String text) {
        String[] tokens = tokenizer.tokenize(text);


        double[] outcomes = categorize.categorize(tokens);
        // Print confidence scores for debugging
        for (int i = 0; i < categorize.getNumberOfCategories(); i++) {
            System.out.println(categorize.getCategory(i) + ": " + outcomes[i]);
        }

        return categorize.getBestCategory(outcomes);
    }

    public LeaveDate getLeaveDates(String input) {
        String regex = "\\b(\\d{2}/\\d{2}/\\d{4})\\b";
        LeaveDate dates = new LeaveDate(null, null);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Extract and print dates
        System.out.println("Input: " + input);
        int count = 0;
        while (matcher.find()) {
            count++;
            if (count == 1) {
                System.out.println("From Date: " + matcher.group(1));
                dates.setFromDate(matcher.group(1));
            } else if (count == 2) {
                System.out.println("To Date: " + matcher.group(1));
                dates.setToDate(matcher.group(1));
            }
        }

        if (count < 2) {
            System.out.println("Could not extract both dates.");
        }
        return dates;
    }

    public RegisterHoursData getRegisterHoursData(String input) {
        RegisterHoursData registerHoursData = new RegisterHoursData(null, null);
        String hoursRegex = "(\\d+)\\s*hours";
        String dateRegex = "(\\d{2}/\\d{2}/\\d{4})";

        Pattern hoursPattern = Pattern.compile(hoursRegex);
        Matcher hoursMatcher = hoursPattern.matcher(input);

        String hours = null;
        if (hoursMatcher.find()) {
            hours = hoursMatcher.group(1);
        }

        Pattern datePattern = Pattern.compile(dateRegex);
        Matcher dateMatcher = datePattern.matcher(input);

        String date = null;
        if (dateMatcher.find()) {
            date = dateMatcher.group(1);
        }

        System.out.println("Input: " + input);
        System.out.println("Hours: " + (hours != null ? hours : "Not found"));
        System.out.println("Date: " + (date != null ? date : "Not found"));
        registerHoursData.setHours((hours != null ? hours : "Not found"));
        registerHoursData.setDate((date != null ? date : "Not found"));
        return registerHoursData;
    }
}
