package com.bank.model;

import java.time.LocalDateTime;

public class Reports {

    private Long report_id;
    private String account_no; // <-- replaced customer/account ids with this
    private Long loan_id;
    private String report_type;
    private LocalDateTime generated_on;
    private String file_path;
    private String status;

    public Reports() {}

    public Reports(Long report_id, String account_no, Long loan_id, String report_type,
                   LocalDateTime generated_on, String file_path, String status) {
        this.report_id = report_id;
        this.account_no = account_no;
        this.loan_id = loan_id;
        this.report_type = report_type;
        this.generated_on = generated_on;
        this.file_path = file_path;
        this.status = status;
    }

    public Long getReport_id() {
        return report_id;
    }

    public void setReport_id(Long report_id) {
        this.report_id = report_id;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    // convenience setter the way you wanted
    public void setAccountNo(String accountNo) {
        this.account_no = accountNo;
    }

    public Long getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(Long loan_id) {
        this.loan_id = loan_id;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public LocalDateTime getGenerated_on() {
        return generated_on;
    }

    public void setGenerated_on(LocalDateTime generated_on) {
        this.generated_on = generated_on;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getStatus() { 
        return status; 
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reports{" +
                "report_id=" + report_id +
                ", account_no='" + account_no + '\'' +
                ", loan_id=" + loan_id +
                ", report_type='" + report_type + '\'' +
                ", generated_on=" + generated_on +
                ", file_path='" + file_path + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
