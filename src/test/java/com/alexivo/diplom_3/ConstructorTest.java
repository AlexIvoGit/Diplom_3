package com.alexivo.diplom_3;

import com.alexivo.diplom_3.pom.pages.MainPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

@DisplayName("Конструктор бургера")
public class ConstructorTest {
    MainPage mainPage = Selenide.page(MainPage.class);

    @Test
    @DisplayName("Переходы к разделам: «Булки», «Соусы», «Начинки»")
    public void goToSectionsTest() throws InterruptedException {
        mainPage
                .openPage()
                .clickIngredientButton();
        Assert.assertEquals("Начинки", mainPage.getTextActionButton());

        mainPage
                .clickSauceButton();
        Assert.assertEquals("Соусы", mainPage.getTextActionButton());

        mainPage
                .clickBunButton();
        Assert.assertEquals("Булки", mainPage.getTextActionButton());
    }
}
