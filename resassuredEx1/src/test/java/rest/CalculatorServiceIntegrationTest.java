/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.parsing.Parser;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author t470
 */
public class CalculatorServiceIntegrationTest {

    public CalculatorServiceIntegrationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/Test1";
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void serverIsRunning() {
        System.out.println("Server is running");
        given().
                when().get().
                then().
                statusCode(200);
    }

    @Test
    public void addOperation() {
        System.out.println("add");
        given().pathParam("n1", 2).pathParam("n2", 2).
                when().get("/api/calculator/add/{n1}/{n2}").
                then().
                statusCode(200).
                body("result", equalTo(4), "operation", equalTo("2 + 2"));
    }

    @Test
    public void addOperationWrongArguments() {
        System.out.println("add wrong arg");
        given().pathParam("n1", 2).pathParam("n2", 2.2).
                when().get("/api/calculator/add/{n1}/{n2}").
                then().
                statusCode(400).
                body("code", equalTo(400));
    }

    @Test
    public void subOperation() {
        System.out.println("sub");
        given().pathParam("n1", 2).pathParam("n2", 2).
                when().get("/api/calculator/sub/{n1}/{n2}").
                then().
                statusCode(200).
                body("result", equalTo(0), "operation", equalTo("2 - 2"));
    }

    @Test
    public void mulOperation() {
        System.out.println("mul");
        given().pathParam("n1", 2).pathParam("n2", 2).
                when().get("/api/calculator/mul/{n1}/{n2}").
                then().
                statusCode(200).
                body("result", equalTo(4), "operation", equalTo("2 * 2"));
    }

    @Test
    public void divOperation() {
        System.out.println("div");
        given().pathParam("n1", 2).pathParam("n2", 2).
                when().get("/api/calculator/div/{n1}/{n2}").
                then().
                statusCode(200).
                body("result", equalTo(1), "operation", equalTo("2 / 2"));
    }

    @Test
    public void divOperationDivByZero() {
        System.out.println("div by zero");
        given().pathParam("n1", 2).pathParam("n2", 0).
                when().get("/api/calculator/div/{n1}/{n2}").
                then().
                statusCode(500).
                body("code", equalTo(500), "message", equalTo("/ by zero"));
    }

    @Test
    public void nonExistingRoute() {
        System.out.println("nonExistingRoute");
        given().pathParam("n1", 2).pathParam("n2", 0).
                when().get("/api/calculator/sqr/{n1}/{n2}").
                then().
                statusCode(404).
                body("code", equalTo(404));
    }

}
