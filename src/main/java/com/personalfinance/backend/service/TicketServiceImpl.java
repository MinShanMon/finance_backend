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
import com.personalfinance.backend.repo.EnquiryRepository;
import com.personalfinance.backend.repo.TicketRepository;
 
@Service

public class TicketServiceImpl implements TicketService {
    @Resource
    private TicketRepository tikRepository;
    @Resource
    private EnquiryRepository enqRepository;
 
    @Autowired 
    private JavaMailSender mailSender;
 
    @Override
    public Ticket saveTik(Ticket tik) {
        return tikRepository.save(tik);
    }

    @Override
    public Ticket updateTik(Ticket updateTik,Integer id){
       Ticket tik = tikRepository.findById(id).get();
        if(tik != null &&
        tik.getTikStatus() == TicketStatusEnum.OPEN){
            tik.setReply(updateTik.getReply());
            tik.setTikStatus(updateTik.getTikStatus());
            updateTik.getReply_dateTime();
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
            String subject = "We have received your enquiry -- FinanceManagementApp";
            String content = "Dear " + enq.getTitle()+". "+ enq.getName() + ",\n\n" 
	        +enq.getTicket().getReply()+"\n\nBest regards,\nFinancial Management Team";
 
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

    // Method 2
    // To send an email with attachment
    // public String
    // sendMailWithAttachment(EmailDetails details)
    // {
    //     // Creating a mime message
    //     MimeMessage mimeMessage
    //         = javaMailSender.createMimeMessage();
    //     MimeMessageHelper mimeMessageHelper;
 
    //     try {
 
    //         // Setting multipart as true for attachments to
    //         // be send
    //         mimeMessageHelper
    //             = new MimeMessageHelper(mimeMessage, true);
    //         mimeMessageHelper.setFrom(sender);
    //         mimeMessageHelper.setTo(details.getRecipient());
    //         mimeMessageHelper.setText(details.getMsgBody());
    //         mimeMessageHelper.setSubject(
    //             details.getSubject());
 
    //         // Adding the attachment
    //         FileSystemResource file
    //             = new FileSystemResource(
    //                 new File(details.getAttachment()));
 
    //         mimeMessageHelper.addAttachment(
    //             file.getFilename(), file);
 
    //         // Sending the mail
    //         javaMailSender.send(mimeMessage);
    //         return "Mail sent Successfully";
    //     }
 
    //     // Catch block to handle MessagingException
    //     catch (MessagingException e) {
 
    //         // Display message when exception occurred
    //         return "Error while sending mail!!!";
    //     }
   // }
}