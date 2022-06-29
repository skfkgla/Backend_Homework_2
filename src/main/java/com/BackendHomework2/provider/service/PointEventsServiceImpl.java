package com.BackendHomework2.provider.service;


import com.BackendHomework2.core.service.PointEventsService;
import com.BackendHomework2.repository.PointEventLogsRepository;
import com.BackendHomework2.repository.ReviewRepository;
import com.BackendHomework2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointEventsServiceImpl implements PointEventsService {
    private UserRepository userRepository;
    private ReviewRepository reviewRepository;
    private PointEventLogsRepository pointEventLogsRepository;


}
