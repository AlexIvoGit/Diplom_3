package com.alexivo.diplom_3.pom.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/";
    //Кнопка Войти в аккаунт
    @FindBy(how = How.CSS, using = ".button_button_size_large__G21Vg")
    private SelenideElement loginAccountButton;
    //Кнопка Оформить заказ
    @FindBy(how = How.XPATH, using = "//div/button[text()='Оформить заказ']")
    private SelenideElement placeAnOrderButton;
    //Кнопка Личный Кабинет
    @FindBy(how = How.CSS, using = ".AppHeader_header__link__3D_hX[href='/account']")
    private SelenideElement personalAccountButton;
    //Кнопка-логотип Stellar Burger
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement stellarBurgerHeader;
    //Кнопка-логотип Конструктор
    @FindBy(how = How.CSS, using = ".AppHeader_header__link__3D_hX[href='/']")
    private SelenideElement constructorHeader;
    //Кнопка Булки
    @FindBy(how = How.XPATH, using = "//span[text()='Булки']")
    private SelenideElement bunButton;
    //Кнопка Соусы
    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']")
    private SelenideElement sauceButton;
    //Кнопка Начинки
    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']")
    private SelenideElement ingredientButton;

    @Step("Открыть страницу")
    public MainPage openPage(){
        open(URL);
        return page(MainPage.class);
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public LoginPage clickLoginAccountButton() {
        loginAccountButton.click();
        return page(LoginPage.class);
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public LoginPage clickPersonalAccountButton() {
        personalAccountButton.click();
        return page(LoginPage.class);
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

    @Step("Клик по кнопке 'Булки'")
    public void clickBunButton() {
        bunButton.click();
    }

    @Step("Клик по кнопке 'Соусы'")
    public void clickSauceButton() {
        sauceButton.click();
    }

    @Step("Клик по кнопке 'Начинки'")
    public void clickIngredientButton() {
        ingredientButton.click();
    }

    @Step("Проверка наличия кнопки 'Оформить заказ'")
    public MainPage placeAnOrderButtonIsDisplayed() {
        boolean isDisplayed = placeAnOrderButton.shouldBe(visible, Duration.ofSeconds(5)).isDisplayed();
        Assert.assertTrue("Кнопка оформить заказ не отображается", isDisplayed);
        return page(MainPage.class);
    }

    @Step("Проверка наличия кнопки 'Вход в аккаунт'")
    public MainPage loginAccountButtonIsDisplayed() {
        boolean isDisplayed = loginAccountButton.shouldBe(visible, Duration.ofSeconds(5)).isDisplayed();
        Assert.assertTrue("Кнопка 'Вход в аккаунт' не отображается", isDisplayed);
        return page(MainPage.class);
    }

    @Step("Ожидание загрузки страницы и появления кнопки 'Оформить заказ'")
    public MainPage waitLoad() {
        placeAnOrderButton.shouldBe(visible);
        return page(MainPage.class);
    }

    @Step("Получение текста активной кнопки конструктора")
    public String getTextActionButton() throws InterruptedException {
        Thread.sleep(1000);
        return $(By.className("tab_tab_type_current__2BEPc")).getText();
    }

    @Step("Получить токен пользователя")
    public String getAccessToken() {
        return localStorage().getItem("accessToken");
    }
}
