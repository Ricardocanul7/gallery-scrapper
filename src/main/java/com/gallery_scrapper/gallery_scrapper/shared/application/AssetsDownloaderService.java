package com.gallery_scrapper.gallery_scrapper.shared.application;

import java.util.Set;

import com.gallery_scrapper.gallery_scrapper.shared.domain.ImageDTO;

public interface AssetsDownloaderService {
    public byte[] downloadZip(Set<ImageDTO> imageDTOs, String defaultFilename);
}
