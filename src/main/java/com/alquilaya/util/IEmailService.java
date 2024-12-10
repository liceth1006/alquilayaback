package com.alquilaya.util;

public interface IEmailService {
	 void sendEmail(String sendTo, String sendFrom, String name, String subject,
             String template, String replyTo, String link) throws Exception;
}
