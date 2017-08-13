
package com.assignment.zolostays.application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by Rashmi on 12/08/17.
 */

@Component(modules = { AndroidInjectionModule.class, ZoloStaysApplicationModule.class, ZoloStaysAppProviderModule.class })
public interface ZoloStaysApplicationComponent extends AndroidInjector<ZoloStaysApplication> {
    void inject(ZoloStaysApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder applicationModule(ZoloStaysAppProviderModule applicationModule);

        ZoloStaysApplicationComponent build();
    }
}
