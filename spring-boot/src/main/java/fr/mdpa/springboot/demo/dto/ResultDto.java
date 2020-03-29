package fr.mdpa.springboot.demo.dto;

public class ResultDto {

    private Boolean ok;

    public ResultDto() {
    }

    public ResultDto(Boolean ok) {
        this.ok = ok;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}
