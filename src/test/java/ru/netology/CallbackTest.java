package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CallbackTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldValidForm() {
        $("[data-test-id=name] input").setValue("Игорь Иго-игорев");
        $("[data-test-id=phone] input").setValue("+79099001111");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotValidName() {
        $("[data-test-id=name] input").setValue("Igor");
        $("[data-test-id=phone] input").setValue("+79099001111");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldEmptyName() {
        $("[data-test-id=phone] input").setValue("+79099001111");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotValidPhoneWithoutPlus() {
        $("[data-test-id=name] input").setValue("Игорь Игорев");
        $("[data-test-id=phone] input").setValue("79099001111");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotValidPhoneMoreNumber() {
        $("[data-test-id=name] input").setValue("Игорь Игорев");
        $("[data-test-id=phone] input").setValue("+790012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotValidPhone() {
        $("[data-test-id=name] input").setValue("Игорь Игорев");
        $("[data-test-id=phone] input").setValue("Игорь");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldEmptyPhone() {
        $("[data-test-id=name] input").setValue("Игорь Игорев");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNoCheckBox() {
        $("[data-test-id=name] input").setValue("Игорь Игорев");
        $("[data-test-id=phone] input").setValue("+79099001111");
        $(".button").click();
        $(".checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldEmptyForm() {
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}
