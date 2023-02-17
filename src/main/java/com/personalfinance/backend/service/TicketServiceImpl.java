package com.personalfinance.backend.service;


import java.time.LocalDateTime;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.personalfinance.backend.exception.ResourceNotFoundException;
import com.personalfinance.backend.model.Enquiry;
import com.personalfinance.backend.model.Ticket;
import com.personalfinance.backend.model.TicketStatusEnum;
import com.personalfinance.backend.repository.EnquiryRepository;
import com.personalfinance.backend.repository.TicketRepository;
 
@Service

public class TicketServiceImpl implements TicketService {
    @Resource
    private TicketRepository tikRepository;
    @Resource
    private EnquiryRepository enqRepository;
 
    @Autowired 
    private JavaMailSender mailSender;

    @Override
    public Ticket addTik() {
        Ticket newTik = new Ticket();
        newTik.setTikStatus(TicketStatusEnum.OPEN);
        return tikRepository.save(newTik);
    }

    private final static String LOCAL_URL = "http://localhost:8083/customer/review/";
    private final static String CLOUD_URL = "http://adteam3frontend-env.eba-9mzsispx.ap-northeast-1.elasticbeanstalk.com/";
 
    @Override
    public Ticket saveTik(Ticket tik) {
        return tikRepository.save(tik);
    }

    @Override
    public Ticket updateTik(Ticket updateTik,Integer id){
       Ticket tik = tikRepository.findById(id).get();
        if(tik != null ){
            tik.setReply(updateTik.getReply());
            tik.setTikStatus(updateTik.getTikStatus());
            tik.setReply_dateTime(LocalDateTime.now());
            return tikRepository.saveAndFlush(tik);
        }
        return tikRepository.save(tik);
    }


   @Override
    public boolean sendEmail(Integer id) throws ResourceNotFoundException {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            Enquiry enq = enqRepository.findById(id).get();
            String subject = "We have received your enquiry -- Finance Management Team";
            String content = "Dear " + enq.getName() + ",\n\n" 
	        +enq.getTicket().getReply()+"\n\nBest regards,\nFinance Management Team";
 
            // Setting up necessary details
            mailMessage.setFrom("ademailapi@gmail.com");
            mailMessage.setTo(enq.getEmail());
            mailMessage.setText(content);
            mailMessage.setSubject(subject);
 
            // Sending the mail
            mailSender.send(mailMessage);
            return true; 
        }
        catch (Exception e) {
                return false;
        }
    }

    @Override
    public boolean sendReview(Integer id) {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            Enquiry enq = enqRepository.findById(id).get();
            String subject = "Please rate our service -- Finance Management Team";
            String content = "Dear " + enq.getName() + ",\n\n" 
            +"Please kindly review our service follows the link below:\n"
            + LOCAL_URL + enq.getId()+ "\nWe value your feedback sincerely. Thanks a lot!"+
            "\n\nBest regards,\nFinance Management Team";

        // Setting up necessary details
            mailMessage.setFrom("ademailapi@gmail.com");
            mailMessage.setTo(enq.getEmail());
            mailMessage.setText(content);
            mailMessage.setSubject(subject);

        // Sending the mail
            mailSender.send(mailMessage);
            return true; 
        }
        catch (Exception e) {
            return false;
        }
    }

}

   