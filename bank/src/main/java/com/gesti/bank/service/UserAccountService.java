package com.gesti.bank.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gesti.bank.dto.AgentResponseDTO;
import com.gesti.bank.dto.AssignClientRequestDTO;
import com.gesti.bank.dto.ClientResponseForAdminDTO;
import com.gesti.bank.dto.CreateAgentRequestDTO;
import com.gesti.bank.dto.CreateClientRequestDTO;
import com.gesti.bank.dto.GetAccountResponseDTO;
import com.gesti.bank.dto.GetUnresolvedRequestsForAgentResponseDTO;
import com.gesti.bank.dto.LoginRequestDTO;
import com.gesti.bank.dto.LoginResponseDTO;
import com.gesti.bank.dto.UpdateAgentRequestDTO;

public interface UserAccountService {

	String createClient(CreateClientRequestDTO request, MultipartFile idDocument, MultipartFile proofHome, MultipartFile proofSalary) throws Exception ;

	String createAgent(CreateAgentRequestDTO request) throws Exception;

	//List<AgentResponseDTO> getAllAgents() throws Exception;
	
	List<AgentResponseDTO> getAllAgents(String filter) throws Exception;

	LoginResponseDTO login(LoginRequestDTO request) throws Exception;

	String updateAgent(UpdateAgentRequestDTO request, int id) throws Exception;

	String deleteAgent(int id) throws Exception;

	GetAccountResponseDTO getAccount(int id) throws Exception;

	List<ClientResponseForAdminDTO> getInvalidClients() throws Exception;

	String assignClient(int agentId, AssignClientRequestDTO request) throws Exception;

	List<GetUnresolvedRequestsForAgentResponseDTO> getUnresolvedRequests(int agentId) throws Exception;

}
