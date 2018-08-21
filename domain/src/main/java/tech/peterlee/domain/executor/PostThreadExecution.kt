package tech.peterlee.domain.executor

import io.reactivex.Scheduler

interface PostThreadExecution {
    val scheduler: Scheduler
}