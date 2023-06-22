package hello.external;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OsEnv {

	public static void main(String[] args) {
		Map<String, String> getenv = System.getenv();
		for (String key : getenv.keySet()) {
			log.info("env {}={}", key, System.getenv());
		}

		// DBURL=dev.db.com 개발서버
		// DBURL=prod.db.com 운영서버
		System.getenv("DBURL");
	}
}
