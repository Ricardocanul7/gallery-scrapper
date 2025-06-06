package com.gallery_scrapper.gallery_scrapper.shared.application;

import java.util.Set;

import com.gallery_scrapper.gallery_scrapper.shared.application.exception.InvalidUrlException;
import com.gallery_scrapper.gallery_scrapper.shared.domain.ImageDTO;

public interface ScrapperService {
    public Set<ImageDTO> getMainGallery(String pageId) throws InvalidUrlException;
}
