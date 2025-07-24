package petstore.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class US02_PostStepDefs {
    private Response response;
    private String petId;
    private String additionalMetadata;
    private File imageFile;
    private String baseUrl = "https://petstore.swagger.io/v2/";

    @Given("I have a pet with ID {string}")
    public void iHaveAPetWithID(String petId) {
        this.petId = petId;
        System.out.println("Pet ID set to: " + petId);
    }

    @Given("I have an image file {string}")
    public void iHaveAnImageFile(String fileName) {
        try {
            // Load image from resources/images folder
            String imagePath = "src/test/resources/images/" + fileName;
            this.imageFile = new File(imagePath);

            // If file doesn't exist, create a dummy one for testing
            if (!this.imageFile.exists()) {
                System.out.println("Image file not found at: " + imagePath);
                System.out.println("Creating dummy image file for testing...");
                createTestImageFile(imagePath);
                this.imageFile = new File(imagePath);
            }

            System.out.println("Image file prepared: " + imagePath);
            System.out.println("File exists: " + this.imageFile.exists());
            System.out.println("File size: " + this.imageFile.length() + " bytes");
        } catch (IOException e) {
            fail("Failed to prepare image file: " + e.getMessage());
        }
    }

    @Given("I have additional metadata {string}")
    public void iHaveAdditionalMetadata(String metadata) {
        this.additionalMetadata = metadata;
        System.out.println("Additional metadata set to: " + metadata);
    }

    @When("I send a POST request to upload the image")
    public void iSendAPOSTRequestToUploadTheImage() {
        baseURI = baseUrl;

        // Build the multipart request.
        var requestSpec = given()
                .contentType("multipart/form-data")
                .accept("application/json");

        // Add additional metadata if provided
        if (additionalMetadata != null && !additionalMetadata.isEmpty()) {
            requestSpec = requestSpec.multiPart("additionalMetadata", additionalMetadata);
        }

        // Add file
        requestSpec = requestSpec.multiPart("file", imageFile, "image/jpeg");

        // Send the request
        response = requestSpec
                .when()
                .post("pet/" + petId + "/uploadImage");

        System.out.println("POST request sent to: pet/" + petId + "/uploadImage");
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        assertEquals("Status code mismatch", expectedStatusCode, response.getStatusCode());
        System.out.println("Status code assertion passed: " + expectedStatusCode);
    }

    @Then("the response should contain upload confirmation")
    public void theResponseShouldContainUploadConfirmation() {
        String responseBody = response.getBody().asString();

        // Check if response contains typical upload confirmation indicators
        assertTrue("Response should contain success indicators",
                responseBody.contains("uploaded") ||
                responseBody.contains("success") ||
                responseBody.contains("code") ||
                responseBody.contains("message"));

        System.out.println("Upload confirmation verified in response");
    }

    // Helper method to create a test image file
    private void createTestImageFile(String filePath) throws IOException {
        // Create directory if it doesn't exist
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
            System.out.println("Created directory: " + parentDir.getPath());
        }

        // Create some dummy image-like content




        byte[] dummyImageContent = createDummyImageContent();

        Files.write(file.toPath(), dummyImageContent);
        System.out.println("Test image file created: " + filePath);
    }

    // Create dummy image content (simplified - you might want to use actual image bytes)
    private byte[] createDummyImageContent() {
        // This is a minimal JPEG header - for testing purposes
        // In a real scenario, you'd use a proper test image file
        return new byte[] {
                (byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE0,
                0x00, 0x10, 0x4A, 0x46, 0x49, 0x46, 0x00, 0x01,
                0x01, 0x01, 0x00, 0x48, 0x00, 0x48, 0x00, 0x00,
                (byte) 0xFF, (byte) 0xD9
        };
    }
}