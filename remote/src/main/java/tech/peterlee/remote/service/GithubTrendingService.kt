package tech.peterlee.remote.service

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import tech.peterlee.remote.model.ProjectsResponseModel

interface GithubTrendingService {

    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String,
                           @Query("sort") sortBy: String,
                           @Query("order") order: String): Flowable<ProjectsResponseModel>
}