package com.alexivo.diplom_3.pom.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {
    private final String URL = "https://stellarburgers.nomoreparties.site/register";

    //Заголовок 'Регистрация'
    @FindBy(how = How.XPATH, using = "//h2[text()='Регистрация']")
    private SelenideElement registerHeader;
    //Поле ввода Имя
    @FindBy(how = How.XPATH, using = "//fieldset[1]/div/div/input")
    private SelenideElement nameInput;
    //Поле ввода Email
    @FindBy(how = How.XPATH, using = "//fieldset[2]/div/div/input")
    private SelenideElement emailInput;
    //Поле ввода Пароль
    @FindBy(how = How.XPATH, using = "//fieldset[3]/div/div/input")
    private SelenideElement passwordInput;
    //Кнопка Зарегистрироваться
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement registrationButton;
    //Кнопка Войти
    @FindBy(how = How.CSS, using = ".Auth_link__1fOlj[href='/login']")
    private SelenideElement loginButton;
    //Поле ошибки при вводе некорректного пароля
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement errorPassword;
    //Поле ошибки при регистрации существующего пользователя
    @FindBy(how = How.XPATH, using = "//p[text()='Такой пользователь уже существует']")
    private SelenideElement errorUserIsAlreadyRegistered;

    @Step("Открыть страницу регистрации")
    public RegistrationPage openPage(){
        open(URL);
        return page(RegistrationPage.class);
    }

    @Step("Ввести имя пользователя")
    public RegistrationPage fillName(String name) {
        nameInput.setValue(name);
        return page(RegistrationPage.class);
    }

    @Step("Ввести электронную почту'")
    public RegistrationPage fillEmail(String email) {
        emailInput.setValue(email);
        return page(RegistrationPage.class);
    }

    @Step("Ввести пароль")
    public RegistrationPage fillPassword(String password) {
        passwordInput.setValue(password);
        return page(RegistrationPage.class);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public LoginPage clickRegistrationButton() {
        registrationButton.click();
        return page(LoginPage.class);
    }

    @Step("Клик по кнопке 'Войти'")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Зарегистрировать пользователя: Имя - {name}, Электронная почта - {email}, Пароль - {password}")
    public LoginPage registrationUser(String name, String email, String password) {
        fillName(name).
        fillEmail(email).
        fillPassword(password).
                clickRegistrationButton();
        return page(LoginPage.class);
    }

    @Step("Проверка отображения ошибки 'Пользователь уже существует'")
    public RegistrationPage errorUserIsAlreadyRegisteredIsDisplayed() {
        boolean isDisplayed = errorUserIsAlreadyRegistered.shouldBe(visible, Duration.ofSeconds(5)).isDisplayed();
        Assert.assertTrue("Ошибка 'Пользователь уже существует' не отображается", isDisplayed);
        return page(RegistrationPage.class);
    }

    @Step("Проверка отображения ошибки 'Не корректный пароль'")
    public RegistrationPage errorPasswordIsDisplayed() {
        boolean isDisplayed = errorPassword.shouldBe(visible, Duration.ofSeconds(5)).isDisplayed();
        Assert.assertTrue("Ошибка 'Не корректный пароль' не отображается", isDisplayed);
        return page(RegistrationPage.class);
    }
}
