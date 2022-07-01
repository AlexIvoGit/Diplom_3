package com.alexivo.diplom_3.pom.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class RecoverPage {
    private final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    //Заголовок Восстановление пароля
    @FindBy(how = How.XPATH, using = "//h2[text()='Восстановление пароля']")
    private SelenideElement recoverPasswordHeader;
    //Поле ввода Email
    @FindBy(how = How.CSS, using = "input[name='name']")
    private SelenideElement emailInput;
    //Кнопка Восстановить
    @FindBy(how = How.XPATH, using = "//button[text()='Восстановить']")
    private SelenideElement recoverButton;
    //Кнопка Войти
    @FindBy(how = How.CSS, using = ".Auth_link__1fOlj[href='/login']")
    private SelenideElement loginButton;

    @Step("Открыть страницу")
    public RecoverPage openPage(){
        open(URL);
        return page(RecoverPage.class);
    }

    @Step("Ввести 'email'")
    public void fillEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Клик по кнопке 'Войти'")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }
}
