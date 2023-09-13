package com.backend.employee.validations;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.repo.RegisterRepo;

/**
 * Class to check if all the input fields fulfill the input requirement or not.
 */
@Component
public class InputFieldsChecksUpdated {

	/**
	 * minimum length of password.
	 */
	private static final int MIN_PASSWORD_LENGTH = 8;

	/**
	 * hashed password length.
	 */
	private static final int HASHCODE_PWD_LENGTH = 20;

	/**
	 * object of employee repository.
	 */
	@Autowired
	private RegisterRepo registerRepo;

	/**
	 * object of password encoder class to match password.
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * check for employee id entered by user.
	 *
	 * @param empId getting employee id to check.
	 * @throws WrongInputException throw an exception.
	 */
	public final void checkEmpId(final String empId)
			throws WrongInputException {
		String empIdPattern = "^N\\d{4}$";
		if (empId.equals("")) {
			throw new WrongInputException("Employee Id should not be empty");
		}
		if (!Pattern.matches(empIdPattern, empId)) {
			String s = "not a valid employee id. "
					+ "Employee Id should be in the form of N0000";
			throw new WrongInputException(s);
		}
	}

	/**
	 * check for date format.
	 *
	 * @param date get date.
	 * @throws WrongInputException throw exception if not a valid DOB.
	 */
	public final void checkDob(final String date) throws WrongInputException {
		String datePattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/"
				+ "(19|20)\\d\\d$";
		if (date.equals("")) {
			throw new WrongInputException("Employee DOB should not be empty");
		}
		if (!Pattern.matches(datePattern, date)) {
			String message = "not a valid DOB. "
					+ "DOB should be in the form of \"DD/MM/YYYY\"";
			throw new WrongInputException(message);
		}
	}

	/**
	 * check for valid DOJ.
	 * 
	 * @param date accept a date.
	 * @throws WrongInputException throw exception if date is not in valid
	 *                             format.
	 */
	public final void checkDoj(final String date) throws WrongInputException {
		String datePattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/"
				+ "(19|20)\\d\\d$";
		if (date.equals("")) {
			throw new WrongInputException("Employee DOJ should not be empty");
		}
		if (!Pattern.matches(datePattern, date)) {
			String message = "not a valid DOJ. "
					+ "DOJ should be in the form of \"DD/MM/YYYY\"";
			throw new WrongInputException(message);
		}
	}

	/**
	 * check if DOJ is 18 year older than DOB.
	 *
	 * @param dob accept DOB.
	 * @param doj accept DOJ.
	 * @throws WrongInputException throw an exception.
	 */
	public final void checkDatesDifference(final String dob, final String doj)
			throws WrongInputException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/YYYY");
		LocalDate date1 = LocalDate.parse(dob, formatter);
		LocalDate date2 = LocalDate.parse(doj, formatter);

		Period period = Period.between(date1, date2);

		int yearsDiff = period.getYears();
		int monthsDiff = period.getMonths();
		int daysDiff = period.getDays();

		if (yearsDiff < 18 || yearsDiff == 18
				&& (monthsDiff < 0 || monthsDiff == 0 && daysDiff < 0)) {
			String s = "DOB should be atleast 18 year older than DOJ.";
			throw new WrongInputException(s);
		}
	}

	/**
	 * check for employee email format.
	 *
	 * @param empEmail getting email.
	 * @throws WrongInputException throw exception for wrong email.
	 */
	public final void checkEmpEmail(final String empEmail)
			throws WrongInputException {
		String empEmailPattern = "^[a-zA-Z0-9._%+-]+@nucleusteq\\.com$";
		if (empEmail.equals("")) {
			throw new WrongInputException("Employee Email should not be empty");
		}
		if (!Pattern.matches(empEmailPattern, empEmail)) {
			String s = "Encorrect email. Admin must have email as "
					+ "ankita.sharma@nucleusteq.com";
			throw new WrongInputException(s);
		}
	}

	public final void checkValidAdminEmail(final String empEmail)
			throws DataAlreadyExistsException {
		String validEmail = "ankita.sharma@nucleusteq.com";
		if (!empEmail.equals(validEmail)) {
			String message = "admin should have email id as " + validEmail;
			throw new DataAlreadyExistsException(message);
		}
	}

	/**
	 * check for employee contact number.
	 *
	 * @param empContactNo getting employee contact.
	 * @throws WrongInputException throw exception if contact pattern not match.
	 */
	public final void checkEmpContactNo(final String empContactNo)
			throws WrongInputException {
		String empContactNoPattern = "^\\d{10}$";
		if (empContactNo.equals("")) {
			throw new WrongInputException(
					"Employee Contact number should not be empty");
		}
		if (!Pattern.matches(empContactNoPattern, empContactNo)) {
			String message = "not a valid Valid Contact Number. "
					+ "Contact Number should have only 10 digits.";
			throw new WrongInputException(message);
		}
	}

	/**
	 * check for password length.
	 *
	 * @param empPassword taking password to check.
	 * @throws WrongInputException throw exception if not a valid password.
	 */
	public final void checkEmpPassword(final String empPassword)
			throws WrongInputException {
		// String empIdPattern = "^N\\d{4}$";
		if (empPassword.equals("")) {
			throw new WrongInputException("Password should not be empty");
		}
		if (empPassword.length() <= MIN_PASSWORD_LENGTH) {
			String message = "Password should contain atleast "
					+ "8 digit numbers or letters or both.";
			throw new WrongInputException(message);
		}
	}

	/**
	 * check for valid name.
	 *
	 * @param name getting name from user.
	 * @throws WrongInputException throw exception for wrong name.
	 */
	public final void checkEmpName(final String name)
			throws WrongInputException {
		String namePattern = "^[A-Za-z\\s]+$";
		if (name.equals("")) {
			throw new WrongInputException("Employee name should not be empty");
		}
		if (!Pattern.matches(namePattern, name)) {
			String s = "invalid employee name. "
					+ "Employee name should not be empty "
					+ "and should contains only characters not number.";
			throw new WrongInputException(s);
		}
	}

	/**
	 * checks if email id is already registered or not.
	 * 
	 * @param email takes an email id.
	 * @throws DataAlreadyExistException throw an exception if email already
	 *                                   registered.
	 */
	public final void checkEmailExistence(final String email)
			throws DataAlreadyExistsException {
		Optional<RegisterEntity> registerEntity = registerRepo
				.findByEmpEmail(email);
		if (registerEntity.isPresent()) {
			String message = "employee with email id " + email
					+ " already exist.";
			throw new DataAlreadyExistsException(message);
		}
	}

	/**
	 * checks if emp id is already registered or not.
	 * 
	 * @param empId accepts employee Id.
	 * @throws DataAlreadyExistException throw exception if id already exist.
	 */
	public final void checkEmpIdExistence(final String empId)
			throws DataAlreadyExistsException {
		Optional<RegisterEntity> registerEntity = registerRepo
				.findByEmpId(empId);
		if (registerEntity.isPresent()) {
			String message = "employee with employee Id " + empId
					+ " already exist.";
			throw new DataAlreadyExistsException(message);
		}
	}

	/**
	 * checks if contact number is already registered or not.
	 * 
	 * @param empContact accept a contact number.
	 * @throws DataAlreadyExistException throw exception if id already exist.
	 */
	public final void checkEmpContactExistence(final String empContact)
			throws DataAlreadyExistsException {
		Optional<RegisterEntity> registerEntity = registerRepo
				.findByEmpContactNo(empContact);
		if (registerEntity.isPresent()) {
			String message = "employee with contact number " + empContact
					+ " already exist.";
			throw new DataAlreadyExistsException(message);
		}
	}

	/**
	 * used to check password is hashed or not.
	 *
	 * @param password accept password.
	 * @return return boolean value after check.
	 */
	

	/**
	 * Login validation.
	 *
	 * @param empEmail email for login.
	 * @param password password for login.
	 * @throws WrongInputException throw exception if not match.
	 */
}
