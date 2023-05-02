package stepDefinitions.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import hooks.api.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.Pojo_BodyDummy;
import pojos.Pojo_DataDummy;
import pojos.Pojo_RegisterCustomer;
import utilities.Authentication;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CommonAPI {

    public static Pojo_DataDummy dataDummy;

    public static Pojo_BodyDummy bodyDummy;

    public static Pojo_BodyDummy requestBody2;

   public static String fullPath;

   public static Pojo_RegisterCustomer requestBody;

   public static JSONObject requestBodyJson;

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



    @Then("Login icin gerekli {string} ve {string} girilir")
    public void loginIcinGerekliVeHazirla(String email, String password) {


        /*
        {
    "email": "admin135@trendlifebuy.com",
    "password": "Trendlife123"
}
         */

        requestBodyJson=new JSONObject();
        requestBodyJson.put("email",ConfigReader.getProperty(email));
        requestBodyJson.put("password",ConfigReader.getProperty(password));
    }

    @Then("Login icin Post request gonderilir")
    public void loginIcinPostRequestGonderilir() {

        Response response=given().spec(HooksAPI.spec).contentType(ContentType.JSON)
                .when().body(requestBodyJson.toString())
                .header("Accept","application/json")
                .post(fullPath);

       response.prettyPrint();
    }

    @Then("AllCountries icin Get request gonderilir")
    public void allcountriesIcinGetRequestGonderilir() {

        Response response=given().spec(HooksAPI.spec).headers("Authorization","Bearer "+ Authentication.generateToken())
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .get(fullPath);

        response.prettyPrint();
    }

    //{
    //"status": "success",
    //"data": {
    //"id": 3,
    //"employee_name": "Ashton Cox",
    //"employee_salary": 86000,
    //"employee_age": 66,
    //"profile_image": ""
    //},
    //"message": "Successfully! Record has been fetched."
    //}


    @Then("Donen response'i kaydet")
    public void donenResponseIKaydet() {

        Response response=given().spec(HooksAPI.spec).contentType(ContentType.JSON)
                .when()
                .get(fullPath);

        response.prettyPrint();
       requestBody2=response.as(Pojo_BodyDummy.class);
    }


    @And("Response body ile expected body karsilastir")
    public void responseBodyIleExpectedBodyKarsilastir() {

       assertEquals(bodyDummy.getStatus(),requestBody2.getStatus());
      // assertEquals(bodyDummy.getDataDummy().getId(),requestBody2.getDataDummy().getId());
       assertEquals(bodyDummy.getDataDummy().getEmployee_age(),requestBody2.getDataDummy().getEmployee_age());
       assertEquals(bodyDummy.getDataDummy().getEmployee_name(),requestBody2.getDataDummy().getEmployee_name());
       assertEquals(bodyDummy.getDataDummy().getEmployee_salary(),requestBody2.getDataDummy().getEmployee_salary());
       assertEquals(bodyDummy.getDataDummy().getProfile_image(),requestBody2.getDataDummy().getProfile_image());
       assertEquals(bodyDummy.getMessage(),requestBody2.getMessage());

    }


    @Then("Response body icin gerekli Request Body {string}, {int}, {string}, {int} , {int} , {string}, {string}  hazirla")
    public void responseBodyIcinGerekliRequestBodyHazirla(String status, int id, String employee_name, int employee_salary, int employee_age, String profile_image, String message) {
        dataDummy=new Pojo_DataDummy(id,employee_name,employee_salary,employee_age,profile_image);

        bodyDummy=new Pojo_BodyDummy(status,dataDummy,message);

    }
}
