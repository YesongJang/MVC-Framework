package mvc;

import java.io.Serializable;

public class Model extends Publisher implements Serializable {
    //Utilities needs these functions, add private variables implied by getters and setters

    private static final long serialVersionUID = 1L;

    private boolean unsavedChanges = false;
    private String fileName = null;


    public boolean getUnsavedChanges() {
        return unsavedChanges;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUnsavedChanges(boolean b) {
        this.unsavedChanges = b;
    }

    public void changed() {
        unsavedChanges = true;
        notifySubscribers();
    }



}