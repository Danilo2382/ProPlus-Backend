package org.springbootapp.proplus_backendapplication.generators;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class ProjectCodeGenerator {

    private static final int MIN_CODE_LENGTH = 6;
    private static final int MAX_CODE_LENGTH = 30;

    private static String generateCode() {
        return RandomStringUtils.random(
                new Random().nextInt((MAX_CODE_LENGTH - MIN_CODE_LENGTH) + MIN_CODE_LENGTH), true, true);
    }

    private static boolean checkCode(String code, List<String> codes) {
        if (codes.isEmpty()) return false;
        return codes.contains(code);
    }

    public static Integer getStatusFromDate(LocalDateTime startDate, LocalDateTime endDate) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (currentDateTime.isBefore(startDate)) return 3;
        if (currentDateTime.isAfter(startDate) && currentDateTime.isBefore(endDate)) return 2;
        return 0;
    }

    public static String generateUniqueCode(List<String> existingCodes) {
        String code;
        do { code = generateCode(); } while (checkCode(code, existingCodes));
        return code;
    }
}
