package com.movile.next.tvON.application;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class tvONApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //FlowManager.init(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

}
