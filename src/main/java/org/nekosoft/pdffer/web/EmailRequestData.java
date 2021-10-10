package org.nekosoft.pdffer.web;

import java.util.List;

/**
 * A DTO for sending HTTP request to download a PDF from PDFfer.
 */
public class EmailRequestData extends AbstractRequestData {
	private List<String> emailTo;
	private String subject;
	private String message;
	private String filename;
	private String sendFrom;
	private String replyTo;

	/**
	 * Gets the email address to send the PDF to.
	 *
	 * @return the email address
	 */
    public List<String> getEmailTo() {
		return emailTo;
	}

	/**
	 * Sets the email address to send the PDF to.
	 *
	 * @param emailTo the email address
	 */
    public void setEmailTo(List<String> emailTo) {
		this.emailTo = emailTo;
	}

	/**
	 * Gets the subject of the email that will contain the PDF.
	 *
	 * @return the email subject
	 */
    public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject of the email that will contain the PDF.
	 *
	 * @param subject the email subject
	 */
    public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the filename that will be used for the attachment.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Gets the main message of the email that will contain the PDF.
	 *
	 * @return the message
	 */
    public String getMessage() {
		return message;
	}

	/**
	 * Sets the main message of the email that will contain the PDF.
	 *
	 * @param message the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Sets the filename that will be used for the attachment.
	 *
	 * @param filename the filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * Gets the send-from email address in full RFC 6854 format. This overrides the setting in the PDFfer configuration.
	 *
	 * @return the send-from email address
	 */
	public String getSendFrom() {
		return sendFrom;
	}

	/**
	 * Sets the send-from email address in full RFC 6854 format. This overrides the setting in the PDFfer configuration.
	 *
	 * @param sendFrom the send-from email address
	 */
	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}

	/**
	 * Gets the reply-to email address in full RFC 6854 format. This overrides the setting in the PDFfer configuration.
	 *
	 * @return the reply-to email address
	 */
	public String getReplyTo() {
		return replyTo;
	}

	/**
	 * Sets the reply-to email address in full RFC 6854 format. This overrides the setting in the PDFfer configuration.
	 *
	 * @param replyTo the reply-to email address
	 */
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
}
