package hello;

import java.util.List;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommandLineBean {

	private final ApplicationArguments arguments;

	public CommandLineBean(ApplicationArguments arguments) {
		this.arguments = arguments;
	}

	@PostConstruct
	public void init(){
		log.info("source {}", List.of(arguments.getSourceArgs()));
		log.info("optionNames {}", arguments.getOptionNames());

		Set<String> optionNames = arguments.getOptionNames();
		for (String optionName : optionNames) {
			log.info("option args {}={}", optionName, arguments.getOptionValues(optionName));
		}

		// source [--url=devdb, --username=dev_user, --password=dev_pw, mode=on]
		// optionNames [password, url, username]
		// option args password=[dev_pw]
		// option args url=[devdb]
		// option args username=[dev_user]
	}
}
