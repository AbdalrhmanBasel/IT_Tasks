import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Bank {
    private int id;
    private String client;
    private String passport;

    public Bank() {}

    // Constructor
    public Bank( String client, String passport) {
        this.client = client;
        this.passport = passport;
    }


    // GETTERS & SETTERS
    @XmlTransient
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
    @XmlElement
    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}