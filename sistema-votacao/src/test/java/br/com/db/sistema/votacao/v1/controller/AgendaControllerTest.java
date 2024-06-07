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
import br.com.db.sistema.votacao.v1.model.dto.AgendaDTO;
import br.com.db.sistema.votacao.v1.model.enums.AgendaStatusEnum;
import br.com.db.sistema.votacao.v1.models.entity.AgendaStub;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Agenda controller")
public class AgendaControllerTest
{
    @Autowired
	private MockMvc mockMvc;

	private String PATH = "/v1/agenda";

    @Test
    @SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertAssembly ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = Querys.resetDB ),
    })
    public void Should_ReturnOk_CreateAgenda() throws Exception
    {
        final AgendaDTO mockAgendaDTO = AgendaStub.createAgendaDTOWithoutId();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAgendaDTO )))
				.andExpect( status().isCreated() )
				.andExpect( jsonPath("$.id").value( 1 ))
				.andExpect( jsonPath("$.description").value( mockAgendaDTO.getDescription() ))
				.andExpect( jsonPath("$.startDate").value( mockAgendaDTO.getStart().toString() ))
				.andExpect( jsonPath("$.endDate").value( mockAgendaDTO.getEnd().toString() ))
				.andExpect( jsonPath("$.status").value( AgendaStatusEnum.WAITING_FOR_RESULT.getValue() ));
    }

	@Test
	@Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.resetDB )
	public void Should_ReturnNotFound_CreateAgenda() throws Exception
	{
		final AgendaDTO mockAgendaDTO = AgendaStub.createAgendaDTOWithoutId();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAgendaDTO )))
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath("$.code").value( 404 ))
				.andExpect( jsonPath("$.status").value( "Not Found" ));
	}
	
	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertAssembly ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = Querys.resetDB ),
    })
	public void Should_ReturnBadRequest_CreateAgendaWithWrongDates() throws Exception
	{
		final AgendaDTO mockAgendaDTO = AgendaStub.createAgendaDTOWithWrongDates();
		
		mockMvc.perform( post( PATH )
						.contentType( MediaType.APPLICATION_JSON )
						.content( json( mockAgendaDTO )))
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath("$.code").value( 400 ))
				.andExpect( jsonPath("$.status").value( "Bad Request" ))
				.andExpect( jsonPath("$.message").value( "Data inicial e final da Agenda devem estar dentro do escopo de datas da Assembleia" ));
	}

	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertAgenda ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertAssociate ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = Querys.resetDB ),
    })
	public void Should_ReturnOk_GetAgendaResult_Approved() throws Exception
	{
		long agendaId = 1;

		mockMvc.perform( get( PATH + "/{agendaId}", agendaId ))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.agendaId").value( agendaId ))
				.andExpect( jsonPath("$.description").value( "" ))
				.andExpect( jsonPath("$.approved").value( 1 ))
				.andExpect( jsonPath("$.rejected").value( 0 ))
				.andExpect( jsonPath("$.abstention").value( 0 ))
				.andExpect( jsonPath("$.protest").value( 0 ))
				.andExpect( jsonPath("$.total").value( 1 ))
				.andExpect( jsonPath("$.status").value( AgendaStatusEnum.APPROVED.getValue() ));
	}
	
	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertAgenda),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertAssociate ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = Querys.resetDB ),
    })
	public void Should_ReturnOk_GetAgendaResult_Rejected() throws Exception
	{
		long agendaId = 1;

		mockMvc.perform( get( PATH + "/{agendaId}", agendaId ))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.agendaId").value( agendaId ))
				.andExpect( jsonPath("$.description").value( "" ))
				.andExpect( jsonPath("$.approved").value( 0 ))
				.andExpect( jsonPath("$.rejected").value( 1 ))
				.andExpect( jsonPath("$.abstention").value( 0 ))
				.andExpect( jsonPath("$.protest").value( 0 ))
				.andExpect( jsonPath("$.total").value( 1 ))
				.andExpect( jsonPath("$.status").value( AgendaStatusEnum.REJECTED.getValue() ));
	}
	
	@Test
	@SqlGroup({
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertAgenda ),
		@Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertAssociate ),
        @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = Querys.insertVote ),
        @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = Querys.resetDB ),
    })
	public void Should_ReturnOk_GetAgendaResult_Tied() throws Exception
	{
		long agendaId = 1;

		mockMvc.perform( get( PATH + "/{agendaId}", agendaId ))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.agendaId").value( agendaId ))
				.andExpect( jsonPath("$.description").value( "" ))
				.andExpect( jsonPath("$.approved").value( 1 ))
				.andExpect( jsonPath("$.rejected").value( 1 ))
				.andExpect( jsonPath("$.abstention").value( 0 ))
				.andExpect( jsonPath("$.protest").value( 0 ))
				.andExpect( jsonPath("$.total").value( 2 ))
				.andExpect( jsonPath("$.status").value( AgendaStatusEnum.TIED.getValue() ));
	}

	@Test
	public void Should_ReturnNotFound_GetAgendaResult() throws Exception
	{
		long agendaId = -1;

		mockMvc.perform( get( PATH + "/{agendaId}", agendaId ))
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath("$.code").value( 404 ))
				.andExpect( jsonPath("$.status").value( "Not Found" ))
				.andExpect( jsonPath("$.message").value( "Agenda não localizada para o id: #" + agendaId ));
	}
}