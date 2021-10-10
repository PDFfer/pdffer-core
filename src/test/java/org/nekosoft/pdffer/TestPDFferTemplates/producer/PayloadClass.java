package org.nekosoft.pdffer.TestPDFferTemplates.producer;

public class PayloadClass {
    private String name;
    private int value;
    private boolean valid;

    public PayloadClass(String name, int value, boolean valid) {
        this.name = name;
        this.value = value;
        this.valid = valid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "PayloadClass{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", flag=" + valid +
                '}';
    }
}
