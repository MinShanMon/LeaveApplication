package ca.team3.laps;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ca.team3.laps.model.Leave;

import ca.team3.laps.model.LeaveStatusEnum;
import ca.team3.laps.model.LeaveTypeEnum;
import ca.team3.laps.model.Staff;
import ca.team3.laps.model.CalendarificAPI.Holiday;
import ca.team3.laps.repository.CalendarRepo;
import ca.team3.laps.repository.LeaveRepository;
import ca.team3.laps.repository.StaffRepo;
import ca.team3.laps.service.LeaveService;
import ca.team3.laps.service.StaffService;

@SpringBootApplication
public class LapsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LapsApplication.class, args);
	}

	// @Bean
	// public WebClient webClient(WebClient.Builder webClientBuilder) {
	// 	return webClientBuilder
	// 			.baseUrl("https://holidayapi.com/v1/holidays")
	// 			.build();
	// }

	// @Bean
	// public CommandLineRunner run(WebClient webClient, CalendarRepo calendarRepo) {
	// 	String key = "e7d34671-7d69-495c-bd74-f4027f7d1127";
	// 	String country = "SG";
	// 	String year = "2021";
	// 	return args -> {
	// 		Mono<HolidayAPIResponse> response = webClient.get()
	// 				.uri(UriBuilder -> UriBuilder
	// 						.queryParam("pretty")
	// 						.queryParam("public")
	// 						.queryParam("key", key)
	// 						.queryParam("country", country)
	// 						.queryParam("year", year)
	// 						.build())
	// 				.retrieve()
	// 				.bodyToMono(HolidayAPIResponse.class);
	// 		List<Holiday> holidays = response.block().getHolidays();
	// 		holidays.forEach( holiday -> calendarRepo.save(holiday) );
	// 	};
	// }

	// Leave leave1 = new Leave("AA001", "medical leave", LocalDate.now(), LocalDate.now().plusDays(10), "PENDING", "something", "programmer", javis);
	// 		leaveRepository.saveAndFlush(leave1);

	@Bean
	public CommandLineRunner run(LeaveRepository leaveRepository, StaffRepo staffRepo,LeaveService leaveService, CalendarRepo calendarRepo, StaffService staffService){
		return (args)->{

			// Staff javis = staffRepository.save(new Staff("A001", null, "Javis", "password", 3, "alrigh", "javis", "john", true, "Javis@gmail.com", 5, 10, 1 ));
			
			Staff subo= staffRepo.save(new Staff(1, "manager", "password", 2, "programmer", "shan", "mon", true, "shan@gmail.com", "otp", 10f, 60, 60, 60, null, null, null,null));
			
			Staff javis = staffRepo.save(new Staff(2, "shanmon", "password", 3, "programmer", "shan", "mon", true, "shan@gmail.com", "otp", 10f, 60, 60, 60, null, subo, null, null));
			Leave leave1 = new Leave(LeaveTypeEnum.MEDICAL_LEAVE, LocalDate.now(), LocalDate.now().plusDays(15), LocalDate.now().plusDays(10).getDayOfYear()-LocalDate.now().plusDays(5).getDayOfYear(),LeaveStatusEnum.SUBMITTED, "null", "null", javis);
			leaveRepository.saveAndFlush(leave1);
			Leave leave2 = new Leave(LeaveTypeEnum.MEDICAL_LEAVE, LocalDate.now().plusDays(2), LocalDate.now().plusDays(4), 2, LeaveStatusEnum.SUBMITTED, "null", "null", javis);
			leaveRepository.saveAndFlush(leave2);
			// LeaveHistoryDisplay leave3 = new LeaveHistoryDisplay(leave2.getId(), "annnnn", LocalDate.now(), LocalDate.now().plusDays(15), 1, "Pending", null, null);


			// System.out.println(leave1);
			// List<LeaveHistoryDisplay> leaves = leaveService.leaveHistory(1);
			// for(LeaveHistoryDisplay l: leaves){
			// 	System.out.println(l);
			// }

			// leaveService.updateLeaveHistory(leave3.getId(), leave3);
			
			LocalDate date1 = LocalDate.now().plusDays(2);
			LocalDate date2 = LocalDate.now().plusDays(4);
			List<Holiday> dates = calendarRepo.findByYear(date1.getYear());
			int count = 0;
			for(int i = 0; i< dates.size(); i++ ){
				if((dates.get(i).getDate().isEqual(date1) || dates.get(i).getDate().isAfter(date1)) && (dates.get(i).getDate().isEqual(date2) || dates.get(i).getDate().isBefore(date2))){
					count++;
				}
			}
			int difference = date2.getDayOfYear()-date1.getDayOfYear();
			System.out.println(difference-count);

			long test = date2.toEpochDay();
			long test1 = date1.toEpochDay();
			System.out.println(test);
			System.out.println(test1);
			System.out.println(test-test1);
			Leave leaveHistoryDisplay = new Leave();
			leaveHistoryDisplay.setStartDate(LocalDate.now());
			leaveHistoryDisplay.setEndDate(LocalDate.now().plusDays(10));
			leaveHistoryDisplay.setType(LeaveTypeEnum.MEDICAL_LEAVE);
			leaveHistoryDisplay.setPeriod(2);
			leaveHistoryDisplay.setStatus(LeaveStatusEnum.SUBMITTED);
			leaveHistoryDisplay.setReason(null);
			leaveHistoryDisplay.setWork(null);

			leaveService.createLeaveHistory(1, leaveHistoryDisplay);
			// Staff staff = leaveService.getStaffWithStaffId(1);

			// ok


			List<Staff> staff = leaveService.getSubordinate(1);
			if(staff != null){

				System.out.println("ok");
			}
			for(Staff s: staff){
				System.out.println(s.getUsername());
			}

			
			// Staff testss = staffRepo.save(new Staff(3, "test", "password", 3, "programmer", "shan", "mon", true, "shan@gmail.com", "otp", 10f, 60, 60, 60, null, subo, null));
			// staffService.createStaff(testss);
			// Leave leaveHistoryDisplay1 = new Leave();
			// leaveHistoryDisplay1.setId(3);
			// leaveHistoryDisplay1.setType(LeaveTypeEnum.MEDICAL_LEAVE);
			// leaveHistoryDisplay1.setStatus(LeaveStatusEnum.APPROVED);

			// Leave lll = leaveService.approveLeave(leaveHistoryDisplay1);

			// Staff staffff = leaveService.getStaffWithStaffId(1);
			// System.out.println(staffff.getMediLeave());
			// System.out.println(javis.getMediLeave());
			// System.out.println(lll.getStatus());
		};
	}

}
