# What's been done

This project is a simple web scrapper that downloads images from otomoto.pl website. It's written in Java using Spring Boot and Jsoup.

> The reason behind the project is that I needed a script to download car images from Otomoto to be able to upload them into the publisher's own website, I was thinking of a small script in python for that but I ended up using Java to improve my skills using this language.

The project consists of three main parts:

1. **ScrapperService**: This is the main service that is responsible for downloading the images. It takes a Target as an argument which can be and id, url (depends on the implementation) and returns a set of `ImageDTO` objects.
2. **ImageDTO**: This is a simple data transfer object that contains information about the image such as the URL, title and alt.
3. **OtomotoGalleryScrapperService**: This is a specific implementation of the `ScrapperService` that is responsible for downloading images from otomoto.pl website. It uses Jsoup to parse the HTML and download the images.
4. The project is open for new implementations using the interfaces **ScrapperService** and **AssetsDownloaderService**

The project also contains a simple REST API that allows you to download the images by providing the URL of the gallery. The API is defined in the `ScrapperController` class.

## Screenshots

Below are some screenshots of the application in action:

### Main page

For the endpoint `localhost:8080/api/gallery/{target}` you will get a list of images presented in json format.

![Main page](github\assets\screenshot_api.png)

### Download page

For the endpoint `localhost:8080/api/gallery/{target}/download` you will be able to download all the car images in the main gallery as a zip file.

![Download page](github\assets\screenshot_zip_file.png)
