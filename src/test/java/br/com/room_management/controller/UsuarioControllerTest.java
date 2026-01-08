package br.com.room_management.controller;

import br.com.room_management.dto.usuario.AtualizarUsuarioDTO;
import br.com.room_management.dto.usuario.CadastrarUsuarioDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    private JacksonTester<CadastrarUsuarioDTO> cadastrarJsonDTO;

    @Autowired
    private JacksonTester<AtualizarUsuarioDTO> atualizarJsonDTO;

    @Test
    @DisplayName("Deveria retornar 200 caso usuário seja salvo corretamente")
    public void cadastrarCenario01() throws Exception {

        // ARRANGE
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO("Wellington", "well@gmail.com", "1223");

        // ACTION
        var response = mockMvc.perform(
                post("/user")
                        .content(cadastrarJsonDTO.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // ASSERT
        assertEquals(200, response.getStatus());
        assertEquals("Usuário criado com sucesso", response.getContentAsString());
    }

    @Test
    @DisplayName("Deveria retornar 400 caso usuário não seja salvo corretamente")
    public void cadastrarCenario02() throws Exception {

        // ARRANGE
        CadastrarUsuarioDTO dto = new CadastrarUsuarioDTO("", "well@gmail.com", "");

        // ACTION
        var response = mockMvc.perform(
                post("/user")
                        .content(cadastrarJsonDTO.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // ASSERT
        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Deveria retornar 200 caso usuário atualize corretamente")
    public void atualizarCenario01() throws Exception {

        // ARRANGE
        AtualizarUsuarioDTO dto = new AtualizarUsuarioDTO("Wellington", "well@gmail.com");
        int id = 1;

        // ACTION
        var response = mockMvc.perform(
                put("/user/{id}", id)
                        .content(atualizarJsonDTO.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // ASSERT
        assertEquals(200, response.getStatus());
        assertEquals("Usuário atualizado com sucesso", response.getContentAsString());
    }

    @Test
    @DisplayName("Deveria retornar 400 caso usuário não atualize corretamente")
    public void atualizarCenario02() throws Exception {

        // ARRANGE
        AtualizarUsuarioDTO dto = new AtualizarUsuarioDTO("", "well@gmail.com");
        int id = 1;

        // ACTION
        var response = mockMvc.perform(
                put("/user/{id}", id)
                        .content(atualizarJsonDTO.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // ASSERT
        assertEquals(400, response.getStatus());
    }
}