package mvc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

// AppPanel is the MVC controller
public class AppPanel extends JPanel implements Subscriber, ActionListener  {

    protected Model model;
    protected AppFactory factory;
    protected View view;
    protected JPanel controlPanel;
    private JFrame frame;
    public static int FRAME_WIDTH = 900;
    public static int FRAME_HEIGHT = 600;

    protected List<String> history = new ArrayList<> ();

    public AppPanel(AppFactory factory) {

        // initialize fields here
        this.factory = factory;
        this.model = factory.makeModel();
        this.view = factory.makeView(model);
        view.setBackground((Color.GRAY));
        controlPanel = new JPanel();
        controlPanel.setBackground((Color.PINK));
        this.add(controlPanel);
        this.add(view);
        this.setLayout(new GridLayout(1, 2));
        model.subscribe(this);

        String[] commands = factory.getEditCommands();
        for (String cmd : commands) {
            JButton button = new JButton(cmd);
            button.addActionListener(this);
            controlPanel.add(button);
        }


        frame = new JFrame();
        frame.setContentPane(this);
        frame.setJMenuBar(createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void display() {
        //System.out.println("Calling setVisible(true)...");
        frame.setVisible(true);
        //System.out.println("Visible set");
    }

    public void update() {
        view.repaint();
    }

    public Model getModel() { return model; }

    // called by file/open and file/new
    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        // view must also unsubscribe then resubscribe:
        view.setModel(this.model);
        model.changed();
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[] {"New",  "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu =
                Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[] {"About", "Help", "History"}, this);
        result.add(helpMenu);

        return result;
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String cmmd = ae.getActionCommand();

            if (cmmd.equals("Save")) {
                Utilities.save(model, false);
            } else if (cmmd.equals("SaveAs")) {
                Utilities.save(model, true);
            } else if (cmmd.equals("Open")) {
                Model newModel = Utilities.open(model);
                if (newModel != null) setModel(newModel);
            } else if (cmmd.equals("New")) {
                Utilities.saveChanges(model);
                setModel(factory.makeModel());
                // needed cuz setModel sets to true:
                model.setUnsavedChanges(false);
            } else if (cmmd.equals("Quit")) {
                Utilities.saveChanges(model);
                System.exit(0);
            } else if (cmmd.equals("About")) {
                Utilities.inform(factory.about());
            } else if (cmmd.equals("Help")) {
                Utilities.inform(factory.getHelp());
            } else if(cmmd.equals("History")) {
                Utilities.inform(history.toArray(new String[history.size()]));
            } else { // must be from Edit menu
                String[] arr = factory.getEditCommands();
                for (String s : arr) {
                    if (cmmd.equals(s)) {
                        System.out.println("[AppPanle] Executing command: " + s);
                        Command command = factory.makeEditCommand(model, s, ae.getSource());
                        command.execute();
                    }
                }
            }
            history.add(cmmd);
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }

}
