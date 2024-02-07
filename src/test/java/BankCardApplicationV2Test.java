import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BankCardApplicationV2Test {
    @Test
    public void shouldTestAllBlankFields() {

        open("http://localhost:9999/");

        SelenideElement form = $("form");

        form.$("button").click();
        $(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));


    }

    @Test
    public void shouldTestNameFieldLatinLetters() {

        open("http://localhost:9999/");

        SelenideElement form = $("form");

        form.$("[data-test-id=name] input").setValue("Ivanov Semen");
        form.$("button").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    public void shouldTestNameFieldSlash() {

        open("http://localhost:9999/");

        SelenideElement form = $("form");

        form.$("[data-test-id=name] input").setValue("Иванов/Петров");
        form.$("button").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    public void shouldTestPhoneFieldWithoutPlus() {

        open("http://localhost:9999/");

        SelenideElement form = $("form");

        form.$("[data-test-id=name] input").setValue("Иванов-Петров Семен Васильевич");
        form.$("[data-test-id=phone] input").setValue("89061236789");
        form.$("button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    public void shouldTestWithoutAgreement() {

        open("http://localhost:9999/");

        SelenideElement form = $("form");

        form.$("[data-test-id=name] input").setValue("Иванов-Петров Семен Васильевич");
        form.$("[data-test-id=phone] input").setValue("+79061236789");
        form.$("button").click();
        $("[data-test-id=agreement]").shouldHave(attribute("class", "input_invalid"));

    }
}
