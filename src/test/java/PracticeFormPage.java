import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PracticeFormPage extends BaseTest {
    AutomationPracticeForm mainPage = new AutomationPracticeForm();
    private final static String BASE_URL = "https://demoqa.com/automation-practice-form";
    private final static String STRING_FIRSTNAME = "Danila";
    private final static String STRING_LASTNAME = "Morozov";
    private final static String EMAIL = "danila.morozov25@mail.ru";
    private final static String GENDER = "Male";
    private final static String PHONE_NUMBER = "9266827407";
    private final static String MONTH = "November";
    private final static String YEAR = "2001";
    private final static String DAY = "14";
    private final static String LANGUAGE = "English";
    private final static String ADDRESS = "ул. Пушкина, д. Колотушкина";
    private final static String STATE = "Rajasthan";
    private final static String CITY = "Jaiselmer";


    @Test
    @DisplayName("Заполнение формы и проверка совпадения заполненной формы")
    public void checkSetValues() {
        mainPage.openMainPage(BASE_URL);
        mainPage.setValueFirstNameAndLastName(STRING_FIRSTNAME, STRING_LASTNAME);
        mainPage.setEmailInput(EMAIL);
        mainPage.setGender(GENDER);
        mainPage.setPhoneNumber(PHONE_NUMBER);
        mainPage.pickData(MONTH, YEAR, DAY);
        mainPage.pickSubjects(LANGUAGE);
        mainPage.pickHobby("Reading");
        mainPage.uploadPicture("выходитебесы.png");
        mainPage.setAddress(ADDRESS);
        mainPage.setStateAndCity(STATE, CITY);
        mainPage.submitForm();
        verifyResults();
        Selenide.sleep(10000);
    }

    public Map<String, String> getExpectedResults() {
        return Map.of(
            "Student Name", STRING_FIRSTNAME + " " + STRING_LASTNAME,
            "Student Email", EMAIL,
            "Gender", GENDER,
            "Mobile", PHONE_NUMBER,
            "Date of Birth", DAY + " " + MONTH + "," + YEAR,
            "Subjects", LANGUAGE,
            "Hobbies", "Reading",
            "Picture", "выходитебесы.png",
            "Address", ADDRESS,
            "State and City", STATE + " " + CITY
        );
    }

    public Map<String, String> getActualResult(){
        Map<String, String> actualResult = new HashMap<>();
        $(".modal-content").shouldBe(Condition.visible);

        ElementsCollection rowsOfResults = $$("table tbody tr");

        for (SelenideElement row : rowsOfResults) {
            String label = row.$("td:first-child").getText();
            String value = row.$("td:last-child").getText();
            actualResult.put(label, value);
        }
        return actualResult;
    }

    public void verifyResults(){
        Map<String, String> expectedResult = getExpectedResults();
        Map<String, String> actualResult = getActualResult();

        for (String key : expectedResult.keySet()) {
            String actualValue = actualResult.get(key);
            String expectedValue = expectedResult.get(key);

            assertThat(actualValue).isEqualTo(expectedValue)
                .as("Сравнение полей: " + key);
        }
    }
}