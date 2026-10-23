package com.damon.ai.expense.trackerr.aiexpensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.FinanceUser;
import com.damon.ai.expense.trackerr.aiexpensetracker.model.OtpGenerateValidate;
import com.damon.ai.expense.trackerr.aiexpensetracker.model.UserProfile;
import com.damon.ai.expense.trackerr.aiexpensetracker.repository.OtpGenerateValidateRepository;
import com.damon.ai.expense.trackerr.aiexpensetracker.repository.UserProfileRepository;
import com.damon.ai.expense.trackerr.aiexpensetracker.Validator.*;
import com.damon.ai.expense.trackerr.aiexpensetracker.exception.EmailAlreadyExistsException;

/**
 * OtpService
 */
@Service
public class OtpService {

	@Autowired
	private OtpGenerateValidateRepository repository;

	@Autowired
	private OtpGeneratorVal otpGeneratorVal;

	private static final Logger logger = LoggerFactory.getLogger(OtpService.class);

	@Value("${otp.length}")
	private int otpLength; // Injected from application.properties

	public String generateOTP(String Email) {
		// Implementation for generating OTP
		String otp = otpGeneratorVal.generateOTP(otpLength, true);

		// Log the OTP generation event
		logger.info("Generated OTP for user [{}]: {}", Email, otp);

		// If you want to log at DEBUG level for more detail:
		logger.debug("OTP details -> length: {}, numericOnly: {}", otpLength, true);

		storeOtpService(Email, otp);

		return "Otp Send Successfully";
	}

	public OtpGenerateValidate storeOtpService(String Email, String otp) {

		OtpGenerateValidate otpm = new OtpGenerateValidate();

		otpm.setEmail(Email);
		otpm.setOtp(otp);
		otpm.setTimestamp(java.time.LocalDateTime.now());

		return repository.save(otpm);
	}

	public boolean validateOtp(String email, String enteredOtp) {
		OtpGenerateValidate record = repository.findTopByEmailOrderByTimestampDesc(email);

		if (record == null) {
			return false;
		}

		// Expiry check: 5 minutes
		if (record.getTimestamp().plusMinutes(5).isBefore(java.time.LocalDateTime.now())) {
			record.setValid(false);
			repository.save(record);
			return false;
		}

		 // Check correctness
	    if (record.getOtp().equals(enteredOtp) && record.isValid()) {
	        // ✅ Mark OTP invalid immediately after successful use
	        record.setValid(false);
	        repository.save(record);
	        return true;
	    }

	    return false;
	}

}
