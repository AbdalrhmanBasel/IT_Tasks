import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Scanner;

public class ClassToXML {
    public static void main(String[] args) throws JAXBException {

        Scanner input = new Scanner(System.in);

        System.out.print("Client name: ");
        String client = input.nextLine();

        System.out.print("Passport Number: ");
        String passport = input.nextLine();

        // Object
        try {
            // New Bank Object
            Bank bank_1 = new Bank(client,passport);

            // Convert to file
            JAXBContext context = JAXBContext.newInstance(Bank.class);
            Marshaller marshaller = context.createMarshaller();

            // set flag for readable XML output in JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // marshalling an object to a file
            marshaller.marshal(bank_1, new File("Bank.xml"));

            System.out.println("File converted to XML successfully!");
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
