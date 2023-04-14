package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.en.Given;

public class CommonAPI {

   public static String fullPath;

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

        fullPath=tempPath.toString();

    }
}
