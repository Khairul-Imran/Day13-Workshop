package sg.edu.nus.iss.Day13Workshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.Day13Workshop.Model.Contact;
import sg.edu.nus.iss.Day13Workshop.Repo.ContactsRepo;
import sg.edu.nus.iss.Day13Workshop.Service.FileService;

@Controller
@RequestMapping("/contact")
public class ContactController {

  @Autowired ContactsRepo contactsRepo;
  @Autowired FileService fileService;

  @GetMapping("/list")
  public String contactList(Model model) {
    List<Contact> contacts = contactsRepo.findAll();

    model.addAttribute("contacts", contacts);

    return "contactlist";
  }

  @GetMapping("/contactupdate/{email}")
  public String updateContact(@PathVariable("email") String email, Model model) {
    Contact contact = contactsRepo.findByEmail(email);
    model.addAttribute("contact", contact);
    fileService.updateContactFile(contact);

    return "contactupdate";
  }

  @PostMapping("/submitupdatedcontact")
  public String updateContactRecord(@ModelAttribute("contact") Contact contact, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "contactupdate";
    }

    contactsRepo.updateContact(contact);
    fileService.updateContactFile(contact);
    
    return "redirect:/contact/list";
  }

  @GetMapping("/contactdelete/{email}")
  public String deleteContact(@PathVariable("email") String email) {
    Contact contact = contactsRepo.findByEmail(email);
    Boolean result = contactsRepo.deleteContact(contact);
    fileService.deleteContactFile(contact);

    return "redirect:/contact/list";
  }



}
