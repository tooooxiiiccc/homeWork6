import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;


import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AutomationPracticeForm {
    private final SelenideElement firstNameInput  = $x("//input[@id='firstName']");
    private final SelenideElement lastNameInput  = $x("//input[@id='lastName']");
    private final SelenideElement emailInput  = $x("//input[@id='userEmail']");
    private final SelenideElement phoneNumberInput  = $x("//input[@id='userNumber']");
    private final SelenideElement uploadPictureInput  = $x("//input[@id='uploadPicture']");
    private final SelenideElement addresInput  = $x("//textarea[@id='currentAddress']");
    private final SelenideElement stateInput  = $x("//input[@id='react-select-3-input']");
    private final SelenideElement cityInput  = $x("//input[@id='react-select-4-input']");
    private final SelenideElement submitButton = $x("//button[@id='submit']");

    public void openMainPage(String url) {
        Selenide.open(url);
        Selenide.sleep(1000);
    }


    public void setValueFirstNameAndLastName(String firstName, String lastName) {
        firstNameInput.setValue(firstName);
        lastNameInput.setValue(lastName);
        firstNameInput.shouldBe(Condition.visible);
        lastNameInput.shouldBe(Condition.visible);
    }

    public void setEmailInput(String email) {
        emailInput.setValue(email);
    }

    public void setGender(String gender) {
        $x("//label[contains(text(),'Male')]")
            .shouldBe(Condition.interactable)
            .click();
    }

    public void setPhoneNumber(String phone) {
        phoneNumberInput.setValue(phone);
    }

    public void pickMonth(String month, String year, String day) {
        $x("//input[@id='dateOfBirthInput']")
            .click();
        $x("//select[@class='react-datepicker__month-select']")
            .selectOption(month);
        $x("//select[@class='react-datepicker__year-select']")
            .selectOption(year);
        $x("//div[contains(@class, 'react-datepicker__day') and text()='14']")
            .shouldBe(Condition.interactable)
            .click();

    }

    public void pickSubjects(String Language) {
        $("#subjectsInput")
            .shouldBe(Condition.interactable)
            .setValue(Language)
            .sendKeys(Keys.ENTER);
    }

    public void pickHobby(String hobby) {
        $x("//label[contains(text(), '" + hobby + "')]")
            .shouldBe(Condition.interactable)
            .click();
    }

    public void uploadPicture(String fileName) {
        uploadPictureInput.uploadFromClasspath(fileName);
    }

    public void setAddress(String address) {
        addresInput.setValue(address);
    }

    public void setStateAndCity(String state, String city) {
        stateInput.setValue(state).sendKeys(Keys.ENTER);
        cityInput.setValue(city).sendKeys(Keys.ENTER);;
    }

    public void submitForm() {
        submitButton.click();
    }

    public Map<String, String> getExpectedResults() {
        return Map.of(
            "Student Name", "Danila Morozov",
            "Student Email", "danila.morozov25@mail.ru",
            "Gender", "Male",
            "Mobile", "9266827407",
            "Date of Birth", "14 November,2001",
            "Subjects", "English",
            "Hobbies", "Reading",
            "Picture", "выходитебесы.png",
            "Address", "ул. Пушкина, д. Колотушкина",
            "State and City", "Rajasthan Jaiselmer"
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
