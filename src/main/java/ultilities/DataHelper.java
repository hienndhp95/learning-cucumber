package ultilities;

import com.github.javafaker.Faker;

public class DataHelper {
	private Faker faker;

	public static DataHelper getData() {
		return new DataHelper();
	}

	public DataHelper() {
		faker = new Faker();
	}

	public String getFirstName() {
		return faker.name().firstName();
	}

	public String getLastName() {
		return faker.name().lastName();
	}

	public String getEditFirstName() {
		return faker.name().firstName();
	}

	public String getEditLastName() {
		return faker.name().lastName();
	}

	public String getAddress() {
		return faker.address().streetAddress();
	}

	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}

	public String getPhoneNumber() {
		return faker.phoneNumber().cellPhone();
	}

	public String getParagraph() {
		return faker.lorem().paragraph();
	}

	public String getEditParagraph() {
		return faker.lorem().paragraph();
	}
}
