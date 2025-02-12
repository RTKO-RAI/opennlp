package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        NlpService nlpService = new NlpService();
        System.out.println(nlpService.getIntent("Register 7 hours to the curie project for the date 21/07/2025"));
//        System.out.println(nlpService.getLeaveDates("Apply for a vacation between these two dates 21/07/2025 to 27/07/2025"));
        System.out.println(nlpService.getRegisterHoursData("Register 7 hours to the curie project for the date 21/07/2025").getDate() + " " + nlpService.getRegisterHoursData("Register 7 hours to the curie project for the date 21/07/2025").getHours());

    }
}