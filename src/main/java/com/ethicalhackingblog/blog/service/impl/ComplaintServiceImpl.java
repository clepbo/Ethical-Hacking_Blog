package com.ethicalhackingblog.blog.service.impl;

import com.ethicalhackingblog.blog.model.Complaint;
import com.ethicalhackingblog.blog.repository.ComplaintRepository;
import com.ethicalhackingblog.blog.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repo;
    @Override
    public Complaint saveCompliant(Complaint complaint) {
        return repo.save(complaint);
    }

    @Override
    public List<Complaint> getAllComplaint(Complaint complaint) {
        return repo.findAll();
    }

    @Override
    public Complaint getComplaintById(long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
