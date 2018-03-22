package com.nju.training_college.util;

public class CookieUtil {
    private static final String STUDENT_COOKIE_NAME = "STDID";

    private static final String INSTITUTION_COOKIE_NAME = "INSID";

    public static String getStudentCookieName() {
        return STUDENT_COOKIE_NAME;
    }

    public static String getInstitutionCookieName() {
        return INSTITUTION_COOKIE_NAME;
    }
}
