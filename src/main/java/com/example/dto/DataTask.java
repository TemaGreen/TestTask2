package com.example.dto;

public class DataTask {
    String type;
    String inputData1;
    String inputData2;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInputData1() {
        return inputData1;
    }

    public void setInputData1(String inputData1) {
        this.inputData1 = inputData1;
    }

    public String getInputData2() {
        return inputData2;
    }

    public void setInputData2(String inputData2) {
        this.inputData2 = inputData2;
    }

    public DataTask(String type, String inputData1, String inputData2) {
        this.type = type;
        this.inputData1 = inputData1;
        this.inputData2 = inputData2;
    }

    public DataTask(String type, String inputData1) {
        this.type = type;
        this.inputData1 = inputData1;
        inputData2 = "";
    }

    public DataTask() {
    }
}
