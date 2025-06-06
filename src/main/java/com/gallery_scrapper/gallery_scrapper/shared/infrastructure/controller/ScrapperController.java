package com.gallery_scrapper.gallery_scrapper.shared.infrastructure.controller;

import org.springframework.web.bind.annotation.RestController;

import com.gallery_scrapper.gallery_scrapper.shared.application.AssetsDownloaderService;
import com.gallery_scrapper.gallery_scrapper.shared.application.ScrapperService;
import com.gallery_scrapper.gallery_scrapper.shared.application.exception.InvalidUrlException;
import com.gallery_scrapper.gallery_scrapper.shared.domain.ImageDTO;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/api/")
public class ScrapperController {
    private final Map<String, ScrapperService> scrapperServices;
    private final Map<String, AssetsDownloaderService> assetsDownloaderServices;

    public ScrapperController(
            Map<String, ScrapperService> scrapperServices,
            Map<String, AssetsDownloaderService> assetsDownloaderServices) {
        this.scrapperServices = scrapperServices;
        this.assetsDownloaderServices = assetsDownloaderServices;
    }

    @GetMapping(path = "/gallery/{target}")
    public Set<ImageDTO> getMainGallery(@PathVariable String target) {
        try {
            ScrapperService concreteScrapperService = scrapperServices
                    .get("otomotoGalleryScrapperService");

            return concreteScrapperService.getMainGallery(target);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }

    @GetMapping(path = "/gallery/{target}/download")
    public ResponseEntity<byte[]> download(@PathVariable String target) {
        ScrapperService concreteScrapperService = scrapperServices
                .get("otomotoGalleryScrapperService");
        AssetsDownloaderService concreteAssetService = assetsDownloaderServices
                .get("otomotoDownloadGalleryToZip");

        System.out.println(concreteAssetService);

        try {
            Set<ImageDTO> dtos = concreteScrapperService.getMainGallery(target);
            byte[] byteContent = concreteAssetService.downloadZip(dtos, target);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM) // Tipo MIME para archivos binarios genéricos
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"downloaded_images.zip\"")
                    .body(byteContent);
        } catch (InvalidUrlException ex) {
            System.out.println(ex);
        }

        return null;
    }
}
