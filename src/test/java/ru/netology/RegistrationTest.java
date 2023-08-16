package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationTest {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static final Date currentDate = new Date();
    private static final Calendar cal = Calendar.getInstance();

    @BeforeEach
    void setup() {
        Configuration.headless = true;
        open("http://localhost:9999/");
    }

    @Test
    void shouldPassWithCyrillicAnd3DaysAfterToday() {
        cal.setTime(currentDate);
        cal.add(Calendar.DATE,3);

        $("[data-test-id='city'] input").setValue("Псков");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(dateFormat.format(cal.getTime()));
        $("[data-test-id='name'] input").setValue("Марк Твен");
        $("[data-test-id='phone'] input").setValue("+73846578392");
        $("[data-test-id=agreement]").click();
        $x("//span[text()='Забронировать']").click();
        $x("//div[contains(@class, 'notification_visible')]").shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldPassWithMoreThen3DaysAfterToday() {
        cal.setTime(currentDate);
        cal.add(Calendar.DATE,18);

        $("[data-test-id='city'] input").setValue("Томск");
        $("[data-test-id='date'] input").doubleClick().sendKeys(dateFormat.format(cal.getTime()));
        $("[data-test-id='name'] input").setValue("Петер Вебер");
        $("[data-test-id='phone'] input").setValue("+73846578392");
        $("[data-test-id=agreement]").click();
        $x("//span[text()='Забронировать']").click();
        $x("//div[contains(@class, 'notification_visible')]").shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldPassWithCyrillicAndHyphen() {
        cal.setTime(currentDate);
        cal.add(Calendar.DATE,3);

        $("[data-test-id='city'] input").setValue("Красноярск");
        $("[data-test-id='date'] input").doubleClick().sendKeys(dateFormat.format(cal.getTime()));
        $("[data-test-id='name'] input").setValue("Керн Анна-Мария");
        $("[data-test-id='phone'] input").setValue("+73846578392");
        $("[data-test-id=agreement]").click();
        $x("//span[text()='Забронировать']").click();
        $x("//div[contains(@class, 'notification_visible')]").shouldBe(visible, Duration.ofSeconds(15));
    }

}