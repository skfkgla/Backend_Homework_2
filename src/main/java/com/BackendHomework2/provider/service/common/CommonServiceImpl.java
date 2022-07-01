package com.BackendHomework2.provider.service.common;

import com.BackendHomework2.core.service.common.CommonService;
import com.BackendHomework2.entity.Photo;
import com.BackendHomework2.entity.Review;
import com.BackendHomework2.entity.ReviewEvent;
import com.BackendHomework2.entity.User;
import com.BackendHomework2.repository.PhotoRepository;
import com.BackendHomework2.repository.ReviewEventRepository;
import com.BackendHomework2.repository.ReviewRepository;
import com.BackendHomework2.repository.UserRepository;
import com.BackendHomework2.web.dto.RequestReviewEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {
    private final PhotoRepository photoRepository;
    private final ReviewEventRepository reviewEventRepository;
    private final UserRepository userRepository;

    // TODO 포인트 이력 등록

    @Override
    @Transactional
    public void registerReviewEvents(RequestReviewEvent.ReviewEvent reviewEvnetDto, int mileage) {
        ReviewEvent reviewEvent = ReviewEvent.builder()
                .action(reviewEvnetDto.getAction())
                .reviewId(reviewEvnetDto.getReviewId())
                .pointSize(mileage)
                .build();
        reviewEventRepository.save(reviewEvent);
    }

    // TODO Photo 등록 및 photo 리스트 반환

    @Override
    @Transactional
    public void registerPhoto(List<String> photoIds, String reviewId){
        for(String photoId : photoIds){
            Photo photo = com.BackendHomework2.entity.Photo.builder()
                    .photoId(photoId)
                    .reviewId(reviewId)
                    .build();
            photoRepository.save(photo);
        }
    }
}
