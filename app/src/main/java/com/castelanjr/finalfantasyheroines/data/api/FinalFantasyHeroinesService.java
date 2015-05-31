package com.castelanjr.finalfantasyheroines.data.api;

import com.castelanjr.finalfantasyheroines.data.api.model.HeroinesResponse;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by angelocastelanjr on 5/30/15.
 */
public interface FinalFantasyHeroinesService {

    /**
     * This is just a static JSON I've put together myself. Hey, this is just for fun!
     *
     * @return An observable of the heroines returned by the service
     */
    @GET("/bins/oapk")
    Observable<HeroinesResponse> getHeroines();

}
