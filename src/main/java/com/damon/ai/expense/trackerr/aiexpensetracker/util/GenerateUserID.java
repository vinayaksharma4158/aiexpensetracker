package com.damon.ai.expense.trackerr.aiexpensetracker.util;

import java.util.Random;

public class GenerateUserID {
    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final int RANDOM_LENGTH = 6;
    private final Random random = new Random();

    public String generateUserId() {
        long timestamp = System.currentTimeMillis(); // milliseconds since epoch
        StringBuilder randomPart = new StringBuilder();

        for (int i = 0; i < RANDOM_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            randomPart.append(CHARACTERS.charAt(index));
        }

        return randomPart.toString() + "_" + timestamp;
    }

}
