package com.alexivo.diplom_3.pom.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement exitButton;
    @FindBy(how = How.CSS, using = "a[href='/account/profile']")
    private SelenideElement profileButton;
    //Кнопка-логотип Stellar Burger
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement stellarBurgerHeader;
    //Кнопка-логотип Конструктор
    @FindBy(how = How.CSS, using = ".AppHeader_header__link__3D_hX[href='/']")
    private SelenideElement constructorHeader;

    @Step("Клик по кнопке 'Выход'")
    public LoginPage exitProfile(){
        exitButton.click();
        return page(LoginPage.class);
    }

    @Step("Проверка отображения кнопки 'Профиль'")
    public ProfilePage visibleProfileButton(){
        boolean isDisplayed = profileButton.shouldBe(visible, Duration.ofSeconds(5)).isDisplayed();
        Assert.assertTrue("Кнопка 'Профиль' не отображается", isDisplayed);
        return page(ProfilePage.class);
    }

    @Step("Клик по логотипу 'Stellar Burger'")
    public MainPage clickStellarBurgerHeader() {
        stellarBurgerHeader.click();
        return page(MainPage.class);
    }

    @Step("Клик по логотипу 'Конструктор'")
    public MainPage clickConstructorHeader() {
        constructorHeader.click();
        return page(MainPage.class);
    }

    @Step("Ожидание загрузки страницы")
    public void waitLoadPage() {
        profileButton.shouldBe(Condition.visible);
    }
}
