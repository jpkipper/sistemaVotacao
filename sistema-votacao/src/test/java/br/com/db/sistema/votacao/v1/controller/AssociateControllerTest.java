package br.com.db.sistema.votacao.v1.controller;

import static br.com.db.sistema.votacao.v1.helper.Serializer.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import br.com.db.sistema.votacao.v1.helper.Querys;
import br.com.db.sistema.votacao.v1.model.entity.Associate;
import br.com.db.sistema.votacao.v1.models.entity.AssociateStub;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Associate controller")
public class AssociateControllerTest 
{

    @Autowired
	private MockMvc mockMvc;

	private String PATH = "/v1/associados";

    @Test
	@DisplayName("[POST] Deve retornar Ok ao criar um Associate")
    public void Should_ReturnOk_CreateAssociate() throws Exception
    {
        final Associate mockAssociate = AssociateStub.createAssociateWithoutId();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAssociate )))
				.andExpect( status().isCreated() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.name").value( mockAssociate.getName() ))
				.andExpect( jsonPath("$.document").value( mockAssociate.getCpf() ));
    }

    @Test
    	@SqlGroup({
		@Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertAssociate ),
		@Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = Querys.resetDB )
	})
	@DisplayName("[POST] Deve retornar UnprocessableEntity ao criar um Associate com documento que já existe")
    public void Should_ReturnUnprocessableEntity_CreateAssociate() throws Exception
    {
        final Associate mockAssociate = AssociateStub.createAssociateWithoutId();

        mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAssociate )))
				.andExpect( status().isUnprocessableEntity() )
				.andExpect( jsonPath("$.code").value( 422 ))
				.andExpect( jsonPath("$.status").value( "Unprocessable Entity" ))
				.andExpect( jsonPath("$.message").value( "Já existe um associado cadastrado com este documento: " + mockAssociate.getCpf() ));
    }

    @Test
    @SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertAssociate ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = Querys.resetDB )
	})
    @DisplayName("[GET] Deve retornar Ok ao buscar um associado")
    public void Should_ReturnOk_GetAssociate() throws Exception
    {
        final Associate mockAssociate = AssociateStub.createAssociateWithId();

		mockMvc.perform( get( PATH + "/{document}", mockAssociate.getCpf() ))
                
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.name").value( mockAssociate.getName() ))
				.andExpect( jsonPath("$.document").value( mockAssociate.getCpf() ))
				.andExpect( jsonPath("$.status").value( mockAssociate.getAssociateStatusEnum().getValue() ));
    }

    @Test
    public void Should_ReturnNotFound_GetAssociate() throws Exception
    {
        String cpf = "01964652065";

        mockMvc.perform( get( PATH + "/{cpf}", cpf ))
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath("$.code").value( 404 ))
				.andExpect( jsonPath("$.status").value( "Not Found" ))
				.andExpect( jsonPath("$.message").value( "Associate não localizado para o documento: " + cpf ));
    }
}
