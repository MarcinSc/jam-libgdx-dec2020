package com.gempukku.libgdx.jam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.gempukku.libgdx.graph.loader.GraphLoader;
import com.gempukku.libgdx.graph.pipeline.PipelineLoaderCallback;
import com.gempukku.libgdx.graph.pipeline.PipelineRenderer;
import com.gempukku.libgdx.graph.pipeline.RenderOutputs;
import com.gempukku.libgdx.graph.shader.particles.GraphParticleEffects;
import com.gempukku.libgdx.graph.shader.particles.generator.PointParticleGenerator;

import java.io.InputStream;

public class JamDec2020 extends ApplicationAdapter {
    private PipelineRenderer pipelineRenderer;
    private Camera camera;

    @Override
    public void create() {
        pipelineRenderer = loadPipeline();
        camera = createCamera();
        pipelineRenderer.setPipelineProperty("Camera", camera);
        GraphParticleEffects graphParticleEffects = pipelineRenderer.getGraphParticleEffects();
        String effectId = graphParticleEffects.createEffect("Snowflakes", new PointParticleGenerator(100));
        graphParticleEffects.startEffect(effectId);
    }

    private Camera createCamera() {
        PerspectiveCamera camera = new PerspectiveCamera();
        camera.near = 0.1f;
        camera.far = 100;
        camera.position.set(0, 0, 1.5f);
        camera.up.set(0f, 1f, 0f);
        camera.lookAt(0, 0, 0);
        camera.update();

        return camera;
    }

    private PipelineRenderer loadPipeline() {
        try {
            InputStream read = Gdx.files.internal("pipeline/snowflakes.json").read();
            try {
                return GraphLoader.loadGraph(read, new PipelineLoaderCallback());
            } finally {
                read.close();
            }
        } catch (Exception exp) {
            throw new GdxRuntimeException("Unable to load pipeline");
        }
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        pipelineRenderer.render(delta, RenderOutputs.drawToScreen);
    }

    @Override
    public void dispose() {
        pipelineRenderer.dispose();
    }
}