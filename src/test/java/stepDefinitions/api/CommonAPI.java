package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.C;
import org.json.JSONObject;
import pojos.Pojo_RegisterCustomer;
import utilities.Authentication;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class CommonAPI {

    public static String fullPath;

    Pojo_RegisterCustomer reqBody;
    JSONObject reqBodyJson;

    @Given("Api kullanicisi {string} path parametreleri set eder")
    public void api_kullanicisi_path_parametreleri_set_eder(String rawPaths) {

        // https://trendlifebuy.com/api/register

       // HooksAPI.spec.pathParams("pp1","api","pp2","register");

        //    api/register

        String [] paths = rawPaths.split("/"); // ["api","register"]

        StringBuilder tempPath = new StringBuilder("/{");

        for (int i = 0; i < paths.length ; i++) {

            String key = "pp" + i; // pp0 pp1 pp2
            String value = paths[i].trim();

            HooksAPI.spec.pathParam(key,value);

            tempPath.append(key + "}/{");
        }
        // System.out.println("tempPath = " + tempPath);

        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));

        // System.out.println("tempPath = " + tempPath);

        fullPath = tempPath.toString(); // /{pp0}/{pp1}/{pp2}

    }


    @Then("Register Customer icin gerekli Request Body {string},{string},{string},{string},{string},{string},{string},{string},{string} hazirla")
    public void registerCustomerIcinGerekliRequestBodyHazirla(String first_name, String last_name, String username, String email, String password, String password_confirmation, String user_type, String phone, String referral_code) {

        /*
        {
    "first_name": "sdad",
    "last_name": "sdsd",
    "username":"sdsadas",
    "email": "daasdasdss@gmail.com",
    "password": "As.123123",
    "password_confirmation": "As.123123",
    "user_type": "customer",
    "phone":"121123123",
    "referral_code": "44546545464546"
    }
         */
        reqBody = new Pojo_RegisterCustomer(first_name,last_name,username,email,password,password_confirmation,user_type,phone,referral_code);

    }

    @Then("Register Customer icin Post request gonder")
    public void registerCustomerIcinPostRequestGonder() {


        Response response = given()
                                .spec(HooksAPI.spec)
                                .contentType(ContentType.JSON)
                                .header("Accept","application/json")
                            .when()
                                .body(reqBody)
                                .post(fullPath);

        response.prettyPrint();





    }

    @Then("Login icin {string} ve {string} girilir")
    public void loginIcinVeGirilir(String email, String password) {

         /*
         {
            "email": "admin135@trendlifebuy.com",
            "password": "Trendlife123"
        }
        */

        reqBodyJson = new JSONObject();

        reqBodyJson.put("email", ConfigReader.getProperty(email));
        reqBodyJson.put("password", ConfigReader.getProperty(password));

    }

    @Then("Login icin Post request gonderilir")
    public void loginIcinPostRequestGonderilir() {

       Response response = given()
                                    .spec(HooksAPI.spec)
                                    .contentType(ContentType.JSON)
                                    .header("Accept","application/json")
                            .when()
                                    .body(reqBodyJson.toString())
                                    .post(fullPath);
        response.prettyPrint();
    }

    @Then("AllCountries icin Get request gonderilir")
    public void allcountriesIcinGetRequestGonderilir() {

        Response response = given()
                                .spec(HooksAPI.spec)
                                .headers("Authorization", "Bearer " + HooksAPI.token)
                                .contentType(ContentType.JSON)
                                .header("Accept","application/json")
                            .when()
                                .get(fullPath);

        response.prettyPrint();




    }
}
