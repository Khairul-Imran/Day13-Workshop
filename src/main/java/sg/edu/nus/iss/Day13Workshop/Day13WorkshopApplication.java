package sg.edu.nus.iss.Day13Workshop;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.Day13Workshop.Service.FileService;

@SpringBootApplication
public class Day13WorkshopApplication implements ApplicationRunner{

	@Autowired FileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(Day13WorkshopApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (args.containsOption("dataDir")) {
			final String dataDir = args.getOptionValues("dataDir").get(0); // To create

			File fileDir = new File(dataDir);

			if (!fileDir.exists()) {
				fileDir.mkdir();
				System.out.println("***" + fileDir.getAbsolutePath());
				System.out.println("***" + fileDir.getPath());
				System.out.println("***" + fileDir.getParent());
			} else {
				System.out.println(fileDir.getAbsolutePath());
			}
		}
	}

}
