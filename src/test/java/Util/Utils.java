package Util;

import Config.ProductModel;
import Config.UserModel;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;


public class Utils {

    static final String adminJSONFile = "./src/test/resources/admin.json";
    static final String userJSONFile = "./src/test/resources/userCredential.json";
    static final String userInfoJSONFile = "./src/test/resources/userInfo.json";

    public static String getLatestEmailID() throws IOException, ParseException {
        RestAssured.baseURI = "https://gmail.googleapis.com";
        Response res = given().contentType("application/json")
                .header("Authorization", "Bearer " + getUser().get("google_access_token").toString())
                .when().get("/gmail/v1/users/me/messages");

        JsonPath jsonPath = res.jsonPath();
        return jsonPath.getString("messages[0].id");
    }

    public static String readLatestMail() throws IOException, ParseException {
        String messageId = getLatestEmailID();
        RestAssured.baseURI = "https://gmail.googleapis.com";
        Response res = given().contentType("application/json")
                .header("Authorization", "Bearer " + getUser().get("google_access_token").toString())
                .when().get("/gmail/v1/users/me/messages/" + messageId);

        JsonPath jsonPath = res.jsonPath();
        return jsonPath.getString("snippet");
    }

    public static JSONObject getUser() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject userJSONObj = (JSONObject) jsonParser.parse(new FileReader(userJSONFile));
        return userJSONObj;
    }

    public static JSONObject getUserInfo() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject userInfoJSONObj = (JSONObject) jsonParser.parse(new FileReader(userInfoJSONFile));

        return userInfoJSONObj;
    }

    public static JSONObject getAdminInfo() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject userInfoJSONObj = (JSONObject) jsonParser.parse(new FileReader(adminJSONFile));

        return userInfoJSONObj;
    }

    public static JSONObject createFakeUser() throws IOException, ParseException {
        Faker faker = new Faker();
        Random random = new Random();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
//        String email = getUser().get("email").toString();
        String password = PasswordGenerator.generatePassword(6);
        String phoneNumber = "018" + RandomNumberGenerator.generateNumber(8);
        String email = null;
        email = makeEmail(phoneNumber);
        String address = faker.address().fullAddress();
        int genderIndex = random.nextInt(2);  //randomly gives male or female index for radio button

        //making a JSON object
        JSONObject fakerObj = new JSONObject();
        fakerObj.put("first-name", firstName);
        fakerObj.put("last-name", lastName);
        fakerObj.put("email", email);
        fakerObj.put("password", password);
        fakerObj.put("phone-number", phoneNumber);
        fakerObj.put("address", address);
        fakerObj.put("gender-index", genderIndex);

        return fakerObj;
    }

    private static String makeEmail(String phoneNumber) throws IOException, ParseException {
        String originalMail = getUser().get("email").toString();
        originalMail = originalMail.replace("@gmail.com", "");

        return originalMail + "+" + phoneNumber + "@gmail.com";
    }

    public static void saveUser(UserModel userModel) {
        JSONObject userJSONObj = new JSONObject();

        userJSONObj.put("first-name", userModel.getFirstName());
        userJSONObj.put("last-name", userModel.getLastName());
        userJSONObj.put("email", userModel.getEmail());
        userJSONObj.put("password", userModel.getPassword());
        userJSONObj.put("phone-number", userModel.getPhoneNumber());
        userJSONObj.put("address", userModel.getAddress());
        userJSONObj.put("gender", userModel.getGender());

        //write fil
        try {
            FileWriter fileWriter = new FileWriter(userInfoJSONFile);
            fileWriter.write(userJSONObj.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ProductModel> loadProducts() throws IOException {
        String productsCsvFile = "./src/test/resources/products.csv";
        CsvMapper csvMapper = new CsvMapper();

        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        MappingIterator<ProductModel> productIterator = csvMapper
                .readerFor(ProductModel.class)
                .with(schema)
                .readValues(new File(productsCsvFile));

        return productIterator.readAll();
    }
}
