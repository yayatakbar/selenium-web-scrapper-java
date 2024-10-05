package org.com.app.Model.Response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class ResponseData implements Serializable {
    private static final long serialVersionUID = -2919415623170868879L;
    private HttpStatus result;
    private Boolean error;
    private String message;
    private HashMap<String, Object> data;

    public void setSuccess(HttpStatus result, boolean error, String message, HashMap<String, Object> data){
        this.result = result;
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public void setNotFound(HttpStatus result, boolean error, String message, HashMap<String, Object> data){
        this.result =  result;
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public void setError(HttpStatus result, boolean error, String message, HashMap<String, Object> data){
        this.result =  result;
        this.error = error;
        this.message = message;
        this.data = data;
    }
}
