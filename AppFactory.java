package mvc;

public interface AppFactory {
    default Model makeModel() {
        Model model = new Model();
        return model;
    }

    default View makeView (Model m){
        View view = new View(m);
        return view;
    }

    default AppPanel makePanel() {
        return new AppPanel(this);
    }

    default String getTitle() {
        return "Title String";
    }

    default String[] getHelp() {
        return new String[] {"String Array"};
    }

    default String about() {
        return "About String";
    }

    default String[] getEditCommands() {
        return new String[] {"String Edit Array"};
    }

    default Command makeEditCommand(Model m, String s, Object source) {
        return new Command(m);
    }


}