package POJO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class MappingThePojo {

    public void mapThePojo(Response response) throws Exception {
     // Convert the SamplePojoClass to a JSON string
     ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody().asString());
        SamplePojoClass pojo = objectMapper.treeToValue(jsonNode, SamplePojoClass.class);
        System.out.println("ID: " + jsonNode.get("id").asText());
        System.out.println("Title: " + pojo.getTitle());
        System.out.println("Body: " + pojo.getBody());
        System.out.println("UserID: " + pojo.getUserId());


    }
}
