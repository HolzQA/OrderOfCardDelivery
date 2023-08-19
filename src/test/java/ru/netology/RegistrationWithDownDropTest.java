package ru.netology;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationWithDownDropTest {
    public static String setLocalDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldPassSelectFromDropDownElements() {
        open("http://localhost:9999/");

        $("[data-test-id='city'] input").setValue("Пс");
        $x("//span[text()='Псков']").click();

        int addDays = 7;
        $("[data-test-id='date'] input").click();
        if (!setLocalDate(addDays, "MM").equals(setLocalDate(0, "MM"))) {
            $("[data-step='1']").click();
        }
        $$(".calendar__day").findBy(text(setLocalDate(addDays, "d"))).click();

        $("[data-test-id='name'] input").setValue("Петер Вебер");
        $("[data-test-id='phone'] input").setValue("+73846578392");
        $("[data-test-id=agreement]").click();
        $x("//span[text()='Забронировать']").click();
        $x("//div[contains(@class, 'notification_visible')]")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно! Встреча успешно забронирована на " + setLocalDate(addDays, "dd.MM.yyyy")));
    }

//    @Test
//    void shouldPassSelectFromDropDownCalendar() {
//        open("http://localhost:9999/");
//
//        $("[data-test-id='city'] input").setValue("Томск");
//
//        int addDays = 7;
//        $("[data-test-id='date'] input").doubleClick();
//        if (!setLocalDate(addDays, "MM").equals(setLocalDate(0, "MM"))) {
//            $("[data-step='1']").click();
//        }
//        $$(".calendar__day").findBy(text(setLocalDate(addDays, "d"))).click();
//
//        $("[data-test-id='name'] input").setValue("Марк Твен");
//        $("[data-test-id='phone'] input").setValue("+73846578392");
//        $("[data-test-id=agreement]").click();
//        $x("//span[text()='Забронировать']").click();
//        $x("//div[contains(@class, 'notification_visible')]")
//                .shouldBe(visible, Duration.ofSeconds(15))
//                .shouldHave(exactText("Успешно! Встреча успешно забронирована на " + setLocalDate(addDays, "dd.MM.yyyy")));
//    }

}
