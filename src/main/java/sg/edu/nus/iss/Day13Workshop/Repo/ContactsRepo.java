package sg.edu.nus.iss.Day13Workshop.Repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.Day13Workshop.Model.Contact;
import sg.edu.nus.iss.Day13Workshop.Service.ContactIdService;

@Repository
public class ContactsRepo {

  @Autowired ContactIdService contactIdService;

  final String directoryPath = "/Users/khairulimran/data";
  final String fileName = "";
  private List<Contact> contacts;


  // To add constructor.


  // To add other methods.

  
  public Boolean save(Contact contact) {
    Boolean result = false;

    contactIdService.assignContactId(contact); // Should set the id.
    result = contacts.add(contact);

    // To insert file stuff.


    return result;
  }
  
}
