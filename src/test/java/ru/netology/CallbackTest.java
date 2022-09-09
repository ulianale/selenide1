package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CallbackTest {
    @Test
    void test() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Игорь Игорев");
        $("[data-test-id=phone] input").setValue("+79099001111");
        $("[data-test-id=agreement]").click();
        $(".button").click();

        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

}
