package com.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transactions {

    private Long txn_id;
    private String from_account_no;
    private String to_account_no;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String description;

    public Transactions() {}

    public Transactions(Long txn_id, String from_account_no, String to_account_no, BigDecimal amount, LocalDateTime timestamp) {
        this.txn_id = txn_id;
        this.from_account_no = from_account_no;
        this.to_account_no = to_account_no;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Long getTxn_id() { 
        return txn_id; 
    }

    public void setTxn_id(Long txn_id) { 
        this.txn_id = txn_id; 
    }

    public String getFrom_account_no() { 
        return from_account_no; 
    }

    public void setFrom_account_no(String from_account_no) { 
        this.from_account_no = from_account_no; 
    }

    public String getTo_account_no() { 
        return to_account_no; 
    }

    public void setTo_account_no(String to_account_no) { 
        this.to_account_no = to_account_no; 
    }

    public BigDecimal getAmount() { 
        return amount; 
    }

    public void setAmount(BigDecimal amount) { 
        this.amount = amount; 
    }

    public LocalDateTime getTimestamp() { 
        return timestamp; 
    }

    public void setTimestamp(LocalDateTime timestamp) { 
        this.timestamp = timestamp; 
    }

    public String getDescription() { 
        return description; 
    }

    public void setDescription(String description) { 
        this.description = description; 
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "txn_id=" + txn_id +
                ", from_account_no='" + from_account_no + '\'' +
                ", to_account_no='" + to_account_no + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", description='" + description + '\'' +
                '}';
    }

	public void setFrom_account_id(Long accountId) {
		// TODO Auto-generated method stub
		
	}

	public void setTo_account_id(Long accountId) {
		// TODO Auto-generated method stub
		
	}
}
