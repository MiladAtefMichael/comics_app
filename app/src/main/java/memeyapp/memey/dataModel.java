package memeyapp.memey;



public class dataModel {
    String link;
    String by;

    public dataModel() {
    }

    public dataModel(String by,String link) {
       this.link = link;
       this.by=by;
    }

    public String getLink() {
        return link;
    }

    public String getBy() {
        return by;
    }
}
