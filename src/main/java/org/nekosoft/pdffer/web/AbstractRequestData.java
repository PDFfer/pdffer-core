package org.nekosoft.pdffer.web;

import java.util.Map;

/**
 * A DTO with the payload for a PDF template. This class is used as a base class to define
 * the structure of the JSON messages that can be sent to the PDFfer endpoints in order to
 * generate PDFs on the fly through the PDFfer web endpoints.
 */
public abstract class AbstractRequestData {
	private Map<String, Object> payload;

	/**
	 * The payload data that the PDF template will use to generate the PDF.
	 *
	 * @return the payload data
	 */
    public Map<String, Object> getPayload() {
		return payload;
	}

	/**
	 * Sets payload data that the PDF template will use to generate the PDF.
	 *
	 * @param payload the payload data
	 */
	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}
}
