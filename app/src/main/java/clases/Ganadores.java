package clases;

import java.util.List;

public class Ganadores {

    private Boolean status;
    private List<Nombres> data;

    public Ganadores(Boolean status, List<Nombres> data)
    {
        this.status = status;
        this.data = data;
    }
    public List<Nombres> getData() {
        return data;
    }

    public void setResults(List<Nombres> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


}
