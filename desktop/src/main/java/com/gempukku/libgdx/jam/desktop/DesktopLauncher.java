package com.gempukku.libgdx.jam.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.gempukku.libgdx.jam.JamDec2020;

/**
 * Launches the desktop (LWJGL) application.
 */
public class DesktopLauncher {
    public static void main(String[] args) {
        createApplication();
    }

    private static LwjglApplication createApplication() {
        return new LwjglApplication(new JamDec2020(), getDefaultConfiguration());
    }

    private static LwjglApplicationConfiguration getDefaultConfiguration() {
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title = "December 2020 libGDX Jam";
        configuration.width = 640;
        configuration.height = 480;
        //// This prevents a confusing error that would appear after exiting normally.
        configuration.forceExit = false;
        ShaderProgram.prependFragmentCode = "#version 120\n";

        for (int size : new int[]{128, 64, 32, 16}) {
            configuration.addIcon("libgdx" + size + ".png", FileType.Internal);
        }
        return configuration;
    }
}