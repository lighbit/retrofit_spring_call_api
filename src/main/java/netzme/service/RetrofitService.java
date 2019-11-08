package netzme.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service("retrofitService")
public class RetrofitService {

	private final String BASE_URL = "https://api.github.com/";

	// TODO: SET URL RANDOM USER
	private final String BASE_URL_PERSON = "https://randomuser.me/";

	private Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create());

	private Retrofit retrofit = builder.build();

	private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

	private HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);

	public <S> S createService(Class<S> serviceClass) {
		if (!httpClient.interceptors().contains(logging)) {
			httpClient.addInterceptor(logging);
			builder.client(httpClient.build());
			retrofit = builder.build();
		}
		return retrofit.create(serviceClass);
	}

	public <S> S createService(Class<S> serviceClass, final String token) {
		if (token != null) {
			httpClient.interceptors().clear();
			httpClient.addInterceptor(new Interceptor() {
				public Response intercept(Interceptor.Chain chain) throws IOException {
					Request original = chain.request();
					Request.Builder builder = original.newBuilder().header("Authorization", token);
					Request request = builder.build();
					return chain.proceed(request);
				}
			});
			builder.client(httpClient.build());
			retrofit = builder.build();
		}
		return retrofit.create(serviceClass);
	}

	// TODO: SET THIS.
	private Retrofit.Builder builderPerson = new Retrofit.Builder().baseUrl(BASE_URL_PERSON)
			.addConverterFactory(GsonConverterFactory.create());

	private Retrofit retrofitPerson = builder.build();
	private OkHttpClient.Builder httpClientPerson = new OkHttpClient.Builder();
	private HttpLoggingInterceptor loggingPerson = new HttpLoggingInterceptor()
			.setLevel(HttpLoggingInterceptor.Level.BASIC);

	// TODO: CALL SERVICE IN HERE.USING BASIC LEVEL FOR LOGGING IN CONSOLE.
	public <S> S createServicePerson(Class<S> serviceClass) {
		if (!httpClientPerson.interceptors().contains(loggingPerson)) {
			httpClientPerson.addInterceptor(loggingPerson);
			builderPerson.client(httpClientPerson.build());
			retrofitPerson = builderPerson.build();
		}
		return retrofitPerson.create(serviceClass);
	}

}
