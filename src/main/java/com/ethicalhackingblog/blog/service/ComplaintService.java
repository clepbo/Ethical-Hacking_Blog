package com.ethicalhackingblog.blog.service;

import com.ethicalhackingblog.blog.model.Complaint;

import java.util.List;

public interface ComplaintService {
    Complaint saveCompliant (Complaint complaint);
    List<Complaint> getAllComplaint (Complaint complaint);
    Complaint getComplaintById(long id);
}
