package com.personalfinance.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;
import com.personalfinance.backend.model.Enquiry;
import com.personalfinance.backend.model.EnquiryTypeEnum;
import com.personalfinance.backend.model.SalutationEnum;
import com.personalfinance.backend.model.Ticket;
import com.personalfinance.backend.model.TicketStatusEnum;
import com.personalfinance.backend.repo.EnquiryRepository;
import com.personalfinance.backend.repo.TicketRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(EnquiryRepository enqRepository, TicketRepository tikRepository) {
		return args -> {

			Ticket tik1 = tikRepository.saveAndFlush(new Ticket("http://www.google.com",TicketStatusEnum.OPEN, LocalDateTime.now()));
			tikRepository.saveAndFlush(tik1);

			Ticket tik2 = tikRepository.saveAndFlush(new Ticket("FIND THIS",TicketStatusEnum.OPEN,LocalDateTime.now().minusDays(3)));
			tikRepository.saveAndFlush(tik2);

			Ticket tik3 = tikRepository.saveAndFlush(new Ticket("CALL THIS NUMBER",TicketStatusEnum.CLOSED,LocalDateTime.now().minusDays(8)));
			tikRepository.saveAndFlush(tik3);

			Ticket tik4 = tikRepository.saveAndFlush(new Ticket("GOOD",TicketStatusEnum.CLOSED,LocalDateTime.now().minusDays(10)));
			tikRepository.saveAndFlush(tik4);

			Ticket tik5 = tikRepository.saveAndFlush(new Ticket("GOOD",TicketStatusEnum.CLOSED,LocalDateTime.now().minusDays(18)));
			tikRepository.saveAndFlush(tik5);


			Enquiry enq1 = enqRepository.saveAndFlush(new Enquiry(EnquiryTypeEnum.PRODUCT, SalutationEnum.MS,"Adele Tan",
			"blissyetbloom@gmail.com", "12345678", "how to buy fet",LocalDateTime.now(), 4,"good",tik1));
			enqRepository.saveAndFlush(enq1);

			Enquiry enq2 = enqRepository.saveAndFlush(new Enquiry(EnquiryTypeEnum.FEEDBACK, SalutationEnum.MRS, "KIM Jean",
			"blissyetbloom@gmail.com", "556789", "Good service", LocalDateTime.now().minusDays(3),5,"good",tik2));
			enqRepository.saveAndFlush(enq2);
			
			
			Enquiry enq3 = enqRepository.saveAndFlush(new Enquiry(EnquiryTypeEnum.ACCOUNT,SalutationEnum.MR, "JOHN Tan",
			"blissyetbloom@gmail.com", "000000", "how to register account",LocalDateTime.now().minusDays(8), 3,"good",tik3));
			enqRepository.saveAndFlush(enq3);

			Enquiry enq4 = enqRepository.saveAndFlush(new Enquiry(EnquiryTypeEnum.FEEDBACK, SalutationEnum.MRS, "KIM Jean",
			"456@gmail.com", "556789", "bad service", LocalDateTime.now().minusDays(10),1,"good",tik4));
			enqRepository.saveAndFlush(enq4);

			Enquiry enq5 = enqRepository.saveAndFlush(new Enquiry(EnquiryTypeEnum.PRODUCT, SalutationEnum.MS,"Davie Hans",
			"000@gmail.com", "000000", "how to buy fet",LocalDateTime.now().minusDays(20).minusYears(2), 4,"good",tik5));
			enqRepository.saveAndFlush(enq5);
			
		};

	}

}
