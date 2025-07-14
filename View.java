package mvc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class View extends JPanel implements Subscriber {
    public Model model = null;
    public View (Model m) {
        this.model = m;
        model.subscribe(this);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
    }

    public Model getModel() { return model; }

    public void setModel(Model m) {
        if (model != null) model.unsubscribe(this);
        this.model = m;
        if (model != null) {
            m.subscribe(this);
            update();
        }
    }

    @Override
    public void update() {
        this.repaint();
    }
}
