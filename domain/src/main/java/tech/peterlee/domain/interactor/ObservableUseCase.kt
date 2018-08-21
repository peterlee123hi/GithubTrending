package tech.peterlee.domain.interactor

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import tech.peterlee.domain.executor.PostThreadExecution

abstract class ObservableUseCase<T, in Params> constructor(
        private val postThreadExecution: PostThreadExecution
) {

    private val disposables = CompositeDisposable()

    protected abstract fun createObservable(params: Params? = null): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params? = null) {
        val observable = this.createObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postThreadExecution.scheduler)
        this.addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        this.disposables.add(disposable)
    }
}