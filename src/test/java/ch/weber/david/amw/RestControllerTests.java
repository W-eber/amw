package ch.weber.david.amw;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import ch.weber.david.amw.profile.Profile;
import ch.weber.david.amw.profile.ProfileRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestControllerTests {
    @Autowired
    private MockMvc api;

    @Autowired
    private ProfileRepository profileRepository;

    @BeforeAll
    void setup() {
        LocalDate joinDate1 = LocalDate.parse("2024-04-18");
        this.profileRepository.save(new Profile("username", "biography", joinDate1));
        
        LocalDate joinDate2 = LocalDate.parse("2024-04-18");
        this.profileRepository.save(new Profile("1234", "", joinDate2));
    }

    @Test
    @Order(1)
    void testGetProfiles() throws Exception {

        String accessToken = obtainAccessToken();

        api.perform(get("/api/profile").header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("username")));
    }


    @Test
    @Order(1)
    void testSaveProfile() throws Exception {
    Profile profile = new Profile();
    profile.setUsername("username");
    profile.setBio("Test REST");
    LocalDate joinDate = LocalDate.parse("2024-04-18");
    profile.setJoinDate(joinDate);

    String accessToken = obtainAccessToken();

    //wegen LocalDate
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    String body = objectMapper.writeValueAsString(profile);

    api.perform(post("/api/profile")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body)
                    .header("Authorization", "Bearer " + accessToken)
                    .with(csrf()))
            .andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("Test REST")));
}

    @Test
    @Order(2)
    void testUpdateProfile() throws Exception {
        String accessToken = obtainAccessToken();

        Profile updatedProfile = new Profile();
        updatedProfile.setUsername("UpdatedUsername");
        updatedProfile.setBio("UpdatedBio");
        LocalDate updatedJoinDate = LocalDate.parse("2024-04-19"); 
        updatedProfile.setJoinDate(updatedJoinDate);

        Long profileId = 1L;

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String body = objectMapper.writeValueAsString(updatedProfile);

        api.perform(put("/api/profile/{id}", profileId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("UpdatedBio")));

        
        Profile retrievedProfile = profileRepository.findById(profileId).orElse(null);
        assertNotNull(retrievedProfile);
        assertEquals("UpdatedUsername", retrievedProfile.getUsername());
        assertEquals("UpdatedBio", retrievedProfile.getBio());
        assertEquals(updatedJoinDate, retrievedProfile.getJoinDate());
    }

    @Test
    @Order(3) 
    void testDeleteProfile() throws Exception {
        String accessToken = obtainAccessToken();
        Long profileId = 1L;

        api.perform(delete("/api/profile/{id}", profileId)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("deleted")));

        assertFalse(profileRepository.existsById(profileId));
    }

    private String obtainAccessToken() {

        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "client_id=amw&" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                "username=admin&" +
                "password=admin";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> resp = rest.postForEntity("http://localhost:8080/realms/ILV/protocol/openid-connect/token", entity, String.class);

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
    }
}