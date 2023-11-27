package sg.edu.nus.iss.Day13Workshop.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.Day13Workshop.Model.Contact;

@Service
public class FileService {

  private static final String CONTACTS_DIRECTORY_PATH = "/Users/khairulimran/data/contacts";
  // private static final String CONTACTS_DIRECTORY = "contacts";

  public void createContactsDirectory() { // Might not need this.
    File directory = new File(CONTACTS_DIRECTORY_PATH + "/contacts");
    if (!directory.exists()) {
      directory.mkdirs();
    }
  }

  public void createContactFile(Contact contact) {
    String fileName = CONTACTS_DIRECTORY_PATH + File.separator + contact.getContactId() + ".txt";
    File contactFile = new File(fileName);

    try(BufferedWriter bw = new BufferedWriter(new FileWriter(contactFile))) {
      String formattedData = contact.formatForWritingToFile();
      bw.write(formattedData);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void updateContactFile(Contact contact) {
    String fileName = CONTACTS_DIRECTORY_PATH + File.separator + contact.getContactId() + ".txt";
    File contactFile = new File(fileName);

    if (contactFile.exists()) {
      // Rewrites the contents.
      try(BufferedWriter bw = new BufferedWriter(new FileWriter(contactFile))) {
        String formattedData = contact.formatForWritingToFile();
        bw.write(formattedData);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("Contact file not found.");
    }
  }

  public void deleteContactFile(Contact contact) {
    String fileName = CONTACTS_DIRECTORY_PATH + File.separator + contact.getContactId() + ".txt";
    try {
      Files.deleteIfExists(Paths.get(fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
