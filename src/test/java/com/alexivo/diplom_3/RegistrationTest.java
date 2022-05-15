package com.alexivo.diplom_3;

import com.alexivo.diplom_3.api.UserClient;
import com.alexivo.diplom_3.pom.pages.MainPage;
import com.alexivo.diplom_3.pom.pages.RegistrationPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@DisplayName("Регистрация пользователя")
public class RegistrationTest {
    MainPage mainPage = Selenide.page(MainPage.class);
    RegistrationPage registrationPage = Selenide.page(RegistrationPage.class);
    UserClient userClient = new UserClient();
    String name;
    String email;
    String password;
    String accessToken;

    @Before
    public void setUp() {
        name = RandomStringUtils.randomAlphabetic(10);
        email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphabetic(6);
    }

    @Test
    @DisplayName("Регистрация пользователя с валидными данными")
    public void registerUserWithValidData() {
        accessToken = mainPage
                .openPage()
                .clickLoginAccountButton()
                .clickRegisterButton()
                .registrationUser(name, email, password)
                .waitLoadPage()
                .loginUser(email, password)
                .placeAnOrderButtonIsDisplayed()
                .getAccessToken();
    }

    @Test
    @DisplayName("Проверка получения ошибки не корректного пароля")
    public void registerUserWithIncorrectPassword() {
        mainPage
                .openPage()
                .clickLoginAccountButton()
                .clickRegisterButton()
                .registrationUser(name, email, RandomStringUtils.randomAlphabetic(5));
        registrationPage.errorPasswordIsDisplayed();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken)
                    .then()
                    .log().all();
            accessToken = null;
        }
    }
}
