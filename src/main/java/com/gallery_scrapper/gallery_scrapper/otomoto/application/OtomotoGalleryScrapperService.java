package com.gallery_scrapper.gallery_scrapper.otomoto.application;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gallery_scrapper.gallery_scrapper.shared.application.ScrapperService;
import com.gallery_scrapper.gallery_scrapper.shared.domain.ImageDTO;

@Service
public class OtomotoGalleryScrapperService implements ScrapperService {
    private final String baseUrl = "https://www.otomoto.pl/osobowe/oferta/%s.html";

    @Override
    public Set<ImageDTO> getMainGallery(String target) {
        Set<ImageDTO> imageDTOs = new HashSet<>();

        extractDataFromOtomotoGallery(imageDTOs, target);

        return imageDTOs;
    }

    private void extractDataFromOtomotoGallery(Set<ImageDTO> imageDTOs, String target) {
        try {
            // Load the HTML
            Document document = Jsoup.connect(String.format(baseUrl, target)).get();

            // Select the element which contains the image list
            Element element = document.select("[data-testid='main-gallery']").first();
            Elements elements = element.getElementsByTag("img");

            // Traversing through the elements
            for (Element galleryItem : elements) {
                ImageDTO imageDTO = new ImageDTO();

                if (StringUtils.hasText(galleryItem.attr("src"))) {
                    imageDTO.setTitle(galleryItem.attr("title"));
                    imageDTO.setSrc(galleryItem.attr("src"));
                    imageDTO.setAlt(galleryItem.attr("alt"));

                    imageDTOs.add(imageDTO);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

}
