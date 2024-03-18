package com.portfolio.model;

import java.util.Date;

public class Session {
    private String ip;
    private Date expDate;
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

    
}
