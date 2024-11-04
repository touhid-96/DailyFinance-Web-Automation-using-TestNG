package Util;

import Config.UserModel;
import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Utils {

    static String adminJSONFile = "./src/test/resources/admin.json";
    static String userJSONFile = "./src/test/resources/userCredential.json";
    static String userInfoJSONFile = "./src/test/resources/userInfo.json";

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

    public static JSONObject createFakeUser() throws IOException, ParseException {
        Faker faker = new Faker();
        Random random = new Random();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
//        String email = getUser().get("email").toString();
        String password = PasswordGenerator.generatePassword(6);
        String phoneNumber = "018" + RandomNumberGenerator.generateNumber(8);
        String email = makeEmail(phoneNumber);
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

    public static void saveUser(UserModel userModel) throws IOException {
        JSONObject userJSONObj = new JSONObject();

        userJSONObj.put("first-name", userModel.getFirstName());
        userJSONObj.put("last-name", userModel.getLastName());
        userJSONObj.put("email", userModel.getEmail());
        userJSONObj.put("password", userModel.getPassword());
        userJSONObj.put("phone-number", userModel.getPhoneNumber());
        userJSONObj.put("address", userModel.getAddress());
        userJSONObj.put("gender", userModel.getGender());

        //write file
        FileWriter fileWriter = new FileWriter(userInfoJSONFile);
        fileWriter.write(userJSONObj.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }

    public static void saveUserEmail(UserModel userModel) throws IOException {
        JSONObject userJSONObj = new JSONObject();

        userJSONObj.replace("email", userModel.getEmail());

        //write file
        FileWriter fileWriter = new FileWriter(userInfoJSONFile);
        fileWriter.write(userJSONObj.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }
}
