package com.gempukku.libgdx.jam.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.gempukku.libgdx.jam.JamDec2020;

/**
 * Launches the GWT application.
 */
public class GwtLauncher extends GwtApplication {
    @Override
    public GwtApplicationConfiguration getConfig() {
        ShaderProgram.prependFragmentCode = "#version 100\n";

        // Resizable application, uses available space in browser
        return new GwtApplicationConfiguration(true);
        // Fixed size application:
        //return new GwtApplicationConfiguration(480, 320);
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new JamDec2020();
    }
}
