package x.snowroller;

public enum Status {
    HTTP_OK("200","OK"),
    HTTP_CREATED("201","Created"),
    HTTP_NO_CONTENT("204","No content"),
    HTTP_BAD_REQUEST("400","Bad Request"),
    HTTP_FORBIDDEN("403","Forbidden"),
    HTTP_404("404","Not found"),
    HTTP_TEAPOT("418","I'm a teapot"),
    HTTP_INTERNAL_SERVER_ERROR("500","Internal Server Error"),
    HTTP_NOT_IMPLEMENTED("501","Not Implemented");

    public final String code;
    public final String text;

    private Status(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String statusCode(){
        return code + " " + text;
    }
}
