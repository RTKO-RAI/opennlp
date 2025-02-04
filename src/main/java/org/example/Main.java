package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        NlpService nlpService = new NlpService();
        System.out.println(nlpService.getIntent("How many days can I take off?"));
    }
}