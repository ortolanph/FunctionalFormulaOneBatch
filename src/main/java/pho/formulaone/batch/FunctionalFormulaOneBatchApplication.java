package pho.formulaone.batch;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.Duration;

@SpringBootApplication
public class FunctionalFormulaOneBatchApplication {

	@Value("${ergast.base.url}")
	private String ergastBaseURL;

	private static final Duration ONE_MINUTE = Duration.ofMinutes(1);

	public static void main(String[] args) {
		SpringApplication.run(FunctionalFormulaOneBatchApplication.class, args);
	}

	@Bean
	public Retrofit ergastAPI() {
		return new Retrofit.Builder()
				.baseUrl(ergastBaseURL)
				.client(okHttpClient())
				.addConverterFactory(JacksonConverterFactory.create())
				.build();
	}

	private OkHttpClient okHttpClient() {
		return new OkHttpClient
				.Builder()
				.connectTimeout(ONE_MINUTE)
				.readTimeout(ONE_MINUTE)
				.build();
	}
}
