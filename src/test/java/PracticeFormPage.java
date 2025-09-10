import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class PracticeFormPage extends BaseTest {
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
    @DisplayName("Заполнение формы")
    public void checkSetValues() {
        AutomationPracticeForm mainPage = new AutomationPracticeForm();
        mainPage.openMainPage(BASE_URL);
        mainPage.setValueFirstNameAndLastName(STRING_FIRSTNAME, STRING_LASTNAME);
        mainPage.setEmailInput(EMAIL);
        mainPage.setGender(GENDER);
        mainPage.setPhoneNumber(PHONE_NUMBER);
        mainPage.pickMonth(MONTH, YEAR, DAY);
        mainPage.pickSubjects(LANGUAGE);
        mainPage.pickHobby("Reading");
        mainPage.uploadPicture("выходитебесы.png");
        mainPage.setAddress(ADDRESS);
        mainPage.setStateAndCity(STATE, CITY);
        mainPage.submitForm();
        mainPage.verifyResults();
        Selenide.sleep(10000);
    }
}

//    /**
//     */ "Я могу как-то вызвать метод проверки соответствия полей отдельно?
//     Или как раз это можно сделать с переходом?"
//     */
//    @Test
//    @DisplayName("Проверка ключей значений при появлении успешной отправке заявки")
//    public void verifyResultsExpectedAndActual(){
//        AutomationPracticeForm mainPage = new AutomationPracticeForm();
//        mainPage.verifyResults();
//    }
//    }


