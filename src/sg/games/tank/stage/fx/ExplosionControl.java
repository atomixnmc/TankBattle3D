package sg.games.tank.stage.fx;

import com.jme3.app.Application;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.shape.Box;
import java.io.IOException;
import sg.atom.core.lifecycle.ThortleState;

/**
 *
 * @author mifth
 */
public class ExplosionControl extends AbstractControl {

    private Vector3f position;
    private Application app;
    private Node parent;
    private float timer;
    private Geometry expl;

    public ExplosionControl(Vector3f position, Node parent, Application app) {
        this.position = position;
        this.app = app;
        this.parent = parent;
        timer = 0.1f;
        // Setup Explosion
        Box b = new Box(Vector3f.ZERO, 1f, 1f, 1f);
        expl = new Geometry("Box", b);
        Material mat_bullet = new Material(this.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat_bullet.setColor("Color", ColorRGBA.Orange);
        expl.setMaterial(mat_bullet);
        expl.setLocalTranslation(this.position);
        expl.setLocalScale(timer);
        expl.setUserData("Type", "Shit");
        this.parent.attachChild(expl);

    }

    @Override
    protected void controlUpdate(float tpf) {
        // Update only for fixed rate
        if (app.getStateManager().getState(ThortleState.class).IsUpdate()) {
            if (timer < 0.5f) {
                timer += app.getStateManager().getState(ThortleState.class).getCurrentTpf() * 3f;
                expl.setLocalScale(timer, timer, timer);
            } else {
                expl.removeFromParent();
                expl = null;
                parent.removeFromParent();
                parent.removeControl(this);
            }
        }
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        InputCapsule in = im.getCapsule(this);
        //TODO: load properties of this Control, e.g.
        //this.value = in.readFloat("name", defaultValue);
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        OutputCapsule out = ex.getCapsule(this);
        //TODO: save properties of this Control, e.g.
        //out.write(this.value, "name", defaultValue);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
}
