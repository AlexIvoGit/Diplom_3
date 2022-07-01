package com.alexivo.diplom_3.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserClient {
    private final String JSON = "application/json";
    private final String ENDPOINT = "https://stellarburgers.nomoreparties.site/api/auth";

    @Step("Создание пользователя")
    public Response createUser(Map<String, String> param){
        return given().log().all()
                .header("Content-type", JSON)
                .and()
                .body(param)
                .when()
                .post(ENDPOINT + "/register");
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken){
        return given().log().all()
                .header("Authorization", accessToken)
                .delete(ENDPOINT + "/user");
    }

    @Step("Генерация тестовых данных для создания пользователя")
    public Map<String, String> getMapGeneratedDataUser() {
        HashMap<String, String> generatedData = new HashMap<>();
        generatedData.put("email", RandomStringUtils.randomAlphabetic(10) + "@yandex.ru");
        generatedData.put("password", RandomStringUtils.randomAlphabetic(10));
        generatedData.put("name", RandomStringUtils.randomAlphabetic(10));
        return generatedData;
    }
}
