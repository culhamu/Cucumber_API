package hooks.api;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;

public class HooksAPI {

    public static RequestSpecification spec;

    @Before
    public void setupApi(){

    spec=new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url_trello")).build();


}




}
