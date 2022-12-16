package ca.team3.laps;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ca.team3.laps.model.Leave;
import ca.team3.laps.model.LeaveHistoryDisplay;
import ca.team3.laps.model.Staff;
import ca.team3.laps.model.CalendarificAPI.Holiday;
import ca.team3.laps.repository.CalendarRepo;
import ca.team3.laps.repository.LeaveRepository;
import ca.team3.laps.repository.StaffRepo;
import ca.team3.laps.service.LeaveService;

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
	public CommandLineRunner run(LeaveRepository leaveRepository, StaffRepo staffRepo,LeaveService leaveService, CalendarRepo calendarRepo){
		return (args)->{

			// Staff javis = staffRepository.save(new Staff("A001", null, "Javis", "password", 3, "alrigh", "javis", "john", true, "Javis@gmail.com", 5, 10, 1 ));
			Staff javis = staffRepo.save(new Staff(1, "shanmon", "password", 1, "programmer", "shan", "mon", true, "shan@gmail.com", "otp", 10f, 12, 1, 1, null, null, null));

			Leave leave1 = new Leave("mc", LocalDate.now(), LocalDate.now().plusDays(15), LocalDate.now().plusDays(10).getDayOfYear()-LocalDate.now().plusDays(5).getDayOfYear(), "PENDING", "null", "null", javis);
			leaveRepository.saveAndFlush(leave1);
			Leave leave2 = new Leave("mcs", LocalDate.now(), LocalDate.now().plusDays(60), 10, "PENDING", "null", "null", javis);
			leaveRepository.saveAndFlush(leave2);
			// LeaveHistoryDisplay leave3 = new LeaveHistoryDisplay(leave2.getId(), "annnnn", LocalDate.now(), LocalDate.now().plusDays(15), 1, "Pending", null, null);


			// System.out.println(leave1);
			// List<LeaveHistoryDisplay> leaves = leaveService.leaveHistory(1);
			// for(LeaveHistoryDisplay l: leaves){
			// 	System.out.println(l);
			// }

			// leaveService.updateLeaveHistory(leave3.getId(), leave3);
			
			LocalDate date1 = LocalDate.now();
			LocalDate date2 = LocalDate.now().plusDays(367);
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
			// ok


		};
	}

}
