package form;

import jakarta.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;


public class FileUploadForm {
    @FormParam("image")
    @PartType("application/octet-stream")
    private byte[] image;

    @FormParam("fileName")
    @PartType("text/plain")
    private String fileName;

    // Getters e Setters
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] file) {
        this.image = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
