package br.com.room_management.controller;

import br.com.room_management.dto.usuario.LoginUsuarioDTO;
import br.com.room_management.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    private JacksonTester<LoginUsuarioDTO> loginUsuarioJsonDTO;

    @Test
    @DisplayName("Deveria retornar status 200 em caso de login bem sucedido")
    public void loginCenario01() throws Exception {

        // ARRANGE
        LoginUsuarioDTO dto = new LoginUsuarioDTO("Wellington", "well@gmail.com", "1223");

        // ACTION
        var response = mockMvc.perform(
                post("/auth/login")
                        .content(loginUsuarioJsonDTO.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Deveria retornar status 400 em caso de login bem sucedido")
    public void loginCenario02() throws Exception {

        // ARRANGE
        LoginUsuarioDTO dto = new LoginUsuarioDTO("", "well@gmail.com", "");

        // ACTION
        var response = mockMvc.perform(
                post("/auth/login")
                        .content(loginUsuarioJsonDTO.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // ASSERT
        assertEquals(400, response.getStatus());
    }
}