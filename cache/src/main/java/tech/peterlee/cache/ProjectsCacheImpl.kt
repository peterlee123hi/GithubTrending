package tech.peterlee.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import tech.peterlee.cache.db.ProjectsDatabase
import tech.peterlee.cache.mapper.CachedProjectMapper
import tech.peterlee.data.model.ProjectEntity
import tech.peterlee.data.repository.ProjectsCache
import javax.inject.Inject

class ProjectsCacheImpl @Inject constructor(
        private val projectsDatabase: ProjectsDatabase,
        private val mapper: CachedProjectMapper
): ProjectsCache {

    override fun clearProjects(): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectDao().insertProjects(
                    projects.map {
                        mapper.mapToCached(it)
                    }
            )
            Completable.complete()
        }
    }

    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectDao().getBookmarkedProjects()
                .map {
                    it.map {
                        mapper.mapFromCached(it)
                    }
                }
    }

    override fun getBookmarkedProjects(): Flowable<List<ProjectEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun areProjectsCached(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isProjectsCacheExpired(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}