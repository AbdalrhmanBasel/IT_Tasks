import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLToClass {

    public static void main(String[] args) {

        // Object
        try {

            File file = new File("Bank.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Bank.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Bank bank= (Bank) jaxbUnmarshaller.unmarshal(file);

            System.out.println(bank.getId()+" "+bank.getClient());
            System.out.println(bank.getPassport());

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
