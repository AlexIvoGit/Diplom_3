package com.alexivo.diplom_3;

import com.alexivo.diplom_3.api.UserClient;
import com.alexivo.diplom_3.pom.pages.MainPage;
import com.alexivo.diplom_3.pom.pages.ProfilePage;
import com.alexivo.diplom_3.pom.pages.RecoverPage;
import com.alexivo.diplom_3.pom.pages.RegistrationPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

@DisplayName("Авторизация и переход в личный кабинет")
public class AuthorizationTest {
    MainPage mainPage = Selenide.page(MainPage.class);
    RegistrationPage registrationPage = Selenide.page(RegistrationPage.class);
    RecoverPage recoverPage = Selenide.page(RecoverPage.class);
    ProfilePage profilePage = Selenide.page(ProfilePage.class);
    UserClient userClient = new UserClient();
    String accessToken;
    String email;
    String name;
    String password;

    @Before
    public void setUp() {
        Map<String, String> generatedDataUser = userClient.getMapGeneratedDataUser();
        email = generatedDataUser.get("email");
        name = generatedDataUser.get("name");
        password = generatedDataUser.get("password");
        accessToken = userClient.createUser(generatedDataUser).then().extract().body().path("accessToken");
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void authByLoginAccountOnMainPage() {
        mainPage
                .openPage()
                .clickLoginAccountButton()
                .loginUser(email, password)
                .placeAnOrderButtonIsDisplayed();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void authByPersonalAccountButtonOnMainPage() {
        mainPage
                .openPage()
                .clickPersonalAccountButton()
                .loginUser(email, password)
                .placeAnOrderButtonIsDisplayed();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void authByEntryButtonOnRegistrationPage() {
        registrationPage
                .openPage()
                .clickLoginButton()
                .loginUser(email, password)
                .placeAnOrderButtonIsDisplayed();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void authByEntryButtonOnRecoveryPage() {
        recoverPage
                .openPage()
                .clickLoginButton()
                .loginUser(email, password)
                .placeAnOrderButtonIsDisplayed();
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void goToPersonalAccountTest() {
        mainPage
                .openPage()
                .clickLoginAccountButton()
                .loginUser(email, password)
                .clickPersonalAccountButton();
        profilePage.visibleProfileButton();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void logoutAccount() {
        mainPage
                .openPage()
                .clickLoginAccountButton()
                .loginUser(email, password)
                .clickPersonalAccountButton();
        profilePage
                .exitProfile()
                .waitLoadPage();
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор»")
    public void goToMainPageByHeaderConstructor() {
        mainPage
                .openPage()
                .clickLoginAccountButton()
                .loginUser(email, password)
                .clickPersonalAccountButton();
        profilePage
                .clickConstructorHeader()
                .placeAnOrderButtonIsDisplayed();
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers")
    public void goToMainPageByLogoStellarBurger() {
        mainPage
                .openPage()
                .clickLoginAccountButton()
                .loginUser(email, password)
                .clickPersonalAccountButton();
        profilePage
                .clickStellarBurgerHeader()
                .placeAnOrderButtonIsDisplayed();
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
