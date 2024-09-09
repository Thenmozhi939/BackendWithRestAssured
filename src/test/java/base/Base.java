package base;

import io.restassured.RestAssured;
import staticResources.GLOBALSTATIC;
import testUtility.excelTextUtility.ExcelTestDataUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {


    public void setup() throws IOException {
        FileInputStream file=new FileInputStream("config/config.Properties");
        GLOBALSTATIC.prop=new Properties();
        GLOBALSTATIC.prop.load(file);
        RestAssured.baseURI=GLOBALSTATIC.prop.getProperty("baseURI");

        GLOBALSTATIC.testdata=new ExcelTestDataUtility("testData/sample.xlsx");


    }



}
