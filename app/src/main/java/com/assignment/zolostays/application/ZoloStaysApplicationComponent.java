
package com.assignment.zolostays.application;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by Rashmi on 12/08/17.
 */

@Component(modules = { AndroidInjectionModule.class, ZoloStaysApplicationModule.class })
public interface ZoloStaysApplicationComponent extends AndroidInjector<ZoloStaysApplication> {
}
