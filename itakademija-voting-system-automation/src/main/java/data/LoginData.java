package data;

import org.testng.annotations.DataProvider;

public class LoginData {

	// Args: URL, TITLE OF PAGE
	@DataProvider(name = "pages")
	public static Object[][] pages() {
		return new Object[][] { { "http://localhost:8080/login", "Rinkimai" }, };
	}

	// Args: LOGIN, PASSWIORD, ERROR
	@DataProvider(name = "AdminLoginWithValidation")
	public static Object[][] AdminLoginWithValidation() {
		return new Object[][] { { "admin", "pass", null },
				{ "", "pass", "Neteisingas prisijungimo vardas arba slaptažodis." },
				{ "admin", "passx", "Neteisingas prisijungimo vardas arba slaptažodis." }
				// {"ZV", "123"}
		};
	}

	// Args: LOGIN, PASSWIORD, ERROR
	@DataProvider(name = "RepresentativeLoginWithValidation")
	public static Object[][] RepresentativeLoginWithValidation() {
		return new Object[][] { { "ZV", "123", null },
				{ "", "123", "Neteisingas prisijungimo vardas arba slaptažodis." } };
	}

	// Args: LOGIN, PASSWIORD
	@DataProvider(name = "loginAsAdmin")
	public static Object[][] loginAsAdmin() {
		return new Object[][] { { "admin", "pass" },

		};
	}

	// Args: LOGIN, PASSWIORD
	@DataProvider(name = "loginAsRepresentative")
	public static Object[][] loginAsRepresentative() {
		return new Object[][] { { "ZV", "123" },

		};
	}

}
