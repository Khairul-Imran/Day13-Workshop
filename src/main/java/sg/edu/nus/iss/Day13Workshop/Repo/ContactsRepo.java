package sg.edu.nus.iss.Day13Workshop.Repo;

import java.io.FileNotFoundException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.Day13Workshop.Model.Contact;
import sg.edu.nus.iss.Day13Workshop.Service.ContactIdService;
import sg.edu.nus.iss.Day13Workshop.Service.FileService;

@Repository
public class ContactsRepo {

  @Autowired ContactIdService contactIdService;
  @Autowired FileService fileService;

  // final String directoryPath = "/Users/khairulimran/data";
  private List<Contact> contacts;


  // Constructor.
  public ContactsRepo() throws ParseException{
    if (contacts == null) {
      contacts = new ArrayList<>();
    }
    // Hard coding the data temporarily. 
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date dt = df.parse("1999-09-09");

    Contact contact = new Contact("Bob Tan", "BobTan@gmail.com", "99998888", dt);
    contacts.add(contact);

    contact = new Contact("Ronaldo", "CR7@gmail.com", "77777777", dt);
    contacts.add(contact);

    // fileService.createContactsDirectory();
  }


  // Methods.
  public List<Contact> findAll() {
    return contacts;
  }

  public Contact findByEmail(String email) {
    return contacts.stream().filter(cont -> cont.getEmail().equals(email)).findFirst().get(); // .get() converts it back to a contact object.
  }

  public Boolean deleteContact(Contact contact) {
    Boolean result = false;
    int contactIndex = contacts.indexOf(contact);

    if (contactIndex >= 0) { // Contact exists.
      contacts.remove(contactIndex);

      fileService.deleteContactFile(contact);
      result = true;
    }
    return result;
  }

  public Boolean updateContact(Contact contact) {
    Boolean result = false;

    // Retrieves the object originally in storage.
    Contact contactObject = contacts.stream().filter(cont -> cont.getEmail().equals(contact.getEmail())).findFirst().get(); // Need to fully understand this lol.
    int contactIndex = contacts.indexOf(contactObject);

    if (contactIndex >= 0) { // Contact exists.
      // Perform the updates.
      contacts.get(contactIndex).setName(contact.getName());
      contacts.get(contactIndex).setEmail(contact.getEmail());
      contacts.get(contactIndex).setPhoneNumber(contact.getPhoneNumber());
      contacts.get(contactIndex).setBirthday(contact.getBirthday());

      fileService.updateContactFile(contact); // This takes the most updated contact.
      result = true;
    }
    return result;
  }

  public Boolean saveContact(Contact contact) throws FileNotFoundException {
    Boolean result = false;

    contactIdService.assignContactId(contact); // Should set the id.
    result = contacts.add(contact);
    fileService.createContactFile(contact);

    return result;
  }
  
}
