package tech.peterlee.cache

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import tech.peterlee.cache.db.ProjectsDatabase
import tech.peterlee.cache.mapper.CachedProjectMapper
import tech.peterlee.cache.model.Config
import tech.peterlee.data.model.ProjectEntity
import tech.peterlee.data.repository.ProjectsCache
import javax.inject.Inject

class ProjectsCacheImpl @Inject constructor(
        private val projectsDatabase: ProjectsDatabase,
        private val mapper: CachedProjectMapper
): ProjectsCache {

    override fun clearProjects(): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().insertProjects(
                    projects.map {
                        mapper.mapToCached(it)
                    }
            )
            Completable.complete()
        }
    }

    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getProjects()
                .map {
                    it.map {
                        mapper.mapFromCached(it)
                    }
                }
    }

    override fun getBookmarkedProjects(): Flowable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getBookmarkedProjects()
                .map {
                    it.map {
                        mapper.mapFromCached(it)
                    }
                }
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(true, projectId)
            Completable.complete()
        }
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Single<Boolean> {
        return projectsDatabase.cachedProjectsDao().getProjects().isEmpty
                .map {
                    !it
                }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            projectsDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isProjectsCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return projectsDatabase.configDao().getConfig()
                .onErrorReturn {
                    Config(lastCacheTime = 0)
                }
                .map {
                    currentTime - it.lastCacheTime > expirationTime
                }
    }
}