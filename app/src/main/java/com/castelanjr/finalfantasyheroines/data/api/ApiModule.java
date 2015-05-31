package com.castelanjr.finalfantasyheroines.data.api;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import com.castelanjr.finalfantasyheroines.data.api.util.AutoParcelAdapterFactory;
import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/**
 * Created by angelocastelanjr on 5/30/15.
 */
@Module
public class ApiModule {
    static final String API_URL = "https://api.myjson.com";

    @Provides @Singleton
    Client provideClient(OkHttpClient client) {
        return new OkClient(client);
    }

    @Provides @Singleton Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(API_URL);
    }

    @Provides @Singleton
    Converter provideConverter() {
        return new GsonConverter(new GsonBuilder()
                .registerTypeAdapterFactory(new AutoParcelAdapterFactory())
                .create());
    }

    @Provides @Singleton
    RestAdapter provideRestAdapter(Endpoint endpoint, Client client, Converter converter) {
        return new RestAdapter.Builder()
                .setClient(client)
                .setEndpoint(endpoint)
                .setConverter(converter)
                .build();
    }

    @Provides @Singleton
    FinalFantasyHeroinesService provideService(RestAdapter restAdapter) {
        return restAdapter.create(FinalFantasyHeroinesService.class);
    }
}
