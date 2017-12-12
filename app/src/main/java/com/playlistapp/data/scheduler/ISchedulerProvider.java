package com.playlistapp.data.scheduler;


import io.reactivex.Scheduler;


public interface ISchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
