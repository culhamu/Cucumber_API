package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.Pojo_RegisterCustomer;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class CommonAPI {

   public static String fullPath;

   public static Pojo_RegisterCustomer requestBody;

    @Given("Api kullanicisi {string} path parametrelerini set eder")
    public void api_kullanicisi_path_parametrelerini_set_eder(String rawPath) {

        //https://trendlifebuy.com/api/register

        //api/register

        String[] paths=rawPath.split("/");//["api","register"]


        StringBuilder tempPath=new StringBuilder("{");

        for (int i = 0; i <paths.length ; i++) {

            String key="pp"+i;//pp0 pp1 pp2

            String value=paths[i].trim();

            HooksAPI.spec.pathParams(key,value);

            tempPath.append(key+"}/{");

        }

        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));

        fullPath=tempPath.toString();//  /{pp0}/{pp1}/{pp2}

    }

    @Then("Register Customer icin gerekli Request Body {string},{string},{string},{string},{string},{string},{string},{string},{string} hazirla")
    public void register_customer_icin_gerekli_request_body_hazirla(String first_name, String last_name, String username, String email, String password, String password_confirmation, String user_type, String phone, String referral_code) {

        //{
        //  "first_name": "sdad",
        //  "last_name": "sdsd",
        //  "username":"sdsadas",
        //  "email": "daasdasdss@gmail.com",
        //  "password": "As.123123",
        //  "password_confirmation": "As.123123",
        //  "user_type": "customer",
        //  "phone":"121123123",
        //  "referral_code": "44546545464546"
        //}

      requestBody=new Pojo_RegisterCustomer(first_name,last_name,username,email,password,password_confirmation,user_type,phone,referral_code);

    }

    @Then("Register Customer icin Post request gonder")
    public void register_customer_icin_post_request_gonder() {

        Response response=given().spec(HooksAPI.spec).contentType(ContentType.JSON)
                .when().body(requestBody)
                .post(fullPath);

        response.prettyPeek();

    }

}
