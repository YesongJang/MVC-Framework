package mvc;

public class Command {
    public Model model;

    public Command (Model m) {
        this.model =m;
    }

    public void execute() throws Exception {};

}
