package com.alexivo.diplom_3.pom.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private final String URL = "https://stellarburgers.nomoreparties.site/login";
    //Заголовок Зарегистрироваться
    @FindBy(xpath = "//a[text()='Зарегистрироваться']")
    private SelenideElement registerButton;
    //Заголовок Вход
    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement entranceHeader;
    //Поле ввода Email
    @FindBy(how = How.CSS, using = "input[name='name']")
    private SelenideElement emailInput;
    //Поле ввода Пароль
    @FindBy(how = How.CSS, using = "input[name='Пароль']")
    private SelenideElement passwordInput;
    //Кнопка Войти
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement loginButton;
    //Кнопка Восстановить пароль
    @FindBy(how = How.CSS, using = ".Auth_link__1fOlj[href='/forgot-password']")
    private SelenideElement recoverPasswordButton;

    @Step("Открыть страницу авторизации")
    public LoginPage openLoginPage() {
        open(URL);
        return page(LoginPage.class);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public RegistrationPage clickRegisterButton() {
        registerButton.shouldBe(visible).click();
        return page(RegistrationPage.class);
    }

    @Step("Ввести 'email'")
    public LoginPage fillEmail(String email) {
        emailInput.setValue(email);
        return page(LoginPage.class);
    }

    @Step("Ввести 'Пароль'")
    public LoginPage fillPassword(String password) {
        passwordInput.setValue(password);
        return page(LoginPage.class);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Клик по кнопке восстановить пароль")
    public RecoverPage clickRecoverPasswordButton() {
        recoverPasswordButton.click();
        return page(RecoverPage.class);
    }

    @Step("Авторизоваться пользователем: {email}, {password}")
    public MainPage loginUser(String email, String password) {
        fillEmail(email).
                fillPassword(password).
                clickLoginButton();
        return page(MainPage.class);
    }

    public LoginPage waitLoadPage() {
        entranceHeader.shouldBe(Condition.visible);
        return page(LoginPage.class);
    }
}
