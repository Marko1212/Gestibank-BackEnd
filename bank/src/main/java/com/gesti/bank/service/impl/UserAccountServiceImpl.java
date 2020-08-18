package com.gesti.bank.service.impl;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gesti.bank.dto.AgentResponseDTO;
import com.gesti.bank.dto.AssignClientRequestDTO;
import com.gesti.bank.dto.BankAccountResponseDTO;
import com.gesti.bank.dto.ClientRequestForAdminDTO;
import com.gesti.bank.dto.ClientResponseForAdminDTO;
import com.gesti.bank.dto.CreateAgentRequestDTO;
import com.gesti.bank.dto.CreateClientRequestDTO;
import com.gesti.bank.dto.FileInfoResponseDTO;
import com.gesti.bank.dto.ForgotPasswordRequestDTO;
import com.gesti.bank.dto.GetAccountResponseDTO;
import com.gesti.bank.dto.GetUnresolvedRequestsForAgentResponseDTO;
import com.gesti.bank.dto.LoginRequestDTO;
import com.gesti.bank.dto.LoginResponseDTO;
import com.gesti.bank.dto.PasswordChangeRequestDTO;
import com.gesti.bank.dto.ResetPasswordRequestDTO;
import com.gesti.bank.dto.UpdateAgentRequestDTO;
import com.gesti.bank.dto.VerifiedClientRequestDTO;
import com.gesti.bank.dto.VerifiedClientsRequestDTO;
import com.gesti.bank.model.Address;
import com.gesti.bank.model.BankAccount;
import com.gesti.bank.model.Document;
import com.gesti.bank.model.Request;
import com.gesti.bank.model.Role;
import com.gesti.bank.model.UserAccount;
import com.gesti.bank.repository.AddressRepository;
import com.gesti.bank.repository.DocumentRepository;
import com.gesti.bank.repository.RequestRepository;
import com.gesti.bank.repository.RoleRepository;
import com.gesti.bank.repository.UserAccountRepository;
import com.gesti.bank.service.BankAccountService;
import com.gesti.bank.service.EmailService;
import com.gesti.bank.service.FilesStorageService;
import com.gesti.bank.service.UserAccountService;
import com.gesti.bank.util.RequestTitlesUtil;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	private final static String ROLE_ADMIN = "admin";
	private final static String ROLE_CLIENT = "client";
	private final static String ROLE_AGENT = "agent";

	private final static String IDENTIFICATION_DOCUMENT = "Identification document";
	private final static String PROOF_HOME = "Home proof document";
	private final static String PROOF_SALARY = "Salary proof document";

	private final static String CREATE_ACCOUNT_TITLE = "CREATE_ACCOUNT";

	private final static String GET_FILES_METHOD_PATH = "/files/getFiles/";

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	BankAccountService bankAccountService;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	FilesStorageService fileStorageService;

	@Autowired
	UserAccountRepository userAccountRepository;

	@Autowired
	RequestRepository requestRepository;

	@Value("${environment}")
	String environment;

	@Override
	@Transactional
	public String createClient(CreateClientRequestDTO request, MultipartFile idDocument, MultipartFile proofHome,
			MultipartFile proofSalary) throws Exception {
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

		final Path rootForUser = Paths.get("uploads/" + userAccount.getIdUserAccount());
		Files.createDirectory(rootForUser);

		try {
			Files.copy(idDocument.getInputStream(), rootForUser.resolve(idDocument.getOriginalFilename()));
		} catch (FileAlreadyExistsException ignore) {
			//ignore exception
		}
		try {
			Files.copy(proofHome.getInputStream(), rootForUser.resolve(proofHome.getOriginalFilename()));
		} catch (FileAlreadyExistsException ignore) {
			//ignore exception
		}
		try {
			Files.copy(proofSalary.getInputStream(), rootForUser.resolve(proofSalary.getOriginalFilename()));
		} catch (FileAlreadyExistsException ignore) {
			//ignore exception
		}
		Document idDocumentDoc = new Document(IDENTIFICATION_DOCUMENT,
				rootForUser.toString() + "\\" + idDocument.getOriginalFilename(), userAccount);
		Document proofHomeDoc = new Document(PROOF_HOME,
				rootForUser.toString() + "\\" + proofHome.getOriginalFilename(), userAccount);
		Document proofSalaryDoc = new Document(PROOF_SALARY,
				rootForUser.toString() + "\\" + proofSalary.getOriginalFilename(), userAccount);
		documentRepository.save(idDocumentDoc);
		documentRepository.save(proofHomeDoc);
		documentRepository.save(proofSalaryDoc);
		emailService.sendConfirmationOfReceiptOfRequestEmail(userAccount.getFirstname(), userAccount.getEmail());

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
		} catch (Exception e) {
			filterId = -1; // like a default value
		}
		List<UserAccount> agents = userAccountRepository.filterPerIdOrLastNameAndRole(agentRole, filterId,
				"%" + filter + "%");
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

			canDelete = false;
		}
		if (agent.getRequestsTo().size() > 0) {

			canDelete = false;
		}
		if (agent.getBankAccounts().size() > 0) {

			canDelete = false;
		}
		if (agent.getNotifications().size() > 0) {

			canDelete = false;
		}
		if (agent.getDocuments().size() > 0) {

			canDelete = false;
		}
		if (canDelete) {
			userAccountRepository.delete(agent);
			userAccountRepository.flush();
		} else {
			agent.setEndDate(new Date());
			agent.setValid((byte) 0);
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

	@Override
	public List<ClientResponseForAdminDTO> getInvalidClients() throws Exception {
		Role clientRole = roleRepository.findByName(ROLE_CLIENT);
		if (clientRole == null) {
			throw new Exception("Role not found");
		}

		List<UserAccount> clients = userAccountRepository.findAllByRoleAndValid(clientRole, (byte) 0);

		if (clients == null || clients.size() == 0) {
			return null;
		}
		List<ClientResponseForAdminDTO> response = new ArrayList<ClientResponseForAdminDTO>();
		for (UserAccount client : clients) {
			String agent = "";
			for (Request request : client.getRequestsFrom()) {
				if (request.getTitle().equals(CREATE_ACCOUNT_TITLE)) {
					agent = request.getUserAccountTo().getFirstname() + " " + request.getUserAccountTo().getLastname();
				}
			}
			ClientResponseForAdminDTO tempObj = new ClientResponseForAdminDTO(client.getIdUserAccount(),
					client.getEmail(), client.getFirstname(), client.getLastname(), client.getPhone(), agent);
			response.add(tempObj);
		}

		return response;
	}

	@Override
	@Transactional
	public String assignClient(int agentId, AssignClientRequestDTO request) throws Exception {
		Optional<UserAccount> agentOpt = userAccountRepository.findById(agentId);
		if (!agentOpt.isPresent()) {
			throw new Exception("Agent not found!");
		}
		UserAccount agent = agentOpt.get();
		Role agentRole = roleRepository.findByName(ROLE_AGENT);
		if (agentRole == null) {
			throw new Exception("Role not found");
		}
		if (!agent.getRole().equals(agentRole)) {
			throw new Exception("Provided ID is not related to an Agent!");
		}

		Role clientRole = roleRepository.findByName(ROLE_CLIENT);
		if (clientRole == null) {
			throw new Exception("Role not found");
		}
		for (ClientRequestForAdminDTO clientReq : request.getClients()) {
			Optional<UserAccount> clientOpt = userAccountRepository.findById(clientReq.getIdUserAccount());
			if (!clientOpt.isPresent()) {
				throw new Exception("Client not found!");
			}
			UserAccount client = clientOpt.get();
			if (!client.getRole().equals(clientRole)) {
				throw new Exception("Provided ID is not related to a Client!");
			}
			Request assignRequest = new Request();
			for (Request r : client.getRequestsFrom()) {
				if (r.getTitle().equals(CREATE_ACCOUNT_TITLE)) {
					assignRequest = r;
				}
			}
			assignRequest.setTitle(CREATE_ACCOUNT_TITLE);
			assignRequest.setDescription("I want to open a new account");
			assignRequest.setTime(new Date());
			assignRequest.setRequestStatus((byte) 0);
			assignRequest.setUserAccountFrom(client);
			assignRequest.setUserAccountTo(agent);
			requestRepository.save(assignRequest);
		}

		return "Success";
	}

	@Override
	public List<GetUnresolvedRequestsForAgentResponseDTO> getUnresolvedRequests(int agentId, int requestTypeFlag)
			throws Exception {
		Optional<UserAccount> agentOpt = userAccountRepository.findById(agentId);
		if (!agentOpt.isPresent()) {
			throw new Exception("User account does not exist!");
		}
		UserAccount agent = agentOpt.get();
		Role agentRole = roleRepository.findByName(ROLE_AGENT);
		if (agentRole == null) {
			throw new Exception("Role not found");
		}
		if (!agent.getRole().equals(agentRole)) {
			throw new Exception("Provided ID is not related to an Agent!");
		}
		List<Request> requests = new ArrayList<Request>();
		if (requestTypeFlag == 0) {
			requests = requestRepository.findAllByUserAccountToAndRequestStatusAndTitle(agent, (byte) 0,
					RequestTitlesUtil.CREATE_ACCOUNT);
		} else {
			requests = requestRepository.findAllByUserAccountToAndRequestStatusAndTitleNot(agent, (byte) 0,
					RequestTitlesUtil.CREATE_ACCOUNT);
		}
		List<GetUnresolvedRequestsForAgentResponseDTO> response = new ArrayList<GetUnresolvedRequestsForAgentResponseDTO>();

		for (Request r : requests) {
			List<Document> documents = documentRepository.findAllByUserAccount(r.getUserAccountFrom());
			List<FileInfoResponseDTO> files = new ArrayList<FileInfoResponseDTO>();
			if (requestTypeFlag == 0) {
				for (Document d : documents) {
					String path = d.getPath();
					String url = environment + GET_FILES_METHOD_PATH + path.replace("\\", "/");
					String name = path.substring(path.lastIndexOf("\\") + 1);
					FileInfoResponseDTO tmpFile = new FileInfoResponseDTO(name, url);
					files.add(tmpFile);
				}
			}
			GetUnresolvedRequestsForAgentResponseDTO tmpObj = new GetUnresolvedRequestsForAgentResponseDTO(
					r.getIdRequest(), r.getTitle(), r.getDescription(), r.getTime(), r.getRequestStatus(),
					r.getUserAccountFrom().getIdUserAccount(), r.getUserAccountFrom().getEmail(),
					r.getUserAccountFrom().getFirstname(), r.getUserAccountFrom().getLastname(),
					r.getUserAccountFrom().getMarriageStatus(), r.getUserAccountFrom().getNumberOfChildren(),
					r.getUserAccountFrom().getPass(), r.getUserAccountFrom().getPhone(),
					r.getUserAccountFrom().getUsername(), r.getUserAccountFrom().getAddress().getAdditionalInfo(),
					r.getUserAccountFrom().getAddress().getCity(), r.getUserAccountFrom().getAddress().getCountry(),
					r.getUserAccountFrom().getAddress().getHomeNumber(),
					r.getUserAccountFrom().getAddress().getStreet(), r.getUserAccountFrom().getAddress().getZip(),
					r.getUserAccountFrom().getValid() == 0 ? false : true, files);
			response.add(tmpObj);
		}

		return response;
	}

	@Override
	@Transactional
	public String validation(VerifiedClientsRequestDTO request) throws Exception {
		for (VerifiedClientRequestDTO obj : request.getValidated()) {

			Optional<UserAccount> agentOpt = userAccountRepository.findById(obj.getIdAgent());
			if (!agentOpt.isPresent()) {
				throw new Exception("User Account does not exist!");
			}
			UserAccount agent = agentOpt.get();
			Role agentRole = roleRepository.findByName(ROLE_AGENT);
			if (agentRole == null) {
				throw new Exception("Role not found!");
			}
			if (!agent.getRole().equals(agentRole)) {
				throw new Exception("Provided ID is not related to an Agent!");
			}

			Optional<UserAccount> clientOpt = userAccountRepository.findById(obj.getIdClient());
			if (!clientOpt.isPresent()) {
				throw new Exception("User Account does not exist!");
			}
			UserAccount client = clientOpt.get();
			Role clientRole = roleRepository.findByName(ROLE_CLIENT);
			if (clientRole == null) {
				throw new Exception("Role not found!");
			}
			if (!client.getRole().equals(clientRole)) {
				throw new Exception("Provided ID is not related to a Client!");
			}

			Optional<Request> requestOpt = requestRepository.findById(obj.getIdRequest());

			if (!requestOpt.isPresent()) {
				throw new Exception("Request not found!");
			}

			Request req = requestOpt.get();

			if (obj.getIdAgent() != req.getUserAccountTo().getIdUserAccount()) {
				throw new Exception("You are not assigned to this request!");
			}

			if (obj.getIdClient() != req.getUserAccountFrom().getIdUserAccount()) {
				throw new Exception("Provided client is not associated to this request!");
			}

			if (req.getRequestStatus() != 0) {
				throw new Exception("Provided request has been already processed!");
			}

			if (client.getValid() != 0) {
				throw new Exception("Provided client has been already processed!");
			}
			if (obj.isValid()) {
				req.setRequestStatus((byte) 1);
				client.setValid((byte) 1);
				requestRepository.save(req);
				userAccountRepository.save(client);
				bankAccountService.createInitialBankAccount(client);
				emailService.sendVerificationEmail(client.getFirstname(), client.getUsername(), client.getPass(),
						client.getEmail());
			} else {
				
				for(Document doc:client.getDocuments()) {
					documentRepository.delete(doc);
					
				}
				final Path rootForUser = Paths.get("uploads/" + client.getIdUserAccount());
				fileStorageService.deleteDirectory(rootForUser.toFile());
				requestRepository.delete(req);
				userAccountRepository.delete(client);
				addressRepository.delete(client.getAddress());
				emailService.sendRejectionEmail(client.getFirstname(), client.getEmail());
			}
		}

		return "Success";
	}
	
	

	@Override
	public AgentResponseDTO getAgentOfClient(int idClient) throws Exception {
		Optional<UserAccount> clientOpt = userAccountRepository.findById(idClient);
		if (!clientOpt.isPresent()) {
			throw new Exception("User account does not exist!");
		}
		UserAccount client = clientOpt.get();
		Role clientRole = roleRepository.findByName(ROLE_CLIENT);
		if (clientRole == null) {
			throw new Exception("Role not found");
		}
		if (!client.getRole().equals(clientRole)) {
			throw new Exception("Provided ID is not related to a Client!");
		}

		// List<AgentResponseDTO> response = new ArrayList<AgentResponseDTO>();

//		List<Request> requests = requestRepository.findAllByUserAccountFromAndRequestStatus(client, (byte) 1);
//		
//		for(Request r:requests) {
//
//			UserAccount agent = r.getUserAccountTo();
//			
//				if (agent.getEndDate() == null) {
//					AgentResponseDTO tempObj = new AgentResponseDTO(agent.getEmail(), agent.getFirstname(),
//							agent.getLastname(), agent.getPhone(), agent.getUsername(),
//							agent.getAddress().getAdditionalInfo(), agent.getAddress().getCity(),
//							agent.getAddress().getCountry(), agent.getAddress().getHomeNumber(),
//							agent.getAddress().getStreet(), agent.getAddress().getZip(), agent.getIdUserAccount());
//					response.add(tempObj);
//					break;
//				}
// 					
//			}
		Optional<UserAccount> agentOpt = Optional
				.ofNullable(requestRepository.fetchAgentForClient(PageRequest.of(0, 1), client, (byte) 1))
				.filter(list -> !list.isEmpty()).map(list -> list.get(0));
		if (!agentOpt.isPresent()) {
			throw new Exception("You have no valid agent!");
		}
		UserAccount agent = agentOpt.get();
		AgentResponseDTO response = new AgentResponseDTO(agent.getEmail(), agent.getFirstname(), agent.getLastname(),
				agent.getPhone(), agent.getUsername(), agent.getAddress().getAdditionalInfo(),
				agent.getAddress().getCity(), agent.getAddress().getCountry(), agent.getAddress().getHomeNumber(),
				agent.getAddress().getStreet(), agent.getAddress().getZip(), agent.getIdUserAccount());
		// response.add(tempObj);
		return response;

	}

	@Override
	public String changePassword(PasswordChangeRequestDTO request) throws Exception {
		Optional<UserAccount> userOpt = userAccountRepository.findById(request.getLoggedInUserId());
		if (!userOpt.isPresent()) {
			throw new Exception("User Account does not exist!");
		}
		UserAccount loggedInUser = userOpt.get();

		if (loggedInUser.getValid() == 0) {
			throw new Exception("User with provided credentials is not valid!");
		}

		if (!loggedInUser.getPass().equals(request.getOldPassword())) {
			throw new Exception("Supplied current password does not match with the password saved in the database!");
		}

		loggedInUser.setPass(request.getNewPassword());

		userAccountRepository.save(loggedInUser);

		return "Success";
	}

	@Override
	public String forgotPassword(ForgotPasswordRequestDTO request) throws Exception {
		Optional<UserAccount> userOpt = userAccountRepository.findByEmail(request.getEmail());
		if (!userOpt.isPresent()) {
			throw new Exception("Provided e-mail does not exist!");
		}
		UserAccount user = userOpt.get();

		if (user.getValid() == 0) {
			throw new Exception("User with provided e-mail is not valid!");
		}
		
		// Generate random 36-character string token for reset password
		String token = UUID.randomUUID().toString();
		user.setToken(token);

		//Save token to database
		userAccountRepository.save(user);
		
		String passwordResetLink = "http://localhost:4200/forgotPassword?token=" + token;
		
		emailService.sendPasswordResetEmail(user.getFirstname(), passwordResetLink, user.getEmail());
		
		return "A password reset link has been sent to : " + user.getEmail() ;
	}

	@Override
	public String resetPassword(ResetPasswordRequestDTO request) throws Exception {
		Optional<UserAccount> userOpt = userAccountRepository.findByToken(request.getToken());
		if (!userOpt.isPresent()) {
			throw new Exception("Oops!  This is an invalid password reset link.");
		}
		UserAccount user = userOpt.get();

		if (user.getValid() == 0) {
			throw new Exception("User is not valid!");
		}
        
		// Set new password    
        user.setPass(request.getNewPassword());
        
		// Set the reset token to null so it cannot be used again
		user.setToken(null);

		// Save user
		userAccountRepository.save(user);
		
		
		return "You have successfully reset your password! You may now login.";
	}

}
