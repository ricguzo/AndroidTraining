package com.ricguzo.dagger_mvp_retrofit;

import com.ricguzo.dagger_mvp_retrofit.data.DataRepository;
import com.ricguzo.dagger_mvp_retrofit.data.GitHubService;
import com.ricguzo.dagger_mvp_retrofit.data.entities.Repository;
import com.ricguzo.dagger_mvp_retrofit.ui.home.HomeContract;
import com.ricguzo.dagger_mvp_retrofit.ui.home.HomePresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ricgu on 05/05/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    @Mock
    private HomePresenter homePresenter;

    @Mock
    GitHubService gitHubService;

    @InjectMocks
    DataRepository dataRepository;

    @Mock
    HomeContract.View view;

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }


    @Before
    public void setUp(){
        homePresenter = new HomePresenter(dataRepository);
        homePresenter.attachView(view);

        List<Repository> repositories = new ArrayList<>();
        Repository repository1 = new Repository(1,"Test1");
        Repository repository2 = new Repository(2,"Test2");
        Repository repository3 = new Repository(3,"Test3");
        Repository repository4 = new Repository(4,"Test4");
        Repository repository5 = new Repository(5,"Test5");
        repositories.add(repository1);
        repositories.add(repository2);
        repositories.add(repository3);
        repositories.add(repository4);
        repositories.add(repository5);

        when(gitHubService.retrieveRepositories()).thenReturn(Single.just(repositories));
    }

    @After
    public void tearDown(){
        homePresenter.detachView();
        homePresenter=null;
    }

    @Test
    public void testLoadData(){
        homePresenter.loadData();
    }

}
