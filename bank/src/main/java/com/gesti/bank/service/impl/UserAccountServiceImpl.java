package com.gesti.bank.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gesti.bank.dto.AgentResponseDTO;
import com.gesti.bank.dto.CreateAgentRequestDTO;
import com.gesti.bank.dto.CreateClientRequestDTO;
import com.gesti.bank.dto.GetAccountResponseDTO;
import com.gesti.bank.dto.LoginRequestDTO;
import com.gesti.bank.dto.LoginResponseDTO;
import com.gesti.bank.dto.UpdateAgentRequestDTO;
import com.gesti.bank.model.Address;
import com.gesti.bank.model.Document;
import com.gesti.bank.model.Role;
import com.gesti.bank.model.UserAccount;
import com.gesti.bank.repository.AddressRepository;
import com.gesti.bank.repository.DocumentRepository;
import com.gesti.bank.repository.RoleRepository;
import com.gesti.bank.repository.UserAccountRepository;
import com.gesti.bank.service.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	private final static String ROLE_ADMIN = "admin";
	private final static String ROLE_CLIENT = "client";
	private final static String ROLE_AGENT = "agent";
	
	private final static String IDENTIFICATION_DOCUMENT = "Identification document";
	private final static String PROOF_HOME = "Home proof document";
	private final static String PROOF_SALARY = "Salary proof document";
	
	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserAccountRepository userAccountRepository;

	@Override
	@Transactional
	public String createClient(CreateClientRequestDTO request, MultipartFile idDocument, MultipartFile proofHome, MultipartFile proofSalary) throws Exception {
		Role clientRole = roleRepository.findByName(ROLE_CLIENT);
		if (clientRole == null) {
			throw new Exception("Role not found");
		}

		Address address = new Address();
		address.setAdditionalInfo(request.getAdditionalInfoAddress());
		address.setCity(request.getCity());
		address.setCountry(request.getCountry());
		address.setHomeNumber(request.getHomeNumber());
		address.setStreet(request.getStreet());
		address.setZip(request.getZip());
		addressRepository.saveAndFlush(address);

		UserAccount userAccount = new UserAccount();
		userAccount.setAddress(address);
		userAccount.setEmail(request.getEmail());
		userAccount.setFirstname(request.getFirstname());
		userAccount.setLastname(request.getLastname());
		userAccount.setMarriageStatus(request.getMarriageStatus());
		userAccount.setNumberOfChildren(request.getNumberOfChildren());
		userAccount.setPass(request.getPass());
		userAccount.setPhone(request.getPhone());
		userAccount.setRole(clientRole);
		userAccount.setStartDate(new Date());
		userAccount.setUsername(request.getUsername());
		boolean isValid = false;
		userAccount.setValid((byte) (isValid ? 1 : 0));
		userAccountRepository.saveAndFlush(userAccount);
		
		final Path rootForUser = Paths.get("uploads/"+userAccount.getIdUserAccount());
		Files.createDirectory(rootForUser);
		 
		Files.copy(idDocument.getInputStream(), rootForUser.resolve(idDocument.getOriginalFilename()));
		Files.copy(proofHome.getInputStream(), rootForUser.resolve(proofHome.getOriginalFilename()));
		Files.copy(proofSalary.getInputStream(), rootForUser.resolve(proofSalary.getOriginalFilename()));
		
		Document idDocumentDoc = new Document(IDENTIFICATION_DOCUMENT, rootForUser.toString()+idDocument.getOriginalFilename(), userAccount);
		Document proofHomeDoc = new Document(PROOF_HOME, rootForUser.toString()+proofHome.getOriginalFilename(), userAccount);
		Document proofSalaryDoc = new Document(PROOF_SALARY, rootForUser.toString()+proofSalary.getOriginalFilename(), userAccount);
		documentRepository.save(idDocumentDoc);
		documentRepository.save(proofHomeDoc);
		documentRepository.save(proofSalaryDoc);
		
		return "Success";
	}

	@Override
	public String createAgent(CreateAgentRequestDTO request) throws Exception {

		Role agentRole = roleRepository.findByName(ROLE_AGENT);
		if (agentRole == null) {
			throw new Exception("Role not found");
		}

		Address address = new Address();
		address.setAdditionalInfo(request.getAdditionalInfoAddress());
		address.setCity(request.getCity());
		address.setCountry(request.getCountry());
		address.setHomeNumber(request.getHomeNumber());
		address.setStreet(request.getStreet());
		address.setZip(request.getZip());
		addressRepository.saveAndFlush(address);

		UserAccount agent = new UserAccount();
		agent.setAddress(address);
		agent.setEmail(request.getEmail());
		agent.setFirstname(request.getFirstname());
		agent.setLastname(request.getLastname());
		agent.setPhone(request.getPhone());
		agent.setPass(request.getPass());
		agent.setRole(agentRole);
		agent.setStartDate(new Date());
		agent.setUsername(request.getUsername());
		boolean isValid = true;
		agent.setValid((byte) (isValid ? 1 : 0));
		userAccountRepository.saveAndFlush(agent);

		return "Success";
	}

//	@Override
//	public List<AgentResponseDTO> getAllAgents() throws Exception {
//		Role agentRole = roleRepository.findByName(ROLE_AGENT);
//		if (agentRole == null) {
//			throw new Exception("Role not found");
//		}
//		List<UserAccount> agents = userAccountRepository.findAllByRole(agentRole);
//		if (agents == null || agents.size() == 0) {
//			return null;
//		}
//		List<AgentResponseDTO> response = new ArrayList<AgentResponseDTO>();
//		for (UserAccount agent : agents) {
//			if (agent.getEndDate() == null) {
//				AgentResponseDTO tempObj = new AgentResponseDTO(agent.getEmail(), agent.getFirstname(),
//						agent.getLastname(), agent.getPhone(), agent.getUsername(),
//						agent.getAddress().getAdditionalInfo(), agent.getAddress().getCity(),
//						agent.getAddress().getCountry(), agent.getAddress().getHomeNumber(),
//						agent.getAddress().getStreet(), agent.getAddress().getZip(), agent.getIdUserAccount());
//				response.add(tempObj);
//			}
//		}
//		return response;
//	}
	
	@Override
	public List<AgentResponseDTO> getAllAgents(String filter) throws Exception {
		Role agentRole = roleRepository.findByName(ROLE_AGENT);
		if (agentRole == null) {
			throw new Exception("Role not found");
		}
		int filterId;
		try {
			filterId = Integer.parseInt(filter);
		}catch (Exception e){
			filterId = -1; //like a default value
		}
		List<UserAccount> agents = userAccountRepository.filterPerIdOrLastNameAndRole(agentRole,filterId, "%"+filter+"%");
		if (agents == null || agents.size() == 0) {
			return null;
		}
		List<AgentResponseDTO> response = new ArrayList<AgentResponseDTO>();
		for (UserAccount agent : agents) {
			if (agent.getEndDate() == null) {
				AgentResponseDTO tempObj = new AgentResponseDTO(agent.getEmail(), agent.getFirstname(),
						agent.getLastname(), agent.getPhone(), agent.getUsername(),
						agent.getAddress().getAdditionalInfo(), agent.getAddress().getCity(),
						agent.getAddress().getCountry(), agent.getAddress().getHomeNumber(),
						agent.getAddress().getStreet(), agent.getAddress().getZip(), agent.getIdUserAccount());
				response.add(tempObj);
			}
		}
		return response;
	}

	@Override
	public LoginResponseDTO login(LoginRequestDTO request) throws Exception {
		UserAccount loggedInUser = userAccountRepository.findByUsernameAndPass(request.getUsername(),
				request.getPassword());
		if (loggedInUser == null) {
			throw new Exception("User with provided credentials does not exist!");
		}
		if (loggedInUser.getValid() == 0) {
			throw new Exception("User with provided credentials is not valid!");
		}
		LoginResponseDTO response = new LoginResponseDTO(loggedInUser.getIdUserAccount(),
				loggedInUser.getRole().getName());
		return response;
	}

	@Override
	public String updateAgent(UpdateAgentRequestDTO request, int id) throws Exception {
		Optional<UserAccount> agentOpt = userAccountRepository.findById(id);
		if (!agentOpt.isPresent()) {
			throw new Exception("User with provided ID does not exist!");
		}
		UserAccount agent = agentOpt.get();
		if (!agent.getRole().getName().equals(ROLE_AGENT)) {
			throw new Exception("User with provided ID is not an agent!");
		}
		Address address = agent.getAddress();
		if (address == null) {
			address = new Address();
		}
		address.setCity(request.getCity());
		address.setAdditionalInfo(request.getAdditionalInfoAddress());
		address.setCountry(request.getCountry());
		address.setHomeNumber(request.getHomeNumber());
		address.setStreet(request.getStreet());
		address.setZip(request.getZip());

		addressRepository.saveAndFlush(address);

		agent.setAddress(address);

		agent.setEmail(request.getEmail());
		agent.setFirstname(request.getFirstname());
		agent.setLastname(request.getLastname());
		agent.setPass(request.getPass());
		agent.setPhone(request.getPhone());
		agent.setUsername(request.getUsername());

		userAccountRepository.saveAndFlush(agent);
		return "Success";

	}

	@Override
	public String deleteAgent(int id) throws Exception {
		Optional<UserAccount> agentOpt = userAccountRepository.findById(id);
		if (!agentOpt.isPresent()) {
			throw new Exception("User with provided ID does not exist!");
		}
		UserAccount agent = agentOpt.get();
		if (!agent.getRole().getName().equals(ROLE_AGENT)) {
			throw new Exception("User with provided ID is not an agent!");
		}
		boolean canDelete = true;
		if (agent.getRequestsFrom().size() > 0) {
			System.out.println("I am in first block");
			canDelete = false;
		}
		if (agent.getRequestsTo().size() > 0) {
			System.out.println("I am in second block");
			canDelete = false;
		}
		if (agent.getBankAccounts().size() > 0) {
			System.out.println("I am in third block");
			canDelete = false;
		}
		if (agent.getNotifications().size() > 0) {
			System.out.println("I am in fourth block");
			canDelete = false;
		}
		if (agent.getDocuments().size() > 0) {
			System.out.println("I am in fifth block");
			canDelete = false;
		}
		if (canDelete) {
			userAccountRepository.delete(agent);
			userAccountRepository.flush();
		} else {
			agent.setEndDate(new Date());
			userAccountRepository.saveAndFlush(agent);
		}
		int idAgentsAddress = agent.getAddress().getIdAddress();
		Optional<Address> agentsAddressOpt = addressRepository.findById(idAgentsAddress);
		if (agentsAddressOpt.isPresent()) {
			Address agentsAddress = agentsAddressOpt.get();
			if (agentsAddress.getUserAccounts() == null || agentsAddress.getUserAccounts().isEmpty()) {
				addressRepository.delete(agentsAddress);
				addressRepository.flush();
			}
		}
		return "Success";
	}

	@Override
	public GetAccountResponseDTO getAccount(int id) throws Exception {
		Optional<UserAccount> userAccountOpt = userAccountRepository.findById(id);
		if (!userAccountOpt.isPresent()) {
			throw new Exception("User with provided ID does not exist!");
		}
		UserAccount user = userAccountOpt.get();
		GetAccountResponseDTO response = new GetAccountResponseDTO(user.getEmail(), user.getFirstname(),
				user.getLastname(), user.getPass(), user.getPhone(), user.getUsername(),
				user.getAddress().getAdditionalInfo(), user.getAddress().getCity(), user.getAddress().getCountry(),
				user.getAddress().getHomeNumber(), user.getAddress().getStreet(), user.getAddress().getZip(), id);
		return response;
	}

}
