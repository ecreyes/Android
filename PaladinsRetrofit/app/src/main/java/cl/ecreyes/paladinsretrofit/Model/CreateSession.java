package cl.ecreyes.paladinsretrofit.Model;

public class CreateSession<T> {
    private String ret_msg;
    private String session_id;
    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getRet_msg() {
        return ret_msg;
    }
    public String getSession_id() {
        return session_id;
    }
}
