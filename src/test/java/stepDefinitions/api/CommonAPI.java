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
import pojos.*;
import utilities.Authentication;
import utilities.ConfigReader;

import java.lang.reflect.Array;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.junit.Assert.assertEquals;

public class CommonAPI {
    public static Pojo_TrelloBody responseBody;

    public  static Pojo_TrelloBody expectedBody;

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


        System.out.println(Arrays.toString(paths));

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

        System.out.println(fullPath);

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
        System.out.println(bodyDummy.toString());
        requestBody2=response.as(Pojo_BodyDummy.class);

    }


    @And("Response body ile expected body karsilastir")
    public void responseBodyIleExpectedBodyKarsilastir() {

       assertEquals(bodyDummy.getStatus(),requestBody2.getStatus());
      // assertEquals(bodyDummy.getDataDummy().getId(),requestBody2.getDataDummy().getId());
       assertEquals(bodyDummy.getData().getEmployee_age(),requestBody2.getData().getEmployee_age());
       assertEquals(bodyDummy.getData().getEmployee_name(),requestBody2.getData().getEmployee_name());
       assertEquals(bodyDummy.getData().getEmployee_salary(),requestBody2.getData().getEmployee_salary());
       assertEquals(bodyDummy.getData().getProfile_image(),requestBody2.getData().getProfile_image());
       assertEquals(bodyDummy.getMessage(),requestBody2.getMessage());

    }


    @Then("Response body icin gerekli Request Body {string}, {int}, {string}, {int} , {int} , {string}, {string}  hazirla")
    public void responseBodyIcinGerekliRequestBodyHazirla(String status, int id, String employee_name, int employee_salary, int employee_age, String profile_image, String message) {
        dataDummy=new Pojo_DataDummy(id,employee_name,employee_salary,employee_age,profile_image);

        bodyDummy=new Pojo_BodyDummy(status,dataDummy,message);

    }


    @Then("query parametrelerini set eder")
    public void queryParametreleriniSetEder() {
        // Oluşturulan POJO sınıflarının nesneleri oluşturuluyor
        Pojo_Prefs prefs = new Pojo_Prefs("private", false, "disabled", "members", "members", true, true, false, "regular", false, "blue", null, null, false, "dark", "#0079BF", "#0079BF", "#0079BF", true, true, true, true, true);
        Pojo_LabelNames labelNames = new Pojo_LabelNames("", "", "", "", "", "", "", "", "", "");
        Pojo_Limits limits = new Pojo_Limits();

// Ana sınıfın nesnesi oluşturuluyor
        expectedBody = new Pojo_TrelloBody("60e17b72a9cb99719157d055", "API2", "", null, false, "608bb1f8c74f6f3f28e98a67", null, false, "https://trello.com/b/qPOnicXa/api2", "https://trello.com/b/qPOnicXa", prefs, labelNames, limits);

// Ana sınıfın JSON formatında string'e dönüştürülmesi


        Response response=given().spec(HooksAPI.spec).contentType(ContentType.JSON)
                .headers("key","698916d06422f4298d917f6890694265","token","ATTA5d0ac97bc9884d7bf52411cf12a0126a2b50d2943dd8b82bec82f3e91566eb9aB826F723","name","API2")
                .when()
                .get(fullPath);
        response.prettyPrint();
        responseBody=response.as(Pojo_TrelloBody.class);

    }

    @Then("donen response’un expected body’ye sahip oldugunu test eder")
    public void donenResponseUnExpectedBodyYeSahipOldugunuTestEder() {

    }

    @Then("response sorgusu olusturulur")
    public void responseSorgusuOlusturulur() {

        Response response=given().header("Accept","application/json")
                .when()
                .get(ConfigReader.getProperty("son"));
        response.prettyPrint();
        System.out.println(response.getStatusCode());
    }

    @Then("Query paramlar olusturulur")
    public void queryParamlarOlusturulur() {

        String bodyString = "{\n" +
                "    \"cover\": {\n" +
                "        \"idAttachment\": null,\n" +
                "        \"color\": \"yellow\",\n" +
                "        \"idUploadedBackground\": null,\n" +
                "        \"size\": \"full\",\n" +
                "        \"brightness\": \"light\",\n" +
                "        \"idPlugin\": null\n" +
                "    }\n" +
                "}";

        JSONObject cover = new JSONObject();
        cover.put("idAttachment", JSONObject.NULL);
        cover.put("color", "black");
        cover.put("idUploadedBackground", JSONObject.NULL);
        cover.put("size", "full");
        cover.put("brightness", "light");
        cover.put("idPlugin", JSONObject.NULL);

        JSONObject body = new JSONObject();
        body.put("cover", cover);

        Response response = given().spec(HooksAPI.spec).contentType(ContentType.JSON)
                .queryParams("key","698916d06422f4298d917f6890694265","token","ATTA5d0ac97bc9884d7bf52411cf12a0126a2b50d2943dd8b82bec82f3e91566eb9aB826F723","name","APITestCard2")
                .when()
                .body(bodyString)
                .put(fullPath);

        response.prettyPrint();

    }

    @Then("get sorgusunu olusturur")
    public void getSorgusunuOlusturur() {

        Response response = given().spec(HooksAPI.spec).queryParam("api_key","MRUnIoWBLClekvZNymgUKl9dn2HmN7Kw7f0KmIb6")
                .when().get(fullPath);

        response.prettyPrint();


    }
}
