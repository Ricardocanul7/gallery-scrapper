package com.gallery_scrapper.gallery_scrapper.otomoto.application;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import com.gallery_scrapper.gallery_scrapper.shared.application.AssetsDownloaderService;
import com.gallery_scrapper.gallery_scrapper.shared.domain.ImageDTO;

@Service
public class OtomotoDownloadGalleryToZip implements AssetsDownloaderService {

    @Override
    public byte[] downloadZip(Set<ImageDTO> imageDTOs, String defaultFilename) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(bos)) {

            int counter = 1;
            for (ImageDTO dto : imageDTOs) {
                URL url = new URL(dto.getSrc());

                BufferedInputStream bis = new BufferedInputStream(url.openStream());
                String fileName = counter + "-" + defaultFilename + ".webp";
                ZipEntry zipEntry = new ZipEntry(fileName);

                zos.putNextEntry(zipEntry);
                IOUtils.copy(bis, zos);
                zos.closeEntry();

                bis.close();
                counter++;
            }

            zos.finish();
            bos.flush();

            return bos.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }

        return null;
    }

}
