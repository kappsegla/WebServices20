package x.snowroller;

public class Response {
    public Status status = Status.HTTP_OK;
    public String contentType = "";
    public byte[] content = new byte[0];

    public void setContent(byte[] body) {
        content = body;
    }

    public int getLength(){
        return content.length;
    }
    public void setStatus(Status status){
        this.status = status;
    }
}
