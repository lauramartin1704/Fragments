package com.example.fragments.Model.Film;

public class FavFilmResponse {
    public int status_code;
    public String status_message;
    boolean success;

    public FavFilmResponse(boolean success, int status_code, String status_message) {
        this.success = success;
        this.status_code = status_code;
        this.status_message = status_message;
    }  public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }
}