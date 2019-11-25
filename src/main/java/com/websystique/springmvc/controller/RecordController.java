package com.websystique.springmvc.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.websystique.springmvc.model.Attended;
import com.websystique.springmvc.model.Barangay;
import com.websystique.springmvc.model.Code;
import com.websystique.springmvc.model.EventDate;
import com.websystique.springmvc.model.Purok;
import com.websystique.springmvc.model.Voter;
import com.websystique.springmvc.service.AttendedService;
import com.websystique.springmvc.service.BarangayService;
import com.websystique.springmvc.service.CodeService;
import com.websystique.springmvc.service.EventDateService;
import com.websystique.springmvc.service.PurokService;
import com.websystique.springmvc.service.ReportCodeService;
import com.websystique.springmvc.service.UserProfileService;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.VoterService;
import com.websystique.springmvc.util.JasperReportUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

@Controller
@RequestMapping("/")
//@SessionAttributes("barangays")
public class RecordController {

	@Autowired
	UserService userService;

	@Autowired
	PurokService purokService;

	@Autowired
	BarangayService barangayService;
	
	@Autowired
	VoterService voterService;
	
	@Autowired
	AttendedService attendedService;
	
	
	@Autowired
	EventDateService eventDateService;

	@Autowired
	CodeService codeService;

	@Autowired
	ReportCodeService reportCodeService;
	
	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	 private static List<String> businessLines = Arrays.asList("OTHERS","MSME", "NGO", "MEDIA", "STUDENT", "NGA", "ACADEME");
	
	 
	private JasperReportUtil jrdao = new JasperReportUtil();
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "","/listvoters" }, method = RequestMethod.GET)
	public String listVoters(ModelMap model) {
		List<Voter> voters = voterService.findAllVoters();
		

		
		
		model.addAttribute("voters", voters);
		model.addAttribute("events", eventDateService.findAllEventDates());
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "personslist";
	}
	
	@RequestMapping(value = { "/liststatus" }, method = RequestMethod.GET)
	public String reportsData(ModelMap model) {

		String dateStr = "";
		if(getEnableEvent()!= null)
			dateStr = formatter.format(getEnableEvent());
		
		model.addAttribute("currentDate", dateStr);
		model.addAttribute("participantsACADEME", attendedService.findAllAttendeds(dateStr, businessLines.get(6)).size() );
		model.addAttribute("participantsNGA", attendedService.findAllAttendeds(dateStr, businessLines.get(5)).size());
		model.addAttribute("participantsSTUDENT", attendedService.findAllAttendeds(dateStr, businessLines.get(4)).size());
		model.addAttribute("participantsMEDIA", attendedService.findAllAttendeds(dateStr, businessLines.get(3)).size());
		model.addAttribute("participantsNGO", attendedService.findAllAttendeds(dateStr, businessLines.get(2)).size());
		model.addAttribute("participantsMSME", attendedService.findAllAttendeds(dateStr, businessLines.get(1)).size());
		model.addAttribute("participantsOTHERS", attendedService.findAllAttendeds(dateStr, businessLines.get(0)).size());
		
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "statuslist";
	}
	
	
	@RequestMapping("/json/data/listvoters")
	@ResponseBody
	public List<Voter> getVotes() {
		return voterService.findAllVoters();
	}
	/**
	 * This method will list all existing users.
	 */
/*	@RequestMapping(value = {  "/listcodes" }, method = RequestMethod.GET)
	public String listCodes(ModelMap model) {
		List<Code> codes = codeService.findAllCodes();
		model.addAttribute("codes", codes);
		model.addAttribute("loggedinuser", getPrincipal());
		return "codeslist";
	}*/
	/**
	 * This method will list all existing users.
	 */
/*	@RequestMapping("/json/data/listcodes")
	@ResponseBody
	public List<Code> getCodes() {
		return codeService.findAllCodes();
	}*/

	private Date getEnableEvent() {
		List<EventDate> events = eventDateService.findAllEnableEventDates();
		if(!events.isEmpty())
			return events.get(events.size()-1).getDate();
		return null;
	}
	
	@RequestMapping(value = { "/mark-participant-{id}" }, method = RequestMethod.GET)
	public String markAttended(@PathVariable String id, ModelMap model,HttpServletRequest request) {

		Voter voter = voterService.findById(Long.parseLong(id));
		
		Attended attended = new Attended();
		Date dateEvent = getEnableEvent();
		if(dateEvent != null)
			attended.setDate(dateEvent);
	
		attended.setVoter(voter);
		
		attendedService.saveAttended(attended);
		
		voter.getAttends().add(attended);
	
		voterService.updateVoter(voter);
		
		model.addAttribute("events", eventDateService.findAllEventDates());
		model.addAttribute("loggedinuser", getPrincipal());
		//return "personslist";
		return "redirect:/";
	}
	
	

	@RequestMapping(value = { "/select-event" }, method = RequestMethod.GET)
	public String selectEvent(@RequestParam(name = "selecteventid") String selecteventid, ModelMap model, HttpServletRequest request) {

		EventDate eventDate = eventDateService.findById(Integer.parseInt(selecteventid));
		List<EventDate> eventDates = eventDateService.findAllEventDates();
		
		// reset nalang
		for(EventDate e : eventDateService.findAllEventDates()) {
			e.setEnable(false);
			eventDateService.updateEventDate(e);
		}
		
		eventDate.setEnable(true);
		eventDateService.updateEventDate(eventDate);
		
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/";
	}
		
	
	
	@RequestMapping(value = { "/generatecode" }, method = RequestMethod.GET)
	public String generate(@RequestParam(name = "barangayId") String barangayId,
			@RequestParam(name = "printNo") String printNo,ModelMap model,
			HttpServletRequest request) {

		Barangay barangay = barangayService.findById(Integer.parseInt(barangayId));
		Integer totalCountPerBo = barangay.getCountGenerated();		

		
		if (Integer.parseInt(printNo) > 0) {

			for (int i = 0; i < Integer.parseInt(printNo); i++) {
				Code code = new Code();
				String codeId = code.getCode();
				code.setBarangay(barangay);

				codeId = barangay.getName().substring(0,3).toUpperCase() + codeId;
				code.setCode(codeId);

				codeService.saveCode(code);
				totalCountPerBo++;;
			}
		}
		barangay.setCountGenerated(totalCountPerBo);
		barangayService.updateBarangay(barangay);

		
		model.addAttribute("loggedinuser", getPrincipal());

		return "redirect:/listcodes";
	}

	@RequestMapping(value = { "/update-code" }, method = RequestMethod.GET)
	public String saveBarangay(@RequestParam(name = "codeid") Long codeid, ModelMap model, HttpServletRequest request) {
		Voter code = voterService.findById(codeid);
		code.setPrinted(true);
		voterService.updateVoter(code);
		return "redirect:/listcodes";
	}

	@RequestMapping(value = { "/generate-participantsform" }, method = RequestMethod.GET)
	public String generateReportRegistrationForm(
			@RequestParam(name = "generateNo") String generateNo, ModelMap model,
			HttpServletRequest request) {

		if (Integer.parseInt(generateNo) > 0) {
			for (int i = 0; i < Integer.parseInt(generateNo); i++) {
				Voter code = new Voter();
				voterService.saveVoter(code);		
			}
		}
		model.addAttribute("loggedinuser", getPrincipal());

		return "redirect:/listvoters";
	}
	
	@RequestMapping(value = "/pdf-participantsform")
	public String pdfReportRegistrationForm(HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException, NamingException {

		Long generateStartNo = Long.parseLong(request.getParameter("generateStartNo"));
		Long generateEndNo = Long.parseLong(request.getParameter("generateEndNo"));	
		boolean isWalking= Boolean.parseBoolean(request.getParameter("isWalking")); 
		
		String reportFileName = "invitee_form";
		if(isWalking)
			reportFileName = "walkin";
		

		HashMap<String, Object> hmParams = new HashMap<String, Object>();
		
		BufferedImage image1 =  null;
		
		try {
			 File initialImage = new File(request.getSession().getServletContext().getRealPath("/jasper/dost.png"));
			 image1 = ImageIO.read(initialImage);
		}catch(Exception e) {}
		
		hmParams.put("dost", image1);

		JasperReport jasperReport = jrdao.getCompiledFile(reportFileName, request);

		List<Map<String, ?>> listCodes = new ArrayList<Map<String, ?>>();
		

		Map<String, Object> m = null;
		if(generateStartNo > 0 && generateStartNo <= generateEndNo)
		for(Voter participant : voterService.findAllVoters(generateStartNo, generateEndNo)) {
				m = new HashMap<String, Object>();
				
				m.put("id", String.valueOf(participant.getId()));
				m.put("number", participant.getCode() );
				
				if(!isWalking) {
					m.put("lastName", participant.getLastName().toUpperCase());
					m.put("middleName", participant.getMiddleName().toUpperCase());
					m.put("firstName", participant.getFirstName().toUpperCase());
					m.put("office", participant.getCompany());
					m.put("designation", participant.getDesignation());
					m.put("contactNo", participant.getContact());
					m.put("emailAdd", participant.getEmail());
					m.put("age", String.valueOf(participant.getAge()));
					m.put("sex", participant.getGender());
					m.put("status", participant.getStatus());
					m.put("business", participant.getBusiness());
				}
				listCodes.add(m);
		}
		
		
		try {
			jrdao.generateReportPDF(response, hmParams, listCodes, jasperReport);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // For
			// PDF
			// report

		return null;
	}
	
	
	@RequestMapping(value = "/barangay-pdf")
	public String generateReportBarangay(HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException, NamingException {

		String reportFileName = "number";
		JasperReportUtil jrdao = new JasperReportUtil();

		HashMap<String, Object> hmParams = new HashMap<String, Object>();
		
		
		JasperReport jasperReport = jrdao.getCompiledFile(reportFileName, request);

		List<Map<String, ?>> listCodes = new ArrayList<Map<String, ?>>();
		
		int printNo = Integer.parseInt(request.getParameter("printNo"));
		int barangayId = Integer.parseInt(request.getParameter("barangayId"));
		//int purokId = Integer.parseInt(request.getParameter("purokId"));
		
		int size = printNo;
		int index = 1;
		
		Map<String, Object> m = null;
		for(Code code : codeService.findAllCodesChecked()) {
			if(index > size)
				break;
			if(code.getBarangay() != null && barangayId == code.getBarangay().getId()) {
				m = new HashMap<String, Object>();
				m.put("number", code.getCode());
				listCodes.add(m);
			}
			index++;
		}
		
		try {
			jrdao.generateReportPDF(response, hmParams, listCodes, jasperReport);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // For
			// PDF
			// report

		return null;
	}
	
	@RequestMapping(value = "/pdf")
	public String generateReport(HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException, NamingException {

		String reportFileName = "number";
		JasperReportUtil jrdao = new JasperReportUtil();

		HashMap<String, Object> hmParams = new HashMap<String, Object>();
		
		
		JasperReport jasperReport = jrdao.getCompiledFile(reportFileName, request);

		List<Map<String, ?>> listCodes = new ArrayList<Map<String, ?>>();
		
/*		int printNo = Integer.parseInt(request.getParameter("printNo"));*/
		int barangayId = Integer.parseInt(request.getParameter("barangayId"));
		//int purokId = Integer.parseInt(request.getParameter("purokId"));
		
	/*	int size = printNo;*/
		int index = 1;
		
		Map<String, Object> m = null;
		for(Voter code : voterService.findAllVoters()) {

/*			if(code.getBarangay() != null && barangayId == code.getBarangay().getId()) {
				m = new HashMap<String, Object>();
				m.put("number", code.getCode());
				m.put("name", code.getCompleteName());
				listCodes.add(m);
			}*/

		}
		
		try {
			jrdao.generateReportPDF(response, hmParams, listCodes, jasperReport);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // For
			// PDF
			// report

		return null;
	}
	
	
	/**
	 * This method will provide Barangay list to views
	 */
	@ModelAttribute("barangays")
	public List<Barangay> initializeBarangays() {
		List<Barangay> barangays = barangayService.findAllBarangays();
		List<Barangay> newbarangays = new ArrayList<>();
		for(Barangay barangay : barangays) {
			if(reportCodeService.findByReportCode(barangay.getId()) != null)
				barangay.setCountGenerated(reportCodeService.findByReportCode(barangay.getId()).getCount());
			newbarangays.add(barangay);
		}
		return newbarangays;
	}

	@ModelAttribute("mgapurok")
	public List<Purok> initializePuroks() {
		return purokService.findAllPuroks();
	}

	@RequestMapping(value = { "/newbarangay" }, method = RequestMethod.GET)
	public String saveBarangay(@RequestParam(name = "name") String name, ModelMap model, HttpServletRequest request) {
		Barangay barangay = new Barangay();
		barangay.setName(name);
		barangayService.saveBarangay(barangay);
		return "redirect:/newperson";
	}

	@RequestMapping(value = { "/newpurok" }, method = RequestMethod.GET)
	public String savePurok(@RequestParam(name = "name") String name,
			@RequestParam(name = "barangayId", required = false) String barangayId, ModelMap model,
			HttpServletRequest request) {

		Purok purok = new Purok();
		purok.setName(name);

/*		if (Integer.parseInt(barangayId) > 0 && barangayService.findById(Integer.parseInt(barangayId)) != null)
			purok.setBarangayId(barangayService.findById(Integer.parseInt(barangayId)).getId());*/

		purokService.savePurok(purok);
		return "redirect:/newperson";
	}

	
	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newperson" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		Voter voter = new Voter();
		
		model.addAttribute("voter", voter);
		model.addAttribute("edit", false);
		model.addAttribute("genders", Arrays.asList("Male","Famale"));
		model.addAttribute("businessLines", businessLines);
		model.addAttribute("loggedinuser", getPrincipal());		
		return "person";
	}
	
	@RequestMapping(value = { "/edit-person-{id}" }, method = RequestMethod.GET)
	public String editPerson(@PathVariable String id, ModelMap model) {

		Voter voter = voterService.findById(Long.parseLong(id));
		
		model.addAttribute("voter", voter);
		model.addAttribute("edit", true);
		model.addAttribute("genders", Arrays.asList("Male","Famale"));
		model.addAttribute("businessLines", Arrays.asList("OTHERS","MSME", "NGO", "MEDIA", "STUDENT", "NGA", "ACADEME"));
		model.addAttribute("loggedinuser", getPrincipal());
		return "person";
	}

	
	@RequestMapping(value = { "/newperson" }, method = RequestMethod.POST)
	public String saveUser(@Valid Voter voter, BindingResult result, ModelMap model) {

		String name = "";
		
/*		Barangay barangay = barangayService.findById(voter.getBarangayId());
		voter.setBarangay(barangay);
		
		Purok purok = null;
		try {
		purok = purokService.findById(Integer.parseInt(voter.getPurokId()));
		voter.setPurok(purok);
		}catch(Exception e) {}
		
		
		try {
			name += voter.getBarangay().getName().substring(0, 3).toUpperCase();
		}catch(Exception e) {
			
		}
	
		if(voter.getPurok() != null ) {
			try {
				name += voter.getPurok().getName().substring(0, 3).toUpperCase();
			}catch(Exception e) {
				
			}
		}
		if (result.hasErrors()) {
			return "person";
		}
		
		voter.setCode(name+voter.getCode());
		
		//countGenerated
		Barangay b = voter.getBarangay();
		voter.setBarangay(b);*/
		
		voterService.saveVoter(voter);
		
		model.addAttribute("success", "Code " + voter.getFirstName() + " " + voter.getLastName() + " added record successfully");

		model.addAttribute("loggedinuser", getPrincipal());
		// return "success";
		return "personsuccess";
		
		
		
		
		
		
		
	}
	


	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-person-{id}" }, method = RequestMethod.POST)
	public String updatePerson(@Valid Voter voter, BindingResult result, ModelMap model, @PathVariable String id) {

		if (result.hasErrors()) {
			return "person";
		}

/*		Barangay barangay = barangayService.findById(voter.getBarangayId());
		voter.setBarangay(barangay);
		
		Purok purok = null;
				
		try {
			purokService.findById(Integer.parseInt(voter.getPurokId()));
		voter.setPurok(purok);
		}catch(Exception e) {}
		*/
		// userService.updateUser(user);
/*		LocalDate myDate = new LocalDate(code.getBarangayStr());
		code.setBirthdate(myDate);*/
		voterService.updateVoter(voter);
		model.addAttribute("success",
				"User " + voter.getFirstName() + " " + voter.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "personsuccess";
	}

	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-person-{id}" }, method = RequestMethod.GET)
	public String deletePerson(@PathVariable String id) {
		Voter code = voterService.findById(Long.parseLong(id));
		voterService.deleteVoter(code);
		return "redirect:/listvoters";
	}
	
	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	/**
	 * This method returns true if users is already authenticated [logged-in], else
	 * false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}
}
