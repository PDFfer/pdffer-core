package org.nekosoft.pdffer.web;

/**
 * A DTO for sending HTTP request to download a PDF from PDFfer.
 */
public class DownloadRequestData extends AbstractRequestData {
	private String filename;

	/**
	 * Gets the name of the file for the PDF to be downloaded.
	 *
	 * @return the filename
	 */
    public String getFilename() {
		return filename;
	}

	/**
	 * Sets the name of the file for the PDF to be downloaded.
	 *
	 * @param filename the filename
	 */
    public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
