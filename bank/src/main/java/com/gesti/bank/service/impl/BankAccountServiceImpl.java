package com.gesti.bank.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gesti.bank.dto.BankAccountResponseDTO;
import com.gesti.bank.dto.BankAccountTypeResponseDTO;
import com.gesti.bank.dto.BankRuleResponseDTO;
import com.gesti.bank.dto.CreateCustomRequestForAgentDTO;
import com.gesti.bank.dto.GetAccountResponseDTO;
import com.gesti.bank.dto.ModifyBankAccountRequestDTO;
import com.gesti.bank.dto.RequestsForAgentResolutionDTO;
import com.gesti.bank.dto.SimpleMessageResponseDTO;
import com.gesti.bank.model.BankAccount;
import com.gesti.bank.model.BankAccountType;
import com.gesti.bank.model.BankRule;
import com.gesti.bank.model.Request;
import com.gesti.bank.model.Role;
import com.gesti.bank.model.UserAccount;
import com.gesti.bank.repository.BankAccountRepository;
import com.gesti.bank.repository.BankAccountTypeRepository;
import com.gesti.bank.repository.BankRuleRepository;
import com.gesti.bank.repository.RequestRepository;
import com.gesti.bank.repository.RoleRepository;
import com.gesti.bank.repository.UserAccountRepository;
import com.gesti.bank.service.BankAccountService;
import com.gesti.bank.service.EmailService;
import com.gesti.bank.util.RequestTitlesUtil;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	private final static int INITIAL_BANK_ACCOUNT_TYPE_ID = 1;
	private final static int INITIAL_RULE_ID = 1;

	private final static String ROLE_ADMIN = "admin";
	private final static String ROLE_CLIENT = "client";
	private final static String ROLE_AGENT = "agent";

	private final static String RULE_TYPE_CONTAINING_CURRENT_TEXT = "Current";
	private final static String RULE_TYPE_CONTAINING_SAVING_TEXT = "Saving";
	private final static String RULE_TYPE_CONTAINING_CHEQUE_TEXT = "Cheque";

	@Autowired
	BankAccountRepository bankAccountRepository;

	@Autowired
	BankAccountTypeRepository bankAccountTypeRepository;

	@Autowired
	BankRuleRepository bankRuleRepository;

	@Autowired
	UserAccountRepository userAccountRepository;

	@Autowired
	RequestRepository requestRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	EmailService emailService;

	@Override
	public void createInitialBankAccount(UserAccount client) throws Exception {
		// TODO Auto-generated method stub
		BankAccount bankAccount = new BankAccount();
		bankAccount.setBankAccountNumber(generateBankAccountNumber());
		Optional<BankAccountType> initialTypeOpt = bankAccountTypeRepository.findById(INITIAL_BANK_ACCOUNT_TYPE_ID);
		if (!initialTypeOpt.isPresent()) {
			throw new Exception("Bank account type with provided id does not exist!");
		}
		BankAccountType initialType = initialTypeOpt.get();
		bankAccount.setBankAccountStatus((byte) 1);
		bankAccount.setBankAccountType(initialType);
		bankAccount.setUserAccount(client);
		Optional<BankRule> initialBankRuleOpt = bankRuleRepository.findById(INITIAL_RULE_ID);
		if (!initialBankRuleOpt.isPresent()) {
			throw new Exception("Bank rule with provided id does not exist!");
		}
		BankRule initialBankRule = initialBankRuleOpt.get();
		bankAccount.setBankRule(initialBankRule);
		bankAccount.setCreationDate(new Date());
		bankAccountRepository.save(bankAccount);
	}

	private String generateBankAccountNumber() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			sb.append(random.nextInt(10));
		}
		String generatedBankAccountNumber = sb.toString();
		List<BankAccount> checkNumberList = bankAccountRepository
				.findAllByBankAccountNumber(generatedBankAccountNumber);
		if (checkNumberList != null && !checkNumberList.isEmpty()) {
			return generateBankAccountNumber();
		}
		return generatedBankAccountNumber;
	}

	@Override
	public List<BankAccountResponseDTO> getBankAccounts(int id) throws Exception {
		Optional<UserAccount> loggedInUserOpt = userAccountRepository.findById(id);
		if (!loggedInUserOpt.isPresent()) {
			throw new Exception("User with provided ID does not exist!");
		}
		UserAccount loggedInUser = loggedInUserOpt.get();
		if (loggedInUser.getValid() == 0) {
			throw new Exception("User with provided credentials is not valid!");
		}
		List<BankAccountResponseDTO> response = new ArrayList<BankAccountResponseDTO>();
		Map<BankAccount, Boolean> bankAccountForResponse = new HashMap<BankAccount, Boolean>();
		// u slucaju da je ulogovani juzer agent
		if (loggedInUser.getRole().getName().equals(ROLE_AGENT)) {
			// razmatraju se validni rikvestovi za otvaranje tekuceg racuna koje je dobio
			// ulogovani agent
			List<Request> requests = requestRepository.findAllByUserAccountToAndRequestStatusAndTitle(loggedInUser,
					(byte) 1, RequestTitlesUtil.CREATE_ACCOUNT);
			for (UserAccount client : requests.stream().map(req -> req.getUserAccountFrom())
					.filter(client -> client.getValid() == (byte) 1).collect(Collectors.toList())) {
				boolean clientSavingAccountFlag = client.getBankAccounts().stream().anyMatch(
						bankAcc -> bankAcc.getBankAccountType().getName().equals(RULE_TYPE_CONTAINING_SAVING_TEXT)
								&& bankAcc.getBankAccountStatus() == (byte) 1);

				client.getBankAccounts().stream().filter(bankAcc -> bankAcc.getBankAccountStatus() == (byte) 1)
						.forEach(bankAcc -> {
							bankAccountForResponse.put(bankAcc, clientSavingAccountFlag);
						});
			}
		}
		// u slucaju da je ulogovani juzer klijent (iznad je vec provereno da li je
		// juzer
		// validan)
		else if (loggedInUser.getRole().getName().equals(ROLE_CLIENT)) {
			boolean clientSavingAccountFlag = loggedInUser.getBankAccounts().stream()
					.anyMatch(bankAcc -> bankAcc.getBankAccountType().getName().equals(RULE_TYPE_CONTAINING_SAVING_TEXT)
							&& bankAcc.getBankAccountStatus() == (byte) 1);
			loggedInUser.getBankAccounts().stream().filter(bankAcc -> bankAcc.getBankAccountStatus() == (byte) 1)
					.forEach(bankAcc -> {
						bankAccountForResponse.put(bankAcc, clientSavingAccountFlag);
					});
			;
		} else {
			throw new Exception("You don't have a permission for this action!");
		}

		for (Map.Entry<BankAccount, Boolean> bankAccEntry : bankAccountForResponse.entrySet()) {
			BankAccount bankAcc = bankAccEntry.getKey();
			Boolean clientSavingAccountFlag = bankAccEntry.getValue();
			BankAccountResponseDTO tmpObj = new BankAccountResponseDTO(bankAcc.getIdBankAccount(),
					bankAcc.getBankAccountNumber(), bankAcc.getBankAccountType().getIdBankAccountType(),
					bankAcc.getBankAccountType().getName(), bankAcc.getUserAccount().getIdUserAccount(),
					bankAcc.getUserAccount().getFirstname() + " " + bankAcc.getUserAccount().getLastname(),
					bankAcc.getBankRule().getIdBankRules(), bankAcc.getBankRule().getPercent(),
					bankAcc.getBankRule().getRuleName(), bankAcc.getCreationDate(), clientSavingAccountFlag);
			response.add(tmpObj);
		}

		if (response.isEmpty()) {
			throw new Exception("There is no bank account!");
		}

		return response.stream().sorted(Comparator.comparing(BankAccountResponseDTO::getUserAccountFullName))
				.collect(Collectors.toList());
	}

	@Override
	public BankAccountResponseDTO getBankAccount(int id, int userID) throws Exception {
		boolean clientSavingAccountFlag = false;
		Optional<UserAccount> userAccountOpt = userAccountRepository.findById(userID);
		if (!userAccountOpt.isPresent()) {
			throw new Exception("User account with provided ID does not exist!");
		}
		UserAccount user = userAccountOpt.get();
		if (user.getValid() == 0) {
			throw new Exception("User with provided credentials is not valid!");
		}
		Optional<BankAccount> bankAccountOpt = bankAccountRepository.findById(id);
		if (!bankAccountOpt.isPresent()) {
			throw new Exception("Bank account with provided ID does not exist!");
		}
		BankAccount bankAcc = bankAccountOpt.get();
		if (bankAcc.getBankAccountStatus() == (byte) 0) {
			throw new Exception("Bank account with provided ID is not active!");
		}
		// u slucaju da je ulogovani juzer agent
		if (user.getRole().getName().equals(ROLE_AGENT)) {

			// prvo se proverava da li agent ima pravo dostupa racunu tog klijenta, odnosno,
			// da li je taj klijent dodeljen njemu ili nije
			boolean havePermission = false;
			List<Request> requests = requestRepository.findAllByUserAccountToAndRequestStatusAndTitle(user, (byte) 1,
					RequestTitlesUtil.CREATE_ACCOUNT);
			for (Request r : requests) {
				if (havePermission) {
					break;
				}
				UserAccount client = r.getUserAccountFrom();
				// razmatraju se samo validni klijenti
				if (client.getValid() == (byte) 1) {
					for (BankAccount tmpBankAcc : client.getBankAccounts()) {
						if (tmpBankAcc.getIdBankAccount() == bankAcc.getIdBankAccount()) {
							havePermission = true;
							break;
						}
					}
				}
			}
			if (!havePermission) {
				throw new Exception("That is not your client!");
			}
			// ukoliko agent ima pravo dostupa tom racunu
			clientSavingAccountFlag = bankAcc.getUserAccount().getBankAccounts().stream().anyMatch(
					bankAccount -> bankAccount.getBankAccountType().getName().equals(RULE_TYPE_CONTAINING_SAVING_TEXT)
							&& bankAccount.getBankAccountStatus() == (byte) 1);
//			UserAccount client = bankAcc.getUserAccount(); // iznad je vec provereno da je taj klijent validan
//			for (BankAccount bankAccount : client.getBankAccounts()) {
//				// prolazi se kroz sve validne racune, bilo kog tipa, tog klijenta i ukoliko se
//				// pronadje validan stedni racun, flag se stavlja na
//				// true, inace ostaje false
//				if (bankAccount.getBankAccountType().getName().equals(RULE_TYPE_CONTAINING_SAVING_TEXT)
//						&& bankAccount.getBankAccountStatus() == (byte) 1) {
//					clientSavingAccountFlag = true;
//					break;
//				}
//			}

		} // u slucaju da je ulogovani juzer klijent
		else if (user.getRole().getName().equals(ROLE_CLIENT))

		{
			if (bankAcc.getUserAccount().getIdUserAccount() != user.getIdUserAccount()) {
				throw new Exception("That is not your account!");
			}

			clientSavingAccountFlag = user.getBankAccounts().stream().anyMatch(
					bankAccount -> bankAccount.getBankAccountType().getName().equals(RULE_TYPE_CONTAINING_SAVING_TEXT)
							&& bankAccount.getBankAccountStatus() == (byte) 1);
//			for (BankAccount bankAccount : user.getBankAccounts()) {
//				// prolazi se kroz sve validne racune, bilo kog tipa, tog klijenta i ukoliko se
//				// pronadje validan stedni racun, flag se stavlja na
//				// true, inace ostaje false
//				if (bankAccount.getBankAccountType().getName().equals(RULE_TYPE_CONTAINING_SAVING_TEXT)
//						&& bankAccount.getBankAccountStatus() == (byte) 1) {
//					clientSavingAccountFlag = true;
//					break;
//				}
//			}
		}
// ovde se napravi DTO Response objekat
		BankAccountResponseDTO response = new BankAccountResponseDTO(bankAcc.getIdBankAccount(),
				bankAcc.getBankAccountNumber(), bankAcc.getBankAccountType().getIdBankAccountType(),
				bankAcc.getBankAccountType().getName(), bankAcc.getUserAccount().getIdUserAccount(),
				bankAcc.getUserAccount().getFirstname() + " " + bankAcc.getUserAccount().getLastname(),
				bankAcc.getBankRule().getIdBankRules(), bankAcc.getBankRule().getPercent(),
				bankAcc.getBankRule().getRuleName(), bankAcc.getCreationDate(), clientSavingAccountFlag);
		return response;
	}

	@Override
	public List<BankAccountTypeResponseDTO> getBankAccountTypes(int bankAccountFlag) throws Exception {
		List<BankAccountTypeResponseDTO> response = new ArrayList<BankAccountTypeResponseDTO>();
		List<BankAccountType> catchTypes = new ArrayList<BankAccountType>();
		if (bankAccountFlag == 0) {
			catchTypes = bankAccountTypeRepository.findAllByNameIgnoreCaseContaining(RULE_TYPE_CONTAINING_CURRENT_TEXT);
		} else if (bankAccountFlag == 1) {
			catchTypes = bankAccountTypeRepository.findAllByNameIgnoreCaseContaining(RULE_TYPE_CONTAINING_SAVING_TEXT);
		} else if (bankAccountFlag == 2) {
			catchTypes = bankAccountTypeRepository.findAllByNameIgnoreCaseContaining(RULE_TYPE_CONTAINING_CHEQUE_TEXT);
		}
		for (BankAccountType bat : catchTypes) {
			BankAccountTypeResponseDTO tmpResObj = new BankAccountTypeResponseDTO();
			tmpResObj.setIdBankAccountType(bat.getIdBankAccountType());
			tmpResObj.setName(bat.getName());
			response.add(tmpResObj);
		}
		return response;
	}

	@Override
	public List<BankRuleResponseDTO> getBankRules(int bankAccountFlag) throws Exception {
		List<BankRuleResponseDTO> response = new ArrayList<BankRuleResponseDTO>();
		List<BankRule> catchRules = new ArrayList<BankRule>();
		if (bankAccountFlag == 0) {
			catchRules = bankRuleRepository.findAllByRuleNameIgnoreCaseContaining(RULE_TYPE_CONTAINING_CURRENT_TEXT);
		} else if (bankAccountFlag == 1) {
			catchRules = bankRuleRepository.findAllByRuleNameIgnoreCaseContaining(RULE_TYPE_CONTAINING_SAVING_TEXT);
		} else if (bankAccountFlag == 2) {
			catchRules = bankRuleRepository.findAllByRuleNameIgnoreCaseContaining(RULE_TYPE_CONTAINING_CHEQUE_TEXT);
		}

		for (BankRule br : catchRules) {
			BankRuleResponseDTO tmpResObj = new BankRuleResponseDTO();
			tmpResObj.setIdBankRules(br.getIdBankRules());
			tmpResObj.setPercent(br.getPercent());
			tmpResObj.setRuleName(br.getRuleName());
			response.add(tmpResObj);
		}
		return response;
	}

	@Override
	public String modifyBankAccount(ModifyBankAccountRequestDTO request, int userID) throws Exception {
		Optional<UserAccount> userAccountOpt = userAccountRepository.findById(userID);
		if (!userAccountOpt.isPresent()) {
			throw new Exception("User account with provided ID does not exist!");
		}

		UserAccount user = userAccountOpt.get();
		if (user.getValid() != 1) {
			throw new Exception("User is not valid!");
		}
		Optional<BankAccount> bankAccountOpt = bankAccountRepository.findById(request.getIdBankAccount());
		if (!bankAccountOpt.isPresent()) {
			throw new Exception("Bank account with provided ID does not exist!");
		}
		BankAccount bankAcc = bankAccountOpt.get();
		if (bankAcc.getBankAccountStatus() == (byte) 0) {
			throw new Exception("Bank account with provided ID is not active!");
		}
		if (user.getRole().getName().equals(ROLE_AGENT)) {
			boolean havePermission = false;
			List<Request> requests = requestRepository.findAllByUserAccountToAndRequestStatus(user, (byte) 1);
			for (Request r : requests) {
				if (havePermission) {
					break;
				}
				UserAccount client = r.getUserAccountFrom();
				if (client.getValid() == 1) {
					for (BankAccount tmpBankAcc : client.getBankAccounts()) {
						if (tmpBankAcc.getIdBankAccount() == bankAcc.getIdBankAccount()) {
							havePermission = true;
							break;
						}
					}
				}
			}
			if (!havePermission) {
				throw new Exception("That is not your client!");
			}
		} else {
			throw new Exception("You do not have a permission!");
		}

		Optional<BankAccountType> bankAccountTypeOpt = bankAccountTypeRepository
				.findById(request.getIdBankAccountType());
		if (!bankAccountTypeOpt.isPresent()) {
			throw new Exception("Bank account type does not exist!");
		}
		BankAccountType bankAccountType = bankAccountTypeOpt.get();

		Optional<BankRule> bankRuleOpt = bankRuleRepository.findById(request.getIdBankRules());
		if (!bankRuleOpt.isPresent()) {
			throw new Exception("Bank rule does not exist!");
		}
		BankRule bankRule = bankRuleOpt.get();

		bankAcc.setBankAccountType(bankAccountType);
		bankAcc.setBankRule(bankRule);

		bankAccountRepository.save(bankAcc);
		return "Success";
	}

	@Override
	public String deactivateBankAccount(int id, int userID) throws Exception {
		Optional<UserAccount> userAccountOpt = userAccountRepository.findById(userID);
		if (!userAccountOpt.isPresent()) {
			throw new Exception("User account with provided ID does not exist!");
		}
		UserAccount user = userAccountOpt.get();
		if (user.getValid() != 1) {
			throw new Exception("User is not valid!");
		}
		Optional<BankAccount> bankAccountOpt = bankAccountRepository.findById(id);
		if (!bankAccountOpt.isPresent()) {
			throw new Exception("Bank account with provided ID does not exist!");
		}
		BankAccount bankAcc = bankAccountOpt.get();
		if (bankAcc.getBankAccountStatus() == (byte) 0) {
			throw new Exception("Bank account with provided ID is not active!");
		}
		if (user.getRole().getName().equals(ROLE_AGENT)) {
			boolean havePermission = false;
			List<Request> requests = requestRepository.findAllByUserAccountToAndRequestStatus(user, (byte) 1);
			for (Request r : requests) {
				if (havePermission) {
					break;
				}
				UserAccount client = r.getUserAccountFrom();
				for (BankAccount tmpBankAcc : client.getBankAccounts()) {
					if (tmpBankAcc.getIdBankAccount() == bankAcc.getIdBankAccount()) {
						havePermission = true;
						break;
					}
				}
			}
			if (!havePermission) {
				throw new Exception("That is not your client!");
			}
		} else {
			throw new Exception("You do not have a permission!");
		}
		bankAcc.setBankAccountStatus((byte) 0);

		bankAccountRepository.save(bankAcc);
		return "Success";

	}

	@Override
	public String createCustomRequestForAgent(CreateCustomRequestForAgentDTO request) throws Exception {
		Optional<UserAccount> clientAccountOpt = userAccountRepository.findById(request.getLoggedInUserId());
		if (!clientAccountOpt.isPresent()) {
			throw new Exception("User account with provided ID does not exist!");
		}
		UserAccount client = clientAccountOpt.get();
		if (client.getValid() != 1) {
			throw new Exception("User is not valid!");
		}
		Optional<Request> lastValidatedRequestOpt = requestRepository
				.findFirstByUserAccountFromAndRequestStatusOrderByIdRequestDesc(client, (byte) 1);
		if (!lastValidatedRequestOpt.isPresent()) {
			throw new Exception("We can not find your agent at the moment, please try again later!");
		}
		Request lastValidatedRequest = lastValidatedRequestOpt.get();

		String requestTitle = request.getTitle();
		if (!RequestTitlesUtil.requestTitlesForClients.contains(requestTitle)) {
			throw new Exception("Request title is not valid!");
		}

		UserAccount agent = lastValidatedRequest.getUserAccountTo();

		Request newRequest = new Request();

		newRequest.setDescription(request.getDescription());
		newRequest.setRequestStatus((byte) 0);
		newRequest.setTime(new Date());
		newRequest.setTitle(requestTitle);
		newRequest.setUserAccountFrom(client);
		newRequest.setUserAccountTo(agent);
		requestRepository.save(newRequest);

		return String.format("Agent %s will process your request soon!",
				agent.getFirstname() + " " + agent.getLastname());
	}

	@Override
	public SimpleMessageResponseDTO markRequestsAsResolved(RequestsForAgentResolutionDTO request) throws Exception {

		System.out.println("Stigao mi je request za agenta " + request.getLoggedInAgentId());

		Optional<UserAccount> agentOpt = userAccountRepository.findById(request.getLoggedInAgentId());

		if (!agentOpt.isPresent()) {
			throw new Exception("User account with provided ID does not exist!");
		}
		UserAccount agent = agentOpt.get();

		if (agent.getValid() != 1) {
			throw new Exception("User is not valid!");
		}

		Role roleAgent = roleRepository.findByName(ROLE_AGENT);

		if (roleAgent == null) {
			throw new Exception("Provided role does not exist!");
		}

		if (!agent.getRole().equals(roleAgent)) {
			throw new Exception("User is not an agent!");
		}

		for (Integer requestId : request.getRequestsIdList()) {
			Optional<Request> reqOpt = requestRepository.findById(requestId);
			if (!reqOpt.isPresent()) {
				throw new Exception("Request with request id " + requestId + " does not exist!");
			}
			Request req = reqOpt.get();
			if (!req.getUserAccountTo().equals(agent)) {
				throw new Exception("Request with request id " + requestId + " is not assigned to you!");
			}

			req.setRequestStatus((byte) 1);
			requestRepository.save(req);
		}

		return new SimpleMessageResponseDTO("Success");
	}

	@Override
	public String createChequeBookForBankAccount(int id, int userID) throws Exception {
		Optional<UserAccount> userAccountOpt = userAccountRepository.findById(userID);
		if (!userAccountOpt.isPresent()) {
			throw new Exception("User account with provided ID does not exist!");
		}
		UserAccount user = userAccountOpt.get();
		if (user.getValid() != 1) {
			throw new Exception("User is not valid!");
		}
		Optional<BankAccount> bankAccountOpt = bankAccountRepository.findById(id);
		if (!bankAccountOpt.isPresent()) {
			throw new Exception("Bank account with provided ID does not exist!");
		}
		BankAccount bankAcc = bankAccountOpt.get();
		if (bankAcc.getBankAccountStatus() == (byte) 0) {
			throw new Exception("Bank account with provided ID is not active!");
		}

		Role roleAgent = roleRepository.findByName(ROLE_AGENT);

		if (roleAgent == null) {
			throw new Exception("Provided role does not exist!");
		}

		if (user.getRole().equals(roleAgent)) {
			boolean havePermission = false;
			List<Request> requests = requestRepository.findAllByUserAccountToAndRequestStatusAndTitle(user, (byte) 1,
					RequestTitlesUtil.CREATE_ACCOUNT);
			for (Request r : requests) {
				if (havePermission) {
					break;
				}
				UserAccount client = r.getUserAccountFrom();
				if (client.getValid() == 1) {
					for (BankAccount tmpBankAcc : client.getBankAccounts()) {
						if (tmpBankAcc.getIdBankAccount() == bankAcc.getIdBankAccount()) {
							havePermission = true;
							break;
						}
					}
				}
			}
			if (!havePermission) {
				throw new Exception("That is not your client!");
			}
		} else {
			throw new Exception("You do not have a permission!");
		}
		
		emailService.sendChequeBookCreationConfirmationEmail(bankAcc.getUserAccount().getFirstname(), bankAcc.getBankAccountType().getName(), bankAcc.getBankAccountNumber(), bankAcc.getUserAccount().getEmail());

		return "La commande de chéquier pour le client : " + bankAcc.getUserAccount().getFirstname() + " "
				+ bankAcc.getUserAccount().getLastname() + ", pour le compte de numéro : " + bankAcc.getBankAccountNumber()
				+  ", de type : " + bankAcc.getBankAccountType().getName()
				+ ", a bien été effectuée!";
	}

}
