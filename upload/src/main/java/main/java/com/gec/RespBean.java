package main.java.com.gec;

public class RespBean {
    private Integer code;    //错误代码
    private String msg;     //消息
    private Object data;    //数据

    public static RespBean error(String msg){
        return new RespBean(500,msg);
    }

    public static RespBean ok(String msg){
        return new RespBean(200,msg);
    }

    private RespBean(String msg){
        this.msg=msg;
    }
    private RespBean(Integer code, String msg){
        this.msg=msg;
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getdata() {
        return data;
    }

    public void setdata(Object data) {
        this.data = data;
    }
}
