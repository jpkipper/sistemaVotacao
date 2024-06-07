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
import br.com.db.sistema.votacao.v1.model.entity.Assembly;
import br.com.db.sistema.votacao.v1.models.entity.AssemblyStub;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Assembly controller")
public class AssemblyControllerTest
{
@Autowired
	private MockMvc mockMvc;

	private String PATH = "/v1/assembleias";

	@Test
	@DisplayName("[POST] Deve retornar Ok ao criar uma Assembly")
	public void Should_ReturnCreated_CreateAssembly() throws Exception 
	{
		final Assembly mockAssembly = AssemblyStub.createAssemblyWithoutId();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAssembly )))
				.andExpect( status().isCreated() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.agendas").value( mockAssembly.getAgendas().toString() ))
				.andExpect( jsonPath("$.start").value( mockAssembly.getStart().toString() ))
				.andExpect( jsonPath("$.end").value( mockAssembly.getEnd().toString() ));
	}

	@Test
	@DisplayName("[POST] Deve retornar BadRequest ao criar uma Assembly com datas inválidas")
	public void Should_ReturnBadRequest_CreateAssembly() throws Exception
	{
		final Assembly mockAssembly = AssemblyStub.createAssemblyWithWrongDates();

		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAssembly )))
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath("$.code").value( 400 ))
				.andExpect( jsonPath("$.status").value( "Bad Request" ));
	}

	@Test
	@SqlGroup({
		@Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertAssembly ),
		@Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = Querys.resetDB )
	})
	@DisplayName("[GET] Deve retornar Ok ao buscar uma Assembly pelo ID informado")
	public void Should_ReturnOk_GetAssemblyById() throws Exception
	{
		final Assembly mockAssembly = AssemblyStub.createAssemblyWithoutId();
		
		mockMvc.perform( get( PATH + "/{assembleiaId}", 1 ))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.description").value( mockAssembly.getAgendas().toString() ))
				.andExpect( jsonPath("$.startDate").value( mockAssembly.getStart().toString() ))
				.andExpect( jsonPath("$.endDate").value( mockAssembly.getEnd().toString() ));
	}

	@Test
	@DisplayName("[GET] Deve retornar NotFound ao buscar uma Assembly pelo ID inválido")
	public void Should_ReturnNotFoundException_GetAssemblyById() throws Exception
	{
		long assembleiaId = -1;

		mockMvc.perform( get( PATH + "/{assembleiaId}", assembleiaId ))
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath("$.code").value( 404 ))
				.andExpect( jsonPath("$.status").value( "Not Found" ))
				.andExpect( jsonPath("$.message").value( "Assembly não localizada para o id: #" + assembleiaId ));
	}
}