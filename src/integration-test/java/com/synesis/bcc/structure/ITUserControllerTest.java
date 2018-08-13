package com.synesis.bcc.structure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synesis.bcc.structure.database.entities.UserType;
import com.synesis.bcc.structure.database.repositories.UserDetailRepository;
import com.synesis.bcc.structure.database.repositories.UserRepository;
import com.synesis.bcc.structure.database.repositories.UserTypeRepository;
import com.synesis.bcc.structure.helpers.dataclass.UserSummary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ITUserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserTypeRepository userTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

	@Test
	public void shouldReturnAvailableUsers() throws Exception {
		mockMvc
				.perform(get("/user"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is("3269c6ad-0b41-4b4c-94d7-26facf399729")))
				.andExpect(jsonPath("$[0].firstname", is("Ishmum")))
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	public void shouldReturnUserWithId() throws Exception {
		mockMvc
				.perform(get("/user/id/3269c6ad-0b41-4b4c-94d7-26facf399729"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is("3269c6ad-0b41-4b4c-94d7-26facf399729")))
				.andExpect(jsonPath("$.firstname", is("Ishmum")));
	}

	@Test
	public void shouldReturnErrorResponseIfNonExistentIdProvided() throws Exception {
		mockMvc
				.perform(get("/user/id/3269c6ad-0b41-4b4c-94d7-26facf399720"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code", is("STR2000")))
				.andExpect(jsonPath("$.message", containsString("No User found")));
	}

	@Test
	public void shouldReturnErrorResponseIfInvalidIdProvided() throws Exception {
		mockMvc
				.perform(get("/user/id/invalid-id"))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code", is("STR1000")))
				.andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors", contains("Invalid Request Parameters")))
				.andExpect(jsonPath("$.message", is("Invalid Request Parameters")));
	}

	@Test
	public void shouldReturnUserWithFirstname() throws Exception {
		mockMvc
				.perform(get("/user/find").param("firstname", "Ishmum"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is("3269c6ad-0b41-4b4c-94d7-26facf399729")))
				.andExpect(jsonPath("$[0].firstname", is("Ishmum")))
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	public void shouldReturnEmptyArrayIfNonExistentNameProvided() throws Exception {
		mockMvc
				.perform(get("/user/find").param("firstname", "non-existent"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void shouldReturnAvailableUserDetails() throws Exception {
		mockMvc
				.perform(get("/user/detail"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].type.name", contains("admin")))
				.andExpect(jsonPath("$[0].state.name", is("active")))
				.andExpect(jsonPath("$[0].user.address", containsString("Dhaka - 1205")))
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	public void shouldReturnAvailableUserTypes() throws Exception {
		mockMvc
				.perform(get("/user/type"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].name", hasItems("admin", "employee", "hr")))
				.andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
	@Transactional
	public void shouldAddNewUserType() throws Exception {
		UserType type = new UserType();
		type.setName("hello");

		mockMvc
				.perform(
						post("/user/type")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(type))
				)
				.andDo(print())
				.andExpect(status().isOk());

		assertEquals(6, userTypeRepository.count());
	}

	@Test
	public void shouldReturnErrorResponseForInvalidUserTypeData() throws Exception {
		UserType type = new UserType();

		mockMvc
				.perform(
						post("/user/type")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(type))
				)
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code", is("STR1000")))
				.andExpect(jsonPath("$.message", containsString("Validation failed")))
				.andExpect(jsonPath("$.errors", hasItem("name must not be null")))
				.andExpect(jsonPath("$.errors", hasSize(1)));

        assertEquals(5, userTypeRepository.count());
	}

    @Test
    @Transactional
    public void shouldAddNewUser() throws Exception {
        UserSummary userSummary = new UserSummary();
        userSummary.setFirstname("randomfirstname");
        userSummary.setLastname("randomlastname");
        userSummary.setEmail("random@email.com");
        userSummary.setPassword("randompassword");
        userSummary.setType(1);
        userSummary.setState(1);
        userSummary.setDesignation(1);

        mockMvc
                .perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userSummary))
                )
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(2, userRepository.count());
        assertEquals(2, userDetailRepository.count());
    }

    @Test
    public void shouldReturnErrorResponseForInvalidUserData() throws Exception {
        UserSummary userSummary = new UserSummary();
        userSummary.setFirstname("randomfirstname");
        userSummary.setEmail("");
        userSummary.setPassword("randompassword");
        userSummary.setState(50);
        userSummary.setDesignation(1);

        mockMvc
                .perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userSummary))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("STR1000")))
                .andExpect(jsonPath("$.message", containsString("Validation failed")))
                .andExpect(jsonPath("$.errors", containsInAnyOrder(
						"lastname must not be blank",
						"state can not be validated",
						"type can not be validated",
						"user data can not be validated",
						"email must not be blank"
                )));

        assertEquals(1, userRepository.count());
        assertEquals(1, userDetailRepository.count());
    }
}
