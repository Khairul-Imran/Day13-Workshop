package sg.edu.nus.iss.Day13Workshop.Service;

import java.security.SecureRandom;

import sg.edu.nus.iss.Day13Workshop.Model.Contact;

public class ContactIdService {

  public Contact assignContactId(Contact contact) {
    String generatedContactId = generateContactId();
    contact.setContactId(generatedContactId);
    return contact;
  }

  public String generateContactId() {
    SecureRandom random = new SecureRandom();
    byte[] bytes = new byte[4];
    random.nextBytes(bytes);
    return convertBytesToHex(bytes);
  }

  public String convertBytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString().substring(0, 8);
  }
}
