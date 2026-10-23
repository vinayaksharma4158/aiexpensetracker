/**
 * 
 */
package com.damon.ai.expense.trackerr.aiexpensetracker.Validator;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

/**
 * 
 */
@Component
public class OtpGeneratorVal {

	/**
	 * 
	 */
	public OtpGeneratorVal() {
		// TODO Auto-generated constructor stub
	}
	
	private static final String DIGITS = "0123456789";
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateOTP(int length, boolean numericOnly) {
        SecureRandom random = new SecureRandom();
        String characters = numericOnly ? DIGITS : ALPHANUMERIC;

        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            otp.append(characters.charAt(random.nextInt(characters.length())));
        }
        return otp.toString();
    }
    

}
