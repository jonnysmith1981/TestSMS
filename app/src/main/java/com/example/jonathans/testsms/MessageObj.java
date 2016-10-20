package com.example.jonathans.testsms;

/**
 * Created by jonathans on 20/10/2016.
 */

public class MessageObj extends Object {

    private String _message;
    private String _data;

    public String getMessage() {
        return this._message;
    }

    public void setMessage(String msg) {
        this._message = msg;
    }

    public String getData() {
        return this._message;
    }

    public void setData(String data) {
        this._data = data;
    }

    public String toString() { // Object.toString() is called by listview
        return _message;
    }
}
