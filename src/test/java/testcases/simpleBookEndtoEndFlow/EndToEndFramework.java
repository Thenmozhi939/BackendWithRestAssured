package testcases.simpleBookEndtoEndFlow;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.juneau.json.JsonParser;
import org.apache.juneau.json.JsonSerializer;
import org.testng.annotations.Test;
import pojoResouces.pojoRequest.GenerateBearerToken;
import pojoResouces.pojoResponse.BearerToken;
import testUtility.excelTextUtility.ExcelTestDataUtility;

public class EndToEndFramework {

    BearerToken bt;

    @Test
    public void generateBearerToken()
    {

        ExcelTestDataUtility data=new ExcelTestDataUtility("testData/SimpleBook1.xlsx");
        String clientName=data.getCellData("BearerToken",1,2);
        GenerateBearerToken token=new GenerateBearerToken(clientName,"thenmozhi21@gmail.com");
        token.setClientName(clientName);
        token.setClientEmail("thenmozhi21@gmail.com");
        JsonSerializer js=JsonSerializer.DEFAULT_READABLE;
       String requestPayload=js.serialize(token);
       RestAssured.baseURI="https://simple-books-api.glitch.me/";
        Response res=RestAssured.given()
                .body(requestPayload)
                .header("Content-Type","application/json")
                .post("/api-clients/")
                .then().log().all().extract().response();
        String responsePayload=res.body().asString();

        JsonParser jp=JsonParser.DEFAULT;
       bt= jp.parse(responsePayload,BearerToken.class);

    }



}
