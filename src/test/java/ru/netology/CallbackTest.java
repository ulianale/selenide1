package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CallbackTest {

    @Test
    void test1() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Игорь Игорев");
        $("[data-test-id=phone] input").setValue("+79099001111");
        $("[data-test-id=agreement]").click();
        $(".button").click();

        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void test2() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Igor");
        $("[data-test-id=phone] input").setValue("+79099001111");
        $("[data-test-id=agreement]").click();
        $(".button").click();

        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void test3() {
        open("http://localhost:9999");

        $(".button").click();

        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void test4() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Игорь Игорев");
        $("[data-test-id=agreement]").click();
        $(".button").click();

        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }


}
