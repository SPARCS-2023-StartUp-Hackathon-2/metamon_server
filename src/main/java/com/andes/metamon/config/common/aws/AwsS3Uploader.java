//package com.andes.metamon.config.common.aws;
//
//import com.amazonaws.services.s3.AmazonS3Client;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@Component
//public class AwsS3Uploader {
//
//    private final AmazonS3Client amazonS3Client;
//
//    @Value("${cloud.aws.s3.bucket}")
//    public String bucket;
//
//    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
//        File uploadFile = convert(multipartFile)        // 파일 생성
//                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File convert fail"));
//
//        return upload(uploadFile, dirName);
//    }
//
//    private String upload(File uploadFile, String dirName) {
//        String fileName = dirName + "/" + UUID.randomUUID() + uploadFile.getName();
//        String uploadImageUrl = putS3(uploadFile, fileName);    // s3로 업로드
//        removeNewFile(uploadFile);
//        return uploadImageUrl;
//    }
//
//    // 1. 로컬에 파일생성
//    private Optional<File> convert(Byte file) throws IOException {
//        File convertFile = new File(file.getOriginalFilename());
//        if (convertFile.createNewFile()) {
//            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
//                fos.write(file.getBytes());
//            }
//            return Optional.of(convertFile);
//        }
//
//        return Optional.empty();
//    }
//
//    // 2. S3에 파일업로드
//    private String putS3(File uploadFile, String fileName) {
//        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
//        log.info("File Upload : " + fileName);
//        return amazonS3Client.getUrl(bucket, fileName).toString();
//    }
//
//    // 3. 로컬에 생성된 파일삭제
//    private void removeNewFile(File targetFile) {
//        if (targetFile.delete()) {
//            log.info("File delete success");
//            return;
//        }
//        log.info("File delete fail");
//    }
//
//
//    public void delete(String fileName) {
//        log.info("File Delete : " + fileName);
//        amazonS3Client.deleteObject(bucket, fileName);
//    }
//
//    public String uploadFileV1(String category, MultipartFile multipartFile) {
//        validateFileExists(multipartFile);
//
//        String fileName = CommonUtils.buildFileName(category, multipartFile.getOriginalFilename());
//
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentType(multipartFile.getContentType());
//
//        try (InputStream inputStream = multipartFile.getInputStream()) {
//            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
//                    .withCannedAcl(CannedAccessControlList.PublicRead));
//        } catch (IOException e) {
//            throw new FileUploadFailedException();
//        }
//
//        return amazonS3Client.getUrl(bucketName, fileName).toString();
//    }
//}