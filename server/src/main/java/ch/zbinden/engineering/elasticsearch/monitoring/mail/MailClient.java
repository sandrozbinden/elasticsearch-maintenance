package ch.zbinden.engineering.elasticsearch.monitoring.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailClient {

	private static final String ALERT_EMAIL_TEMPLATE_NAME = "alert-email";

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Value("${alert.mail.subject}")
	private String mailSubject;
	
	@Value("${alert.mail.to}")
	private String mailTo;

	public void sendAlertMessageForQuery(String query) throws MessagingException {
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
		message.setTo(mailTo);
		message.setSubject(mailSubject);
		message.setText(getAlertMessageContent(query), true);
		this.mailSender.send(mimeMessage);
	}

	private String getAlertMessageContent(String query) {
		Context context = new Context();
        context.setVariable("query", query);
		return templateEngine.process(ALERT_EMAIL_TEMPLATE_NAME, context);
	}
}
