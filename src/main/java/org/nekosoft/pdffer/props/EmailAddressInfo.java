package org.nekosoft.pdffer.props;

/**
 * A Property POJO part that represents an email address.
 */
public class EmailAddressInfo {
    // field declarations
    private final String name;
    private final String address;

    /**
     * Instantiates new email address info.
     *
     * @param name    the name of the owner of the address
     * @param address the email address
     */
    public EmailAddressInfo(String name, String address) {
        // set field values
        this.name = name;
        this.address = address;
    }

    // getters only

    /**
     * Gets the name of the owner of the address.
     *
     * @return the name of the owner of the address
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email address.
     *
     * @return the email address
     */
    public String getAddress() {
        return address;
    }
}
